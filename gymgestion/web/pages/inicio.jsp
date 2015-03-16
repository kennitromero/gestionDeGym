<%-- 
    Document   : inicio
    Created on : 14/03/2015, 11:29:11 AM
    Author     : kennross
--%>

<%@page import="modelo.dtos.ReservaDto"%>
<%@page import="modelo.daos.ReservaDao"%>
<%@page import="modelo.dtos.PersonaDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.dtos.EspacioDto"%>
<%@page import="modelo.daos.EspacioDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession miSesion = request.getSession(false);

    PersonaDto perActual;
    perActual = (PersonaDto) miSesion.getAttribute("usuarioEntro");
    if (perActual != null) {
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">        
        <link rel="shortcut icon" type="image/x-icon" href="../img/code.ico">
        <script type="text/javascript" src="../js/jquery-1.11.2.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <title>Espacios y Reservas</title>
        <script type="text/javascript">
            $(document).ready(function () {
                // Initialize tooltip
                $('[data-toggle="tooltip"]').tooltip({
                    placement: 'right'
                });
            });

            $(function () {
                $('[data-toggle="popover"]').popover()
            })
        </script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="page-header well">
                        <h2 class="text-center text-primary">Espacios del Gimnasio</h2>                        
                    </div>
                </div>
            </div>
            <!-- Mensajes de alertas -->
            <%
                if (request.getParameter("msg") != null && request.getParameter("tipoAlert") != null) {
            %>
            <div class="alert alert-<%= request.getParameter("tipoAlert")%>" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <p class="text-center"><%= request.getParameter("msg")%></p>
            </div>
            <%
                }
            %>            
            <!-- Fin de mensajes de alertas -->
            <div class="row">
                <div class="col-md-4">
                    <div class="thumbnail">
                        <img alt="Imagen de perfil" src="../img/bicep.png">
                        <div class="caption">
                            <h3><%= perActual.getNombres() + " " + perActual.getApellidos()%> <br><small><strong>Código: </strong>(<%= perActual.getIdPersona()%>)</small></h3>
                            <p><strong>Edad: </strong><%= perActual.getEdad()%> años</p>
                            <p><strong>Sexo: </strong><% if (perActual.getSexo() == 0) {
                                    out.print("Mujer");
                                } else {
                                    out.print("Hombre");
                                }%></i></p>
                            <p><strong>Correo: </strong><%= perActual.getCorreoElectronico()%></p>
                            <p><a href="../Sesiones?op=salir" class="btn btn-default" role="button">Cerrar Sesión</a></p>
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="container-fluid">
                        <div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
                            <ul id="myTab" class="nav nav-tabs" role="tablist">
                                <li role="presentation" class="active"><a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true">Espacios</a></li>
                                <li role="presentation"><a href="#profile" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile">Mis Reservas</a></li>                               
                            </ul>
                            <div id="myTabContent" class="tab-content">
                                <div role="tabpanel" class="tab-pane fade in active" id="home" aria-labelledby="home-tab">
                                    <!-- Listado de espacios -->
                                    <%
                                        EspacioDao edao = new EspacioDao();
                                        ArrayList<EspacioDto> listaDeEspacios;
                                        listaDeEspacios = (ArrayList<EspacioDto>) edao.readEspacios();

                                        if (listaDeEspacios != null) {
                                            for (EspacioDto e : listaDeEspacios) {
                                    %>
                                    <div class="panel panel-info">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">
                                                <div class="pull-left">
                                                    <strong>Código de espacio: </strong><%= e.getIdEspacio()%>
                                                </div>                                
                                                <div class="pull-right">
                                                    <strong>Cupos Disponibles: </strong> <%= e.getNumeroCupos()%>
                                                </div>
                                                <br>
                                            </h3>                            
                                        </div>
                                        <div class="panel-body">
                                            <div class="pull-left">
                                                <strong>Fecha de apertura: </strong> <%= e.getFechaApertura()%>
                                            </div>

                                            <div class="pull-right">                                                
                                                <strong>Estado: </strong>
                                                <%
                                                    if (e.getEstado() == 1) {
                                                        out.print("Abierta");
                                                    } else if (e.getEstado() == 2) {
                                                        out.print("Acabada");
                                                    }
                                                %>
                                                <a href="../Logica?rrIdPersona=<%= perActual.getIdPersona()%>&rrIdEspacio=<%= e.getIdEspacio()%>&rrCupos=<%= e.getNumeroCupos()%>&op=ins">
                                                    <button class="btn btn-info">
                                                        Inscribirme
                                                    </button>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <%
                                        }
                                    } else {
                                    %>
                                    <center>
                                        <div class="alert alert-info"><strong>No hay espacios <i class="glyphicon glyphicon-remove-sign"></i></strong></div>
                                    </center>
                                    <%
                                        }
                                    %>
                                    <!-- Fin listado de espacios -->
                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="profile" aria-labelledby="profile-tab">
                                    <!-- Listado de reservas -->
                                    <%
                                        ReservaDao rdao = new ReservaDao();
                                        ArrayList<ReservaDto> listaDeReservas;
                                        listaDeReservas = (ArrayList<ReservaDto>) rdao.readReservasPorPersona(perActual.getIdPersona());
                                    %>
                                    <table class="table">
                                        <thead>
                                            <%
                                                if (!listaDeReservas.isEmpty()) {
                                            %>
                                            <tr>
                                                <th>Código Reserva</th>                                                
                                                <th>Código Espacio</th>
                                                <th>Fecha Apertura</th>
                                                <th>Estado</th>
                                                <th>Acción</th>
                                            </tr>      
                                            <%
                                                }
                                            %>                                            
                                        </thead>
                                        <tbody>
                                            <%
                                                if (!listaDeReservas.isEmpty()) {
                                                    for (ReservaDto r : listaDeReservas) {
                                            %>
                                            <tr>
                                                <td><%= r.getIdReserva()%></td>                                                
                                                <td><%= r.getIdEspacio()%></td>
                                                <td><%= edao.obtenerFechaPorId(r.getIdEspacio())%></td>
                                                <td>
                                                    <%
                                                        // Para (1) es Activa y para (2) es Cancelada
                                                        if (r.getEstado() == 1) {
                                                            out.print("Activa ");
                                                        } else if (r.getEstado() == 2) {
                                                            out.print("Cancelada");
                                                        }
                                                    %>
                                                </td>
                                                <td>
                                                    <a href="<% if (r.getEstado() != 2) { out.print("../Logica?ceIdReserva=" + r.getIdReserva() + "&ceIdEspacio=" + r.getIdEspacio() +"&op=canc"); } else { out.print("#"); }%>">
                                                        <button 
                                                            <%
                                                                if (r.getEstado() == 2) {
                                                                    out.print("data-toggle='popover' data-placement='top' title='Acción Invalida' data-content='No puede realizar la cancelación porque ya está cancelada'");
                                                                }
                                                            %> class="btn btn-danger">
                                                            Cancelar 
                                                            <i class="glyphicon glyphicon-remove"></i>
                                                        </button>
                                                    </a>
                                                </td>
                                            </tr>
                                            <%
                                                }
                                            } else {
                                            %>
                                        <center>
                                            <div class="alert alert-info"><strong>No tiene reservas de espacios <i class="glyphicon glyphicon-remove-sign"></i></strong></div>
                                        </center>
                                        <%
                                            }
                                        %>
                                        </tbody>
                                    </table>                                    
                                </div>
                                <!-- Listado de reservas -->
                            </div>
                        </div>
                    </div>                        
                </div>
            </div>
        </div>
    </body>
</html>
<%
    } else {
        response.sendRedirect("../index.jsp?msg=<strong>¡Inicie Sesión! <i class='glyphicon glyphicon-exclamation-sign'></i></strong> Debe iniciar sesión primero.&tipoAlert=info");
    }
%>