package com.mycompany.trabalhosda3;

public class Processo {
    private String identificador;
    private String host;
    private Integer port;
    
    public Processo(String identificador, String host, Integer port){
        this.identificador = identificador;
        this.host = host;
        this.port = port;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
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
