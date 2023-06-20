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

public class Vendedor extends Tipo {

    public Vendedor(String porta, String nome, String identificador) {
        super(porta, nome, identificador);
        mensagemOperacao = "venda";
    }

    public void run() {
        this.iniciarConexao();
        while (true) {
            try {
                try {
                    //Manda um requisição ao servidor retornando um booleano se ele respondeu
                    Boolean servidorRodando = Processos.getInstance().servidorRodando();

                    if (servidorRodando) {
                        try {
                            //Quando acessa ao servidor remove o lider 
                            Processos.getInstance().setLider(null);

                            Processo servidor = Processos.getInstance().getServidor();
                            System.out.println("Servidor rodando");

                            String host = servidor.getHost();
                            Integer porta = servidor.getPort();

                            if (host == null || porta == null) {
                                throw new Exception("Erro - Porta ou servidor com valor null");
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
                            Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "Servidor não respondeu", ex);
                        }
                    } else {
                        /*
                         * Caso o servidor principal não esteja funcionando utilizará o temporário ou inicia eleição
                         */
                        System.out.println("Servidor não encontrado !");
                        //Verifica se existe um lider para utilizá-lo como servidor temporário
                        if (Processos.getInstance().existeLider()) {
                            /*
                            * Utiliza o lider como servidor temporario 
                             */
                            try {
                                Processo lider = Processos.getInstance().getLider();
                                String host = lider.getHost();
                                Integer porta = lider.getPort();
                                System.out.println("Utilizando o servidor temporario! " + lider.getNome() + " com identificador " + lider.getIdentificador());

                                if (host == null || porta == null) {
                                    throw new Exception("Erro - Porta ou servidor com valor null");
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
                                Processos.getInstance().setLider(null);
                            }
                        } else {
                            /* 
                            * Inicia eleição 
                             */
                            if (!Eleicao.getInstance().eleicaoIniciada()) {
                                Eleicao.getInstance().iniciaEleicao();

                                continue;
                            }
                        }
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Em espera!");
                Thread.sleep(1000 * 5);
            } catch (InterruptedException ex) {
                Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "ThreadSleep", ex);
            }
        }
    }
}
