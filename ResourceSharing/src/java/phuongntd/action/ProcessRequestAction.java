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

/**
 *
 * @author Yun
 */
public class ProcessRequestAction {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private final String EXECUTED = "executed";
    private String resourceName;
    private String requestDate;
    private String userID;
    private String slStatus;
    private int page;
    private String requestID;
    private String processType;
    private static final Logger LOGGER = Logger.getLogger(ProcessRequestAction.class.getName());

    public ProcessRequestAction() {
    }

    public String execute() {
        String url = FAIL;

        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session != null) {
            try {
                RequestDAO requestDAO = new RequestDAO();
                int statusRequest = requestDAO.getStatusRequestById(requestID);
                if (statusRequest != 1) {
                    url = EXECUTED;
                } else {
                    url = SUCCESS;

                    if (getProcessType().equals("Delete")) {
                        RequestDetailDAO requestDetailDAO = new RequestDetailDAO();
                        requestDetailDAO.getListDetailByRequestID(requestID);
                        List<RequestDetailDTO> listRequestDetail = requestDetailDAO.getListRequestDetail();
                        ResourceDAO resourceDAO = new ResourceDAO();
                        boolean result = resourceDAO.recoverQuantityResource(listRequestDetail);
                        if (result) {
                            requestDAO.updateRequest(requestID, 5);
                        }

                    } else if (getProcessType().equals("Approval")) {
//                    RequestDetailDAO reqDetailDAO = new RequestDetailDAO();
//                    reqDetailDAO.getListDetailByRequestID(requestID);
//                    List<RequestDetailDTO> listDetail = reqDetailDAO.getListRequestDetail();
//                    ResourceDAO resourceDAO = new ResourceDAO();
//                    resourceDAO.updateQuantityResource(listDetail);
                        requestDAO.updateRequest(requestID, 4);
                    }
                }

            } catch (SQLException ex) {
                LOGGER.error("ProcessRequestAction_SQLException " + ex.getMessage());
            } catch (NamingException ex) {
                LOGGER.error("ProcessRequestAction_NamingException " + ex.getMessage());
            }
        }

        return url;
    }

    /**
     * @return the requestDate
     */
    public String getRequestDate() {
        return requestDate;
    }

    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
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

    /**
     * @return the processType
     */
    public String getProcessType() {
        return processType;
    }

    /**
     * @param processType the processType to set
     */
    public void setProcessType(String processType) {
        this.processType = processType;
    }

    /**
     * @return the slStatus
     */
    public String getSlStatus() {
        return slStatus;
    }

    /**
     * @param slStatus the slStatus to set
     */
    public void setSlStatus(String slStatus) {
        this.slStatus = slStatus;
    }

    /**
     * @return the resourceName
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * @param resourceName the resourceName to set
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

}
