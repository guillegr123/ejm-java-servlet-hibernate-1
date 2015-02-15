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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author java
 */
public class LibrosListaServlet extends HttpServlet {

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
        List<Libro> libros = null;
        HttpSession sesion = request.getSession();
        String msj = (String)sesion.getAttribute("msj");
        Resultado res = (Resultado)sesion.getAttribute("res");
        String tituloBusq = request.getParameter("titulo");
        try (LibroDao libroDao = new LibroDao()) {
            if (tituloBusq == null || tituloBusq.trim().equals("")) {
                libros = libroDao.obtenerTodos();
            } else {
                libros = libroDao.buscarPorTitulo(tituloBusq);
            }
        } catch (Throwable ex) {
            System.err.println("LibrosListaServlet.doGet. Error: " + ex.getMessage());
            if (msj == null) {
                msj = "No se pudo obtener el listado de libros.";
            }
            res = Resultado.Error;
        }
        if (libros == null) {
            libros = new ArrayList<>();
        }
        if (msj != null) {
            request.setAttribute("msj", msj);
            sesion.removeAttribute("msj");
        }
        if (res != null) {
            request.setAttribute("res", res);
            sesion.removeAttribute("res");
        }
        request.setAttribute("libros", libros);
        this.getServletContext().getRequestDispatcher("/librosLst.jsp").include(request, response);
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
