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
import phuongntd.request.RequestDTO;
import phuongntd.user.UserDTO;

/**
 *
 * @author Yun
 */
public class ViewHistoryAction {

    private static final Logger LOGGER = Logger.getLogger(ViewHistoryAction.class.getName());
    private final String SUCCESS = "success";
    private List<RequestDTO> listReq;
    private final String FAIL = "fail";

    public ViewHistoryAction() {
    }

    public String execute() {
        String url = FAIL;

        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session != null) {
            try {
                UserDTO user = (UserDTO) session.getAttribute("USER");
                if (user != null) {
                    url = SUCCESS;
                    String userID = user.getUserID();
                    RequestDAO reqDAO = new RequestDAO();
                    reqDAO.getAllRequestForHistoryUser(userID);
                    listReq = reqDAO.getListRequest();
                }

            } catch (SQLException ex) {
                LOGGER.error("ViewHistoryAction_SQLException " + ex.getMessage());
            } catch (NamingException ex) {
                LOGGER.error("ViewHistoryAction_NamingException " + ex.getMessage());
            }

        }

        return url;
    }

    /**
     * @return the listReq
     */
    public List<RequestDTO> getListReq() {
        return listReq;
    }

}
