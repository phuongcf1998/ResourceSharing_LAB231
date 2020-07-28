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
import phuongntd.request.RequestDAO;
import phuongntd.user.UserDTO;

/**
 *
 * @author Yun
 */
public class SearchRequestHistoryAction {

    private static final Logger LOGGER = Logger.getLogger(SearchRequestHistoryAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String resourceName;
    private String requestDate;
    private String userID;

    private List listSearchRequestHistory;
    private int page;
    private int totalPage;

    public SearchRequestHistoryAction() {
    }

    public String execute() {
        String url = FAIL;
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session != null) {
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if (user != null) {
                url = SUCCESS;
                int pageSize = 6;
                int pageIndex = 1;

                if (page > 1) {
                    pageIndex = page;
                }
                Date requestDateParse = Date.valueOf(getRequestDate());

                try {
                    RequestDAO dao = new RequestDAO();
                    dao.searchRequestHistory(userID, resourceName, requestDateParse, pageIndex, pageSize);
                    listSearchRequestHistory = dao.getListRequest();
                    int countList = dao.countSearchRequestHistory(userID, resourceName, requestDateParse);

                    setTotalPage(countList / pageSize);

                    if (countList % pageSize != 0) {
                        setTotalPage(getTotalPage() + 1);
                    }
                } catch (SQLException ex) {
                    LOGGER.error("SearchRequestHistoryAction_SQLException " + ex.getMessage());
                } catch (NamingException ex) {
                    LOGGER.error("SearchRequestHistoryAction_NamingException " + ex.getMessage());
                }
            }
        }

        return url;
    }

    /**
     * @return the resourceName
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * @param resourceName the resourceName to set
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
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
     * @return the requestDate
     */
    public String getRequestDate() {
        return requestDate;
    }

    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * @return the listSearchRequestHistory
     */
    public List getListSearchRequestHistory() {
        return listSearchRequestHistory;
    }

}
