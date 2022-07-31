/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.dao;

import com.muet.model.ThesisDetails;
import java.util.List;

/**
 *
 * @author khatr
 */
public interface ThesisDetailsDao {
    public Boolean addThesis(ThesisDetails thesis);
    public Boolean updateThesis(ThesisDetails thesis);
    public Boolean DeleteThesis(Integer id);
    public ThesisDetails getThesisById(Integer id);
    public List<ThesisDetails> getThesisDetails();
}
