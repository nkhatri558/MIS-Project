/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.dao;

import com.muet.model.Batch;
import com.muet.model.StudentThesis;
import com.muet.model.Thesis;
import java.util.List;

/**
 *
 * @author 92310
 */
public interface StudentThesisDao {
    public Boolean addStudentThesis(StudentThesis thesis);
    public List<StudentThesis> getAllStudentThesis(Integer id);
    public Boolean updateStudentThesis(StudentThesis thesis);
    public List<Batch> getAllBatches();
    public StudentThesis getStudentThesisByStudentId(Integer id);
}
