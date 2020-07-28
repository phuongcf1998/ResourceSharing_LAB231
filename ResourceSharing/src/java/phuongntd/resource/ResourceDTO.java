/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.resource;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Yun
 */
public class ResourceDTO implements Serializable {

    private String resourceID;
    private String name;
    private int categoryID;
    private String color;
    private int quantity;
    private Date from_date;
    private Date to_date;

    public ResourceDTO() {
    }

    public ResourceDTO(String resourceID, String name, int categoryID, String color, int quantity, Date from_date, Date to_date) {
        this.resourceID = resourceID;
        this.name = name;
        this.categoryID = categoryID;
        this.color = color;
        this.quantity = quantity;
        this.from_date = from_date;
        this.to_date = to_date;
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
     * @return the categoryID
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * @param categoryID the categoryID to set
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
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

    /**
     * @return the from_date
     */
    public Date getFrom_date() {
        return from_date;
    }

    /**
     * @param from_date the from_date to set
     */
    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    /**
     * @return the to_date
     */
    public Date getTo_date() {
        return to_date;
    }

    /**
     * @param to_date the to_date to set
     */
    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }
    
     @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ResourceDTO)) {
            return false;
        }
        ResourceDTO other = (ResourceDTO) obj;

        if ((this.getResourceID() == null && other.getResourceID() != null)
                || (this.getResourceID() != null && !this.resourceID.equals(other.resourceID))) {
            return false;

        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getResourceID() != null ? getResourceID().hashCode() : 0);
        return hash;
    }

}
