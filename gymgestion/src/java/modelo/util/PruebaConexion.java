/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.util;

import java.sql.Connection;
import java.util.ArrayList;
import modelo.daos.EspacioDao;
import modelo.daos.PersonaDao;
import modelo.daos.ReservaDao;
import modelo.dtos.EspacioDto;
import modelo.dtos.PersonaDto;
import modelo.dtos.ReservaDto;

/**
 *
 * @author kennross
 */
public class PruebaConexion {

    public static void main(String[] args) {

        Connection miCon = Conexion.getInstance();

        if (miCon != null) {
            System.out.println("ok");
        } else {
            System.out.println("Error");
        }
        
        EspacioDao edao = new EspacioDao();
        ReservaDao rdao = new ReservaDao();
        
        System.out.println("-----------------------------------------------");
                
        ArrayList<ReservaDto> listaDeReservas;
        listaDeReservas = (ArrayList<ReservaDto>) rdao.readReservasPorPersona(1);

        for (ReservaDto r : listaDeReservas) {
            System.out.println(r);
        }
        System.out.println("-----------------------------------------------");

        
        ArrayList<EspacioDto> listaDeEspacios;
        listaDeEspacios = (ArrayList<EspacioDto>) edao.readEspacios();

        for (EspacioDto e : listaDeEspacios) {
            System.out.println(e);
        }
        System.out.println("-----------------------------------------------");
        String correo = "kdruz@misena.ed.co";
        PersonaDao perDao = new PersonaDao();
        PersonaDto queryPersona;
        queryPersona = perDao.readPersona(correo);

        if (queryPersona != null) {
            System.out.println("Existe");
        } else {
            System.out.println("No Existe");
        }
        
        System.out.println("------------------------------------------");
        
        if (rdao.validarCupoDisponible(3, 1)) {
            System.out.println("ok");
        } else {
            System.out.println("okno");
        }
    }
}
