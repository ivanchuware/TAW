package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author EloyB
 */
@WebServlet(urlPatterns = {"/servlet/RegistroServlet"})
public class RegistroServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            
            String nombre=request.getParameter("nombre");  
            String apellido=request.getParameter("apellido");  
            String ciudad=request.getParameter("ciudad");  
            String domicilio=request.getParameter("domicilio");  
            String sexo=request.getParameter("sex");  
            String nacimiento=request.getParameter("nacimiento");
            String email=request.getParameter("nacimiento");  
            String password=request.getParameter("nacimiento");  
            
            try{  
                Class.forName("oracle.jdbc.driver.OracleDriver");  
                Connection con=DriverManager.getConnection(  
                "jdbc:derby://localhost:1527/db","system","oracle");  

                PreparedStatement ps=con.prepareStatement(  
                "insert into usuario values(?,?,?,?,?,?,?,?,?,?)");  
                
                
                ps.setString(2,nombre);  
                ps.setString(3,apellido);  
                ps.setString(4,domicilio);  
                ps.setString(5,ciudad);
                ps.setString(6,nacimiento);
                ps.setString(7,sexo);
                ps.setString(8,email);
                ps.setInt(9,1);
                ps.setString(10, password);

                int i=ps.executeUpdate();  
                if(i>0)  
                out.print("You are successfully registered...");  


                }catch (Exception e2) {System.out.println(e2);}  

                
            
           
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            out.println("<h2>Nombre : " + nombre + "</h2>");
            out.println("<h2>Fecha : " + nacimiento + "</h2>");
            out.println("<h2>Sexo : " + sexo + "</h2>");
            out.println("</body>");
            out.println("</html>");
            
            out.close();  
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
