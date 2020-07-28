/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.action;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import phuongntd.request.RequestDAO;
import phuongntd.request.detail.RequestDetailDAO;
import phuongntd.request.detail.RequestDetailDTO;
import phuongntd.resource.ResourceDAO;
import phuongntd.user.UserDTO;

/**
 *
 * @author Yun
 */
public class DeleteRequestByUserAction {

    private static final Logger LOGGER = Logger.getLogger(DeleteRequestByUserAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private final String EXECUTED = "executed";
    private String requestID;

    public DeleteRequestByUserAction() {
    }

    public String execute() {
        String url = FAIL;
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session != null) {
            try {
                UserDTO user = (UserDTO) session.getAttribute("USER");
                if (user != null) {
                    RequestDAO requestDAO = new RequestDAO();
                    int statusRequest = requestDAO.getStatusRequestById(requestID);
                    if (statusRequest != 1) {
                        url = EXECUTED;
                    } else {
                        url = SUCCESS;

                        RequestDetailDAO requestDetailDAO = new RequestDetailDAO();
                        requestDetailDAO.getListDetailByRequestID(requestID);
                        List<RequestDetailDTO> listRequestDetail = requestDetailDAO.getListRequestDetail();
                        ResourceDAO resourceDAO = new ResourceDAO();
                        boolean result = resourceDAO.recoverQuantityResource(listRequestDetail);
                        if (result) {
                            requestDAO.updateRequest(requestID, 3);
                        }
                    }
                }

            } catch (SQLException ex) {
                LOGGER.error("DeleteRequestByUserAction_SQLException " + ex.getMessage());
            } catch (NamingException ex) {
                LOGGER.error("DeleteRequestByUserAction_NamingException " + ex.getMessage());
            }

        }

        return url;
    }

    /**
     * @return the requestID
     */
    public String getRequestID() {
        return requestID;
    }

    /**
     * @param requestID the requestID to set
     */
    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

}
