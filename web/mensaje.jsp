<%@page import="ejemploh2.servlet.util.Resultado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-xs-12">
        <%
            Resultado res = (Resultado)request.getAttribute("res");
            String msj = (String)request.getAttribute("msj");
            if (msj != null) {
        %>
        <div class="alert alert-<%= (res == Resultado.Exito) ? "success" : ((res == Resultado.Error) ? "danger" : "warning") %> alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong><%= (res == Resultado.Exito) ? "Info" : ((res == Resultado.Error) ? "Error" : "Alerta") %>:</strong> <%=msj%>
        </div>
        <%
            }
        %>
    </div>
</div>
