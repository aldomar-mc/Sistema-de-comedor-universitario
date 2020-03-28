
package Internals;
/**** @author Ing. Miguel Angel Silva Zapata.   **********/
import javax.swing.*;
import Clases.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import jxl.*;

import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.channels.FileChannel;
import java.util.Date;
import javax.swing.filechooser.FileNameExtensionFilter;
public class Registro_Asistencia_Manual extends javax.swing.JInternalFrame {
    /*************************Atributos**********************/ 
    Mimodelo modelo=new Mimodelo();
    Mimodelo modelo1=new Mimodelo();
    Controlador control=new Controlador();
    String idBnc;
    private double tarifa=0;
    private JFileChooser chooser = new JFileChooser();
    private JFileChooser chooserim = new JFileChooser();
    /*************************Atributos**********************/ 
    
    /*************************Métodos************************/ 
    public void CrearBanco(){   
     if(lbComensal.getText().trim().length()==0 ){
      JOptionPane.showMessageDialog(rootPane,"Faltan datos");   
      //txFoto.requestFocus();
     }    
     else{  
         String tipcompro=control.DevolverRegistroDto("select idtipocomprobante from tipocomprobante where descricomproba='"+cbturno.getSelectedItem().toString()+"';",1);
         
         String facu=control.DevolverRegistroDto("select idfacultad from facultad where descrifacu='';",1);
      control.Sql=String.format("select * from pago where  fecha='%s' and  idconsumidores='%s' and monto='%s' and numcomprobante='%s';",
              control.Formato_Amd(fcConsumo.getDate()),idBnc,txHoraReg.getText());
      if(control.Verificandoconsulta(control.Sql)!=true){
          
          try {
            control.Sql=String.format("insert into pago values(null,'%s','%s','%s','%s','%s','%s');",idBnc,control.Formato_Amd(fcConsumo.getDate()),tipcompro,"null",txHoraReg.getText());
            int idpago=control.executeAndGetId(control.Sql);
            control.fila=0;
            int dias=3;//Integer.parseInt(lbNumDiasConsumo.getText());
            while(control.fila<dias){
               control.Sql=String.format("insert into cronogramaconsumo values(null,'%s','%s','%s');",
               idpago,""+jTable1.getValueAt(control.fila, 2).toString(), "0");
               control.CrearRegistro(control.Sql );
               control.fila++;
            }
            JOptionPane.showMessageDialog(null, "Se ha Ingresado Correctamente el Pago y se Genero el Cronograma para el Comensal");
            MostrarBancos();
               Cancelar();            
             } catch (Exception e) {
                 e.printStackTrace();
             }
      }
      else{       
       JOptionPane.showMessageDialog(null, "EL Pago Ya se ha Registrado anteriormente\n Ya se genero el Cronograma","",3);
      }
      
     }   
    }
    public void Cancelar(){   
     tBancos.clearSelection();
     txBuscar.setText(null);
     txBuscar.grabFocus();
     cbturno.setSelectedIndex(-1);
     lbComensal.setText(" ");
     lbDni.setText(" ");
     lbHoraMax.setText(" ");
     txHoraReg.setText(" ");     
     lbHoraMin.setText(" ");
     fcConsumo.setDate(new Date());
     MostrarBancos();
     control.LimTabla(modelo1);
    }


