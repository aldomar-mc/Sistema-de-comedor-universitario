/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;
import javax.swing.*;
import javax.swing.table.*;
public class ClsVisualizador {
 /*************************DECLARACION DE ATRIBUTOS*****************************************/
public JOptionPane Dlg=new JOptionPane();
Clscontrolador Ctld=new Clscontrolador();
int intentos=0;ConexionBD cn=new ConexionBD();
/*************************FIN DECLARACION DE ATRIBUTOS*************************************/
public String obtDatoTabla(JTable tb, DefaultTableModel m,int item ){
String it="";
int p=0;
p=tb.getSelectedRow();
it=m.getValueAt(p, item).toString().trim();
return  it;
}
public String obtdat(DefaultTableModel mdtb, JTable tb,JTextField txtape,
   JTextField txtnom,JTextField ema,JTextField cel, JTextField dni,JComboBox cbocar,JComboBox cbofac){
cbocar.setSelectedIndex(-1);
cbofac.setSelectedIndex(-1);
     int p=0;
      String cod="";
       String ape="";
       String nom="";

      if(mdtb.getRowCount()>0){
       p=tb.getSelectedRow();
       cod=mdtb.getValueAt(p, 0).toString();
      ema.setText(mdtb.getValueAt(p, 4).toString());
       cel.setText(mdtb.getValueAt(p, 5).toString());
           MostrarEnCombo(mdtb.getValueAt(p, 3).toString().trim(),cbofac);
       MostrarEnCombo(mdtb.getValueAt(p, 2).toString().trim(),cbocar);

  try{//cn.Conectar();
  String sq="{call SeleccionarApeNom('"+mdtb.getValueAt(p, 0)+"')}";
      cn.st=cn.conec.createStatement();cn.rt=cn.st.executeQuery(sq);
      while(cn.rt.next()){
                    txtape.setText(cn.rt.getString(1));
                    txtnom.setText(cn.rt.getString(2));
                    dni.setText(cn.rt.getString(3));
      }

     }
  catch(Exception E)
  {
   E.printStackTrace();
  }

      }
      else{
       JOptionPane.showMessageDialog(null,"No hay nada que mostrar","Aviso",2);
      }
 return  cod;
    }
public void MostrarEnCombo(String vl,JComboBox cbo){
  int p=0,ct=0;
  while(ct<cbo.getItemCount()){
   if(cbo.getItemAt(ct).toString().trim().compareTo(vl)==0){
    p=ct;ct=cbo.getItemCount();

   }
   ct++;
  }
  cbo.setSelectedIndex(p);
 }
public String[] obtHorFec(String cod){
   String a[]=new String[2];
      String b[]=new String[2];

     try{
         cn.prest=cn.conec.prepareCall("{call verHorFec(?)}");
         cn.prest.setString(1,cod.trim());
         cn.rt=cn.prest.executeQuery();
         while(cn.rt.next()){

                a[0]=cn.rt.getDate(1).toString().trim();
                a[1]=cn.rt.getTime(2).toString().trim();

         }
     }
     catch(Exception e){
         System.out.println(e.getMessage());
     }
String auxf="";
char a1[]=a[0].toCharArray();
int j=0;
for(int h=8;h<a[0].length();h++){
auxf+=a1[h];
}
for(int h=5;h<7;h++){
auxf+=a1[h];
}
for(int h=0;h<4;h++){
auxf+=a1[h];
}

String auxh="";
char a2[]=a[1].toCharArray();

for(int h=0;h<2;h++){
auxh+=a2[h];
}
for(int h=3;h<5;h++){
auxh+=a2[h];
}
for(int h=6;h<a[1].length();h++){
auxh+=a2[h];
}

b[0]=auxf;
b[1]=auxh;

 return  b;
}
public String obtcod(DefaultTableModel mdtb, JTable tb){

     int p;
      String cod="";
      if(mdtb.getRowCount()>0&&tb.getSelectedRow()>=0 ){
       p=tb.getSelectedRow();
      cod=mdtb.getValueAt(p,0).toString();

      }

 return  cod;
    }
public String obtest(DefaultTableModel mdtb, JTable tb){


      String cod="";
      if(mdtb.getRowCount()>0&&tb.getSelectedRow()>=0){
           int p=-1;
       p=tb.getSelectedRow();
      cod=mdtb.getValueAt(p,3).toString();

      }

 return  cod;
    }
public String obtCodAct(DefaultTableModel mdtb, JTable tb){


      String cod="";
      if(mdtb.getRowCount()>0&&tb.getSelectedRow()>=0){
           int p=-1;
       p=tb.getSelectedRow();
      cod=mdtb.getValueAt(p,0).toString();

      }

 return  cod;
    }
public void VerenTablaPFac(DefaultTableModel mdl, String[] dts, String cnslt) {
        try {
            Ctld.LimpiTabla(mdl);
   //         cn.Conectar();
            cn.st = cn.conec.createStatement();
            cn.rt = cn.st.executeQuery(cnslt);
            while (cn.rt.next()) {
                dts[0] = cn.rt.getString(1);
                dts[1] = cn.rt.getString(2);
                dts[2] = cn.rt.getString(3);
                dts[3] = cn.rt.getString(4);
                dts[4] = cn.rt.getString(5);
                dts[5] = cn.rt.getString(6);
                mdl.addRow(dts);
            }

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, E.getMessage());
        }
    }
