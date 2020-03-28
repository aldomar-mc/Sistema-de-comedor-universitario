
package Internals;
/**** @author Ing. Miguel Angel Silva Zapata.   **********/
import javax.swing.*;
import Clases.*;
import java.util.Date;
public class Semestre_Academico extends javax.swing.JInternalFrame {
    /*************************Atributos**********************/ 
    Mimodelo modelo=new Mimodelo();
    Controlador control=new Controlador();
    String idBnc;
    /*************************Atributos**********************/ 
    
    /*************************Métodos************************/ 
    public void CrearBanco(){   
     if(txBanco.getText().trim().length()==0){
      JOptionPane.showMessageDialog(rootPane,"Faltan datos");   
      txBanco.requestFocus();
     }    
     else{
      control.Sql="select * from semestre where descrisemestre='"+
      txBanco.getText()+"'";   
      if(control.Verificandoconsulta(control.Sql)){
       JOptionPane.showMessageDialog(rootPane,"El Semestre es correcto");          
      }
      else{       
       //control.Sql="insert into facultad  values(null,'','')";  
       control.Sql="update semestre set estadosem='1';";
       control.EditarRegistro(control.Sql);
       control.Sql=String.format("insert into semestre  values(null,'%s','%s','%s','%s')",control.PriLtrasMayuscula(txBanco.getText()), cbEstado.getSelectedIndex(),control.Formato_Amd(jDateChooser1.getDate()),control.Formato_Amd(jDateChooser2.getDate()));
       System.out.println(control.Sql);
       control.CrearRegistro(control.Sql );MostrarBancos(); 
      }
      Cancelar();
     }   
    }
    public void Cancelar(){
     txBanco.setText(null);cbEstado.setSelectedIndex(0);   
     tBancos.clearSelection();txBuscar.setText(null);
     txBanco.requestFocus();
     bModificar.setText("Editar");
     bCrear.setEnabled(true);
     bEliminar.setEnabled(true);
     jDateChooser1.setDate(new Date());
     jDateChooser2.setDate(new Date());
    }
    public void ModificarBanco(){
     if(txBanco.getText().length()>0){
      control.Sql="select * from semestre where descrisemestre='"+txBanco.getText()+
      "' and estadosem='"+cbEstado.getSelectedIndex()+"' and idsemestre<>'"+idBnc+"'";        
      if(!control.Verificarconsulta(control.Sql)){
          if(cbEstado.getSelectedIndex()==0){
              control.Sql="update semestre set estadosem='1';";
              control.EditarRegistro(control.Sql);
          }
       control.Sql=String.format("update semestre set descrisemestre='%s',estadosem='%s' ,fecini='%s', fecfin='%s' where idsemestre='%s';", txBanco.getText(),cbEstado.getSelectedIndex(),control.Formato_Amd(jDateChooser1.getDate()),control.Formato_Amd(jDateChooser2.getDate()), idBnc);
       //control.Sql="update semestre set descrisemestre='',estadosem='' where idsemestre='';";
       control.EditarRegistro(control.Sql);Cancelar();MostrarBancos();
       bModificar.setText("Editar");
       bCrear.setEnabled(true);
       bEliminar.setEnabled(true);
      }
      else{
       JOptionPane.showMessageDialog(rootPane,"Existe repeticion en la Denominación del Semestre");   
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
//      control.Sql="select * from cuentaban where idBanco='"+idBnc+"'";           
//      if(!control.Verificandoconsulta(control.Sql)){
       if(JOptionPane.showConfirmDialog(rootPane,"Seguro deseas eliminar","Confirmar",0)==0){
        control.Sql=String.format("delete from semestre where idsemestre='%s'", idBnc);
           //control.Sql="delete from semestre where idsemestre='"+idBnc+"'";  
        
        control.EliminarRegistro(control.Sql);
        Cancelar();MostrarBancos();    
       }          
//      }
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
      txBanco.setText(tBancos.getValueAt(control.fila,1).toString());   
      cbEstado.setSelectedItem(tBancos.getValueAt(control.fila,2).toString());
      control.PonerFechaenDateChooser(jDateChooser1, tBancos.getValueAt(control.fila,3).toString());
      control.PonerFechaenDateChooser(jDateChooser2, tBancos.getValueAt(control.fila,4).toString());
     }   
    }
    public void MostrarBancos(){    
     BuscarBanco();               
    }
    public void BuscarBanco(){
     control.Sql="select idsemestre, descrisemestre,if(estadosem=0, 'Activo','InActivo'),fecini,fecfin from semestre where (descrisemestre like '%"+txBuscar.getText()+"%') order by estadosem asc";
     control.LlenarJTabla(modelo,control.Sql,5);    
    }
    public void ValidardatosBancos(){
          
    }
    public Semestre_Academico() {
     initComponents();setTitle("Datos del Semestre Academico");
     this.setFrameIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Vender.png")));
     tBancos.setModel(modelo);modelo.setColumnIdentifiers(new String[] {"Id","Semestre","Estado","Fecha Inicio","Fecha Final"});
//     tBancos.getColumnModel().getColumn(0).setMaxWidth(0);
//     tBancos.getColumnModel().getColumn(0).setMinWidth(0);
//     tBancos.getColumnModel().getColumn(0).setPreferredWidth(0);
     tBancos.getColumnModel().getColumn(1).setPreferredWidth(200);
     tBancos.getColumnModel().getColumn(2).setPreferredWidth(150); 
     bCrear.setMnemonic('c');bCancelar.setMnemonic('a');     
     bModificar.setMnemonic('d');bEliminar.setMnemonic('e');
     bSalir.setMnemonic('s');MostrarBancos();
     control.forma_table_ver(modelo, tBancos);
     cbEstado.addItem("Activo");
     cbEstado.addItem("InActivo");
     cbEstado.setSelectedIndex(0);
     jDateChooser1.setDate(new Date());
     jDateChooser2.setDate(new Date());
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
        jLabel3 = new javax.swing.JLabel();
        txBuscar = new javax.swing.JTextField();
        txBanco = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tBancos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
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
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, 20));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Semestre Academico");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });
        getContentPane().add(txBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 490, -1));
        getContentPane().add(txBanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 290, -1));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 560, 210));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Semestre Academico", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("Fecha Final");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, 20));

        jPanel2.add(cbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 290, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Estado");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, 20));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("Fecha Inicio");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, 20));

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jPanel2.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 290, -1));

        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        jPanel2.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 290, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 580, 140));

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Semestres Academicos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 580, 260));

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
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
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
    private javax.swing.JTextField txBanco;
    private javax.swing.JTextField txBuscar;
    // End of variables declaration//GEN-END:variables
}
