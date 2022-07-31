/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.dao;

import com.muet.model.Fees;
import com.muet.model.StudentFees;
import java.util.List;

/**
 *
 * @author khatr
 */
public interface FeesDao {
    /**
     *
     * @param fees
     * @return 
     */
    
    public Boolean addFees(Fees fees);
    
    /**
     *
     * @param fees
     * @return 
     */
    
    public Boolean updateFees(Fees fees);
    
    /**
     * @param id
     * @return 
     */
    
    public Boolean deleteFees(Integer id);

    /**
     *
     * @param id
     * @return 
     */

    public Fees getFeesById(Integer id);
    
    /**
     *
     * @return List of Faculty
     */
    public List<Fees> getFees();
    
    public List<Fees> getFeesListByStudentId(Integer studentId);
    
   
}
