/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.daos.EspacioDao;
import modelo.daos.ReservaDao;
import modelo.dtos.ReservaDto;

/**
 *
 * @author kennross
 */
public class Logica extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        EspacioDao edao = new EspacioDao();
        ReservaDao rdao = new ReservaDao();

        /**
         * Realizar una reserva en base a un espacio (7) días de anticipación
         * Cero (0) reservas hechas
         */
        if (request.getParameter("op") != null && request.getParameter("op").equals("ins")) {
            int tempIdPersona = Integer.parseInt(request.getParameter("rrIdPersona"));
            int tempIdEspacio = Integer.parseInt(request.getParameter("rrIdEspacio"));
            String salida;

            //Validar no tener reservas
            if (rdao.validarNumeroReservasPorPersona(tempIdPersona)) {
                //Validar la anticipación Segundo
                if (edao.validarFechaDeAnticipacion(tempIdEspacio)) {
                    ReservaDto nReserva = new ReservaDto();
                    nReserva.setIdEspacio(tempIdEspacio);
                    nReserva.setIdPersona(tempIdPersona);
                    nReserva.setEstado(1);

                    salida = rdao.insertReserva(nReserva);

                    if (salida.equals("ok")) {
                        response.sendRedirect("pages/inicio.jsp?msg=<strong><i class='glyphicon glyphicon-ok'></i> ¡Registro Éxitoso!</strong> La inscripción se realizó correctamente.&tipoAlert=success");
                    } else if (salida.equals("okno")) {
                        response.sendRedirect("pages/inicio.jsp?msg=<strong><i class='glyphicon glyphicon-exclamation-sign'></i> ¡Algo salió mal!</strong> Por favor intentelo de nuevo.&tipoAlert=warning");
                    } else {
                        response.sendRedirect("pages/inicio.jsp?msg=<strong><i class='glyphicon glyphicon-exclamation-sign'></i> ¡Ocurrió un error!</strong> Detalle: " + salida + "&tipoAlert=danger");
                    }
                } else {
                    //La fecha para poder hacer la reserva ya pasó
                    response.sendRedirect("pages/inicio.jsp?msg=<strong><i class='glyphicon glyphicon-exclamation-sign'></i> ¡Fecha Expedida!</strong> La fecha de inscripción ya expidió.&tipoAlert=danger");
                }
            } else {
                //No tiene cupo para poder reservas
                response.sendRedirect("pages/inicio.jsp?msg=<strong><i class='glyphicon glyphicon-exclamation-sign'></i> ¡Máximo de reservas!</strong> Usted no puede inscribirse en este espacio porque ya tiene un o más reservas.&tipoAlert=warning");
            }

            /**
             * Cancelar una reserva 24 horas antes
             */
        } else if (request.getParameter("op") != null && request.getParameter("op").equals("canc")) {
            int tempIdReserva = Integer.parseInt(request.getParameter("ceIdReserva"));
            int tempIdEspacio = Integer.parseInt(request.getParameter("ceIdEspacio"));
            //Valido la fecha para poder cancelar sea un día antes
            if (rdao.validarCancelacion(tempIdEspacio)) {
                String salida = rdao.cambiarEstado(tempIdReserva, 2);

                if (salida.equals("ok")) {
                    response.sendRedirect("pages/inicio.jsp?msg=<strong><i class='glyphicon glyphicon-ok'></i> ¡Cancelación Éxitosa!</strong> La reserva se cancelo antes del tiempo mínimo.&tipoAlert=success");
                } else if (salida.equals("okno")) {
                    response.sendRedirect("pages/inicio.jsp?msg=<strong><i class='glyphicon glyphicon-exclamation-sign'></i> ¡Algo salió mal!</strong> Por favor intentelo de nuevo.&tipoAlert=warning");
                } else {
                    response.sendRedirect("pages/inicio.jsp?msg=<strong><i class='glyphicon glyphicon-exclamation-sign'></i> ¡Ocurrió un error!</strong> Detalle: " + salida + "&tipoAlert=danger");
                }
            } else {
                response.sendRedirect("pages/inicio.jsp?msg=<strong><i class='glyphicon glyphicon-exclamation-sign'></i> ¡No se puede cancelar!</strong> No puede cancelar la reserva un día antes.&tipoAlert=danger");
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
