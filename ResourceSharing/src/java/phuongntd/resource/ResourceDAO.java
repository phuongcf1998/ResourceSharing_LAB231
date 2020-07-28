/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.resource;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import phuongntd.request.detail.RequestDetailDTO;
import phuongntd.utils.DBUtils;

/**
 *
 * @author Yun
 */
public class ResourceDAO implements Serializable {

    List<ResourceDTO> listResource;

    public List<ResourceDTO> getListResource() {
        return listResource;
    }

    public void searchResource(String name, int category, Date fromDate, Date toDate, int pageIndex, int pageSize) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT resourceID,name,categoryID,color,quantity"
                        + ",from_date,to_date "
                        + "FROM tbl_Resource WHERE "
                        + "name like ? and categoryID = ? and from_date >= ? and to_date <= ? "
                        + "ORDER BY name DESC "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? "
                        + "ROW ONLY";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                stm.setInt(2, category);
                stm.setDate(3, fromDate);
                stm.setDate(4, toDate);
                stm.setInt(5, (pageIndex - 1) * pageSize);
                stm.setInt(6, pageSize);
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (listResource == null) {
                        listResource = new ArrayList<>();
                    }
                    String id = rs.getString("resourceID");
                    String resourceName = rs.getString("name");
                    int categoryResource = rs.getInt("categoryID");
                    String color = rs.getString("color");
                    int quantity = rs.getInt("quantity");
                    Date fromDateResource = rs.getDate("from_date");
                    Date toDateResource = rs.getDate("to_date");
                    ResourceDTO dto = new ResourceDTO(id, resourceName, categoryResource, color, quantity, fromDateResource, toDateResource);
                    listResource.add(dto);
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

    public int countSearchResource(String name, int category, Date fromDate, Date toDate) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        int result = 0;

        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(resourceID) FROM tbl_Resource WHERE name like ? and categoryID = ? and from_date >= ? and to_date <= ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                stm.setInt(2, category);
                stm.setDate(3, fromDate);
                stm.setDate(4, toDate);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt(1);
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

        return result;
    }

    public ResourceDTO getResourceById(String id) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ResourceDTO result = null;

        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT name,categoryID,color,quantity"
                        + ",from_date,to_date "
                        + "FROM tbl_Resource WHERE resourceID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {

                    String resourceName = rs.getString("name");
                    int categoryResource = rs.getInt("categoryID");
                    String color = rs.getString("color");
                    int quantity = rs.getInt("quantity");
                    Date fromDateResource = rs.getDate("from_date");
                    Date toDateResource = rs.getDate("to_date");
                    ResourceDTO dto = new ResourceDTO(id, resourceName, categoryResource, color, quantity, fromDateResource, toDateResource);
                    result = dto;
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
        return result;

    }

    public boolean updateQuantityResource(Map<ResourceDTO, Integer> listResource) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;

        boolean result = false;

        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                int count = 0;
                for (Map.Entry resource : listResource.entrySet()) {
                    String sql = "Update tbl_Resource set quantity = ? WHERE resourceID = ?";
                    stm = conn.prepareStatement(sql);
                    ResourceDTO dto = (ResourceDTO) resource.getKey();
                    int quantityBefore = getResourceById(dto.getResourceID()).getQuantity();
                    stm.setInt(1, quantityBefore - (Integer) resource.getValue());
                    stm.setString(2, dto.getResourceID());

                    int row = stm.executeUpdate();
                    if (row > 0) {
                        count++;
                    }

                }
                if (count == listResource.size()) {

                    result = true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean recoverQuantityResource(List<RequestDetailDTO> listRequestDetail) throws SQLException, NamingException {

        Connection conn = null;
        PreparedStatement stm = null;

        boolean result = false;

        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                int count = 0;
                for (RequestDetailDTO dto : listRequestDetail) {
                    String sql = "Update tbl_Resource set quantity = ? WHERE resourceID = ?";
                    stm = conn.prepareStatement(sql);
                    int quantityBefore = getResourceById(dto.getResourceID()).getQuantity();
                    stm.setInt(1, quantityBefore + dto.getQuantity());
                    stm.setString(2, dto.getResourceID());

                    int row = stm.executeUpdate();
                    if (row > 0) {
                        count++;
                    }

                }
                if (count == listRequestDetail.size()) {

                    result = true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return result;

    }

}
