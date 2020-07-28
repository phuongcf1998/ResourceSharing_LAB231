/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.action;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Yun
 */
public class LogOutAction {

    private final String SUCCESS = "success";

    public LogOutAction() {
    }

    public String execute() throws Exception {
        String url = SUCCESS;
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return url;
    }

}
