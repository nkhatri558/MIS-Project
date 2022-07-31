/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.daoimpl;

import com.muet.connection.DBConnection;
import com.muet.dao.BatchDao;
import com.muet.dao.ThesisDao;
import com.muet.model.Batch;
import com.muet.model.Thesis;
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
public class ThesisDaoImpl implements ThesisDao {

    private Connection conn = null;
    private ResultSet rst = null;
    private PreparedStatement pst = null;

    @Override
    public Boolean addThesis(Thesis thesis) {
        //To change body of generated methods, choose Tools | Templates.
        conn = DBConnection.getConnection();
        BatchDao batchDao = new BatchDaoImpl();
        Batch batch = batchDao.getBatchByBatchName(thesis.getBatch().getBatchName());
        try {

            pst = conn.prepareStatement("insert into thesis(batch_id,announcement_date,due_date) values(?,?,?)");
            pst.setInt(1, batch.getBatchId());
            pst.setString(2, thesis.getAnnouncementDate());
            pst.setString(3, thesis.getDueDate());
            boolean check = pst.execute();
            return check;
        } catch (SQLException ex) {
            Logger.getLogger(ThesisDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Thesis> getAllThesis() {
        //To change body of generated methods, choose Tools | Templates.
        conn = DBConnection.getConnection();
        List<Thesis> list = new ArrayList<>();

        BatchDao batchDao = new BatchDaoImpl();
        try {
            pst = conn.prepareStatement("select * from thesis");
            rst = pst.executeQuery();
            while (rst.next()) {
                Batch batch = new Batch();
                Thesis thesis = new Thesis();
                thesis.setThesisId(rst.getInt(1));
                batch.setBatchId(rst.getInt(2));
                thesis.setAnnouncementDate(rst.getString(3));
                thesis.setDueDate(rst.getString(4));
                thesis.setBatch(batch);
                batch = batchDao.getBatchById(thesis.getBatch().getBatchId());
                thesis.setBatch(batch);
                list.add(thesis);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ThesisDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
    public Boolean updateThesis(Thesis thesis) {
        //To change body of generated methods, choose Tools | Templates.
        conn = DBConnection.getConnection();
        BatchDao batchDao = new BatchDaoImpl();
        Batch batch = batchDao.getBatchByBatchName(thesis.getBatch().getBatchName());
        try {
            pst = conn.prepareStatement("update thesis set batch_id=?,announcement_date=?,due_date=? where thesis_id=?");
            pst.setInt(1, batch.getBatchId());
            pst.setString(2, thesis.getAnnouncementDate());
            pst.setString(3, thesis.getDueDate());
            pst.setInt(4, thesis.getThesisId());
            boolean check = pst.execute();
            return check;
        } catch (SQLException ex) {
            Logger.getLogger(ThesisDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void delete(Integer id) {
        //To change body of generated methods, choose Tools | Templates.
        conn = DBConnection.getConnection();
        try {
            pst = conn.prepareStatement("delete from thesis where thesis_id=?");
            pst.setInt(1, id);
            boolean check = pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ThesisDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Thesis> getThesisDataById(Integer id) {
        //To change body of generated methods, choose Tools | Templates.
        conn = DBConnection.getConnection();
        List<Thesis> list = new ArrayList<>();

        try {
            pst = conn.prepareStatement("select * from thesis where thesis_id=?");
            pst.setInt(1, id);
            rst = pst.executeQuery();
            while (rst.next()) {
                Batch batch = new Batch();
                Thesis thesis = new Thesis();
                BatchDao batchDao = new BatchDaoImpl();
                batch = batchDao.getBatchById(rst.getInt("batch_id"));
                thesis.setBatch(batch);
                thesis.setThesisId(rst.getInt(1));
                batch.setBatchId(rst.getInt(2));
                thesis.setAnnouncementDate(rst.getString(3));
                thesis.setDueDate(rst.getString(4));
                thesis.setBatch(batch);
                list.add(thesis);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ThesisDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     *
     * @param batch
     * @return
     * @throws Exception
     */
    @Override
    public Thesis getThesisRecordByBatchId(Integer id) {
        conn = DBConnection.getConnection();
        Thesis thesis = new Thesis();
        try {

            pst = conn.prepareStatement("SELECT B.batch_name,T.`thesis_id`,B.`batch_id` FROM batch B INNER JOIN thesis T ON T.`batch_id`=B.`batch_id` WHERE  DATEDIFF(CURDATE(),due_date)<=0 && B.batch_id='" + id + "'");
            rst = pst.executeQuery();
            while (rst.next()) {
                thesis.setThesisId(rst.getInt("thesis_id"));
                System.out.println(thesis.getThesisId());
            }

        } catch (SQLException ex) {
            Logger.getLogger(ThesisDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thesis;
    }

}
