/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosda3.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Data {

    private static SimpleDateFormat formatadorAnoMesDia = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat formatadorDiaMesAno = new SimpleDateFormat("dd-MM-yyyy");

    public static String formataParaDiaMesAno(String data) {
        String dataFormatada = "";
        try {
            Date dataTemporaria = formatadorAnoMesDia.parse(data);
            dataFormatada = formatadorDiaMesAno.format(dataTemporaria);
        } catch (ParseException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, "Erro Data::formataParaDiaMesAno", ex);
        }
        return dataFormatada;
    }

    public static String formataParaAnoMesDia(String data) {
        String dataFormatada = "";
        try {
            Date dataTemporaria = formatadorDiaMesAno.parse(data);
            dataFormatada = formatadorAnoMesDia.format(dataTemporaria);
        } catch (ParseException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, "Erro Data::formataParaAnoMesDia", ex);
        }
        return dataFormatada;
    }
    
    public static boolean validaData(String data){
        
        System.out.println(data);
        String[] dataNaoFormatado = data.split("-");
        
        Integer dia = Integer.valueOf(dataNaoFormatado[0]);
        Integer mes = Integer.valueOf(dataNaoFormatado[1]);
        Integer ano =  Integer.valueOf(dataNaoFormatado[2]);
        
        if( ano >= 1900 && ano <= 2023 &&
            mes > 0 && mes <= 12 &&
            dia > 0 && dia <= 31){
            return true;
        }
        
        return false;
    }
}
