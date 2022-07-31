/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.controller;

import com.google.gson.Gson;
import com.muet.dao.DepartmentDao;
import com.muet.dao.StudentDao;
import com.muet.dao.UserDao;
import com.muet.daoimpl.DepartmentDaoImpl;
import com.muet.daoimpl.StudentDaoImpl;
import com.muet.daoimpl.UserDaoImpl;
import com.muet.model.Department;
import com.muet.model.Student;
import com.muet.model.StudentAcademicInformationBoard;
import com.muet.model.StudentAcademicInformationGraduate;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

/**
 *
 * @author 92310
 */
@MultipartConfig
@WebServlet(name = "StudentController", urlPatterns = {"/StudentController"})
public class StudentController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Integer studentId = 0;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        System.out.println("----------------> Action : " + action);
        if (action.equals("add")) {
            addData(request, response);
        } else if (action.equals("view")) {
            viewData(request, response);
        } else if (action.equals("delete")) {
            deleteData(request, response);
        } else if (action.equals("getStudentRecord")) {
            getStudentRecord(request, response);
        } else if (action.equals("getStudentProfileRecord")) {
            getStudentProfileRecord(request, response);
        } else if (action.equals("update")) {
            updateData(request, response);
        } else if (action.equals("registerStudent")) {
            registerStudent(request, response);
        } else if (action.equals("addMatriculationInfo")) {
            addMatriculationInfo(request, response);
        } else if (action.equals("addIntermediateInfo")) {
            addIntermediateInfo(request, response);
        } else if (action.equals("addGraduateInfo")) {
            addGraduateInfo(request, response);
        } else if (action.equals("getMatriculationInfo")) {
            getMatriculationInfo(request, response);
        } else if (action.equals("getIntermediateInfo")) {
            getIntermediateInfo(request, response);
        } else if (action.equals("getGraduateInfo")) {
            getGraduateInfo(request, response);
        } else if (action.equals("addStudentData")) {
            uploadStudentData(request, response);
        }

    }

    private void viewData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Student student = new Student();
        PrintWriter pw = response.getWriter();
        StudentDao studentDao = new StudentDaoImpl();
        List<Student> std = studentDao.getStudents();
        Gson gson = new Gson();
        pw.write(gson.toJson(std));
    }

    private void addData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Student student = new Student();
        PrintWriter pw = response.getWriter();
        student.setFullName(request.getParameter("studentName"));
        student.setRollNumber(request.getParameter("studentRollNo"));
        pw.println(student.getFullName());
        StudentDao studentDao = (StudentDao) new StudentDaoImpl();
        studentDao.addStudent(student);

    }

    private void getStudentRecord(HttpServletRequest request, HttpServletResponse response) throws Exception {
        StudentDao studentDao = new StudentDaoImpl();
        PrintWriter pw = response.getWriter();
        int id = Integer.parseInt(request.getParameter("studentId"));
        studentId = id;
        Student student = studentDao.getStudentById(id);
        Gson gson = new Gson();
        pw.write(gson.toJson(student));
    }

    private void updateData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Student student = new Student();
        StudentDao studentDao = new StudentDaoImpl();
        //Integer id = Integer.parseInt(request.getParameter("studentId"));
        student.setStudentId(studentId);
        student.setAddress(request.getParameter("address"));
        student.setAdmissionDate(request.getParameter("admissionDate"));
        student.setBatch(request.getParameter("batch"));
        student.setBloodGroup(request.getParameter("bloodGroup"));
        student.setCountryOfBirth(request.getParameter("countryOfBirth"));
        student.setCurrentAddress(request.getParameter("currentAddress"));
        student.setDob(request.getParameter("dob"));
        student.setDomicile(request.getParameter("domicile"));
        student.setEmail(request.getParameter("email"));
        student.setFathersName(request.getParameter("fathersName"));
        student.setFieldProgram(request.getParameter("fieldProgram"));
        student.setFullName(request.getParameter("fullName"));
        student.setLegalId(request.getParameter("legalId"));
        student.setLegalIdNo(request.getParameter("legalIdNo"));
        student.setMobile(request.getParameter("mobile"));
        student.setNationality(request.getParameter("nationality"));
        student.setPlaceOfIssueOfLegalId(request.getParameter("placeOfIssueOfLegalId"));
        student.setProgram(request.getParameter("program"));
        student.setReligion(request.getParameter("religion"));
        student.setRollNumber(request.getParameter("rollNumber"));
        student.setSemester(Integer.parseInt(request.getParameter("semester")));
        student.setShift(request.getParameter("shift"));
        student.setTiming(request.getParameter("timing"));
        //Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));
        String departmentName = request.getParameter("departmentName");
        DepartmentDao departmentDao = new DepartmentDaoImpl();
        Department department = departmentDao.getDepartmentByName(departmentName);
