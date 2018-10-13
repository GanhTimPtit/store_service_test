package com.ptit.edu.store.auth.controller;

import com.ptit.edu.store.admin.dao.AdminRepository;
import com.ptit.edu.store.admin.models.Admin;
import com.ptit.edu.store.admin.models.body.AdminRegisterBody;
import com.ptit.edu.store.auth.dao.UserRepository;
import com.ptit.edu.store.auth.models.body.FacebookLoginBody;
import com.ptit.edu.store.auth.models.data.*;
import com.ptit.edu.store.auth.models.body.CustomerRegisterBody;
import com.ptit.edu.store.auth.models.body.NewPassword;
import com.ptit.edu.store.auth.models.models_view.FacebookUserInfo;
import com.ptit.edu.store.constants.ApplicationConstant;
import com.ptit.edu.store.constants.Constant;
import com.ptit.edu.store.constants.DataValidator;
import com.ptit.edu.store.customer.dao.CustomerRespository;
import com.ptit.edu.store.customer.models.data.Customer;
import com.ptit.edu.store.customer.models.view.HeaderProfile;
import com.ptit.edu.store.response_model.*;
import com.ptit.edu.store.utils.*;
import io.swagger.annotations.*;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Api(value = "auth-api", description = "Nhóm API đăng nhập và cấp access token, Không yêu cầu access token")
public class AuthController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomerRespository customerRespository;
    @Autowired
    AdminRepository adminRepository;


    @ApiOperation(value = "api đăng nhập cho khách hàng", response = Iterable.class)
    @PostMapping("/customer/login")
    public Response CustomerLogin(@ApiParam(name = "encodedString", value = "username+\":\"+password, lấy kết quả encode theo Base64, sau đó thêm \"Basic \" + kết quả")
                                  @RequestHeader(HeaderConstant.AUTHORIZATION) String encodedString,
                                  @RequestBody(required = false) String gcmToken) {
        Response response;
        try {
            User user = UserDecodeUtils.decodeFromAuthorizationHeader(encodedString);
            if(userRepository.findByUsername(user.getUsername()) == null){
                return new NotFoundResponse("Account not exist");
            }
            if (!userRepository.isAccountActivated(user.getUsername(), RoleConstants.CUSTOMER)) {
                response = new ForbiddenResponse(ResponseConstant.ErrorMessage.ACCOUNT_NOT_VERIFIED);
            } else {
//                User login = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//                   if (login != null) {
//                       Customer customer = customerRespository.findByUser_Id(login.getId());
//                       HeaderProfile profile = new HeaderProfile(customer.getId(), customer.getFullName(), customer.getAvatarUrl(), user.getUsername());
//                       response = new OkResponse(profile);
//                } else {
//                    response = new Response(HttpStatus.UNAUTHORIZED, ResponseConstant.Vi.WRONG_EMAIL_OR_PASSWORD);
//                }
                response = login(user.getUsername(), user.getPassword(), gcmToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new Response(HttpStatus.UNAUTHORIZED, ResponseConstant.Vi.WRONG_EMAIL_OR_PASSWORD);
        }

        return response;
    }
    private Response login(String username, String password,String gcmToken) {
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", username);
        body.add("password", password);

        JWTToken token = new HttpPostRequestBuilder(restTemplate)
                .withUrl(ApplicationConstant.LOCAL_HOST + "/oauth/token")
                .setContentType(MediaType.APPLICATION_FORM_URLENCODED)
                .addToHeader(HeaderConstant.AUTHORIZATION, HeaderConstant.AUTHORIZATION_VALUE_PREFIX + Base64Utils.encode("trusted-app:secret"))
                .setFormDataBody(body)
                .execute(JWTToken.class);

        TokenData tokenData = new TokenData(token.getAccess_token(),
                token.getRefresh_token(),
                token.getExpires_in());
        return new OkResponse(tokenData);
    }

    @ApiOperation(value = "Đăng nhập bằng facebook cho customer", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Đăng nhập thành công")
    })
    @PostMapping("/customer/facebook")
    public Response candidateFacebookLogin(@RequestBody FacebookLoginBody facebookLoginBody) {
        try {
            return facebookLogin(facebookLoginBody.getAccessToken(), RoleConstants.CUSTOMER,facebookLoginBody.getFcmToken());
        } catch (Exception e) {
            return new ServerErrorResponse();
        }
    }
    private Response facebookLogin(String accessToken, String role,String fcmToken) {
        FacebookUserID facebookUserID = new HttpGetRequestBuilder(restTemplate)
                .withParam(Constant.FIELDS, FacebookUser.ID)
                .withParam(Constant.ACCESS_TOKEN, accessToken)
                .withProtocol(HttpGetRequestBuilder.HTTPS)
                .withUrl("graph.facebook.com/v2.11/me")
                .execute(FacebookUserID.class);

        String userID = facebookUserID.getId();
        User user = userRepository.findByUsername(userID);
        if (user == null) {
            user = registerFacebookUser(userID, accessToken, role);
        }
        if (!user.getActived()) {
            Customer customer = customerRespository.findOne(user.getDataID());
            FacebookUserInfo facebookUserInfo = new FacebookUserInfo(customer);
            return new UnAuthorizationResponse(ResponseConstant.ErrorMessage.ACCOUNT_NOT_VERIFIED, facebookUserInfo);
        } else {
            return login(userID, userID,fcmToken);
        }
    }

    private User registerFacebookUser(String username, String accessToken, String role) {
        FacebookUser facebookUser = new HttpGetRequestBuilder(restTemplate)
                .withParam(Constant.FIELDS, FacebookUser.ID,
                        FacebookUser.EMAIL,
                        FacebookUser.BIRTHDAY,
                        FacebookUser.GENDER,
                        FacebookUser.FULL_NAME,
                        FacebookUser.FIRST_NAME,
                        FacebookUser.LAST_NAME,
                        FacebookUser.COVER)
                .withParam(Constant.ACCESS_TOKEN, accessToken)
                .withProtocol(HttpGetRequestBuilder.HTTPS)
                .withUrl("graph.facebook.com/v2.11/me")
                .execute(FacebookUser.class);

        FacebookAvatar facebookAvatar = new HttpGetRequestBuilder(restTemplate)
                .withProtocol(HttpGetRequestBuilder.HTTPS)
                .withParam(FacebookAvatar.TYPE, FacebookAvatar.LARGE)
                .withParam(FacebookAvatar.REDIRECT, false)
                .withParam(Constant.ACCESS_TOKEN, accessToken)
                .withUrl("graph.facebook.com/v2.11/me/picture")
                .execute(FacebookAvatar.class);

        User user = new User();
        user.setAccountType(Constant.FACEBOOK);
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(username));
        user.setRole(role);
        user = userRepository.save(user);

        switch (role) {
            case RoleConstants.CUSTOMER: {
                Customer customer = new Customer();
                customer.setUser(user);
                customer.setLastName(facebookUser.getLast_name());
                customer.setFirstName(facebookUser.getFirst_name());
                customer.setBirthday(facebookUser.getBirthday());
                customer.setEmail(facebookUser.getEmail());
                customer.setGender(DataValidator.getGender(facebookUser.getGender()));
                if (facebookAvatar != null) {
                    customer.setAvatarUrl(facebookAvatar.getData().getUrl());
                }
                FacebookCover facebookCover = facebookUser.getCover();
                if (facebookCover != null) {
                    customer.setCoverUrl(facebookCover.getSource());
                }
                customer = customerRespository.save(customer);
                user.setDataID(customer.getId());
                user = userRepository.save(user);
            }
            break;

        /*    case RoleConstant.EMPLOYER: {
                Employer employer = new Employer();
                employer.setUser(user);
                employer.setEmployerName(facebookUser.getName());
                if (facebookAvatar != null) {
                    employer.setLogoUrl(facebookAvatar.getData().getUrl());
                }
                FacebookCover facebookCover = facebookUser.getCover();
                if (facebookCover != null) {
                    employer.setCoverUrl(facebookCover.getSource());
                }
                employer = employerRepository.save(employer);
                user.setDataID(employer.getId());
                user = userRepository.save(user);
            }
            break;*/

            default: {
                break;
            }
        }
        return user;
    }
