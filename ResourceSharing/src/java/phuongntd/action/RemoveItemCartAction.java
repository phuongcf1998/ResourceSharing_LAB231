/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.action;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import phuongntd.cart.CartObj;

/**
 *
 * @author Yun
 */
public class RemoveItemCartAction {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String resourceID;

    public RemoveItemCartAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;

        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session != null) {
            url = SUCCESS;
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart != null) {
                cart.removeItemFromCart(resourceID);
                session.setAttribute("CART", cart);
            }

        }

        return url;
    }

    /**
     * @return the resourceID
     */
    public String getResourceID() {
        return resourceID;
    }

    /**
     * @param resourceID the resourceID to set
     */
    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

}
