<%-- 
    Document   : index
    Created on : 14/03/2015, 11:24:49 AM
    Author     : kennross
--%>

<%@page import="modelo.dtos.PersonaDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession miSesion = request.getSession(false);
    
    PersonaDto perActual;
    perActual = (PersonaDto) miSesion.getAttribute("usuarioEntro");
    if (perActual == null) {
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script type="text/javascript" src="js/jquery-1.11.2.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <link rel="shortcut icon" type="image/x-icon" href="img/code.ico">
        <title>Gestión De Gym</title>
        <script type="text/javascript">
            $(document).ready(function () {
                // Initialize tooltip
                $('[data-toggle="tooltip"]').tooltip({
                    placement: 'top'
                });
            });
        </script>
    </head>
    <body>


        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="page-header well">
                        <h1 class="text-center">Sistema de gestión de reservas para Gym</h1>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
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
                </div>
                <div class="col-md-1"></div>
            </div>

            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <form method="POST" action="Sesiones">
                        <legend class="text-center">Iniciar Sesión</legend>
                        <div class="input-group">
                            <span class="input-group-addon" id="isCorreo">@</span>
                            <input type="email" class="form-control" placeholder="Correo Electrónico" 
                                   aria-describedby="basic-addon1" name="isCorreo" value="kdruz@misena.edu.co" required>
                        </div>
                        <br>
                        <div class="input-group">
                            <span class="input-group-addon" id="isClave"><i class="glyphicon glyphicon-unchecked"></i></span>
                            <input type="password" class="form-control" placeholder="Contraseña de ingreso" 
                                   aria-describedby="basic-addon1" name="isClave" value="ooo" required>
                        </div>
                        <br>
                        <input type="hidden" name="isEnviar" value="ok">
                        <center>
                            <button type="submit" class="btn btn-default">Entrar</button>
                        </center>                        
                    </form>
                </div>
                <div class="col-md-4"></div>
            </div>

            <div class="row">
                <nav class="navbar navbar-fixed-bottom">
                    <div class="container-fluid">
                        <center>
                            <div class="navbar-header">                            
                                <a class="navbar-brand" href="#">Kennit Romero <small>97043009625</small></a>
                            </div>
                        </center>                        
                    </div>
                </nav>
            </div>
        </div>
    </body>
</html>
<%
    } else {
        response.sendRedirect("pages/inicio.jsp?msg=<strong>¡No cerró sesión! <i class='glyphicon glyphicon-exclamation-sign'></i></strong> Debe cerrar sesión correctamente.&tipoAlert=danger");
    }
%>