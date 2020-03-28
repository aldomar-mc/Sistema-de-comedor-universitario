/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

/**
 *
 * @author Diana
 */
import javax.swing.*;
import java.sql.*;
public class ClsCodificador {
 //****************************DECLARACION DE ATRIBUTOS*************************
 Clscontrolador Ctld=new Clscontrolador(); int c=0;
 //****************************FIN DECLARACION DE ATRIBUTOS*********************

 //************************IMPLEMENTACION DE METODOS****************************
 public String CapturaIniciales(String txt){
    String cd=""+txt.charAt(0);int i;String a="";
    for(i=0;i<txt.length();i++){
        if(txt.charAt(i)==' '){
            cd=cd+txt.charAt(i+1);
        }
    }
    return cd.toUpperCase();
}
 public boolean VerificaNorepite(String tabla, String nom, String cod){
    boolean b=false;//Ctld.cn.Conectar();
    try{String sq="select * from "+tabla+" where "+nom+"='"+cod+"';";
        Ctld.cn.st =(Statement)Ctld.cn.conec.createStatement();
        Ctld.cn.rt=Ctld.cn.st.executeQuery(sq);
        b=Ctld.cn.rt.next();
    }
    catch(Exception e){
        System.out.println(e.getMessage());
    }
    return b;
}
 public String CodigoValido(String tabla, String nom,String vlr){
    String codi=CapturaIniciales(vlr);boolean ba=false;
    while(ba==false){
     if(VerificaNorepite(tabla,nom,codi)==true){
      c++;codi=CapturaIniciales(vlr)+"_"+Integer.toString(c);ba=false;
     }
     else
       ba=true;
    }    
    return codi.toUpperCase();
}
 public int CodigoValido1(String tabla, String nom,String vlr){
    int codi=1;
    boolean ba=false;
    while(ba==false){
     if(VerificaNorepite(tabla,nom,Integer.toString(codi))==true){
      c++;
      codi=c;
      ba=false;
     }
     else
       ba=true;
    }
    return codi;
}
 //************************FIN IMPLEMENTACION DE METODOS************************
}
