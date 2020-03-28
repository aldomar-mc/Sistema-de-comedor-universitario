/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CambiarClave.java
 *
 * Created on 06-feb-2014, 16:24:53
 */
package Internals;
import Clases.*;
import javax.swing.JOptionPane;
/**** @author Silva *****/
public class CambiarClave extends javax.swing.JInternalFrame {
Controlador control= new Controlador();
InfoGeneral info= new InfoGeneral();
    public CambiarClave() {
        initComponents();
//        txtNomUsu.grabFocus();
        txtNomUsu.setEnabled(false);
        txtNomUsu.setText(info.getUsuario());
        psAnter.grabFocus();
    }
    public boolean  bloquear(){
        boolean a=false;
        if(txtNomUsu.getText().trim().length()>0 && psAnter.getText().trim().length()>0 && psContaNu.getText().trim().length()>0 && psContraNuevRep.getText().trim().length()>0){
            a=true;
        }
        return a;
    }
    public void CambiarClave(){
     if(bloquear()){
      if(info.getUsuario().compareTo(txtNomUsu.getText())==0){
       if(psContaNu.getText().compareTo(psContraNuevRep.getText())==0){
        int ddd=Integer.parseInt(control.DevolverRegistroDto("select count(*) from usuario where nomusr='"+txtNomUsu.getText()+"' and psw='"+psAnter.getText()+"';", 1));
        if(ddd==1){
         int isud=Integer.parseInt(control.DevolverRegistroDto("select idusuario from usuario where nomusr='"+txtNomUsu.getText()+"' and psw='"+psAnter.getText()+"';", 1));
         control.Sql="update usuario set psw='"+psContaNu.getText()+"' where idusuario='"+isud+"';";
         control.EditarRegistro(control.Sql);
         JOptionPane.showMessageDialog(null, "Cambiado con Exito!!");
         txtNomUsu.setText("");psAnter.setText("");psContaNu.setText("");
         psContraNuevRep.setText("");txtNomUsu.grabFocus();
        }
        else{
         JOptionPane.showMessageDialog(null, "el Usuario y la Contraseña no son Correctos!!");   
        }
      }else{
                    JOptionPane.showMessageDialog(null, "Las nuevas Contraseñas no son Iguales");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Ingrese el Usuario con el que inicio Sección");
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNomUsu = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        psAnter = new javax.swing.JPasswordField();
        psContaNu = new javax.swing.JPasswordField();
        psContraNuevRep = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 153, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del usuario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 102))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Nombre Usuario:");
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setText("Contraseña Anterior");
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 102));
        jLabel5.setText("Repetir Contraseña Nueva");
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 139, -1, -1));

        txtNomUsu.setName("txtNomUsu"); // NOI18N
        jPanel1.add(txtNomUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 25, 190, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 102));
        jLabel4.setText("Ingresar Contraseña Nueva");
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 102, -1, -1));

        psAnter.setName("psAnter"); // NOI18N
        jPanel1.add(psAnter, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 63, 190, -1));

        psContaNu.setName("psContaNu"); // NOI18N
        jPanel1.add(psContaNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 190, -1));

        psContraNuevRep.setName("psContraNuevRep"); // NOI18N
        jPanel1.add(psContraNuevRep, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 138, 190, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 170));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Accept.png"))); // NOI18N
        jButton1.setText("Aceptar");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 13, -1, 40));

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir.png"))); // NOI18N
        jButton2.setText("Salir");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 13, 104, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 410, 65));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CambiarClave();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField psAnter;
    private javax.swing.JPasswordField psContaNu;
    private javax.swing.JPasswordField psContraNuevRep;
    private javax.swing.JTextField txtNomUsu;
    // End of variables declaration//GEN-END:variables
}
