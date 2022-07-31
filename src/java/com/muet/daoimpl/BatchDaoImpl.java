/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.daoimpl;

import com.muet.connection.DBConnection;
import com.muet.dao.BatchDao;
import com.muet.model.Batch;
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
 * @author khatr
 */
public class BatchDaoImpl implements BatchDao {
    
    private final Connection con;
    private PreparedStatement pst;
    private ResultSet rst;
    
    public BatchDaoImpl() {
        con = DBConnection.getConnection();
    }
    @Override
    public Boolean addBatch(Batch batch) {
        try {
            pst = con.prepareStatement("insert into batch (batch_name) values (?)");
            pst.setString(1, batch.getBatchName());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BatchDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return false;
    }

    @Override
    public Boolean updateBatch(Batch batch) {
        try {
            pst = con.prepareStatement("update batch set batch_name = ? where batch_id = ?");
            pst.setString(1, batch.getBatchName());
            pst.setInt(2, batch.getBatchId());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BatchDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public Boolean deleteBatch(Integer id) {
        try {
            pst = con.prepareStatement("delete from batch where batch_id = ?");
            pst.setInt(1, id);
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BatchDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public Batch getBatchById(Integer id) {
        Batch batch = new Batch();
        try {
            pst = con.prepareStatement("select * from batch where batch_id = ?");
            pst.setInt(1, id);
            rst = pst.executeQuery();
            while (rst.next()) {
                batch.setBatchId(rst.getInt("batch_id"));
                batch.setBatchName(rst.getString("batch_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BatchDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return batch;
    }

    @Override
    public List<Batch> getBatches() {
        List<Batch> batches = new ArrayList<>();
        try {
            pst = con.prepareStatement("select * from batch;");
            rst = pst.executeQuery();
            while (rst.next()) {
                Batch batch = new Batch();
                batch.setBatchId(rst.getInt("batch_id"));
                batch.setBatchName(rst.getString("batch_name"));
                batches.add(batch);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BatchDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return batches;
    }

    @Override
    public Batch getBatchByBatchName(String batchName) {
        Batch batch = new Batch();
        try {
            pst = con.prepareStatement("select * from batch where batch_name = ?");
            pst.setString(1, batchName);
            rst = pst.executeQuery();
            while (rst.next()) {
                batch.setBatchId(rst.getInt("batch_id"));
                batch.setBatchName(rst.getString("batch_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BatchDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return batch;
    }
    
}
