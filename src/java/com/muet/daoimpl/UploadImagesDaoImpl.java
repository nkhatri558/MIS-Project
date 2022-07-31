/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.daoimpl;

import com.muet.connection.DBConnection;
import com.muet.dao.UploadImagesDao;
import com.muet.model.UploadImages;
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
public class UploadImagesDaoImpl implements UploadImagesDao {

    private Connection conn;
    private ResultSet rst;
    private PreparedStatement pst;

    @Override
    public Boolean addImages(UploadImages image) {
        //To change body of generated methods, choose Tools | Templates.
        conn = DBConnection.getConnection();

        try {
            pst = conn.prepareStatement("insert into upload_images(link,path,active) values(?,?,?)");
            pst.setString(1, image.getLink());
            pst.setString(2, image.getPath());
            pst.setString(3, "1");
            return pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UploadImagesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<UploadImages> getAllImages() {
        List<UploadImages> upload = new ArrayList<>();
        UploadImages images;
        conn = DBConnection.getConnection();
        try {
            //To change body of generated methods, choose Tools | Templates.

            pst = conn.prepareStatement("select id,link, path from upload_images where active=?");
            pst.setString(1, "1");
            rst = pst.executeQuery();
            while (rst.next()) {
                images = new UploadImages();
                images.setId(rst.getInt("id"));
                images.setLink(rst.getString("link"));
                images.setPath(rst.getString("path"));
                upload.add(images);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UploadImagesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return upload;
    }

    @Override
    public void deleteImages(UploadImages images) {
        //To change body of generated methods, choose Tools | Templates.
        conn = DBConnection.getConnection();

        try {
            pst = conn.prepareStatement("update upload_images set active=? where id=?");
            pst.setString(1, "0");
            pst.setInt(2, images.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UploadImagesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
