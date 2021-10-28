<%-- 
    Document   : Maestro_ventas
    Created on : 26/09/2021, 18:48:35
    Author     : joseg
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.prueba"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="Modelo.Ventas_Detalle"%>
<%@page import="Modelo.Empleado"%>
<%@page import="Modelo.Cliente"%>
<%@page import="Modelo.Marcas"%>
<%@page import="java.util.HashMap"%>
<%@page import="Modelo.Producto"%>
<%@page import="Controlador.controlador"%>
<%@page import="Controlador.sr_cerrar_sesion"%>
<%@page import="javax.swing.table.DefaultTableModel"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <link href="css/estilo_menu.css" rel="stylesheet" type="text/css"/>
        <title>Maestro Venta Detalle</title>
    </head>
    <body>
        
        <div id="header">
            <ul class="nav">
                <img src="imagenes/onitech.png" alt=""/>
                <li><a href="index_inicio_principal.jsp">Inicio</a></li>
                <li><a href="index_producto.jsp">Productos</a>
                    <ul>
                        <li><a href="index_marcas.jsp">Marcas</a></li>
                    </ul>
                </li>
                <li><a href="Maestro_ventas.jsp">Ventas</a>
                    <ul>
                        <li><a href="index_cliente.jsp">Clientes</a></li>
                        <li><a href="index_empleado.jsp">Empleados</a>
                            <ul>
                                <li><a href="index_puesto.jsp">Puestos</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li><a href="Maestro_compras.jsp">Compras</a>
                    <ul>
                        <li><a href="index_prooveedores.jsp">Proveedores</a></li>
                    </ul>
                </li>
                <li><a href="">Reportes</a></li>
                <li >
                    <%
       response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
       if(session.getAttribute("txtUsuario")==null&&session.getAttribute("nombre")==null){
           response.sendRedirect("index.html");
       }
                    %>
                    <a > 
                        <form action="sr_cerrar_sesion" >
                            <input class="cerrar_sesion" type="submit"  value="Cerrar Sesion">
                        </form>
                    </a>

                </li>
            </ul>
        </div>
        
        <div class="container">
            <form action="controlador_ventas" method="post" class="form-group" >
                <h1>Formulario de ventas</h1>
                
                <%
                    Ventas_Detalle venta_d=  new Ventas_Detalle();
                    int idventa=0, idventa2=0;
                    idventa2 =venta_d.idventamax();
                    idventa=venta_d.idventamax()+1;
                    String id_venta=String.valueOf(idventa);
                    String id_venta2=String.valueOf(idventa2);
                %>
                
                <div class="row">
                        <div class="row g-3">
                            <div class="col-md-2">
                                <label form="lbl_id">ID de la venta</label>
                                <input type="text" name="txt_id_venta_detalle" id="txt_id_venta_detalle" class="form-control" value="<%= id_venta %>" readonly=""> 
                                
                            </div>
                            <div class="col-md-2">
                              <label for="lbl_serie">Numero de Factura</label>
                              <input type="text" class="form-control" name="txt_numero_factura" id="txt_numero_factura" value="<%= id_venta %>" placeholder="Numero de la factura" aria-label="numero de la factura" readonly="">
                            </div>
                            <div class="col-md-2">
                              <label for="lbl_serie">Serie</label>
                              <input type="text" class="form-control" name="txt_serie" id="txt_serie" placeholder="serie de la factura" value="A" aria-label="serie de la factura" readonly="">
                            </div>
                            <div class="col-md-4">
                              <label for="lbl_serie">Fecha de Ingreso</label>
                              <input type="text" class="form-control" name="txt_fecha_ingreso" id="txt_fecha_ingreso" placeholder="Fecha de ingreso" aria-label="Fecha de Ingreso" readonly="">
                            </div>  
                        </div>
                </div>
                
                <div class="row">
                        <div class="row g-3">
                            <div class="col-md-4">
                              <label for="lbl_producto"><b>Producto</b></label>
                                <select name="drop_producto" id="drop_producto" class="form-select">
                                <%
                                    Producto producto = new Producto();
                                    HashMap<String,String> mostrar= producto.mostrar_producto();
                                    for(String i:mostrar.keySet())
                                    {
                                        out.println("<option value="+i+">"+mostrar.get(i)+ "</option>");
                                    }
                                %>
                              </select>
                          </div>
                            <div class="col-md-2">
                              <label for="lbl_cantidad"><b>Cantidad</b></label>
                              <input type="number" class="form-control" name="txt_cantidad" id="txt_cantidad">
                          </div>
                              
                              <%
                                    double precio_unitario=1;
                                    String precio_producto;
                                    int id_producto=5;//Integer.valueOf(request.getParameter("drop_producto"));
                                    precio_unitario=venta_d.precio_unitario(id_producto);
                                    precio_producto=String.valueOf(precio_unitario);     
                                    /*double precio_unitario=1;
                                    String precio_producto="100";
                                    String id_producto=request.getParameter("drop_producto");
                                    precio_unitario=venta_d.prueba_precio(id_producto);
                                    precio_producto=String.valueOf(precio_unitario); */
                              %>
                          <div class="col-md-4">
                              <label for="lbl_cantidad"><b>Precio Unitario</b></label>
                              <input type="number" class="form-control" name="txt_precio_unitario" id="txt_precio_unitario" value="0" readonly>
                          </div>

                        </div>
                </div>
                    <br>
                    <div class="row">
                        <div class="row g-6">
                            <div class="col-md-6">
                                <label for="lbl_cliente"><b>Cliente</b></label>
                                <select name="drop_cliente" id="drop_cliente" class="form-select">
                                <%
                                    Cliente cliente= new Cliente();
                                    Map<String, ArrayList<String>> mostrar_cliente= cliente.mostrar_c();
                                    for(String i:mostrar_cliente.keySet())
                                    {
                                        out.println("<option value="+i+">"+mostrar_cliente.get(i)+ "</option>");
                                    }
                                    
                                    /*Cliente cliente= new Cliente();
                                    HashMap<String,String> mostrar_c= cliente.mostrar_clientes();
                                    for(String i:mostrar_c.keySet())
                                    {
                                        out.println("<option value="+i+">"+mostrar_c.get(i)+ "</option>");
                                    } */
                                %>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <br>
                                    <a href="index_cliente.jsp" class="btn btn-primary">Clientes</a>
                            </div>
                        </div>
                    </div>
                              <br>   
                              
                              
                              <div class="row">
                        <div class="row g-6">
                            <div class="col-md-6">
                                <label for="lbl_empleado"><b>Empleado</b></label>
                                <select name="drop_empleado" id="drop_empleado" class="form-select">
                                <%
                                    Empleado empleado= new Empleado();
                                    HashMap<String,String> mostrar_e= empleado.mostrar_empleado();
                                    for(String i:mostrar_e.keySet())
                                    {
                                        out.println("<option value="+i+">"+mostrar_e.get(i)+ "</option>");
                                    }
                                %>
                              </select>
                            </div>
                            <div class="col-md-6">
                                <br>
                                <a href="index_empleado.jsp" class="btn btn-primary">Empleados</a>
                            </div>
                        </div>
                    </div>
                              
                              <br>   
                              
                    <div class="row">
                        <div class="row g-6">
                            <div class="col-md-6">
                                <label for="lbl_fecha_facturacion"><b>Fecha de Facturacion</b></label>
                                <input type="date" name="txt_fecha_facturacion" id="txt_fecha_facturacion" class="form-control" placeholder="Fecha en que se está facturando">
                            </div>
                        </div>
                    </div>
                    <br>

                   

                              <br> <br>
                    <div class="row">
                        <div class="row g-6">
                            <div class="d-grid gap-2 col-4 mx-auto">
                                <button name="btn_agregar_venta" id="btn_agregar_venta" class="btn btn-success" value="agregar_venta">Agregar Venta</button>
                            </div>
                                  <br>
                            <div class="d-grid gap-2 col-4 mx-auto">
                                <button name="btn_modificar_venta" id="btn_modificar_venta" class="btn btn-primary" value="modificar_venta">Modificar Venta</button>
                            </div>
                            <br>
                            <div class="d-grid gap-2 col-4 mx-auto">
                                <button name="btn_eliminar_venta" id="btn_eliminar_venta" class="btn btn-danger" value="eliminar_venta">Eliminar Venta</button>
                            </div>                            
                        </div>
                    </div>
                              <br>
                    <div class="row">
                        <div class="row g-6">
                            <div class="d-grid gap-2 col-4 mx-auto">
                            <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_empleado" onclick="limpiar()">Agregar Otro Producto</button>
                            </div>
                        </div>
                    </div>
            </form>         
            <br> <br>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>serie </th>
                        <th>No_f </th>
                        <th>fecha_f</th>
                        <th>producto </th>
                        <th>cantidad </th>
                        <th>precio_u</th>
                        <th>nombreC</th>
                        <th>ApellidoC </th>
                        <th>NIT</th>
                        <th>nombreE </th>
                        <th>ApellidoE</th>
                        <th>fechaingreso</th>
                    </tr>
                </thead>
                <tbody id="tbl_ventas_detalle">
                    <%
                    Ventas_Detalle ventas_D = new Ventas_Detalle();
                    DefaultTableModel tabla = new DefaultTableModel();
                    tabla = ventas_D.leer();
                    for (int t=0;t <tabla.getRowCount();t++){
                        out.println("<tr data-id="+tabla.getValueAt(t, 0)+" data-id_producto="+tabla.getValueAt(t, 13)+ " data-id_cliente= "+tabla.getValueAt(t, 14) +" data-id_empleado="+tabla.getValueAt(t, 15)+">");
                        out.println("<td>"+ tabla.getValueAt(t, 1)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 2)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 3)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 4)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 5)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 6)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 7)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 8)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 9)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 10)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 11)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 12)+"</td>");
                        out.println("</tr>");
                    }
                    %>
                </tbody>
            </table>
        </div>
                
        
        
        <div class="container">
            <div class="modal fade" id="modal_empleado" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">       
                        <div class="modal-body">

                            <form action="controlador_ventas2" method="post" class="form-group">
                                <label form="lbl_id_venta"><b>ID</b></label>
                                <input type="text" name="txt_id_venta" id="txt_id_venta" class="form-control" value="<%= id_venta2 %>" readonly="">

                                <div class="row">
                                    <div class="row g-3">
                                        <div class="col-md-6">
                                          <label for="lbl_producto2"><b>Producto</b></label>
                                            <select name="drop_producto2" id="drop_producto2" class="form-select">
                                            <%

                                                HashMap<String,String> mostrar2= producto.mostrar_producto();
                                                for(String i:mostrar.keySet())
                                                {
                                                    out.println("<option value="+i+">"+mostrar2.get(i)+ "</option>");
                                                }
                                            %>
                                        </select>
                                      </div>
                                        <div class="col-md-6"> 
                                            <label for="lbl_cantidad2"><b>Cantidad</b></label>
                                            <input type="number" class="form-control" name="txt_cantidad2" id="txt_cantidad2">
                                        </div>
                                    </div>
                                </div>
                                

                                <br>
                                <button name="btn_agregar_nuevo_prod" id="btn_agregar_nuevo_prod" value="Agregar_nuevo_prod" class="btn btn-primary ">Agregar</button>
                                <button type="button" class="btn btn-warning" data-dismiss="modal">Cerrar</button>
                            </form>
                                    
                        </div>
                    </div>
                                    
                </div>
            </div>
        </div>        
                
        
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        
        <script type="text/javascript">
            $("#tbl_ventas_detalle").on("click","tr td", function(evt)
            {
                var target, id, id_producto, id_cliente, id_empleado,nofact,serie,fechaingreso,producto,cantidad,precio_unitario,cliente,empleado,fechafacturacion,marca_producto;
                target=$(evt.target);
                id=target.parent().data("id");
                id_producto=target.parent().data("id_producto");
                id_cliente=target.parent().data("id_cliente");
                id_empleado=target.parent().data("id_empleado");
                serie=target.parent("tr").find("td").eq(0).html();
                nofact=target.parent("tr").find("td").eq(1).html();
                fechafacturacion=target.parent("tr").find("td").eq(2).html();
                producto=target.parent("tr").find("td").eq(3).html();
                cantidad=target.parent("tr").find("td").eq(4).html();
                precio_unitario=target.parent("tr").find("td").eq(5).html();
                //nombresc, apellidosc, nit
                cliente=target.parent("tr").find("td").eq(6).html();
                //nombres, apellidos
                empleado=target.parent("tr").find("td").eq(7).html(); //el 6 y el 7 son para nombrec y apellidoc y el 8 para el nit
                fechaingreso=target.parent("tr").find("td").eq(11).html();
                marca_producto=target.parent("tr").find("td").eq(9).html();
                
                $("#txt_id_venta_detalle").val(id); //
                $("#txt_numero_factura").val(nofact); //
                $("#txt_serie").val(serie); //
                $("#txt_fecha_ingreso").val(fechaingreso);
                $("#drop_producto").val(id_producto);
                $("#txt_cantidad").val(cantidad); //
                $("#txt_precio_unitario").val(precio_unitario); //
                $("#drop_cliente").val(id_cliente);
                $("#drop_empleado").val(id_empleado);
                $("#txt_fecha_facturacion").val(fechafacturacion); //
                //$("#drop_marcas").val(id);
                $("#txt_id_venta").val(nofact);
            });
            $("#txt_id_venta").on("click", function(evt)
            {
                
                $("#txt_id_venta").val(<%=id_venta2%>);
            });
        </script>
    </body>
</html>

