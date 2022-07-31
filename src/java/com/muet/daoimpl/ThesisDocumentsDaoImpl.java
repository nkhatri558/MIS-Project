/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.daoimpl;

import com.muet.connection.DBConnection;
import com.muet.dao.ThesisDocumentsDao;
import com.muet.model.ThesisDocuments;
import com.muet.model.ThesisDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.muet.dao.ThesisDetailsDao;

/**
 *
 * @author khatr
 */
public class ThesisDocumentsDaoImpl implements ThesisDocumentsDao{
    private final Connection con;
    private PreparedStatement pst;
    private ResultSet rst;
    public ThesisDocumentsDaoImpl() {
        con = DBConnection.getConnection();
    }
    @Override
    public Boolean addThesisDocuments(ThesisDocuments thesisDocuments) {
        try {
            System.out.println("Id is "+thesisDocuments.getStudentThesis().getThesisDetailsId());
            pst = con.prepareStatement("insert into thesis_documents (thesis_document_title, thesis_details_id,thesis_document) values (?,?,?);");
            pst.setString(1, thesisDocuments.getThesisDocumentTitle());
            pst.setInt(2, thesisDocuments.getStudentThesis().getThesisDetailsId());
            pst.setString(3,thesisDocuments.getThesisDocument());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ThesisDocumentsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean updateThesisDocuments(ThesisDocuments thesisDocuments) {
        try {
            pst = con.prepareStatement("update thesis_documents set thesis_document_title = ?, thesis_group_id = ? where thesis_document_id = ?;");
            pst.setString(1, thesisDocuments.getThesisDocumentTitle());
            pst.setInt(2, thesisDocuments.getStudentThesis().getThesisDetailsId());
            pst.setInt(3, thesisDocuments.getThesisDocumentId());
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ThesisDocumentsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean DeleteThesisDocuments(Integer id) {
        try {
            pst = con.prepareStatement("delete from thesis_documents where thesis_document_id = ?;");
            pst.setInt(1, id);
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ThesisDocumentsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ThesisDocuments getThesisDocumentsById(Integer id) {
        ThesisDocuments thesisDocuments = new ThesisDocuments();
        try {
            pst = con.prepareStatement("select * from thesis_documents where thesis_document_id = ?");
            pst.setInt(1, id);
            rst = pst.executeQuery();
            while(rst.next()) {
                thesisDocuments.setThesisDocumentId(rst.getInt("thesis_document_id"));
                thesisDocuments.setThesisDocumentTitle(rst.getString("thesis_document_title"));
                ThesisDetailsDao thesisGroupDao = new ThesisDetailsDaoImpl();
                ThesisDetails thesisGroup = thesisGroupDao.getThesisById(rst.getInt("thesis_group_id"));
                thesisDocuments.setThesis(thesisGroup);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThesisDocumentsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thesisDocuments;
    }

    @Override
    public List<ThesisDocuments> getThesisDocuments() {
        List<ThesisDocuments> thesisDocumentsList = new ArrayList<>();
        try {
            pst = con.prepareStatement("select * from thesis_documents;");
            rst = pst.executeQuery();
            while(rst.next()) {
                ThesisDocuments thesisDocuments = new ThesisDocuments();
                thesisDocuments.setThesisDocumentId(rst.getInt("thesis_document_id"));
                thesisDocuments.setThesisDocumentTitle(rst.getString("thesis_document_title"));
                ThesisDetailsDao thesisGroupDao = new ThesisDetailsDaoImpl();
                ThesisDetails thesisGroup = thesisGroupDao.getThesisById(rst.getInt("thesis_group_id"));
                thesisDocuments.setThesis(thesisGroup);
                thesisDocumentsList.add(thesisDocuments);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThesisDocumentsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thesisDocumentsList;
    }
    
}
