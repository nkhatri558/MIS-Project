/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.daoimpl;

import com.muet.connection.DBConnection;
import com.muet.dao.DepartmentDao;
import com.muet.dao.FacultyDao;
import com.muet.model.Department;
import com.muet.model.Faculty;
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
public class DepartmentDaoImpl implements DepartmentDao{
    private PreparedStatement pst;
    private ResultSet rst;
    private Connection con;
    @Override
    public Boolean addDepartment(Department department) {
        con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("insert into department (faculty_id, department_name) values (?, ?)");
            pst.setInt(1, department.getFaculty().getFacultyId());
            pst.setString(2, department.getDepartmentName());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public Boolean updateDepartment(Department department) {
        con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("update department set faculty_id = ?, department_name = ? where department_id = ?");
            pst.setInt(1, department.getFaculty().getFacultyId());
            pst.setString(2, department.getDepartmentName());
            pst.setInt(3, department.getDepartmentId());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        return false;
    }

    @Override
    public Boolean deleteDepartment(Integer id) {
        con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("delete from department where department_id = ?");
            pst.setInt(1, id);
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public Department getDepartmentById(Integer id) {
        con = DBConnection.getConnection();
        Department department = new Department();
        try {
            pst = con.prepareStatement("SELECT * from department where department_id = ?");
            pst.setInt(1, id);
            rst = pst.executeQuery();
            while(rst.next()) {
                department.setDepartmentId(rst.getInt("department_id"));
                department.setDepartmentName(rst.getString("department_name"));
                FacultyDao facultyDao = new FacultyDaoImpl();
                Faculty faculty = facultyDao.getFacultyById(rst.getInt("faculty_id"));
                department.setFaculty(faculty);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return department;
    }

    @Override
    public List<Department> getDepartments() {
        con = DBConnection.getConnection();
        List<Department> departments = new ArrayList<>();
        try {
            pst = con.prepareStatement("SELECT * from department;");
            rst = pst.executeQuery();
            while(rst.next()) {
                Department department = new Department();
                department.setDepartmentId(rst.getInt("department_id"));
                department.setDepartmentName(rst.getString("department_name"));
                FacultyDao facultyDao = new FacultyDaoImpl();
                Faculty faculty = facultyDao.getFacultyById(rst.getInt("faculty_id"));
                department.setFaculty(faculty);
                departments.add(department);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return departments;
    }
    @Override
    public Boolean updateDepartmentOnly(Department department) {
        con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("update department set department_name = ? where department_id = ?");
            pst.setString(1, department.getDepartmentName());
            pst.setInt(2, department.getDepartmentId());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
        return false;
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        con = DBConnection.getConnection();
        Department department = new Department();
        try {
            pst = con.prepareStatement("SELECT * from department where department_name = ?");
            pst.setString(1, departmentName);
            rst = pst.executeQuery();
            while(rst.next()) {
                department.setDepartmentId(rst.getInt("department_id"));
                department.setDepartmentName(rst.getString("department_name"));
                FacultyDao facultyDao = new FacultyDaoImpl();
                Faculty faculty = facultyDao.getFacultyById(rst.getInt("faculty_id"));
                department.setFaculty(faculty);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return department;
    }
}
