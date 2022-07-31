/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.daoimpl;

import com.muet.connection.DBConnection;
import com.muet.dao.BatchDao;
import com.muet.dao.DepartmentDao;
import com.muet.dao.StudentDao;
import com.muet.dao.UserDao;
import com.muet.model.Batch;
import com.muet.model.Department;
import com.muet.model.Student;
import com.muet.model.StudentAcademicInformationBoard;
import com.muet.model.StudentAcademicInformationGraduate;
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
 * @author 92310
 */
public class StudentDaoImpl implements StudentDao {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rst = null;
    ResultSet rst2 = null;
    int t = 0;

    @Override
    public Integer addStudent(Student student) {
        //To change body of generated methods, choose Tools | Templates.
        con = DBConnection.getConnection();
        UserDao userDao=new UserDaoImpl();
        User user=new User();
        Integer deptId=0;
        Integer stdId=0;
        DepartmentDao departmentDao = new DepartmentDaoImpl();
        deptId = departmentDao.getDepartmentByName(student.getDepartment().getDepartmentName()).getDepartmentId();
        try {
//            pst=con.prepareStatement("select department_id from department where department_name=?");
//            pst.setString(1,student.getDepartment().getDepartmentName());
//            ResultSet rst=pst.executeQuery();
//            while(rst.next()){
//                deptId=rst.getInt(1);
//            }
            
            pst = con.prepareStatement("insert into student(full_name,roll_number,gender,email,department_id,registration_status, batch) values(?,?,?,?,?,?,?)");
            pst.setString(1, student.getFullName());
            pst.setString(2, student.getRollNumber());
            pst.setString(3,student.getGender());
            pst.setString(4,student.getEmail());
            pst.setInt(5,deptId);
            pst.setString(6,student.getRegistrationStatus());
            BatchDao batchDao = new BatchDaoImpl();
            System.out.println(batchDao.getBatchByBatchName(student.getBatch()));
            if(batchDao.getBatchByBatchName(student.getBatch())== null) {
                Batch batch = new Batch();
                batch.setBatchName(student.getBatch());
                batchDao.addBatch(batch);
            }
            pst.setString(7, student.getBatch());
            t = pst.executeUpdate();
            pst=con.prepareStatement("select student_id from student where roll_number=?");
            pst.setString(1,student.getRollNumber());
            rst=pst.executeQuery();
            while(rst.next()){
                stdId=rst.getInt(1);
            }
            student.setStudentId(stdId);
            userDao.addStudent(student);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return t;
    }

    @Override
    public Boolean updateStudent(Student student) {
        //To change body of generated methods, choose Tools | Templates.
        con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("update student set full_name=?,roll_number=?,fathers_name=?,legal_id=?,legal_id_no=?,nationality=?,place_of_issue_of_legal_id=?,date_of_issue_of_legal_id=?,mobile=?,dob=?,email=?,"
                    + "domicile=?,country_of_birth=?,blood_group=?,religion=?,address=?,current_address=?,semester=?,batch=?,admission_date=?,program=?,field_program=?,shift=?,timing=? where student_id=?");
            pst.setString(1, student.getFullName());
            pst.setString(2, student.getRollNumber());
            pst.setString(3, student.getFathersName());
            pst.setString(4, student.getLegalId());
            pst.setString(5, student.getLegalIdNo());
            pst.setString(6, student.getNationality());
            pst.setString(7, student.getPlaceOfIssueOfLegalId());
            pst.setString(8, student.getDateOfIssueOfLegalId());
            pst.setString(9, student.getMobile());
            pst.setString(10, student.getDob());
            pst.setString(11, student.getEmail());
            pst.setString(12, student.getDomicile());
            pst.setString(13, student.getCountryOfBirth());
            pst.setString(14, student.getBloodGroup());
            pst.setString(15, student.getReligion());
            pst.setString(16, student.getAddress());
            pst.setString(17, student.getCurrentAddress());
            pst.setInt(18, student.getSemester());
            pst.setString(19, student.getBatch());
            pst.setString(20, student.getAdmissionDate());
            pst.setString(21, student.getProgram());
            pst.setString(22, student.getFieldProgram());
            pst.setString(23, student.getShift());
            pst.setString(24, student.getTiming());
            pst.setInt(25, student.getStudentId());

            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean deleteStudent(Integer id) {
        //To change body of generated methods, choose Tools | Templates.
        con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("delete from student where student_id = ?");
            pst.setInt(1, id);
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Student getStudentById(Integer id) {
        //To change body of generated methods, choose Tools | Templates.
        con = DBConnection.getConnection();
//         List<Student> std = new ArrayList<>();

        Student student = new Student();

        try {
            pst = con.prepareStatement("select * from student where student_id =?");
            pst.setInt(1, id);
            rst = pst.executeQuery();
            while (rst.next()) {
                student.setStudentId(rst.getInt("student_id"));
                student.setFullName(rst.getString("full_name"));
                student.setRollNumber(rst.getString("roll_number"));
                student.setFathersName(rst.getString("fathers_name"));
                student.setLegalId(rst.getString("legal_id"));
                student.setLegalIdNo(rst.getString("legal_id_no"));
                student.setNationality(rst.getString("nationality"));
                student.setPlaceOfIssueOfLegalId(rst.getString("place_of_issue_of_legal_id"));
                student.setDateOfIssueOfLegalId(rst.getString("date_of_issue_of_legal_id"));
                student.setMobile(rst.getString("mobile"));
                student.setDob(rst.getString("dob"));
                student.setEmail(rst.getString("email"));
                student.setDomicile(rst.getString("domicile"));
                student.setCountryOfBirth(rst.getString("country_of_birth"));
                student.setBloodGroup(rst.getString("blood_group"));
                student.setReligion(rst.getString("religion"));
                student.setAddress(rst.getString("address"));
                student.setCurrentAddress(rst.getString("current_address"));
                student.setSemester(rst.getInt("semester"));
                DepartmentDao departmentDao = new DepartmentDaoImpl();
                Department department = departmentDao.getDepartmentById(rst.getInt("department_id"));
                student.setDepartment(department);
                //  student.setDepartmentId(rst.getInt("department_id"));
                student.setBatch(rst.getString("batch"));
                student.setAdmissionDate(rst.getString("admission_date"));
                // student.setSupervisorId(rst.getInt("supervisor_id"));
                student.setProgram(rst.getString("program"));
                student.setFieldProgram(rst.getString("field_program"));
                student.setShift(rst.getString("shift"));
                student.setTiming(rst.getString("timing"));
                student.setProfileImage(rst.getString("profile_image"));
                student.setRegistrationStatus(rst.getString("registration_status"));
                //student.setSupervisor(rst.getString("supervisor"));

//                std.add(student);  
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;

    }

    @Override
    public List<Student> getStudents() {
        //To change body of generated methods, choose Tools | Templates.
        con = DBConnection.getConnection();
        List<Student> std = new ArrayList<>();

        try {
            pst = con.prepareStatement("select * from student");
            rst = pst.executeQuery();
            while (rst.next()) {
                Student student = new Student();
                student.setStudentId(rst.getInt("student_id"));
                student.setFullName(rst.getString("full_name"));
                student.setRollNumber(rst.getString("roll_number"));
                student.setRegistrationStatus(rst.getString("registration_status"));
                std.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return std;
    }

    @Override
    public Student getStudentByStudentName(String studentName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean registerStudent(Student student) {
        con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("update student set full_name=?,roll_number=?,fathers_name=?,legal_id=?,legal_id_no=?,nationality=?,place_of_issue_of_legal_id=?,date_of_issue_of_legal_id=?,mobile=?,dob=?,email=?,"
                    + "domicile=?,country_of_birth=?,blood_group=?,religion=?,address=?,current_address=?,semester=?,batch=?,admission_date=?,program=?,field_program=?,shift=?,timing=?, profile_image=?, registration_status=?, supervisor=? where student_id=?");
            pst.setString(1, student.getFullName());
            pst.setString(2, student.getRollNumber());
            pst.setString(3, student.getFathersName());
            pst.setString(4, student.getLegalId());
            pst.setString(5, student.getLegalIdNo());
            pst.setString(6, student.getNationality());
            pst.setString(7, student.getPlaceOfIssueOfLegalId());
            pst.setString(8, student.getDateOfIssueOfLegalId());
            pst.setString(9, student.getMobile());
            pst.setString(10, student.getDob());
            pst.setString(11, student.getEmail());
            pst.setString(12, student.getDomicile());
            pst.setString(13, student.getCountryOfBirth());
            pst.setString(14, student.getBloodGroup());
            pst.setString(15, student.getReligion());
            pst.setString(16, student.getAddress());
            pst.setString(17, student.getCurrentAddress());
            pst.setInt(18, student.getSemester());
            pst.setString(19, student.getBatch());
            pst.setString(20, student.getAdmissionDate());
            pst.setString(21, student.getProgram());
            pst.setString(22, student.getFieldProgram());
            pst.setString(23, student.getShift());
            pst.setString(24, student.getTiming());
            pst.setString(25, student.getProfileImage());
            pst.setString(26, "registered");
            pst.setString(27, student.getSupervisor());
            pst.setInt(28, student.getStudentId());

            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean addMatriculationInfo(StudentAcademicInformationBoard academicInformationBoard) {
        con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("insert into academic_information_board (student_id, passing_year, board, marks, subject, academic_type, marksheet_image, certificate_image) values (?,?,?,?,?,?,?,?)");
            pst.setInt(1, academicInformationBoard.getStudent().getStudentId());
            pst.setString(2, academicInformationBoard.getPassingYear());
            pst.setString(3, academicInformationBoard.getBoard());
            pst.setDouble(4, academicInformationBoard.getMarks());
            pst.setString(5, academicInformationBoard.getSubject());
            pst.setString(6, academicInformationBoard.getAcademicType());
            pst.setString(7, academicInformationBoard.getMarksheetImage());
            pst.setString(8, academicInformationBoard.getCertificateImage());
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean addIntermediateInfo(StudentAcademicInformationBoard academicInformationBoard) {
        con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("insert into academic_information_board (student_id, passing_year, board, marks, subject, academic_type, marksheet_image, certificate_image) values (?,?,?,?,?,?,?,?)");
            pst.setInt(1, academicInformationBoard.getStudent().getStudentId());
            pst.setString(2, academicInformationBoard.getPassingYear());
            pst.setString(3, academicInformationBoard.getBoard());
            pst.setDouble(4, academicInformationBoard.getMarks());
            pst.setString(5, academicInformationBoard.getSubject());
            pst.setString(6, academicInformationBoard.getAcademicType());
            pst.setString(7, academicInformationBoard.getMarksheetImage());
            pst.setString(8, academicInformationBoard.getCertificateImage());
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean addGraduateInfo(StudentAcademicInformationGraduate academicInformationGraduate) {
        con = DBConnection.getConnection();
        try {
            pst = con.prepareStatement("insert into academic_information_graduate (student_id, passing_year, university, cgpa, degree_program, graduate_type, marksheet_image, certificate_image) values (?,?,?,?,?,?,?,?)");
            pst.setInt(1, academicInformationGraduate.getStudent().getStudentId());
            pst.setString(2, academicInformationGraduate.getPassingYear());
            pst.setString(3, academicInformationGraduate.getUniversity());
            pst.setDouble(4, academicInformationGraduate.getCgpa());
            pst.setString(5, academicInformationGraduate.getDegreeProgram());
            pst.setString(6, academicInformationGraduate.getGraduateType());
            pst.setString(7, academicInformationGraduate.getMarksheetImage());
            pst.setString(8, academicInformationGraduate.getCertificateImage());
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public StudentAcademicInformationBoard getStudentAcademicBoardInfo(Integer studentId, String academicType) {
        StudentAcademicInformationBoard academicInformationBoard = new StudentAcademicInformationBoard();
        Connection con1 = DBConnection.getConnection();
        try {
            PreparedStatement pst1 = con1.prepareStatement("select academic_information_board_id ,student_id, passing_year, board, marks, subject, academic_type, marksheet_image, certificate_image from academic_information_board where student_id = ? and academic_type = ?;");
            pst1.setInt(1, studentId);
            pst1.setString(2, academicType);
            ResultSet rst1 = pst1.executeQuery();
            while (rst1.next()) {
                academicInformationBoard.setAcademicInformationBoardId(rst1.getInt("academic_information_board_id"));
                Student student = getStudentById(rst1.getInt("student_id"));
                academicInformationBoard.setStudent(student);
                //System.out.println("----?"+rst.getInt("passing_year"));
                academicInformationBoard.setPassingYear(rst1.getString("passing_year"));
                academicInformationBoard.setBoard(rst1.getString("board"));
                academicInformationBoard.setMarks(rst1.getDouble("marks"));
                academicInformationBoard.setAcademicType(rst1.getString("academic_type"));
                academicInformationBoard.setSubject(rst1.getString("subject"));
                academicInformationBoard.setMarksheetImage(rst1.getString("marksheet_image"));
                academicInformationBoard.setCertificateImage(rst1.getString("certificate_image"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return academicInformationBoard;
    }

    @Override
    public StudentAcademicInformationGraduate getStudentAcademicGraduateInfo(Integer studentId, String graduateType) {
        StudentAcademicInformationGraduate academicInformationGraduate = new StudentAcademicInformationGraduate();
        Connection con1 = DBConnection.getConnection();
        try {
            PreparedStatement pst1 = con1.prepareStatement("select academic_information_graduate_id, student_id, passing_year, university, cgpa, degree_program, graduate_type, marksheet_image, certificate_image from academic_information_graduate where student_id = ? and graduate_type = ?;");
            pst1.setInt(1, studentId);
            pst1.setString(2, graduateType);
            ResultSet rst1 = pst1.executeQuery();
            while (rst1.next()) {
                academicInformationGraduate.setAcademicInformationGraduateId(rst1.getInt("academic_information_graduate_id"));
                Student student = getStudentById(rst1.getInt("student_id"));
                academicInformationGraduate.setStudent(student);
                //academicInformationGraduate.setPassingYear(rst.getString(3));
                academicInformationGraduate.setUniversity(rst1.getString("university"));
                academicInformationGraduate.setCgpa(rst1.getDouble("cgpa"));
                academicInformationGraduate.setGraduateType(rst1.getString("graduate_type"));
                academicInformationGraduate.setDegreeProgram(rst1.getString("degree_program"));
                academicInformationGraduate.setMarksheetImage(rst1.getString("marksheet_image"));
                academicInformationGraduate.setCertificateImage(rst1.getString("certificate_image"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return academicInformationGraduate;
    }

    @Override
    public Student getStudentByRollNo(String rollNo) {
        con = DBConnection.getConnection();
//         List<Student> std = new ArrayList<>();

        Student student = new Student();

        try {
            pst = con.prepareStatement("select * from student where roll_number =?");
            pst.setString(1, rollNo);
            rst = pst.executeQuery();
            while (rst.next()) {
                student.setStudentId(rst.getInt("student_id"));
                student.setFullName(rst.getString("full_name"));
                student.setRollNumber(rst.getString("roll_number"));
                student.setFathersName(rst.getString("fathers_name"));
                student.setLegalId(rst.getString("legal_id"));
                student.setLegalIdNo(rst.getString("legal_id_no"));
                student.setNationality(rst.getString("nationality"));
                student.setPlaceOfIssueOfLegalId(rst.getString("place_of_issue_of_legal_id"));
                student.setDateOfIssueOfLegalId(rst.getString("date_of_issue_of_legal_id"));
                student.setMobile(rst.getString("mobile"));
                student.setDob(rst.getString("dob"));
                student.setEmail(rst.getString("email"));
                student.setDomicile(rst.getString("domicile"));
                student.setCountryOfBirth(rst.getString("country_of_birth"));
                student.setBloodGroup(rst.getString("blood_group"));
                student.setReligion(rst.getString("religion"));
                student.setAddress(rst.getString("address"));
                student.setCurrentAddress(rst.getString("current_address"));
                student.setSemester(rst.getInt("semester"));
                DepartmentDao departmentDao = new DepartmentDaoImpl();
                Department department = departmentDao.getDepartmentById(rst.getInt("department_id"));
                student.setDepartment(department);
                //  student.setDepartmentId(rst.getInt("department_id"));
                student.setBatch(rst.getString("batch"));
                student.setAdmissionDate(rst.getString("admission_date"));
                // student.setSupervisorId(rst.getInt("supervisor_id"));
                student.setProgram(rst.getString("program"));
                student.setFieldProgram(rst.getString("field_program"));
                student.setShift(rst.getString("shift"));
                student.setTiming(rst.getString("timing"));
                student.setProfileImage(rst.getString("profile_image"));
                student.setRegistrationStatus(rst.getString("registration_status"));
                student.setSupervisor(rst.getString("supervisor"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }

}