public void VerenTablaPOge(DefaultTableModel mdl, String[] dts, String cnslt) {
        try {
            Ctld.LimpiTabla(mdl);
//            cn.Conectar();
            cn.st = cn.conec.createStatement();
            cn.rt = cn.st.executeQuery(cnslt);
            while (cn.rt.next()) {
                dts[0] = cn.rt.getString(1);
                dts[1] = cn.rt.getString(2);
                dts[2] = cn.rt.getString(3);
                 mdl.addRow(dts);
            }

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, E.getMessage());
        }
    }
public void MensajeCorrectaInsercion(JInternalFrame intfrm){
     Dlg.showMessageDialog(intfrm,"Se grabó correctamente","Aviso",Dlg.INFORMATION_MESSAGE);
 }
public void MensajeCorrectaActualizacion(JInternalFrame intfrm){
     Dlg.showMessageDialog(intfrm,"Se editó correctamente","Aviso",Dlg.INFORMATION_MESSAGE);
 }
public void VerenTabla1(DefaultTableModel mdltb,String[] dts,String cnslt){
  try{Ctld.LimpiTabla(mdltb);
//   Ctld.cn.Conectar();
   Ctld.cn.st=Ctld.cn.conec.createStatement();
   Ctld.cn.rt=Ctld.cn.st.executeQuery(cnslt);
   while(Ctld.cn.rt.next()){
     dts[0]=Ctld.cn.rt.getString(1); dts[1]=Ctld.cn.rt.getString(2);
     dts[2]=Ctld.cn.rt.getString(3);
     mdltb.addRow(dts);
   }
   
  }
  catch(Exception E){
   Dlg.showMessageDialog(null, E.getMessage());   
  }
 }
public void VerenTabla2(DefaultTableModel mdltb,String[] dts,String cnslt){
  try{Ctld.LimpiTabla(mdltb);
//   Ctld.cn.Conectar();
   Ctld.cn.st=Ctld.cn.conec.createStatement();
   Ctld.cn.rt=Ctld.cn.st.executeQuery(cnslt);
   while(Ctld.cn.rt.next()){
     dts[0]=Ctld.cn.rt.getString(1); dts[1]=Ctld.cn.rt.getString(2);
     dts[2]=Ctld.cn.rt.getString(3);dts[3]=Ctld.cn.rt.getString(4);
     mdltb.addRow(dts);
   }

  }
  catch(Exception E){
   Dlg.showMessageDialog(null, E.getMessage());
  }
 }
public void VerenTabla3(DefaultTableModel mdltb,String[] dts,String cnslt){
  try{Ctld.LimpiTabla(mdltb);
//   Ctld.cn.Conectar();
   Ctld.cn.st=Ctld.cn.conec.createStatement();
   Ctld.cn.rt=Ctld.cn.st.executeQuery(cnslt);
   while(Ctld.cn.rt.next()){
     dts[0]=Ctld.cn.rt.getString(1); dts[1]=Ctld.cn.rt.getString(2);
     dts[2]=Ctld.cn.rt.getString(3);dts[3]=Ctld.cn.rt.getString(4);
     dts[4]=Ctld.cn.rt.getString(5);
     mdltb.addRow(dts);
   }

  }
  catch(Exception E){
   Dlg.showMessageDialog(null, E.getMessage());
  }
 }
public void VerenTabla(DefaultTableModel mdltb,String[] dts,String cnslt){
  try{Ctld.LimpiTabla(mdltb);
//   Ctld.cn.Conectar();
   Ctld.cn.st=Ctld.cn.conec.createStatement();
   Ctld.cn.rt=Ctld.cn.st.executeQuery(cnslt);
   while(Ctld.cn.rt.next()){
     dts[0]=Ctld.cn.rt.getString(1); dts[1]=Ctld.cn.rt.getString(2);
     mdltb.addRow(dts);
   }

   
  }
  catch(Exception E){
   Dlg.showMessageDialog(null, E.getMessage());   
  }
 }
public void Salir(JFrame frm){
  if(Dlg.showConfirmDialog(null,"Deseas Salir","Confirmar",0)==0)
   frm.dispose();
 }
public void MostrarFrm(JFrame frm){
   frm.setVisible(true);  
 }
public void MostrarIntFrm(JInternalFrame intfrm,JDesktopPane escritorio){
     escritorio.add(intfrm);
     intfrm.show();
 }
public void Acceso(JFrame frmacc,JFrame frm,JComboBox Cbo,JPasswordField psw,int cint){
   intentos++;
   if(Ctld.VerifClv(Cbo.getSelectedItem().toString(), psw.getText())){
      frm.dispose();frmacc.setVisible(true);
   }
   else{
    if(intentos==1)
     Dlg.showMessageDialog(null, "Lleva un intento");
    else{
     if(intentos==cint){
      Dlg.showMessageDialog(null, "Ya van"+cint+" Intentos se cerrara el Sistema");
      frm.dispose();
     }
     else{
      Dlg.showMessageDialog(null, "Llevas "+intentos+" intentos");      
     }      
    }
    Ctld.MarcaTxt(psw);
   }
 }
public void LlamaProgramas(String Prg){
     try{
           Runtime rt=Runtime.getRuntime();
           Process p=rt.exec(Prg);
           p.waitFor();
       }
       catch(Exception e){
           e.printStackTrace();
       }
 }
public String CargarDts(DefaultTableModel mdtb, JTable tb,JTextField tx){
      int p;String cd="";
      if(mdtb.getRowCount()>0){
           p=tb.getSelectedRow();cd=mdtb.getValueAt(p, 0).toString();
           tx.setText(mdtb.getValueAt(p, 1).toString());
      }
      else{
           Dlg.showMessageDialog(null,"No hay nada que mostrar","Aviso",2);
      }
      return cd;
    }
}
