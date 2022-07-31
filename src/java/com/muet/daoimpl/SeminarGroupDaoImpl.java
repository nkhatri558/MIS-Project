/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.daoimpl;

import com.muet.connection.DBConnection;
import com.muet.dao.BatchDao;
import com.muet.dao.SeminarDao;
import com.muet.dao.SeminarGroupDao;
import com.muet.dao.StudentDao;
import com.muet.dao.ThesisDao;
import com.muet.dao.ThesisDetailsDao;
import com.muet.model.Batch;
import com.muet.model.Seminar;
import com.muet.model.SeminarGroup;
import com.muet.model.Student;
import com.muet.model.ThesisDetails;
import java.sql.Connection;
import java.sql.Date;
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
public class SeminarGroupDaoImpl implements SeminarGroupDao {

    private PreparedStatement pst;
    private ResultSet rst;
    private Connection con;

    @Override
    public Boolean updateSeminarGroup(SeminarGroup seminarGroup) {
        //To change body of generated methods, choose Tools | Templates.
        con = DBConnection.getConnection();
//        SeminarDao seminarDao = new SeminarDaoImpl();
//        Seminar seminar = new Seminar();
//        seminar.setSeminarId(seminarGroup.getSeminar().getSeminarId());
//        seminar.setSeminarTitle(seminarGroup.getSeminar().getSeminarTitle());
//        seminarDao.updateSeminar(seminar);
//        
//        Thesis thesis =new Thesis();
//        ThesisDao thesisDao=new ThesisDaoImpl();
//        thesis.setThesisId(seminarGroup.getThesis().getThesisId());
//        thesis.setThesisTitle(seminarGroup.getThesis().getThesisTitle());
//        thesisDao.updateThesis(thesis);
        try {
            pst = con.prepareStatement("update seminar_thesis set seminar_id = ?,date_applied=?,date_conducted=?,remarks=?,comments=?, status=? where seminar_group_id = ?");
            pst.setInt(1, seminarGroup.getSeminar().getSeminarId());
            pst.setDate(2, new Date(seminarGroup.getDateApplied().getTime()));
            pst.setDate(3, new Date(seminarGroup.getDateConducted().getTime()));
            pst.setString(4, seminarGroup.getRemarks());
            pst.setString(5, seminarGroup.getComments());
            pst.setString(6, seminarGroup.getStatus());
            //pst.setInt(7,seminarGroup.getThesis().getThesisId());
            pst.setInt(7, seminarGroup.getSeminarGroupId());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean deleteSeminarGroup(Integer id) {
        //To change body of generated methods, choose Tools | Templates.
        con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("delete from seminar_thesis where seminar_group_id = ?");
            pst.setInt(1, id);
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public SeminarGroup getSeminarGroupById(Integer id) {
        //To change body of generated methods, choose Tools | Templates.
        con = DBConnection.getConnection();
        SeminarGroup seminarGroup = new SeminarGroup();
        try {
            pst = con.prepareStatement("select * from seminar_thesis where seminar_group_id = ?");
            pst.setInt(1, id);
            rst = pst.executeQuery();
            while (rst.next()) {
                Seminar seminar = new Seminar();
                SeminarDao seminarDao = new SeminarDaoImpl();
                ThesisDetails thesis=new ThesisDetails();
                ThesisDetailsDao thesisDao=new ThesisDetailsDaoImpl();
                seminarGroup.setSeminarGroupId(rst.getInt("seminar_group_id"));
                seminarGroup.setDateApplied(rst.getDate("date_applied"));
                seminarGroup.setDateConducted(rst.getDate("date_conducted"));
                seminarGroup.setRemarks(rst.getString("remarks"));
                seminarGroup.setComments(rst.getString("comments"));
                seminarGroup.setStatus(rst.getString("status"));
                seminar = seminarDao.getSeminarById(rst.getInt("seminar_id"));
                seminarGroup.setSeminar(seminar);
                //thesis=thesisDao.getThesisById(rst.getInt("thesis_id"));
                //seminarGroup.setThesis(thesis);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seminarGroup;
    }

    @Override
    public List<SeminarGroup> getSeminarGroups() {
        //To change body of generated methods, choose Tools | Templates.
        con = DBConnection.getConnection();
        List<SeminarGroup> seminarGroups = new ArrayList<>();
        try {
            pst = con.prepareStatement("select * from seminar_thesis;");
            rst = pst.executeQuery();
            while (rst.next()) {
                SeminarGroup seminarGroup = new SeminarGroup();
                SeminarDao seminarDao = new SeminarDaoImpl();
                ThesisDetailsDao thesisDao=new ThesisDetailsDaoImpl();
                seminarGroup.setSeminarGroupId(rst.getInt("seminar_group_id"));
                seminarGroup.setDateApplied(rst.getDate("date_applied"));
                seminarGroup.setDateConducted(rst.getDate("date_conducted"));
                seminarGroup.setRemarks(rst.getString("remarks"));
                seminarGroup.setComments(rst.getString("comments"));
                seminarGroup.setStatus(rst.getString("status"));
                Seminar seminar = seminarDao.getSeminarById(rst.getInt("seminar_id"));
                seminarGroup.setSeminar(seminar);
                //ThesisDetails thesis=thesisDao.getThesisById(rst.getInt("thesis_id"));
                //seminarGroup.setThesis(thesis);
                StudentDao studentDao = new StudentDaoImpl();
                Student student = studentDao.getStudentById(rst.getInt("student_id"));
                seminarGroup.setStudent(student);
                seminarGroups.add(seminarGroup);
                System.out.println("Seminar List is working");
            }
            System.out.println(seminarGroups.size());
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seminarGroups;
    }

    @Override
    public List<SeminarGroup> getSeminarGroupsByStudentId(Integer id) {
        con = DBConnection.getConnection();
        List<SeminarGroup> seminarGroups = new ArrayList<>();
        try {
            pst = con.prepareStatement("SELECT * FROM seminar_thesis WHERE student_id = ?;");
            pst.setInt(1, id);
            rst = pst.executeQuery();
            while (rst.next()) {
                SeminarGroup seminarGroup = new SeminarGroup();
                SeminarDao seminarDao = new SeminarDaoImpl();
                ThesisDetailsDao thesisDao=new ThesisDetailsDaoImpl();
                seminarGroup.setSeminarGroupId(rst.getInt("seminar_group_id"));
                seminarGroup.setDateApplied(rst.getDate("date_applied"));
                seminarGroup.setDateConducted(rst.getDate("date_conducted"));
                seminarGroup.setRemarks(rst.getString("remarks"));
                seminarGroup.setComments(rst.getString("comments"));
                seminarGroup.setStatus(rst.getString("status"));
                Seminar seminar = seminarDao.getSeminarById(rst.getInt("seminar_id"));
                seminarGroup.setSeminar(seminar);
                //ThesisDetails thesis=thesisDao.getThesisById(rst.getInt("thesis_id"));
                //seminarGroup.setThesis(thesis);
                seminarGroups.add(seminarGroup);
                System.out.println("Seminar List is working");
            }
            System.out.println(seminarGroups.size());
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seminarGroups;
    }

    @Override
    public List<Seminar> getStudentSeminarRecords(Integer studentId, Integer batchId) {
        con = DBConnection.getConnection();
        List<Seminar> seminars = new ArrayList<>();
        
        try {
            pst = con.prepareStatement("SELECT s.* FROM seminar s WHERE CURDATE() BETWEEN s.`announcement_date` AND s.`due_date` \n" +
"AND s.`seminar_id` NOT IN (SELECT st.`seminar_id` FROM seminar_thesis st WHERE st.`student_id` = ?) AND s.`batch_id` = ?;");
            pst.setInt(1, studentId);
            pst.setInt(2, batchId);
            rst = pst.executeQuery();
            while ( rst.next() ) {
                Seminar seminar = new Seminar();
                seminar.setSeminarId(rst.getInt("seminar_id"));
                seminar.setSeminarTitle(rst.getString("seminar_title"));
                seminar.setAnnouncementDate(rst.getDate("announcement_date"));
                seminar.setDueDate(rst.getDate("due_date"));
                BatchDao batchDao = new BatchDaoImpl();
                Batch batch = batchDao.getBatchById(rst.getInt("batch_id"));
                seminar.setBatch(batch);
                seminars.add(seminar);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seminars;
    }

    @Override
    public Boolean addSeminarGroup(SeminarGroup seminarGroup) {
        con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("insert into seminar_thesis (seminar_id, date_applied, student_id) values (?, ?, ?)");
            pst.setInt(1, seminarGroup.getSeminar().getSeminarId());
            pst.setDate(2, new Date(System.currentTimeMillis()));
            pst.setInt(3, seminarGroup.getStudent().getStudentId());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SeminarGroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    

}
