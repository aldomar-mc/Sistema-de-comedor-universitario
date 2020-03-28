
package Internals;
/**** @author Ing. Miguel Angel Silva Zapata.   **********/
import javax.swing.*;
import Clases.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import jxl.*;

import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Date;
import javax.swing.filechooser.FileNameExtensionFilter;
public class Registro_Pago extends javax.swing.JInternalFrame {
    /*************************Atributos**********************/ 
    Mimodelo modelo=new Mimodelo();
    Mimodelo modelo1=new Mimodelo();
    Controlador control=new Controlador();
    String idBnc;
    private double tarifa=0;
    String soloal="0";
    private int identificador=0;
    private JFileChooser chooser = new JFileChooser();
    private JFileChooser chooserim = new JFileChooser();
    /*************************Atributos**********************/ 
    
    /*************************Métodos************************/ 
    public void CrearBanco(){   
     if(lbComensal.getText().trim().length()==0 ||  cbTipoComensal.getSelectedIndex()<0){
      JOptionPane.showMessageDialog(rootPane,"Faltan datos");   
      //txFoto.requestFocus();
     }    
     else{  
      control.Sql=String.format("select * from pago where  fecha='%s' and  idconsumidores='%s' and monto='%s' and numcomprobante='%s';",
              control.Formato_Amd(fcFechaUno.getDate()),idBnc,txmontoPago.getText(),txNuncomprobante.getText());
      if(control.Verificandoconsulta(control.Sql)!=true){
          
          try {
              if(identificador==4){                  
                  control.Sql=String.format("insert into pago values(null,'%s','0.00',curdate(),'1','null','001-00100','0');",idBnc);
              }else{
                    String tipcompro=control.DevolverRegistroDto("select idtipocomprobante from tipocomprobante where descricomproba='"+cbtipoComprobante.getSelectedItem().toString()+"';",1);
                    String facu=control.DevolverRegistroDto("select idfacultad from facultad where descrifacu='"+cbTipoComensal.getSelectedItem().toString()+"';",1);
                    control.Sql=String.format("insert into pago values(null,'%s','%s','%s','%s','%s','%s','1');",idBnc,txmontoPago.getText(),control.Formato_Amd(fcFechaUno.getDate()),tipcompro,"null",txNuncomprobante.getText());
              }
            int idpago=control.executeAndGetId(control.Sql);
            control.fila=0;
            int dias=Integer.parseInt(lbNumDiasConsumo.getText());
            while(control.fila<dias){
               control.Sql=String.format("insert into cronogramaconsumo values(null,'%s','%s','%s','%s');",
               idpago,""+jTable1.getValueAt(control.fila, 2).toString(), "0",soloal);
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
     txmontoPago.requestFocus();
     bCrear.setEnabled(true);
     txmontoPago.setText(null);
     cbtipoComprobante.setSelectedIndex(0);
     lbComensal.setText(" ");
     lbDni.setText(" ");
     lbEstado.setText(" ");
     lbNumDiasConsumo.setText(" ");
     lbTarifa.setText(" ");
     tarifa=0;
     cbtipoComprobante.setSelectedIndex(-1);
     fcFechaUno.setDate(new Date());
     fcFechaDos.setDate(new Date());
     txNuncomprobante.setText(" ");
     txmontoPago.setText(" ");
     control.LimTabla(modelo1);
     chbLun.setSelected(true);
     chbMar.setSelected(true);
     chbMier.setSelected(true);
     chbJuev.setSelected(true);
     chbVier.setSelected(true);
     chbSab.setSelected(false);
     chbDom.setSelected(false);
     jCheckBox1.setSelected(false);
     
     cbtipoComprobante.setEnabled(true);
     txNuncomprobante.setEnabled(true);
     //jDateChooser1.setEnabled(true);
     lbFechaFinal.setText("Inicio de Consumo:");
     lbFechaInicio.setText("Fecha Pago:");
     txmontoPago.setEnabled(true);
    }

   
    public void VerBanco(){
     control.fila=tBancos.getSelectedRow();
     if(control.fila>=0){
         if(identificador==4){
            idBnc =tBancos.getValueAt(control.fila,0).toString();
            lbComensal.setText(tBancos.getValueAt(control.fila,2).toString().toUpperCase());
            lbDni.setText(tBancos.getValueAt(control.fila,1).toString());
            lbEstado.setText(tBancos.getValueAt(control.fila,4).toString().toUpperCase());
            lbTarifa.setText(tBancos.getValueAt(control.fila,6).toString());
            tarifa=Double.parseDouble(tBancos.getValueAt(control.fila,6).toString());             
            cbtipoComprobante.setEnabled(false);
            txNuncomprobante.setEnabled(false);
            txmontoPago.setEnabled(false);
            lbFechaFinal.setText("Fecha Final:");
            lbFechaInicio.setText("Fecha Inicio:");
         }else{
            idBnc =tBancos.getValueAt(control.fila,0).toString();
            lbComensal.setText(tBancos.getValueAt(control.fila,2).toString().toUpperCase());
            lbDni.setText(tBancos.getValueAt(control.fila,1).toString());
            lbEstado.setText(tBancos.getValueAt(control.fila,4).toString().toUpperCase());
            lbTarifa.setText(tBancos.getValueAt(control.fila,6).toString());
            tarifa=Double.parseDouble(tBancos.getValueAt(control.fila,6).toString());
         }
     }   
    }
    public void MostrarBancos(){    
     BuscarBanco();               
    }
    public void BuscarBanco(){
        int idtipo=Integer.parseInt(control.DevolverRegistroDto("select * from tipocomensal where descritipcom='"+cbTipoComensal.getSelectedItem().toString()+"';", 1));
        identificador=idtipo;
        if(idtipo==1){
        control.Sql=" SELECT " +
                " idconsumidores,dni, nombres, if(genero='0','Masculino','Femenino'),  if(c.estado=0,'Activo','InActivo'),  descrifacu,  monto, categor, descrisemestre,  descritipcom " +
                " FROM alumnos a, consumidores c, facultad f,tarifas t,semestre s, tipocomensal tc " +
                " where a.idfacultad=f.idfacultad and a.idalumnos=c.idcomensales and c.idtipocomensal=tc.idtipocomensal " +
                " and c.idtarifas=t.idtarifas and c.idsemestre=s.idsemestre and tc.idtipocomensal='1' and s.estadosem='0' " +
                " and (dni like '%"+txBuscar.getText()+"%' or nombres like '%"+txBuscar.getText()+"%')";
            
        }else{
            if(idtipo==2){
                control.Sql=" SELECT " +
                        " idconsumidores,dni, nombres, if(genero='0','Masculino','Femenino'),  if(c.estado=0,'Activo','InActivo'),  'No Asignado',  monto, categor, descrisemestre,  descritipcom " +
                        " FROM docentes a, consumidores c,tarifas t,semestre s, tipocomensal tc " +
                        " where a.iddocentes=c.idcomensales and c.idtipocomensal=tc.idtipocomensal " +
                        " and c.idtarifas=t.idtarifas and c.idsemestre=s.idsemestre and tc.idtipocomensal='2' and s.estadosem='0' " +
                        " and (dni like '%"+txBuscar.getText()+"%' or nombres like '%"+txBuscar.getText()+"%')";

                }else{
                    if(idtipo==3){
                        control.Sql=" SELECT " +
                            " idconsumidores,dni, nombres, if(genero='0','Masculino','Femenino'),  if(c.estado=0,'Activo','InActivo'),  'No Asignado',  monto, categor, descrisemestre,  descritipcom " +
                            " FROM administrativos a, consumidores c,tarifas t,semestre s, tipocomensal tc " +
                            " where  a.idadministrativos=c.idcomensales and c.idtipocomensal=tc.idtipocomensal " +
                            " and c.idtarifas=t.idtarifas and c.idsemestre=s.idsemestre and tc.idtipocomensal='3' and s.estadosem='0' " +
                            " and (dni like '%"+txBuscar.getText()+"%' or nombres like '%"+txBuscar.getText()+"%')";
                    }else{
                        control.Sql=" SELECT " +
                        " idconsumidores,dni, nombres, if(genero='0','Masculino','Femenino'),  if(c.estado=0,'Activo','InActivo'),  codcasoesp,  monto, categor, descrisemestre,  descritipcom " +
                        " FROM regcasosespeciales a, consumidores c,tarifas t,semestre s, tipocomensal tc , casoespecial cas" +
                        " where  a.idregcasosespeciales=c.idcomensales and c.idtipocomensal=tc.idtipocomensal and a.idcasoespecial=cas.idcasoespecial " +
                        " and c.idtarifas=t.idtarifas and c.idsemestre=s.idsemestre and tc.idtipocomensal='4' and s.estadosem='0' " +
                        " and (dni like '%"+txBuscar.getText()+"%' or nombres like '%"+txBuscar.getText()+"%')";
                    }
                }
            }
        System.out.println(control.Sql);
     control.LlenarJTabla(modelo,control.Sql,10);    
    }
    public void ValidardatosBancos(){
          
    }
    public Registro_Pago() {
     initComponents();setTitle("Registro de Pagos");
     this.setFrameIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Vender.png")));
     tBancos.setModel(modelo);modelo.setColumnIdentifiers(new String[] {"Id","DNI/CODIGO","Nombres","Genero","Estado","Facultad","Tarifa","Categoria","Semestre","Tipo Comsumidor"});
     tBancos.getColumnModel().getColumn(0).setMaxWidth(0);
     tBancos.getColumnModel().getColumn(0).setMinWidth(0);
     tBancos.getColumnModel().getColumn(0).setPreferredWidth(0);
     tBancos.getColumnModel().getColumn(1).setPreferredWidth(30);
     tBancos.getColumnModel().getColumn(2).setPreferredWidth(140);
     tBancos.getColumnModel().getColumn(3).setPreferredWidth(30);
     tBancos.getColumnModel().getColumn(4).setMaxWidth(0);
     tBancos.getColumnModel().getColumn(4).setMinWidth(0);
     tBancos.getColumnModel().getColumn(4).setPreferredWidth(0);
     tBancos.getColumnModel().getColumn(5).setPreferredWidth(40);
     tBancos.getColumnModel().getColumn(6).setPreferredWidth(30);
     tBancos.getColumnModel().getColumn(7).setPreferredWidth(30);
     tBancos.getColumnModel().getColumn(8).setMaxWidth(0);
     tBancos.getColumnModel().getColumn(8).setMinWidth(0);
     tBancos.getColumnModel().getColumn(8).setPreferredWidth(0);
     tBancos.getColumnModel().getColumn(9).setMaxWidth(0);
     tBancos.getColumnModel().getColumn(9).setMinWidth(0);
     tBancos.getColumnModel().getColumn(9).setPreferredWidth(0);
     jTable1.setModel(modelo1);
     fcFechaUno.setDate(new Date());
     fcFechaDos.setDate(new Date());
     modelo1.setColumnIdentifiers(new String[] {"N°","Dia","Fecha"});
     bCrear.setMnemonic('c');bCancelar.setMnemonic('a');     
//     bModificar.setMnemonic('d');bEliminar.setMnemonic('e');
     bSalir.setMnemonic('s');
     FormatoTabla ft= new FormatoTabla(2);
     tBancos.setDefaultRenderer(Object.class, ft);
     jTable1.setDefaultRenderer(Object.class, ft);
     chbLun.setSelected(true);
     chbMar.setSelected(true);
     chbMier.setSelected(true);
     chbJuev.setSelected(true);
     chbVier.setSelected(true);
     chbSab.setSelected(false);
     chbDom.setSelected(false);
     control.LlenarCombo(cbTipoComensal,"SELECT * FROM tipocomensal",2);
     control.LlenarCombo(cbtipoComprobante,"SELECT * FROM tipocomprobante;",2);
     cbTipoComensal.setSelectedIndex(0);
     MostrarBancos();
     cbTipoComensal.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             MostrarBancos();
             Cancelar();
         }
     });
    }
    /*************************Métodos************************/

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bSalir = new javax.swing.JButton();
        bCrear = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tBancos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lbTarifa = new javax.swing.JLabel();
        lbComensal = new javax.swing.JLabel();
        lbDni = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbEstado = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbTipoComensal = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lbFechaFinal = new javax.swing.JLabel();
        cbtipoComprobante = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txmontoPago = new javax.swing.JTextField();
        fcFechaUno = new com.toedter.calendar.JDateChooser();
        lbNumDiasConsumo = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        chbDom = new javax.swing.JCheckBox();
        chbLun = new javax.swing.JCheckBox();
        chbMar = new javax.swing.JCheckBox();
        chbMier = new javax.swing.JCheckBox();
        chbJuev = new javax.swing.JCheckBox();
        chbVier = new javax.swing.JCheckBox();
        chbSab = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txNuncomprobante = new javax.swing.JTextField();
        lbFechaInicio = new javax.swing.JLabel();
        fcFechaDos = new com.toedter.calendar.JDateChooser();
        jCheckBox1 = new javax.swing.JCheckBox();

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
        jPanel1.add(bSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 100, 40));

