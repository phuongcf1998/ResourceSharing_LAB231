/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.action;

import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import phuongntd.user.UserDAO;

/**
 *
 * @author Yun
 */
public class VerifyAccountAction {

    private static final Logger LOGGER = Logger.getLogger(VerifyAccountAction.class.getName());
    private String key1;
    private String key2;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public VerifyAccountAction() {

    }

    public String execute() {
        String url = FAIL;

        try {
            UserDAO dao = new UserDAO();
            boolean result = dao.updateStatusAccount(key1);
            if (result) {
                url = SUCCESS;
            }
        } catch (SQLException ex) {
            LOGGER.error("VerifyAccountAction_SQLException " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("VerifyAccountAction_NamingException " + ex.getMessage());
        }
        return url;
    }

    /**
     * @return the key1
     */
    public String getKey1() {
        return key1;
    }

    /**
     * @param key1 the key1 to set
     */
    public void setKey1(String key1) {
        this.key1 = key1;
    }

    /**
     * @return the key2
     */
    public String getKey2() {
        return key2;
    }

    /**
     * @param key2 the key2 to set
     */
    public void setKey2(String key2) {
        this.key2 = key2;
    }

}
