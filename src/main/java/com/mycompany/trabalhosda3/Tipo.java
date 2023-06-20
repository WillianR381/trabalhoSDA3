/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3;

import java.io.IOException;

public abstract class Tipo {

    protected String porta;
    protected String nome;
    protected String identificador;
    protected ServidorSocket serverSocket;
    
    protected String mensagemOperacao; 

    //Ao instância cria os processo (Gerente, Vendedor ou Servidor)
    public Tipo(String porta, String nome, String identificador) {
        this.porta = porta;
        this.nome = nome;
        this.identificador = identificador;

        Processos.getInstance().importaArquivoConfiguracao(porta, nome, identificador);
    }

    //Inicia um servidorSocket, possibilitando receber mensagens ou servidor/servidorTemporário
    protected void iniciarConexao() {
        try {
            serverSocket = new ServidorSocket(Integer.valueOf(this.porta), this.identificador, this.nome);
            Thread thread = new Thread(serverSocket);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public abstract void run();
    
}