    public void VerBanco(){
     control.fila=tBancos.getSelectedRow();
     if(control.fila>=0){
      idBnc =tBancos.getValueAt(control.fila,0).toString();
      lbComensal.setText(tBancos.getValueAt(control.fila,2).toString().toUpperCase());
      lbDni.setText(tBancos.getValueAt(control.fila,1).toString());
      control.Sql="SELECT idasistencia, descripturno, hora, fecha FROM vat_asistencia where dni='"+tBancos.getValueAt(control.fila,1).toString()+"' and fecha='"+control.Formato_Amd(fcConsumo.getDate())+"';";
      control.LlenarJTabla(modelo1, control.Sql, 3);
     }   
    }
    public void MostrarBancos(){    
     BuscarBanco();               
    }
    public void BuscarBanco(){
        control.Sql="SELECT idcronogramaconsumo,  dni, nombres, fechaconsumo "
                + "FROM crono_comensale where fechaconsumo='"+control.Formato_Amd(fcConsumo.getDate())+"' and estadocrono=0 and (dni like '%"+txBuscar.getText()+"%' or nombres like '%"+txBuscar.getText()+"%');";   
        System.out.println(control.Sql);
     control.LlenarJTabla(modelo,control.Sql,4);    
    }
    public void ValidardatosBancos(){
          
    }
    public Registro_Asistencia_Manual() {
     initComponents();setTitle("Registro Asistencia Manual");
     this.setFrameIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Vender.png")));
     tBancos.setModel(modelo);modelo.setColumnIdentifiers(new String[] {"Id","DNI/CODIGO","Nombres","Fecha"});
     tBancos.getColumnModel().getColumn(0).setMaxWidth(0);
     tBancos.getColumnModel().getColumn(0).setMinWidth(0);
     tBancos.getColumnModel().getColumn(0).setPreferredWidth(0);
     jTable1.setModel(modelo1);
     fcConsumo.setDate(new Date());
     modelo1.setColumnIdentifiers(new String[] {"N°","Turno","Hora","Fecha"});
     FormatoTabla ft= new FormatoTabla(2);
     tBancos.setDefaultRenderer(Object.class, ft);
     jTable1.setDefaultRenderer(Object.class, ft);

    // control.LlenarCombo(cbTipoComensal,"SELECT * FROM tipocomensal",2);
     control.LlenarCombo(cbturno,"SELECT * FROM turno;",2);
    
     MostrarBancos();
    
     cbturno.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             if(cbturno.getSelectedIndex()>=0){
                control.Sql="select * from turno where descripturno='"+cbturno.getSelectedItem().toString()+"';";
                lbHoraMin.setText(control.DevolverRegistroDto(control.Sql, 3));
                lbHoraMax.setText(control.DevolverRegistroDto(control.Sql, 4));
                txHoraReg.setText(control.DevolverRegistroDto(control.Sql, 3));
             }
         }
     });
       fcConsumo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if("date".equals(evt.getPropertyName())){
                    JDateChooser isse=(JDateChooser)evt.getSource();
                    boolean isdatasele=false;
                    try {
                        Field dateselfi=JDateChooser.class.getDeclaredField("dateSelect");
                        dateselfi.setAccessible(true);
                        isdatasele=dateselfi.getBoolean(isse);
                    } catch (Exception ignoreOrNot) {
                        MostrarBancos();
                        tBancos.clearSelection();
                        txBuscar.setText(null);
                        txBuscar.grabFocus();
                        cbturno.setSelectedIndex(-1);
                        lbComensal.setText(" ");
                        lbDni.setText(" ");
                        lbHoraMax.setText(" ");
                        txHoraReg.setText(" ");     
                        lbHoraMin.setText(" ");
                        control.LimTabla(modelo1);
                    }
                }
            }
        });
    }
    /*************************Métodos************************/

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tBancos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lbComensal = new javax.swing.JLabel();
        lbDni = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        fcConsumo = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        cbturno = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txHoraReg = new javax.swing.JTextField();
        lbHoraMin = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbHoraMax = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 153, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 640, 470));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Comensal", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbComensal.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbComensal.setForeground(new java.awt.Color(204, 51, 0));
        lbComensal.setText(" ");
        jPanel2.add(lbComensal, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 290, 20));

        lbDni.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbDni.setForeground(new java.awt.Color(204, 51, 0));
        jPanel2.add(lbDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 140, 20));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 153));
        jLabel11.setText("COMENSAL");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 20));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 153));
        jLabel10.setText("DNI");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 500, 90));

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Comensales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });
        jPanel3.add(txBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 280, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Buscar");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 153));
        jLabel14.setText("Fecha Pago");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, -1, 20));
        jPanel3.add(fcConsumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 130, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 660, 530));

        jPanel4.setBackground(new java.awt.Color(51, 153, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Pagos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.add(cbturno, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 130, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 153));
        jLabel9.setText("Hora Registro");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 80, 20));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 120, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 480, 250));

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Accept.png"))); // NOI18N
        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 153));
        jLabel13.setText("Turno");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));
        jPanel4.add(txHoraReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 130, -1));

        lbHoraMin.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbHoraMin.setForeground(new java.awt.Color(204, 51, 0));
        lbHoraMin.setText(" ");
        jPanel4.add(lbHoraMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 130, 20));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("Hora Minima");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 153));
        jLabel12.setText("Hora Maxima");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, -1, 20));

        lbHoraMax.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbHoraMax.setForeground(new java.awt.Color(204, 51, 0));
        lbHoraMax.setText(" ");
        jPanel4.add(lbHoraMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 130, 20));

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 51, 102));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancel.png"))); // NOI18N
        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 110, 500, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if( lbComensal.getText().trim().length()>0){
            generarCronograma();
        }else{
            JOptionPane.showMessageDialog(null,"Selecione un Comensal e Ingrese El monto del Pago");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Cancelar();
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbturno;
    private com.toedter.calendar.JDateChooser fcConsumo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbComensal;
    private javax.swing.JLabel lbDni;
    private javax.swing.JLabel lbHoraMax;
    private javax.swing.JLabel lbHoraMin;
    private javax.swing.JTable tBancos;
    private javax.swing.JTextField txBuscar;
    private javax.swing.JTextField txHoraReg;
    // End of variables declaration//GEN-END:variables

    
    private void generarCronograma(){
        
        String dni=lbDni.getText().trim();
        if(dni.trim().length()>0){
            control.Sql="SELECT count(*) FROM comensale c where dni='"+dni+"';";
            int cntcon=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
            if(cntcon==1){
                control.Sql="SELECT count(*) FROM crono_comensale where dni='"+dni+"' and fechaconsumo=curdate()and estadocrono=0;";
                int vercrono=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
                if(vercrono==1){
                    control.Sql="SELECT idcronogramaconsumo FROM crono_comensale where dni='"+dni+"' and fechaconsumo=curdate();";
                    String idcrono=control.DevolverRegistroDto(control.Sql, 1);
                        control.Sql="select * from turno where descripturno='"+cbturno.getSelectedItem().toString()+"';";
                        String idturn=control.DevolverRegistroDto(control.Sql, 1);
                        control.Sql="select count(*) from asistencia where  idcronogramaconsumo='"+idcrono+"' and idturno='"+idturn+"';";
                        int vercua=Integer.parseInt(control.DevolverRegistroDto(control.Sql,1));
                        if(vercua==0){
                            control.Sql=String.format("insert into asistencia values(null,'%s','%s','%s','%s');",txHoraReg.getText(),idcrono,idturn,control.Formato_Amd(fcConsumo.getDate()));
                            control.CrearRegistro(control.Sql);
                            control.LimTabla(modelo1);
                            control.Sql="SELECT idasistencia, descripturno, hora, fecha FROM vat_asistencia where dni='"+tBancos.getValueAt(control.fila,1).toString()+"' and fecha='"+control.Formato_Amd(fcConsumo.getDate())+"';";
                            control.LlenarJTabla(modelo1, control.Sql, 3);
                        }else{
                            JOptionPane.showMessageDialog(null, "Usted ya Consumio la Racion de Hoy","System Message",3);
                        }
                }else{
                    JOptionPane.showMessageDialog(null,"Usted no tiene Asignado el Menu de Hoy!!","System Message",3);
                }
            }else{
                JOptionPane.showMessageDialog(null, "No Existe Registro con el N° de Dni "+dni);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Ingrese N° de DNI");
        }
    }
    

    private String diasespañol(String dia){
         String d="";
        switch(dia){
            case "Monday":
            d="Lunes";
            break;
            case "Tuesday":
            d="Martes";
            break;
            case "Wednesday":
            d="Miercoles";
            break;
            case "Thursday":
            d="Jueves";
            break;
            case "Friday":
            d="Viernes";
            break;
            case "Saturday":
            d="Sabado";
            break;
            case "Sunday":
            d="Domingo";
            break;       
        }
        return d;
    }
    private void leerArchivoExcel(String archivoDestino) {
        //jTextArea1.append("Iniciando Lectura del Archivo\n");
        String dni="",nombres="",gen="",facu="",tari="",semes="";
        int  countsemes=Integer.parseInt(control.DevolverRegistroDto("SELECT count(idsemestre) FROM semestre where estadosem=0;",1));
        if(countsemes==0){
            JOptionPane.showMessageDialog(null, "No hay Semestre Activo");
            
        }else{
          semes=control.DevolverRegistroDto("SELECT idsemestre FROM semestre where estadosem=0;",1);
            try {
                Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));
                for (int sheetNo = 0; sheetNo < archivoExcel.getNumberOfSheets(); sheetNo++) {
                    Sheet hoja = archivoExcel.getSheet(sheetNo);
                    int numFilas = hoja.getRows();
                    //jProgressBar1.setMaximum(numFilas);
                    //jProgressBar1.setValue(0);
                    for (int fila = 1; fila < numFilas; fila++) {
                        //jProgressBar1.setValue(fila);
                        //jTextArea1.append("Leyendo Fila [" + (fila + 1) + "]");
                        dni = hoja.getCell(0, fila).getContents();
                        if (dni.length() == 0) {
                            //jTextArea1.append("Errror en el formato del DNI\n");
                            JOptionPane.showMessageDialog(null, "Error en Formato Archivo");
                            break;
                        }
                        nombres=hoja.getCell(1, fila).getContents();
                        gen=hoja.getCell(2, fila).getContents();
                        facu=hoja.getCell(3, fila).getContents();
                        tari=hoja.getCell(4, fila).getContents();
                        ///semes=hoja.getCell(5, fila).getContents();
                        int vercantidad=Integer.parseInt(control.DevolverRegistroDto("select count(idalumnos),idalumnos from alumnos where dni='"+dni+"';", 1));
                        if(vercantidad==0){
                            control.Sql=String.format("insert into alumnos values(null,'%s','%s','%s','sinImagen','%s');", nombres,dni,gen,facu);
                            int idpers=control.executeAndGetId(control.Sql);
                            System.out.println(control.Sql);
                            //String tari=control.DevolverRegistroDto("SELECT * FROM tarifas where categor=substring('"+cbTarifa.getSelectedItem().toString()+"', 1,1) and estadotar=0 and idtipocomensal=1;",1);
                            control.Sql=String.format("insert into consumidores values(null,'%s','%s','0','%s','1');", 
                                    tari,semes,""+idpers) ;
                            control.CrearRegistro(control.Sql );
                        }else{
                            int idpers=Integer.parseInt(control.DevolverRegistroDto("select count(idalumnos),idalumnos from alumnos where dni='"+dni+"';", 2));
                            System.out.println("SELECT count(*) from consumidores where idcomensales='"+idpers+"' and idtipocomensal='1' and idsemestre='"+semes+"' and estado='0';");
                            int vercom=Integer.parseInt(control.DevolverRegistroDto("SELECT count(*) from consumidores where idcomensales='"+idpers+"' and idtipocomensal='1' and idsemestre='"+semes+"' and estado='0';", 1));
                            
                            
                            if(vercom==0){
                                control.Sql=String.format("insert into consumidores values(null,'%s','%s','0','%s','1');",tari,semes,""+idpers) ;
                                control.CrearRegistro(control.Sql );
                            }
                            
                        }
                    }
                }
                MostrarBancos();
            } catch (Exception ioe) {
                ioe.printStackTrace();
            }  
        }
        
    }
    
         public void CopiarImagen(String rtaorigen,String rtadestino){
        File dirOrigen=new File(rtaorigen);
        File dirSalida=new File(rtadestino);
  //File in=new File(rtadestino);
     try{
        FileInputStream fis = new FileInputStream(dirOrigen); //inFile -> Archivo a copiar
        FileOutputStream fos = new FileOutputStream(dirSalida); //outFile -> Copia del archivo
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        fis.close();fos.close();   
     }
     catch(IOException ioe){ ioe.printStackTrace();}   
 }
       public String ver(String dato){
      String d="";
      char da=0;
      
      for(int a=0;a<dato.length();a++){
        da=dato.charAt(a);
        if(da==92){
            d=d+da+da;
       //     System.out.println(da+" g");
        }else{
            d=d+da;
         //   System.out.println(da+" gg");
        }
      }
      return d;
  }
       public String capturar_extencion(String arch){
        String exte="";
        char bit=0;
        boolean cntl=false;
        for(int a=0; a<arch.length();a++){
            bit=arch.charAt(a);
            if(bit==46){
                cntl=true;
            }
            if(cntl){
                exte=exte+bit;
            }
        }
        return exte;
    }
}

