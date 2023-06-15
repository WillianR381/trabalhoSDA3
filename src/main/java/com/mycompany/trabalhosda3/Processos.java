/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3;

import com.mycompany.trabalhosda3.eleicao.Eleicao;
import java.util.ArrayList;
import java.util.List;
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
    
    private Processos(){
        this.processos = new ArrayList<>();
    }
    
    public static synchronized Processos getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new Processos();
        }
        return uniqueInstance;
    }
    
    public void addProcesso(Processo processo){
        this.processos.add(processo);
    }
    
    public List<Processo> getProcessos(){
        return this.processos;
    }
    
    public Processo getMe(){
        return this.me;
    }
    
    public Processo getServidor(){
        return this.servidor;
    }
    
    public void setServidor(Processo servidor){
        this.servidor = servidor;
    }
    
    public void setMe(Processo me){
        this.me = me;
    }
    
    public Processo getLider(){
        return this.lider;
    }
    
    public void setLider(Processo lider){
        this.lider = lider;
    }
    
    public Integer getTotalProcesso(){
        return this.totalProcesso;
    }
    
    public void setTotalProcesso(Integer totalProcesso){
        this.totalProcesso = totalProcesso;
    }
    
    public boolean existeLider(){
        return this.lider == null;
    }
    
    public Processo pegaProcessoPeloIdentificador(Integer id){
        for(Processo processo : this.processos){
            if(processo.getIdentificador().equals(id)){
                return processo;
            }
        }
        return null;
    }
    
    public List<Processo> pegaProcessosIdentificadorMaior(Integer id){
        List<Processo> processosIdMaior = new ArrayList<>();
        for(Processo processo : this.processos){
            if(processo.getIdentificador() > id){
                processosIdMaior.add(processo);
            }
        }
       return  ! processosIdMaior.isEmpty() ? processosIdMaior : null;
    }
    /*
    public Processo getRandomProcesso(){
        Random rand = new Random();
        int index = rand.nextInt(processos.size());
        index = (index == 0) ? processos.size() : index;
        return processos.get(index);
    }*/
    
    
    public static void main(String[] args) {
     
    }
}
