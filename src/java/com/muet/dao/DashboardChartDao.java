/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.dao;

import com.muet.model.DashboardChart;
import java.util.List;

/**
 *
 * @author 92310
 */
public interface DashboardChartDao {
    public List<DashboardChart> getRegistrationRecords();
    public List<DashboardChart> getSeminarRecords();
    public List<DashboardChart> getFeesRecords();
    public List<DashboardChart> getGenderRecords();
    
}
