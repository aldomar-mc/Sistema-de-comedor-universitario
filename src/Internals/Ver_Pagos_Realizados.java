package Internals;
/**** @author Ing. Miguel Angel Silva Zapata.   **********/
import Dialogs.Ver_Cronograma;
import javax.swing.*;import Clases.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ver_Pagos_Realizados extends javax.swing.JInternalFrame {

    /*************************Atributos**********************/ 
    Mimodelo modelo=new Mimodelo();
    Controlador control=new Controlador();
    String idLinProd="",versentencia="";
    InfoGeneral info= new InfoGeneral();
    /*************************Atributos**********************/ 
    
    /*************************Métodos************************/     


    public void VerLineaproducto(){
     control.fila=tLineaproducto.getSelectedRow();
     if(control.fila>=0){
      idLinProd=tLineaproducto.getValueAt(control.fila,0).toString();
     }    
    }
    public void MostrarLineaproducto(){
     BuscarLineaproducto();    
    }
    public void BuscarLineaproducto(){
        int tipocom=0;
        if(cbTipoComensal.getSelectedItem().toString().equals("TODOS")){
            tipocom=4;
        }else{
            tipocom=Integer.parseInt(control.DevolverRegistroDto("select idtipocomensal from tipocomensal where descritipcom='"+cbTipoComensal.getSelectedItem().toString()+"';", 1));
        }
        if(tipocom==1){
            control.Sql=" select idpago,descritipcom,codfacu,concat(categor,' - ',ta.monto),dni, a.nombres,fechapag,descricomproba,numcomp,p.monto " +
           " from consumidores c,alumnos a,pago p,tipocomensal t, tarifas ta, facultad f, tipocomprobante tc " +
           " where c.idcomensales=a.idalumnos and c.idtipocomensal=t.idtipocomensal and c.idtipocomensal=1 " +
           " and c.idconsumidores=p.idconsumidores and c.idtarifas=ta.idtarifas and a.idfacultad=f.idfacultad " +
           " and p.idtipocomprobante=tc.idtipocomprobante and (nombres like '%"+txBuscar.getText()+"%' or " +
           " dni like '%"+txBuscar.getText()+"%' or descricomproba like '%"+txBuscar.getText()+"%') "+versentencia ;
        }else{
            if(tipocom==2){
                control.Sql=" select idpago,descritipcom,'No Asignado',concat(categor,' - ',ta.monto),dni, a.nombres,fechapag,descricomproba,numcomp,p.monto " +
               " from consumidores c,docentes a,pago p,tipocomensal t, tarifas ta, tipocomprobante tc " +
               " where c.idcomensales=a.iddocentes and c.idtipocomensal=t.idtipocomensal and c.idtipocomensal=2 " +
               " and c.idconsumidores=p.idconsumidores and c.idtarifas=ta.idtarifas " +
               " and p.idtipocomprobante=tc.idtipocomprobante and (nombres like '%"+txBuscar.getText()+"%' or " +
               " dni like '%"+txBuscar.getText()+"%' or descricomproba like '%"+txBuscar.getText()+"%') "+versentencia;
            }else{
                if(tipocom==3){
                    control.Sql=" select idpago,descritipcom,'No Asignado',concat(categor,' - ',ta.monto),dni, a.nombres,fechapag,descricomproba,numcomp,p.monto " +
                   " from consumidores c,administrativos a,pago p,tipocomensal t, tarifas ta,  tipocomprobante tc " +
                   " where c.idcomensales=a.idadministrativos and c.idtipocomensal=t.idtipocomensal and c.idtipocomensal=3 " +
                   " and c.idconsumidores=p.idconsumidores and c.idtarifas=ta.idtarifas " +
                   " and p.idtipocomprobante=tc.idtipocomprobante and (nombres like '%"+txBuscar.getText()+"%' or " +
                   " dni like '%"+txBuscar.getText()+"%' or descricomproba like '%"+txBuscar.getText()+"%') "+versentencia;
                }else{
//                    if(tipocom==4){
//                    control.Sql=" select idpago,descritipcom,'No Asignado',concat(categor,' - ',ta.monto),dni, a.nombres,fechapag,descricomproba,numcomp,p.monto " +
//                   " from consumidores c,regcasosespeciales a,pago p,tipocomensal t, tarifas ta, casoespecial cas, tipocomprobante tc " +
//                   " where c.idcomensales=a.idregcasosespeciales and c.idtipocomensal=t.idtipocomensal and c.idtipocomensal=4 " +
//                   " and c.idconsumidores=p.idconsumidores and c.idtarifas=ta.idtarifas and a.idcasoespecial=cas.idcasoespecial" +
//                   " and p.idtipocomprobante=tc.idtipocomprobante and (nombres like '%"+txBuscar.getText()+"%' or " +
//                   " dni like '%"+txBuscar.getText()+"%' or descricomproba like '%"+txBuscar.getText()+"%') "+versentencia;
//                    }else{
                    /*    control.Sql="select idpago,descritipcom,codfacu,concat(categor,' - ',ta.monto),dni, a.nombres,fechapag,descricomproba,numcomp,p.monto from consumidores c,alumnos a,pago p,tipocomensal t, tarifas ta, facultad f, tipocomprobante tc  where c.idcomensales=a.idalumnos and c.idtipocomensal=t.idtipocomensal and c.idtipocomensal=1  and c.idconsumidores=p.idconsumidores and c.idtarifas=ta.idtarifas and a.idfacultad=f.idfacultad  and p.idtipocomprobante=tc.idtipocomprobante and "+
                    " (nombres like '%"+txBuscar.getText()+"%' or  dni like '%"+txBuscar.getText()+"%' or descricomproba like '%"+txBuscar.getText()+"%') "+versentencia+" union" +
                    " select idpago,descritipcom,'No Asignado',concat(categor,' - ',ta.monto),dni, a.nombres,fechapag,descricomproba,numcomp,p.monto  from consumidores c,docentes a,pago p,tipocomensal t, tarifas ta,  tipocomprobante tc  where c.idcomensales=a.iddocentes and c.idtipocomensal=t.idtipocomensal and c.idtipocomensal=2  and c.idconsumidores=p.idconsumidores and c.idtarifas=ta.idtarifas  and p.idtipocomprobante=tc.idtipocomprobante "+
                    " and (nombres like '%"+txBuscar.getText()+"%' or  dni like '%"+txBuscar.getText()+"%' or descricomproba like '%"+txBuscar.getText()+"%') "+versentencia+" union" +
                    " select idpago,descritipcom,'No Asignado',concat(categor,' - ',ta.monto),dni, a.nombres,fechapag,descricomproba,numcomp,p.monto  from consumidores c,administrativos a,pago p,tipocomensal t, tarifas ta, tipocomprobante tc  where c.idcomensales=a.idadministrativos and c.idtipocomensal=t.idtipocomensal and c.idtipocomensal=3  and c.idconsumidores=p.idconsumidores and c.idtarifas=ta.idtarifas  and p.idtipocomprobante=tc.idtipocomprobante and"+
                    " (nombres like '%"+txBuscar.getText()+"%' or  dni like '%"+txBuscar.getText()+"%' or descricomproba like '%"+txBuscar.getText()+"%') "+versentencia+" union "+
                    " select idpago,descritipcom,codcasoesp,concat(categor,' - ',ta.monto),dni, a.nombres,fechapag,descricomproba,numcomp,p.monto from consumidores c,regcasosespeciales a,pago p,tipocomensal t, tarifas ta, casoespecial f, tipocomprobante tc  where c.idcomensales=a.idregcasosespeciales and c.idtipocomensal=t.idtipocomensa4 and c.idtipocomensal=4  and c.idconsumidores=p.idconsumidores and c.idtarifas=ta.idtarifas and a.idcasoespecial=f.idcasoespecial  and p.idtipocomprobante=tc.idtipocomprobante and ";
                        */
                    control.Sql="select idpago,descritipcom,codfacu,concat(categor,' - ',ta.monto),dni, a.nombres,fechapag,descricomproba,numcomp,p.monto from consumidores c,alumnos a,pago p,tipocomensal t, tarifas ta, facultad f, tipocomprobante tc  where c.idcomensales=a.idalumnos and c.idtipocomensal=t.idtipocomensal and c.idtipocomensal=1  and c.idconsumidores=p.idconsumidores and c.idtarifas=ta.idtarifas and a.idfacultad=f.idfacultad  and p.idtipocomprobante=tc.idtipocomprobante and "+
                    " (nombres like '%"+txBuscar.getText()+"%' or  dni like '%"+txBuscar.getText()+"%' or descricomproba like '%"+txBuscar.getText()+"%') "+versentencia+" union" +
                    " select idpago,descritipcom,'No Asignado',concat(categor,' - ',ta.monto),dni, a.nombres,fechapag,descricomproba,numcomp,p.monto  from consumidores c,docentes a,pago p,tipocomensal t, tarifas ta,  tipocomprobante tc  where c.idcomensales=a.iddocentes and c.idtipocomensal=t.idtipocomensal and c.idtipocomensal=2  and c.idconsumidores=p.idconsumidores and c.idtarifas=ta.idtarifas  and p.idtipocomprobante=tc.idtipocomprobante "+
                    " and (nombres like '%"+txBuscar.getText()+"%' or  dni like '%"+txBuscar.getText()+"%' or descricomproba like '%"+txBuscar.getText()+"%') "+versentencia+" union" +
                    " select idpago,descritipcom,'No Asignado',concat(categor,' - ',ta.monto),dni, a.nombres,fechapag,descricomproba,numcomp,p.monto  from consumidores c,administrativos a,pago p,tipocomensal t, tarifas ta, tipocomprobante tc  where c.idcomensales=a.idadministrativos and c.idtipocomensal=t.idtipocomensal and c.idtipocomensal=3  and c.idconsumidores=p.idconsumidores and c.idtarifas=ta.idtarifas  and p.idtipocomprobante=tc.idtipocomprobante and"+
                    " (nombres like '%"+txBuscar.getText()+"%' or  dni like '%"+txBuscar.getText()+"%' or descricomproba like '%"+txBuscar.getText()+"%') "+versentencia+" ;";
                    
                                                
//                    }
                }
            }
        }
System.out.println(tipocom+"--"+control.Sql);
     control.LlenarJTabla(modelo,control.Sql,10);          
    }
    
    public void Cancelar(){
     

     
    }
    public Ver_Pagos_Realizados() {
     initComponents();setTitle("Ver Pagos Realizados");
     this.setFrameIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Vender.png")));
     tLineaproducto.setModel(modelo);modelo.setColumnIdentifiers(new String[] {"Id","Tipo Comensal","Facultad","Categoria","Dni/Codigo","Nombres","Fecha Pago","Comprobante","N° Comprobante","Monto Pago"});
     tLineaproducto.getColumnModel().getColumn(0).setMaxWidth(0);
     tLineaproducto.getColumnModel().getColumn(0).setMaxWidth(0);
     tLineaproducto.getColumnModel().getColumn(0).setPreferredWidth(0);
     tLineaproducto.getColumnModel().getColumn(2).setPreferredWidth(25);
     tLineaproducto.getColumnModel().getColumn(3).setPreferredWidth(20);
     tLineaproducto.getColumnModel().getColumn(4).setPreferredWidth(20);
     tLineaproducto.getColumnModel().getColumn(6).setPreferredWidth(20);
     tLineaproducto.getColumnModel().getColumn(7).setPreferredWidth(20);
     tLineaproducto.getColumnModel().getColumn(9).setPreferredWidth(20);
     control.LlenarCombo(cbTipoComensal, "select * from tipocomensal where idtipocomensal<>4 ",2);
     cbTipoComensal.addItem("TODOS");
     cbTipoComensal.setSelectedIndex(0);     
     cbEstado.addItem("ACTIVOS");
     cbEstado.addItem("ATENDIDOS");
     cbEstado.addItem("TODOS");
     versentencia=" and idpago in(select idpago from cronogramaconsumo where estadocrono=0)";
     cbEstado.setSelectedIndex(0);
     MostrarLineaproducto();
     control.forma_table_ver(modelo, tLineaproducto);
     cbTipoComensal.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             MostrarLineaproducto();
         }
     });
     cbEstado.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
             int est=cbEstado.getSelectedIndex();
             if(est==0){versentencia=" and idpago in(select idpago from cronogramaconsumo where estadocrono=0)";}
             if(est==1){versentencia=" and idpago in(select idpago from cronogramaconsumo where estadocrono=1)";}
             if(est==2){versentencia=" and idpago in(select idpago from cronogramaconsumo)";}
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
        jLabel3 = new javax.swing.JLabel();
        cbTipoComensal = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(51, 153, 255));
        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Pagos Realizados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 102))); // NOI18N
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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 970, 450));

        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });
        jPanel2.add(txBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 27, 300, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Buscar");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 20));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Estado");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, -1, 20));

        jPanel2.add(cbTipoComensal, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 160, -1));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 153));
        jButton1.setText("Anular Pago");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Tipo de Comensal");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, 20));

        jPanel2.add(cbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 140, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 990, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void tLineaproductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tLineaproductoMouseClicked

    if(evt.getClickCount()==2){
        if(tLineaproducto.getSelectedRowCount()==1){
          Ver_Cronograma crono = new Ver_Cronograma(null, true);
          crono.setLocationRelativeTo(this);
          crono.llenardatos(tLineaproducto.getValueAt(tLineaproducto.getSelectedRow(), 0).toString());
          crono.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Selecione un Registro para Editar","",3);
        }
    }
    
}//GEN-LAST:event_tLineaproductoMouseClicked

private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
 BuscarLineaproducto();
}//GEN-LAST:event_txBuscarKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    AnularPago();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbEstado;
    private javax.swing.JComboBox cbTipoComensal;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tLineaproducto;
    private javax.swing.JTextField txBuscar;
    // End of variables declaration//GEN-END:variables

    private void AnularPago() {
        if(tLineaproducto.getSelectedRowCount()==1){
            if(JOptionPane.showConfirmDialog(null, "Desea Anular el Pago Realizado?","System Message",JOptionPane.YES_NO_OPTION)==0){
                idLinProd=tLineaproducto.getValueAt(control.fila,0).toString();
                control.Sql=String.format("delete from cronogramaconsumo where idpago='%s';", idLinProd);
                control.EliminarRegistro(control.Sql);
                control.Sql=String.format("delete from pago where idpago='%s';", idLinProd);
                control.EliminarRegistro(control.Sql);
                MostrarLineaproducto();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecione un Registro para anularlo!!!","",3);
        }
    }
}
