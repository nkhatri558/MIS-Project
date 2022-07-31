/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.dao;

import com.muet.model.Faculty;
import java.util.List;

/**
 *
 * @author khatr
 */
public interface FacultyDao {
    
    /**
     *
     * @param faculty
     * @return 
     */
    
    public Boolean addFaculty(Faculty faculty);
    
    /**
     *
     * @param faculty
     * @return 
     */
    
    public Boolean updateFaculty(Faculty faculty);
    
    /**
     * @param id
     * @return 
     */
    
    public Boolean deleteFaculty(Integer id);

    /**
     *
     * @param id
     * @return 
     */

    public Faculty getFacultyById(Integer id);
    
    /**
     *
     * @return List of Faculty
     */
    public List<Faculty> getFaculties();
    /**
     *
     * @param facultyName
     * @return 
     */

    public Faculty getFacultyByFacultyName(String facultyName);
}
