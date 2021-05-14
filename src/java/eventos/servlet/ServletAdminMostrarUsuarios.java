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
 * @author aaron
 */
@WebServlet(name = "ServletAdminMostrarUsuarios", urlPatterns = {"/ServletAdminMostrarUsuarios"})
public class ServletAdminMostrarUsuarios extends HttpServlet {

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
        String strbusqueda = request.getParameter("busqueda");
        String strEvento = request.getParameter("busquedaEvento");
        List<Usuario> lista = this.usuarioFacade.findAll();
        List<Usuario> listaFiltrada = new ArrayList<Usuario>();
        List<Evento> listaEvento = this.eventoFacade.findAll();
        List<Evento> listaEventoFiltrada = new ArrayList<Evento>();
        
        if (strbusqueda!="" && strbusqueda!=null){
            for(Usuario u : lista){
                if(u.getNombre().toLowerCase().contains(strbusqueda.toLowerCase()) || u.getApellidos().toLowerCase().contains(strbusqueda.toLowerCase())){
                    listaFiltrada.add(u);
                }
            }
        } else {
            listaFiltrada = lista;
        }
        
        if (strEvento!="" && strEvento!=null){
            for(Evento e : listaEvento){
                if(e.getTitulo().toLowerCase().contains(strEvento.toLowerCase())){
                    listaEventoFiltrada.add(e);
                }
            }
        } else {
            listaEventoFiltrada = listaEvento;
        }
        request.setAttribute("busqueda", strbusqueda);
        request.setAttribute("lista", listaFiltrada);
        request.setAttribute("busquedaEvento", strEvento);
        request.setAttribute("listaEvento", listaEventoFiltrada);
        RequestDispatcher rd = request.getRequestDispatcher("AdminListar.jsp");
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
