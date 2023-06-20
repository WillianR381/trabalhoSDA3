package com.mycompany.trabalhosda3.config;

import com.mycompany.trabalhosda3.TrabalhoSDA3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private static Connection uniqueInstance;

    public static synchronized Connection getInstance() {
        if (uniqueInstance == null) {
            try {
                uniqueInstance = DriverManager.getConnection("jdbc:sqlite:banco.db");
                System.out.println("Conexão realizada !!!!");
            } catch (SQLException ex) {
                Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "SQLException", ex);
            }
        }
        return uniqueInstance;
    }

    public void closeConnection() {
        try {
            if (Database.uniqueInstance != null) {
                uniqueInstance.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "SQLException", ex);
        }
    }

    private void criaTabelas() {
        try {
            String createTableVendedores = "CREATE TABLE IF NOT EXISTS vendedores (\n"
                    + "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + "  nome TEXT NOT NULL\n"
                    + ");";

            String createTableProdutos = "CREATE TABLE IF NOT EXISTS produtos (\n"
                    + "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + "  nome TEXT NOT NULL\n"
                    + ");";

            String createTableVendas = "CREATE TABLE IF NOT EXISTS vendas (\n"
                    + "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + "  vendedor_id INTEGER NOT NULL,\n"
                    + "  produto_id INTEGER NOT NULL,\n"
                    + "  data_venda DATE NOT NULL,\n"
                    + "  valor REAL NOT NULL,\n"
                    + "  FOREIGN KEY (vendedor_id) REFERENCES vendedores(id),\n"
                    + "  FOREIGN KEY (produto_id) REFERENCES produtos(id)\n"
                    + ");";

            Statement stmt = Database.uniqueInstance.createStatement();

            stmt.executeUpdate(createTableVendedores);
            System.out.println("Tabela Vendedores Criada !!!");

            stmt.executeUpdate(createTableVendedores);
            System.out.println("Tabela Vendedores Criada !!!");

            stmt.executeUpdate(createTableProdutos);
            System.out.println("Tabela Produtos Criada !!!");

            stmt.executeUpdate(createTableVendas);
            System.out.println("Tabela Vendas Criada !!!");

        } catch (SQLException ex) {
            Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "SQLException", ex);
        }

    }

    private void insereRegistros() {
        try {
            Statement stmt = Database.uniqueInstance.createStatement();

            stmt.executeUpdate("INSERT INTO vendedores (nome) VALUES ('carlos')");
            stmt.executeUpdate("INSERT INTO vendedores (nome) VALUES ('roberto')");
            stmt.executeUpdate("INSERT INTO vendedores (nome) VALUES ('junior')");

            stmt.executeUpdate("INSERT INTO produtos (nome) VALUES ('arroz');");
            stmt.executeUpdate("INSERT INTO produtos (nome) VALUES ('feijao');");
            stmt.executeUpdate("INSERT INTO produtos (nome) VALUES ('macarrao');");

            stmt.executeUpdate("INSERT INTO vendas (vendedor_id, produto_id, data_venda, valor) VALUES (1, 1, '2023-05-01', 100.00);");
            stmt.executeUpdate("INSERT INTO vendas (vendedor_id, produto_id, data_venda, valor) VALUES (2, 1, '2023-05-02', 150.00);");
            stmt.executeUpdate("INSERT INTO vendas (vendedor_id, produto_id, data_venda, valor) VALUES (1, 2, '2023-05-02', 200.00);");
            stmt.executeUpdate("INSERT INTO vendas (vendedor_id, produto_id, data_venda, valor) VALUES (2, 3, '2023-05-03', 250.00);");
            stmt.executeUpdate("INSERT INTO vendas (vendedor_id, produto_id, data_venda, valor) VALUES (3, 3, '2023-05-03', 300.00);");
            stmt.executeUpdate("INSERT INTO vendas (vendedor_id, produto_id, data_venda, valor) VALUES (3, 2, '2023-12-03', 350.00);");

        } catch (SQLException ex) {
            Logger.getLogger(TrabalhoSDA3.class.getName()).log(Level.SEVERE, "SQLException", ex);
        }
    }

    public static void iniciaBancoDados() {
        Database.getInstance();
        
        Database database = new Database();
        database.criaTabelas();
        database.insereRegistros();
        database.closeConnection();
    }

}
