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

public class VendedorService {

    private Connection conexaoDb = null;

    public VendedorService() {
        Database database = new Database();
        this.conexaoDb = database.pegaConexao();
    }

    public String realizaVenda(String nomeVendedor, String nomeProduto, String dataVenda, Double valorVenda) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String resposta = "Error";

        if (nomeVendedor.equals("") || nomeProduto.equals("") || dataVenda.equals("") || (valorVenda <= 0)) {
            return resposta;
        }

        try {
            String queryPegaIdVendedorPorNome = "SELECT v.id  \n"
                    + "FROM vendedores v \n"
                    + "WHERE v.nome == ?";

            pstmt = this.conexaoDb.prepareStatement(queryPegaIdVendedorPorNome);
            pstmt.setString(1, nomeVendedor);
            rs = pstmt.executeQuery();
            
            if(!rs.next()){
                return resposta;
            }
            
            int idVendedor = rs.getInt("id");

            String queryPegaIdProdutoPorNome = "SELECT * \n"
                    + "FROM produtos p \n"
                    + "WHERE nome == ?";

            pstmt = this.conexaoDb.prepareStatement(queryPegaIdProdutoPorNome);
            pstmt.setString(1, nomeProduto);
            rs = pstmt.executeQuery();
            
            if(!rs.next()){
                return resposta;
            }
            
            int idProduto = rs.getInt("id");

            String insertCriaVenda = "INSERT INTO vendas  (vendedor_id, produto_id, data_venda, valor)"
                    + " VALUES (?, ?, ?, ?)";

            pstmt = this.conexaoDb.prepareStatement(insertCriaVenda);
            pstmt.setInt(1, idVendedor);
            pstmt.setInt(2, idProduto);
            pstmt.setString(3, dataVenda);
            pstmt.setDouble(4, valorVenda);

            pstmt.executeUpdate();
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

    public String totalVendas(String nomeVendedor) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String resposta = "Error";
        
        if (nomeVendedor.equals("")) {
            return resposta;
        }

        try {

            String pegaTotalVendas = "SELECT v.nome AS vendedor, SUM(vs.valor) AS total_vendas\n"
                    + "FROM vendedores v\n"
                    + "JOIN vendas vs ON v.id = vs.vendedor_id\n"
                    + "WHERE v.nome = ?\n";

            pstmt = this.conexaoDb.prepareStatement(pegaTotalVendas);
            pstmt.setString(1, nomeVendedor);
            rs = pstmt.executeQuery();
            
            if(!rs.next()){
                return resposta;
            }
            
            resposta = "Vendedor: " + rs.getString("vendedor") + " tem o total de vendas de  " + rs.getDouble("total_vendas") ;
            
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

    public String melhorVendedor(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String resposta = "Error";
        try {

            String pegaTotalVendas = "SELECT v.nome AS vendedor, SUM(vs.valor) AS total_vendas\n"
                    + "FROM vendedores v\n"
                    + "JOIN vendas vs ON v.id = vs.vendedor_id\n"
                    + "GROUP BY v.nome\n"
                    + "ORDER BY total_vendas DESC\n"
                    + "LIMIT 1;";

            pstmt = this.conexaoDb.prepareStatement(pegaTotalVendas);
            rs = pstmt.executeQuery();
            
            if(!rs.next()){
                return resposta;
            }

            resposta = "Vendedor que mais realizou vendas é : " + rs.getString("vendedor") + " no total de  : R$" + rs.getDouble("total_vendas");
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
