/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.daoimpl;

import com.muet.connection.DBConnection;
import com.muet.dao.BatchDao;
import com.muet.dao.SeminarDao;
import com.muet.model.Batch;
import com.muet.model.Seminar;
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
public class SeminarDaoImpl implements SeminarDao{

    private PreparedStatement pst;
    private ResultSet rst;
    private Connection con;

    @Override
    public Boolean addSeminar(Seminar seminar) {
        con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("insert into seminar (seminar_title, announcement_date, due_date, batch_id) values (?,?,?,?)");
            pst.setString(1, seminar.getSeminarTitle());
            pst.setDate(2, new Date(seminar.getAnnouncementDate().getTime()));
            pst.setDate(3, new Date(seminar.getDueDate().getTime()));
            pst.setInt(4, seminar.getBatch().getBatchId());
            System.out.println("Done");
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false ;
    }

    @Override
    public Boolean updateSeminar(Seminar seminar) {
       //To change body of generated methods, choose Tools | Templates.
       con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("update seminar set seminar_title = ?, announcement_date = ?, due_date = ?, batch_id = ? where seminar_id = ?");
            
            pst.setString(1, seminar.getSeminarTitle());
            pst.setDate(2, new java.sql.Date(seminar.getAnnouncementDate().getTime()));
            pst.setDate(3, new Date(seminar.getDueDate().getTime()));
            pst.setInt(4, seminar.getBatch().getBatchId());
            pst.setInt(5, seminar.getSeminarId());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean deleteSeminar(Integer id) {
        //To change body of generated methods, choose Tools | Templates.
        con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("delete from seminar where seminar_id = ?");
            pst.setInt(1, id);
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Seminar getSeminarById(Integer id) {
         //To change body of generated methods, choose Tools | Templates.
         con = DBConnection.getConnection();
        Seminar seminar = new Seminar();
        try {
            pst = con.prepareStatement("select * from seminar where seminar_id = ?");
            pst.setInt(1, id);
            rst = pst.executeQuery();
            while ( rst.next() ) {
                seminar.setSeminarId(rst.getInt("seminar_id"));
                seminar.setSeminarTitle(rst.getString("seminar_title"));
                seminar.setAnnouncementDate(rst.getDate("announcement_date"));
                System.out.println("0-------------->" + rst.getDate("announcement_date"));  
                seminar.setDueDate(rst.getDate("due_date"));
                BatchDao batchDao = new BatchDaoImpl();
                Batch batch = batchDao.getBatchById(rst.getInt("batch_id"));
                seminar.setBatch(batch);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seminar;
    }

    @Override
    public List<Seminar> getSeminars() {
        //To change body of generated methods, choose Tools | Templates.
        con = DBConnection.getConnection();
        List<Seminar> seminars = new ArrayList<>();
        
        try {
            pst = con.prepareStatement("select * from seminar");
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
    public Seminar getSeminarBySeminarName(String seminarTitle) {
         con = DBConnection.getConnection();
        Seminar seminar = new Seminar();
        try {
            pst = con.prepareStatement("select * from seminar where seminar_title = ?");
            pst.setString(1, seminarTitle);
            rst = pst.executeQuery();
            while ( rst.next() ) {
                seminar.setSeminarId(rst.getInt("seminar_id"));
                seminar.setSeminarTitle(rst.getString("seminar_title"));
                seminar.setAnnouncementDate(rst.getDate("announcement_date"));
                seminar.setDueDate(rst.getDate("due_date"));
                BatchDao batchDao = new BatchDaoImpl();
                Batch batch = batchDao.getBatchById(rst.getInt("batch_id"));
                seminar.setBatch(batch);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seminar;
    }
}
    

