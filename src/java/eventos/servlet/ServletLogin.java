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
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

            String stremail = request.getParameter("correo");
            String strpwd = request.getParameter("contrasena");
            Usuario user = usuarioFacade.findByEmail(stremail);
            
            Boolean error = false;
            String errorMsg = "";
            
            if (stremail == null || stremail == "")
            {
                error = true;
                errorMsg = "Inserte Email";
                
            }
            if (strpwd == null || strpwd == "")
            {
                if (error){
                    errorMsg += " y Contraseña";
                } else {
                    error = true;
                    errorMsg = "Inserte Contraseña";
                }
            }
            if (user != null && strpwd.equals(user.getPassword()))
            {
                if(user.getRol().getIdRol()==1){//Creador de eventos
                    request.setAttribute("usuario", user);
                    RequestDispatcher rd = request.getRequestDispatcher("inicioCreador.jsp");
                    rd.forward(request, response);
                }else{
                    request.setAttribute("usuario", user);
                    RequestDispatcher rd = request.getRequestDispatcher("inicio.jsp");
                    rd.forward(request, response);
                }
            }
        

        if (!error && user != null && strpwd.equals(user.getPassword())) {
            if (user.getRol().getIdRol() == 1) {//Creador de eventos
                request.setAttribute("usuario", user);
                RequestDispatcher rd = request.getRequestDispatcher("inicioCreador.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("usuario", user);
                RequestDispatcher rd = request.getRequestDispatcher("inicio.jsp");
                rd.forward(request, response);
            }
        } else if (!error) {
            error = true;
            errorMsg = "Email o Contraseña invalido";
            request.setAttribute("error", error);
            request.setAttribute("errorMsg", errorMsg);
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("error", error);
            request.setAttribute("errorMsg", errorMsg);
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
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
