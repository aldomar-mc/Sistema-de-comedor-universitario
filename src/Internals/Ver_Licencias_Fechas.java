package Internals;
/**** @author Ing. Miguel Angel Silva Zapata.   **********/
import Dialogs.Ver_Cronograma;
import javax.swing.*;import Clases.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.lang.reflect.Field;
import java.text.Format;
import java.util.Date;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ver_Licencias_Fechas extends javax.swing.JInternalFrame {

    /*************************Atributos**********************/ 
    Mimodelo modelo=new Mimodelo();
    Controlador control=new Controlador();
    String idLinProd="",versentencia="", cnatidaddeasis="2";
    InfoGeneral info= new InfoGeneral();
    IMPRIMIR impri= new IMPRIMIR();
    int idturno=0;
    /*************************Atributos**********************/ 
    
    /*************************Métodos************************/     
   
    public void MostrarLineaproducto(){
     BuscarLineaproducto();    
    }
    public void BuscarLineaproducto(){
//        control.Sql="SELECT count(*) FROM crono_comensale where fechaconsumo = '"+control.Formato_Amd(jDateChooser1.getDate())+"' and estadocrono<>2 "+ versentencia;
//     int cantTotal=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
//     idturno=Integer.parseInt(control.DevolverRegistroDto("select idturno from turno where descripturno='';",1));
        if(cbTipoComensal.getSelectedItem().toString().equals("TODOS")){
//            control.Sql="SELECT idcronogramaconsumo, descritipcom,codfacu, dni, nombres, fechaconsumo,'"+cbTurno.getSelectedItem().toString()+"' FROM crono_comensale" +
//            " where fechaconsumo='"+control.Formato_Amd(jDateChooser1.getDate())+"'  and estadocrono<>2 and"+
//            " descritipcom like '%%' and idcronogramaconsumo not in" +
//            " (select idcronogramaconsumo from asistencia where idturno='"+idturno+"')";
            control.Sql="select * from vta_licencias where fechaconsumo between '"+control.Formato_Amd(jDateChooser1.getDate())+"' and '"+control.Formato_Amd(jDateChooser2.getDate())+"' and descritipcom like '%%' and (dni like '%"+txBuscar.getText()+"%' or nombres like '%"+txBuscar.getText()+"%');";
        }else{
//                control.Sql="SELECT idcronogramaconsumo, descritipcom,codfacu, dni, nombres, fechaconsumo FROM crono_comensale" +
//            " where fechaconsumo='"+control.Formato_Amd(jDateChooser1.getDate())+"'  and estadocrono<>2 "+
//            " and descritipcom like '%"+cbTipoComensal.getSelectedItem().toString()+"%' and idcronogramaconsumo not in" +
//            " (select idcronogramaconsumo from asistencia where idturno='"+idturno+"')";
//                
            control.Sql="select * from vta_licencias where fechaconsumo between '"+control.Formato_Amd(jDateChooser1.getDate())+"' and '"+control.Formato_Amd(jDateChooser2.getDate())+"' and descritipcom like '%"+cbTipoComensal.getSelectedItem().toString()+"%' and (dni like '%"+txBuscar.getText()+"%' or nombres like '%"+txBuscar.getText()+"%');";
        }     

        System.out.println(control.Sql);
        
     control.LlenarJTabla(modelo,control.Sql,10);
//     control.Sql="SELECT count(*) FROM vat_asistencia where fecha= '"+control.Formato_Amd(jDateChooser1.getDate())+"' "+" and descripturno='' "+ versentencia;
//     int cant=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
//     if(cant==0){
//         lbCantidadRaciones.setText("N° de Aistentes: 0");
//         cnatidaddeasis=""+cantTotal;
//         lbFaltantes.setText("N° de Faltantes: "+(cantTotal-0));
//     }else{
//         cnatidaddeasis=""+(cantTotal-cant);
//         lbCantidadRaciones.setText("N° de Aistentes: "+cant);
//         lbFaltantes.setText("N° de Faltantes: "+(cantTotal-cant));
//     }
    }

    public Ver_Licencias_Fechas() {
     initComponents();setTitle("Ver Faltantes");
     this.setFrameIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Vender.png")));
     tLineaproducto.setModel(modelo);modelo.setColumnIdentifiers(new String[]{"Id", "Tipo Comensal", "Facultad", "Dni", "Nombres", "Fecha-Consumo", "Comprobante","N° Comprobante","Motivo", "Nueva-Fecha"});
     tLineaproducto.getColumnModel().getColumn(0).setMaxWidth(0);
     tLineaproducto.getColumnModel().getColumn(0).setMaxWidth(0);
     tLineaproducto.getColumnModel().getColumn(0).setPreferredWidth(0);
     tLineaproducto.getColumnModel().getColumn(2).setPreferredWidth(25);
     tLineaproducto.getColumnModel().getColumn(3).setPreferredWidth(20);
     tLineaproducto.getColumnModel().getColumn(4).setPreferredWidth(20);
     tLineaproducto.getColumnModel().getColumn(6).setPreferredWidth(20);
     txBuscar.grabFocus();
     control.LlenarCombo(cbTipoComensal, "select * from tipocomensal ",2);
     cbTipoComensal.addItem("TODOS");
     cbTipoComensal.setSelectedItem("TODOS");     
     jDateChooser1.setDate(new Date());
     jDateChooser2.setDate(new Date());
//     control.LlenarCombo(cbTurno,"select * from turno;", 2);
//     cbTurno.setSelectedIndex(0);
     //versentencia=" and idpago in(select idpago from cronogramaconsumo where estadocrono=0)";
     MostrarLineaproducto();
     control.forma_table_ver(modelo, tLineaproducto);
     cbTipoComensal.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             MostrarLineaproducto();
         }
     });
 
    jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
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
                        MostrarLineaproducto();
                        txBuscar.setText(null);
                        txBuscar.grabFocus();
                    }
                }
            }
        });
    jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
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
                        MostrarLineaproducto();
                        txBuscar.setText(null);
                        txBuscar.grabFocus();
                    }
                }
            }
        });
       
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tLineaproducto = new javax.swing.JTable();
        txBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lbCantidadRaciones = new javax.swing.JLabel();
        cbTipoComensal = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbFaltantes = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(51, 153, 255));
        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista Licencias", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 102))); // NOI18N
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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 970, 420));

        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });
        jPanel2.add(txBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 390, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Buscar");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, 20));

        lbCantidadRaciones.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        lbCantidadRaciones.setForeground(new java.awt.Color(0, 51, 153));
        lbCantidadRaciones.setText(" ");
        jPanel2.add(lbCantidadRaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 150, 30));

        jPanel2.add(cbTipoComensal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 150, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Tipo de Comensal");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));
        jPanel2.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 140, -1));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 153));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Print.png"))); // NOI18N
        jButton1.setText("Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 50, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("Fecha Inicio");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, 20));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 153));
        jLabel7.setText("Fecha Final");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, -1, 20));

        lbFaltantes.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        lbFaltantes.setForeground(new java.awt.Color(0, 51, 153));
        lbFaltantes.setText(" ");
        jPanel2.add(lbFaltantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 50, 160, 30));
        jPanel2.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 130, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 990, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void tLineaproductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tLineaproductoMouseClicked

    if(evt.getClickCount()==2){
      
    }
    
}//GEN-LAST:event_tLineaproductoMouseClicked

private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
 BuscarLineaproducto();
}//GEN-LAST:event_txBuscarKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      String cadena=cbTipoComensal.getSelectedItem().toString();
      if(cadena.equals("TODOS")){
       cadena="";   
      }System.out.println("fec:->"+ control.Formato_Amd(jDateChooser1.getDate())+ " agcad:->"+""+idturno+ "   cant:->"+ cnatidaddeasis+"  descr:->"+cadena+"  fecf:-> :->Ver_InAsistentes");
        impri.Impresion3Parametros("fec", control.Formato_Amd(jDateChooser1.getDate()), "agcad",cadena,"fecf",control.Formato_Amd(jDateChooser2.getDate()), "Ver_Licencias");
        
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbTipoComensal;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCantidadRaciones;
    private javax.swing.JLabel lbFaltantes;
    private javax.swing.JTable tLineaproducto;
    private javax.swing.JTextField txBuscar;
    // End of variables declaration//GEN-END:variables


}
