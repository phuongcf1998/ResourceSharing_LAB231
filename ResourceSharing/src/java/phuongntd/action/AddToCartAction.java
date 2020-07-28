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
import phuongntd.cart.CartObj;
import phuongntd.resource.ResourceDAO;
import phuongntd.resource.ResourceDTO;

/**
 *
 * @author Yun
 */
public class AddToCartAction {

    private static final Logger LOGGER = Logger.getLogger(AddToCartAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String id;
    private String name;
    private String category;
    private String fromDate;
    private String toDate;
    private String fullQuantity;

    private int page;

    public AddToCartAction() {
    }

    public String execute() {
        String url = FAIL;
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session != null) {
            url = SUCCESS;
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObj();
            }
            try {
                ResourceDAO dao = new ResourceDAO();
                ResourceDTO item = dao.getResourceById(getId());
                if (cart.getItems() != null) {
                    if (cart.getItems().containsKey(item) && cart.getItems().get(item) == item.getQuantity()) {
                        setFullQuantity(item.getName() + " " + " out of quantity ! ");
                    } else {
                        cart.addItemToCart(item);
                        session.setAttribute("CART", cart);
                    }
                } else {
                    cart.addItemToCart(item);
                    session.setAttribute("CART", cart);
                }

            } catch (SQLException ex) {
                LOGGER.error("AddToCartAction_SQLException " + ex.getMessage());
            } catch (NamingException ex) {
                LOGGER.error("AddToCartAction_NamingException " + ex.getMessage());
            }
        }
        return url;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the fromDate
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the toDate
     */
    public String getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(String toDate) {
        this.toDate = toDate;
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
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the fullQuantity
     */
    public String getFullQuantity() {
        return fullQuantity;
    }

    /**
     * @param fullQuantity the fullQuantity to set
     */
    public void setFullQuantity(String fullQuantity) {
        this.fullQuantity = fullQuantity;
    }

}
