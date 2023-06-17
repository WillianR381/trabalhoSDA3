/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3.eleicao;

import com.mycompany.trabalhosda3.ClienteSocket;
import com.mycompany.trabalhosda3.Processo;
import com.mycompany.trabalhosda3.Processos;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author willian
 */
public class Eleicao {

    private static Eleicao uniqueInstance;

    private Boolean eleicaoIniciada;

    private Eleicao() {
        eleicaoIniciada = false;
    }

    public static synchronized Eleicao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Eleicao();
        }
        return uniqueInstance;
    }

    public void setEleicao(boolean eleicaoIniciada) {
        this.eleicaoIniciada = eleicaoIniciada;
    }

    public void iniciaEleicao() {
        String mensagemOperacao = "iniciaEleicao";
        
         for (Processo processo : Processos.getInstance().getProcessos()) {
            String respostaIdProcesso = this.enviaMensagem(mensagemOperacao, processo.getHost(), processo.getPort());
         }
         
        this.verificaProcessoComIdentificadorMaior();
    }
    
    public void verificaProcessoComIdentificadorMaior(){
        String mensagemOperacao = "verificaProcessoComIdentificadorMaior";
        
        //Identificador do atual processo
        Integer meIdentificador = Processos.getInstance().getMe().getIdentificador();

        for (Processo processo : Processos.getInstance().getProcessos()) {
            if (Objects.equals(meIdentificador, processo.getIdentificador())) {
                continue;
            }

            String respostaIdProcesso = this.enviaMensagem(mensagemOperacao, processo.getHost(), processo.getPort());

            if (respostaIdProcesso == null) {
                continue;
            }

            if (Integer.valueOf(respostaIdProcesso) > meIdentificador) {
                this.manipulaProcessoComIdentificadorMaior(processo);
                return;
            }
        }

        Processo me = Processos.getInstance().getMe();
        Processos.getInstance().setLider(me);

        System.out.println("Resultado Eleicao: " + me.getNome() + " id: " + me.getIdentificador() + " é o lider");
        this.notificaNovoLider();
        this.encerraEleicao();
    } 
    
  
    public void manipulaProcessoComIdentificadorMaior(Processo processo) {
        String mensagemOperacao = "manipulaProcessoComIdentificadorMaior";
        
        try {
            ClienteSocket socket = new ClienteSocket(processo.getHost(), processo.getPort());
            socket.enviar(mensagemOperacao);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void notificaNovoLider() {
        String mensagemOperacao = "novoLider";

        String identificadorLider = String.valueOf(Processos.getInstance().getLider().getIdentificador());
        for (Processo processo : Processos.getInstance().getProcessos()) {

            try {
                ClienteSocket socket = new ClienteSocket(processo.getHost(), processo.getPort());
                socket.enviar(mensagemOperacao);
                // Envia o novo lider
                socket.enviar(identificadorLider);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    public void encerraEleicao() {
        String mensagemOperacao = "encerraEleicao";
        for (Processo processo : Processos.getInstance().getProcessos()) {
            //Notifica a todos que eleicao acabou
            this.enviaMensagem(mensagemOperacao, processo.getHost(), processo.getPort());
        }
    }

    public String enviaMensagem(String mensagemOperacao, String host, Integer porta) {
        try {
            ClienteSocket socket = new ClienteSocket(host, porta);
            socket.enviar(mensagemOperacao);

            String resposta = socket.receber();
            return resposta;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public boolean eleicaoIniciada() {
        return this.eleicaoIniciada;
    }
}
