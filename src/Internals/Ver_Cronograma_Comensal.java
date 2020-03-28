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

public class Ver_Cronograma_Comensal extends javax.swing.JInternalFrame {

    /*************************Atributos**********************/ 
    Mimodelo modelo=new Mimodelo();
    Mimodelo modelo1=new Mimodelo();
    Controlador control=new Controlador();
    String idLinProd="",versentencia="";
    InfoGeneral info= new InfoGeneral();
    IMPRIMIR imprime=new IMPRIMIR();
    String cantid="",dni="";
    int flag=0, id=0;
    /*************************Atributos**********************/ 
    
    /*************************Métodos************************/     
   
    public void MostrarLineaproducto(){
     BuscarLineaproducto();    
    }
    public void BuscarLineaproducto(){
        //int tipocom=0;
        if(cbTipoComensal.getSelectedItem().toString().equals("TODOS")){
            versentencia="  descritipcom like '%%' ";
        }else{
            versentencia=" descritipcom  like '%"+cbTipoComensal.getSelectedItem().toString()+"%' ";
        }        
         control.Sql="SELECT idconsumidores,descritipcom, codfacu,categor,monto,dni,nombres,gene "+
                 " FROM inscritos where (dni like '%"+txBuscar.getText()+"%' or nombres like '%"+txBuscar.getText()+"%') and "+versentencia;
        System.out.println(control.Sql);
     control.LlenarJTabla(modelo,control.Sql,8);
     control.Sql="SELECT count(*) FROM inscritos where  "+ versentencia;
     int cant=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
     cantid=""+cant;
//     if(cant==0){
//         lbCantidadRaciones.setText("N° de Inscritos: 0");
//     }else{
//         lbCantidadRaciones.setText("N° de Inscritos: "+control.DevolverRegistroDto(control.Sql, 1));
//     }
    }

    public Ver_Cronograma_Comensal() {
     initComponents();setTitle("Ver Cronogrma de Comensales");
     this.setFrameIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Vender.png")));
     tLineaproducto.setModel(modelo);modelo.setColumnIdentifiers(new String[] {"Id","Tipo Comensal","Facultad","Categoria","Monto","Dni","Nombres","Genero"});
     tLineaproducto.getColumnModel().getColumn(0).setMaxWidth(0);
     tLineaproducto.getColumnModel().getColumn(0).setMaxWidth(0);
     tLineaproducto.getColumnModel().getColumn(0).setPreferredWidth(0);
     tLineaproducto.getColumnModel().getColumn(1).setPreferredWidth(35);
     tLineaproducto.getColumnModel().getColumn(2).setPreferredWidth(25);
     tLineaproducto.getColumnModel().getColumn(3).setPreferredWidth(20);
     tLineaproducto.getColumnModel().getColumn(4).setPreferredWidth(20);
     tLineaproducto.getColumnModel().getColumn(5).setPreferredWidth(20);
     tLineaproducto.getColumnModel().getColumn(7).setPreferredWidth(20);
     tbFaltas.setModel(modelo1);modelo1.setColumnIdentifiers(new String[] {"Id","Fecha","Comida"});
     txBuscar.grabFocus();
     fcFinal.setDate(new Date());
     fcInicio.setDate(new Date());
     control.LlenarCombo(cbTipoComensal, "select * from tipocomensal ",2);
     cbTipoComensal.addItem("TODOS");
     cbTipoComensal.setSelectedItem("TODOS");     

     //jDateChooser1.setDate(new Date());
     versentencia=" and idpago in(select idpago from cronogramaconsumo where estadocrono=0)";
     MostrarLineaproducto();
//        FormatoTabla ft= new FormatoTabla(2);
//        tbFaltas.setDefaultRenderer(Object.class, ft);
     control.forma_table_ver(modelo1, tbFaltas);
     control.forma_table_ver(modelo, tLineaproducto);
     cbTipoComensal.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             MostrarLineaproducto();
         }
     });

      fcInicio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
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
                        CargarReporte();
                    }
                }
            }
        });
       fcFinal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
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
                        CargarReporte();
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
        cbTipoComensal = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbFaltas = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        lbNombres = new javax.swing.JLabel();
        fcInicio = new com.toedter.calendar.JDateChooser();
        fcFinal = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbDni = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 153, 255));
        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cronograma de Consumo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 102))); // NOI18N
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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 620, 420));

        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });
        jPanel2.add(txBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 320, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Buscar");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, 20));

        jPanel2.add(cbTipoComensal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 320, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Tipo de Comensal");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 153));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Print.png"))); // NOI18N
        jButton1.setText("Imprimir Todo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 140, -1));

        tbFaltas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbFaltas);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, 480, 390));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("Fecha Final");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 80, 70, 20));

        lbNombres.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbNombres.setForeground(new java.awt.Color(204, 0, 0));
        lbNombres.setText(" ");
        jPanel2.add(lbNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, 330, 20));
        jPanel2.add(fcInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, 130, -1));
        jPanel2.add(fcFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 80, 130, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 153));
        jLabel8.setText("Fecha Inicio");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 70, 20));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 153));
        jLabel9.setText("Nombre Comensal");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 100, 20));

        lbDni.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbDni.setForeground(new java.awt.Color(204, 0, 0));
        lbDni.setText(" ");
        jPanel2.add(lbDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, 330, 20));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 153));
        jLabel11.setText("Dni");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 30, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1140, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void tLineaproductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tLineaproductoMouseClicked

    if(evt.getClickCount()==2){
        
      ///System.out.println(tLineaproducto.getValueAt(tLineaproducto.getSelectedRow(), 5).toString());
      //imprime.Impresion2Parametros("agcad", tLineaproducto.getValueAt(tLineaproducto.getSelectedRow(), 5).toString(),"","","Ver_Estadistica_Persona");
        
        id=Integer.parseInt(tLineaproducto.getValueAt(tLineaproducto.getSelectedRow(), 0).toString());
        dni=tLineaproducto.getValueAt(tLineaproducto.getSelectedRow(), 5).toString();
        lbNombres.setText(tLineaproducto.getValueAt(tLineaproducto.getSelectedRow(), 6).toString());
        lbDni.setText(dni);
        CargarReporte();
    }
    
}//GEN-LAST:event_tLineaproductoMouseClicked

