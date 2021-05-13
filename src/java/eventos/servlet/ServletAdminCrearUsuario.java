/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos.servlet;

import eventos.dao.RolesFacade;
import eventos.dao.UsuarioFacade;
import eventos.entity.Roles;
import eventos.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aaron
 */
@WebServlet(name = "ServletAdminCrearUsuario", urlPatterns = {"/ServletAdminCrearUsuario"})
public class ServletAdminCrearUsuario extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;
    
    @EJB
    private RolesFacade rolesFacade;
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
        Usuario u = new Usuario();
        String nombre = request.getParameter("nombre");
        String ape = request.getParameter("apellidos");
        String pas = request.getParameter("pass");
        String dom = request.getParameter("dom");
        String ciu = request.getParameter("ciudad");
        String gen = request.getParameter("genero");
        String email = request.getParameter("email");
        String f = request.getParameter("fecha");
        String rol = request.getParameter("rol");
        Roles r = this.rolesFacade.find(new Integer(rol));
        Date fecha = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fecha = df.parse("f");
        } catch (ParseException ex) {
            
        }
        u.setNombre(nombre);
        u.setApellidos(ape);
        u.setPassword(pas);
        u.setDomicilio(dom);
        u.setCiudad(ciu);
        u.setEmail(email);
        u.setNacimiento(fecha);
        u.setGenero(gen.charAt(0));
        u.setRol(r);
        this.usuarioFacade.create(u);
        response.sendRedirect("ServletAdminMostrarUsuarios");
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