//        DepartmentDao departmentDao = new DepartmentDaoImpl();
//        department.setDepartmentId(departmentId);
//        department.setDepartmentName(departmentName);
        departmentDao.updateDepartmentOnly(department);
        student.setDepartment(department);
        studentDao.updateStudent(student);
    }

    private void deleteData(HttpServletRequest request, HttpServletResponse response) {
        //To change body of generated methods, choose Tools | Templates.
        StudentDao studentDao = new StudentDaoImpl();
        Integer id = Integer.parseInt(request.getParameter("studentId"));
        studentDao.deleteStudent(id);
    }

    private void getStudentProfileRecord(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
        StudentDao studentDao = new StudentDaoImpl();
        PrintWriter pw = response.getWriter();
        Integer id = Integer.parseInt(request.getParameter("id"));
        studentId = id;
        Student student = studentDao.getStudentById(id);
        Gson gson = new Gson();
        pw.write(gson.toJson(student));

    }

    private void registerStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        StudentDao studentDao = new StudentDaoImpl();
        Integer id = Integer.parseInt(request.getParameter("studentId"));
        Student student = studentDao.getStudentById(id);
        student.setAddress(request.getParameter("address"));
        student.setAdmissionDate(request.getParameter("admissionDate"));
        student.setBatch(request.getParameter("batch"));
        student.setBloodGroup(request.getParameter("bloodGroup"));
        student.setCountryOfBirth(request.getParameter("countryOfBirth"));
        student.setCurrentAddress(request.getParameter("currentAddress"));
        student.setDob(request.getParameter("dob"));
        student.setDomicile(request.getParameter("domicile"));
        //student.setEmail(request.getParameter("email"));
        student.setFathersName(request.getParameter("fatherName"));
        student.setFieldProgram(request.getParameter("fieldProgram"));
        //student.setFullName(request.getParameter("fullName"));
        student.setLegalId(request.getParameter("legalId"));
        student.setLegalIdNo(request.getParameter("legalIdNo"));
        student.setMobile(request.getParameter("mobileNumber"));
        student.setNationality(request.getParameter("nationality"));
        student.setPlaceOfIssueOfLegalId(request.getParameter("placeOfIssueOfLegalId"));
        student.setDateOfIssueOfLegalId(request.getParameter("dateOfIssueOfLegalId"));
        student.setProgram(request.getParameter("program"));
        student.setReligion(request.getParameter("religion"));
        //student.setRollNumber(request.getParameter("rollNumber"));
        //student.setSemester(Integer.parseInt(request.getParameter("semesterStudying")));
        student.setSemester(1);
        student.setShift(request.getParameter("shift"));
        student.setTiming(request.getParameter("timing"));
        String departmentName = request.getParameter("departmentName");
        DepartmentDao departmentDao = new DepartmentDaoImpl();
        Department department = departmentDao.getDepartmentByName(departmentName);
        student.setDepartment(department);
        Part part = request.getPart("profileImage");
        String fileName = part.getSubmittedFileName();
        PrintWriter out = response.getWriter();
        out.println(fileName);
        System.out.println("----------> File Name: " + fileName);
        // Uploading profile image
        InputStream is = part.getInputStream();
        byte[] data = new byte[is.available()];
        is.read(data);
        String path = "uploads" + File.separator + "profileImages" + File.separator + student.getRollNumber().trim() + "_profileImage." + fileName.split("\\.")[1];
        FileOutputStream fos = new FileOutputStream(request.getRealPath("/") + path);
        fos.write(data);
        fos.close();
        student.setProfileImage(path);
        student.setRegistrationStatus("registered");
        studentDao.registerStudent(student);
        UserDao userDao = new UserDaoImpl();
        userDao.updateStudent(student);
    }

    private void addMatriculationInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        StudentAcademicInformationBoard academicInformationBoard = new StudentAcademicInformationBoard();
        StudentDao studentDao = new StudentDaoImpl();
        Student student = studentDao.getStudentById(Integer.parseInt(request.getParameter("studentId")));
        academicInformationBoard.setStudent(student);
        academicInformationBoard.setPassingYear(request.getParameter("matriculationPassingYear"));
        academicInformationBoard.setBoard(request.getParameter("matriculationBoard"));
