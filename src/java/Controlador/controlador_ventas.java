/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.Ventas_Detalle;
import Modelo.Ventas;
/**
 *
 * @author joseg
 */
public class controlador_ventas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Ventas_Detalle ventas_detalle;
    Ventas venta;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controlador_ventas</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet controlador_ventas at " + request.getContextPath() + "</h1>");
            //double precio=ventas_detalle.precio_unitario(Integer.valueOf(request.getParameter("drop_marcas")));
            ventas_detalle = new Ventas_Detalle(Integer.valueOf(request.getParameter("txt_id_venta_detalle")), Integer.valueOf(request.getParameter("drop_producto")), Integer.valueOf(request.getParameter("txt_cantidad")), Double.valueOf(request.getParameter("txt_precio_unitario")));
            venta = new Ventas(Integer.valueOf(request.getParameter("txt_numero_factura")), Integer.valueOf(request.getParameter("drop_cliente")), Integer.valueOf(request.getParameter("drop_empleado")), request.getParameter("txt_serie"), request.getParameter("txt_fecha_facturacion"));
            if("agregar_venta".equals(request.getParameter("btn_agregar_venta")))
            { //PARA AGREGAR VARIOS REGISTROS SOLO EN LA TABLA VENTAS_DETALLE HACER QUE LAS FUNCIONES DE INGRESAR RECIBAN UN VALOR, Y DEPENDIENDO EL VALOR SE INGRESARÁ O NO
                if(ventas_detalle.reducir_cantidad()>0)
                    {
                        if (venta.agregar()>0)
                        {
                            ventas_detalle.agregar();

                            response.sendRedirect("Maestro_ventas.jsp");
                        }
                        else
                        {
                            String imagen ="https://c.tenor.com/AATQJ1cM-IgAAAAC/cheems-doge.gif";
                            out.println("<p>Error, no se pudo realizar la venta :c</p>");
                            //response.sendRedirect("Tablas\\producto.jsp");
                            out.println("<img src="+imagen);
                        }
                        
                    }
                    else
                    {
                        String imagen ="https://c.tenor.com/2CoAwNOjrDYAAAAC/aqua-konosuba.gif";
                        out.println("<p>Error, ya no queda el suficiente producto para hacer esta venta</p>");
                        out.println("<img src="+imagen);
                    }
                
            }
            if("modificar_venta".equals(request.getParameter("btn_modificar_venta")))
            {
                if(ventas_detalle.modificando_cantidad()>0)
                {
                    if (ventas_detalle.modificar()>0)
                    {
                        venta.modificar(Integer.valueOf(request.getParameter("txt_id_venta_detalle")));
                        response.sendRedirect("Maestro_ventas.jsp");
                    }
                    else
                    {
                        //String imagen ="https://i.blogs.es/4329e4/error500/450_1000.png";
                        out.println("<p>Error en el modificar en general</p>");
                        //response.sendRedirect("Tablas\\producto.jsp");
                        //out.println("<img src="+imagen);
                    }
                }
                else
                {
                    String imagen ="https://c.tenor.com/2CoAwNOjrDYAAAAC/aqua-konosuba.gif";
                        out.println("<p>Error, hubo un error al modificar la cantidad</p>");
                        out.println("<img src="+imagen);
                }
            }
            if("eliminar_venta".equals(request.getParameter("btn_eliminar_venta")))
            {
                int cantidad_de_ventas= ventas_detalle.cantidad_de_ventasdetalle();
                ventas_detalle.modificacion_antiguoidprod(cantidad_de_ventas);
                int idven=venta.id_venta(Integer.valueOf(request.getParameter("txt_id_venta_detalle")));
                if (ventas_detalle.eliminar()>0)
                {
                    //int despues=venta.id_venta(Integer.valueOf(request.getParameter("txt_id_venta_detalle")));
                    //if(despues==0)
                    //{
                        venta.eliminar(idven);
                    //}
                    response.sendRedirect("Maestro_ventas.jsp");
                }
                else
                {
                    //String imagen ="https://i.blogs.es/4329e4/error500/450_1000.png";
                    out.println("<p>Error</p>");
                    //response.sendRedirect("Tablas\\producto.jsp");
                    //out.println("<img src="+imagen);
                }
            }
            
            out.println("</body>");
            out.println("</html>");
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
