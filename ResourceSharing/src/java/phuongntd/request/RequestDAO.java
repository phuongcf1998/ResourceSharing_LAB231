/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.request;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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
public class RequestDAO implements Serializable {

    private List<RequestDTO> listRequest;

    public List<RequestDTO> getListRequest() {
        return listRequest;
    }

    public boolean insertRequest(String requestID, String requestName, String userID, double total, Date createTime) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {

                String sql = "Insert into tbl_Request(requestID,requestName ,userID,total,request_date,status) values(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, requestID);
                stm.setString(2, requestName);
                stm.setString(3, userID);
                stm.setDouble(4, total);
                stm.setDate(5, createTime);
                stm.setInt(6, 1);
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

    public void getAllRequestForHistoryUser(String userID) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {

                String sql = "Select requestID,requestName,total,request_date,status from tbl_Request where userID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (listRequest == null) {
                        listRequest = new ArrayList<>();
                    }
                    String requestID = rs.getString("requestID");
                    int total = rs.getInt("total");
                    String reqName = rs.getString("requestName");
                    Date requestDate = rs.getDate("request_date");
                    int status = rs.getInt("status");
                    RequestDTO req = new RequestDTO(requestID, reqName, userID, total, requestDate, status);
                    listRequest.add(req);
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

    public boolean updateRequest(String requestID, int status) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {

                String sql = "Update tbl_Request set status = ? where requestID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setString(2, requestID);
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

    public void searchRequestHistory(String userID, String requestName, Date requestDate, int pageIndex, int pageSize) throws SQLException, NamingException {

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {

                String sql = "Select requestID,requestName,total,request_date,status from tbl_Request "
                        + "WHERE requestName LIKE ? AND userID =? AND request_date =? ORDER BY requestName DESC "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? "
                        + "ROW ONLY";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + requestName + "%");
                stm.setString(2, userID);
                stm.setDate(3, requestDate);
                stm.setInt(4, (pageIndex - 1) * pageSize);
                stm.setInt(5, pageSize);
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (listRequest == null) {
                        listRequest = new ArrayList<>();
                    }
                    String requestID = rs.getString("requestID");
                    int total = rs.getInt("total");
                    String reqName = rs.getString("requestName");
                    Date requestDateResult = rs.getDate("request_date");
                    int status = rs.getInt("status");
                    RequestDTO req = new RequestDTO(requestID, reqName, userID, total, requestDateResult, status);
                    listRequest.add(req);
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

    public int countSearchRequestHistory(String userID, String requestName, Date requestDate) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        int result = 0;

        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "Select COUNT(requestID) from tbl_Request "
                        + "WHERE requestName LIKE ? AND userID =? AND request_date =?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + requestName + "%");
                stm.setString(2, userID);
                stm.setDate(3, requestDate);
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

    public void searchRequestByManager(String userID, String resourceName, Date requestDate, int status, int pageIndex, int pageSize) throws SQLException, NamingException {

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {

                String sql = "Select tbl_Request.requestID,userID,requestName,total,request_date,status from tbl_Request  INNER JOIN tbl_RequestDetail ON tbl_Request.requestID = tbl_RequestDetail.requestID  "
                        + "WHERE resoureName LIKE ? AND userID LIKE ? AND request_date = ? and status = ? GROUP BY tbl_Request.requestID,userID,requestName,total,request_date,status ORDER BY request_date DESC "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? "
                        + "ROW ONLY";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + resourceName + "%");
                stm.setString(2, "%" + userID + "%");
                stm.setDate(3, requestDate);
                stm.setInt(4, status);
                stm.setInt(5, (pageIndex - 1) * pageSize);
                stm.setInt(6, pageSize);
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (listRequest == null) {
                        listRequest = new ArrayList<>();
                    }
                    String requestID = rs.getString("requestID");
                    String userIdResult = rs.getString("userID");
                    int total = rs.getInt("total");
                    Date requestDateResult = rs.getDate("request_date");
                    String reqName = rs.getString("requestName");
                    RequestDTO req = new RequestDTO(requestID, reqName, userIdResult, total, requestDateResult, status);
                    listRequest.add(req);
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

    public int countSearchRequestManager(String userID, String resourceName, int status, Date requestDate) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        int result = 0;

        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "Select COUNT(tbl_Request.requestID) from tbl_Request INNER JOIN tbl_RequestDetail ON tbl_Request.requestID = tbl_RequestDetail.requestID  "
                        + "WHERE resoureName LIKE ? AND userID LIKE ? AND request_date = ? and status = ? GROUP BY tbl_Request.requestID,userID,requestName,total,request_date,status";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + resourceName + "%");
                stm.setString(2, "%" + userID + "%");
                stm.setDate(3, requestDate);
                stm.setInt(4, status);
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

    public int getStatusRequestById(String id) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;

        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT status from tbl_Request WHERE requestID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {

                    int status = rs.getInt("status");

                    result = status;
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
}
