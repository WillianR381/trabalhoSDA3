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

public class Servidor extends Tipo {
    public Servidor(String porta, String nome){
        super(porta, nome);
    }
    
    public void run(){
       /* while(true){
           try{
              for(Processo processo : processos){
                   try{
                       ClienteSocket socket = new ClienteSocket(processo.getHost(), processo.getPort());
                       socket.enviar("Olá, eu sou o processo Servidor " + this.nome);
                       String resposta = socket.receber();
                       System.out.println("Resposta: " + resposta);
                   } catch(IOException ex){
                       Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "Erro na conexão com " + servidor.getIdentificador() + ": " + ex.getMessage());
                   }
               }
               
               System.out.println("Vou dormir!");
               Thread.sleep(1000*15);
               System.out.println("Acordei");
               Thread.sleep(1000*15);
            }catch(InterruptedException ex){
                Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "ThreadSleep", ex);
            }
        }
    */
        System.out.println("Rodando");
    } 
}
