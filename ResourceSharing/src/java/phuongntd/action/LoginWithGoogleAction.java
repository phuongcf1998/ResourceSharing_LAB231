/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.action;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import phuongntd.google.GooglePojo;
import phuongntd.google.GoogleUtils;
import phuongntd.user.UserDAO;
import phuongntd.user.UserDTO;
import phuongntd.utils.DateCaculator;
import phuongntd.utils.RandomString;

/**
 *
 * @author Yun
 */
public class LoginWithGoogleAction {

    private String code;
    private final String SUCCESS_MANAGER = "manager";
    private final String DENIED = "denied";
    private final String SUCCESS_USER = "user";
    private final String FAIL = "fail";
    private static final Logger LOGGER = Logger.getLogger(LoginWithGoogleAction.class.getName());

    public LoginWithGoogleAction() {

    }

    public String execute() {
        String url = FAIL;

        if (code == null || code.isEmpty()) {

        } else {
            try {
                String accessToken = GoogleUtils.getToken(code);

                if (accessToken != null) {

                    GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
                    if (googlePojo != null) {
                        String password = RandomString.generateRandomString(9);
                        HttpSession session = ServletActionContext.getRequest().getSession();
                        Date currentDate = DateCaculator.getCurrentDate();
                        UserDAO dao = new UserDAO();

                        UserDTO UserExist = dao.checkUserGoogleIsExist(googlePojo.getEmail());
                        if (UserExist != null) {

                            if (UserExist.getStatus() == 2) {
                                int role = UserExist.getRole();
                                switch (role) {
                                    case 1: {

                                        session.setAttribute("MANAGER", UserExist);
                                        url = SUCCESS_MANAGER;
                                        break;
                                    }
                                    case 2:
                                    case 3: {

                                        session.setAttribute("USER", UserExist);
                                        url = SUCCESS_USER;
                                        break;
                                    }

                                }
                            } else {

                                url = DENIED;
                            }

                        } else {
                            UserDTO dto = new UserDTO(googlePojo.getEmail(), password, googlePojo.getName(), googlePojo.getLocale(), 0, currentDate, 2, 2);
                            boolean result = dao.insertUserGoogleWhenLogin(dto);
                            if (result) {
                                session.setAttribute("USER", dto);
                                url = SUCCESS_USER;
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                LOGGER.error("LoginWithGoogleAction_SQLException " + ex.getMessage());
            } catch (NamingException ex) {
                LOGGER.error("LoginWithGoogleAction_NamingException " + ex.getMessage());
            } catch (ClientProtocolException ex) {
                LOGGER.error("LoginWithGoogleAction_ClientProtocolException " + ex.getMessage());
            } catch (IOException ex) {
                LOGGER.error("LoginWithGoogleAction_IOException " + ex.getMessage());
            }

        }

        return url;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

}
