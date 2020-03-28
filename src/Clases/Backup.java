/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author usuario
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
public class Backup {
    private int BUFFER = 10485760;  
    //para guardar en memmoria
    private StringBuffer temp = null;
    //para guardar el archivo SQL
    private FileWriter  archivo = null;
    private PrintWriter pw = null;
 public boolean CrearBackup(String host, String puert, String usuar, String password, String db, String ruta){
 boolean ok=false;
    try{       
        //sentencia para crear el BackUp
//         Process run = Runtime.getRuntime().exec(
//        "C:\\Archivos de programa\\MySQL\\MySQL Server 5.1\\bin\\mysqldump --host=" + host + " --port=" + puert +
//        " --user=" + usuar + " --password=" + password +
//        " --compact --complete-insert --extended-insert --skip-quote-names" +
//        " --skip-comments --skip-triggers " + db);
//         //C:\\Program Files\\MySQL\\MySQL Server 5.1\\bin\\
          String comand="cmd /c mysqldump --opt -a --skip-comments --single-transaction -h "+ConexionBD.host+" -u "+ConexionBD.usu+" -p"+ConexionBD.contraseña+" --routines=true --databases "+ConexionBD.db+" > "+ruta+"";
          System.out.println(comand);
        //se guarda en memoria el backup
          Process run=Runtime.getRuntime().exec(comand);
        InputStream in = run.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        temp = new StringBuffer();
        int count;
        char[] cbuf = new char[BUFFER];
        while ((count = br.read(cbuf, 0, BUFFER)) != -1)
            temp.append(cbuf, 0, count);
        br.close();
        in.close();        
        /* se crea y escribe el archivo SQL */
        archivo = new FileWriter(ruta);
        pw = new PrintWriter(archivo);                                                    
        pw.println(temp.toString());  
        ok=true;
        JOptionPane.showMessageDialog(null, "Se realizo la copia de Seguridad con Exito!!");
   }
    catch (Exception ex){
            ex.printStackTrace();
    } finally {
       try {           
         if (null != archivo)
        	 archivo.close();
       } catch (Exception e2) {
           e2.printStackTrace();
       }
    }   
    return ok; 
 }  


	  
}
