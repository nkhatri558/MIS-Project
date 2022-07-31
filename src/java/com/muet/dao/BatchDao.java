/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.dao;

import com.muet.model.Batch;
import java.util.List;

/**
 *
 * @author khatr
 */
public interface BatchDao {
    /**
     *
     * @param batch
     * @return 
     */
    
    public Boolean addBatch(Batch batch);
    
    /**
     *
     * @param batch
     * @return 
     */
    
    public Boolean updateBatch(Batch batch);
    
    /**
     * @param id
     * @return 
     */
    
    public Boolean deleteBatch(Integer id);

    /**
     *
     * @param id
     * @return 
     */

    public Batch getBatchById(Integer id);
    
    /**
     *
     * @return List of Faculty
     */
    public List<Batch> getBatches();
    /**
     *
     * @param batchName
     * @return 
     */

    public Batch getBatchByBatchName(String batchName);
}
