/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.request.detail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import phuongntd.resource.ResourceDTO;
import phuongntd.utils.DBUtils;

/**
 *
 * @author Yun
 */
public class RequestDetailDAO implements Serializable {

    private List<RequestDetailDTO> listRequestDetail;

    public List<RequestDetailDTO> getListRequestDetail() {
        return listRequestDetail;
    }

    public boolean insertRequestDetail(Map<ResourceDTO, Integer> listresourceRequest, String requestID) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;

        boolean result = false;

        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                int count = 0;
                for (Map.Entry request : listresourceRequest.entrySet()) {
                    String sql = "Insert into tbl_RequestDetail(requestID,resourceID,resoureName,quantity) values(?,?,?,?)";
                    ResourceDTO dto = (ResourceDTO) request.getKey();
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, requestID);
                    stm.setString(2, dto.getResourceID());
                    stm.setString(3, dto.getName());
                    stm.setInt(4, (int) request.getValue());

                    int row = stm.executeUpdate();
                    if (row > 0) {
                        count++;
                    }

                }
                if (count == listresourceRequest.size()) {

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

    public void getListDetailByRequestID(String requestID) throws SQLException, NamingException {

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {

                String sql = "Select resourceID,resoureName,quantity from tbl_RequestDetail where requestID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, requestID);

                rs = stm.executeQuery();
                while (rs.next()) {
                    if (listRequestDetail == null) {
                        listRequestDetail = new ArrayList<>();
                    }
                    String resourceID = rs.getString("resourceID");
                    String resoureName = rs.getString("resoureName");
                    int quantity = rs.getInt("quantity");
                    RequestDetailDTO dto = new RequestDetailDTO(requestID, resourceID, resoureName, quantity);
                    listRequestDetail.add(dto);
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