//        academicInformationBoard.setMarks(Double.parseDouble(request.getParameter("matriculationPercentage")));
        academicInformationBoard.setMarks(90.0);
        academicInformationBoard.setSubject(request.getParameter("matriculationSubject"));
        academicInformationBoard.setAcademicType("matriculation");
        // Uploading Marksheet
        Part marksheetPart = request.getPart("matriculationMarksheet");
        InputStream marksheetIS = marksheetPart.getInputStream();
        byte[] marksheetData = new byte[marksheetIS.available()];
        marksheetIS.read(marksheetData);
        String marksheetPath = "uploads" + File.separator + "academicDocuments" + File.separator + "matriculation" + File.separator + student.getRollNumber().trim() + "_marksheetImage." + marksheetPart.getSubmittedFileName().split("\\.")[1];
        FileOutputStream fos1 = new FileOutputStream(request.getRealPath("/") + marksheetPath);
        fos1.write(marksheetData);
        fos1.close();
        academicInformationBoard.setMarksheetImage(marksheetPath);
        // Uploading Certificate
        Part certificatePart = request.getPart("matriculationCertficate");
        InputStream certificateIS = certificatePart.getInputStream();
        byte[] certificateData = new byte[certificateIS.available()];
        certificateIS.read(certificateData);
        String certificatePath = "uploads" + File.separator + "academicDocuments" + File.separator + "matriculation" + File.separator + student.getRollNumber().trim() + "_certificateImage." + certificatePart.getSubmittedFileName().split("\\.")[1];
        FileOutputStream fos2 = new FileOutputStream(request.getRealPath("/") + certificatePath);
        fos2.write(certificateData);
        fos2.close();
        academicInformationBoard.setCertificateImage(certificatePath);

        studentDao.addMatriculationInfo(academicInformationBoard);
    }

    private void addIntermediateInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        StudentAcademicInformationBoard academicInformationBoard = new StudentAcademicInformationBoard();
        StudentDao studentDao = new StudentDaoImpl();
        Student student = studentDao.getStudentById(Integer.parseInt(request.getParameter("studentId")));
        academicInformationBoard.setStudent(student);
        academicInformationBoard.setPassingYear(request.getParameter("intermediatePassingYear"));
        academicInformationBoard.setBoard(request.getParameter("intermediateBoard"));
