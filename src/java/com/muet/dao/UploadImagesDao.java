/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.dao;

import com.muet.model.UploadImages;
import java.util.List;

/**
 *
 * @author 92310
 */
public interface UploadImagesDao {
    public Boolean addImages(UploadImages image);
    public List<UploadImages> getAllImages();
    public void deleteImages(UploadImages images);
}
