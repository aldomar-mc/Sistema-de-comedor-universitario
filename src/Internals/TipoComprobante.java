package Internals;
/**** @author Ing. Miguel Angel Silva Zapata.   **********/
import javax.swing.*;import Clases.*;

public class TipoComprobante extends javax.swing.JInternalFrame {

    /*************************Atributos**********************/ 
    Mimodelo modelo=new Mimodelo();
    Controlador control=new Controlador();
    String idLinProd;
    InfoGeneral info= new InfoGeneral();
    /*************************Atributos**********************/ 
    
    /*************************Métodos************************/     
    public void CrearLineaproducto(){   
     if(txLineas.getText().trim().length()==0){
      JOptionPane.showMessageDialog(rootPane,"Faltan datos");   
      txLineas.requestFocus();
     }    
     else{
      control.Sql="select * from tipocomprobante where descricomproba='"+txLineas.getText()+"'";   
      if(control.Verificandoconsulta(control.Sql)){
       JOptionPane.showMessageDialog(rootPane,"El tipo de Comprobante está repetida");          
      }
      else{       
         control.Sql=String.format("insert into tipocomprobante values(null,'%s')",control.PriLtrasMayuscula(txLineas.getText()));
        //control.Sql="insert into lineaproducto (nomlna) values('"+control.PriLtrasMayuscula(txLineas.getText())+"')";
        control.CrearRegistro(control.Sql );MostrarLineaproducto();       
      }
      if(info.getGg()==5){
        //  control.LlenarCombo(Catalogoproductos.cbLineas,"select * from lineaproducto",2);
         // Catalogoproductos.cbLineas.
      }
      Cancelar();
     }    
    }
    public void ModificarLineaproducto(){
     if(txLineas.getText().length()>0){
         //control.Sql=String.format(title, args);
      control.Sql="select * from tipocomprobante where descricomproba='"+txLineas.getText()+
      "' and idtipocomprobante<>'"+idLinProd+"'";        
      if(!control.Verificarconsulta(control.Sql)){
          control.Sql=String.format("update tipocomprobante set descricomproba='%s' where idtipocomprobante='%s';",txLineas.getText(), idLinProd);
       //control.Sql="update tipocomensal set descritipcom='' where idtipocomensal='';";        
       control.EditarRegistro(control.Sql);Cancelar();MostrarLineaproducto();
      }
      else{
       JOptionPane.showMessageDialog(rootPane,"Existe repeticion en el Tipo de Comprobante");   
      }         
     } 
     else{
      JOptionPane.showMessageDialog(rootPane,"Faltan datos");   
     }   
    }
    public void EliminarLineaproducto(){
     control.fila=tLineaproducto.getSelectedRow();        
     if(control.fila>=0){
      idLinProd=tLineaproducto.getValueAt(control.fila,0).toString();   
//      control.Sql="select * from catalogoproducto where idLineaproducto='"+idLinProd+"'";           
//      if(!control.Verificandoconsulta(control.Sql)){
       if(JOptionPane.showConfirmDialog(rootPane,"Seguro deseas eliminar","Confirmar",0)==0){
        control.Sql="delete from tipocomprobante where idtipocomprobante='"+idLinProd+"'";    
        control.EliminarRegistro(control.Sql);Cancelar();MostrarLineaproducto();    
       }          
//      }
     }     
     else{
      JOptionPane.showMessageDialog(rootPane,"Seleccione la fila a eliminar");   
     }      
    }
    public void VerLineaproducto(){
     control.fila=tLineaproducto.getSelectedRow();
     if(control.fila>=0){
         bCrear.setEnabled(false);
         bEliminar.setEnabled(false);
      idLinProd=tLineaproducto.getValueAt(control.fila,0).toString();   
      txLineas.setText(tLineaproducto.getValueAt(control.fila,1).toString());         
     }    
    }
    public void MostrarLineaproducto(){
     BuscarLineaproducto();    
    }
    public void BuscarLineaproducto(){
     control.Sql="select * from tipocomprobante where descricomproba like '%"+txBuscar.getText()+"%'";
     control.LlenarJTabla(modelo,control.Sql,2);          
    }
    public void ValidardatosLineaproducto(){
         
    }
    public void Cancelar(){
     
        bCrear.setEnabled(true);
         bEliminar.setEnabled(true);
     txLineas.setText(null);
     //tLineaproducto.clearSelection();
    //    MostrarLineaproducto();
     //txBuscar.setText(null);
     //control.MarcarTexto(txBuscar);
     txLineas.requestFocus();
     
    }
    public TipoComprobante() {
     initComponents();setTitle("Tipo Comprobantes");
     this.setFrameIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Vender.png")));
     tLineaproducto.setModel(modelo);modelo.setColumnIdentifiers(new String[] {"Código","Tipo Comprobante"});
     tLineaproducto.getColumnModel().getColumn(1).setPreferredWidth(250);
     bCrear.setMnemonic('c');bCancelar.setMnemonic('a');     
     bModificar.setMnemonic('d');bEliminar.setMnemonic('e');
     bSalir.setMnemonic('s');MostrarLineaproducto();
     control.forma_table_ver(modelo, tLineaproducto);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bSalir = new javax.swing.JButton();
        bCrear = new javax.swing.JButton();
        bModificar = new javax.swing.JButton();
        bEliminar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tLineaproducto = new javax.swing.JTable();
        txBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txLineas = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 153, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bSalir.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bSalir.setForeground(new java.awt.Color(0, 51, 102));
        bSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir.png"))); // NOI18N
        bSalir.setText("Salir");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });
        jPanel1.add(bSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 10, 100, 40));

        bCrear.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bCrear.setForeground(new java.awt.Color(0, 51, 102));
        bCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Accept.png"))); // NOI18N
        bCrear.setText("Crear");
        bCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCrearActionPerformed(evt);
            }
        });
        jPanel1.add(bCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 40));

        bModificar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bModificar.setForeground(new java.awt.Color(0, 51, 102));
        bModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Edit.png"))); // NOI18N
        bModificar.setText("Editar");
        bModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModificarActionPerformed(evt);
            }
        });
        jPanel1.add(bModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 100, 40));

        bEliminar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bEliminar.setForeground(new java.awt.Color(0, 51, 102));
        bEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        bEliminar.setText("Eliminar");
        bEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(bEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 110, 40));

        bCancelar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bCancelar.setForeground(new java.awt.Color(0, 51, 102));
        bCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancel.png"))); // NOI18N
        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(bCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 10, 110, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 0, 580, 60));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Tipo de Comprobantes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 102))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tLineaproducto.setForeground(new java.awt.Color(0, 51, 102));
        tLineaproducto.setModel(new javax.swing.table.DefaultTableModel(
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
        tLineaproducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tLineaproductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tLineaproducto);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 590, 210));

        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });
        jPanel2.add(txBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 27, 537, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Buscar");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 610, 270));

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Tipo de Comprobante", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 102))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txLineas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txLineasKeyReleased(evt);
            }
        });
        jPanel3.add(txLineas, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 370, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Tipo de Comprobantes");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 20));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 63, 610, 55));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
 dispose();
}//GEN-LAST:event_bSalirActionPerformed
private void bCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCrearActionPerformed
 CrearLineaproducto();
}//GEN-LAST:event_bCrearActionPerformed
private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed

        if(tLineaproducto.getSelectedRowCount()==1){
            ModificarLineaproducto();
        }else{
            JOptionPane.showMessageDialog(null, "Selecione un Registro para Editar","",3);
        }
            
    
}//GEN-LAST:event_bModificarActionPerformed
private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
 EliminarLineaproducto(); 
}//GEN-LAST:event_bEliminarActionPerformed
private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
txBuscar.setText(null); Cancelar(); MostrarLineaproducto();
        //txBuscar.setText(""); 
}//GEN-LAST:event_bCancelarActionPerformed
private void tLineaproductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tLineaproductoMouseClicked

    if(evt.getClickCount()==2){
        if(tLineaproducto.getSelectedRowCount()==1){
            VerLineaproducto();
        }else{
            JOptionPane.showMessageDialog(null, "Selecione un Registro para Editar","",3);
        }
    }
    
}//GEN-LAST:event_tLineaproductoMouseClicked

private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
 BuscarLineaproducto();
}//GEN-LAST:event_txBuscarKeyReleased

private void txLineasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txLineasKeyReleased
//control.Mayusculas(txLineas);
}//GEN-LAST:event_txLineasKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bCrear;
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton bModificar;
    private javax.swing.JButton bSalir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tLineaproducto;
    private javax.swing.JTextField txBuscar;
    private javax.swing.JTextField txLineas;
    // End of variables declaration//GEN-END:variables
}
