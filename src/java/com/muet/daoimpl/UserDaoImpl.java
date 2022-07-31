/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.daoimpl;

import com.muet.connection.DBConnection;
import com.muet.dao.StudentDao;
import com.muet.dao.UserDao;
import com.muet.model.Student;
import com.muet.model.User;
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
public class UserDaoImpl implements UserDao {

    private final Connection con;
    private PreparedStatement pst;
    private ResultSet rst;

    public UserDaoImpl() {
        con = DBConnection.getConnection();
    }

    @Override
    public User login(String email, String password) {
        User user = new User();
        try {
            pst = con.prepareStatement("select * from users where email = ? and password = ?;");
            pst.setString(1, email);
            pst.setString(2, password);
            rst = pst.executeQuery();
            while (rst.next()) {
                user.setUserId(rst.getInt("user_id"));
                user.setFullName(rst.getString("full_name"));
                user.setEmail(rst.getString("email"));
                user.setRole(rst.getString("role"));
                Integer studentId = rst.getInt("student_id");
                if(studentId != null) {
                    StudentDao studentDao = new StudentDaoImpl();
                    Student student = studentDao.getStudentById(studentId);
                    user.setStudent(student);
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    @Override
    public Boolean addStudent(Student student) {
        try {
            pst = con.prepareStatement("insert into users (full_name, email, password, role, student_id) values (?,?,?,?,?);");
            pst.setString(1, student.getFullName());
            pst.setString(2, student.getEmail());
            pst.setString(3, student.getPassword());
            pst.setString(4, "student");
            pst.setInt(5, student.getStudentId());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    @Override
    public Boolean addUser(User user) {
        try {
            pst = con.prepareStatement("insert into users (full_name, email, password, role) values (?,?,?, ?);");
            pst.setString(1, user.getFullName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, "user");
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean updateUser(User user) {
        try {
            pst = con.prepareStatement("update users set full_name = ?, email = ?, password = ? where user_id = ?;");
            pst.setString(1, user.getFullName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setInt(4, user.getUserId());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean deleteUser(Integer id) {
        try {
            pst = con.prepareStatement("delete from users where user_id = ?;");
            pst.setInt(1, id);
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public User getUserById(Integer id) {
        User user = new User();
        try {
            pst = con.prepareStatement("select * from users where user_id = ? and role = 'user'");
            pst.setInt(1, id);
            rst = pst.executeQuery();
            while (rst.next()) {
                user.setFullName(rst.getString("full_name"));
                user.setUserId(rst.getInt("user_id"));
                user.setEmail(rst.getString("email"));
                user.setPassword(rst.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            pst = con.prepareStatement("select * from users where role = 'user';");
            rst = pst.executeQuery();
            while (rst.next()) {
                User user = new User();
                user.setFullName(rst.getString("full_name"));
                user.setUserId(rst.getInt("user_id"));
                user.setEmail(rst.getString("email"));
                user.setPassword(rst.getString("password"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    public Boolean updateStudent(Student student) {
        try {
            pst = con.prepareStatement("update users set full_name = ?, email = ? where student_id = ?;");
            pst.setString(1, student.getFullName());
            pst.setString(2, student.getEmail());
            pst.setInt(3, student.getStudentId());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean changePassword(String password, Integer studentId) {
        try {
            pst = con.prepareStatement("update users set password = ? where student_id = ?;");
            pst.setString(1, password);
            pst.setInt(2, studentId);
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean changeAdminPassword(String password, Integer userId) {
        try {
            pst = con.prepareStatement("update users set password = ? where user_id = ?;");
            pst.setString(1, password);
            pst.setInt(2, userId);
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
