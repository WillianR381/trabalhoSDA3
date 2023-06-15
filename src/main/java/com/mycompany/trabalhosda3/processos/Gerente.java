/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3.processos;

import com.mycompany.trabalhosda3.ClienteSocket;
import com.mycompany.trabalhosda3.Processo;
import com.mycompany.trabalhosda3.Processos;
import com.mycompany.trabalhosda3.Tipo;
import com.mycompany.trabalhosda3.TrabalhoSDA3;
import com.mycompany.trabalhosda3.eleicao.Eleicao;
import com.mycompany.trabalhosda3.utils.Impressao;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gerente extends Tipo {

    public Gerente(String porta, String nome, String identificador) {
        super(porta, nome, identificador);
    }

    public void run() {
        this.iniciarConexao();
        
        while (true) {
            try {
                try {
                    Processo servidor = Processos.getInstance().getServidor();
                    ClienteSocket socket = new ClienteSocket(servidor.getHost(), servidor.getPort());
                    Scanner scan = new Scanner(System.in);

                    socket.enviar("busca");

                    String resposta = socket.receber();
                    Impressao.noTerminal(resposta);

                    String mensagem = scan.nextLine();
                    socket.enviar(mensagem);

                    resposta = socket.receber();
                    Impressao.noTerminal(resposta);

                } catch (IOException ex) {
                    //Processo servidor = Processos.getInstance().getServidor();
                    //Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "Erro na conexão com " + servidor.getIdentificador() + ": " + ex.getMessage());
                    
                    if(! Eleicao.getInstance().eleicaoIniciada()){
                        Eleicao.getInstance().iniciaEleicao();
                    }
                }

                System.out.println("Vou dormir!");
                Thread.sleep(1000 * 3);
            } catch (InterruptedException ex) {
               
                Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "ThreadSleep", ex);
            }
        }
    }
}
