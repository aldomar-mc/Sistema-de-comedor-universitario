/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Permisos.java
 *
 * Created on 06-feb-2014, 17:11:22
 */
package Internals;
import Clases.*;
/**
 *
 * @author Silva
 */
public class Permisos extends javax.swing.JInternalFrame {
Controlador control=new Controlador();
Mimodelo modelo=new Mimodelo();
Mimodelo modelo1=new Mimodelo();
Mimodelo modelo2=new Mimodelo();
String codP="";
    /** Creates new form Permisos */
    public Permisos() {
        initComponents();
        tbUsuarios.setModel(modelo);
        modelo.setColumnIdentifiers(new String[] {"Codigo","Nombre","Login"});
        tbPermidosDisponibles.setModel(modelo1);
        modelo1.setColumnIdentifiers(new String[] {"Item","Permiso"});
        tbPermidosDisponibles.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbPermidosDisponibles.getColumnModel().getColumn(1).setPreferredWidth(250);
        
        tbPermisoUsuario.setModel(modelo2);
        modelo2.setColumnIdentifiers(new String[] {"Item","Permiso"});
        tbPermisoUsuario.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbPermisoUsuario.getColumnModel().getColumn(1).setPreferredWidth(250);
        MostrarCliente();
        control.forma_table_ver(modelo, tbUsuarios);
        control.forma_table_ver(modelo1,tbPermidosDisponibles);
        control.forma_table_ver(modelo2, tbPermisoUsuario);
    }
   public void MostrarCliente(){
     BuscarCliente();   
    }    
    public void BuscarCliente(){
     control.Sql="select u.idusuario, nom, nomusr from datosusuarios d, usuario u,tipousuario t where d.idusuario=u.idusuario and u.idtipousuario=t.idtipousuario and "
             + " (nomusr like'"+txtBuscarCliente.getText()+"%' or nom like'"+txtBuscarCliente.getText()+"%') ";
     control.LlenarJTabla(modelo,control.Sql,3); 
     
 //    JOptionPane.showMessageDialog(null,control.Sql);
    }
    public void TodoPermisos(String cod){
      control.LimTabla(modelo1);
     control.Sql="SELECT * FROM permisos p where descripcion like'"+jTextField1.getText()+"%' and  idPermisos not in (SELECT idPermisos FROM permisosusuario p  where p.idusuario='"+cod+"');";
     control.LlenarJTabla(modelo1, control.Sql, 2);
    }
    public void PermisoUsuario(String cod){
        control.LimTabla(modelo2);
        control.Sql="SELECT idpermisos,descripcion FROM vtapermisosusuarios where descripcion like '"+jTextField2.getText()+"%' and  idusuario='"+cod+"';";
         control.LlenarJTabla(modelo2, control.Sql, 2);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        tbUsuarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lbNombre = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lbLogin = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPermidosDisponibles = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPermisoUsuario = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(51, 153, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        tbUsuarios.setForeground(new java.awt.Color(0, 51, 102));
        tbUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbUsuarios.setName("tbUsuarios"); // NOI18N
        tbUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUsuariosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbUsuarios);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 44, 720, 120));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Asignar Permisos");
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Buscar");
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 26, -1, -1));

        txtBuscarCliente.setName("txtBuscarCliente"); // NOI18N
        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClienteKeyReleased(evt);
            }
        });
        getContentPane().add(txtBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 23, 300, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 102));
        jLabel5.setText("Nombre Usuario:");
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        lbNombre.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbNombre.setForeground(new java.awt.Color(0, 51, 102));
        lbNombre.setText(" ");
        lbNombre.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbNombre.setName("lbNombre"); // NOI18N
        getContentPane().add(lbNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 220, -1));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, 95, 37));

        lbLogin.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbLogin.setForeground(new java.awt.Color(0, 51, 102));
        lbLogin.setText(" ");
        lbLogin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbLogin.setName("lbLogin"); // NOI18N
        getContentPane().add(lbLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 170, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 102));
        jLabel8.setText("Nombre Login:");
        jLabel8.setName("jLabel8"); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, -1, -1));

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Accesos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(51, 0, 153))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tbPermidosDisponibles.setForeground(new java.awt.Color(0, 51, 102));
        tbPermidosDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tbPermidosDisponibles.setName("tbPermidosDisponibles"); // NOI18N
        tbPermidosDisponibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPermidosDisponiblesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPermidosDisponibles);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setText("Buscar");
        jLabel3.setName("jLabel3"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(10, 10, 10)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(220, 220, 220))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3))
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 350, 310));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Permisos del usuario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(51, 0, 153))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tbPermisoUsuario.setForeground(new java.awt.Color(0, 51, 102));
        tbPermisoUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tbPermisoUsuario.setName("tbPermisoUsuario"); // NOI18N
        tbPermisoUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPermisoUsuarioMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbPermisoUsuario);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 102));
        jLabel4.setText("Buscar");
        jLabel4.setName("jLabel4"); // NOI18N

        jTextField2.setName("jTextField2"); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(10, 10, 10)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4))
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 360, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyReleased
        
        MostrarCliente();
        
    }//GEN-LAST:event_txtBuscarClienteKeyReleased

    private void tbUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUsuariosMouseClicked
        if(evt.getClickCount()==2){
            if(tbUsuarios.getSelectedRowCount()==1){
               codP=modelo.getValueAt(tbUsuarios.getSelectedRow(), 0).toString();
               lbNombre.setText(modelo.getValueAt(tbUsuarios.getSelectedRow(), 1).toString());
               lbLogin.setText(modelo.getValueAt(tbUsuarios.getSelectedRow(), 2).toString());
               TodoPermisos(codP);
               PermisoUsuario(codP);
            }
        }
    }//GEN-LAST:event_tbUsuariosMouseClicked

    private void tbPermidosDisponiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPermidosDisponiblesMouseClicked
        if(evt.getClickCount()==2){
            if(tbPermidosDisponibles.getSelectedRowCount()==1){
                control.Sql="insert into permisosusuario (idUsuario,idPermisos) values('"+codP+"','"+modelo1.getValueAt(tbPermidosDisponibles.getSelectedRow(), 0).toString()+"');";
                control.CrearRegistro(control.Sql);
                TodoPermisos(codP);
                PermisoUsuario(codP);
            }
        }
    }//GEN-LAST:event_tbPermidosDisponiblesMouseClicked

    private void tbPermisoUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPermisoUsuarioMouseClicked
        if(evt.getClickCount()==2){
            if(tbPermisoUsuario.getSelectedRowCount()==1){
                control.Sql="delete from permisosusuario where idUsuario='"+codP+"' and idPermisos='"+modelo2.getValueAt(tbPermisoUsuario.getSelectedRow(), 0).toString()+"';";
                control.EliminarRegistro(control.Sql);
                TodoPermisos(codP);
                PermisoUsuario(codP);
            }
        }
    }//GEN-LAST:event_tbPermisoUsuarioMouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        TodoPermisos(codP);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        PermisoUsuario(codP);
    }//GEN-LAST:event_jTextField2KeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JTable tbPermidosDisponibles;
    private javax.swing.JTable tbPermisoUsuario;
    private javax.swing.JTable tbUsuarios;
    private javax.swing.JTextField txtBuscarCliente;
    // End of variables declaration//GEN-END:variables
}
