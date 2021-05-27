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
@WebServlet(name = "ServletAdminCrearEvento", urlPatterns = {"/ServletAdminCrearEvento"})
public class ServletAdminCrearEvento extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;
    
    @EJB
    private EventoFacade eventoFacade;
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
        String titulo = request.getParameter("titulo");
        String fe = request.getParameter("date");
        String feReq = request.getParameter("fechaRes");
        String coste = request.getParameter("coste");
        String asiFijo = request.getParameter("asientos");
        String aforo = request.getParameter("aforo");
        String entradas = request.getParameter("entradas");
        String numfilas = request.getParameter("nfilas");
        String asifil = request.getParameter("asifil");
        String idcre = request.getParameter("idcre");
        String desc = request.getParameter("desc");
        boolean error = false;
        String errorMsg = "";
        int aux = 0;
        int aux2 = 0;
         if(titulo.isEmpty()){
            error = true;
            errorMsg += "Titulo no especificado ";
        }       
        if(fe.isEmpty()){
            error = true;
            errorMsg += "Fecha de evento no especificada ";
        }
        
        if(feReq.isEmpty()){
            error = true;
            errorMsg += "Fecha limite de venta de entradas no especificada ";
        }
        
        if(coste.isEmpty()){
            error = true;
            errorMsg += "Coste no especificado ";            
        }
            
        
        if(entradas.isEmpty()){
            error = true;
            errorMsg += "Entradas no especificadas ";            
        }

        if(aforo.isEmpty()){
            error = true;
            errorMsg += "Aforo no especificado ";            
        }

        if(idcre.isEmpty()){
            error = true;
            errorMsg += "ID del crador no especificado ";            
        }        
        
        if(desc.isEmpty()){
            error = true;
            errorMsg += "Descripcion no especificada ";            
        }        
        
        
        Date fecha = new Date();
        Date fecha2 = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fecha = df.parse(fe);
        } catch (ParseException ex) {
            error = true;
        }
        
        try {
            fecha2 = df.parse(feReq);
        } catch (ParseException ex) {
            error = true;
        }
        
        if(asiFijo != null){
         if(asiFijo.charAt(0) == 'N'){
            numfilas = null;
            asifil = null;
        } else{
            if(numfilas == null){
                error = true;
                errorMsg += "Numero de filas no especificado ";            
            } else{
                aux = Integer.parseInt(numfilas);
            }

            if(asifil == null){
                error = true;
                errorMsg += "Numero de asientos por fila no especificado ";            
            } else {
                aux2 = Integer.parseInt(asifil);
            }
          }           
        } else{
            error = true;
            errorMsg += "Asientos fijos no especificados ";
        }

        
        request.setAttribute("error", error);
        request.setAttribute("errorMsg", errorMsg);
        
        if(!error){
            Usuario u = this.usuarioFacade.find(new Integer(idcre));
            Evento evento = new Evento();
            evento.setTitulo(titulo);
            evento.setCoste(new Integer(coste));
            evento.setNumfilas(aux);
            evento.setNumasientosporfila(aux2);
            evento.setDescripcion(desc);
            evento.setIdCreador(u);
            evento.setAsientosfijos(asiFijo.charAt(0));
            evento.setEntradas(new Integer(entradas));
            evento.setAforo(new Integer(aforo));
            evento.setFecha(fecha);
            evento.setFechares(fecha2);
            this.eventoFacade.create(evento);
            RequestDispatcher rd = request.getRequestDispatcher("ServletAdminMostrarUsuarios");
            rd.forward(request, response);
        } else{
            RequestDispatcher rd = request.getRequestDispatcher("AdminAgregarEvento.jsp");
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
