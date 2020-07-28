/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.action;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import phuongntd.category.CategoryDAO;
import phuongntd.category.CategoryDTO;
import phuongntd.resource.ResourceDAO;
import phuongntd.resource.ResourceDTO;

/**
 *
 * @author Yun
 */
public class SearchResourceUserAction {

    private static final Logger LOGGER = Logger.getLogger(SearchResourceUserAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String name;
    private String category;
    private String fromDate;
    private String toDate;
    private List<ResourceDTO> listResource;
    private int page;
    private int totalPage;
    private String fullQuantity;
    private List<CategoryDTO> listCategory;

    public SearchResourceUserAction() {

    }

    public String execute() {
        String url = FAIL;
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session != null) {

            url = SUCCESS;
            int pageSize = 6;
            int pageIndex = 1;

            if (page > 1) {
                pageIndex = page;
            }
            try {

                ResourceDAO dao = new ResourceDAO();
                int categoryParse = Integer.parseInt(category);
                Date fromDateParse = Date.valueOf(getFromDate());
                Date toDateParse = Date.valueOf(getToDate());
                dao.searchResource(name, categoryParse, fromDateParse, toDateParse, pageIndex, pageSize);
                listResource = dao.getListResource();
                int countList = dao.countSearchResource(name, categoryParse, fromDateParse, toDateParse);

                setTotalPage(countList / pageSize);

                if (countList % pageSize != 0) {
                    setTotalPage(getTotalPage() + 1);
                }
                CategoryDAO categoryDAO = new CategoryDAO();
                categoryDAO.getAllListCategory();
                listCategory = categoryDAO.getListCategory();
            } catch (SQLException ex) {
                LOGGER.error("SearchResourceUserAction_SQLException " + ex.getMessage());
            } catch (NamingException ex) {
                LOGGER.error("SearchResourceUserAction_NamingException " + ex.getMessage());
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
     * @return the listResource
     */
    public List<ResourceDTO> getListResource() {
        return listResource;
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
     * @return the totalPage
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * @param totalPage the totalPage to set
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
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

    /**
     * @return the listCategory
     */
    public List<CategoryDTO> getListCategory() {
        return listCategory;
    }

}
