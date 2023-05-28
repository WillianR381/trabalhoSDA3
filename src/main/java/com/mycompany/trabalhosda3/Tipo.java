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

public abstract class Tipo {
    protected String porta;
    protected String nome;
    protected List<Processo> processos;
    protected ServidorSocket serverSocket;
    protected Processo servidor = null;
    
    protected void iniciarConexao(){
        try{
            serverSocket = new ServidorSocket(Integer.valueOf(this.porta), this.nome);
            Thread thread = new Thread(serverSocket);
            thread.start();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public Tipo(String porta, String nome){
        this.porta = porta;
        this.nome = nome;
        System.out.println("Estou escutando na porta " + this.porta);
        
        Properties prop = new Properties();
        try(FileInputStream fis = new FileInputStream("app.config")){
            prop.load(fis);
        }catch(FileNotFoundException ex){
            System.err.println("Arquivo de configuração não encontrado");
            System.exit(0);
        }catch(IOException ex){
            ex.printStackTrace();
            System.exit(0);
        }
        
        this.iniciarConexao();
        
        System.out.println(prop.getProperty("app.name"));
        
        Integer totalProcesso = Integer.valueOf(prop.getProperty("app.processo.total"));
        processos = new ArrayList<>();
        for(int i = 1; i <= totalProcesso; i++){
            String identificador = prop.getProperty("app.processo." + i + ".identificador");
            String host = prop.getProperty("app.processo." + i + ".host");
            String port = prop.getProperty("app.processo." + i + ".port");
            
            if(identificador.equals("") || host.equals("") || port.equals("")) continue;
            
            if(this.nome.equals(identificador)) continue;
              
            Processo processo = new Processo(identificador, host, Integer.valueOf(port));
            
            if(identificador.equals("servidor") && this.servidor == null){
                this.servidor = processo;
            }
            
            processos.add(processo);
        }
        
    }
    
    public abstract void run();
    
}
