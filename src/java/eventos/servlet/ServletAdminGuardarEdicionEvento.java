/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos.servlet;

import eventos.dao.EventoFacade;
import eventos.dao.UsuarioFacade;
import eventos.entity.Evento;
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
@WebServlet(name = "ServletAdminGuardarEdicionEvento", urlPatterns = {"/ServletAdminGuardarEdicionEvento"})
public class ServletAdminGuardarEdicionEvento extends HttpServlet {

    @EJB
    private EventoFacade eventoFacade;
    
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
        response.setContentType("text/html;charset=UTF-8");
        String str = request.getParameter("idEvento");
        Evento evento = this.eventoFacade.find(new Integer(str));
        String titulo = request.getParameter("titulo");
        String fe = request.getParameter("date");
        String feReq = request.getParameter("fechaRes");
        String coste = request.getParameter("coste");
        String asiFijo = request.getParameter("asientos");
        String asiFijoDefault = request.getParameter("asi");
        String aforo = request.getParameter("aforo");
        String entradas = request.getParameter("entradas");
        String numfilas = request.getParameter("nfilas");
        String asifil = request.getParameter("asifil");
        String idcre = request.getParameter("idcre");
        String desc = request.getParameter("desc");
        Usuario u = this.usuarioFacade.find(new Integer(idcre));
        
        if(asiFijo != null){
            evento.setAsientosfijos(asiFijo.charAt(0));
        } else{
            evento.setAsientosfijos(asiFijoDefault.charAt(0));
        }
        
        Date fecha = new Date();
        Date fecha2 = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fecha = df.parse(fe);
        } catch (ParseException ex) {
            
        }
        
        try {
            fecha2 = df.parse(feReq);
        } catch (ParseException ex) {
            
        }
        evento.setTitulo(titulo);
        evento.setCoste(new Integer(coste));
        evento.setNumfilas(new Integer(numfilas));
        evento.setNumasientosporfila(new Integer(asifil));
        evento.setDescripcion(desc);
        evento.setIdCreador(u);
        evento.setEntradas(new Integer(entradas));
        evento.setAforo(new Integer(aforo));
        evento.setFecha(fecha);
        evento.setFechares(fecha2);
        this.eventoFacade.edit(evento);
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
