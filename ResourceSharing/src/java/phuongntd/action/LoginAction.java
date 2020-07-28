/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.action;

import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import phuongntd.user.UserDAO;
import phuongntd.user.UserDTO;

/**
 *
 * @author Yun
 */
public class LoginAction {

    private String username;
    private String password;
    private static final Logger LOGGER = Logger.getLogger(LoginAction.class.getName());
    private final String SUCCESS_MANAGER = "manager";
    private final String DENIED = "denied";
    private final String SUCCESS_USER = "user";
    private final String FAIL = "fail";

    public LoginAction() {
    }

    public String execute() {
        String url = FAIL;

        try {
            UserDAO dao = new UserDAO();

            UserDTO user = dao.checkLogin(getUsername(), getPassword());
            if (user != null) {

                int status = user.getStatus();
                if (status == 2) {
                    int role = user.getRole();
                    switch (role) {
                        case 1: {
                            HttpSession session = ServletActionContext.getRequest().getSession();
                            session.setAttribute("MANAGER", user);
                            url = SUCCESS_MANAGER;
                            break;
                        }
                        case 2:
                        case 3: {
                            HttpSession session = ServletActionContext.getRequest().getSession();
                            session.setAttribute("USER", user);
                            url = SUCCESS_USER;
                            break;
                        }

                    }
                } else {
                    url = DENIED;
                }

            }
        } catch (SQLException ex) {
            LOGGER.error("LoginAction_SQLException " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("LoginAction_NamingException " + ex.getMessage());
        }

        return url;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
