package controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ServiceReg;

import java.sql.SQLException;

@Controller
public class RegController {
    @Autowired
    ServiceReg serviceReg;

    private static Logger logger = Logger.getLogger(RegController.class);

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public ModelAndView reg(@RequestParam(value = "login", required = false) String login,
                            @RequestParam(value = "pass", required = false) String pass,
                            @RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "last", required = false) String last) {
        if (!"".equals(login) && !"".equals(login) && !"".equals(login) && !"".equals("")) {
            boolean res = false;
            try {
                res = serviceReg.reg(name, last, "any", "16.01.2017", "man",
                        "java", login, pass, "16-01-2018");
            } catch (SQLException e) {
                logger.error("exception in reg");
                e.printStackTrace();
            }
            if (!res) {
                ModelAndView modelAndView = new ModelAndView("Registr");
                modelAndView.addObject("messageFromReg", "Ошибка");
                return modelAndView;
            } else {
                ModelAndView modelAndView = new ModelAndView("index");
                modelAndView.addObject("messageFromReg", "Ошибка");
                modelAndView.addObject("messageFromReg", "Регистрация прошла успешно");
                return modelAndView;
            }
        } else {
            ModelAndView modelAndView = new ModelAndView("Registr");
            modelAndView.addObject("messageFromReg", "Пустые поля");
            return modelAndView;
        }
    }
}
