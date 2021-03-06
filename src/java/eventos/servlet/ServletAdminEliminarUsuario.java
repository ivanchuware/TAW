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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aaron
 */
@WebServlet(name = "ServletAdminEliminarUsuario", urlPatterns = {"/ServletAdminEliminarUsuario"})
public class ServletAdminEliminarUsuario extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;
    
    @EJB
    private ConversacionFacade conversacionFacade;
    
    @EJB
    private MensajeFacade mensajesFacade;
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
        String str = request.getParameter("idusuario");
        Usuario u = this.usuarioFacade.find(new Integer(str));
        List<Conversacion> lista = this.conversacionFacade.findAll();
        if(lista.size() > 0){
            for(Conversacion c : lista){
                if(c.getIdUsuario2().getIdUsuario() == u.getIdUsuario() || c.getIdUsuario1().getIdUsuario() == u.getIdUsuario()){
                    List<Mensaje> listaMensajes = c.getMensajeList();
                    for(Mensaje m : listaMensajes){
                        this.mensajesFacade.remove(m);
                    }
                    this.conversacionFacade.remove(c);

                }
            }
        }
        this.usuarioFacade.remove(u);
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
