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

public class ClienteHandler implements Runnable {

    private Socket socket;
    private String identificador;

    public ClienteHandler(Socket socket, String identificador) {
        this.socket = socket;
        this.identificador = identificador;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String resposta = in.readLine();

            if (resposta.equals("venda")) {
                String mensagem = "Digite <nomeVendedor> <nomeProduto> <dataVenda> <valorVenda> exemplo: Carlos Arroz 01-06-2023- 333.30 ";
                out.println(mensagem);

                resposta = in.readLine();
                if (resposta == null) {
                    throw new IllegalArgumentException("Operação inválida");
                }

                System.out.println("Venda recebida: " + resposta);

                String[] variaveisEntrada = resposta.split(" ");
                if (variaveisEntrada.length < 4) {
                    throw new IllegalArgumentException("Preencher com <nomeVendedor> <nomeProduto> <dataVenda> <valorVenda>");
                }

                String nomeVendedor = variaveisEntrada[0];
                String nomeProdutor = variaveisEntrada[1];
                String dataVenda = variaveisEntrada[2];
                String valorVendaString = variaveisEntrada[3];

                if (nomeVendedor.equals("") || nomeProdutor.equals("") || dataVenda.equals("") || valorVendaString.equals("")) {
                    throw new IllegalArgumentException("Variável vazia");
                }

                VendedorService vendedorService = new VendedorService();

                Double valorVenda = Double.valueOf(variaveisEntrada[3]);
                dataVenda = Data.formataParaAnoMesDia(dataVenda);

                mensagem = vendedorService.realizaVenda(nomeVendedor, nomeProdutor, dataVenda, valorVenda);
                out.println(mensagem);

            } else if (resposta.equals("busca")) {
                String mensagem = "Digite: 1 <nomeVendedor> - Buscar o total de vendas de um vendedor ex: 1 Carlos <novaLinha>"
                        + "Digite: 2 <nomeProduto> - Buscar o total de vendas de um produto ex: 2 Arroz <novaLinha>"
                        + "Digite: 3 <dataInicial> <dataFinal> - Buscar o total de vendas dos produtos por periodo ex: 3 02-02-2023 05-05-2023 <novaLinha>"
                        + "Digite: 4 - Buscar o vendedor que mais realizou vendas ex: 4 <novaLinha>"
                        + "Digite: 5 - Buscar o produto que foi mais vendido ex: 5";

                out.println(mensagem);

                resposta = in.readLine();
                if (resposta == null) {
                    throw new IllegalArgumentException("Operação inválida");
                }

                System.out.println("Busca recebida: operação " + resposta);

                String[] variaveisEntrada = resposta.split(" ");

                String operacao = variaveisEntrada[0];
                switch (operacao) {
                    case "1":
                        if (variaveisEntrada.length < 2) {
                            throw new IllegalArgumentException("Preencher com <nomeVendedor>");
                        }
                        VendedorService vendedorService = new VendedorService();
                        String nomeVendedor = variaveisEntrada[1];
                        mensagem = vendedorService.totalVendas(nomeVendedor);
                        break;
                    case "2":
                        if (variaveisEntrada.length < 2) {
                            throw new IllegalArgumentException("Preencher com <nomeProduto>");
                        }

                        ProdutoService produtoService = new ProdutoService();
                        String nomeProduto = variaveisEntrada[1];
                        mensagem = produtoService.totalVendas(nomeProduto);
                        break;
                    case "3":
                        if (variaveisEntrada.length < 3) {
                            throw new IllegalArgumentException("Preencher com <dataInicial> <dataFinal>");
                        }

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
        } catch (IllegalArgumentException e) {
            try {
                out.println(e.getMessage());
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
