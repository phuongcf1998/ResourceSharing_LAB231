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
import phuongntd.request.detail.RequestDetailDAO;
import phuongntd.request.detail.RequestDetailDTO;

/**
 *
 * @author Yun
 */
public class ViewDetailRequestAction {

    private static final Logger LOGGER = Logger.getLogger(ViewDetailRequestAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String requestID;
    private List<RequestDetailDTO> listRequestDetail;

    public ViewDetailRequestAction() {
    }

    public String execute() {
        String url = FAIL;

        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session != null) {
            try {
                url = SUCCESS;
                RequestDetailDAO dao = new RequestDetailDAO();
                dao.getListDetailByRequestID(requestID);
                listRequestDetail = dao.getListRequestDetail();
            } catch (SQLException ex) {
                LOGGER.error("ViewDetailRequestAction_SQLException " + ex.getMessage());
            } catch (NamingException ex) {
                LOGGER.error("ViewDetailRequestAction_NamingException " + ex.getMessage());
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

    /**
     * @return the listRequestDetail
     */
    public List<RequestDetailDTO> getListRequestDetail() {
        return listRequestDetail;
    }

}
