/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.dao;

import com.muet.model.ThesisDocuments;
import java.util.List;

/**
 *
 * @author khatr
 */
public interface ThesisDocumentsDao {
    public Boolean addThesisDocuments(ThesisDocuments thesisDocuments);
    public Boolean updateThesisDocuments(ThesisDocuments thesisDocuments);
    public Boolean DeleteThesisDocuments(Integer id);
    public ThesisDocuments getThesisDocumentsById(Integer id);
    public List<ThesisDocuments> getThesisDocuments();
}
