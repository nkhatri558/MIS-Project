/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.dao;

import com.muet.model.StudentFees;
import java.util.List;

/**
 *
 * @author khatr
 */
public interface StudentFeesDao {
    /**
     *
     * @param studentFees
     * @return 
     */
    
    public Boolean addStudentFees(StudentFees studentFees);
    
    /**
     *
     * @param studentFees
     * @return 
     */
    
    public Boolean updateStudentFees(StudentFees studentFees);
    
    /**
     * @param id
     * @return 
     */
    
    public Boolean deleteStudentFees(Integer id);

    /**
     *
     * @param id
     * @return 
     */

    public StudentFees getStudentFeesById(Integer id);
    
    /**
     *
     * @return List of Faculty
     */
    public List<StudentFees> getStudentFee();
    /**
     *
     * @param id
     * @return List of Fee
     */
    public List<StudentFees> getFeesByStudentId(Integer id);
    
    public Boolean addNewStudentFeesRecord(StudentFees studentFees);
}
