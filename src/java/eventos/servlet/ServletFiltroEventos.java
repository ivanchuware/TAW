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
@WebServlet(name = "ServletFiltroEventos", urlPatterns = {"/ServletFiltroEventos"})
public class ServletFiltroEventos extends HttpServlet {

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
        String strDes = request.getParameter("des");
        HttpSession session = request.getSession();
        Usuario user = (Usuario)session.getAttribute("usuario"); 
        List<Evento> listaEventos=null;
        if(strDes!=null && strDes.equals("1")){//Deshacer filtro
            listaEventos = this.eventoFacade.findByIdCreador(user);
        }else{//Aplicar filtro
            String filtroTitulo = request.getParameter("filtroTitulo");
            String filtroDescripcion = request.getParameter("filtroDescripcion");
            String filtroPrecioMin = request.getParameter("filtroPrecioMin");
            String filtroPrecioMax = request.getParameter("filtroPrecioMax");
            request.setAttribute("filtroTitulo", filtroTitulo);
            request.setAttribute("filtroDescripcion", filtroDescripcion);
            request.setAttribute("filtroPrecioMin", filtroPrecioMin);
            request.setAttribute("filtroPrecioMax", filtroPrecioMax);
            if( (filtroTitulo!=null && filtroTitulo.length()>0) || (filtroDescripcion!=null && filtroDescripcion.length()>0) || 
                    (filtroPrecioMin!=null && filtroPrecioMin.length()>0) || (filtroPrecioMax!=null && filtroPrecioMax.length()>0) ){
                if(filtroPrecioMin!=null && filtroPrecioMax!=null && filtroPrecioMin.length()>0 && filtroPrecioMax.length()>0 &&
                        Integer.parseInt(filtroPrecioMin) > Integer.parseInt(filtroPrecioMax)){
                    boolean error = true;
                    request.setAttribute("error", error);
                    listaEventos = this.eventoFacade.findByIdCreador(user);
                }else{
                    listaEventos = this.eventoFacade.findByFiltro(user, filtroTitulo, filtroDescripcion, filtroPrecioMin, filtroPrecioMax);
                }
                
            }else{
                listaEventos = this.eventoFacade.findByIdCreador(user);
            }
        }
        session.setAttribute("listaEventos", listaEventos);
        RequestDispatcher rd = request.getRequestDispatcher("inicio.jsp");
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
