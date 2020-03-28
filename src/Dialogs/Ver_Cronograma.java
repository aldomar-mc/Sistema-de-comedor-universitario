/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dialogs;
import Clases.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Silva
 */
public class Ver_Cronograma extends javax.swing.JDialog {
Controlador control = new Controlador();
Mimodelo modelo = new Mimodelo();
    /**
     * Creates new form NewJDialog
     */
    public Ver_Cronograma(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jTable1.setModel(modelo);
        this.setTitle("Cronograma de Consumo");
        modelo.setColumnIdentifiers(new String[] {"Id","#","Dia","Fecha","Estado","Comida"});
        //control.forma_table_ver(modelo, jTable1);
        FormatoTabla ft=new FormatoTabla(3);
        jTable1.setDefaultRenderer(Object.class, ft);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        
    }
public void llenardatos(String idpag){
    control.Sql=String.format("select idtipocomensal from pago p, consumidores c where c.idconsumidores=p.idconsumidores and idpago='%s';", idpag);
    System.out.println(String.format("select idtipocomensal from pago p, consumidores c where c.idconsumidores=p.idconsumidores and idpago='%s';", idpag));
    int idtipco=Integer.parseInt(control.DevolverRegistroDto(control.Sql,1));
    control.fila=0;
    String [] datos=new String[6];
    control.Sql="select count(*) from cronogramaconsumo where idpago='"+idpag+"';";
        int cntcrono=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
    if(idtipco==1){
        control.Sql="select p.idpago,descritipcom,codfacu,concat(categor,' - ',ta.monto),dni, a.nombres,"+
                " fechapag,descricomproba,numcomp,p.monto,fechaconsumo,"+
                " case estadocrono when 0 then 'Por Consumir' when 1 then 'Consumido' when 2 then 'Reservado' end as estado, "+
                " dayname(fechaconsumo) ,idcronogramaconsumo ,if(almu=0,'Todo','Almuerzo') as t" +
                " from consumidores c,alumnos a,pago p,tipocomensal t, tarifas ta,facultad f,tipocomprobante tc,cronogramaconsumo cr " +
                " where c.idcomensales=a.idalumnos and c.idtipocomensal=t.idtipocomensal and c.idtipocomensal=1 " +
                " and c.idconsumidores=p.idconsumidores and c.idtarifas=ta.idtarifas and a.idfacultad=f.idfacultad " +
                " and p.idtipocomprobante=tc.idtipocomprobante and p.idpago=cr.idpago and p.idpago='"+idpag+"' order by fechaconsumo asc;";
    }else{
        if(idtipco==2){
            control.Sql="select p.idpago,descritipcom,'No Asignado',concat(categor,' - ',ta.monto),dni, a.nombres,"+
                " fechapag,descricomproba,numcomp,p.monto,fechaconsumo,"+
                " case estadocrono when 0 then 'Por Consumir' when 1 then 'Consumido' when 2 then 'Reservado' end as estado,"+
                " dayname(fechaconsumo) ,idcronogramaconsumo ,if(almu=0,'Todo','Almuerzo') as t " +
                " from consumidores c,docentes a,pago p,tipocomensal t, tarifas ta, tipocomprobante tc,cronogramaconsumo cr " +
                " where c.idcomensales=a.iddocentes and c.idtipocomensal=t.idtipocomensal and c.idtipocomensal=2 " +
                " and c.idconsumidores=p.idconsumidores and c.idtarifas=ta.idtarifas  " +
                " and p.idtipocomprobante=tc.idtipocomprobante and p.idpago=cr.idpago and p.idpago='"+idpag+"' order by fechaconsumo asc;";
        }else{
            control.Sql="select p.idpago,descritipcom,'No Asignadi',concat(categor,' - ',ta.monto),dni, a.nombres,"+
                " fechapag,descricomproba,numcomp,p.monto,fechaconsumo,"+
                " case estadocrono when 0 then 'Por Consumir' when 1 then 'Consumido' when 2 then 'Reservado' end as estado,"+
                " dayname(fechaconsumo),idcronogramaconsumo ,if(almu=0,'Todo','Almuerzo') as t " +
                " from consumidores c,administrativos a,pago p,tipocomensal t, tarifas ta, tipocomprobante tc,cronogramaconsumo cr " +
                " where c.idcomensales=a.idadministrativos and c.idtipocomensal=t.idtipocomensal and c.idtipocomensal=3 " +
                " and c.idconsumidores=p.idconsumidores and c.idtarifas=ta.idtarifas  " +
                " and p.idtipocomprobante=tc.idtipocomprobante and p.idpago=cr.idpago and p.idpago='"+idpag+"' order by fechaconsumo asc;";
        }
    }
    
    System.out.println(control.Sql);
            lbComensal.setText(control.DevolverRegistroDto(control.Sql, 6).toUpperCase());
            lbDni.setText(control.DevolverRegistroDto(control.Sql, 5));
            lbTarifa.setText(control.DevolverRegistroDto(control.Sql, 4));
            lbComprobante.setText(control.DevolverRegistroDto(control.Sql, 8));
            lbNumComprobante.setText(control.DevolverRegistroDto(control.Sql, 9));
            lbFechaPago.setText(control.DevolverRegistroDto(control.Sql, 7));
            lbMontoPagado.setText(control.DevolverRegistroDto(control.Sql, 10));
            lbTipoComensal.setText(control.DevolverRegistroDto(control.Sql, 2));
            control.fila=0;
           try {
               control.Base.st=control.Base.conec.createStatement();
               control.Base.rt=control.Base.st.executeQuery(control.Sql);
               while (control.Base.rt.next()) {                   
                    datos[0]=control.Base.rt.getString(14);
                    datos[1]=""+(control.fila+1);
                    datos[2]=control.diasespañol(control.Base.rt.getString(13));
                    datos[3]=control.Base.rt.getString(11);
                    datos[4]=control.Base.rt.getString(12);
                    datos[5]=control.Base.rt.getString(15);
                    control.fila++;
                    modelo.addRow(datos);
               }
            } catch (Exception e) {
                e.printStackTrace();
            }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lbTarifa = new javax.swing.JLabel();
        lbComensal = new javax.swing.JLabel();
        lbDni = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbComprobante = new javax.swing.JLabel();
        lbNumComprobante = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbMontoPagado = new javax.swing.JLabel();
        lbFechaPago = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbTipoComensal = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(51, 153, 255));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Comensal", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbTarifa.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbTarifa.setForeground(new java.awt.Color(204, 51, 0));
        lbTarifa.setText(" ");
        lbTarifa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbTarifa, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 290, 20));

        lbComensal.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbComensal.setForeground(new java.awt.Color(204, 51, 0));
        lbComensal.setText(" ");
        lbComensal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbComensal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 290, 20));

        lbDni.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbDni.setForeground(new java.awt.Color(204, 51, 0));
        lbDni.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 290, 20));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 153));
        jLabel11.setText("COMENSAL");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, 20));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 153));
        jLabel10.setText("DNI");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, -1, 20));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("TARIFA");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, -1, 20));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 153));
        jLabel13.setText("COMPROBANTE");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, 20));

        lbComprobante.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbComprobante.setForeground(new java.awt.Color(204, 51, 0));
        lbComprobante.setText(" ");
        lbComprobante.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbComprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 290, 20));

        lbNumComprobante.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbNumComprobante.setForeground(new java.awt.Color(204, 51, 0));
        lbNumComprobante.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbNumComprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 290, 20));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 153));
        jLabel14.setText("N° COMPROBANTE");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 20));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("FECHA PAGO");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, 20));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 153));
        jLabel15.setText("MONTO PAGO");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, 20));

        lbMontoPagado.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbMontoPagado.setForeground(new java.awt.Color(204, 51, 0));
        lbMontoPagado.setText(" ");
        lbMontoPagado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbMontoPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 290, 20));

        lbFechaPago.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbFechaPago.setForeground(new java.awt.Color(204, 51, 0));
        lbFechaPago.setText(" ");
        lbFechaPago.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbFechaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 290, 20));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 51, 153));
        jLabel16.setText("TIPO COMENSAL");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, 20));

        lbTipoComensal.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbTipoComensal.setForeground(new java.awt.Color(204, 51, 0));
        lbTipoComensal.setText(" ");
        lbTipoComensal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbTipoComensal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 290, 20));

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cronograma de Consumo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 26, 480, 170));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if(evt.getClickCount()==2){
            if(jTable1.getSelectedRowCount()==1){
                if(modelo.getValueAt(jTable1.getSelectedRow(), 4).toString().equals("Por Consumir")){
                    String alm=modelo.getValueAt(jTable1.getSelectedRow(), 5).toString();
                    if(JOptionPane.showConfirmDialog(null, "Desea Reprogramar el Cronograma?","",JOptionPane.YES_NO_OPTION)==0){
                        this.dispose();
                        Licencias_Consumo lic= new Licencias_Consumo(null, true);
                        lic.setLocationRelativeTo(this);
                        lic.llenardatos(modelo.getValueAt(jTable1.getSelectedRow(), 0).toString(),alm);
                        lic.setVisible(true);
                    }
                }else{
                    if(modelo.getValueAt(jTable1.getSelectedRow(), 4).toString().equals("Reservado")){
                        control.Sql="select  motivo, numdoc, nufecha, descricomproba from licencias l, tipocomprobante t where l.idtipocomprobante=t.idtipocomprobante and idcronogramaconsumo='"+modelo.getValueAt(jTable1.getSelectedRow(), 0).toString()+"';";
                        JOptionPane.showMessageDialog(null, 
                                "Motivo de Licencia: "+control.DevolverRegistroDto(control.Sql, 1)+
                                "\nTipo Documento: "+control.DevolverRegistroDto(control.Sql, 4)+
                                "\nN° Documento: "+control.DevolverRegistroDto(control.Sql, 2)+
                                "\nNueva Fecha: "+control.DevolverRegistroDto(control.Sql, 3));
                    }else{
                        //JOptionPane.showMessageDialog(, evt);
                    }
                }
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ver_Cronograma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ver_Cronograma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ver_Cronograma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ver_Cronograma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Ver_Cronograma dialog = new Ver_Cronograma(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbComensal;
    private javax.swing.JLabel lbComprobante;
    private javax.swing.JLabel lbDni;
    private javax.swing.JLabel lbFechaPago;
    private javax.swing.JLabel lbMontoPagado;
    private javax.swing.JLabel lbNumComprobante;
    private javax.swing.JLabel lbTarifa;
    private javax.swing.JLabel lbTipoComensal;
    // End of variables declaration//GEN-END:variables
}
