/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.cart;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import phuongntd.resource.ResourceDTO;
import phuongntd.utils.DateCaculator;

/**
 *
 * @author Yun
 */
public class CartObj implements Serializable {

    private String customerId;
    Map<ResourceDTO, Integer> items;
    Date date = DateCaculator.getCurrentDate();

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Map<ResourceDTO, Integer> getItems() {
        return items;
    }

    public boolean isEmptyItems() {
        return items.isEmpty();
    }

    public void addItemToCart(ResourceDTO item) {
        if (this.items == null) {
            this.items = new HashMap<>();

        }
        int quanity = 1;
        if (this.items.containsKey(item)) {
            quanity = this.items.get(item) + 1;
        }
        this.items.put(item, quanity);
    }

    public void updateItemToCart(String id, int count) {
        if (this.items == null) {
            this.items = new HashMap<>();

        }
        Set<ResourceDTO> set = this.items.keySet();
        for (ResourceDTO key : set) {
            if (key.getResourceID().equals(id)) {
                if (this.items.containsKey(key)) {

                    this.items.replace(key, count);

                }

            }
        }

    }

    public void removeItemFromCart(String id) {
        if (this.items == null) {
            return;
        }

        ResourceDTO dto = new ResourceDTO(id, "", 1, "", 0, date, date);
        if (this.items.containsKey(dto)) {
            this.items.remove(dto);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

}
