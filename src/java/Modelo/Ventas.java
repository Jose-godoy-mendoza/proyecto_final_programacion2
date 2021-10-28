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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joseg
 */
public class Ventas {
    private int nofactura,idcliente,idempleado;
    private String serie,fechafactura;
    Conexion cn;
    
    public Ventas(){}
    
    public Ventas(int nofactura, int idcliente, int idempleado, String serie, String fechafactura) {
        this.nofactura = nofactura;
        this.idcliente = idcliente;
        this.idempleado = idempleado;
        this.serie = serie;
        this.fechafactura = fechafactura;
    }

    public int getNofactura() {
        return nofactura;
    }

    public void setNofactura(int nofactura) {
        this.nofactura = nofactura;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getFechafactura() {
        return fechafactura;
    }

    public void setFechafactura(String fechafactura) {
        this.fechafactura = fechafactura;
    }
    
    public int id_venta(int id_ventadetalle)
    {
        int id=0;
        try
        {
            cn=new Conexion();
            String consulta="select idventa from ventas_detalle where idventa_detalle = ?";
            cn.abrir_conexion();
            PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
            parametro.setInt(1, id_ventadetalle);
            
            ResultSet peticion=parametro.executeQuery();
            while(peticion.next())
            {
                id=peticion.getInt(1);
            }
            cn.cerrar_conexion();
        }catch(Exception ex)
        {
            
        }
        return id;
    }
    
    public int agregar()
    {
        int retornar=0;
        try
        {
            String fecha= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            cn=new Conexion();
            String agregar="insert into ventas (nofactura, serie, fechafactura, idcliente, idempleado, fechaingreso) VALUES (?,?,?,?,?,?)";
            cn.abrir_conexion();
            PreparedStatement parametro=(PreparedStatement) cn.conexionBD.prepareStatement(agregar);
            parametro.setInt(1, getNofactura());
            parametro.setString(2, getSerie());
            parametro.setString(3, getFechafactura());
            parametro.setInt(4, getIdcliente());
            parametro.setInt(5, getIdempleado());
            parametro.setString(6, fecha);
            retornar=parametro.executeUpdate();
            
            cn.cerrar_conexion();
        }catch(Exception ex)
        {
            
        }
        
        return retornar;
    }
    
    public int modificar(int idventa)
    {
        int retornar=0;
        int id_venta_modificar=id_venta(idventa);
        try
        {
            String fecha= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            cn=new Conexion();
            String agregar="update ventas set nofactura=?, serie=?, fechafactura=?, idcliente=?, idempleado=?, fechaingreso=? where idventa=?";
            cn.abrir_conexion();
            PreparedStatement parametro=(PreparedStatement) cn.conexionBD.prepareStatement(agregar);
            parametro.setInt(1, getNofactura());
            parametro.setString(2, getSerie());
            parametro.setString(3, getFechafactura());
            parametro.setInt(4, getIdcliente());
            parametro.setInt(5, getIdempleado());
            parametro.setString(6, fecha);
            parametro.setInt(7, id_venta_modificar);
            retornar=parametro.executeUpdate();
            
            cn.cerrar_conexion();
        }catch(Exception ex)
        {
            
        }
        
        return retornar;
    }
    public int eliminar(int id)
    {
        int devolver=0;
        try
        {
            PreparedStatement parametro;
            String codigo_sql="delete from db_punto_venta.ventas where idventa=?";
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
}
