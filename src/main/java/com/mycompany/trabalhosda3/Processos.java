/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3;

import com.mycompany.trabalhosda3.eleicao.Eleicao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 *
 * @author willian
 */
public class Processos {

    private static Processos uniqueInstance;

    private List<Processo> processos;

    private Processo me;
    private Processo lider;
    private Processo servidor;

    private Integer totalProcesso;

    private Processos() {
        this.processos = new ArrayList<>();
    }

    public static synchronized Processos getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Processos();
        }
        return uniqueInstance;
    }
    
    public void importaArquivoConfiguracao(String porta, String nome, String identificador){
        System.out.println("Estou escutando na porta " + porta);

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
        
        this.setTotalProcesso(totalProcesso);
        
        for (int i = 1; i <= this.getTotalProcesso(); i++) {
            String nomeProcesso = prop.getProperty("app.processo." + i + ".nome");
            Integer  identificadorProcesso = Integer.valueOf( prop.getProperty("app.processo." + i + ".identificador"));
            String hostProcesso = prop.getProperty("app.processo." + i + ".host");
            String portProcesso = prop.getProperty("app.processo." + i + ".port");
           
            Processo processo = new Processo(nomeProcesso, identificadorProcesso, hostProcesso, Integer.valueOf(portProcesso));
            
            if (nome.equals(nomeProcesso)) {
                this.setMe(processo);
            }
            
            if (nomeProcesso.equals("servidor")) {
              this.setServidor(processo);
            }

            this.addProcesso(processo);
        }
    }
    
    
    public void addProcesso(Processo processo) {
        this.processos.add(processo);
    }

    public List<Processo> getProcessos() {
        return this.processos;
    }

    public Processo getMe() {
        return this.me;
    }

    public Processo getServidor() {
        return this.servidor;
    }

    public void setServidor(Processo servidor) {
        this.servidor = servidor;
    }

    public void setMe(Processo me) {
        this.me = me;
    }

    public Processo getLider() {
        return this.lider;
    }

    public void setLider(Processo lider) {
        this.lider = lider;
    }

    public Integer getTotalProcesso() {
        return this.totalProcesso;
    }

    public void setTotalProcesso(Integer totalProcesso) {
        this.totalProcesso = totalProcesso;
    }

    public boolean existeLider() {
        return this.lider != null;
    }

    public Processo pegaProcessoPeloIdentificador(Integer id) {
        for (Processo processo : this.processos) {
            if (processo.getIdentificador().equals(id)) {
                return processo;
            }
        }
        return null;
    }

    public List<Processo> pegaProcessosIdentificadorMaior(Integer id) {
        List<Processo> processosIdMaior = new ArrayList<>();
        for (Processo processo : this.processos) {
            if (processo.getIdentificador() > id) {
                processosIdMaior.add(processo);
            }
        }
        return !processosIdMaior.isEmpty() ? processosIdMaior : null;
    }


    public boolean servidorRodando() {
        try {
            // Verifica se o servidor está rodando 
            ClienteSocket socket = new ClienteSocket(this.servidor.getHost(), this.servidor.getPort());
            socket.enviar("ping");

            String resposta = socket.receber();

            return resposta.equals("ok");
        } catch (IOException ex) {
            return false;
        }
        
    }
}
