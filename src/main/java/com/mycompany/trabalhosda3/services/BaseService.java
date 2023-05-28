/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3.services;

import com.mycompany.trabalhosda3.config.Database;
import java.sql.Connection;

/**
 *
 * @author willian
 */
public abstract class BaseService {
 protected Connection connection = null; 
 
    public BaseService() {
         this.connection = Database.getConnection(); 
    }
    
    
}
