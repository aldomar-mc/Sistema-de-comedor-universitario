/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

/**
 *
 * @author chichi
 */
import javax.swing.*;
public class Visualizar {
/************************************ATRIBUTOS************************************/
public int intentos=0;
public JOptionPane Dlg=new JOptionPane();
/************************************FIN ATRIBUTOS********************************/

/************************************METODOS**************************************/
public void Salir(JFrame frm){
  if(Dlg.showConfirmDialog(null,"Deseas Salir","Confirmar",0)==0)
   frm.dispose();
 }
 public void Mostrar(JFrame frm){
   frm.setVisible(true);
 }
 public void MostrarIntFrm(JInternalFrame intfrm,JDesktopPane escritorio){
     escritorio.add(intfrm);
     intfrm.show();
 }
 public void MostrarIntFrm1(JInternalFrame intfrm,JDesktopPane escritorio){
     //escritorio.add(intfrm);
     intfrm.show();
 }
/************************************FIN METODOS**********************************/
}
