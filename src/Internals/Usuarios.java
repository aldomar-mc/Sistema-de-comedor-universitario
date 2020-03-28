
package Internals;
import Clases.*;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Usuarios extends javax.swing.JInternalFrame {
Controlador control = new Controlador();
Mimodelo  modelo= new Mimodelo();
String dato="", pass="", Codigo="";

    /** Creates new form Vendedores */
   public Usuarios() {
        initComponents();
        bloquear(false);
        setTitle("Los Usuarios");
     this.setFrameIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Vender.png")));
     tVendedores.setModel(modelo);
     modelo.setColumnIdentifiers(new String[] {"Código","Dni","Nombre","Dirección","Telefono","Login","Tipo Usuario"});  
     control.LlenarCombo(cbTipoUsu, "SELECT * FROM tipousuario t;", 2);
     MostrarCliente();
     control.forma_table_ver(modelo, tVendedores);
    }
   public void bloquear(boolean  a){
       tNombre.setEnabled(a);
       txDireccion.setEnabled(a);
       txDni.setEnabled(a);
       txTelefono.setEditable(a);
       txUsuario.setEnabled(a);
       pwClave.setEnabled(a);
       cbTipoUsu.setEnabled(a);
      
   }
   public void limpiar(){
       cbTipoUsu.setSelectedIndex(-1);
       txDireccion.setText("");
       txDni.setText("");
       txTelefono.setText("");
       txUsuario.setText("");
       pwClave.setText("");
       tNombre.setText("");
   }
   public void Cancelar(){
    limpiar();bloquear(false);bCrear.setText("Crear");
    bModificar.setText("Editar");
       bCrear.setEnabled(true);
       bModificar.setEnabled(true);
       bEliminar.setEnabled(true);
   }
   public void MostrarCliente(){
     BuscarCliente();   
    }    
   public void BuscarCliente(){
     control.Sql="select u.idusuario, dni, nom, dire, tel,  nomusr,nomtpus ,psw from datosusuarios d, usuario u,tipousuario t where d.idusuario=u.idusuario and u.idtipousuario=t.idtipousuario"
             + " and (nomusr like'"+
         txtbucarVendedor.getText()+"%' or dni like'"+txtbucarVendedor.getText()+"%' or nom like'"+txtbucarVendedor.getText()+"%'  )";
     control.LlenarJTabla(modelo,control.Sql,7);
    }
   public void AgregarVendedor(){
    if(bCrear.getText().compareTo("Crear")==0){
     bloquear(true);tNombre.grabFocus();bEliminar.setEnabled(false);
     bModificar.setEnabled(false);bCrear.setText("Grabar");bCrear.setMnemonic('g');
    }
    else{
     if(verfica()){
      if(control.Verificarconsulta("select * from usuario where nomusr='"+txUsuario.getText()+"';")==false){
       control.Sql="select InsertaVendedor('"+txUsuario.getText()+"','"+pwClave.getText()+
       "','"+cbTipoUsu.getSelectedItem().toString()+"');";
       dato=control.DevolverRegistroDto(control.Sql, 1);                    
       control.Sql="insert into datosusuarios(nom,dire,tel, dni, idusuario)values"
       + "('"+control.PriLtrasMayuscula(tNombre.getText())+"','"+txDireccion.getText()+"','"+
       txTelefono.getText()+"','"+txDni.getText()+"','"+dato+"');";control.CrearRegistro(control.Sql);
       bloquear(false);limpiar(); bEliminar.setEnabled(true);bCrear.setMnemonic('c'); 
       bModificar.setEnabled(true);bCrear.setText("Crear");control.LimTabla(modelo);MostrarCliente();
      }
      else
       JOptionPane.showMessageDialog(null,"El usuario ya Existe!!");               
     }
     else
      JOptionPane.showMessageDialog(null,"Informacion Incompleta!!");           
    }
   }
   public boolean  verfica(){
    boolean a=false;
    if(tNombre.getText().trim().length()>0 && txDireccion.getText().trim().length()>0 && txDni.getText().trim().length()>0 && txTelefono.getText().trim().length()>0 && txUsuario.getText().trim().length()>0 && cbTipoUsu.getSelectedIndex()!=-1){
     a=true;
    }
    return a;
   }
   public void EliminarVendedor(){        
    if(tVendedores.getSelectedRowCount()==1){
     if(JOptionPane.showConfirmDialog(null, "Desea eliminar este Vendedor!!","",JOptionPane.YES_NO_OPTION)==0){
      control.Sql="delete from datosusuarios where idusuario='"+modelo.getValueAt(tVendedores.getSelectedRow(), 0) +"';";
      control.EliminarRegistro(control.Sql );
      control.Sql="delete from usuario where idusuario='"+modelo.getValueAt(tVendedores.getSelectedRow(), 0) +"';";
      control.EliminarRegistro(control.Sql );limpiar();MostrarCliente();
     }
    }
    else{
     JOptionPane.showMessageDialog(null, "Tiene Que Seleccionar un Clinete para Eliminar","",2);
    } 
   }
   public void EditarVendedor(){
    if(bModificar.getText().compareTo("Editar")==0){
     if(tVendedores.getSelectedRowCount()==1){
      bloquear(true);tNombre.grabFocus();bCrear.setEnabled(false);bEliminar.setEnabled(false);
      Codigo=modelo.getValueAt(tVendedores.getSelectedRow(), 0).toString();
      tNombre.setText(modelo.getValueAt(tVendedores.getSelectedRow(), 2).toString());
      txDireccion.setText(modelo.getValueAt(tVendedores.getSelectedRow(), 3).toString());
      txDni.setText(modelo.getValueAt(tVendedores.getSelectedRow(), 1).toString());
      txTelefono.setText(modelo.getValueAt(tVendedores.getSelectedRow(), 4).toString());
      txUsuario.setText(modelo.getValueAt(tVendedores.getSelectedRow(), 5).toString());
      cbTipoUsu.setSelectedItem(modelo.getValueAt(tVendedores.getSelectedRow(), 6).toString());
      pass=control.DevolverRegistroDto("select psw from usuario where idusuario='"+Codigo+"';", 1);
      pwClave.setText(pass);bModificar.setText("Grabar");                
     }
     else
      JOptionPane.showMessageDialog(null, "Selecione un Vendedor!!");     
    }
    else{
     if(verfica()){
      control.Sql="call UpdateVendedor('"+Codigo+"','"+txUsuario.getText()+"','"+pwClave.getText()
      +"','"+control.PriLtrasMayuscula(tNombre.getText())+"','"+txDireccion.getText()+"','"+
      txTelefono.getText()+"','"+txDni.getText()+"');";
      control.EditarRegistro(control.Sql);
      control.Sql="update usuario set idTipousuario='"+control.DevolverRegistroDto("SELECT idTipousuario FROM tipousuario where nomtpus='"
      +cbTipoUsu.getSelectedItem().toString()+"';", 1)+"' where idusuario='"+Codigo+"';";
      control.EditarRegistro(control.Sql);bloquear(false);limpiar();bCrear.setEnabled(true);
      bEliminar.setEnabled(true);bModificar.setText("Editar");control.LimTabla(modelo);MostrarCliente();
     }
     else{
      JOptionPane.showMessageDialog(null, "Datos incompletos!!!");
     }
    }
   }
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bSalir = new javax.swing.JButton();
        bCrear = new javax.swing.JButton();
        bModificar = new javax.swing.JButton();
        bEliminar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        tNombre = new javax.swing.JTextField();
        txTelefono = new javax.swing.JTextField();
        txtbucarVendedor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tVendedores = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txDireccion = new javax.swing.JTextField();
        cbTipoUsu = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pwClave = new javax.swing.JPasswordField();
        txUsuario = new javax.swing.JTextField();
        txDni = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bSalir.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bSalir.setForeground(new java.awt.Color(0, 51, 102));
        bSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir.png"))); // NOI18N
        bSalir.setMnemonic('s');
        bSalir.setText("Salir");
        bSalir.setName("bSalir"); // NOI18N
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });
        jPanel1.add(bSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 10, 110, 40));

        bCrear.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bCrear.setForeground(new java.awt.Color(0, 51, 102));
        bCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Accept.png"))); // NOI18N
        bCrear.setMnemonic('c');
        bCrear.setText("Crear");
        bCrear.setName("bCrear"); // NOI18N
        bCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCrearActionPerformed(evt);
            }
        });
        jPanel1.add(bCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 10, 110, 40));

        bModificar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bModificar.setForeground(new java.awt.Color(0, 51, 102));
        bModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Edit.png"))); // NOI18N
        bModificar.setMnemonic('e');
        bModificar.setText("Editar");
        bModificar.setName("bModificar"); // NOI18N
        bModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModificarActionPerformed(evt);
            }
        });
        jPanel1.add(bModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 10, 110, 40));

        bEliminar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bEliminar.setForeground(new java.awt.Color(0, 51, 102));
        bEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        bEliminar.setMnemonic('l');
        bEliminar.setText("Eliminar");
        bEliminar.setName("bEliminar"); // NOI18N
        bEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(bEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 10, 110, 40));

        bCancelar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bCancelar.setForeground(new java.awt.Color(0, 51, 102));
        bCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancel.png"))); // NOI18N
        bCancelar.setMnemonic('a');
        bCancelar.setText("Cancelar");
        bCancelar.setName("bCancelar"); // NOI18N
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(bCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(375, 10, 110, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 0, 620, 60));

        tNombre.setName("tNombre"); // NOI18N
        tNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNombreKeyTyped(evt);
            }
        });
        getContentPane().add(tNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 85, 240, -1));

        txTelefono.setName("txTelefono"); // NOI18N
        txTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txTelefonoKeyTyped(evt);
            }
        });
        getContentPane().add(txTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 240, -1));

        txtbucarVendedor.setName("txtbucarVendedor"); // NOI18N
        txtbucarVendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbucarVendedorKeyReleased(evt);
            }
        });
        getContentPane().add(txtbucarVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 380, -1));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tVendedores.setForeground(new java.awt.Color(0, 51, 102));
        tVendedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tVendedores.setName("tVendedores"); // NOI18N
        jScrollPane1.setViewportView(tVendedores);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 830, 150));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 102));
        jLabel9.setText("Buscar");
        jLabel9.setName("jLabel9"); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, 20));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setText("Tipo de Usuario");
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, 20));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 102));
        jLabel5.setText("Dirección");
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 20));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 102));
        jLabel7.setText("Nombre");
        jLabel7.setName("jLabel7"); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 87, -1, 20));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 102));
        jLabel8.setText("Teléfono");
        jLabel8.setName("jLabel8"); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, 20));

        txDireccion.setName("txDireccion"); // NOI18N
        txDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txDireccionKeyReleased(evt);
            }
        });
        getContentPane().add(txDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 128, 240, -1));

        cbTipoUsu.setName("cbTipoUsu"); // NOI18N
        getContentPane().add(cbTipoUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 211, 240, -1));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del usuario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 102));
        jLabel10.setText("Dni");
        jLabel10.setName("jLabel10"); // NOI18N
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 25, -1, 20));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 102));
        jLabel6.setText("Usuario");
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 75, 60, 20));

        pwClave.setName("pwClave"); // NOI18N
        jPanel3.add(pwClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 120, 210, -1));

        txUsuario.setName("txUsuario"); // NOI18N
        jPanel3.add(txUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 75, 210, -1));

        txDni.setName("txDni"); // NOI18N
        txDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txDniKeyTyped(evt);
            }
        });
        jPanel3.add(txDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 25, 210, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 102));
        jLabel11.setText("Clave");
        jLabel11.setName("jLabel11"); // NOI18N
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 122, -1, 20));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 62, 850, 180));

        jPanel4.setBackground(new java.awt.Color(51, 153, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de usuarios", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 245, 850, 230));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
 dispose();
}//GEN-LAST:event_bSalirActionPerformed
private void bCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCrearActionPerformed
    AgregarVendedor();
}//GEN-LAST:event_bCrearActionPerformed
private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed
 EditarVendedor();
}//GEN-LAST:event_bModificarActionPerformed
private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
 EliminarVendedor();    
}//GEN-LAST:event_bEliminarActionPerformed
private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
    Cancelar();
}//GEN-LAST:event_bCancelarActionPerformed
    private void txtbucarVendedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbucarVendedorKeyReleased
      MostrarCliente();        
    }//GEN-LAST:event_txtbucarVendedorKeyReleased
    private void tNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNombreKeyReleased
     control.Mayusculas(tNombre);
    }//GEN-LAST:event_tNombreKeyReleased
    private void txDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDireccionKeyReleased
     control.Mayusculas(txDireccion);
    }//GEN-LAST:event_txDireccionKeyReleased
    private void tNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNombreKeyTyped
     control.SoloLetras(evt);
    }//GEN-LAST:event_tNombreKeyTyped
    private void txTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txTelefonoKeyTyped
     control.Solonumeros(evt);control.Longitudtx(txTelefono, evt, 13);
    }//GEN-LAST:event_txTelefonoKeyTyped
    private void txDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDniKeyTyped
     control.Solonumeros(evt);control.Longitudtx(txDni, evt,8);
    }//GEN-LAST:event_txDniKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bCrear;
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton bModificar;
    private javax.swing.JButton bSalir;
    private javax.swing.JComboBox cbTipoUsu;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField pwClave;
    private javax.swing.JTextField tNombre;
    private javax.swing.JTable tVendedores;
    private javax.swing.JTextField txDireccion;
    private javax.swing.JTextField txDni;
    private javax.swing.JTextField txTelefono;
    private javax.swing.JTextField txUsuario;
    private javax.swing.JTextField txtbucarVendedor;
    // End of variables declaration//GEN-END:variables
}
