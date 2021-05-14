/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos.servlet;

import eventos.dao.ConversacionFacade;
import eventos.entity.Conversacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "ServletMenuConversaciones", urlPatterns = {"/ServletMenuConversaciones"})
public class ServletMenuConversaciones extends HttpServlet {

    @EJB
    private ConversacionFacade conversacionFacade;
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
        String strbusqueda = request.getParameter("busqueda");
        List<Conversacion> listaconv = conversacionFacade.findAll();
        List<Conversacion> listacontiene = new ArrayList<Conversacion>();
        if (strbusqueda!="" && strbusqueda!=null)
        {
            for (Conversacion conv : listaconv)
            {
                if (conv.getIdUsuario1().getNombre().contains(strbusqueda) 
                 || conv.getIdUsuario1().getApellidos().contains(strbusqueda)
                 || conv.getIdUsuario2().getNombre().contains(strbusqueda)
                 || conv.getIdUsuario2().getApellidos().contains(strbusqueda))
                {
                    listacontiene.add(conv);
                }
                    
            }
        } else 
        {
            listacontiene = listaconv;
        }
        request.setAttribute("busqueda", strbusqueda);
        request.setAttribute("listacontiene", listacontiene);
        RequestDispatcher rd = request.getRequestDispatcher("menuConversaciones.jsp");
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
