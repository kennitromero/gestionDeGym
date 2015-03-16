/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.daos.PersonaDao;
import modelo.dtos.PersonaDto;

/**
 *
 * @author kennross
 */
public class Sesiones extends HttpServlet {

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

        if (request.getParameter("isEnviar") != null && request.getParameter("isEnviar").equals("ok")) {
            String correo = request.getParameter("isCorreo");
            PersonaDao perDao = new PersonaDao();
            PersonaDto queryPersona;
            queryPersona = perDao.readPersona(correo);

            if (queryPersona != null) {
                HttpSession miSesion = request.getSession();
                miSesion.setAttribute("usuarioEntro", queryPersona);
                
                String mensaje;
                if (queryPersona.getSexo() == 0) {
                    mensaje = "<strong>¡Bienvenida!</strong> Señorita: " + queryPersona.getNombres() + " <strong><i class='glyphicon glyphicon-thumbs-up'></i></strong>";
                } else {
                    mensaje = "<strong>¡Bienvenido!</strong> Señorito: " + queryPersona.getNombres() + " <strong><i class='glyphicon glyphicon-thumbs-down'></i></strong>";
                }
                response.sendRedirect("pages/inicio.jsp?msg=" + mensaje + ".&tipoAlert=success");
            } else {
                response.sendRedirect("index.jsp?msg=<strong>¡No Existe Usuario! <i class='glyphicon glyphicon-exclamation-sign'></i></strong> Pida a un administrador que lo registre en el sistema.&tipoAlert=danger");
            }
        } else if (request.getParameter("op").equals("salir")) {
            HttpSession miSesion = request.getSession();           
            miSesion.invalidate();
            
            response.sendRedirect("index.jsp?msg=<strong>¡Sesión Cerrada Correctamente!</strong> <i class='glyphicon glyphicon-ok'></i>&tipoAlert=info");
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
