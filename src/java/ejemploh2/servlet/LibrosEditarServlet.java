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
 * @author GuillermoGutierrez
 */
public class LibrosEditarServlet extends HttpServlet {

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
        String idStr = request.getParameter("id");
        int id;
        if (idStr == null) {
            libro = new Libro();
        } else {
            try (LibroDao libroDao = new LibroDao()) {
                id = Integer.parseInt(idStr);
                libro = libroDao.obtenerPorId(id);
                if (libro == null) {
                    msj = "No se encontró el libro solicitado.";
                    request.setAttribute("msj", msj);
                    request.setAttribute("res", Resultado.Fracaso);
                    getServletContext().getRequestDispatcher("/librosLst.jsp").include(request, response);
                    return;
                }
            } catch (Throwable ex) {
                System.err.println("LibrosEditarServlet.doGet. Error: " + ex.getMessage());
                msj = "Se produjo un error al intentar obtener la información del libro requerido.";
                request.setAttribute("msj", msj);
                request.setAttribute("res", Resultado.Error);
                getServletContext().getRequestDispatcher("/librosLst.jsp").include(request, response);
                return;
            }
        }
        request.setAttribute("libro", libro);
        getServletContext().getRequestDispatcher("/libroEditar.jsp").include(request, response);
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
        Libro libro = null;
        String idStr = request.getParameter("id");
        try (LibroDao libroDao = new LibroDao()) {
            int id;
            if (idStr == null) {
                libro = new Libro();
            } else {
                id = Integer.parseInt(idStr);
                libro = libroDao.obtenerPorId(id);
            }
            libro.setEdicion(Integer.parseInt(request.getParameter("edicion")));
            libro.setEjemplares(Integer.parseInt(request.getParameter("ejemplares")));
            libro.setIsbn(Long.parseLong(request.getParameter("isbn")));
            libro.setPaginas(Integer.parseInt(request.getParameter("paginas")));
            libro.setSubtitulo(request.getParameter("subtitulo"));
            libro.setTitulo(request.getParameter("titulo"));
            
            // Guardando
            libroDao.guardar(libro);
            HttpSession sesion = request.getSession();
            sesion.setAttribute("res", Resultado.Exito);
            String msj;
            if (idStr == null) {
                msj = "Libro '" + libro.getTitulo()+  "' creado exitosamente.";
            } else {
                msj = "Libro '" + libro.getTitulo()+  "' actualizado exitosamente.";
            }
            sesion.setAttribute("msj", msj);
            response.sendRedirect("../libros");
        } catch(NumberFormatException nfe) {
            System.err.println("LibrosEditarServlet.doPost. Error: " + nfe.getMessage());
            request.setAttribute("libro", (libro == null) ? new Libro() : libro);
            request.setAttribute("res", Resultado.Fracaso);
            request.setAttribute("msj", "Uno o más datos no válidos.");
            getServletContext().getRequestDispatcher("/libroEditar.jsp").include(request, response);
        }catch (Throwable ex) {
            System.err.println("LibrosEditarServlet.doPost. Error: " + ex.getMessage());
            request.setAttribute("libro", (libro == null) ? new Libro() : libro);
            request.setAttribute("res", Resultado.Error);
            request.setAttribute("msj", "Se produjo un error al intentar guardar la informaci&oacute;n del libro.");
            getServletContext().getRequestDispatcher("/libroEditar.jsp").include(request, response);
        }
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
