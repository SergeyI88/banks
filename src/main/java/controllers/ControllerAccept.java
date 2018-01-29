package controllers;

import db.pojo.LoanContract;
import db.pojo.LoanRequest;
import db.pojo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.ServiceForApprovedRequestAndCreateContract;
import services.ServiceForGetAllRequestAndContractsByIdUser;

import java.sql.SQLException;
import java.util.List;


@Controller
@SessionAttributes("user")
public class ControllerAccept {
    @Autowired
    private ServiceForApprovedRequestAndCreateContract serviceForApprovedRequestAndCreateContract;
    @Autowired
    private ServiceForGetAllRequestAndContractsByIdUser serviceForGetAllRequestAndContractsByIdUser;


    @ModelAttribute("contracts")
    public List<LoanContract> getContracts(@ModelAttribute("user")UserData userData) throws SQLException {
        return serviceForGetAllRequestAndContractsByIdUser.getAllContractsByIdUser(userData.getId());
    }


    @RequestMapping(value = "/inner/accept", method = RequestMethod.POST)
    public ModelAndView newContract(@ModelAttribute("contracts") List<LoanContract> loanContracts, @RequestParam(value = "id_request", required = false) Integer id_request) throws SQLException {

        if (id_request != null) {
            for (LoanContract loanContract: loanContracts) {
                System.out.println(id_request);
                System.out.println(loanContract.getId_request());
                if (loanContract.getId_request() == id_request) {
                    ModelAndView modelAndView = new ModelAndView("forward:/inner/dashboard");
                    modelAndView.addObject("error", "уже существует контракт по этому реквесту");
                    return modelAndView;
                }
            }
            try {
                LoanRequest loanRequest = serviceForApprovedRequestAndCreateContract.getRequestById(id_request);
                serviceForApprovedRequestAndCreateContract.approvedClientAndCreateContract(loanRequest.getId()
                        , loanRequest.getDuration() + " duration:start"
                        , loanRequest.getDuration() + " duration:finish"
                        , loanRequest.getSum()
                        , loanRequest.getId_client()
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ModelAndView modelAndView = new ModelAndView("forward:/inner/dashboard");
        return modelAndView;
    }
}





