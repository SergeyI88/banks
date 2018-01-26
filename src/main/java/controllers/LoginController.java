//package controllers;
//
//import db.pojo.UserData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.servlet.ModelAndView;
//import services.ServiceAuth;
//
//import java.sql.SQLException;
//
//@Controller
//@SessionAttributes("user")
//public class LoginController {
//    @Autowired
//    ServiceAuth serviceAuth;
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ModelAndView loginPage(@RequestParam("username") String login,
//                                  @RequestParam("userpass") String pass) {
//        ModelAndView modelAndView = new ModelAndView("login");
//        UserData userData = new UserData();
//        System.out.println(login);
//        System.out.println(pass);
//        int id;
//        try {
//           id = serviceAuth.auth(login, pass);
//            System.out.println(id);
//            userData.setId(id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        modelAndView.addObject("user", userData);
//        return modelAndView;
//    }
//}
