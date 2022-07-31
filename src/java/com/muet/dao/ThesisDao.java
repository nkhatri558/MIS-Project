/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.dao;

import com.muet.model.Batch;
import com.muet.model.Thesis;
import java.util.List;

/**
 *
 * @author 92310
 */
public interface ThesisDao {
    public Boolean  addThesis(Thesis thesis);
    public List<Thesis> getAllThesis();
    public Boolean updateThesis(Thesis thesis);
    public void delete(Integer id);
    public List<Thesis> getThesisDataById(Integer id);
     public Thesis getThesisRecordByBatchId(Integer id);
}
