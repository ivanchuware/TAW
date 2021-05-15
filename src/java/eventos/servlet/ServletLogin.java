/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos.servlet;

import eventos.dao.UsuarioFacade;
import eventos.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ivanchu
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;

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
            
            HttpSession session = request.getSession();
            String stremail = request.getParameter("correo");
            String strpwd = request.getParameter("contrasena");
            Usuario user = null;
            try {
                user = usuarioFacade.findByEmail(stremail);
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }

            List<Usuario> lista = usuarioFacade.findAll();
            
            Boolean error = false;
            String errorMsg = "";
            
            if (stremail == null || stremail.isEmpty())
            {
                error = true;
                errorMsg = "Inserte Email";
                
            }
            if (strpwd == null || strpwd.isEmpty())
            {
                if (error){
                    errorMsg += " y Contraseña";
                } else {
                    error = true;
                    errorMsg = "Inserte Contraseña";
                }
            }
        
        RequestDispatcher rd;
                
        if (!error && user != null && user.getPassword().equals(strpwd)) {
            session.setAttribute("usuario", user);
            session.setAttribute("listaUsuario", lista);
            rd = request.getRequestDispatcher("inicio.jsp");                  
        } else if (!error) {
            error = true;
            errorMsg = "Email o Contraseña invalido";
            request.setAttribute("error", error);
            request.setAttribute("errorMsg", errorMsg);
            rd = request.getRequestDispatcher("login.jsp");
        } else {
            request.setAttribute("error", error);
            request.setAttribute("errorMsg", errorMsg);
            rd = request.getRequestDispatcher("login.jsp");
        }
        
        rd.forward(request, response); 
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