private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
 BuscarLineaproducto();
}//GEN-LAST:event_txBuscarKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      String cadena=cbTipoComensal.getSelectedItem().toString();
      if(cadena.equals("TODOS")){
       cadena="";   
      }
        imprime.Impresion4Parametros("dni",dni,"feci", control.Formato_Amd(fcInicio.getDate()), "fecf",control.Formato_Amd(fcFinal.getDate()) , "", "", "Ver_Reporte_Cronograma");
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbTipoComensal;
    private com.toedter.calendar.JDateChooser fcFinal;
    private com.toedter.calendar.JDateChooser fcInicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbDni;
    private javax.swing.JLabel lbNombres;
    private javax.swing.JTable tLineaproducto;
    private javax.swing.JTable tbFaltas;
    private javax.swing.JTextField txBuscar;
    // End of variables declaration//GEN-END:variables

    private void CargarReporte() {
        //if(){}
   
    String cadena="";
    
    
//    if(turno.equals("Almuerzo")==false){
//        cadena = " and almu='0'";
//    }
    control.Sql="SELECT idconsumidores, fechaconsumo, if(almu=0,'Todos','Almuerzo') FROM crono_comensale where dni = '"+dni+"' "+
            " and fechaconsumo between '"+control.Formato_Amd(fcInicio.getDate())+"' "+
            " and '"+control.Formato_Amd(fcFinal.getDate())+"' "+cadena+
            " and estadocrono<>2 order by fechaconsumo asc;";

//    control.Sql="SELECT " +
//            " idconsumidores, fechaconsumo, turno" +
//            " FROM vta_faltas where dni='"+dni+"' "+
//            " and fechaconsumo between '"+control.Formato_Amd(fcInicio.getDate())+"' "+
//            " and '"+control.Formato_Amd(fcFinal.getDate())+"' "+cadena+
//            " and turno like '%"+turno+"%' order by fechaconsumo ,idt asc;";
        
    System.out.println(control.Sql);
    control.LlenarJTabla(modelo1, control.Sql, 3);
    }


}
