/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.request.detail;

import java.io.Serializable;

/**
 *
 * @author Yun
 */
public class RequestDetailDTO implements Serializable{
    private String requestID;
    private String resourceID;
    private String resoureName;
    private int quantity;

    public RequestDetailDTO() {
    }

    public RequestDetailDTO(String requestID, String resourceID, String resoureName, int quantity) {
        this.requestID = requestID;
        this.resourceID = resourceID;
        this.resoureName = resoureName;
        this.quantity = quantity;
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

    /**
     * @return the resoureName
     */
    public String getResoureName() {
        return resoureName;
    }

    /**
     * @param resoureName the resoureName to set
     */
    public void setResoureName(String resoureName) {
        this.resoureName = resoureName;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    
}
