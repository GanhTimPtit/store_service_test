package com.ptit.edu.store.customer.controller;

import com.ptit.edu.store.auth.dao.UserRepository;
import com.ptit.edu.store.auth.models.body.CustomerRegisterBody;
import com.ptit.edu.store.auth.models.data.User;
import com.ptit.edu.store.constants.Constant;
import com.ptit.edu.store.customer.dao.CustomerRespository;
import com.ptit.edu.store.customer.dao.FeedbackRepository;
import com.ptit.edu.store.customer.dao.RateRepository;
import com.ptit.edu.store.customer.dao.SaveClothesRepository;
import com.ptit.edu.store.customer.models.body.ProfileBody;
import com.ptit.edu.store.customer.models.body.RateBody;
import com.ptit.edu.store.customer.models.body.SetOrderBody;
import com.ptit.edu.store.customer.models.data.Customer;
import com.ptit.edu.store.customer.models.data.Feedback;
import com.ptit.edu.store.customer.models.data.Rate;
import com.ptit.edu.store.customer.models.view.HeaderProfile;
import com.ptit.edu.store.customer.models.view.OrderPreview;
import com.ptit.edu.store.customer.models.view.Profile;
import com.ptit.edu.store.customer.models.view.SaveClothesPreview;
import com.ptit.edu.store.product.dao.ClothesRepository;
import com.ptit.edu.store.product.dao.OrderRepository;
import com.ptit.edu.store.customer.models.body.OrderBody;
import com.ptit.edu.store.product.models.data.Clothes;
import com.ptit.edu.store.customer.models.data.Order;
import com.ptit.edu.store.product.models.data.SaveClothes;
import com.ptit.edu.store.response_model.*;
import com.ptit.edu.store.utils.PageAndSortRequestBuilder;
import com.ptit.edu.store.utils.PasswordValidate;
import com.ptit.edu.store.utils.SendEmailUtils;
import com.ptit.edu.store.utils.UserDecodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/customers")
@Api(value = "candidate-api", description = "Nhóm API Customer, Yêu cầu access token của Khách hàng")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    CustomerRespository customerRespository;
    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    RateRepository rateRepository;
    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    SaveClothesRepository saveClothesRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;


    @ApiOperation(value = "Đăng ký tài khoản khách hàng", response = Iterable.class)
    @PostMapping("/register")
    public Response register(@ApiParam(name = HeaderConstant.AUTHORIZATION, value = "username+\":\"+password, lấy kết quả encode theo Base64, sau đó thêm \"Basic \" + kết quả", required = true)
                             @RequestHeader(value = HeaderConstant.AUTHORIZATION) String encodedString, @ApiParam(name = "customerRegisterBody", value = "Tên đầy đủ KH", required = true)
                             @Valid @RequestBody CustomerRegisterBody customerRegisterBody) {
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
                u.setRole(RoleConstants.CUSTOMER);
                u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
                u.setActived(false);
                Customer customer = new Customer();
                customer.updatecontruct(u, customerRegisterBody);
                customer = customerRespository.save(customer);
                u = customer.getUser();
                u.setDataID(customer.getId());

                userRepository.save(u);

                SendEmailUtils.sendEmailActiveAccount(u.getUsername());
                response = new OkResponse(u.getUsername());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    @ApiOperation(value = "Lấy Lấy avatar + email + tên Khách hàng", response = Iterable.class)
    @GetMapping("/headerProfiles/{id}")
    Response getHeaderProfile(@PathVariable("id") String customerID) {
        Response response;
        try {
            HeaderProfile headerProfile = customerRespository.getHeaderProfile(customerID);
            response = new OkResponse(headerProfile);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    @GetMapping("/profiles/{id}")
    Response getProfile(@PathVariable("id") String customerID) {
        Response response;
        try {
            Profile profile = customerRespository.getProfile(customerID);
            response = new OkResponse(profile);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    @ApiOperation(value = "Lấy Lấy avatar + email + tên Khách hàng", response = Iterable.class)
    @PutMapping("profiles/{id}")
    Response updateProfile(@PathVariable("id") String customerID, @RequestBody ProfileBody profileBody) {
        Response response;
        try {
            Customer customer = customerRespository.findOne(customerID);
            if (customer == null) {
                return new NotFoundResponse("Customer not exist!");
            }
            customer.update(profileBody);
            customerRespository.save(customer);
            Profile profile = new Profile(customer);
            response = new OkResponse(profile);
        } catch (Exception e) {
            e.printStackTrace();
            ;
            response = new ServerErrorResponse();
        }
        return response;
    }


    //Gui phan hoi
    @ApiOperation(value = "Gưi phản hồi từ khách hàng", response = Iterable.class)
    @PostMapping("/{customerID}/feedback")
    Response feedback(@PathVariable("customerID") String customerID,
                      @RequestBody String content) {
        Response response;

        try {
            Customer customer = customerRespository.findOne(customerID);
            if (customer == null) {
                return new NotFoundResponse("Customer not existed!");
            }
            Feedback feedback = new Feedback(customer, content);
            feedbackRepository.save(feedback);
            response = new OkResponse();
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    //Danh gia shop
    @ApiOperation(value = "Đánh giá cửa hàng", response = Iterable.class)
    @PutMapping("/{customerID}/rate")
    Response rateShop(@PathVariable("customerID") String customerID,
                      @RequestBody RateBody rateBody) {
        Response response;
        try {
            Customer customer = customerRespository.findOne(customerID);
            if (customer == null) {
                return new NotFoundResponse("Customer not found");
            }
            Rate rate = new Rate(customer, rateBody);
            rateRepository.save(rate);
            response = new OkResponse("Success");
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    //Cap nhat mo ta
    @ApiOperation(value = "api Cập nhật mô tả khách hàng", response = Iterable.class)
    @PostMapping("/{customerID}/description")
    Response updateDescription(@PathVariable("customerID") String customerID, @RequestBody String description) {
        Response response;
        try {
            if (customerRespository.findOne(customerID) == null) {
                return new NotFoundResponse("Customer not exist");
            }
            customerRespository.updateDescription(customerID, description);
            response = new OkResponse(description);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    /**********************SaveClothes********************/
    @ApiOperation(value = "Api lưu sản phẩm", response = Iterable.class)
    @GetMapping("/{customerID}/save_clothes")
    public Response saveClothes(@PathVariable("customerID") String customerID,
                                @ApiParam(name = "sortBy", value = "Trường cần sort, mặc định là " + SaveClothes.SAVED_DATE)
                                @RequestParam(value = "sortBy", defaultValue = SaveClothes.SAVED_DATE) String sortBy,
                                @ApiParam(name = "sortType", value = "Nhận (asc | desc), mặc định là desc")
                                @RequestParam(value = "sortType", defaultValue = "desc") String sortType,
                                @ApiParam(name = "pageIndex", value = "Index trang, mặc định là 0")
                                @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                                @ApiParam(name = "pageSize", value = "Kích thước trang, mặc đinh và tối đa là " + Constant.MAX_PAGE_SIZE)
                                @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        Response response;
        try {
            Customer customer = customerRespository.findOne(customerID);
            if (customer == null) {
                return new NotFoundResponse("Customer not Exist");
            }

            Pageable pageable = PageAndSortRequestBuilder
                    .createPageRequest(pageIndex, pageSize, sortBy, sortType, Constant.MAX_PAGE_SIZE);
            Page<SaveClothesPreview> saveClothesPreviews = saveClothesRepository.getAllSavedClothes(customerID, pageable);

            response = new OkResponse(saveClothesPreviews);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    //Lay chi tiet clothes
    @ApiOperation(value = "Lấy chi tiết quần áo", response = Iterable.class)
    @GetMapping("/clothes/{id}")
    public Response getDetailClothes(@PathVariable("id") String clothesID) {
        Response response;
        try {
            response = new OkResponse(clothesRepository.findById(clothesID));
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }
    @ApiOperation(value = "Api Lưu quần áo", response = Iterable.class)
    @PutMapping("/{customerID}/save_clothes/{id}")
    public Response saveClothes(@PathVariable("customerID") String customerID,
                                @PathVariable("id") String clothesID) {
        Response response;
        try {
            Customer customer = customerRespository.findOne(customerID);
            if (customer == null) {
                return new NotFoundResponse("Customer not Exist");
            }
            Clothes clothes = clothesRepository.findById(clothesID);
            if (clothes == null) {
                return new NotFoundResponse("Clothes not Exist");
            }

            clothes.addSave();
            clothesRepository.save(clothes);

            saveClothesRepository.save(new SaveClothes(clothes, customer));
            response = new OkResponse();
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    @Transactional
    void updateSaveClothes(String customerID, String clothesID) {
//        saveClothesRepository.updateState(customerID,clothesID);
    }
    @ApiOperation(value = "Api hủy lưu sản phẩm", response = Iterable.class)
    @DeleteMapping("/{customerID}/save_clothes/{id}")
    public Response deleteSaveClothes(@PathVariable("customerID") String customerID,
                                      @PathVariable("id") String clothesID,
                                      @ApiParam(name = "pageIndex", value = "index trang, mặc định là 0")
                                      @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                                      @ApiParam(name = "pageSize", value = "Kích thước trang, mặc định và tối đa là " + Constant.MAX_PAGE_SIZE)
                                      @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                      @ApiParam(name = "sortBy", value = "Trường cần sort, mặc định là : " + SaveClothes.SAVED_DATE)
                                      @RequestParam(value = "pageIndex", defaultValue = SaveClothes.SAVED_DATE) String sortBy,
                                      @ApiParam(name = "sortType", value = "Nhận asc|desc, mặc đính là desc")
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "desc") String sortType) {
        Response response;
        try {

            Customer customer = customerRespository.findOne(customerID);
            if (customer == null) {
                return new NotFoundResponse("Customer not Exist");
            }
            Clothes clothes = clothesRepository.findById(clothesID);
            if (clothes == null) {
                return new NotFoundResponse("Clothes not Exist");
            }

            clothes.subSave();
            clothesRepository.save(clothes);
            saveClothesRepository.deleteByCustomer_idAndAndClothes_Id(customer.getId(), clothesID);

            Pageable pageable = PageAndSortRequestBuilder
                    .createPageRequest(pageIndex, pageSize, sortBy, sortType, Constant.MAX_PAGE_SIZE);
            Page<SaveClothesPreview> saveClothesPreviews = saveClothesRepository.getAllSavedClothes(customerID, pageable);

            response = new OkResponse(saveClothesPreviews);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    //Order
    @ApiOperation(value = "Api tạo order cho khách hàng", response = Iterable.class)
    @PostMapping("/{customerID}/orders/{clothesID}")
    public Response insertOrder(@PathVariable("customerID") String customerID,
                                @PathVariable("clothesID") String clothesID,
                                @RequestBody OrderBody orderBody) {
        Response response;
        try {
            Customer customer = customerRespository.findOne(customerID);
            if (customer == null) {
                return new NotFoundResponse("Customer not Exist");
            }

            Clothes clothes = clothesRepository.findOne(clothesID);
            if (clothes == null) {
                return new NotFoundResponse("Customer not Exist");
            }

            Order order = new Order(clothes, customer, orderBody);
            orderRepository.save(order);
            response = new OkResponse();
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }//Order
    @ApiOperation(value = "Api tạo order cho khách hàng", response = Iterable.class)
    @PutMapping("/{customerID}/orders")
    public Response insertOrder(@PathVariable("customerID") String customerID,
                                @RequestBody Set<SetOrderBody> orderBodies) {
        Response response;
        try {
            Customer customer = customerRespository.findOne(customerID);
            if (customer == null) {
                return new NotFoundResponse("Customer not Exist");
            }

            for(SetOrderBody setOrderBody:orderBodies){
                Clothes clothes = clothesRepository.findOne(setOrderBody.getClothesID());
                if(clothes!=null){
                    Order order = new Order(clothes,customer,setOrderBody);
                    orderRepository.save(order);
                }
            }
            response = new OkResponse();
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    @ApiOperation(value = "Lay tat ca cac don dat hang cua khach hang")
    @GetMapping("/{customerID}/orders")
    public Response getAllOrder(@PathVariable("customerID") String customerID,
                                @ApiParam(name = "sortBy", value = "Trường cần sort, mặc định là : " + Order.CREATED_DATE)
                                @RequestParam(value = "pageIndex", defaultValue = Order.CREATED_DATE) String sortBy,
                                @ApiParam(name = "sortType", value = "Nhận asc|desc, mặc đính là desc")
                                @RequestParam(value = "pageSize", required = false, defaultValue = "desc") String sortType,
                                @ApiParam(name = "pageIndex", value = "index trang, mặc định là 0")
                                @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                                @ApiParam(name = "pageSize", value = "Kích thước trang, mặc định và tối đa là " + Constant.MAX_PAGE_SIZE)
                                @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        Response response;
        try {
            Customer customer = customerRespository.findOne(customerID);
            if (customer == null) {
                return new NotFoundResponse("Customer not Exist");
            }
            Pageable pageable = PageAndSortRequestBuilder.createPageRequest(pageIndex, pageSize, Order.CREATED_DATE, "desc", Constant.MAX_PAGE_SIZE);
            Page<OrderPreview> orderPreviews = orderRepository.getAllOrderPreview(customerID, pageable);

            response = new OkResponse(orderPreviews);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }
}
