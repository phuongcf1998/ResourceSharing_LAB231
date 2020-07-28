/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.category;

import java.io.Serializable;

/**
 *
 * @author HOME
 */
public class CategoryDTO implements Serializable{
    private String categoryID;
    private String description; 

    public CategoryDTO() {
    }

    public CategoryDTO(String categoryID, String description) {
        this.categoryID = categoryID;
        this.description = description;
    }

    /**
     * @return the categoryID
     */
    public String getCategoryID() {
        return categoryID;
    }

    /**
     * @param categoryID the categoryID to set
     */
    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
