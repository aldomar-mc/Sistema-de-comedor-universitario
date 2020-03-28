package Internals;
/**** @author Ing. Miguel Angel Silva Zapata.   **********/
import javax.swing.*;
import Clases.*;
public class Turnos_Consumo extends javax.swing.JInternalFrame {
    /*************************Atributos**********************/ 
    Mimodelo modelo=new Mimodelo();
    Controlador control=new Controlador();
    String idBnc;
    /*************************Atributos**********************/ 
    
    /*************************Métodos************************/ 
    public void CrearBanco(){   
     if(txHoraMax.getText().trim().length()==0 || txHoraMin.getText().length()==0){
      JOptionPane.showMessageDialog(rootPane,"Faltan datos");   
      txHoraMin.requestFocus();
     }    
     else{
//      String idcome=control.DevolverRegistroDto("select idtipocomensal from tipocomensal where descritipcom='';", 1);
//      control.Sql="select * from tarifas where categor='"+cbTurno.getSelectedItem().toString()+"' and estado='0' and idtipocomensal='"+idcome+"';";   
//      if(control.Verificandoconsulta(control.Sql)){
//       JOptionPane.showMessageDialog(rootPane,"La Tarifa es Correcta es correcto");          
//      }
//      else{       
       control.Sql=String.format("update turno set estado='1' where descripturno='%s';",cbTurno.getSelectedItem().toString());
       control.EditarRegistro(control.Sql);
       control.Sql=String.format("insert into turno values(null,'%s','%s','%s','%s')",cbTurno.getSelectedItem().toString(),txHoraMin.getText(), txHoraMax.getText(), cbEstado.getSelectedIndex(),cbTurno.getSelectedItem().toString());
       System.out.println(control.Sql);
       control.CrearRegistro(control.Sql );MostrarBancos(); 
      //}
      Cancelar();
     }   
    }
    public void Cancelar(){
     txHoraMax.setText(null);cbEstado.setSelectedIndex(0);   
     tBancos.clearSelection();txBuscar.setText(null);
     txHoraMax.requestFocus();
     bModificar.setText("Editar");
     bCrear.setEnabled(true);
     bEliminar.setEnabled(true);
     cbTurno.setSelectedIndex(0);
     //cbTipoComsumis.setSelectedIndex(0);
     cbEstado.setSelectedIndex(0);
    }
    public void ModificarBanco(){
     if(txHoraMax.getText().length()>0){
      control.Sql="select * from turno where descripturno='"+cbTurno.getSelectedItem().toString()+
      "' and estado='"+cbEstado.getSelectedIndex()+"' and horamin='"+txHoraMin.getText()+"'and horamax='"+txHoraMax.getText()+"' and idturno<>'"+idBnc+"'";
      if(!control.Verificarconsulta(control.Sql)){
          if(cbEstado.getSelectedIndex()==0){
              control.Sql=String.format("update turno set estado='1' where descripturno='%s';",cbTurno.getSelectedItem().toString());
              control.EditarRegistro(control.Sql);
          }
       control.Sql=String.format("update turno set horamin='%s', horamax='%s',estado='%s' where idturno='%s';",txHoraMin.getText(),txHoraMax.getText(),cbEstado.getSelectedIndex(),idBnc);
       control.EditarRegistro(control.Sql);Cancelar();MostrarBancos();
       bModificar.setText("Editar");
       bCrear.setEnabled(true);
       bEliminar.setEnabled(true);
      }
      else{
       JOptionPane.showMessageDialog(rootPane,"Existe un Turno Activo con los Mismos Datos");   
      }         
     } 
     else{
      JOptionPane.showMessageDialog(rootPane,"Faltan datos");   
     }    
    }
    public void EliminarBanco(){
     control.fila=tBancos.getSelectedRow();   
     if(control.fila>=0){
      idBnc=tBancos.getValueAt(control.fila,0).toString();   
       if(JOptionPane.showConfirmDialog(rootPane,"Seguro deseas eliminar","Confirmar",0)==0){
        control.Sql=String.format("delete from turno where idturno='%s'", idBnc);       
        control.EliminarRegistro(control.Sql);
        Cancelar();MostrarBancos();    
       }
     }     
     else{
      JOptionPane.showMessageDialog(rootPane,"Seleccione la fila a eliminar");   
     }    
    }
    public void VerBanco(){
     control.fila=tBancos.getSelectedRow();
     if(control.fila>=0){
         bModificar.setText("Grabar");
         bCrear.setEnabled(false);
         bEliminar.setEnabled(false);
      idBnc =tBancos.getValueAt(control.fila,0).toString();
      txHoraMin.setText(tBancos.getValueAt(control.fila,2).toString());
      txHoraMax.setText(tBancos.getValueAt(control.fila,3).toString());
      cbEstado.setSelectedItem(tBancos.getValueAt(control.fila,4).toString());
      cbTurno.setSelectedItem(tBancos.getValueAt(control.fila,1).toString());
     }   
    }
    public void MostrarBancos(){    
     BuscarBanco();               
    }
    public void BuscarBanco(){
        control.Sql="SELECT idturno, descripturno, horamin, horamax, if(estado=0, 'Activo','InActivo') FROM turno t "
                + " where  descripturno like '%"+txBuscar.getText()+"%' order by estado asc ;";
     control.LlenarJTabla(modelo,control.Sql,5);    
    }
    public void ValidardatosBancos(){
          
    }
    public Turnos_Consumo() {
     initComponents();setTitle("Horarios de Consumos de Alimentos");
     this.setFrameIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Vender.png")));
     tBancos.setModel(modelo);modelo.setColumnIdentifiers(new String[] {"Id","Turno","Hora Minima","Hora Maxima","Estado"});
     tBancos.getColumnModel().getColumn(0).setMaxWidth(0);
     tBancos.getColumnModel().getColumn(0).setMinWidth(0);
     tBancos.getColumnModel().getColumn(0).setPreferredWidth(0);
     tBancos.getColumnModel().getColumn(1).setPreferredWidth(100);
     tBancos.getColumnModel().getColumn(2).setPreferredWidth(50);
     tBancos.getColumnModel().getColumn(3).setPreferredWidth(50);
     tBancos.getColumnModel().getColumn(4).setPreferredWidth(50);
     bCrear.setMnemonic('c');bCancelar.setMnemonic('a');     
     bModificar.setMnemonic('d');bEliminar.setMnemonic('e');
     bSalir.setMnemonic('s');MostrarBancos();
     cbEstado.addItem("Activo");
     cbEstado.addItem("InActivo");
     cbEstado.setSelectedIndex(0);
     cbTurno.addItem("Desayuno");
     cbTurno.addItem("Almuerzo");
     cbTurno.addItem("Cena");
     cbTurno.setSelectedIndex(0);
     FormatoTabla ft= new FormatoTabla(1);
     tBancos.setDefaultRenderer(Object.class, ft);
     //control.LlenarCombo(cbTipoComsumis,"SELECT * FROM tipocomensal",2);
    }
    /*************************Métodos************************/

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bSalir = new javax.swing.JButton();
        bCrear = new javax.swing.JButton();
        bModificar = new javax.swing.JButton();
        bEliminar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tBancos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txHoraMax = new javax.swing.JTextField();
        cbTurno = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txHoraMin = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(51, 153, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
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
        jPanel1.add(bSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 100, 40));

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
        jPanel1.add(bCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 110, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 60));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Buscar");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, 20));

        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });
        getContentPane().add(txBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 490, -1));

        tBancos.setAutoCreateRowSorter(true);
        tBancos.setForeground(new java.awt.Color(0, 51, 102));
        tBancos.setModel(new javax.swing.table.DefaultTableModel(
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
        tBancos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tBancosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tBancos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 560, 210));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Turno", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("Hora Maxima");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, 20));

        jPanel2.add(cbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 290, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Hora Minima");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, 20));
        jPanel2.add(txHoraMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 290, -1));

        jPanel2.add(cbTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 290, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Turno");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, 20));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("Estado");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, 20));
        jPanel2.add(txHoraMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 290, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 580, 150));

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista Turnos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 580, 260));

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
 dispose();
}//GEN-LAST:event_bSalirActionPerformed
private void bCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCrearActionPerformed
 CrearBanco();
}//GEN-LAST:event_bCrearActionPerformed
private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed

    if(tBancos.getSelectedRowCount()==1){
        if(bModificar.getText().equals("Editar")){
            VerBanco();
        }else{
            ModificarBanco();
        }
    }
    
}//GEN-LAST:event_bModificarActionPerformed
private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
 Cancelar();
}//GEN-LAST:event_bCancelarActionPerformed
private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
 
    if(tBancos.getSelectedRowCount()==1){
        EliminarBanco(); 
    }
    
}//GEN-LAST:event_bEliminarActionPerformed
private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
 BuscarBanco();
}//GEN-LAST:event_txBuscarKeyReleased
private void tBancosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tBancosMouseClicked
 if(evt.getClickCount()==2){
    if(tBancos.getSelectedRowCount()==1){
        VerBanco();
    }
 }
    
    
}//GEN-LAST:event_tBancosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bCrear;
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton bModificar;
    private javax.swing.JButton bSalir;
    private javax.swing.JComboBox cbEstado;
    private javax.swing.JComboBox cbTurno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tBancos;
    private javax.swing.JTextField txBuscar;
    private javax.swing.JTextField txHoraMax;
    private javax.swing.JTextField txHoraMin;
    // End of variables declaration//GEN-END:variables
}