//        academicInformationBoard.setMarks(Double.parseDouble(request.getParameter("intermediatePercentage")));
        academicInformationBoard.setMarks(90.0);
        academicInformationBoard.setSubject(request.getParameter("intermediateSubject"));
        academicInformationBoard.setAcademicType("intermediate");
        // Uploading Marksheet
        Part marksheetPart = request.getPart("intermediateMarksheet");
        InputStream marksheetIS = marksheetPart.getInputStream();
        byte[] marksheetData = new byte[marksheetIS.available()];
        marksheetIS.read(marksheetData);
        String marksheetPath = "uploads" + File.separator + "academicDocuments" + File.separator + "intermediate" + File.separator + student.getRollNumber().trim() + "_marksheetImage." + marksheetPart.getSubmittedFileName().split("\\.")[1];
        FileOutputStream fos1 = new FileOutputStream(request.getRealPath("/") + marksheetPath);
        fos1.write(marksheetData);
        fos1.close();
        academicInformationBoard.setMarksheetImage(marksheetPath);
        // Uploading Certificate
        Part certificatePart = request.getPart("intermediateCertificate");
        InputStream certificateIS = certificatePart.getInputStream();
        byte[] certificateData = new byte[certificateIS.available()];
        certificateIS.read(certificateData);
        String certificatePath = "uploads" + File.separator + "academicDocuments" + File.separator + "intermediate" + File.separator + student.getRollNumber() + "_certificateImage." + certificatePart.getSubmittedFileName().split("\\.")[1];
        FileOutputStream fos2 = new FileOutputStream(request.getRealPath("/") + certificatePath);
        fos2.write(certificateData);
        fos2.close();
        academicInformationBoard.setCertificateImage(certificatePath);

        studentDao.addIntermediateInfo(academicInformationBoard);
    }

    private void addGraduateInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        StudentAcademicInformationGraduate academicInformationGraduate = new StudentAcademicInformationGraduate();
        StudentDao studentDao = new StudentDaoImpl();
        Student student = studentDao.getStudentById(Integer.parseInt(request.getParameter("studentId")));
        academicInformationGraduate.setStudent(student);
        //academicInformationGraduate.setPassingYear(request.getParameter("graduatePassingYear"));
        academicInformationGraduate.setPassingYear("2020");
        academicInformationGraduate.setUniversity(request.getParameter("graduateUniversity"));
        //academicInformationGraduate.setCgpa(Double.parseDouble(request.getParameter("graduateCgpa")));
        academicInformationGraduate.setCgpa(90.0);
        academicInformationGraduate.setDegreeProgram(request.getParameter("graduateDegreeProgram"));
        academicInformationGraduate.setGraduateType("masters");
        // Uploading Marksheet
        Part marksheetPart;
        marksheetPart = request.getPart("graduateMarksheet");
        InputStream marksheetIS = marksheetPart.getInputStream();
        byte[] marksheetData = new byte[marksheetIS.available()];
        marksheetIS.read(marksheetData);
        String marksheetPath = "uploads" + File.separator + "academicDocuments" + File.separator + "graduate" + File.separator + student.getRollNumber().trim() + "_marksheetImage." + marksheetPart.getSubmittedFileName().split("\\.")[1];
        FileOutputStream fos1 = new FileOutputStream(request.getRealPath("/") + marksheetPath);
        fos1.write(marksheetData);
        fos1.close();
        academicInformationGraduate.setMarksheetImage(marksheetPath);
        // Uploading Certificate
        Part certificatePart = request.getPart("graduateCertificate");
        InputStream certificateIS = certificatePart.getInputStream();
        byte[] certificateData = new byte[certificateIS.available()];
        certificateIS.read(certificateData);
        String certificatePath = "uploads" + File.separator + "academicDocuments" + File.separator + "graduate" + File.separator + student.getRollNumber().trim() + "_certificateImage." + certificatePart.getSubmittedFileName().split("\\.")[1];
        FileOutputStream fos2 = new FileOutputStream(request.getRealPath("/") + certificatePath);
        fos2.write(certificateData);
        fos2.close();
        academicInformationGraduate.setCertificateImage(certificatePath);

        studentDao.addGraduateInfo(academicInformationGraduate);
    }

    private void getMatriculationInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StudentDao studentDao = new StudentDaoImpl();
        PrintWriter pw = response.getWriter();
        Integer id = Integer.parseInt(request.getParameter("id"));
        StudentAcademicInformationBoard academicInformationIntermediate = studentDao.getStudentAcademicBoardInfo(id, "intermediate");
        Gson gson = new Gson();
        pw.write(gson.toJson(academicInformationIntermediate));
    }

    private void getIntermediateInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StudentDao studentDao = new StudentDaoImpl();
        PrintWriter pw = response.getWriter();
        Integer id = Integer.parseInt(request.getParameter("id"));
        StudentAcademicInformationBoard academicInformationMatriculation = studentDao.getStudentAcademicBoardInfo(id, "matriculation");
        Gson gson = new Gson();
        pw.write(gson.toJson(academicInformationMatriculation));
    }

    private void getGraduateInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StudentDao studentDao = new StudentDaoImpl();
        PrintWriter pw = response.getWriter();
        Integer id = Integer.parseInt(request.getParameter("id"));
        StudentAcademicInformationGraduate academicInformationGraduate = studentDao.getStudentAcademicGraduateInfo(id, "masters");
        System.out.println("Image Path: ---" + academicInformationGraduate.getCertificateImage());
        Gson gson = new Gson();
        pw.write(gson.toJson(academicInformationGraduate));
    }

    private void uploadStudentData(HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException, Exception {
        //To change body of generated methods, choose Tools | Templates.
        response.setContentType("application/html");
        File file = null;
        String randomPassword="";
        Random random=new Random();
        Student student = new Student();
        StudentDao dao=new StudentDaoImpl();
        PrintWriter pw = response.getWriter();
        String line = "";
        StringTokenizer st = null;
        int lineNumber = 0;
        int tokenNumber = 0;
        List<List> list2=new ArrayList<>();
        Part dataUpload = request.getPart("uploadStudentData");
        InputStream data = dataUpload.getInputStream();
        byte[] imageData = new byte[data.available()];
        data.read(imageData);
        String fileName = dataUpload.getSubmittedFileName();
        String extension = dataUpload.getContentType();
        String link = "uploads" + File.separator + "studentData" + File.separator + fileName;
        FileOutputStream fos1 = new FileOutputStream(request.getRealPath("/") + link);
        String fileStorePath = getServletContext().getRealPath("/") + link;
        PrintWriter out = response.getWriter();
        fos1.write(imageData);
        ArrayList<String> ar = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(fileStorePath));
        while ((line = br.readLine()) != null) {
            lineNumber++;  
            List list=new ArrayList();
            st = new StringTokenizer(line, ",");
            while (st.hasMoreTokens()) {
                
                tokenNumber++;
                String sd = st.nextToken() + "  ";
                if (sd != null) {
                    ar.add(sd);
                    list.add(sd);
                }  
            } 
            System.out.println();
            list2.add(list);
            tokenNumber = 0;
        }
        for(int i=1; i<list2.size(); i++){
            
            for(int j=0; j<list2.get(i).size(); j++){
                System.out.println(list2.get(i).get(j));
                
            }
            for(int k=0; k<8; k++){
                int ch=random.nextInt(9);
                randomPassword+=ch;
                
            }
            
            student.setRollNumber(list2.get(i).get(0)+"");
            student.setFullName(list2.get(i).get(1)+"");
            student.setGender(list2.get(i).get(2)+"");
            student.setEmail(list2.get(i).get(3)+"");
            DepartmentDao departmentDao = new DepartmentDaoImpl();
            Department department=departmentDao.getDepartmentByName(list2.get(i).get(4)+"");
            student.setBatch(list2.get(i).get(5)+"");
            //department.setDepartmentName(list2.get(i).get(4)+"");
            student.setDepartment(department);
            student.setRegistrationStatus("unregistered");
            student.setPassword(randomPassword);
            System.out.println(student.getRollNumber() + ", " + student.getFullName() + ", " + student.getGender() + ", " + student.getDepartment().getDepartmentName());
            dao.addStudent(student);
        }
        fos1.close();

    }

}
