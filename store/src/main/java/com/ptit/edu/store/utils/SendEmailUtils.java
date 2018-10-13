package com.ptit.edu.store.utils;


//import net.bytebuddy.utility.RandomString;
//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.Email;
//import org.apache.commons.mail.SimpleEmail;

public class SendEmailUtils {
    public static void SendMail(String to) {
//        String from = "tuaananh.khong.96@gmail.com";
//
//
//        Properties properties = System.getProperties();
//
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//        Session session = Session.getInstance(properties,
//                new javax.mail.Authenticator() {
//                    @Override
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication("tuaananh.khong.96@gmail.com", "tuananh96");
//                    }
//                });
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO,
//                    new InternetAddress(to));
//            message.setSubject("ThuyAnhStore Registration Confirmation");
//            message.setText("Click to the link below to verify your account");
//            message.setContent("<a>localhost:1996/registration-confirm/</a>",
//                    "text/html");
//            Transport.send(message);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

        public static void sendEmailActiveAccount (String to){
//        try {
//
//            Email email = new SimpleEmail();
//            email.setHostName("smtp.googlemail.com");
//            email.setSmtpPort(465);
//            email.setAuthenticator(new DefaultAuthenticator("tuaananh.khong.96@gmail.com", "tuananh96"));
//
//            email.setSSLOnConnect(true);
//            email.setFrom("tuaananh.khong.96@gmail.com");
//            email.addTo(to);
//
//            email.setMsg("Click to the link below to verify your account : localhost:1995/api/auths/registration/confirm/" + to);
//            email.setSubject("ThuyAnhStore Registration Confirmation ");
//
//            email.setHtmlMsg("<html>" +
//                    "<h2>Click to the link below to verify your account</h2>" +
//                    "<a>localhost:1996/api/auth/registration-confirm</a>" +
//                    "</html>");

//            email.send();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        }

        public static void sendEmailResetPassword (String to, String resetPassword){
//        try {
//
//            Email email = new SimpleEmail();
//            email.setHostName("smtp.googlemail.com");
//            email.setSmtpPort(465);
//            email.setAuthenticator(new DefaultAuthenticator("tuaananh.khong.96@gmail.com", "tuananh96"));
//
//            email.setSSLOnConnect(true);
//            email.setFrom("tuaananh.khong.96@gmail.com");
//            email.addTo(to);
//
//            email.setMsg("Password Reset Is : " + resetPassword);
//            email.setSubject("ThuyAnhStore Reset Password");
//
//
//            email.send();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        }


}
