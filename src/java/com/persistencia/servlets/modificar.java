/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.persistencia.servlets;

import com.persistencia.Empleado;
import com.persistencia.controller.EmpleadoJpaController;
import com.persistencia.controller.exceptions.RollbackFailureException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno
 */
public class modificar extends HttpServlet {

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
        
        EntityManagerFactory em = Persistence.createEntityManagerFactory("EmpleadosJPAPU");
        EmpleadoJpaController cont = new EmpleadoJpaController(em);
        
        String idEmp = request.getParameter("idMod");
        String EdEmp = request.getParameter("EdEmp");
        
        if(idEmp != null)
        {
            //Recupero el empleado
           Empleado emp = cont.findEmpleado(Integer.parseInt(idEmp));
            if (emp != null)
            {
                // si existe relleno el formulario
                String code = "";
                try (PrintWriter out = response.getWriter()) {
                response.setContentType("text/html;charset=UTF-8");
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet modificar</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<form action=\"editar.html\" method=\"POST\">");
                code = "ID: <input type=\"text\" readonly name=\"idEmpleado\" value=";
                code += emp.getIdEmpleado();
                code += " /><br />";
                out.println(code);
                code = "Nombre: <input type=\"text\" name=\"TxNombre\" value=";
                code += emp.getNombre();
                code += " /><br />";
                out.println(code);
                code = "Salario: <input type=\"text\" name=\"TxSalario\" value=";
                code += emp.getSalario();
                code += " /><br />";
                out.println(code);
                out.println("<input type=\"submit\" name=\"EdEmp\" value=\"Editar\" />");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
                }
            }
        }
        
        if(request.getParameter("EdEmp").equals("Editar")){
            String id = request.getParameter("idEmpleado");
            String nombre = request.getParameter("TxNombre");
            String salario = request.getParameter("TxSalario");
            Empleado editar = new Empleado();
            editar.setIdEmpleado(Integer.parseInt(id));
            editar.setNombre(nombre);
            editar.setSalario(Double.parseDouble(salario));
            
            if (nombre != null && salario !=null){
                try {
                    cont.edit(editar);
                } catch (RollbackFailureException ex) {
                    Logger.getLogger(modificar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(modificar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                try (PrintWriter out = response.getWriter()) {
                out.println("ERROR! hay campos vacios");
                }
            }
            
            RequestDispatcher dsp = request.getRequestDispatcher("Listado.html");
            dsp.forward(request, response);
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
