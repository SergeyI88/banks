package controllers;

import db.pojo.LoanContract;
import db.pojo.LoanRequest;
import db.pojo.UserData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import services.ServiceForApprovedRequestAndCreateContract;
import services.ServiceForGetAllRequestAndContractsByIdUser;

import java.sql.SQLException;
import java.util.List;

@Controller
@SessionAttributes("user")
public class ControllerManager {

    private static Logger logger = Logger.getLogger(DashBoardController.class);
    @Autowired
    private ServiceForGetAllRequestAndContractsByIdUser serviceForGetAllRequestAndContractsByIdUser;
    @Autowired
    private ServiceForApprovedRequestAndCreateContract serviceForApprovedRequestAndCreateContract;


    @ModelAttribute("requests")
    public List<LoanRequest> getRequest(@ModelAttribute("user")UserData userData) throws SQLException {
        return serviceForGetAllRequestAndContractsByIdUser.getAllRequests();
    }

    @ModelAttribute("contracts")
    public List<LoanContract> getContracts(@ModelAttribute("user")UserData userData) throws SQLException {
        return serviceForGetAllRequestAndContractsByIdUser.getAllContractsByIdManager(userData.getId());
    }

    
    
    @RequestMapping(value = "boardMan")
    public ModelAndView getAll(@ModelAttribute("contracts") List<LoanContract> loanContracts,
                               @ModelAttribute("requests") List<LoanRequest> loanRequests) {
        ModelAndView modelAndView = new ModelAndView("dashboard");
        modelAndView.addObject("requests", loanRequests);
        modelAndView.addObject("contracts", loanContracts);
        return modelAndView;
    }



    @RequestMapping("/inner/approve")
    public ModelAndView newContract(@ModelAttribute("user")UserData userData, @RequestParam(value = "id_request") Integer id_request) throws SQLException {
        try {
            serviceForApprovedRequestAndCreateContract.approvedManager(id_request, userData.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView("forward:/inner/dashboard");
        return modelAndView;
    }
}

