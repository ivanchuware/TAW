/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos.servlet;

import eventos.dao.ConversacionFacade;
import eventos.dao.MensajeFacade;
import eventos.dao.UsuarioFacade;
import eventos.entity.Conversacion;
import eventos.entity.Mensaje;
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

/**
 *
 * @author Ivanchu
 */
@WebServlet(name = "ServletBorrarConversacion", urlPatterns = {"/ServletBorrarConversacion"})
public class ServletBorrarConversacion extends HttpServlet {

    @EJB
    private ConversacionFacade conversacionFacade;
    
    @EJB
    private MensajeFacade mensajeFacade;
    
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
        
        String strid = request.getParameter("id");
        Conversacion c = conversacionFacade.find(new Integer (strid));
        
         List<Mensaje> listaMensajes = c.getMensajeList();
                    for(Mensaje m : listaMensajes){
                        this.mensajeFacade.remove(m);
                    }
                    Usuario user1 = c.getIdUsuario1();
                    Usuario user2 = c.getIdUsuario2();
                    
                    List<Conversacion> list1 = user1.getConversacionList();
                    List<Conversacion> list2 = user2.getConversacionList1();
                    
                    list1.remove(c);
                    list2.remove(c);
                    
                    user1.setConversacionList1(list1);
                    user2.setConversacionList(list2);
                    
                    this.usuarioFacade.edit(user1);
                    this.usuarioFacade.edit(user2);
                    
                    this.conversacionFacade.remove(c);
        
        
                    request.setAttribute("busqueda", "");
        
        RequestDispatcher rd = request.getRequestDispatcher("ServletMenuConversaciones");
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
