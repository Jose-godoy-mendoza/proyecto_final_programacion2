/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joseg
 */
public class Producto {
    private String producto, descripcion, imagen, fecha_ingreso;
    private int idmarca, existencia;
    private double precio_costo, precio_venta;
    Conexion cn;
    
    public Producto(){  }

    public Producto(String producto, String descripcion, String imagen, String fecha_ingreso, int idmarca, int existencia, double precio_costo, double precio_venta) {
        this.producto = producto;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.fecha_ingreso = fecha_ingreso;
        this.idmarca = idmarca;
        this.existencia = existencia;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(int idmarca) {
        this.idmarca = idmarca;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public double getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(double precio_costo) {
        this.precio_costo = precio_costo;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }
    
    public HashMap mostrar_producto()
    {
        HashMap<String, String> drop_producto=new HashMap();
        try
        {
            String codigo_sql="select idproducto as id, producto from productos";
            cn= new Conexion();
            cn.abrir_conexion();
            ResultSet consulta=cn.conexionBD.createStatement().executeQuery(codigo_sql);
            while(consulta.next())
            {
                drop_producto.put(consulta.getString("id"), consulta.getString("producto"));
            }
            
            cn.cerrar_conexion();
        }catch(Exception ex)
        {
            
        }
        
        return drop_producto;
    }
    
    
    public int eliminar(int id)
    {
        int devolver=0;
        try
        {
            PreparedStatement parametro;
            String codigo_sql="delete from db_punto_venta.productos where idproducto=?";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro=(PreparedStatement) cn.conexionBD.prepareStatement(codigo_sql);
            parametro.setInt(1, id);
            
            devolver=parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(HeadlessException | SQLException ex)
        {
            System.out.println("error........"+ex.getMessage());
        }
        
        return devolver;
    } 
    
    
   public int modificar(int id)
    {
        int devolver=0;
        try
        {
            String fecha= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            PreparedStatement parametro;
            String codigo_sql="update db_punto_venta.productos set producto=?, descripcion=?, imagen=?, fecha_ingreso=?, idmarca=?, existencia=?, precio_costo=?, precio_venta=? where idproducto=?";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro=(PreparedStatement) cn.conexionBD.prepareStatement(codigo_sql);
            parametro.setString(1, getProducto());
            parametro.setString(2, getDescripcion());
            parametro.setString(3, getImagen());
            //parametro.setString(4, getFecha_ingreso());
            parametro.setString(4, fecha);
            parametro.setInt(5,    getIdmarca());
            parametro.setInt(6, getExistencia());
            parametro.setDouble(7, getPrecio_costo());
            parametro.setDouble(8, getPrecio_venta());
            parametro.setInt(9, id);
            
            devolver=parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(HeadlessException | SQLException ex)
        {
            System.out.println("error........"+ex.getMessage());
        }
        
        return devolver;
    } 
    
   
   public int agregar()
    {
        int devolver=0;
        try
        {
            String fecha= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            PreparedStatement parametro;
            String codigo_sql="Insert into db_punto_venta.productos (producto, descripcion, imagen, fecha_ingreso, idmarca, existencia, precio_costo, precio_venta) values (?,?,?,?,?,?,?,?)";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro=(PreparedStatement) cn.conexionBD.prepareStatement(codigo_sql);
            parametro.setString(1, getProducto());
            parametro.setString(2, getDescripcion());
            parametro.setString(3, getImagen());
            //parametro.setString(4, getFecha_ingreso());
            parametro.setString(4, fecha);
            parametro.setInt(5,    getIdmarca());
            parametro.setInt(6, getExistencia());
            parametro.setDouble(7, getPrecio_costo());
            parametro.setDouble(8, getPrecio_venta());
            
            devolver=parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(HeadlessException | SQLException ex)
        {
            System.out.println("error........"+ex.getMessage());
        }
        
        return devolver;
    }
    
    public DefaultTableModel leer()
    {
        DefaultTableModel tabla = new DefaultTableModel();
        try
        {
            cn = new Conexion();
            String query="Select p.idproducto as id, p.producto, p.descripcion, p.imagen, p.precio_costo, p.precio_venta, p.existencia, p.fecha_ingreso, m.marca, p.idmarca from productos as p INNER JOIN marcas as m on p.idmarca=m.idmarca ORDER BY idproducto;";
            cn.abrir_conexion();
            ResultSet consulta=cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[]={"id","producto","descripcion","Imagen","precio_costo","precio_venta","Existencias","Fecha ingreso","marca","id_marca"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[]= new String [10];
            while(consulta.next())
            {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("producto");
                datos[2] = consulta.getString("descripcion");
                datos[3] = consulta.getString("imagen");
                datos[4] = consulta.getString("precio_costo");
                datos[5] = consulta.getString("precio_venta");
                datos[6] = consulta.getString("existencia");
                datos[7] = consulta.getString("fecha_ingreso");
                datos[8] = consulta.getString("marca");
                datos[9] = consulta.getString("idmarca");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        }catch(SQLException ex)
        {
            System.out.println("Error:"+ex.getMessage());
        }
        return tabla;
    }
    
}
