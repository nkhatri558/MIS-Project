/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.dao;

import com.muet.model.Student;
import com.muet.model.User;
import java.util.List;

/**
 *
 * @author khatr
 */
public interface UserDao {
    public User login(String email, String password);
    
    /**
     *
     * @param user
     * @return 
     */
    
    public Boolean addUser(User user);
    
    /**
     *
     * @param user
     * @return 
     */
    
    public Boolean updateUser(User user);
    
    /**
     * @param id
     * @return 
     */
    
    public Boolean deleteUser(Integer id);

    /**
     *
     * @param id
     * @return 
     */

    public User getUserById(Integer id);
    
    /**
     *
     * @return List of Faculty
     */
    public List<User> getUsers();
    
    public Boolean addStudent(Student student);
    public Boolean updateStudent(Student student);
    public Boolean changePassword(String password, Integer studentId);
    public Boolean changeAdminPassword(String password, Integer userId);
}
