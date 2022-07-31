/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.dao;

import com.muet.model.Seminar;
import com.muet.model.SeminarGroup;
import java.util.List;

/**
 *
 * @author 92310
 */
public interface SeminarGroupDao {
     
    
    public Boolean updateSeminarGroup(SeminarGroup seminarGroup);
    
    /**
     * @param id
     * @return 
     */
    
    public Boolean deleteSeminarGroup(Integer id);

    /**
     *
     * @param id
     * @return 
     */

    public SeminarGroup getSeminarGroupById(Integer id);
    
    /**
     *
     * @return List of Seminar
     */
    public List<SeminarGroup> getSeminarGroups();
   /**
     *
     * @param id
     * @return List of Seminar
     */
    public List<SeminarGroup> getSeminarGroupsByStudentId(Integer id);
    
    public List<Seminar> getStudentSeminarRecords(Integer studentId, Integer batchId);
    
    public Boolean addSeminarGroup(SeminarGroup seminarGroup);

    
}
