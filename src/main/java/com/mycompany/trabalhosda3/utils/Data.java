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

/**
 *
 * @author willian
 */
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
}
