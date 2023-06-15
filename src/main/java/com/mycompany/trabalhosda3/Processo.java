package com.mycompany.trabalhosda3;

public class Processo {
    private String nome; 
    private Integer identificador;
    private String host;
    private Integer port;
    
    public Processo(String nome, Integer identificador, String host, Integer port){
        this.nome = nome;
        this.identificador = identificador;
        this.host = host;
        this.port = port;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
    
}
