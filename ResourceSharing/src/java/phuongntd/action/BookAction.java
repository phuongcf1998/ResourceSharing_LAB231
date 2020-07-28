/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.action;

import java.sql.Date;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import phuongntd.cart.CartObj;
import phuongntd.request.RequestDAO;
import phuongntd.request.detail.RequestDetailDAO;
import phuongntd.resource.ResourceDAO;
import phuongntd.user.UserDTO;
import phuongntd.utils.DateCaculator;
import phuongntd.utils.RandomString;

/**
 *
 * @author Yun
 */
public class BookAction {

    private static final Logger LOGGER = Logger.getLogger(BookAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String totalQuantity;
    private String requestName;

    public BookAction() {
    }

    public String execute() {
        String url = FAIL;
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session != null) {
            try {
                url = SUCCESS;
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    int totalQuantityParse = Integer.parseInt(totalQuantity);
                    Date currentDate = DateCaculator.getCurrentDate();
                    UserDTO user = (UserDTO) session.getAttribute("USER");
                    String userID = user.getUserID();
                    String requestID = RandomString.generateRandomString(6);
                    RequestDAO dao = new RequestDAO();
                    boolean result = dao.insertRequest(requestID, requestName, userID, totalQuantityParse, currentDate);
                    if (result) {
                        RequestDetailDAO detailDAO = new RequestDetailDAO();
                        boolean resultDetail = detailDAO.insertRequestDetail(cart.getItems(), requestID);
                        if (resultDetail) {
                            ResourceDAO resourceDAO = new ResourceDAO();
                            resourceDAO.updateQuantityResource(cart.getItems());
                            session.removeAttribute("CART");

                        }
                    }
                }
            } catch (SQLException ex) {
                LOGGER.error("BookAction_SQLException " + ex.getMessage());
            } catch (NamingException ex) {
                LOGGER.error("BookAction_NamingException " + ex.getMessage());
            }

        }
        return url;
    }

    /**
     * @return the totalQuantity
     */
    public String getTotalQuantity() {
        return totalQuantity;
    }

    /**
     * @param totalQuantity the totalQuantity to set
     */
    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    /**
     * @return the requestName
     */
    public String getRequestName() {
        return requestName;
    }

    /**
     * @param requestName the requestName to set
     */
    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

}
