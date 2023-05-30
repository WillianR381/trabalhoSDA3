/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3.processos;

import com.mycompany.trabalhosda3.ClienteSocket;
import com.mycompany.trabalhosda3.Tipo;
import com.mycompany.trabalhosda3.TrabalhoSDA3;
import com.mycompany.trabalhosda3.utils.Impressao;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Vendedor extends Tipo {
    public Vendedor(String porta, String nome){
        super(porta, nome);
    }


    public void run(){
        while(true){
           try{
                try{
                       ClienteSocket socket = new ClienteSocket(servidor.getHost(), servidor.getPort());
                       Scanner scan = new Scanner(System.in);
                       
                       socket.enviar("venda");
                       
                       String resposta = socket.receber();
                       Impressao.noTerminal(resposta);
                       
                       String mensagem = scan.nextLine();
                       socket.enviar(mensagem);
                       
                       resposta = socket.receber();
                       Impressao.noTerminal(resposta);
                                              
                   } catch(IOException ex){
                       Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "Erro na conexão com " + servidor.getIdentificador() + ": " + ex.getMessage());
                   }
               System.out.println("Vou dormir!");
               Thread.sleep(1000*10);
            }catch(InterruptedException ex){
                Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "ThreadSleep", ex);
            }
        }
    }
}