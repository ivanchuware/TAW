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
import javax.servlet.RequestDispatcher;
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
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellidos");
        String password = request.getParameter("pass");
        String domicilio = request.getParameter("dom");
        String ciudad = request.getParameter("ciudad");
        String sexo = request.getParameter("genero");
        String email = request.getParameter("email");
        String nacimiento = request.getParameter("fecha");
        String rol = request.getParameter("rol");
        Roles r;
        boolean error = false;
        String errorMsg = "";
        if(rol == null){
            error = true;
            errorMsg += "Rol no especificado";
        } else{
            r = this.rolesFacade.find(new Integer(rol));
        }
  
        Date fecha = new Date();
        
        if (nombre.isEmpty()) {
            error = true;
            errorMsg += " Nombre vacío";
        }
        if (apellido.isEmpty()) {
            error = true;
            errorMsg += " Apellido vacío";
        }
        if (ciudad.isEmpty()) {
            error = true;
            errorMsg += " Ciudad vacía";
        }
        if (domicilio.isEmpty()) {
            error = true;
            errorMsg += " Domicilio vacío";
        }
        if (sexo == null) {
            error = true;
            errorMsg += " Sexo no especificado";
        }
        if (nacimiento.isEmpty()) {
            error = true;
            errorMsg += " Fecha de nacimiento no especificada";
        }
        if (email.isEmpty()) {
            error = true;
            errorMsg += " E-mail vacío";
        }
        if (password.isEmpty()) {
            error = true;
            errorMsg += " Contraseña vacía";
        }        
        
        request.setAttribute("error", error);
        request.setAttribute("errorMsg", errorMsg);        
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fecha = df.parse(nacimiento);
        } catch (ParseException ex) {
            error = true;
        }
        
        if(!error){
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setApellidos(apellido);
            usuario.setCiudad(ciudad);
            usuario.setEmail(email);
            usuario.setGenero(sexo.charAt(0));
            usuario.setPassword(password);
            usuario.setNacimiento(fecha);
            usuario.setDomicilio(domicilio);
            this.usuarioFacade.create(usuario);
            RequestDispatcher rd = request.getRequestDispatcher("ServletAdminMostrarUsuarios");
            rd.forward(request, response);  
        } else{
            RequestDispatcher rd = request.getRequestDispatcher("AdminAgregarUsuario.jsp");
            rd.forward(request, response);       
        }

        
        
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
