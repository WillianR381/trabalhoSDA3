/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3;

import com.mycompany.trabalhosda3.services.VendedorService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteHandler implements Runnable {
    private Socket socket;
    private String identificador;
    
    public ClienteHandler(Socket socket, String identificador){
        this.socket = socket;
        this.identificador = identificador;
}
    
@Override
public void run(){
    try{
        System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        String resposta = in.readLine();
        /* System.out.println("Mensagem recebida: " + resposta);
        out.println("Oi! E eu sou o processo " + this.identificador);*/
        
        if(resposta.equals("venda")){
            String mensagem = "Digite o nome do vendedor, nome do produto, data da venda e o valor da venda seguindo o exemplo abaixo:  Carlos Arroz 2023-06-01 333.30 ";
                  
            out.println(mensagem);
            
            resposta = in.readLine();
            System.out.println(resposta);
            
            String[] variaveis = resposta.split(" ");
            
            String nomeVendedor = variaveis[0];
            String nomeProdutor = variaveis[1];
            String dataVenda = variaveis[2];
            String valorVendaString = variaveis[3];
            
            if(nomeVendedor.equals("") || nomeProdutor.equals("") || dataVenda.equals("") || valorVendaString.equals("") ){
                String ex = "Variável vazia";
                Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, null, ex);
                in.close();
                return ;
            }
            
            VendedorService vendedorService = new VendedorService();
           
            Double valorVenda = Double.valueOf(variaveis[3]);
            
            mensagem = vendedorService.realizaVenda(nomeVendedor, nomeProdutor, dataVenda, valorVenda);
            out.println(mensagem);
           
        }else if(resposta.equals("busca")){
            System.out.println("Busca");
        }
       
        in.close();
    }catch (IOException e){
        e.printStackTrace();
    }catch (Exception e){
        e.printStackTrace();
    }finally{
        try{
            socket.close();
            System.out.println("Conexão encerrada com o cliente: "
                    + socket.getInetAddress().getHostAddress());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
}
