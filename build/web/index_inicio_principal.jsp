<%-- 
    Document   : index_inicio_principal
    Created on : 20/10/2021, 20:16:05
    Author     : joseg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenido</title>
         <link href="css/Principal.css" rel="stylesheet" type="text/css"/>
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
                <li><a href="">Ventas</a>
                    <ul>
                        <li><a href="index_cliente.jsp">Clientes</a></li>
                        <li><a href="index_empleado.jsp">Empleados</a>
                            <ul>
                                <li><a href="index_puesto.jsp">Puestos</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li><a href="">Compras</a>
                    <ul>
                        <li><a href="index_prooveedores.jsp">Proveedores</a></li>
                    </ul>
                </li>
                <li><a href="">Reportes</a></li>
                  <li>
                    <%
       response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
       if(session.getAttribute("txtUsuario")==null&&session.getAttribute("nombre")==null){
           response.sendRedirect("index.html");
       }  %>
                    <a > 
                        <form action="sr_cerrar_sesion" >
                            <input class="cerrar_sesion" type="submit"  value="Cerrar Sesion">
                        </form>
                    </a>
                </li>
            </ul>
           
        </div>
   
         <div class="contenedor">
            <div class="flex22"></div>
          <img class="Imagen_pol" src="imagenes/kung1.jpg">  
          <div class="flex23">
              </div>
         </div>
       
    </body>
</html>
