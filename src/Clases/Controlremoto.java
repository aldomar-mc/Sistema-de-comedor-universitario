/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.*;import javax.swing.*;
/********************* @author Miguel Silva    **********************/
public class Controlremoto {
 public PreparedStatement prest=null; public  CallableStatement cllst=null;
 public static Connection conec=null; public  Connection conec1=null;
 public Statement st=null,st1=null; public  ResultSet rt=null,rt1=null;
 public static String usu,contraseña,host,db;      
 public void Conectar(){  
   try{
    usu="root";contraseña="123";host="localhost";      
    db="baseicecomputec4"; String url="jdbc:mysql://"+host+"/"+db; 
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    conec=DriverManager.getConnection(url, usu, contraseña);       
      System.out.print(host+"  --"+db);
     }
    catch(Exception e){
     e.printStackTrace();
     }
}   
public void LlenarCombo(JComboBox cbo, String Consulta, int pos) {        
 cbo.removeAllItems(); Conectar();
 try{
  st=conec.createStatement();    
  rt=st.executeQuery(Consulta);
  while(rt.next()){
   cbo.addItem(rt.getString(pos));
  }
  cbo.setSelectedIndex(-1);
 }
 catch(Exception e){
     
 }
 
//        try {    
//            while (conec.rt.next()) {
//                cbo.addItem(Base.rt.getString(pos));
//            }
//            cbo.setSelectedIndex(-1);
//        } catch (Exception e) {
//           e.printStackTrace(); 
//        }
    }
    
}
