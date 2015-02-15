/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploh2.servlet;

import ejemploh2.persistencia.Libro;
import ejemploh2.persistencia.dao.LibroDao;
import ejemploh2.servlet.util.Resultado;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author java
 */
public class LibrosEliminarServlet extends HttpServlet {

    // <editor-fold defaultstate="expanded" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        Libro libro = null;
        String msj = null;
        Resultado res = Resultado.Error;
        HttpSession sesion = request.getSession();
        try (LibroDao libroDao = new LibroDao()) {
            String idStr = request.getParameter("id");
            int id;
            if (idStr != null) {
                id = Integer.parseInt(idStr);
                libro = libroDao.obtenerPorId(id);
                if (libro != null) {
                    libroDao.eliminar(libro);
                    msj = "Libro '" + libro.getTitulo()+ "' eliminado.";
                    res = Resultado.Exito;
                } else {
                    msj = "No se encontró el libro a eliminar.";
                }
            } else {
                msj = "No se especificó el libro a eliminar.";
            }
        } catch (Throwable ex) {
            System.err.println("LibrosEliminarServlet.doGet. Error: " + ex.getMessage());
            msj = "No se pudo eliminar el libro debido a un error interno.";
        }
        sesion.setAttribute("res", res);
        sesion.setAttribute("msj", msj);
        response.sendRedirect("../libros");
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
