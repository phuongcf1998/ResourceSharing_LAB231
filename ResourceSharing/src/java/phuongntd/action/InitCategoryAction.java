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
import phuongntd.category.CategoryDAO;
import phuongntd.category.CategoryDTO;
import phuongntd.user.UserDTO;

/**
 *
 * @author HOME
 */
public class InitCategoryAction {

    private static final Logger LOGGER = Logger.getLogger(InitCategoryAction.class.getName());
    private final String SUCCESS_MANAGER = "success_manager";
    private final String SUCCESS_USER = "success_user";
    private final String FAIL = "fail";
    private List<CategoryDTO> listCategory;

    public InitCategoryAction() {
    }

    public String execute() {
        String url = FAIL;
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session != null) {
            try {
                UserDTO user = (UserDTO) session.getAttribute("USER");
                if (user != null) {
                    url = SUCCESS_USER;
                    CategoryDAO dao = new CategoryDAO();
                    dao.getAllListCategory();
                    listCategory = dao.getListCategory();
                } else {
                    url = SUCCESS_MANAGER;
                    CategoryDAO dao = new CategoryDAO();
                    dao.getAllListCategory();
                    listCategory = dao.getListCategory();
                }

            } catch (SQLException ex) {
                LOGGER.error("InitCategoryAction_SQLException " + ex.getMessage());
            } catch (NamingException ex) {
                LOGGER.error("InitCategoryAction_NamingException " + ex.getMessage());
            }

        }

        return url;
    }

    /**
     * @return the listCategory
     */
    public List<CategoryDTO> getListCategory() {
        return listCategory;
    }

}
