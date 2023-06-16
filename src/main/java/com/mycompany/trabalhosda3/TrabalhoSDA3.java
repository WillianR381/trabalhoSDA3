/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.trabalhosda3;

import com.mycompany.trabalhosda3.config.Database;
import com.mycompany.trabalhosda3.processos.Gerente;
import com.mycompany.trabalhosda3.processos.Servidor;
import com.mycompany.trabalhosda3.processos.Vendedor;
import java.io.File;

public class TrabalhoSDA3 {

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Para executar o programa: <tipo> <nome> <identificador> <porta>");
            System.exit(0);
        }

        File arquivoTemporario = new File("banco.db");
        boolean existe = arquivoTemporario.exists();

        if (!existe) {
            System.out.println("Iniciando a criação do banco");
            Database.iniciaBancoDados();
        }

        String tipo = args[0];
        String nome = args[1];
        String identificador = args[2];
        String porta = args[3];
        System.out.println("Olá, eu sou o processo do tipo " + tipo + " com o identificador " + nome);

        switch (tipo) {
            case "vendedor":
                Vendedor vendedor = new Vendedor(porta, nome, identificador);
                vendedor.run();

                break;
            case "gerente":
                Gerente gerente = new Gerente(porta, nome, identificador);
                gerente.run();
                break;
            case "servidor":
                Servidor servidor = new Servidor(porta, nome, identificador);
                servidor.run();
                break;
            default:
                System.out.println("Tipo não válido!");
                break;
        }
    }
}
