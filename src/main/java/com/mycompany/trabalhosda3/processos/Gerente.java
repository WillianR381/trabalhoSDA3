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
        mensagemOperacao = "busca";
    }

    public void run() {
        this.iniciarConexao();
        
        while (true) {
            try {
                try {
                    String host = null;
                    Integer porta = null;
                    
                    Boolean servidorRodando = Processos.getInstance().servidorRodando();
                    
                    if(! servidorRodando){
                        
                        System.out.println("Servidor não encontrado !");
                        //Verifica se existe lider para utilizá-lo
                        if(Processos.getInstance().existeLider()){
                            Processo lider = Processos.getInstance().getLider();
                            host = lider.getHost();
                            porta = lider.getPort();
                            System.out.println("Utilizando o servidor temporario! No qual é " + lider.getNome() + " com identificador " + lider.getIdentificador());
                        }else{
                            // Inicia eleição se não estiver um rodando
                            if(! Eleicao.getInstance().eleicaoIniciada()){
                                System.out.println("Eleição Iniciada ");
                                Eleicao.getInstance().iniciaEleicao();
                                
                                continue;
                            }
                        }
                    }else {
                        System.out.println("Servidor Rodando ");
                        Processo servidor = Processos.getInstance().getServidor();
                        host = servidor.getHost();
                        porta = servidor.getPort();
                        
                        Processos.getInstance().setLider(null);
                    }
                    
                    if( host == null || porta == null){
                        throw  new Exception("Porta ou servidor com valor null");
                    }
                    
                    ClienteSocket socket = new ClienteSocket(host, porta);
                    Scanner scan = new Scanner(System.in);

                    socket.enviar(mensagemOperacao);

                    String resposta = socket.receber();
                    Impressao.noTerminal(resposta);

                    String mensagem = scan.nextLine();
                    socket.enviar(mensagem);

                    resposta = socket.receber();
                    Impressao.noTerminal(resposta);

                } catch (IOException ex) {
                    //Processo servidor = Processos.getInstance().getServidor();
                    //Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "Erro na conexão com " + servidor.getIdentificador() + ": " + ex.getMessage());
                    
                    /*if(! Eleicao.getInstance().eleicaoIniciada()){
                        Eleicao.getInstance().iniciaEleicao();
                    }*/
                }catch( Exception e){
                    System.out.println(e.getMessage());
                }

                System.out.println("Vou dormir!");
                Thread.sleep(1000 * 3);
            } catch (InterruptedException ex) {
               
                Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "ThreadSleep", ex);
            }
        }
    }
}
