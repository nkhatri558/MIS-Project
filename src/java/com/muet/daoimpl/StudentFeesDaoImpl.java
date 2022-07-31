/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.daoimpl;

import com.muet.connection.DBConnection;
import com.muet.dao.FeesDao;
import com.muet.dao.StudentDao;
import com.muet.model.Fees;
import com.muet.model.Student;
import com.muet.model.StudentFees;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.muet.dao.StudentFeesDao;

/**
 *
 * @author khatr
 */
public class StudentFeesDaoImpl implements StudentFeesDao{
    private final Connection con;
    private PreparedStatement pst;
    private ResultSet rst;
    
    public StudentFeesDaoImpl() {
        con = DBConnection.getConnection();
    }

    @Override
    public Boolean addStudentFees(StudentFees studentFees) {
        try {
            pst = con.prepareStatement("insert into student_fees (student_id, fees_id, date_paid, amount_paid, late_fees, challan_no) values (?, ?, ?, ?, ?, ?);");
            pst.setInt(1, studentFees.getStudent().getStudentId());
            pst.setInt(2, studentFees.getFees().getFeesId());
            pst.setString(3, studentFees.getDatePaid());
            pst.setInt(4, studentFees.getAmountPaid());
            pst.setInt(5, studentFees.getLateFees());
            pst.setInt(6, studentFees.getChallanNumber());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentFeesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean updateStudentFees(StudentFees studentFees) {
        try {
            pst = con.prepareStatement("update student_fees set student_id = ?, fees_id = ?, date_paid = ?, amount_paid = ?, late_fees = ?, challan_no = ?, challan_image = ?, status = ? where student_fees_id = ?;");
            pst.setInt(1, studentFees.getStudent().getStudentId());
            pst.setInt(2, studentFees.getFees().getFeesId());
            pst.setString(3, studentFees.getDatePaid());
            pst.setInt(4, studentFees.getAmountPaid());
            pst.setInt(5, studentFees.getLateFees());
            pst.setInt(6, studentFees.getChallanNumber());
            pst.setString(7, studentFees.getChallanImage());
            pst.setString(8, studentFees.getStatus());
            pst.setInt(9, studentFees.getStudentFeesId());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentFeesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean deleteStudentFees(Integer id) {
        try {
            pst = con.prepareStatement("delete from student_fees where student_fees_id = ?");
            pst.setInt(1, id);
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentFeesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public StudentFees getStudentFeesById(Integer id) {
        StudentFees studentFees = new StudentFees();
        try {
            pst = con.prepareStatement("select * from student_fees where student_fees_id = ?");
            pst.setInt(1, id);
            rst = pst.executeQuery();
            while(rst.next()) {
                studentFees.setStudentFeesId(rst.getInt("student_fees_id"));
                StudentDao studentDao = new StudentDaoImpl();
                Student student = studentDao.getStudentById(rst.getInt("student_id"));
                studentFees.setStudent(student);
                FeesDao feesDao = new FeesDaoImpl();
                Fees fees = feesDao.getFeesById(rst.getInt("fees_id"));
                studentFees.setFees(fees);
                studentFees.setDatePaid(rst.getString("date_paid"));
                studentFees.setAmountPaid(rst.getInt("amount_paid"));
                studentFees.setLateFees(rst.getInt("late_fees"));
                studentFees.setChallanNumber(rst.getInt("challan_no"));
                studentFees.setChallanImage(rst.getString("challan_image"));
                studentFees.setStatus(rst.getString("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFeesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentFees;
    }

    @Override
    public List<StudentFees> getStudentFee() {
        List<StudentFees> studentFee = new ArrayList();
        try {
            pst = con.prepareStatement("select * from student_fees;");
            rst = pst.executeQuery();
            while(rst.next()) {
                StudentFees studentFees = new StudentFees();
                studentFees.setStudentFeesId(rst.getInt("student_fees_id"));
                StudentDao studentDao = new StudentDaoImpl();
                Student student = studentDao.getStudentById(rst.getInt("student_id"));
                studentFees.setStudent(student);
                FeesDao feesDao = new FeesDaoImpl();
                Fees fees = feesDao.getFeesById(rst.getInt("fees_id"));
                studentFees.setFees(fees);
                studentFees.setDatePaid(rst.getString("date_paid"));
                studentFees.setAmountPaid(rst.getInt("amount_paid"));
                studentFees.setLateFees(rst.getInt("late_fees"));
                studentFees.setChallanNumber(rst.getInt("challan_no"));
                studentFees.setChallanImage(rst.getString("challan_image"));
                studentFees.setStatus(rst.getString("status"));
                studentFee.add(studentFees);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentFeesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentFee;
    }
    
    @Override
    public List<StudentFees> getFeesByStudentId(Integer id) {
        List<StudentFees> studentFeeRecords = new ArrayList<>();
        try {
            pst = con.prepareStatement("select * from student_fees where student_id = ?;");
            pst.setInt(1, id);
            rst = pst.executeQuery();
            while (rst.next()) {
                StudentFees studentFees = new StudentFees();
                studentFees.setStudentFeesId(rst.getInt("student_fees_id"));
                StudentDao studentDao = new StudentDaoImpl();
                Student student = studentDao.getStudentById(rst.getInt("student_id"));
                FeesDao feesDao = new FeesDaoImpl();
                Fees fees = feesDao.getFeesById(rst.getInt("fees_id"));
                studentFees.setStudent(student);
                studentFees.setFees(fees);
                studentFees.setDatePaid(rst.getString("date_paid"));
                studentFees.setAmountPaid(rst.getInt("amount_paid"));
                studentFees.setChallanNumber(rst.getInt("challan_no"));
                studentFees.setLateFees(rst.getInt("late_fees"));
                studentFees.setChallanImage(rst.getString("challan_image"));
                studentFees.setStatus(rst.getString("status"));
                studentFeeRecords.add(studentFees);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentFeeRecords;
    }

    @Override
    public Boolean addNewStudentFeesRecord(StudentFees studentFees) {
        try {
            pst = con.prepareStatement("insert into student_fees (student_id, fees_id, date_paid, amount_paid, late_fees, challan_no, challan_image, status) values (?, ?, ?, ?, ?, ?,?,?);");
            pst.setInt(1, studentFees.getStudent().getStudentId());
            pst.setInt(2, studentFees.getFees().getFeesId());
            pst.setString(3, studentFees.getDatePaid());
            pst.setInt(4, studentFees.getAmountPaid());
            pst.setInt(5, studentFees.getLateFees());
            pst.setInt(6, studentFees.getChallanNumber());
            pst.setString(7, studentFees.getChallanImage());
            pst.setString(8, studentFees.getStatus());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentFeesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
