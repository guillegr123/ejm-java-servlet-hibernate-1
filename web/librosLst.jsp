<%@page import="ejemploh2.persistencia.Libro"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Libros</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            function eliminar(id, nombre) {
                if (confirm('Esta seguro que desea eliminar el libro "' + nombre + '"?')) {
                    window.location = 'libros/eliminar?id=' + id;
                }
            }
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="mensaje.jsp" />
            <div class="row">
                <div class="col-xs-12">
                    <h1>Biblioteca</h1>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form class="form-inline" method="GET" action="libros">
                                <div class="form-group">
                                    <label for="titulo">T&iacute;tulo</label>
                                    <% String tituloBusq = request.getParameter("titulo"); %>
                                    <input type="text" class="form-control" id="titulo" name="titulo" placeholder="T&iacute;tulo" value="<%=((tituloBusq == null) ? "" : tituloBusq)%>">
                                </div>
                                <button type="submit" class="btn btn-warning">Buscar</button>
                            </form>
                        </div>
                    </div>
                    <table class="table table-bordered table-striped table-responsive">
                        <thead>
                            <tr>
                                <th>Titulo</th>
                                <th>Subtitulo</th>
                                <th>Edici&oacute;n</th>
                                <th>ISBN</th>
                                <th>Ejemplares</th>
                                <th>P&aacute;ginas</th>
                                <th>
                                    <a class="btn btn-success" href="libros/editar">Agregar</a>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <% List<Libro> libros = (List<Libro>)request.getAttribute("libros"); %>
                            <% if (libros.size() == 0) { %>
                                <tr>
                                    <td colspan="7">No hay libros</td>
                                </tr>
                            <% } else { %>
                                <% for (Libro libro: libros) { %>
                                    <tr>
                                        <td><%=libro.getTitulo()%></td>
                                        <td><%=libro.getSubtitulo()%></td>
                                        <td><%=libro.getEdicion()%></td>
                                        <td><%=libro.getIsbn()%></td>
                                        <td><%=libro.getEjemplares()%></td>
                                        <td><%=libro.getPaginas()%></td>
                                        <td>
                                            <a class="btn btn-primary" href="libros/editar?id=<%=libro.getIdLibro()%>">Editar</a>
                                            <button class="btn btn-danger" onclick="eliminar(<%=libro.getIdLibro()%>,'<%=libro.getTitulo()%>')">Eliminar</button>
                                        </td>
                                    </tr>
                                <% } %>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
