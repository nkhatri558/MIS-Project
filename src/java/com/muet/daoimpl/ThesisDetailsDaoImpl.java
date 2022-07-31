/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.daoimpl;

import com.muet.connection.DBConnection;
import com.muet.dao.StudentDao;
import com.muet.dao.ThesisDao;
import com.muet.model.ThesisDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.muet.model.Student;
import com.muet.dao.ThesisDetailsDao;
import com.muet.model.Thesis;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author khatr
 */
@MultipartConfig
@WebServlet(name = "ThesisDetailsController", urlPatterns = {"/ThesisDetailsController"})
public class ThesisDetailsDaoImpl implements ThesisDetailsDao{
    
    private final Connection con;
    private PreparedStatement pst;
    private ResultSet rst;
    
    public ThesisDetailsDaoImpl() {
        con = DBConnection.getConnection();
    }

    @Override
    public Boolean addThesis(ThesisDetails thesis) {
        
        try {
            pst = con.prepareStatement("insert into thesis (thesis_title, student_id, thesis_exam_date, final_results, remarks) values (?, ?, ?, ?, ?);");
            pst.setString(1, thesis.getThesisTitle());
            pst.setInt(2, thesis.getStudent().getStudentId());
            pst.setString(3, thesis.getThesisExamDate());
            pst.setInt(4, thesis.getFinalResults());
            pst.setString(5, thesis.getRemarks());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ThesisDetailsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public Boolean updateThesis(ThesisDetails thesis) {
        try {
            ThesisDetailsDao thesisDaoImpl=new ThesisDetailsDaoImpl();
            thesisDaoImpl.getThesisById(thesis.getThesisDetailsId());
            pst = con.prepareStatement("update thesis_details set thesis_title = ?, applied_date = ?, final_results = ?, remarks = ?, student_id = ? , status = ? where thesis_details_id = ?");
            pst.setString(1, thesis.getThesisTitle());
            pst.setString(2, thesis.getThesisExamDate());
            pst.setInt(3, thesis.getFinalResults());
            pst.setString(4, thesis.getRemarks());
            pst.setInt(5, thesis.getStudent().getStudentId());
            pst.setString(6,thesis.getStatus());
            pst.setInt(7, thesis.getThesisDetailsId());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ThesisDetailsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public Boolean DeleteThesis(Integer id) {
        try {
            pst = con.prepareStatement("delete from thesis_details  where thesis_details_id = ?");
            pst.setInt(1, id);
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ThesisDetailsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ThesisDetails getThesisById(Integer id) {
        ThesisDetails thesisDetails = new ThesisDetails();
        try {
            pst = con.prepareStatement("select * from thesis_details where thesis_details_id = ?");
            pst.setInt(1, id);
            rst = pst.executeQuery();
            while (rst.next()) {
                StudentDao studentDao = new StudentDaoImpl();
                Student student = studentDao.getStudentById(rst.getInt("student_id"));
                thesisDetails.setStudent(student);
                Thesis thesis=new Thesis();
                PreparedStatement p=con.prepareStatement("select * from thesis where thesis_id=?");
                p.setInt(1,rst.getInt("thesis_id"));
                ResultSet rs=p.executeQuery();
                while(rs.next()){
                    thesis.setDueDate(rs.getString("due_date"));
                }
                thesisDetails.setThesis(thesis);
                thesisDetails.setThesisTitle(rst.getString("thesis_title"));
                thesisDetails.setThesisExamDate(rst.getString("applied_date"));
                thesisDetails.setThesisDetailsId(rst.getInt("thesis_details_id"));
                thesisDetails.setFinalResults(rst.getInt("final_results"));
                thesisDetails.setRemarks(rst.getString("remarks"));
                thesisDetails.setStatus(rst.getString("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThesisDetailsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thesisDetails;
    }

    @Override
    public List<ThesisDetails> getThesisDetails() {
        List<ThesisDetails> theses = new ArrayList<>();
        try {
            pst = con.prepareStatement("select * from thesis_details;");
            rst = pst.executeQuery();
            while (rst.next()) {
                ThesisDetails thesisDetails = new ThesisDetails();
                Thesis thesis=new Thesis();
                ThesisDao thesisDao=new ThesisDaoImpl();
                StudentDao studentDao = new StudentDaoImpl();
                Student student = studentDao.getStudentById(rst.getInt("student_id"));
                thesisDetails.setStudent(student);
                PreparedStatement p=con.prepareStatement("select * from thesis where thesis_id=?");
                p.setInt(1,rst.getInt("thesis_id"));
                ResultSet rs=p.executeQuery();
                while(rs.next()){
                    thesis.setDueDate(rs.getString("due_date"));
                }
                thesisDetails.setThesis(thesis);
                thesisDetails.setThesisTitle(rst.getString("thesis_title"));
                thesisDetails.setThesisExamDate(rst.getString("applied_date"));
                thesisDetails.setThesisDetailsId(rst.getInt("thesis_details_id"));
                thesisDetails.setFinalResults(rst.getInt("final_results"));
                thesisDetails.setRemarks(rst.getString("remarks"));
                thesisDetails.setStatus(rst.getString("status"));
                theses.add(thesisDetails);    
                System.out.println("Applied date is "+thesisDetails.getThesis().getDueDate());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ThesisDetailsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return theses;
    }
    
}
