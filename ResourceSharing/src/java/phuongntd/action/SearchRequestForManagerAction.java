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
import phuongntd.status.StatusDAO;
import phuongntd.status.StatusDTO;
import phuongntd.user.UserDTO;

/**
 *
 * @author Yun
 */
public class SearchRequestForManagerAction {

    private static final Logger LOGGER = Logger.getLogger(SearchRequestForManagerAction.class.getName());
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String resourceName;
    private String requestDate;
    private String userID;
    private String slStatus;
    private List listSearchRequestForManager;
    private int page;
    private int totalPage;
    private List<StatusDTO> listStatus;

    public SearchRequestForManagerAction() {
    }

    public String execute()  {
        String url = FAIL;
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session != null) {
            UserDTO user = (UserDTO) session.getAttribute("MANAGER");
            if (user != null) {
                url = SUCCESS;

                int pageSize = 6;
                int pageIndex = 1;

                if (page > 1) {
                    pageIndex = page;
                }

                try {
                    Date requestDateParse = Date.valueOf(getRequestDate());
                    int statusParse = Integer.parseInt(getSlStatus());

                    RequestDAO dao = new RequestDAO();
                    dao.searchRequestByManager(userID, getResourceName(), requestDateParse, statusParse, pageIndex, pageSize);
                    listSearchRequestForManager = dao.getListRequest();
                    int countList = dao.countSearchRequestManager(userID, getResourceName(), statusParse, requestDateParse);

                    setTotalPage(countList / pageSize);

                    if (countList % pageSize != 0) {
                        setTotalPage(getTotalPage() + 1);
                    }
                    StatusDAO statusDAO = new StatusDAO();
                    statusDAO.getAllListStatus();
                    listStatus = statusDAO.getListStatus();
                } catch (SQLException ex) {
                    LOGGER.error("SearchRequestForManagerAction_SQLException " + ex.getMessage());
                } catch (NamingException ex) {
                    LOGGER.error("SearchRequestForManagerAction_NamingException " + ex.getMessage());
                }

            }
        }

        return url;
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
     * @return the listSearchRequestForManager
     */
    public List getListSearchRequestForManager() {
        return listSearchRequestForManager;
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
     * @return the listStatus
     */
    public List<StatusDTO> getListStatus() {
        return listStatus;
    }

    /**
     * @return the slStatus
     */
    public String getSlStatus() {
        return slStatus;
    }

    /**
     * @param slStatus the slStatus to set
     */
    public void setSlStatus(String slStatus) {
        this.slStatus = slStatus;
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

}
