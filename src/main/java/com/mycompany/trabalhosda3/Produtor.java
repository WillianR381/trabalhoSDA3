/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1271919682
 */
public class Produtor extends Tipo {
    public Produtor(String porta, String nome){
        super(porta, nome);
    }


    public void run(){
        while(true){
           try{
               for(Processo processo : processos){
                   try{
                       ClienteSocket socket = new ClienteSocket(processo.getHost(), processo.getPort());
                       socket.enviar("Olá, eu sou o processo produtor " + this.nome);
                       String resposta = socket.receber();
                       System.out.println("Resposta: " + resposta);
                   } catch(IOException ex){
                       Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "Erro na conexão com " + processo.getIdentificador() + ": " + ex.getMessage());
                   }
               }
               
               System.out.println("Vou dormir!");
               Thread.sleep(1000*60);
               System.out.println("Acordei");
               Thread.sleep(1000*60);
            }catch(InterruptedException ex){
                Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "ThreadSleep", ex);
            }
        }
    }
}