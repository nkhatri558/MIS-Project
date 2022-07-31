/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.dao;

import com.muet.model.Student;
import com.muet.model.StudentAcademicInformationBoard;
import com.muet.model.StudentAcademicInformationGraduate;
import java.util.List;

/**
 *
 * @author 92310
 */
public interface StudentDao {
    public Integer addStudent(Student student);
    
    /**
     *
     * @param student 
     * @return 
     */
    
    public Boolean updateStudent(Student student);
    
    /**
     * @param id
     * @return 
     */
    
    public Boolean deleteStudent(Integer id );

    /**
     *
     * @param id
     * @return 
     */

    public Student getStudentById(Integer id);
    
    /**
     *
     * @return List of Faculty
     */
    public List<Student> getStudents();
    /**
     *
     * @param studentName
     * @return 
     */

    public Student getStudentByStudentName(String studentName);
    
    public Boolean registerStudent(Student student);
    public Boolean addMatriculationInfo(StudentAcademicInformationBoard academicInformationBoard);
    public Boolean addIntermediateInfo(StudentAcademicInformationBoard academicInformationBoard);
    public Boolean addGraduateInfo(StudentAcademicInformationGraduate academicInformationGraduate);
    public StudentAcademicInformationBoard getStudentAcademicBoardInfo(Integer studentId, String academicType);
    public StudentAcademicInformationGraduate getStudentAcademicGraduateInfo(Integer studentId, String graduateType);
    public Student getStudentByRollNo(String rollNo);
}
