/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3.servicos;

import com.mycompany.trabalhosda3.TrabalhoSDA3;
import com.mycompany.trabalhosda3.config.Database;
import com.mycompany.trabalhosda3.utils.ValorMonetario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para realizar as operações no banco de dados em relação ao vendedor
 */
public class VendedorService {

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

            pstmt = Database.getInstance().prepareStatement(queryPegaIdVendedorPorNome);
            pstmt.setString(1, nomeVendedor);
            rs = pstmt.executeQuery();
            
            if(!rs.next()){
                return resposta;
            }
            
            int idVendedor = rs.getInt("id");
            
            pstmt.close();
            rs.close();
            
            String queryPegaIdProdutoPorNome = "SELECT * \n"
                    + "FROM produtos p \n"
                    + "WHERE nome == ?";

            pstmt = Database.getInstance().prepareStatement(queryPegaIdProdutoPorNome);
            pstmt.setString(1, nomeProduto);
            rs = pstmt.executeQuery();
            
            if(!rs.next()){
                return resposta;
            }
            
            int idProduto = rs.getInt("id");

            String insertCriaVenda = "INSERT INTO vendas  (vendedor_id, produto_id, data_venda, valor)"
                    + " VALUES (?, ?, ?, ?)";

            pstmt = Database.getInstance().prepareStatement(insertCriaVenda);
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

            pstmt = Database.getInstance().prepareStatement(pegaTotalVendas);
            pstmt.setString(1, nomeVendedor);
            rs = pstmt.executeQuery();
            
            if(!rs.next()){
                return resposta;
            }
            
            String nomeVendedor2  = rs.getString("vendedor");
            Double totalVendasNaoFormatado = rs.getDouble("total_vendas");
            
            if(nomeVendedor2 == null || totalVendasNaoFormatado == null  ){
                throw  new Exception("Produto não encontrado");
            }
            
            String totalVendas = ValorMonetario.formatar(totalVendasNaoFormatado);
            resposta = "Vendedor: " + nomeVendedor2 + " tem o total de vendas de  " + totalVendas ;
            
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

            pstmt = Database.getInstance().prepareStatement(pegaTotalVendas);
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
