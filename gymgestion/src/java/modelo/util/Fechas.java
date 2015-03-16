/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.util;

/**
 *
 * @author kennross
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Fechas {

    public static void main(String[] args) {
        try {
            Date fechaActual = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            String tempFechaSistema = formateador.format(fechaActual);
            
            String tempFechaAnticipacion = "25/03/2015";
            Date fechaAnticipacion = formateador.parse(tempFechaAnticipacion);
            Date fechaSistema = formateador.parse(tempFechaSistema);
            
            System.out.println(fechaAnticipacion.compareTo(fechaSistema));
        } catch (ParseException ex) {
            Logger.getLogger(Fechas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
