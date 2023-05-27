/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
        System.out.println("Mensagem recebida: " + resposta);
        out.println("Oi! E eu sou o processo " + this.identificador);
        in.close();
    }catch (IOException e){
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
