/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.dao;

import com.muet.model.Department;
import java.util.List;

/**
 *
 * @author khatr
 */
public interface DepartmentDao {
    /**
     *
     * @param department
     * @return 
     */
    
    public Boolean addDepartment(Department department);
    
    /**
     *
     * @param department
     * @return 
     */
    
    public Boolean updateDepartment(Department department);
    
    /**
     * @param department
     * @return 
     */
    public Boolean updateDepartmentOnly(Department department);
    
    public Boolean deleteDepartment(Integer id);

    /**
     *
     * @param id
     * @return 
     */

    public Department getDepartmentById(Integer id);
    /**
     *
     * @param departmentName
     * @return 
     */

    public Department getDepartmentByName(String departmentName);
    
    /**
     *
     * @return List of Faculty
     */
    public List<Department> getDepartments();
}
