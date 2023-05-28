/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3.services;

import com.mycompany.trabalhosda3.TrabalhoSDA3;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendedorService extends BaseService {

    
    public String realizaVenda(String nomeVendedor, String nomeProduto, String dataVenda, Double valorVenda) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String status = "Error";
      
        if(nomeVendedor.equals("") || nomeProduto.equals("") || dataVenda.equals("") || (valorVenda <= 0)){
               return status;
        }
        
        try {
            String queryPegaIdVendedorPorNome = "SELECT v.id  \n"
                    + "FROM vendedores v \n"
                    + "WHERE v.nome == ?";

            pstmt = connection.prepareStatement(queryPegaIdVendedorPorNome);
            pstmt.setString(1, nomeVendedor);
            rs = pstmt.executeQuery();

            int idVendedor = rs.getInt("id");

            String queryPegaIdProdutoPorNome = "SELECT * \n"
                    + "FROM produtos p \n"
                    + "WHERE nome == ?";

            pstmt = this.connection.prepareStatement(queryPegaIdProdutoPorNome);
            pstmt.setString(1, nomeProduto);
            rs = pstmt.executeQuery();

            int idProduto = rs.getInt("id");

            String insertCriaVenda = "INSERT INTO vendas  (vendedor_id, produto_id, data_venda, valor)"
                    + " VALUES (?, ?, ?, ?)";

            pstmt = this.connection.prepareStatement(insertCriaVenda);
            pstmt.setInt(1, idVendedor);
            pstmt.setInt(2, idProduto);
            pstmt.setString(3, dataVenda);
            pstmt.setDouble(4, valorVenda);

            pstmt.executeUpdate();

            status = "OK";
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

}
