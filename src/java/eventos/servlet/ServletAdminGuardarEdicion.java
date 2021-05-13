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
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ServletAdminGuardarEdicion", urlPatterns = {"/ServletAdminGuardarEdicion"})
public class ServletAdminGuardarEdicion extends HttpServlet {

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
        String str = request.getParameter("idUsuario");
        Integer id = new Integer(str);
        Usuario u = this.usuarioFacade.find(id);
        String nombre = request.getParameter("nombre");
        String ape = request.getParameter("apellidos");
        String pas = request.getParameter("pass");
        String dom = request.getParameter("dom");
        String ciu = request.getParameter("ciudad");
        String gen = request.getParameter("genero");
        String genDefault = request.getParameter("gen");
        String email = request.getParameter("email");
        String r = request.getParameter("rol");
        String rolDefault = request.getParameter("rolDef");
        Roles rolD = this.rolesFacade.find(new Integer(rolDefault));
        if(gen != null){
            u.setGenero(gen.charAt(0));
        } else{
            u.setGenero(genDefault.charAt(0));
        }
        
        if(r != null){
            Roles rol = this.rolesFacade.find(new Integer(r));
            u.setRol(rol);
        } else{
            u.setRol(rolD);
        }
        
        String f = request.getParameter("fecha");
        Date fecha = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fecha = df.parse(f);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        } 
      
        u.setNombre(nombre);
        u.setApellidos(ape);
        u.setPassword(pas);
        u.setDomicilio(dom);
        u.setCiudad(ciu);
        u.setEmail(email);
        u.setNacimiento(fecha);
        this.usuarioFacade.edit(u);
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
