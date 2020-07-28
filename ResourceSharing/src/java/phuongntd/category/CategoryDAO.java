/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.category;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phuongntd.utils.DBUtils;

/**
 *
 * @author HOME
 */
public class CategoryDAO implements Serializable {

    List<CategoryDTO> listCategory;

    public List<CategoryDTO> getListCategory() {
        return listCategory;
    }

    public void getAllListCategory() throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {

                String sql = "Select categoryID,description from tbl_Category";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (listCategory == null) {
                        listCategory = new ArrayList<>();
                    }
                    String id = rs.getString("categoryID");
                    String des = rs.getString("description");
                    CategoryDTO category = new CategoryDTO(id, des);
                    listCategory.add(category);

                }

            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }

            if (conn != null) {
                conn.close();
            }
        }

    }

}
