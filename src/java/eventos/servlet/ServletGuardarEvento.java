/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos.servlet;

import eventos.dao.EventoFacade;
import eventos.entity.Evento;
import eventos.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
 * @author luilo
 */
@WebServlet(name = "ServletGuardarEvento", urlPatterns = {"/ServletGuardarEvento"})
public class ServletGuardarEvento extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario)session.getAttribute("usuario");
        
        Date fechaActual = new Date();
        Date fechaEvento = new Date();
        Date fechaReserva = new Date();
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String id = request.getParameter("id");
        String titulo=request.getParameter("titulo");  
        String descripcion=request.getParameter("descripcion");  
        String strFechaEvento=request.getParameter("fecha"); 
        String strFechaReserva=request.getParameter("fechaReserva");  
        String precio=request.getParameter("precio");  
        String aforo=request.getParameter("aforo");
        String limiteEntradas=request.getParameter("limiteEntradas");  
        String asientosFijos=request.getParameter("asientosFijos"); 
        String numFilas=request.getParameter("numFilas");  
        String asientosFila=request.getParameter("asientosFila");        
        boolean error = false;
        String errorMsg = "";
        
        if(titulo.isEmpty()){
            error = true;
            errorMsg += " Título vacío";
        }
        if(descripcion.isEmpty()){
            error = true;
            errorMsg += " Descripción vacío";
        }
        try {
            fechaEvento = df.parse(strFechaEvento);
            if(fechaEvento.before(fechaActual)){
                error = true;
                errorMsg += "La fecha del evento indicada es anterior a la fecha actual";
            }
        } catch (Exception e) {
            error = true;
        }
        
        try{
            fechaReserva = df.parse(strFechaReserva);
            if(fechaReserva.before(fechaActual)){
                error = true;
                errorMsg += "La fecha de reserva del evento indicada es anterior a la fecha actual";
            }else{
                if(fechaEvento.before(fechaReserva)){
                    error = true;
                    errorMsg += "La fecha del evento indicada es anterior a la fecha límite de reserva";
                }
            }
            
        } catch (Exception e) {
            error = true;
        }
        if(precio.isEmpty()){
            error = true;
            errorMsg += " Precio vacío";
        }
        if(aforo.isEmpty()){
            error = true;
            errorMsg += " Aforo vacío";
        }
        if(limiteEntradas.isEmpty()){
            error = true;
            errorMsg += " Límite de entradas vacío";
        }
        
        if(asientosFijos != null){ //Evento de asientos fijos asignados
            if(numFilas.isEmpty()){
                error = true;
                errorMsg += " Número de filas vacío";
            }
            if(asientosFila.isEmpty()){
                error = true;
                errorMsg += " Número de asientos por fila vacío";
            }
        }
        
        if(!error){
            Evento evento;
            if (id == null || id.length()<=0) { // Crear nuevo evento
                evento = new Evento();            
            } else { // Editar evento existente
                evento = this.eventoFacade.find(new Integer(id));
            }
            evento.setTitulo(titulo);
            evento.setDescripcion(descripcion);
            evento.setFecha(fechaEvento);
            evento.setFechares(fechaReserva);
            evento.setCoste(Integer.parseInt(precio));
            evento.setAforo(Integer.parseInt(aforo));
            evento.setEntradas(Integer.parseInt(limiteEntradas));
            char fijos;
            if(asientosFijos==null){
                fijos = 'N';
                evento.setNumfilas(null);
                evento.setNumasientosporfila(null);
            }else{
                fijos = 'S';
                evento.setNumfilas(Integer.parseInt(numFilas));
                evento.setNumasientosporfila(Integer.parseInt(asientosFila));
            }
            evento.setAsientosfijos(fijos);
            if (id == null  || id.length()<=0) { // Crear nuevo evento
                evento.setIdCreador(usuario);    
                this.eventoFacade.create(evento);
            } else { // Editar evento existente
                this.eventoFacade.edit(evento);
            }
            List<Evento> listaEventos;
            listaEventos = this.eventoFacade.findByIdCreador(usuario);
            session.setAttribute("listaEventos", listaEventos);
            
            //response.sendRedirect("inicio.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("inicio.jsp");
            rd.forward(request, response);
        }else{
            request.setAttribute("error", error);
            request.setAttribute("errorMsg", errorMsg);
            RequestDispatcher rd = request.getRequestDispatcher("crearEvento.jsp");
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
