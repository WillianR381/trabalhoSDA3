/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3.utils;

/**
 *
 * @author willian
 */
public class Impressao {
    
    
    public static void noTerminal(String stringParaImprimir){
        if(stringParaImprimir == null) return;
        String[] linhas = stringParaImprimir.split("<novaLinha>");
        for(String linha : linhas){
            System.out.println(linha);
        }
    }
}
