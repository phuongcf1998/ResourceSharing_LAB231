/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.request;

import java.sql.Date;

/**
 *
 * @author Yun
 */
public class RequestDTO {

    private String requestID;
    private String requestName;
    private String userID;
    private int total;
    private Date request_date;
    private int status;

    public RequestDTO() {
    }

    public RequestDTO(String requestID, String requestName, String userID, int total, Date request_date, int status) {
        this.requestID = requestID;
        this.requestName = requestName;
        this.userID = userID;
        this.total = total;
        this.request_date = request_date;
        this.status = status;
    }

    /**
     * @return the requestID
     */
    public String getRequestID() {
        return requestID;
    }

    /**
     * @param requestID the requestID to set
     */
    public void setRequestID(String requestID) {
        this.requestID = requestID;
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
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the request_date
     */
    public Date getRequest_date() {
        return request_date;
    }

    /**
     * @param request_date the request_date to set
     */
    public void setRequest_date(Date request_date) {
        this.request_date = request_date;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    

}
