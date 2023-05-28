/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.trabalhosda3;

import com.mycompany.trabalhosda3.entities.Gerente;
import com.mycompany.trabalhosda3.entities.Servidor;
import com.mycompany.trabalhosda3.entities.Vendedor;

public class TrabalhoSDA3 {

      public static void main(String[] args) {
        if(args.length != 3){
            System.out.println("Para executar o programa: MultiPrograma <tipo> <identificador> <porta>");
            System.exit(0);
        }
        
        String tipo = args[0];
        String nome = args[1];
        String porta = args[2];
        System.out.println("Olá, eu sou o programa do tipo " + tipo + " com o identificador " + nome);
        
        if (tipo.equals("vendedor")){
            Vendedor vendedor = new Vendedor(porta, nome);
            vendedor.run();
        }else if(tipo.equals("gerente")){
            Gerente gerente = new Gerente(porta, nome);
            gerente.run();
        }else if(tipo.equals("servidor")){
            Servidor servidor = new Servidor(porta,nome);
            servidor.run();
        }else {
            System.out.println("Tipo não válido!");
        }
    }
}
