/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author usuario
 */
import java.sql.*;
import javax.swing.*;
public class ConexAlternativa {
 //***************ATRIBUTOS**************
 public  PreparedStatement prest=null; public  CallableStatement cllst=null;
 public static Connection conec=null;
 public  Connection conec1=null;
 public  Statement st=null,st1=null;
 public  ResultSet rt=null,rt1=null;
 public  String usu;
 public  String contraseña; public  String host;
 public  String db; 
 //***************FIN ATRIBUTOS**************
 //**************METODOS**************
 
 public void ConectarDinamyc(String us,String clv,String hs,String bs){
    try{
 usu=us;contraseña=clv;host=hs;
 db=bs; String url="jdbc:mysql://"+host+"/"+db;
 Class.forName("com.mysql.jdbc.Driver").newInstance();
 conec=DriverManager.getConnection(url, usu, contraseña);
    }
   catch(Exception e){
       e.printStackTrace();
 }
}    
}
