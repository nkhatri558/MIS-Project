/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.daoimpl;

import com.muet.connection.DBConnection;
import com.muet.dao.FacultyDao;
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
public class FacultyDaoImpl implements FacultyDao {
    private PreparedStatement pst;
    private ResultSet rst;
    private Connection con;

    @Override
    public Boolean addFaculty(Faculty faculty) {
        con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("insert into faculty (faculty_name) values (?)");
            pst.setString(1, faculty.getFacultyName());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean updateFaculty(Faculty faculty) {
        con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("update faculty set faculty_name = ? where faculty_id = ?");
            pst.setString(1, faculty.getFacultyName());
            pst.setInt(2, faculty.getFacultyId());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean deleteFaculty(Integer id) {
        con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("delete from faculty where faculty_id = ?");
            pst.setInt(1, id);
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    @Override
    public Faculty getFacultyById(Integer id) {
        con = DBConnection.getConnection();
        Faculty faculty = new Faculty();
        try {
            pst = con.prepareStatement("select * from faculty where faculty_id = ?");
            pst.setInt(1, id);
            rst = pst.executeQuery();
            while ( rst.next() ) {
                faculty.setFacultyId(rst.getInt("faculty_id"));
                faculty.setFacultyName(rst.getString("faculty_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return faculty;
    }

    @Override
    public List<Faculty> getFaculties() {
        con = DBConnection.getConnection();
        List<Faculty> faculties = new ArrayList<>();
        
        try {
            pst = con.prepareStatement("select * from faculty");
            rst = pst.executeQuery();
            while ( rst.next() ) {
                Faculty faculty = new Faculty();
                faculty.setFacultyId(rst.getInt("faculty_id"));
                faculty.setFacultyName(rst.getString("faculty_name"));
                faculties.add(faculty);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return faculties;
    }

    @Override
    public Faculty getFacultyByFacultyName(String facultyName) {
        con = DBConnection.getConnection();
        Faculty faculty = new Faculty();
        try {
            pst = con.prepareStatement("select * from faculty where faculty_name = ?");
            pst.setString(1, facultyName);
            rst = pst.executeQuery();
            while ( rst.next() ) {
                faculty.setFacultyId(rst.getInt("faculty_id"));
                faculty.setFacultyName(rst.getString("faculty_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return faculty;
    }
    
}
