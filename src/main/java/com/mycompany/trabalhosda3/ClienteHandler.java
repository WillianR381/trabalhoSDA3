/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3;

import com.mycompany.trabalhosda3.servicos.ProdutoService;
import com.mycompany.trabalhosda3.servicos.VendedorService;
import com.mycompany.trabalhosda3.utils.Data;
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

    public ClienteHandler(Socket socket, String identificador) {
        this.socket = socket;
        this.identificador = identificador;
    }

    @Override
    public void run() {
        try {
            System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String resposta = in.readLine();

            if (resposta.equals("venda")) {
                String mensagem = "Digite <nomeVendedor> <nomeProduto> <dataVenda> <valorVenda> exemplo: Carlos Arroz 01-06-2023- 333.30 ";
                out.println(mensagem);

                resposta = in.readLine();

                /* if(resposta == null) {
                    out.println("Parâmetro vázio");
                    in.close();
                } */
                System.out.println("Venda recebida: " + resposta);

                String[] variaveisEntrada = resposta.split(" ");

                String nomeVendedor = variaveisEntrada[0];
                String nomeProdutor = variaveisEntrada[1];
                String dataVenda = variaveisEntrada[2];
                String valorVendaString = variaveisEntrada[3];

                if (nomeVendedor.equals("") || nomeProdutor.equals("") || dataVenda.equals("") || valorVendaString.equals("")) {
                    String ex = "Variável vazia";
                    Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, null, ex);
                    in.close();
                    return;
                }
                
                VendedorService vendedorService = new VendedorService();

                Double valorVenda = Double.valueOf(variaveisEntrada[3]);
                dataVenda = Data.formataParaAnoMesDia(dataVenda);
                
                mensagem = vendedorService.realizaVenda(nomeVendedor, nomeProdutor, dataVenda, valorVenda);
                out.println(mensagem);

            } else if (resposta.equals("busca")) {
                String mensagem = "Digite: 1 <nomeVendedor> - Buscar o total de vendas de um vendedor ex: 1 Carlos "
                                + "Digite: 2 <nomeProduto> - Buscar o total de vendas de um produto ex: 2 Arroz  "
                                + "Digite: 3 <dataInicial> <dataFinal> - Buscar o total de vendas dos produtos por periodo ex: 3 02-02-2023 05-05-2023"
                                + "Digite: 4 - Buscar o vendedor que mais realizou vendas ex: 4  "
                                + "Digite: 5 - Buscar o produto que foi mais vendido ex: 5";

                out.println(mensagem);

                resposta = in.readLine();
                System.out.println("Busca recebida: " + resposta);

                String[] variaveisEntrada = resposta.split(" ");
                
                String operacao = variaveisEntrada[0];
                switch (operacao) {
                    case "1":
                        VendedorService vendedorService = new VendedorService();
                        String nomeVendedor = variaveisEntrada[1];
                        mensagem = vendedorService.totalVendas(nomeVendedor);
                        break;
                    case "2":
                        ProdutoService produtoService = new ProdutoService();
                        String nomeProduto = variaveisEntrada[1];
                        mensagem = produtoService.totalVendas(nomeProduto);
                        break;
                    case "3":
                        String dataInicial = variaveisEntrada[1];
                        String dataFinal = variaveisEntrada[2];
                        
                        ProdutoService produtoService1 = new ProdutoService();
                        dataInicial = Data.formataParaAnoMesDia(dataInicial);
                        dataFinal = Data.formataParaAnoMesDia(dataFinal);
                        mensagem = produtoService1.totalVendasPorPeriodo(dataInicial, dataFinal);
                        break;
                    case "4":
                        VendedorService vendedorService1 = new VendedorService();
                        mensagem = vendedorService1.melhorVendedor();
                        break;
                    case "5":
                        ProdutoService produtoService2 = new ProdutoService();
                        mensagem = produtoService2.melhorProduto();
                        break;
                    default:
                        mensagem = "Operação inválida";
                }
                out.println(mensagem);
            } 
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("Conexão encerrada com o cliente: "
                        + socket.getInetAddress().getHostAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
