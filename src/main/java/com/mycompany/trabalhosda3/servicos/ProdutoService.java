/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3.servicos;

import com.mycompany.trabalhosda3.TrabalhoSDA3;
import com.mycompany.trabalhosda3.config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author willian
 */
public class ProdutoService {

    private Connection conexaoDb = null;

    public ProdutoService() {
        Database database = new Database();
        this.conexaoDb = database.pegaConexao();
    }

    public String totalVendas(String nomeProduto) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String status = "Error";
        try {
            String pegaTotalVendas = "SELECT p.nome AS produto, SUM(vs.valor) AS total_vendas, p.id AS produto_id, vs.data_venda AS data_venda \n"
                    + "FROM produtos p \n"
                    + "JOIN vendas vs ON p.id = vs.produto_id \n"
                    + "WHERE p.nome = ?";

            pstmt = this.conexaoDb.prepareStatement(pegaTotalVendas);
            pstmt.setString(1, nomeProduto);
            rs = pstmt.executeQuery();

            status = "Produto: " + rs.getString("produto") + " tem o total de vendas de  " + rs.getDouble("total_vendas") + ".";
        } catch (SQLException ex) {
            Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pstmt.close();

            } catch (SQLException ex) {
                Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return status;
    }

    public String totalVendasPorPeriodo(String dataInicial, String dataFinal) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String resposta = "Error";
        
        if (dataInicial.equals("") || dataFinal.equals("") ) {
            return resposta;
        }
        try {
            String pegaTotalVendasPorPeriodo = "SELECT p.nome AS produto, SUM(vs.valor) AS total_vendas \n"
                    + "FROM produtos p \n"
                    + "JOIN vendas vs ON p.id = vs.produto_id \n"
                    + "WHERE vs.data_venda  BETWEEN ? AND ? \n"
                    + "GROUP BY p.nome";

            pstmt = this.conexaoDb.prepareStatement(pegaTotalVendasPorPeriodo);
            pstmt.setString(1, dataInicial);
            pstmt.setString(2, dataFinal);
            rs = pstmt.executeQuery();

            resposta = "OK";
        } catch (SQLException ex) {
            Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pstmt.close();

            } catch (SQLException ex) {
                Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return resposta;
    }

    public String melhorProduto() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String resposta = "Error";
        try {
            String pegaTotalVendasPorPeriodo = "SELECT p.nome AS produto, SUM(vs.valor) AS total_vendas\n"
                    + "FROM produtos p\n"
                    + "JOIN vendas vs ON p.id = vs.produto_id\n"
                    + "GROUP BY p.nome\n"
                    + "ORDER BY total_vendas DESC\n"
                    + "LIMIT 1;";

            pstmt = this.conexaoDb.prepareStatement(pegaTotalVendasPorPeriodo);
            rs = pstmt.executeQuery();

            resposta = "O produto mais vendido é : " + rs.getString("produto") + " no total de  : R$" + rs.getDouble("total_vendas");
        } catch (SQLException ex) {
            Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pstmt.close();

            } catch (SQLException ex) {
                Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return resposta;
    }

}
