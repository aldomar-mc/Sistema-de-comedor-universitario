 /** To change this template, choose Tools | Templates*and open the template in the editor.*/
 package Clases;
 /**** @author Miguel*/
 import java.awt.event.*;import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;import java.util.*;
import javax.swing.*;import javax.swing.table.*;
//import sun.util.calendar.CalendarDate;
//import sun.util.resources.CalendarData;
 public class Clscontrolador {
 /********************DECLARMACION DE ATRIBUTOS************************/
 static String[] Usuario=new String[1];static String sq="";
 static ConexionBD cn=new ConexionBD();
 static ClsCodificador Codigo=new ClsCodificador();
 static ClsVisualizador Vsz=new ClsVisualizador();
 public JOptionPane msg=new JOptionPane();
 public IMPRIMIR impresor = new IMPRIMIR();
 /********************FIN DECLARACION DE ATRIBUTOS************************/

 /********************************IMPLEMENTACION DE METODOS************************/
public void BuscarDatoDoc(DefaultTableModel md,String fac,String nom,String sentencia,int numCam){
     String[] datos=new String[numCam];int i=0;
     try{LimpiTabla(md);
         cn.prest=cn.conec.prepareCall(sentencia);
         cn.prest.setString(1,fac.trim());
         cn.prest.setString(2,nom.trim());
         cn.rt=cn.prest.executeQuery();
         while(cn.rt.next()){
            while(i<numCam){
                datos[i]=cn.rt.getString(i+1);
                i++;
            }

            md.addRow(datos);
            i=0;
         }
     }
     catch(Exception e){
         System.out.println(e.getMessage());
         JOptionPane.showMessageDialog(null, "no se hizo la conexion");
     }
}

public boolean InsertarProcCurApe(int cicl,int cred,String curs, String curri, String escu, int doce,String secci,String grup,String seme) {
        boolean b = true;
        try {
            {


                try {
                    cn.prest = cn.conec.prepareCall("{call IngCurApe(?,?,?,?,?,?,?,?,?)}");
                    cn.prest.setInt(1, cicl);
                    cn.prest.setInt(2, cred);
                    cn.prest.setString(3, curs);
                    cn.prest.setString(4, curri);
                    cn.prest.setString(5, escu);
                    cn.prest.setInt(6, doce);
                    cn.prest.setString(7, secci);
                    cn.prest.setString(8, grup);
                    cn.prest.setString(9, seme);

                    int p = cn.prest.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return b;
    }
public String Mayus(String n) {
        String text = "";
        char ch;
        int cont = 0;
        if (n.trim().length() > 0) {
            text = n.toUpperCase().charAt(0) + n.substring(1);
            for (int i = 0; i < n.length(); i++) {
                ch = n.charAt(i);
                if (cont == 1) {
                    text = n.substring(0, i) + n.toUpperCase().charAt(i) + n.substring(i + 1);
                    cont = 0;
                }
                if (ch == ' ') {
                    cont += 1;
                }
            }
        }
        return text;
    }
    public void EscMayus(JTextField txt1){
       txt1.setText(Mayus(txt1.getText()));
       this.sololetras(null);
       this.LimpiTabla(null);
    }

 public boolean InsertarProcMovAct(String mot, String Tba,
         String codAct,String codPOge,String codPFac,
         String cmpcod,JTextField fec,JTextField hor) {
     String cod="";
         String fecHor[]=new String[2];
        boolean b = true;
        try {
            cod = Codigo.CodigoValido(Tba, cmpcod, mot);
            if (Codigo.VerificaNorepite(Tba, cmpcod, mot) == false) {


                try {
                    cn.prest = cn.conec.prepareCall("{call InsertarMovActa(?,?,?,?,?)}");
                    cn.prest.setString(1, cod);
                    cn.prest.setString(2, mot);
                    cn.prest.setString(3, codAct);
                    cn.prest.setString(4, codPOge);
                    cn.prest.setString(5, codPFac);
                  
                    int p = cn.prest.executeUpdate();
                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                    
            } else {
                Vsz.Dlg.showMessageDialog(null, " Ya existe");
                b = false;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        fecHor=Vsz.obtHorFec(cod);
                    fec.setText(fecHor[0].trim());
                    hor.setText(fecHor[1].trim());
                   
        return b;
    }

 public boolean InsertarProcDevAct(String cod) {

         boolean b = true;
        try {
                 try {
                    cn.prest = cn.conec.prepareCall("{call InsertarDevActa1(?)}");
                    cn.prest.setString(1, cod);

                    int p = cn.prest.executeUpdate();

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


        } catch (Exception e) {
            e.getStackTrace();
        }

        return b;
    }


 public boolean ActualizarTblaConPFac(String cod, String fac,String car,String ape,String nom,String ema, String cel, String dni)
  {

             boolean b = false;
        try {
            cn.prest = cn.conec.prepareCall("{call ActPFac(?,?,?,?,?,?,?,?)}");
            cn.prest.setString(1, cod);
            cn.prest.setString(2, fac);
            cn.prest.setString(3, car);
            cn.prest.setString(4, ape);
            cn.prest.setString(5, nom);
            cn.prest.setString(6, ema);
            cn.prest.setString(7, cel);
            cn.prest.setString(8, dni);

            int p = cn.prest.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return b;
    }


public boolean InsertarProcPer(String ape, String Tba,String nom,String med, String ema, String dni, String cmpcod) {
        boolean b = true;
        try {
            String cod = Codigo.CodigoValido(Tba, cmpcod, ape);
            if (Codigo.VerificaNorepite(Tba, cmpcod, ape) == false) {


                try {
                    cn.prest = cn.conec.prepareCall("{call InsertarPersonal(?,?,?,?,?,?)}");
                    cn.prest.setString(1, cod);
                    cn.prest.setString(2, nom);
                    cn.prest.setString(3, ape);
                    cn.prest.setString(4, med);
                    cn.prest.setString(5, ema);
                    cn.prest.setString(6, dni);

                    int p = cn.prest.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                Vsz.Dlg.showMessageDialog(null, " Ya existe");
                b = false;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return b;
    }

public boolean InsertarProcAlmcPFac(String ape, String Tba,String fac,String car, String nom, String cmpcod) {
        boolean b = true;
        try {
            
                try {
                    cn.prest = cn.conec.prepareCall("{call InsertarPerFac1(?,?,?,?)}");
                 
                    cn.prest.setString(1, fac);
                    cn.prest.setString(2, car);
                    cn.prest.setString(3, nom);
                    cn.prest.setString(4, ape);


                    int p = cn.prest.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
             
        } catch (Exception e) {
            e.getStackTrace();
        }
        return b;
    }

public void BuscarSalFacultad(DefaultTableModel md,String fac,String nom){
     String[] datos=new String[4];int i=0;
     try{LimpiTabla(md);
         cn.prest=cn.conec.prepareCall("{call VerActa(?,?)}");
         cn.prest.setString(1,fac.trim());
         cn.prest.setString(2,nom.trim());
         cn.rt=cn.prest.executeQuery();
         while(cn.rt.next()){
            while(i<4){
                datos[i]=cn.rt.getString(i+1);
                i++;
            }

            md.addRow(datos);
            i=0;
         }
     }
     catch(Exception e){
         System.out.println(e.getMessage());
         JOptionPane.showMessageDialog(null, "no se hizo la coneccion");
     }
}

 public boolean validarfecha(String fecha){
     int p=0;boolean b=true;int año=0,mes=0,dia=0;
     while(p<fecha.length()){
         if( p!=2 && p!=5){
            if( fecha.charAt(p)<48 || fecha.charAt(p)>57 ){
                b=false; p=fecha.length();
            }
         }
         p++;
     }
     if(b){
         año=Integer.parseInt(fecha.substring(6));
         mes=Integer.parseInt(fecha.substring(3,5));
         dia=Integer.parseInt(fecha.substring(0,2));
         if(año>2000 && año<2050){
             if(mes>0 && mes<13){
                 if(año%4==0){
                     if(mes==2){
                         if(dia<1 || dia>29)
                             b=false;
                     }
                     else{
                         if(mes<8){
                             if(mes%2==0){
                                 if(dia<1 || dia>30)
                                     b=false;
                             }else{
                                 if(dia<1 || dia>31)
                                     b=false;
                             }
                         }else{
                             if(mes%2==0){
                                 if(dia<1 || dia>31)
                                     b=false;
                             }else{
                                 if(dia<1 || dia>30)
                                     b=false;
                             }
                         }
                     }
                 }

             }
             else
                 b=false;
         }
         else
             b=false;
     }
     return b;
 }
public boolean InsertarStand(String dato,String Tba,String cmpcod,String cmpnom,int piso){
  boolean b=true;
  try{
    if(Codigo.VerificaNorepite(Tba, cmpnom, dato)==false){
     String cod=Codigo.CodigoValido(Tba, cmpcod,dato);
     try{cn.prest=cn.conec.prepareCall("{call InsertStand(?,?,?)}");
         cn.prest.setString(1, cod);
         cn.prest.setString(2, dato);
         cn.prest.setInt(3, piso);
         int p=cn.prest.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    else{
     Vsz.Dlg.showMessageDialog(null,dato+" Ya existe");b=false;
    }
 }
 catch(Exception e){
  e.getStackTrace();
 }
 return b;
}

public boolean InsertarFacu(String dato,String Tba,String cmpcod,String cmpnom,String dec){
  boolean b=true;
  try{
    if(Codigo.VerificaNorepite(Tba, cmpnom, dato)==false){
     String cod=Codigo.CodigoValido(Tba, cmpcod,dato);
     try{cn.prest=cn.conec.prepareCall("{call InsertFacu(?,?,?)}");
         cn.prest.setString(1, cod);
         cn.prest.setString(2, dato);
         cn.prest.setString(3, dec);
         int p=cn.prest.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    else{
     Vsz.Dlg.showMessageDialog(null,dato+" Ya existe");b=false;
    }
 }
 catch(Exception e){
  e.getStackTrace();
 }
 return b;
}
public boolean InsertarDoc(String dato,String Tba,String cmpcod,String cmpnom,String ape,String dni){
  boolean b=true;
  try{
    if(Codigo.VerificaNorepite(Tba, cmpnom, dato)==false){
     int cod=Codigo.CodigoValido1(Tba, cmpcod,dato);
     String codd=Integer.toString(cod);
     try{cn.prest=cn.conec.prepareCall("{call InsertDoc(?,?,?,?)}");
         cn.prest.setString(1, codd);
         cn.prest.setString(2, dato);
         cn.prest.setString(3, ape);
         cn.prest.setString(4, dni);
         int p=cn.prest.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    else{
     Vsz.Dlg.showMessageDialog(null,dato+" Ya existe");b=false;
    }
 }
 catch(Exception e){
  e.getStackTrace();
 }
 return b;
}
public boolean InsertarEscue(String dato,String Tba,String cmpcod,String cmpnom,String dir,String dep){
  boolean b=true;
  try{
    if(Codigo.VerificaNorepite(Tba, cmpnom, dato)==false){
     String cod=Codigo.CodigoValido(Tba, cmpcod,dato);
     try{cn.prest=cn.conec.prepareCall("{call InsEscuela(?,?,?,?)}");
         cn.prest.setString(1, cod);
         cn.prest.setString(2, dato);
         cn.prest.setString(3, dir);
         cn.prest.setString(4, dep);
         int p=cn.prest.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    else{
     Vsz.Dlg.showMessageDialog(null,dato+" Ya existe");b=false;
    }
 }
 catch(Exception e){
  e.getStackTrace();
 }
 return b;
}

public boolean InsertarDept(String cod,String dato,String Tba,String cmpnom,String jef,String fac){
  boolean b=true;
  try{
    if(Codigo.VerificaNorepite(Tba, cmpnom, dato)==false){
     
     try{cn.prest=cn.conec.prepareCall("{call InsertDep(?,?,?,?)}");
         cn.prest.setString(1, cod);
         cn.prest.setString(2, dato);
         cn.prest.setString(3, jef);
         cn.prest.setString(4, fac);
         int p=cn.prest.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    else{
     Vsz.Dlg.showMessageDialog(null,dato+" Ya existe");b=false;
    }
 }
 catch(Exception e){
  e.getStackTrace();
 }
 return b;
}

public void sololetras(KeyEvent k) {
        char t = k.getKeyChar();
        if ((t >= 48) && (t <= 57)) {
            k.consume();
        }
    }



public boolean ActualizarDep(String cod,String dato,String jef,String fac){
 boolean b=false;
 try{cn.prest=cn.conec.prepareCall("{call ActDep(?,?,?,?)}");
         cn.prest.setString(1, cod);
         cn.prest.setString(2, dato);
         cn.prest.setString(3, jef);
         cn.prest.setString(4, fac);
         int p=cn.prest.executeUpdate();
         b=true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
 return b;
}

public void BuscarDato(DefaultTableModel md,String fac,String nom,String sentencia,int numCam){
     String[] datos=new String[4];int i=0;
     try{LimpiTabla(md);
         cn.prest=cn.conec.prepareCall(sentencia);
         cn.prest.setString(1,fac.trim());
         cn.prest.setString(2,nom.trim());
         cn.rt=cn.prest.executeQuery();
         while(cn.rt.next()){
            while(i<numCam){
                datos[i]=cn.rt.getString(i+1);
                i++;
            }
            
            md.addRow(datos);
            i=0;
         }
     }
     catch(Exception e){
         System.out.println(e.getMessage());
         JOptionPane.showMessageDialog(null, "no se hizo la coneccion");
     }
}
public void EliminaPagcnpro(String cd) {
        try {
            cn.prest = cn.conec.prepareCall("{call ElimPFac(?)}");
            cn.prest.setString(1, cd);
            int p = cn.prest.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
public boolean Eliminausu(String cdu) {
    boolean dev=true;
        try {
            cn.prest = cn.conec.prepareCall("{call ElimUsu(?)}");
            cn.prest.setString(1, cdu);
            int p = cn.prest.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            dev=false;
        }
    return dev;
    }
public boolean EliminaPCurApe(String cd) {
    boolean dev=true;
        try {
            cn.prest = cn.conec.prepareCall("{call ElimCurApe(?)}");
            cn.prest.setString(1, cd);
            int p = cn.prest.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            dev=false;
        }
    return dev;
    }
public void MostrarEnTabla(String consulta,DefaultTableModel md,int ndatos){
    String[] datos=new String[ndatos];int i=0;
    try{
        LimpiTabla(md);
        cn.Conectar();
        sq=consulta;
        cn.st=cn.conec.createStatement();
        cn.rt=cn.st.executeQuery(sq);
        while(cn.rt.next()){
            while(i<ndatos){
                datos[i]=cn.rt.getString(i+1);
                i++;
            }
            md.addRow(datos);
            i=0;
        }
        
    }catch(Exception e){
        msg.showMessageDialog(null, "Error "+e.getMessage(),"Aviso02",0);
    }
}
public boolean ActualizarEscuela(String cod,String dato,String dir,String dep){
 boolean b=false;
 try{cn.prest=cn.conec.prepareCall("{call ActEscuela(?,?,?,?)}");
         cn.prest.setString(1, cod);
         cn.prest.setString(2, dato);
         cn.prest.setString(3, dir);
         cn.prest.setString(4, dep);
         int p=cn.prest.executeUpdate();
         b=true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
 return b;
}
public boolean ActualizarStand(String cod,String dato,int piso){
 boolean b=false;
 try{cn.prest=cn.conec.prepareCall("{call ActStand(?,?,?)}");
         cn.prest.setString(1, cod);
         cn.prest.setString(2, dato);
         cn.prest.setInt(3, piso);
         int p=cn.prest.executeUpdate();
         b=true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
 return b;
}
public boolean ActualizarFacultad(String cod,String dato,String dec){
 boolean b=false;
 try{cn.prest=cn.conec.prepareCall("{call ActFacu(?,?,?)}");
         cn.prest.setString(1, cod);
         cn.prest.setString(2, dato);
         cn.prest.setString(3, dec);
         int p=cn.prest.executeUpdate();
         b=true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
 return b;
}
public boolean ActualizarDocente(String cod,String nom,String ape,String dni){
 boolean b=false;
 try{cn.prest=cn.conec.prepareCall("{call ActDoc(?,?,?,?)}");
         cn.prest.setString(1, cod);
         cn.prest.setString(2, nom);
         cn.prest.setString(3, ape);
         cn.prest.setString(4, dni);
         int p=cn.prest.executeUpdate();
         b=true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
 return b;
}
public void solonumeros(KeyEvent k) {
        char t = k.getKeyChar();
        if ((t < 48) || (t > 57)) {
            k.consume();
        }
    }
public void Solonumeros(KeyEvent e){
  if((e.getKeyChar()<48)||(e.getKeyChar()>57)){
        e.consume();
  }
 }
public void NoNuevo(JButton b,JButton b1, String cd, String cd1, KeyEvent E){
  if((b.getText().compareTo(cd)==0)&&(b1.getText().compareTo(cd1)==0)){
   E.consume();
  }
 }
public void Mayusculas(JTextField tx){
    tx.setText(tx.getText().toUpperCase());
 }
public void MarcaTxt(JTextField txt){
    txt.setSelectionStart(0);txt.setSelectionEnd(txt.getText().length());txt.requestFocus();
}
public void MarcaTxt(JFormattedTextField txt){
    txt.setSelectionStart(0);txt.setSelectionEnd(txt.getText().length());txt.requestFocus();
}
public void MarcaTxt(JPasswordField ps){
    ps.setSelectionStart(0);ps.setSelectionEnd(ps.getText().length());ps.requestFocus();
}
public void Enter(KeyEvent e, JButton btn){
        if(e.getKeyChar()==10){
            btn.doClick();
        }
}
public void Mayus(JTextField txt){
    txt.setText(txt.getText().toUpperCase());
}
public void SinEspcBlanco(KeyEvent e,JTextField t){
        if(e.getKeyChar()==32){
            if(t.getCaretPosition()==0){
                e.consume();
            }
            else{
                if(t.getCaretPosition()<t.getText().length()){
                    if((t.getText().charAt(t.getCaretPosition()-1)==' ')||(t.getText().charAt(t.getCaretPosition())==' '))
                        e.consume();
                }
                else{
                    if(t.getText().charAt(t.getCaretPosition()-1)==' ')
                        e.consume();
                }
            }
        }
    }
public void Volvercbo(JComboBox Cbo){
 if(Cbo.getSelectedIndex()==-1){
  Cbo.requestFocus();
 }
}
public void LlenaCbs(JComboBox cbu, String consulta){
  try{cn.Conectar();
      cn.st=cn.conec.createStatement();
      cn.rt=cn.st.executeQuery(consulta);
      while(cn.rt.next()){
       cbu.addItem(cn.rt.getString(2));
      }
      cbu.setSelectedIndex(-1);
  }
  catch(Exception E){
    E.printStackTrace();
  }
}
public void LlenaCbsConprocalmac(JComboBox cbu, String consulta){
  try{cn.Conectar();String sq="{call "+consulta+"()}";
      cn.st=cn.conec.createStatement();cn.rt=cn.st.executeQuery(sq);
      while(cn.rt.next())
       cbu.addItem(cn.rt.getString(1).toString());
      cbu.setSelectedIndex(-1);
     }
  catch(Exception E)
  {
   E.printStackTrace();
  }
}
public boolean VerifClv(String usu,String psw){
    boolean b=false;cn.Conectar();
    try{String sq="select* from usuario where nomusr='"+usu+"' and clvusr='"+psw+"';";
        cn.st=cn.conec.createStatement();cn.rt=cn.st.executeQuery(sq);
        b=cn.rt.next();
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
    return b;
}
public boolean VerifClv1(String codPer,String psw){
    boolean b=false;cn.Conectar();
    try{String sq="select* from personaloge where codpers='"+codPer+"' and clave='"+psw+"';";
        cn.st=cn.conec.createStatement();cn.rt=cn.st.executeQuery(sq);
        b=cn.rt.next();
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
    return b;
}
public boolean BscrExiste(String tba,String cmpo, String vlr){
  boolean b=false;
  sq="select * from "+tba+" where "+cmpo+"='"+vlr+"'";
  return b;
}
public void LimpiTabla(DefaultTableModel md){
        while(md.getRowCount()>0){
          md.removeRow(0);
    }
}
public boolean Insertar(String dato,String Tba,String cmpcod,String cmpnom){
  boolean b=true;
  try{
    if(Codigo.VerificaNorepite(Tba, cmpnom, dato)==false){
     int cod=Codigo.CodigoValido1(Tba, cmpcod,dato);
     sq="insert into "+Tba+" values('"+cod+"','"+dato+"');";
     cn.st= cn.conec.createStatement();
     int p=cn.st.executeUpdate(sq);
    }
    else{
     Vsz.Dlg.showMessageDialog(null,dato+" Ya existe");b=false;
    }
 }
 catch(Exception e){
  e.getStackTrace();
 }
 return b; 
}
public boolean InsertarProcAlmc(String dato,String Tba,String cmpcod,String cmpnom){
  boolean b=true;
  try{
    if(Codigo.VerificaNorepite(Tba, cmpnom, dato)==false){
     String cod=Codigo.CodigoValido(Tba, cmpcod,dato);
     try{cn.prest=cn.conec.prepareCall("{call InsertarCtg(?,?)}");
         cn.prest.setString(1, cod);
         cn.prest.setString(2, dato);
         int p=cn.prest.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    else{
     Vsz.Dlg.showMessageDialog(null,dato+" Ya existe");b=false;
    }
 }
 catch(Exception e){
  e.getStackTrace();
 }
 return b;
}
public void BuscarDatoProc(Mimodelo md,String proc,String dato,int ndatos){
  String[] datos=new String[ndatos]; int i=0;
     try{
         LimpiTabla(md);
         cn.Conectar();
         cn.prest=cn.conec.prepareCall("{call "+proc+"(?)}");
         cn.prest.setString(1,dato);
         cn.rt=cn.prest.executeQuery();
         while(cn.rt.next()){
            while(i<ndatos){
                datos[i]=cn.rt.getString(i+1);
                i++;
            }
            md.addRow(datos);
            i=0;
         }
     }
     catch(Exception e){
         e.printStackTrace();
     }

}
public void BuscarDatosProc(DefaultTableModel md,String proc,String dato1, String dato2,int ndatos){
  String[] datos=new String[ndatos]; int i=0;
     try{
         LimpiTabla(md);
         cn.prest=cn.conec.prepareCall("{call "+proc+"(?,?)}");
         cn.prest.setString(1,dato1);
         cn.prest.setString(2,dato2);
         cn.rt=cn.prest.executeQuery();
         while(cn.rt.next()){
            while(i<ndatos){
                datos[i]=cn.rt.getString(i+1);
                i++;
            }
            md.addRow(datos);
            i=0;
         }
     }
     catch(Exception e){
         System.out.println(e.getMessage());
     }

}
public boolean EntreFechas(String fecini,String fecfin){
        Date facatual=new Date();
        GregorianCalendar gc=new GregorianCalendar();
        boolean b=false;
        int añofecini,mesfecini,diafecini;
        int añofecfin,mesfecfin,diafecfin;
        gc.setTime(facatual);
        añofecini=Integer.parseInt(fecini.substring(0,4));mesfecini=Integer.parseInt(fecini.substring(5,7));diafecini=Integer.parseInt(fecini.substring(8));
        añofecfin=Integer.parseInt(fecfin.substring(0,4));mesfecfin=Integer.parseInt(fecfin.substring(5,7));diafecfin=Integer.parseInt(fecfin.substring(8));
        if(gc.get(Calendar.YEAR)==añofecini && gc.get(Calendar.YEAR)==añofecfin){
            if(gc.get(Calendar.MONTH)+1>mesfecini && gc.get(Calendar.MONTH)+1<mesfecfin){
                b=true;
            }
            if(gc.get(Calendar.MONTH)+1==mesfecini){
                if(gc.get(Calendar.DAY_OF_MONTH)+1>=diafecini){
                    b=true;
                }
            }
            if(gc.get(Calendar.MONTH)+1==mesfecfin){
                if(gc.get(Calendar.DAY_OF_MONTH)+1<=diafecfin){
                    b=true;
                }
            }
        }
        if(gc.get(Calendar.YEAR)==añofecini && gc.get(Calendar.YEAR)+1==añofecfin){
            if(gc.get(Calendar.MONTH)+1==mesfecini){
                if(gc.get(Calendar.DAY_OF_MONTH)+1>=diafecini){
                    b=true;
                }
            }
            if(gc.get(Calendar.MONTH)+1>mesfecini){
                b=true;
            }
        }
        if(gc.get(Calendar.YEAR)>añofecini && gc.get(Calendar.YEAR)+1==añofecfin){
            if(gc.get(Calendar.MONTH)+1>mesfecini){
                b=true;
            }
            if(gc.get(Calendar.MONTH)+1==mesfecini){
                if(gc.get(Calendar.DAY_OF_MONTH)+1>=diafecini){
                    b=true;
                }
            }
            if(gc.get(Calendar.MONTH+1)<mesfecfin){
                b=true;
            }
            if(gc.get(Calendar.MONTH+1)==mesfecfin){
                if(gc.get(Calendar.DAY_OF_MONTH)+1<=diafecfin){
                    b=true;
                }
            }
        }
        return b;
    }
public String EncontrarSemestre(){
        boolean b=false;
        String semestre="";
        try{
            cn.Conectar();
            cn.st=cn.conec.createStatement();
            cn.rt=cn.st.executeQuery("Select * from semestre");
            while(cn.rt.next()){
                if(EntreFechas(cn.rt.getString(3),cn.rt.getString(4))){
                    semestre=cn.rt.getString(2);
                    break;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return semestre;
    }
public boolean ActualizarTbla(String tb, String DtAc,String cmpact,String cdc,String cmpcond){
 boolean b=false;
 try{sq="select * from "+tb+" where "+cmpact+"='"+DtAc+"' and "+cmpcond+"<>'"+cdc+"'";
    cn.st=cn.conec.createStatement();
    cn.rt=cn.st.executeQuery(sq);
    if(cn.rt.next()==false){
     sq="update "+tb+" set "+cmpact+"='"+DtAc+"' where "+cmpcond+"='"+cdc+"';";
     cn.st=cn.conec.createStatement();
     int r=cn.st.executeUpdate(sq); b=true;
   }   
 }
 catch(Exception e){
  e.getMessage();
 }
 return b;
}
public boolean ActualizarTblaConProCategoria(String cod,String dato){
 boolean b=false;
 try{cn.prest=cn.conec.prepareCall("{call ActzCategoria(?,?)}");
         cn.prest.setString(1, cod);cn.prest.setString(2, dato);
         int p=cn.prest.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
 return b;
}
public void Eliminar(String tbl, String cmpvld,String vlr){
 try{sq="delete from "+tbl+" where "+cmpvld+"='"+vlr+"';";
     cn.st=cn.conec.createStatement();
     int r=cn.st.executeUpdate(sq);
 }
 catch(Exception e){
   e.printStackTrace();
 }
}
public void EliminaCtgcnpro(String cd){
  try{cn.prest=cn.conec.prepareCall("{call ElimCategoria(?)}");
       cn.prest.setString(1, cd);
       int p=cn.prest.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
}
public void BuscarDatosProc(DefaultTableModel md,String tb,String dato,int ndatos){
  String[] datos=new String[ndatos]; int i=0;
     try{LimpiTabla(md);
         cn.prest=cn.conec.prepareCall("{call buscar"+tb+"(?)}");
         cn.prest.setString(1,dato);
         cn.rt=cn.prest.executeQuery();
         while(cn.rt.next()){
            while(i<ndatos){
                datos[i]=cn.rt.getString(i+1);
                i++;
            }
            md.addRow(datos);
            i=0;
         }
     }
     catch(Exception e){
         System.out.println(e.getMessage());
     }

}
public void InsertarPrd(String prd,double cst,String cat,String mrc){
     try{
         cn.prest=cn.conec.prepareCall("{call insertarProdu(?,?,?,?)}");
         cn.prest.setString(1,prd);
         cn.prest.setDouble(2,cst);
         cn.prest.setString(3,cat);
         cn.prest.setString(4,mrc);
         cn.rt=cn.prest.executeQuery();
         int p=cn.prest.executeUpdate();
     }
     catch(Exception e){
         System.out.println(e.getMessage());
     }  
}
public void VerificarPersonal(JTable tblprsoge,JComboBox cbofac,JInternalFrame intfr){
        JPasswordField jpf = new JPasswordField();
        JLabel titulo = new JLabel ("Ingrese su clave");
        if(JOptionPane.showConfirmDialog (intfr,new Object[]{titulo,jpf}, "Verificacion de Personal", JOptionPane.OK_CANCEL_OPTION)==msg.CANCEL_OPTION){
            tblprsoge.clearSelection();
            return;
        }
        boolean b=false;
        char p[] = jpf.getPassword();
        String pass = new String(p);
        try{
            cn.Conectar();
            cn.st=cn.conec.createStatement();
            cn.rt=cn.st.executeQuery("select * from personaloge where codpers='"+
                    tblprsoge.getValueAt(tblprsoge.getSelectedRow(), 0).toString()+"' and clave='"+pass+"'");
            b=cn.rt.next();
        }catch(Exception e){
            msg.showMessageDialog(intfr,"Error en la verificacion de usuario","Error",msg.ERROR_MESSAGE);

        }
        if(b){
            cbofac.setEnabled(true);
        }
        else{
            JOptionPane.showMessageDialog(intfr,"Error en la verificacion de personal","Mensaje del Sistema",msg.WARNING_MESSAGE);
            tblprsoge.clearSelection();
        }
    }
/********************************FIN IMPLEMENTACION DE METODOS************************/
}