//    @ApiOperation(value = "Cập nhật thông tin cho tài khoản đăng ký bằng facebook", response = Iterable.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Đăng nhập thành công", response = TokenOkResponseModel.class),
//            @ApiResponse(code = 401, message = "Tài khoản cần đăng ký đủ thông tin", response = FacebookAccountNotVerifiedResponseModel.class)
//    })
//    @PostMapping("/candidates/facebook/confirm")
//    public Response confirmFacebookLogin(@Valid @RequestBody FacebookUserRegisterBody facebookUserRegisterBody) {
//        try {
//            FacebookUserID facebookUserID = new HttpGetRequestBuilder(restTemplate)
//                    .withParam(Constant.FIELDS, FacebookUser.ID)
//                    .withParam(Constant.ACCESS_TOKEN, facebookUserRegisterBody.getAccessToken())
//                    .withProtocol(HttpGetRequestBuilder.HTTPS)
//                    .withUrl("graph.facebook.com/v2.11/me")
//                    .execute(FacebookUserID.class);
//
//            User user = userRepository.findByUsername(facebookUserID.getId());
//            Candidate candidate = candidateRepository.findById(user.getDataID());
//            candidate.update(facebookUserRegisterBody);
//
//            if (!GoogleLocationService.updateRegionAndAddressFor(candidate, regionRepository, restTemplate)) {
//                return new NotFoundResponse(ResponseConstant.En.REGION_NOT_SUPPORTED);
//            }
//
//            candidateRepository.save(candidate);
//            user.setIsActivated(true);
//            userRepository.save(user);
//
//            return login(user.getUsername(), user.getUsername(),facebookUserRegisterBody.getGcmToken());
//        } catch (Exception e) {
//            return new ServerErrorResponse();
//        }
//    }
    @ApiOperation(value = "Api đăng nhập cho admin", response = Iterable.class)
    @PostMapping("/admin/login")
    public Response AdminLogin(@ApiParam(name = "encodedString", value = "username+\":\"+password, lấy kết quả encode theo Base64, sau đó thêm \"Basic \" + kết quả")
                               @RequestHeader(HeaderConstant.AUTHORIZATION) String encodedString) {
        Response response;
        try {
            User user = UserDecodeUtils.decodeFromAuthorizationHeader(encodedString);
            if (!userRepository.isAccountActivated(user.getUsername(), RoleConstants.ADMIN)) {
                response = new ForbiddenResponse(ResponseConstant.ErrorMessage.ACCOUNT_NOT_VERIFIED);
            } else {
                User login = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
                if (login != null) {
                    response = new OkResponse();
                } else {
                    response = new Response(HttpStatus.UNAUTHORIZED, ResponseConstant.Vi.WRONG_EMAIL_OR_PASSWORD);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

//    @ApiOperation(value = "Đăng ký tài khoản khách hàng", response = Iterable.class)
//    @PostMapping("/customers/register")
//    public Response register(@ApiParam(name = HeaderConstant.AUTHORIZATION, value = "username+\":\"+password, lấy kết quả encode theo Base64, sau đó thêm \"Basic \" + kết quả", required = true)
//                             @RequestHeader(value = HeaderConstant.AUTHORIZATION) String encodedString, @ApiParam(name = "customerRegisterBody", value = "Tên đầy đủ KH", required = true)
//                             @Valid @RequestBody CustomerRegisterBody customerRegisterBody) {
//        Response response;
//        try {
//            User u = UserDecodeUtils.decodeFromAuthorizationHeader(encodedString);
//            if (!PasswordValidate.isPasswordValidate(u.getPassword())) {
//                return new ForbiddenResponse(ResponseConstant.ErrorMessage.PASSWORD_TOO_SHORT);
//            }
//
//            User user = userRepository.findByUsername(u.getUsername());
//            if (user != null) {
//                return new ResourceExistResponse("Tai khoan da ton tai!");
//            } else {
//                user = new User();
//                user.setPassword(u.getPassword());
//                user.setUsername(u.getUsername());
//                user.setRole(RoleConstants.CUSTOMER);
//                user.setActived(false);
//
//                userRepository.save(user);
//
//                Customer customer = new Customer();
//                customer.setUser(user);
//                customer.setFullName(customerRegisterBody.getFullName());
//                customer.setAddress(customerRegisterBody.getAddress());
//                customer.setPhone(customerRegisterBody.getPhone());
//
//                customerRespository.save(customer);
//
//                SendEmailUtils.sendEmailActiveAccount(u.getUsername());
//                response = new OkResponse(u.getUsername());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            response = new ServerErrorResponse();
//        }
//        return response;
//    }

    @ApiOperation(value = "Đăng ký tài khoản admin", response = Iterable.class)
    @PostMapping("/admins/register")
    public Response adminRegister(@ApiParam(name = HeaderConstant.AUTHORIZATION, value = "username+\":\"+password, lấy kết quả encode theo Base64, sau đó thêm \"Basic \" + kết quả", required = true)
                                  @RequestHeader(value = HeaderConstant.AUTHORIZATION) String encodedString, @ApiParam(name = "adminRegisterBody", value = "Tên đầy đủ KH", required = true)
                                  @Valid @RequestBody AdminRegisterBody adminRegisterBody) {
        Response response;
        try {
            User u = UserDecodeUtils.decodeFromAuthorizationHeader(encodedString);
            if (!PasswordValidate.isPasswordValidate(u.getPassword())) {
                return new ForbiddenResponse(ResponseConstant.ErrorMessage.PASSWORD_TOO_SHORT);
            }

            User user = userRepository.findByUsername(u.getUsername());
            if (user != null) {
                return new ResourceExistResponse("Tai khoan da ton tai!");
            } else {
                user = new User();
                user.setPassword(u.getPassword());
                user.setUsername(u.getUsername());
                user.setRole(RoleConstants.ADMIN);
                user.setActived(true);

                userRepository.save(user);

                Admin admin = new Admin();
                admin.setFullName(adminRegisterBody.getFullName());
                admin.setPosition(adminRegisterBody.getPosition());

                adminRepository.save(admin);

//                SendEmailUtils.sendEmailrequest(u.getUsername());
                response = new OkResponse();
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    @ApiOperation(value = "Xac nhan email", response = Iterable.class)
    @GetMapping("/registration/confirm/{username}")
    public Response confirm_email(@PathVariable("username") String username) {
        Response response;
        try {
            username += ".com";
            if (userRepository.findByUsername(username) == null) {
                return new NotFoundResponse("Email khong ton tai !");
            }
            userRepository.activeAccount(true, username);
            response = new OkResponse();
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    @ApiOperation(value = "Xac nhan email", response = Iterable.class)
    @PostMapping("/resend/registration/confirm/{username}")
    public Response resend_confirm_email(@PathVariable("username") String username) {
        Response response;
        try {
            username += ".com";
            SendEmailUtils.sendEmailActiveAccount(username);
            response = new OkResponse();
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    @ApiOperation(value = "Đổi mật khẩu", response = Iterable.class)
    @PostMapping("/customer/{customerID}/newPassword")
    public Response changePassword(@PathVariable("customerID") String customerID,
                                   @Valid @RequestBody NewPassword password) {
        Response response;
        try {
            Customer customer = customerRespository.findOne(customerID);
            if (customer == null) {
                return new NotFoundResponse("Customer not Exist");
            }
            User u = customer.getUser();
            if (u.getPassword().matches(password.getOldPassword())) {
                u.setPassword(password.getNewPassword());
                userRepository.save(u);
                response = new OkResponse();
            } else {
                response = new Response(HttpStatus.CONFLICT, ResponseConstant.Vi.OLD_PASSWORD_MISMATCH);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    @ApiOperation(value = "Quên mật khẩu (Gửi lại email reset mật khẩu)", response = Iterable.class)
    @PostMapping("/customer/{id}/reset_password")
    public Response sendEmailToRessetPassword(
            @PathVariable("id") String customerID,
            @Valid @RequestBody String email) {
        Response response;
        try {
            Customer customer = customerRespository.findOne(customerID);
            if(customer==null){
                return new NotFoundResponse("Customer not Exist");
            }
            if(!EmailValidate.validate(email)){
                return new Response(HttpStatus.GONE,ResponseConstant.ErrorMessage.INVALID_EMAIL);
            }

            User u = customer.getUser();
            RandomString randomString = new RandomString();
            String resetPassword = randomString.nextString();
            u.setPassword(resetPassword);
            userRepository.save(u);
            SendEmailUtils.sendEmailResetPassword(email,resetPassword);
            response = new OkResponse();
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

}
