/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.action;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import phuongntd.user.UserDTO;

/**
 *
 * @author HOME
 */
public class ViewCartAction {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public ViewCartAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session != null) {
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if (user != null) {
                url = SUCCESS;

            }
        }

        return url;
    }

}
