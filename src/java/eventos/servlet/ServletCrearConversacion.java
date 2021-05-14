/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos.servlet;

import eventos.dao.ConversacionFacade;
import eventos.dao.UsuarioFacade;
import eventos.entity.Conversacion;
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
@WebServlet(name = "ServletCrearConversacion", urlPatterns = {"/ServletCrearConversacion"})
public class ServletCrearConversacion extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;
    
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
        
         HttpSession session = request.getSession();
        Usuario uses = (Usuario) session.getAttribute("usuario");
            String strid = request.getParameter("id");
            Usuario usid = usuarioFacade.find(new Integer (strid));
            Boolean encontrado = false;
            Conversacion found = new Conversacion();
            
            List<Conversacion> lista = conversacionFacade.findAll();
            for (Conversacion c : lista)
            {
                if ((c.getIdUsuario1().getIdUsuario() == uses.getIdUsuario() 
                 && c.getIdUsuario2().getIdUsuario() == usid.getIdUsuario())
                 || (c.getIdUsuario1().getIdUsuario() == usid.getIdUsuario() 
                 && c.getIdUsuario2().getIdUsuario() == uses.getIdUsuario()))
                {
                    encontrado = true;
                    found = c;
                    
                }
            }
            if (!encontrado)
            {
            Conversacion conv = new Conversacion();
            if (uses.getRol().getIdRol()==4)
            {
                conv.setIdUsuario1(uses);
                conv.setIdUsuario2(usid);
                List<Conversacion> list1 = uses.getConversacionList();
                List<Conversacion> list2 = usid.getConversacionList1();
                list1.add(conv);
                list2.add(conv);
                uses.setConversacionList(list1);
                usid.setConversacionList1(list2);
            }
            else {
                conv.setIdUsuario1(usid);
                conv.setIdUsuario2(uses);
                List<Conversacion> list1 = usid.getConversacionList();
                List<Conversacion> list2 = uses.getConversacionList1();
                list1.add(conv);
                list2.add(conv);
                usid.setConversacionList(list1);
                uses.setConversacionList1(list2);
            }
            conversacionFacade.create(conv);
            usuarioFacade.edit(uses);
            usuarioFacade.edit(usid);
            request.setAttribute("conversacion", conv);
            
            RequestDispatcher rd = request.getRequestDispatcher("conversacion.jsp");
            rd.forward(request, response);
            }
            else {
                request.setAttribute("conversacion", found);
            
            RequestDispatcher rd = request.getRequestDispatcher("conversacion.jsp");
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
