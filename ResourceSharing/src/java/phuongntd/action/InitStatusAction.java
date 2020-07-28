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
import phuongntd.status.StatusDAO;
import phuongntd.status.StatusDTO;

/**
 *
 * @author Yun
 */
public class InitStatusAction {

    private static final Logger LOGGER = Logger.getLogger(InitStatusAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private List<StatusDTO> listStatus;
  

    public InitStatusAction() {
    }

    public String execute() {
        String url = FAIL;
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session != null) {
            try {
                url = SUCCESS;
                StatusDAO statusDAO = new StatusDAO();
                statusDAO.getAllListStatus();
                listStatus = statusDAO.getListStatus();
              
            } catch (SQLException ex) {
                LOGGER.error("InitStatusAction_SQLException " + ex.getMessage());
            } catch (NamingException ex) {
                LOGGER.error("InitStatusAction_NamingException " + ex.getMessage());
            }

        }

        return url;
    }

    /**
     * @return the listStatus
     */
    public List<StatusDTO> getListStatus() {
        return listStatus;
    }


}
