/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3.entities;

import com.mycompany.trabalhosda3.ClienteSocket;
import com.mycompany.trabalhosda3.Processo;
import com.mycompany.trabalhosda3.Tipo;
import com.mycompany.trabalhosda3.TrabalhoSDA3;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gerente extends Tipo {
    public Gerente(String porta, String nome){
        super(porta, nome);
    }
    
    public void run(){
        while(true){
           try{
                try{
                       ClienteSocket socket = new ClienteSocket(servidor.getHost(), servidor.getPort());
                       socket.enviar("Olá, eu sou o processo Gerente " + this.nome);
                       String resposta = socket.receber();
                       System.out.println("Resposta: " + resposta);
                   } catch(IOException ex){
                      Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "Erro na conexão com " + servidor.getIdentificador() + ": " + ex.getMessage());
                   }
               
              /* for(Processo processo : processos){
                   try{
                       ClienteSocket socket = new ClienteSocket(processo.getHost(), processo.getPort());
                       socket.enviar("Olá, eu sou o processo consumidor " + this.nome);
                       String resposta = socket.receber();
                       System.out.println("Resposta: " + resposta);
                   } catch(IOException ex){
                       Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "Erro na conexão com " + processo.getIdentificador() + ": " + ex.getMessage());
                   }
               }*/
               
               System.out.println("Vou dormir!");
               Thread.sleep(1000*15);
               System.out.println("Acordei");
               Thread.sleep(1000*15);
            }catch(InterruptedException ex){
                Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "ThreadSleep", ex);
            }
        }
    }   
}
