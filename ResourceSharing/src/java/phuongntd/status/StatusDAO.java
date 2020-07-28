/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.status;

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
 * @author Yun
 */
public class StatusDAO implements Serializable {

    List<StatusDTO> listStatus;

    public List<StatusDTO> getListStatus() {
        return listStatus;
    }

    public void getAllListStatus() throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {

                String sql = "Select status,description from tbl_Status";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (listStatus == null) {
                        listStatus = new ArrayList<>();
                    }
                    String status = rs.getString("status");
                    String des = rs.getString("description");
                    StatusDTO dto = new StatusDTO(status, des);
                    listStatus.add(dto);

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
