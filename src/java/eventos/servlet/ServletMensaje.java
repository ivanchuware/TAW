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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
@WebServlet(name = "ServletMensaje", urlPatterns = {"/ServletMensaje"})
public class ServletMensaje extends HttpServlet {
    
    @EJB
    private UsuarioFacade usuarioFacade;
    
    @EJB
    private MensajeFacade mensajeFacade;
    
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
        Usuario user = (Usuario) session.getAttribute("usuario");
        String msg = request.getParameter("msg");
        String conver = request.getParameter("conver");
        Conversacion conversacion = conversacionFacade.find(new Integer (conver));
        
        Mensaje mensaje = new Mensaje();
        Date date = new Date();
        String hora = new SimpleDateFormat("HH").format(date);
        String minuto = new SimpleDateFormat("mm").format(date);
        mensaje.setFecha(date);
        mensaje.setHora(new Integer(hora));
        mensaje.setMinuto(new Integer(minuto));
        mensaje.setIdConversacion(conversacion);
        mensaje.setIdUsuario(user);
        mensaje.setMensaje(msg);
        
        mensajeFacade.create(mensaje);
        List<Mensaje> lista = conversacion.getMensajeList();
        lista.add(mensaje);
        conversacion.setMensajeList(lista);
        Usuario user1 = conversacion.getIdUsuario1();
        Usuario user2 = conversacion.getIdUsuario2();
        List<Conversacion> lista1 = user1.getConversacionList();
        List<Conversacion> lista2 = user2.getConversacionList1();
        
        for (int i = 0; i <lista1.size(); i++)
        {
            if (conversacion.equals(lista1.get(i)))
            {
                lista1.set(i, conversacion);
            }
        }
        
        for (int j = 0; j <lista2.size(); j++)
        {
            if (conversacion.equals(lista2.get(j)))
            {
                lista2.set(j, conversacion);
            }
        }
        user1.setConversacionList(lista1);
        user2.setConversacionList(lista2);
        
        usuarioFacade.edit(user1);
        usuarioFacade.edit(user2);
        conversacionFacade.edit(conversacion);
        
        request.setAttribute("conversacion", conversacion);
        
        RequestDispatcher rd = request.getRequestDispatcher("conversacion.jsp");
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
