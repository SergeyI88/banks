package controllers;

import db.pojo.LoanContract;
import db.pojo.LoanRequest;
import db.pojo.UserData;
import logger.Main;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import services.ServiceForApprovedRequestAndCreateContract;
import services.ServiceForGetAllRequestAndContractsByIdUser;

import java.sql.SQLException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Controller
@SessionAttributes({"user"})
public class DashBoardController{
    private static Logger logger = Logger.getLogger(DashBoardController.class);
    @Autowired
    private  ServiceForGetAllRequestAndContractsByIdUser serviceForGetAllRequestAndContractsByIdUser;
    @Autowired
    private  ServiceForApprovedRequestAndCreateContract serviceForApprovedRequestAndCreateContract;

    @ModelAttribute("requests")
    public List<LoanRequest> getRequest(@ModelAttribute("user")UserData userData) throws SQLException {
       return serviceForGetAllRequestAndContractsByIdUser.getAllRequestByIdUser(userData.getId());
    }

    @ModelAttribute("contracts")
    public List<LoanContract> getContracts(@ModelAttribute("user")UserData userData) throws SQLException {
        return serviceForGetAllRequestAndContractsByIdUser.getAllContractsByIdUser(userData.getId());
    }

    @RequestMapping(value = "/inner/dashboard", method = RequestMethod.POST)
    public ModelAndView getAll(@ModelAttribute("contracts") List<LoanContract> loanContracts,
                               @ModelAttribute("requests") List<LoanRequest> loanRequests) {
        ModelAndView modelAndView = new ModelAndView("dashboard");
        modelAndView.addObject("requests", loanRequests);
        modelAndView.addObject("contracts", loanContracts);
        return modelAndView;
    }
}
