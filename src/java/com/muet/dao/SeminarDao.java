/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.dao;

import com.muet.model.Seminar;
import java.util.List;

/**
 *
 * @author 92310
 */
public interface SeminarDao {
    public Boolean addSeminar(Seminar seminar);
    
    /**
     *
     * @param seminar
     * @return 
     */
    
    public Boolean updateSeminar(Seminar seminar);
    
    /**
     * @param id
     * @return 
     */
    
    public Boolean deleteSeminar(Integer id);

    /**
     *
     * @param id
     * @return 
     */

    public Seminar getSeminarById(Integer id);
    
    /**
     *
     * @return List of Seminar
     */
    public List<Seminar> getSeminars();
    /**
     *
     * @param seminarTitle
     * @return 
     */

    public Seminar getSeminarBySeminarName(String seminarTitle);
}
