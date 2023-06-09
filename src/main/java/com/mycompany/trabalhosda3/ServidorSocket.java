/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket implements Runnable {
    
    private ServerSocket serverSocket;
    private String identificador;
    private String nome;
    
    public ServidorSocket(Integer port, String identificador, String nome) throws IOException {
        serverSocket = new ServerSocket(port);
        this.identificador = identificador;
        this.nome = nome;
        System.out.println("Servidor iniciado na porta " + port + ". Aguardando conexões...");
    }
    
    /**
     * Fica escutando até um clientSocket conecte-se ao servidor e atribuir a uma nova thread 
     * o ClientHandler trocará mensagem para fazer as operações desejada pelo cliente,
     * o uso de Thread possíbilita multiplas conexões ao servidor
     */
    @Override
    public void run(){
        try {
            while(true){
                Socket socket = serverSocket.accept();
                
                Thread thread = new Thread(new ClienteHandler(socket, this.identificador, this.nome));
               
                thread.start();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
    

