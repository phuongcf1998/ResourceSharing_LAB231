/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import phuongntd.utils.DBUtils;
import phuongntd.utils.DateCaculator;

/**
 *
 * @author Yun
 */
public class UserDAO implements Serializable {

    public UserDTO checkLogin(String username, String password) throws SQLException, NamingException {
        UserDTO user = null;

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {
                String sql = "Select userID , password , fullName, create_date , role , status from tbl_User where userID = ? and password = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    Date create_date = rs.getDate("create_date");
                    int role = rs.getInt("role");
                    int status = rs.getInt("status");
                    user = new UserDTO(userID, "", fullName, "", 0, create_date, role, status);

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

        return user;

    }

    public boolean createNewUser(String email, String password, String fullName, int phone, String address) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean result = false;
        Date currentDate = DateCaculator.getCurrentDate();

        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "Insert into tbl_User(userID,password,fullName,address,phone,create_date,role,status) values(?,?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                stm.setString(3, fullName);
                stm.setString(4, address);
                stm.setInt(5, phone);
                stm.setDate(6, currentDate);
                stm.setInt(7, 2);
                stm.setInt(8, 1);
                int row = stm.executeUpdate();
                if (row > 0) {
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

    public boolean updateStatusAccount(String email) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "Update tbl_User set status = ? where userID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, 2);
                stm.setString(2, email);
                int row = stm.executeUpdate();
                if (row > 0) {
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

    public UserDTO checkUserGoogleIsExist(String userID) throws SQLException, NamingException {

        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {
                String sql = "Select userID , password , fullName, create_date , role , status from tbl_User where userID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {

                    String fullName = rs.getString("fullName");
                    int role = rs.getInt("role");
                    int status = rs.getInt("status");
                    Date date = rs.getDate("create_date");
                    user = new UserDTO(userID, "", fullName, "", 0, date, role, status);

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

        return user;

    }

    public boolean insertUserGoogleWhenLogin(UserDTO dto) throws NamingException, SQLException {

        boolean result = false;

        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {
                String sql = "Insert into tbl_User(userID,password,fullName,address,phone,create_date,role,status) values(?,?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getUserID());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setString(4, dto.getAddress());
                stm.setInt(5, dto.getPhone());
                stm.setDate(6, dto.getCreate_date());
                stm.setInt(7, dto.getRole());
                stm.setInt(8, dto.getStatus());

                int row = stm.executeUpdate();
                if (row > 0) {
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
