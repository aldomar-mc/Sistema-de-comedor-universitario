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

public class Ver_Comensales_Inscritos extends javax.swing.JInternalFrame {

    /*************************Atributos**********************/ 
    Mimodelo modelo=new Mimodelo();
    Controlador control=new Controlador();
    String idLinProd="",versentencia="";
    InfoGeneral info= new InfoGeneral();
    IMPRIMIR imprime=new IMPRIMIR();
    String cantid="";
    /*************************Atributos**********************/ 
    
    /*************************Métodos************************/     
   
    public void MostrarLineaproducto(){
     BuscarLineaproducto();    
    }
    public void BuscarLineaproducto(){
        //int tipocom=0;
        if(cbTipoComensal.getSelectedItem().toString().equals("TODOS")){
            versentencia=" ";
        }else{
            versentencia=" and  descritipcom='"+cbTipoComensal.getSelectedItem().toString()+"' ";
        }        
         control.Sql="SELECT idconsumidores,descritipcom, codfacu,categor,monto,dni,nombres,gene "
                 + "FROM inscritos where (dni like '%"+txBuscar.getText()+"%' or nombres like '%"+txBuscar.getText()+"%' or codfacu like '%"+txBuscar.getText()+"%') "+versentencia;
        System.out.println(control.Sql);
     control.LlenarJTabla(modelo,control.Sql,8);
     control.Sql="SELECT count(*) FROM inscritos "+ versentencia;
     int cant=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
     cantid=""+cant;
     if(cant==0){
         lbCantidadRaciones.setText("N° de Inscritos: 0");
     }else{
         lbCantidadRaciones.setText("N° de Inscritos: "+control.DevolverRegistroDto(control.Sql, 1));
     }
    }

    public Ver_Comensales_Inscritos() {
     initComponents();setTitle("Ver Inscritos en el Semestre");
     this.setFrameIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Vender.png")));
     tLineaproducto.setModel(modelo);modelo.setColumnIdentifiers(new String[] {"Id","Tipo Comensal","Facultad","Categoria","Monto","Dni","Nombres","Genero"});
     tLineaproducto.getColumnModel().getColumn(0).setMaxWidth(0);
     tLineaproducto.getColumnModel().getColumn(0).setMaxWidth(0);
     tLineaproducto.getColumnModel().getColumn(0).setPreferredWidth(0);
     tLineaproducto.getColumnModel().getColumn(2).setPreferredWidth(25);
     tLineaproducto.getColumnModel().getColumn(3).setPreferredWidth(20);
     tLineaproducto.getColumnModel().getColumn(4).setPreferredWidth(20);
     tLineaproducto.getColumnModel().getColumn(6).setPreferredWidth(140);
     txBuscar.grabFocus();
     control.LlenarCombo(cbTipoComensal, "select * from tipocomensal ",2);
     cbTipoComensal.addItem("TODOS");
     cbTipoComensal.setSelectedItem("TODOS");     
     //jDateChooser1.setDate(new Date());
     versentencia=" and idpago in(select idpago from cronogramaconsumo where estadocrono=0)";
     MostrarLineaproducto();
     control.forma_table_ver(modelo, tLineaproducto);
     cbTipoComensal.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             MostrarLineaproducto();
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
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 153, 255));
        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista Comensales Inscritos en el Semestre", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 102))); // NOI18N
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
        jPanel2.add(txBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 360, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Buscar");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, 20));

        lbCantidadRaciones.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbCantidadRaciones.setForeground(new java.awt.Color(0, 51, 153));
        lbCantidadRaciones.setText(" ");
        jPanel2.add(lbCantidadRaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 300, 40));

        jPanel2.add(cbTipoComensal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 360, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Tipo de Comensal");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 153));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Print.png"))); // NOI18N
        jButton1.setText("Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 30, -1, -1));

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
      }
        imprime.Impresion3Parametros("fec", "", "cant", cantid, "agcad", cadena, "Ver_Comensales_Semestre");
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbTipoComensal;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCantidadRaciones;
    private javax.swing.JTable tLineaproducto;
    private javax.swing.JTextField txBuscar;
    // End of variables declaration//GEN-END:variables


}
