package controllers;

import com.google.gson.Gson;
//import configs.UserInside;
import configs.UserInside;
import db.pojo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import services.ServiceAuth;

import java.sql.SQLException;

@Controller
@SessionAttributes("user")
public class ControllerIndex {
    @Autowired
    ServiceAuth serviceAuth;


    @RequestMapping("/index")
    public ModelAndView index() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = securityContext.getAuthentication();
        User user = ((User) auth.getPrincipal());
        UserData userInside = new UserData();

        try {
            userInside.setId(serviceAuth.getIdUserDataByLogin(user.getUsername()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(userInside);
        if (userInside != null) {
                String role = user.getAuthorities().toString();
            System.out.println(role);
            if (role.equals("[role_user]")) {
                ModelAndView modelAndView = new ModelAndView("index");
                UserData userData = new UserData();
                try {
                    userData.setId(serviceAuth.getIdUserDataByLogin(user.getUsername()));
                    modelAndView.addObject("user", userData);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                modelAndView.addObject("isLogin", true);
                return modelAndView;
            } else {
                ModelAndView modelAndView = new ModelAndView("forward:/inner/boardMan");
                UserData userData = new UserData();
                try {
                    userData.setId(serviceAuth.getIdUserDataByLogin(user.getUsername()));
                    modelAndView.addObject("user", userData);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return modelAndView;
            }
        }
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
