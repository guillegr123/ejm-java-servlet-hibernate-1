<%@page import="ejemploh2.persistencia.Libro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String idLibro = request.getParameter("id");
    Libro libro = (Libro)request.getAttribute("libro");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=(idLibro == null) ? "Nuevo" : "Editar"%> libro</title>
        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="../js/jquery-1.11.2.min.js" type="text/javascript"></script>
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="mensaje.jsp" />
            <div class="row">
                <div class="col-xs-12">
                    <form class="form-horizontal" method="POST" action="">
                        <% if(idLibro != null) { %>
                        <input type="hidden" name="id" value="<%=idLibro%>">
                        <% } %>
                        <h1><%=(idLibro == null) ? "Nuevo" : "Editar"%> libro</h1>
                        <div class="form-group">
                            <label for="titulo" class="col-sm-2 col-md-1 control-label">Titulo</label>
                            <div class="col-sm-10 col-md-11">
                                <input type="text" class="form-control" id="titulo" name="titulo" placeholder="T&iacute;tulo" value="<%=libro.getTitulo()%>" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="subtitulo" class="col-sm-2 col-md-1 control-label">Subt&iacute;tulo</label>
                            <div class="col-sm-10 col-md-11">
                                <input type="text" class="form-control" id="subtitulo" name="subtitulo" placeholder="Subt&iacute;tulo" value="<%=libro.getSubtitulo()%>" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edicion" class="col-sm-2 col-md-1 control-label">Edici&oacute;n</label>
                            <div class="col-sm-10 col-md-11">
                                <input type="number" class="form-control" id="edicion" name="edicion" placeholder="Edici&oacute;n" value="<%=libro.getEdicion()%>" required min="0">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="isbn" class="col-sm-2 col-md-1 control-label">ISBN</label>
                            <div class="col-sm-10 col-md-11">
                                <input type="number" class="form-control" id="isbn" name="isbn" placeholder="ISBN" value="<%=libro.getIsbn()%>" required min="0">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="ejemplares" class="col-sm-2 col-md-1 control-label">Ejemplares</label>
                            <div class="col-sm-10 col-md-11">
                                <input type="number" class="form-control" id="ejemplares" name="ejemplares" placeholder="Ejemplares" value="<%=libro.getEjemplares()%>" required min="0">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="paginas" class="col-sm-2 col-md-1 control-label">P&aacute;ginas</label>
                            <div class="col-sm-10 col-md-11">
                                <input type="number" class="form-control" id="paginas" name="paginas" placeholder="P&aacute;ginas" value="<%=libro.getPaginas()%>" required min="0">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-md-offset-1 col-sm-10 col-md-11">
                                <button type="submit" class="btn btn-success"><%=(idLibro == null) ? "Crear" : "Guardar"%></button>
                                <a class="btn btn-danger" href="../libros">Cancelar</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
