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

public class Ver_Total_Pagos extends javax.swing.JInternalFrame {

    /*************************Atributos**********************/ 
    Mimodelo modelo=new Mimodelo();
    Controlador control=new Controlador();
    String idLinProd="",versentencia="",totalIngresos="";
    InfoGeneral info= new InfoGeneral();
    IMPRIMIR impr= new IMPRIMIR();
    /*************************Atributos**********************/ 
    
    /*************************Métodos************************/     



    public void MostrarLineaproducto(){
     BuscarLineaproducto();    
    }
    public void BuscarLineaproducto(){
        //int tipocom=0;
        if(cbTipoComensal.getSelectedItem().toString().equals("TODOS")){
            versentencia=" and descritipcom like '%%' ";
        }else{
            versentencia=" and descritipcom like '%"+cbTipoComensal.getSelectedItem().toString()+"%' ";
        }        
         control.Sql="SELECT * FROM comensale where  idtipocomensal <> 4 and fechapag between '"+control.Formato_Amd(jdFecMin.getDate())+"' and '"+control.Formato_Amd(jdFecMax.getDate())+"'"+
                versentencia+
                " and (dni like '%"+txBuscar.getText()+"%' or nombres like '%"+txBuscar.getText()+"%'); ";
        System.out.println(control.Sql);
     control.LlenarJTabla(modelo,control.Sql,10);
     control.Sql="SELECT count(monto),sum(monto) FROM comensale where fechapag between '"+control.Formato_Amd(jdFecMin.getDate())+"' and '"+control.Formato_Amd(jdFecMax.getDate())+"' "+ versentencia;
     int cant=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
     if(cant==0){
         lbTotalIngresos.setText("Total  S/. 0.00");
         totalIngresos="0.00";
     }else{
         lbTotalIngresos.setText("Total  S/. "+control.DevolverRegistroDto(control.Sql, 2));
         totalIngresos=control.DevolverRegistroDto(control.Sql, 2);
     }
    }
    
    public void Cancelar(){
    }
    public Ver_Total_Pagos() {
     initComponents();setTitle("Ver Ingresos");
     this.setFrameIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Vender.png")));
     tLineaproducto.setModel(modelo);modelo.setColumnIdentifiers(new String[] {"Id","Tipo Comensal","Facultad","Categoria","Dni","Nombres","Fecha Pago","Comprobante","N° Comprobante","Monto Pago"});
     tLineaproducto.getColumnModel().getColumn(0).setMaxWidth(0);
     tLineaproducto.getColumnModel().getColumn(0).setMaxWidth(0);
     tLineaproducto.getColumnModel().getColumn(0).setPreferredWidth(0);
     tLineaproducto.getColumnModel().getColumn(2).setPreferredWidth(25);
     tLineaproducto.getColumnModel().getColumn(3).setPreferredWidth(20);
     tLineaproducto.getColumnModel().getColumn(4).setPreferredWidth(20);
     tLineaproducto.getColumnModel().getColumn(4).setPreferredWidth(120);
     tLineaproducto.getColumnModel().getColumn(6).setPreferredWidth(20);
     txBuscar.grabFocus();
     control.LlenarCombo(cbTipoComensal, "select * from tipocomensal where idtipocomensal<>4 ",2);
     cbTipoComensal.addItem("TODOS");
     cbTipoComensal.setSelectedItem("TODOS");     
     jdFecMin.setDate(new Date());
     jdFecMax.setDate(new Date());
     versentencia=" and idpago in(select idpago from cronogramaconsumo where estadocrono=0)";
     MostrarLineaproducto();
     control.forma_table_ver(modelo, tLineaproducto);
     cbTipoComensal.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             MostrarLineaproducto();
         }
     });
    jdFecMin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
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
    jdFecMax.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
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
        jLabel3 = new javax.swing.JLabel();
        cbTipoComensal = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jdFecMin = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        lbTotalIngresos = new javax.swing.JLabel();
        jdFecMax = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 153, 255));
        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Ingresos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 102))); // NOI18N
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
        jPanel2.add(txBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 440, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Buscar");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, 20));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Fecha Inicio");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, 20));

        jPanel2.add(cbTipoComensal, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 230, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Tipo de Comensal");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));
        jPanel2.add(jdFecMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 120, -1));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 153));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Print.png"))); // NOI18N
        jButton1.setText("Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 30, 120, -1));

        lbTotalIngresos.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbTotalIngresos.setForeground(new java.awt.Color(0, 51, 153));
        lbTotalIngresos.setText(" ");
        jPanel2.add(lbTotalIngresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, 190, 20));
        jPanel2.add(jdFecMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 120, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("Fecha Final");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, -1, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 990, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void tLineaproductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tLineaproductoMouseClicked
    
}//GEN-LAST:event_tLineaproductoMouseClicked

private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
    BuscarLineaproducto();
}//GEN-LAST:event_txBuscarKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       String cadena=cbTipoComensal.getSelectedItem().toString();
      if(cadena.equals("TODOS")){
       cadena="";   
      }
        impr.Impresion4Parametros("fec", control.Formato_Amd(jdFecMin.getDate()),"fecf",control.Formato_Amd(jdFecMax.getDate()) , "agcad", cadena, "cant", totalIngresos, "Ver_Pagos_Realizados");
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbTipoComensal;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdFecMax;
    private com.toedter.calendar.JDateChooser jdFecMin;
    private javax.swing.JLabel lbTotalIngresos;
    private javax.swing.JTable tLineaproducto;
    private javax.swing.JTextField txBuscar;
    // End of variables declaration//GEN-END:variables


}
