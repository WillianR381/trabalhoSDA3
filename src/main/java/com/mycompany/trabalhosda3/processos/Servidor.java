/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3.processos;

import com.mycompany.trabalhosda3.Tipo;

public class Servidor extends Tipo {
    public Servidor(String porta, String nome, String identificador){
        super(porta, nome, identificador);
    }
    
    @Override
    public void run(){
        this.iniciarConexao();
    } 
}
