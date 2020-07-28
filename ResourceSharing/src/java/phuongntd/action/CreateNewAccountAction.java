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
import phuongntd.utils.RandomString;
import phuongntd.utils.SendEmail;

/**
 *
 * @author Yun
 */
public class CreateNewAccountAction  {

    private static final Logger LOGGER = Logger.getLogger(CreateNewAccountAction.class.getName());
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String phone;
    private String errorDuplicate;
    private String address;
    private String SUCCESS = "success";
    private String DUPLICATE = "duplicate";

    public CreateNewAccountAction() {
    }

    public String execute()  {
        int phoneParse = Integer.parseInt(phone);
        String url = SUCCESS;
        UserDAO dao = new UserDAO();
        try {
            boolean result = dao.createNewUser(email, password, fullName, phoneParse, address);
            if (result) {
                String randomKey = RandomString.generateRandomString(6);
                SendEmail.sendEmail(email, randomKey);
            }

        } catch (SQLException ex) {
            LOGGER.error("CreateNewAccountAction_SQLException " + ex.getMessage());
            if (ex.getMessage().contains("duplicate")) {
                setErrorDuplicate(email + "is existed !!!");
                url = DUPLICATE;
            }

        } catch (NamingException ex) {
            LOGGER.error("CreateNewAccountAction_NamingException " + ex.getMessage());
        }

        return url;

    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the errorDuplicate
     */
    public String getErrorDuplicate() {
        return errorDuplicate;
    }

    /**
     * @param errorDuplicate the errorDuplicate to set
     */
    public void setErrorDuplicate(String errorDuplicate) {
        this.errorDuplicate = errorDuplicate;
    }

}
