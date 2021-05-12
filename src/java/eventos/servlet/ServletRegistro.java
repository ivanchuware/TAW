/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos.servlet;

import eventos.dao.RolesFacade;
import eventos.dao.UsuarioFacade;
import eventos.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
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
 * @author EloyB
 */
@WebServlet(name = "ServletRegistro", urlPatterns = {"/ServletRegistro"})
public class ServletRegistro extends HttpServlet {

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
        
        Date fecha = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String nombre=request.getParameter("nombre");  
        String apellido=request.getParameter("apellido");  
        String ciudad=request.getParameter("ciudad");  
        String domicilio=request.getParameter("domicilio");  
        String sexo=request.getParameter("sex");  
        String nacimiento=request.getParameter("nacimiento");
        String email=request.getParameter("email");  
        String password=request.getParameter("password");
        boolean error = false;
        String errorMsg = "";
        
        request.setAttribute("nombre", nombre);
        request.setAttribute("apellido", apellido);
        request.setAttribute("ciudad", ciudad);
        request.setAttribute("domicilio", domicilio);
        request.setAttribute("sexo", sexo);
        request.setAttribute("fecha", nacimiento);
        request.setAttribute("email", email);
        
        
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
        
        try {
            fecha = df.parse(nacimiento);
        } catch (Exception e) {
            error = true;
        }
        request.setAttribute("error", error);
        request.setAttribute("errorMsg", errorMsg);
        
        if (!error) {
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setApellidos(apellido);
            usuario.setCiudad(ciudad);
            usuario.setEmail(email);
            char genero;
            if (sexo.equals("male")) {
                genero = 'M';
            } else {
                genero = 'F';
            }
            usuario.setGenero(genero);
            usuario.setPassword(password);
            usuario.setNacimiento(fecha);
            usuario.setDomicilio(domicilio);
            usuario.setRol(rolesFacade.find(3));
            
            usuarioFacade.create(usuario);
         
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("registro.jsp");
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
