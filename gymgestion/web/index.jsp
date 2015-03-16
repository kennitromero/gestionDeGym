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
                    <a href="index.jsp">
                        <div class="page-header well">
                            <h1 class="text-center">Sistema de gestión de reservas para Gym</h1>
                        </div>
                    </a>                    
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

            <br>

            <div class="row">
                <div role="tabpanel">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#datos" aria-controls="home" role="tab" data-toggle="tab">Datos de entrada</a></li>
                        <li role="presentation"><a href="#regla1" aria-controls="profile" role="tab" data-toggle="tab">Regla de Negocio uno (1)</a></li>
                        <li role="presentation"><a href="#regla2" aria-controls="messages" role="tab" data-toggle="tab">Regla de Negocio dos (2)</a></li>
                        <li role="presentation"><a href="#regla3" aria-controls="settings" role="tab" data-toggle="tab">Regla de Negocio tres (3)</a></li>
                        <li role="presentation"><a href="#regla4" aria-controls="settings" role="tab" data-toggle="tab">Regla de Negocio cuatro (4)</a></li>
                    </ul>

                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="datos">
                            <h4>Existen 5 personas registradas en la base de datos <small>Para hacer pruebas</small></h4>
                            <ol>
                                <li><strong>Correo </strong>kdruz@misena.edu.co</li>
                                <li><strong>Correo </strong>solucionaticos@gmail.com</li>
                                <li><strong>Correo </strong>alyssa-luna@hotmail.com</li>
                                <li><strong>Correo </strong>raranda3@misena.edu.co</li>
                                <li><strong>Correo </strong>kdruz@misena.edu.co</li>
                            </ol>
                            <p>La contraseña puede ser cualquier cosa, luego de que no lo mande vacía.</p>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="regla1">
                            <h2><mark>Máximo de reservas por persona</mark></h2><br>
                            <em>
                                Una persona sólo puede tener una reserva registrada en la base de datos, es decir,<br> sólo podrá realizar inscribiciones a reservas
                                cuando haya concluido la reserva o cuando el aprendiz la cancele.
                            </em>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="regla2">
                            <h2><mark>Cupos limitados para espacios</mark></h2><br>
                            <em>
                                Todos los espacios abiertos para los aprendices tienen un cupo determinado <br> La regla dice entonces
                                que cuando se llene un espacio, no sea posible el registro de una reserva para dicho espacio.
                            </em>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="regla3">
                            <h2><mark>Una semana de anticipación para reserva</mark></h2><br>
                            <em>
                                Para que una persona pueda realizar una reserva para debe cumplir con el parametro con que<br> cuando se
                                haga la reserva esté dentro del plazo que acordado, es decir, que la fecha de apertura del espacio y la fecha de<br>
                                en el cual el aprendiz va a hacer la reserva, tenga  al menos 7 días de diferencia.
                            </em>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="regla4">
                            <h2><mark>Un (1) día de anticiáción para cancelar reserva</mark></h2><br>
                            <em>
                                El aprendiz tendrá un listado de todas las reservas que tiene, pero para poder cancelar una de esas reservas<br>
                                se debe realizar con un día de anticipación con respecto a la fecha de apertura
                            </em>
                        </div>
                    </div>

                </div>
            </div>

            <div class="row">
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                        <center>
                            <div class="navbar-header">                            
                                <a class="navbar-brand" href="#"><strong>Aprendiz: </strong>Kennit Romero &AMP; <small><strong>Número de documento: </strong>97043009625</small></a>
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