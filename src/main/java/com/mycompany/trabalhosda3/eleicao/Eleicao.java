/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3.eleicao;

import com.mycompany.trabalhosda3.ClienteSocket;
import com.mycompany.trabalhosda3.Processo;
import com.mycompany.trabalhosda3.Processos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author willian
 */
public class Eleicao {
    private static Eleicao uniqueInstance;
    
    private Boolean eleicaoIniciada;
    
    private Eleicao(){
        eleicaoIniciada = false;
    }
    
    public static synchronized Eleicao getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new Eleicao();
        }
        return uniqueInstance;
    }
    
    public void setEleicao(boolean eleicaoIniciada){
        this.eleicaoIniciada = eleicaoIniciada;
    }
    

    
    public void iniciaEleicao(){
        this.setEleicao(true);
        
        String mensagemOperacao = "iniciaEleicao";
        
        Integer meIdentificador = Processos.getInstance().getMe().getIdentificador();
        
        List<Processo> processosComIdentificadorMaior = new ArrayList<>();
        
        for (Processo processo : Processos.getInstance().getProcessos()) {
            if(Objects.equals(meIdentificador, processo.getIdentificador())) continue;
            
            
            String respostaIdProcesso = Eleicao.getInstance().enviaMensagem(mensagemOperacao, processo.getHost(), processo.getPort());
            
            if(respostaIdProcesso == null) continue ; 
            
            System.out.println("Resposta: "+ respostaIdProcesso);

            if(Integer.valueOf(respostaIdProcesso) > meIdentificador){
               processosComIdentificadorMaior.add(processo);
            }
        }
        
        if(processosComIdentificadorMaior.isEmpty()){
            Processo me = Processos.getInstance().getMe();
            Processos.getInstance().setLider(me);
                
            System.out.println("Resultado Eleicao: " + me.getNome() + " id: " + me.getIdentificador() + " é o lider" );
            this.encerraEleicao();
        }
        
        
        //Fazer o processo de quando não acha nenhum lider ele asssume, assim
        //atribuindo em processo me como lider e mandando msg para os outros processos que eu sou o lider
        //No outros processos colocará em Processos quem é o lider e encerrar a eleição
        //Criar ping no servidor para ser se tá conectado 
        
    }
    
    public void encerraEleicao(){    
        String mensagemOperacao = "encerraEleicao";
         
        Integer meIdentificador = Processos.getInstance().getMe().getIdentificador();
        
         for (Processo processo : Processos.getInstance().getProcessos()) {
            if(Objects.equals(meIdentificador, processo.getIdentificador())) continue;
            
             try{
                ClienteSocket socket = new ClienteSocket(processo.getHost(), processo.getPort());
                socket.enviar(mensagemOperacao);
                
                String identificadorLider = String.valueOf( Processos.getInstance().getLider().getIdentificador());
                socket.enviar(identificadorLider);
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }
           
        }
        
         
        this.setEleicao(false);
    }
    
    public String enviaMensagem(String mensagemOperacao, String host, Integer porta  ){
         try{
                ClienteSocket socket = new ClienteSocket(host, porta);
                socket.enviar(mensagemOperacao);
                
                String resposta = socket.receber();
                return  resposta;
            }catch(IOException ex){
                System.out.println(ex.getMessage());
                return null;
            }
    }
    

    public void notificaNovoLider(){
        
    }
    public boolean eleicaoIniciada(){
        return this.eleicaoIniciada;
    }
}

