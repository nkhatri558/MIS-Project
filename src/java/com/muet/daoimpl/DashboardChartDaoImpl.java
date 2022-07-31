/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.daoimpl;

import com.muet.connection.DBConnection;
import com.muet.dao.DashboardChartDao;
import com.muet.model.DashboardChart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 92310
 */
public class DashboardChartDaoImpl implements DashboardChartDao {

    private Connection conn;
    private ResultSet rst;
    private PreparedStatement pst;

    List<DashboardChart> list = new ArrayList<>();

    @Override
    public List<DashboardChart> getRegistrationRecords() {
        //To change body of generated methods, choose Tools | Templates.
        conn = DBConnection.getConnection();

        DashboardChart dc = new DashboardChart();
        try {
            pst = conn.prepareStatement("select count(student_id) from student where registration_status=?");
            pst.setString(1, "registered");
            rst = pst.executeQuery();
            while (rst.next()) {
                dc.setRegisteredStudentsCount(rst.getInt(1));
                list.add(dc);
            }
            pst = conn.prepareStatement("select count(student_id) from student where registration_status=?");
            pst.setString(1, "unregistered");
            rst = pst.executeQuery();
            while (rst.next()) {
                dc.setUnRegisteredStudentsCount(rst.getInt(1));
                list.add(dc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardChartDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
    public List<DashboardChart> getSeminarRecords() {
        conn = DBConnection.getConnection();

        DashboardChart dc = new DashboardChart();
        try {
            //To change body of generated methods, choose Tools | Templates.

            pst = conn.prepareStatement("select count(seminar_group_id) from seminar_thesis where status=?");
            pst.setString(1, "approved");
            rst = pst.executeQuery();
            while (rst.next()) {
                dc.setSeminarApproved(rst.getInt(1));
                list.add(dc);
            }
            pst = conn.prepareStatement("SELECT COUNT(seminar_group_id) FROM seminar_thesis WHERE STATUS=?");
            pst.setString(1, "rejected");
            rst = pst.executeQuery();
            while (rst.next()) {
                dc.setSeminarRejected(rst.getInt(1));
                list.add(dc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardChartDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<DashboardChart> getFeesRecords() {
        //To change body of generated methods, choose Tools | Templates.
        conn = DBConnection.getConnection();
        DashboardChart dc = new DashboardChart();
        try {
            //To change body of generated methods, choose Tools | Templates.

            pst = conn.prepareStatement("select count(student_fees_id) from student_fees where status=?");
            pst.setString(1, "accepted");
            rst = pst.executeQuery();
            while (rst.next()) {
                int value = rst.getInt(1);
                dc.setFeesPaidCount(value);
                list.add(dc);
            }
            pst = conn.prepareStatement("select count(student_fees_id) from student_fees where status=?");
            pst.setString(1, "rejected");
            rst = pst.executeQuery();
            while (rst.next()) {
                dc.setFeesUnPaidCount(rst.getInt(1));
                list.add(dc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardChartDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<DashboardChart> getGenderRecords() {
        //To change body of generated methods, choose Tools | Templates.
         conn = DBConnection.getConnection();
        DashboardChart dc = new DashboardChart();
        try {
            //To change body of generated methods, choose Tools | Templates.

            pst = conn.prepareStatement("select count(student_id) from student where gender=?");
            pst.setString(1, "male");
            rst = pst.executeQuery();
            while (rst.next()) {
                
                dc.setMaleCount(rst.getInt(1));
                list.add(dc);
            }
            pst = conn.prepareStatement("select count(student_id) from student where gender=?");
            pst.setString(1, "female");
            rst = pst.executeQuery();
            while (rst.next()) {
                dc.setFemaleCount(rst.getInt(1));
                list.add(dc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardChartDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

}
