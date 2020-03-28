
package Clases;import java.io.BufferedReader;
import java.io.File;import java.io.FileReader;import java.sql.*;
import javax.swing.JOptionPane;
public class ConexionBD {
//***************ATRIBUTOS**************
 public PreparedStatement prest=null; public  CallableStatement cllst=null;
 public static Connection conec=null; public  Connection conec1=null;
 public Statement st=null,st1=null; public  ResultSet rt=null,rt1=null;
 public static String usu,contraseña,host,db;  
 
 //***************FIN ATRIBUTOS**************
 //**************METODOS**************
// public ConexionBD(){
//  Conectar();    
// }         
 public ConexionBD(String ubd,String pbd){
  ConectarDinamyc(ubd, pbd,"localhost","bdsiga");    
 }
 public ConexionBD(){
  //LeerIP(); 
  Conectar(); //   din dns minombre:3306   
 }
 public void Conectar(){  
  try{
   usu="root";contraseña="aldomar";
   host="localhost";      
   db="baseicecomputec4prueba"; String url="jdbc:mysql://"+host+"/"+db; 
   Class.forName("com.mysql.jdbc.Driver").newInstance();
   conec=DriverManager.getConnection(url, usu, contraseña);
   System.out.println(host+"  -- "+db);
  }
  catch(Exception e){
   e.printStackTrace();
  }
}
 public void LeerIP(){
      File archivo = null; FileReader fr = null;
      BufferedReader br = null;
      String linea;
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("d:\\Configuracion\\prueba.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr); 
         // Lectura del fichero         
         while((linea=br.readLine())!=null)
           // System.out.println(linea);
           host=linea;  
//         JOptionPane.showMessageDialog(null, host);
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }      
    }
// public void Conectar(){     
//  try{
//   //usu="root";host="localhost";contraseña="123";db="baseicecomputec3"; anterior el 2210
//   usu="root";host="localhost";contraseña="123";db="baseicecomputec4";      
//   //usu="root";host="190.235.10.122";contraseña="123";db="baseicecomputec4";    
//   String url="jdbc:mysql://"+host+"/"+db;
//   Class.forName("com.mysql.jdbc.Driver").newInstance();
//   conec=DriverManager.getConnection(url, usu, contraseña);
//  }
//  catch(Exception e){
//   e.printStackTrace();
//  }
//}
 public void ConectarDinamyc(String usbd,String clvbd,String hs,String bs){
  try{
   usu=usbd;contraseña=clvbd;host=hs;
   db=bs; String url="jdbc:mysql://"+host+"/"+db; 
   Class.forName("com.mysql.jdbc.Driver").newInstance();
   conec=DriverManager.getConnection(url, usu, contraseña);
  }
  catch(Exception e){
   e.printStackTrace();
  }
}
//***************FIN METODOS**************
}