        bCrear.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bCrear.setForeground(new java.awt.Color(0, 51, 102));
        bCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Accept.png"))); // NOI18N
        bCrear.setText("Crear");
        bCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCrearActionPerformed(evt);
            }
        });
        jPanel1.add(bCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 100, 40));

        bCancelar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bCancelar.setForeground(new java.awt.Color(0, 51, 102));
        bCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancel.png"))); // NOI18N
        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(bCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 110, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 480, 60));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 640, 470));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Comensal", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbTarifa.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbTarifa.setForeground(new java.awt.Color(204, 51, 0));
        lbTarifa.setText(" ");
        jPanel2.add(lbTarifa, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 140, 20));

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
        jLabel10.setText("CODIGO/DNI");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, 20));

        lbEstado.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbEstado.setForeground(new java.awt.Color(204, 51, 0));
        lbEstado.setText(" ");
        jPanel2.add(lbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 140, 20));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("TARIFA");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, 20));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 153));
        jLabel12.setText("ESTADO");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 500, 140));

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Comensales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });
        jPanel3.add(txBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 180, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Buscar");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        jPanel3.add(cbTipoComensal, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 150, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 153));
        jLabel8.setText("Tipo de Comensal");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, 20));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 660, 530));

        jPanel4.setBackground(new java.awt.Color(51, 153, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Pagos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("Monto de Pago");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 80, 80, 20));

        lbFechaFinal.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbFechaFinal.setForeground(new java.awt.Color(0, 51, 153));
        lbFechaFinal.setText("Inicio de Consumo");
        jPanel4.add(lbFechaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, -1, 20));

        jPanel4.add(cbtipoComprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 130, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 153));
        jLabel9.setText("N° Comprobante");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 100, 20));

        txmontoPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txmontoPagoKeyTyped(evt);
            }
        });
        jPanel4.add(txmontoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 130, -1));
        jPanel4.add(fcFechaUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 130, -1));

        lbNumDiasConsumo.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbNumDiasConsumo.setForeground(new java.awt.Color(0, 51, 153));
        lbNumDiasConsumo.setText("Numero de Dias");
        jPanel4.add(lbNumDiasConsumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 130, 20));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 153));
        jLabel15.setText("Numero de Dias");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 110, 90, 20));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 102));
        jButton1.setText("Limpiar Cronograma");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, -1, -1));

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

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 470, 170));

        chbDom.setText("Dom");
        jPanel4.add(chbDom, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, -1, -1));

        chbLun.setText("Lun");
        jPanel4.add(chbLun, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        chbMar.setText("Mar");
        jPanel4.add(chbMar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));

        chbMier.setText("Mier");
        jPanel4.add(chbMier, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, -1, -1));

        chbJuev.setText("Juev");
        jPanel4.add(chbJuev, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, -1, -1));

        chbVier.setText("Vier");
        jPanel4.add(chbVier, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, -1, -1));

        chbSab.setText("Sab");
        jPanel4.add(chbSab, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, -1));

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 102));
        jButton2.setText("Generar Cronograma");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 153));
        jLabel13.setText("Tipo Comprobante");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));
        jPanel4.add(txNuncomprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 100, -1));

        lbFechaInicio.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbFechaInicio.setForeground(new java.awt.Color(0, 51, 153));
        lbFechaInicio.setText("Fecha Pago");
        jPanel4.add(lbFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, 20));
        jPanel4.add(fcFechaDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 100, -1));

        jCheckBox1.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(0, 51, 153));
        jCheckBox1.setText("Solo Almuerzo");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel4.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 210, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, 500, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
 dispose();
}//GEN-LAST:event_bSalirActionPerformed
private void bCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCrearActionPerformed
 CrearBanco();
}//GEN-LAST:event_bCrearActionPerformed
private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
 Cancelar();
}//GEN-LAST:event_bCancelarActionPerformed
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        control.LimTabla(modelo1);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(identificador==4){
//            if(txmontoPago.getText().trim().length()>0 && lbComensal.getText().trim().length()>0){
                generarCronograma();
//            }else{
//                JOptionPane.showMessageDialog(null,"Selecione un Comensal e Ingrese El monto del Pago");
//            }
        }else{
        if(txmontoPago.getText().trim().length()>0 && lbComensal.getText().trim().length()>0){
            generarCronograma();
        }else{
            JOptionPane.showMessageDialog(null,"Selecione un Comensal e Ingrese El monto del Pago");
        }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txmontoPagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txmontoPagoKeyTyped
    control.Solonumeros(evt);
    }//GEN-LAST:event_txmontoPagoKeyTyped

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if(jCheckBox1.isSelected()){
            soloal="1";
        }else{
            soloal="0";
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bCrear;
    private javax.swing.JButton bSalir;
    private javax.swing.JComboBox cbTipoComensal;
    private javax.swing.JComboBox cbtipoComprobante;
    private javax.swing.JCheckBox chbDom;
    private javax.swing.JCheckBox chbJuev;
    private javax.swing.JCheckBox chbLun;
    private javax.swing.JCheckBox chbMar;
    private javax.swing.JCheckBox chbMier;
    private javax.swing.JCheckBox chbSab;
    private javax.swing.JCheckBox chbVier;
    private com.toedter.calendar.JDateChooser fcFechaDos;
    private com.toedter.calendar.JDateChooser fcFechaUno;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbComensal;
    private javax.swing.JLabel lbDni;
    private javax.swing.JLabel lbEstado;
    private javax.swing.JLabel lbFechaFinal;
    private javax.swing.JLabel lbFechaInicio;
    private javax.swing.JLabel lbNumDiasConsumo;
    private javax.swing.JLabel lbTarifa;
    private javax.swing.JTable tBancos;
    private javax.swing.JTextField txBuscar;
    private javax.swing.JTextField txNuncomprobante;
    private javax.swing.JTextField txmontoPago;
    // End of variables declaration//GEN-END:variables

    
    private void generarCronograma(){
        
        if(identificador==4){
            boolean flag=true;
            String fe="";
            String fecini=control.Formato_Amd(fcFechaUno.getDate());
            String fecfin=control.Formato_Amd(fcFechaDos.getDate());
            //int diastotal=Integer.parseInt(control.DevolverRegistroDto("select datediff( '"+fecfin+"','"+ fecini+"');",1));
            //lbNumDiasConsumo.setText(""+(diastotal+1));
            int contador=0,aunemt=0;
            String [] datos= new String[3];
            while(flag){
                control.Sql="select dayname(adddate('"+fecini+"', interval "+aunemt+" day)),adddate('"+fecini+"', interval "+aunemt+" day);";
                if(verdia(control.DevolverRegistroDto(control.Sql, 1))){
                    System.out.println(control.DevolverRegistroDto(control.Sql, 2));
                    System.out.println(control.DevolverRegistroDto(control.Sql, 1));
                    datos[1]=diasespañol(control.DevolverRegistroDto(control.Sql, 1));
                    datos[2]=control.DevolverRegistroDto(control.Sql, 2);
                    fe=control.DevolverRegistroDto(control.Sql, 2);
                    contador++;
                    datos[0]=""+contador;
                    modelo1.addRow(datos);
                }
                aunemt++;
                control.Sql="select datediff('"+fecfin+"','"+fe+"');";
                int ver=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
                if(ver==0){
                    lbNumDiasConsumo.setText(""+contador);
                    flag=false;
                }
            }
        }else{
            double pago=Double.parseDouble(txmontoPago.getText());
        if(pago%tarifa!=0){
            JOptionPane.showMessageDialog(null, "Ingrese una Cantidad Multiple de Su Tarifa!!!");
            txmontoPago.requestFocus();
            return;
        }
            control.LimTabla(modelo1);
            String fecini=control.Formato_Amd(fcFechaDos.getDate());
            String diain=control.DevolverRegistroDto("select dayname('"+fecini+"')",1);
            lbNumDiasConsumo.setText(String.valueOf((int)(pago/tarifa)));
            int diastotal=(int)(pago/tarifa);
            int contador=0,aunemt=0;
            String [] datos= new String[3];
            while(contador<diastotal){
                control.Sql="select dayname(adddate('"+fecini+"', interval "+aunemt+" day)),adddate('"+fecini+"', interval "+aunemt+" day);";
                if(verdia(control.DevolverRegistroDto(control.Sql, 1))){
                    System.out.println(control.DevolverRegistroDto(control.Sql, 2));
                    System.out.println(control.DevolverRegistroDto(control.Sql, 1));
                    datos[1]=diasespañol(control.DevolverRegistroDto(control.Sql, 1));
                    datos[2]=control.DevolverRegistroDto(control.Sql, 2);
                    contador++;
                    datos[0]=""+contador;
                    modelo1.addRow(datos);
                }
                aunemt++;
            }
        }
        
    }
    
    private boolean verdia(String dia){
        boolean d=true;
        switch(dia){
            case "Monday":
            if(chbLun.isSelected()){d=true;}else{d=false;}
            break;
            case "Tuesday":
            if(chbMar.isSelected()){d=true;}else{d=false;}
            break;
            case "Wednesday":
            if(chbMier.isSelected()){d=true;}else{d=false;}
            break;
            case "Thursday":
            if(chbJuev.isSelected()){d=true;}else{d=false;}
            break;
            case "Friday":
            if(chbVier.isSelected()){d=true;}else{d=false;}
            break;
            case "Saturday":
            if(chbSab.isSelected()){d=true;}else{d=false;}
            break;
            case "Sunday":
            if(chbDom.isSelected()){d=true;}else{d=false;}
            break;       
        }
        return d;
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

}

