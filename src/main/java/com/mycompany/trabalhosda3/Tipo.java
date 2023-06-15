/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Tipo {

    protected String porta;
    protected String nome;
    protected String identificador;
    protected ServidorSocket serverSocket;


    public Tipo(String porta, String nome, String identificador) {
        this.porta = porta;
        this.nome = nome;
        this.identificador = identificador;

        System.out.println("Estou escutando na porta " + this.porta);

        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("app.config")) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            System.err.println("Arquivo de configuração não encontrado");
            System.exit(0);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(0);
        }

        Integer totalProcesso = Integer.valueOf(prop.getProperty("app.processo.total"));
        
        Processos processos = Processos.getInstance();
        processos.setTotalProcesso(totalProcesso);
        
        for (int i = 1; i <= totalProcesso; i++) {
            String nomeProcesso = prop.getProperty("app.processo." + i + ".nome");
            Integer  identificadorProcesso = Integer.valueOf( prop.getProperty("app.processo." + i + ".identificador"));
            String hostProcesso = prop.getProperty("app.processo." + i + ".host");
            String portProcesso = prop.getProperty("app.processo." + i + ".port");
           
            Processo processo = new Processo(nomeProcesso, identificadorProcesso, hostProcesso, Integer.valueOf(portProcesso));
            
            if (this.nome.equals(nomeProcesso)) {
                processos.setMe(processo);
            }
            
            if (nomeProcesso.equals("servidor")) {
              processos.setServidor(processo);
            }

            processos.addProcesso(processo);
        }

    }

    //Possibilita receber mensagens 
    protected void iniciarConexao() {
        try {
            serverSocket = new ServidorSocket(Integer.valueOf(this.porta), this.identificador, this.nome);
            Thread thread = new Thread(serverSocket);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void comunicaOutrosProcesso() {
        for (Processo processo : Processos.getInstance().getProcessos()) {
            try {
                ClienteSocket socket = new ClienteSocket(processo.getHost(), processo.getPort());
                socket.enviar("mudarServidor");
                socket.enviar("Olá, eu sou o processo consumidor " + this.nome);
                String resposta = socket.receber();
                System.out.println("Resposta: " + resposta);
            } catch (IOException ex) {
                Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "Erro na conexão com " + processo.getIdentificador() + ": " + ex.getMessage());
            }
        }
    }*/

    public abstract void run();

}
