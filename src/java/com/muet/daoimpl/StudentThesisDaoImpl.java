/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.daoimpl;

import com.muet.connection.DBConnection;
import com.muet.dao.BatchDao;
import com.muet.dao.StudentThesisDao;
import com.muet.dao.ThesisDao;
import com.muet.model.Batch;
import com.muet.model.Student;
import com.muet.model.StudentThesis;
import com.muet.model.Thesis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 92310
 */
public class StudentThesisDaoImpl implements StudentThesisDao{

    private Connection conn=null;
    private PreparedStatement pst=null;
    private ResultSet rst=null;
    @Override
    public List<Batch> getAllBatches() {
         //To change body of generated methods, choose Tools | Templates.
         conn=DBConnection.getConnection();
        
         List<Batch> list=new ArrayList<>();         
        try {
            Statement st=conn.createStatement();
          rst= st.executeQuery("SELECT B.batch_name FROM batch B INNER JOIN thesis T ON T.`batch_id`=B.`batch_id` WHERE  DATEDIFF(CURDATE(),due_date)<=0");
          while(rst.next()){
               Batch batch=new Batch();
               
                batch.setBatchName(rst.getString(1));
                list.add(batch);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentThesisDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Boolean addStudentThesis(StudentThesis studentThesis) {
         //To change body of generated methods, choose Tools | Templates.
         conn=DBConnection.getConnection();
         BatchDao batchDao =new BatchDaoImpl();
         ThesisDao thesisDao=new ThesisDaoImpl();
        Batch batch= batchDao.getBatchByBatchName(studentThesis.getThesis().getBatch().getBatchName());
        Thesis thesis=thesisDao.getThesisRecordByBatchId(batch.getBatchId());
        try {            
            pst=conn.prepareStatement("insert into thesis_details(thesis_title,applied_date,student_id ,thesis_id,remarks,status,final_results) values(?,?,?,?,?,?,?)");
            pst.setString(1,studentThesis.getThesisTitle());
            pst.setString(2,studentThesis.getAppliedDate());
            pst.setInt(3,studentThesis.getStudent().getStudentId());
            pst.setInt(4,thesis.getThesisId());
            pst.setString(5,"Under Checking");
            pst.setString(6,"pending");
            pst.setInt(7,0);
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentThesisDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false ;
    }

    @Override
    public List<StudentThesis> getAllStudentThesis(Integer id) {
         //To change body of generated methods, choose Tools | Templates.
         conn=DBConnection.getConnection();
         List<StudentThesis> list=new ArrayList<>();
         
        try {
            pst=conn.prepareStatement("SELECT S.roll_number ,T.thesis_title ,T.applied_date ,T.final_results,T.remarks,T.status ,T.thesis_details_id FROM `thesis_details` T INNER JOIN student S ON S.`student_id`=T.`student_id` INNER JOIN \n" +
            "thesis TH ON TH.`thesis_id`=T.`thesis_id` WHERE DATEDIFF(CURDATE(),due_date)<=0 AND S.`student_id`=?");
            pst.setInt(1,id);
            rst=pst.executeQuery();
            while(rst.next()){
                Student student=new Student();
                StudentThesis studentThesis=new StudentThesis();
                Batch batch=new Batch();
                student.setRollNumber(rst.getString(1));
                studentThesis.setThesisTitle(rst.getString(2));
                studentThesis.setAppliedDate(rst.getString(3));
                studentThesis.setFinalResults(rst.getInt(4));
                studentThesis.setRemarks(rst.getString(5));
                studentThesis.setStatus(rst.getString(6));
                studentThesis.setThesisDetailsId(rst.getInt(7));
                studentThesis.setStudent(student);
                list.add(studentThesis);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentThesisDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;  
    }

    @Override
    public Boolean updateStudentThesis(StudentThesis thesis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentThesis getStudentThesisByStudentId(Integer id) {
       //To change body of generated methods, choose Tools | Templates.
       conn=DBConnection.getConnection();
       StudentThesis studentThesis=new StudentThesis();
       Thesis thesis=new Thesis();
        try {
            pst=conn.prepareStatement("select * from thesis_details where student_id=?");
            pst.setInt(1,id);
            rst=pst.executeQuery();
            while(rst.next()){
                studentThesis.setThesisDetailsId(rst.getInt("thesis_details_id"));
                studentThesis.setThesisTitle(rst.getString("thesis_title"));
                thesis.setThesisId(rst.getInt("thesis_id"));
                studentThesis.setThesis(thesis);
                studentThesis.setAppliedDate(rst.getString("applied_date"));
                studentThesis.setFinalResults(rst.getInt("final_results"));
                studentThesis.setStatus(rst.getString("status"));
                studentThesis.setRemarks(rst.getString("remarks"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentThesisDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       return studentThesis;
    }
    
}
