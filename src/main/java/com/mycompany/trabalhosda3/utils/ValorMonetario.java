/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3.utils;

public class ValorMonetario {
    
    public static String formatar(Double valor){
        return "R$ " + String.format("%.2f", valor);
    }
}
