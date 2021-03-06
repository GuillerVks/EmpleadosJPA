/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.persistencia.servlets;

import com.google.common.base.Predicates;
import com.persistencia.Empleado;
import com.persistencia.Puesto;
import com.persistencia.controller.EmpleadoJpaController;
import com.persistencia.controller.PuestoJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno
 */
public class Alta extends HttpServlet {

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
         PuestoJpaController cont2 = new PuestoJpaController(null, em);
        
        String nombre = request.getParameter("TxNombre");
        double salario = 0;
        int idPuesto = 0;
        try {
            salario = Double.parseDouble(request.getParameter("TxSalario"));
            idPuesto = Integer.parseInt(request.getParameter("Puesto"));
        } catch (NumberFormatException numberFormatException) {
        }
        
        Empleado emp = new Empleado();
        emp.setNombre((nombre));
        emp.setSalario(salario);
        emp.setFechaAlta(new Date());
        Puesto p = cont2.findPuesto(idPuesto);
        emp.setIdPuesto(p);
        
        
        request.setAttribute("emple", emp);
        
         
        try {
            cont.create(emp);
        } catch (Exception ex) {
            Logger.getLogger(Alta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("index.html");
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
