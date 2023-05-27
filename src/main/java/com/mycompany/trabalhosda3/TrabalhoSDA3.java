/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.trabalhosda3;

/**
 *
 * @author willian
 */
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
        
        if (tipo.equals("produtor")){
            Produtor p = new Produtor(porta, nome);
            p.run();
        }else if(tipo.equals("consumidor")){
            Consumidor c = new Consumidor(porta, nome);
            c.run();
        } else {
            System.out.println("Tipo não válido!");
        }
    }
}
