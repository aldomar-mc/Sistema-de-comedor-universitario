
package Internals;
/**** @author Ing. Miguel Angel Silva Zapata.   **********/
import javax.swing.*;
import Clases.*;
import jxl.*;

import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import javax.swing.filechooser.FileNameExtensionFilter;
public class Registro_Administrativos extends javax.swing.JInternalFrame {
    /*************************Atributos**********************/ 
    Mimodelo modelo=new Mimodelo();
    Controlador control=new Controlador();
    String idBnc;
    private String ruta="";
    private JFileChooser chooser = new JFileChooser();
    private JFileChooser chooserim = new JFileChooser();
    /*************************Atributos**********************/ 
    
    /*************************Métodos************************/ 
    public void CrearBanco(){   
     if(txNombres.getText().trim().length()==0 || txDni.getText().trim().length()==0 ){
      JOptionPane.showMessageDialog(rootPane,"Faltan datos");   
      txFoto.requestFocus();
     }    
     else{
      String semes=control.DevolverRegistroDto("select idsemestre from semestre where descrisemestre='"+cbSemestre.getSelectedItem().toString()+"';",1);
      control.Sql="select * from administrativos where dni='"+txDni.getText()+"' ;";
      if(control.Verificandoconsulta(control.Sql)){
       //JOptionPane.showMessageDialog(rootPane,"El Docente ya esta Registrado en el sistema\nEs Nesecario la Actualizacion de la Informacion del Docente Para el Semestre ","",3);
          int idpers=Integer.parseInt(control.DevolverRegistroDto("select count(idadministrativos),idadministrativos from administrativos where dni='"+txDni.getText()+"';", 2));
          String tari=control.DevolverRegistroDto("SELECT * FROM tarifas where categor=substring('"+cbTarifa.getSelectedItem().toString()+"', 1,1) and estadotar=0 and idtipocomensal=3;",1);
          control.Sql=String.format("insert into consumidores values(null,'%s','%s','%s','%s','%s');", tari,semes,cbEstado.getSelectedIndex(),""+idpers, "3") ;
          control.CrearRegistro(control.Sql );MostrarBancos(); 
      }
      else{       
            control.Sql=String.format("insert into administrativos values(null,'%s','%s','%s','%s')", 
            control.PriLtrasMayuscula(txNombres.getText()), txDni.getText(),cbGenero.getSelectedIndex(),txFoto.getText());
            int idpers=control.executeAndGetId(control.Sql);
            System.out.println(control.Sql);
            String tari=control.DevolverRegistroDto("SELECT * FROM tarifas where categor=substring('"+cbTarifa.getSelectedItem().toString()+"', 1,1) and estadotar=0 and idtipocomensal=3;",1);
            control.Sql=String.format("insert into consumidores values(null,'%s','%s','%s','%s','%s');", tari,semes,cbEstado.getSelectedIndex(),""+idpers, "3") ;
            control.CrearRegistro(control.Sql );MostrarBancos(); 
      }
      Cancelar();
     }   
    }
    public void Cancelar(){
        lbFoto.setIcon(null);
     lbFoto.setText("FOTO");
     txFoto.setText(null);cbEstado.setSelectedIndex(0);   
     tBancos.clearSelection();txBuscar.setText(null);
     txNombres.requestFocus();
     bModificar.setText("Editar");
     bCrear.setEnabled(true);
     bEliminar.setEnabled(true);
     txDni.setText(null);
     txNombres.setText(null);
     cbFacultad.setSelectedIndex(-1);
     cbSemestre.setSelectedIndex(0);
     cbTarifa.setSelectedIndex(-1);
     cbGenero.setSelectedIndex(0);
     cbEstado.setSelectedIndex(-1);
     txArchivo.setText(null);
     btnProcesar.setEnabled(false);
     
    }
    public void ModificarBanco(){
     if(txNombres.getText().length()>0 || txDni.getText().length()>0){
      //String idcome=control.DevolverRegistroDto("select idtipocomensal from tipocomensal where descritipcom='"+cbGenero.getSelectedItem().toString()+"';", 1);
      control.Sql="select * from administrativos where dni='"+txDni.getText()+
      "' and nombres='"+txNombres.getText()+"' and idadministrativos<>'"+idBnc+"'";        
      if(!control.Verificarconsulta(control.Sql)){
      String semes=control.DevolverRegistroDto("select idsemestre from semestre where descrisemestre='"+cbSemestre.getSelectedItem().toString()+"';",1);
      String tari=control.DevolverRegistroDto("SELECT * FROM tarifas where categor=substring('"+cbTarifa.getSelectedItem().toString()+"', 1,1) and estadotar=0 and idtipocomensal=3;",1);
       control.Sql=String.format("update administrativos set dni='%s',nombres='%s', genero='%s', foto='%s' where idadministrativos='%s';",
               txDni.getText(),control.PriLtrasMayuscula(txNombres.getText()),cbGenero.getSelectedIndex(),txFoto.getText(),idBnc);
       System.out.println(control.Sql);
       control.EditarRegistro(control.Sql);
       control.Sql=String.format("update consumidores set idtarifas='%s', estado='%s' where idcomensales='%s' and idsemestre='%s' and idtipocomensal='%s' ;",
                              tari,cbEstado.getSelectedIndex(),idBnc,semes,"3");
       control.EditarRegistro(control.Sql);
       Cancelar();MostrarBancos();
       bModificar.setText("Editar");
       bCrear.setEnabled(true);
       bEliminar.setEnabled(true);
      }
      else{
       JOptionPane.showMessageDialog(rootPane,"El Docente Ya Fue Registrado\nActualice Información Sobre Semestre Actual","",3);
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
        control.Sql=String.format("delete from consumidores where idcomensales='%s' and idtipocomensal='3'", idBnc);
        control.EliminarRegistro(control.Sql);
        control.Sql=String.format("delete from administrativos where idadministrativos='%s'", idBnc);
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
      
      txNombres.setText(tBancos.getValueAt(control.fila,2).toString());
      txDni.setText(tBancos.getValueAt(control.fila,1).toString());
      txFoto.setText(control.DevolverRegistroDto("select foto from administrativos where idadministrativos='"+idBnc+"';", 1));
      if(txFoto.getText().equals("sinImagen")==false || txFoto.getText().trim().length()>0){
           ImageIcon fot = new ImageIcon("D:\\fotos\\"+txFoto.getText());
            ImageIcon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), java.awt.Image.SCALE_DEFAULT));
          lbFoto.setIcon(icono);
          //lbFoto.setIcon(new ImageIcon("D:\\fotos\\"+txFoto.getText()));

      }
      cbGenero.setSelectedItem(tBancos.getValueAt(control.fila,3).toString());
      cbEstado.setSelectedItem(tBancos.getValueAt(control.fila,4).toString());
      cbTarifa.setSelectedItem(tBancos.getValueAt(control.fila,6).toString()+" - "+tBancos.getValueAt(control.fila,5).toString());
//      cbFacultad.setSelectedItem(tBancos.getValueAt(control.fila,5).toString());
      cbSemestre.setSelectedItem(tBancos.getValueAt(control.fila,7).toString());
      
     }   
    }
    public void MostrarBancos(){    
     BuscarBanco();               
    }
    public void BuscarBanco(){
        control.Sql="select " +
                " idadministrativos,dni, nombres, if(genero='0','Masculino','Femenino'),  if(c.estado=0,'Activo','InActivo'),  monto,  categor, descrisemestre,descritipcom " +
                " from administrativos d,consumidores c, tipocomensal tc, tarifas ta, semestre se " +
                " where d.idadministrativos=c.idcomensales and c.idtipocomensal=tc.idtipocomensal and " +
                " c.idtarifas=ta.idtarifas and c.idsemestre=se.idsemestre " +
                " and tc.idtipocomensal=3 and se.estadosem='0' and (dni like '%"+txBuscar.getText()+"%' or nombres like '%"+txBuscar.getText()+"%');";
        
        
     control.LlenarJTabla(modelo,control.Sql,9);    
    }
    public void ValidardatosBancos(){
          
    }
    public Registro_Administrativos() {
     initComponents();setTitle("Registro de Administrativos");
     this.setFrameIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Vender.png")));
     tBancos.setModel(modelo);modelo.setColumnIdentifiers(new String[] {"Id","DNI","Nombres","Genero","Estado","Tarifa","Categoria","Semestre","Tipo Comsumidor"});
     tBancos.getColumnModel().getColumn(0).setMaxWidth(0);
     tBancos.getColumnModel().getColumn(0).setMinWidth(0);
     tBancos.getColumnModel().getColumn(0).setPreferredWidth(0);
     tBancos.getColumnModel().getColumn(1).setPreferredWidth(100);
     tBancos.getColumnModel().getColumn(2).setPreferredWidth(80);
     tBancos.getColumnModel().getColumn(3).setPreferredWidth(30);
     tBancos.getColumnModel().getColumn(4).setPreferredWidth(30);
     tBancos.getColumnModel().getColumn(5).setPreferredWidth(30);
     tBancos.getColumnModel().getColumn(6).setPreferredWidth(30);
     tBancos.getColumnModel().getColumn(7).setPreferredWidth(110);
     tBancos.getColumnModel().getColumn(8).setPreferredWidth(50);
     //tBancos.getColumnModel().getColumn(4).setPreferredWidth(50);
     ruta=control.DevolverRegistroDto("select * from directorio;", 2);
     jProgressBar1.setVisible(false);
     bCrear.setMnemonic('c');bCancelar.setMnemonic('a');     
     bModificar.setMnemonic('d');bEliminar.setMnemonic('e');
     bSalir.setMnemonic('s');MostrarBancos();
     control.forma_table_ver(modelo, tBancos);
     cbEstado.addItem("Activo");
     cbEstado.addItem("InActivo");
     cbEstado.setSelectedIndex(0);
     cbTarifa.addItem("A");
     cbTarifa.addItem("B");
     cbTarifa.addItem("C");
     cbGenero.addItem("Masculino");
     cbGenero.addItem("Femenino");
     cbTarifa.setSelectedIndex(0);
     jLabel8.setVisible(false);
     cbFacultad.setVisible(false);
     control.LlenarCombo(cbFacultad,"SELECT * FROM facultad",2);
     control.LlenarCombo(cbSemestre,"SELECT * FROM semestre where estadosem='0';",2);
     control.LlenarCombo(cbTarifa,"SELECT concat(categor,' - ',monto) FROM tarifas where estadotar='0' and idtipocomensal='3';",1);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tBancos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txFoto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbGenero = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txNombres = new javax.swing.JTextField();
        txDni = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox();
        cbFacultad = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        cbSemestre = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        cbTarifa = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txArchivo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnProcesar = new javax.swing.JButton();
        btnSelecionarArc = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        lbFoto = new javax.swing.JLabel();

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 580, 60));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 940, 180));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Personales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("Foto");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, 20));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Genero");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 20));
        jPanel2.add(txFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 210, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Nombres");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));

        jPanel2.add(cbGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 290, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("DNI");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, 20));
        jPanel2.add(txNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 290, -1));

        txDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txDniKeyTyped(evt);
            }
        });
        jPanel2.add(txDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 290, -1));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 102));
        jButton1.setText("Imagen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 370, 150));

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Comensales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });
        jPanel3.add(txBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 490, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Buscar");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 960, 240));

        jPanel4.setBackground(new java.awt.Color(51, 153, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de los Comensales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("Estado");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, 20));

        jPanel4.add(cbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 250, -1));

        jPanel4.add(cbFacultad, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 250, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 153));
        jLabel8.setText("Facultad");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, 20));

        jPanel4.add(cbSemestre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 250, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 153));
        jLabel9.setText("Semestre Academico");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));

        jPanel4.add(cbTarifa, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 250, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 153));
        jLabel11.setText("Tarifa");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, 20));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 400, 150));

        jPanel5.setBackground(new java.awt.Color(51, 153, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cargar desde Excel", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txArchivo.setEditable(false);
        txArchivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txArchivoKeyReleased(evt);
            }
        });
        jPanel5.add(txArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 330, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 153));
        jLabel10.setText("Selecionar Archivo");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        btnProcesar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnProcesar.setForeground(new java.awt.Color(0, 51, 102));
        btnProcesar.setText("Procesar");
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarActionPerformed(evt);
            }
        });
        jPanel5.add(btnProcesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, -1, -1));

        btnSelecionarArc.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnSelecionarArc.setForeground(new java.awt.Color(0, 51, 102));
        btnSelecionarArc.setText("Selecionar");
        btnSelecionarArc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarArcActionPerformed(evt);
            }
        });
        jPanel5.add(btnSelecionarArc, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, -1, -1));
        jPanel5.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 240, -1));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 960, 50));

        lbFoto.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lbFoto.setForeground(new java.awt.Color(0, 51, 153));
        lbFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFoto.setText("Foto");
        lbFoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(lbFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 130, 140));

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

    private void txArchivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txArchivoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txArchivoKeyReleased

    private void btnSelecionarArcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarArcActionPerformed
    chooser.setAcceptAllFileFilterUsed(false);
    chooser.addChoosableFileFilter(new FileNameExtensionFilter("Archivo Excel (.xls)", "xls"));
    int retVal = chooser.showOpenDialog(this);
    if (retVal == JFileChooser.APPROVE_OPTION) {
        txArchivo.setText(chooser.getSelectedFile().getAbsolutePath());
        btnProcesar.setEnabled(true);
    }
    }//GEN-LAST:event_btnSelecionarArcActionPerformed

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarActionPerformed
    //jButton1.setEnabled(false);
    
        if (txArchivo.getText().length() > 0) {
            SwingWorker worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    leerArchivoExcel(txArchivo.getText());
                    return true;
                }

                @Override
                protected void done() {
                    txArchivo.setText("");
                    btnProcesar.setEnabled(false);
                    //jButton1.setEnabled(true);
                }
            };
            worker.execute();

        } else {
            JOptionPane.showMessageDialog(this, "Por favor primero seleccione un archivo para procesar", "Atención", JOptionPane.WARNING_MESSAGE);
        }
  
    }//GEN-LAST:event_btnProcesarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(txDni.getText().trim().length()>0){
            chooserim.setAcceptAllFileFilterUsed(false);//"Capturar el Acta", "jpg","pdf", "jpeg", "png", "gif"
            chooserim.addChoosableFileFilter(new FileNameExtensionFilter("Capturar el Acta", "jpg","jpeg", "png", "gif"));
            int retVal = chooserim.showOpenDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                txFoto.setText(chooserim.getSelectedFile().getAbsolutePath());
                //btnProcesar.setEnabled(true);
                String nomarchivo=txDni.getText()+capturar_extencion(txFoto.getText());
                CopiarImagen(ver(txFoto.getText()),ruta+nomarchivo);
                txFoto.setText(nomarchivo);
                //lbFoto.setIcon(new ImageIcon("D:\\fotos\\"+nomarchivo)); 
                ImageIcon fot = new ImageIcon(ruta+txFoto.getText());
                ImageIcon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), java.awt.Image.SCALE_DEFAULT));
                lbFoto.setIcon(icono);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Ingrese Datos Completos del Administrativo");
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDniKeyTyped
        control.Solonumeros(evt);
        control.Longitudtx(txDni, evt, 8);
    }//GEN-LAST:event_txDniKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bCrear;
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton bModificar;
    private javax.swing.JButton bSalir;
    private javax.swing.JButton btnProcesar;
    private javax.swing.JButton btnSelecionarArc;
    private javax.swing.JComboBox cbEstado;
    private javax.swing.JComboBox cbFacultad;
    private javax.swing.JComboBox cbGenero;
    private javax.swing.JComboBox cbSemestre;
    private javax.swing.JComboBox cbTarifa;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JTable tBancos;
    private javax.swing.JTextField txArchivo;
    private javax.swing.JTextField txBuscar;
    private javax.swing.JTextField txDni;
    private javax.swing.JTextField txFoto;
    private javax.swing.JTextField txNombres;
    // End of variables declaration//GEN-END:variables

    private void leerArchivoExcel(String archivoDestino) {
        //jTextArea1.append("Iniciando Lectura del Archivo\n");
        String dni="",nombres="",gen="",facu="",tari="",semes="";
        int  countsemes=Integer.parseInt(control.DevolverRegistroDto("SELECT count(idsemestre) FROM semestre where estadosem=0;",1));
        if(countsemes==0){
            JOptionPane.showMessageDialog(null, "No hay Semestre Activo");
            
        }else{
          semes=control.DevolverRegistroDto("SELECT idsemestre FROM semestre where estadosem=0;",1);
            try {
                 WorkbookSettings confi=new WorkbookSettings();
                confi.setEncoding("ISO-8859-1");
                Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino),confi);
                for (int sheetNo = 0; sheetNo < archivoExcel.getNumberOfSheets(); sheetNo++) {
                    Sheet hoja = archivoExcel.getSheet(sheetNo);
                    int numFilas = hoja.getRows();
                    jProgressBar1.setMaximum(numFilas);
                    jProgressBar1.setValue(0);
                    for (int fila = 1; fila < numFilas; fila++) {
                        jProgressBar1.setValue(fila);
                        //jTextArea1.append("Leyendo Fila [" + (fila + 1) + "]");
                        dni = hoja.getCell(0, fila).getContents();
                        if (dni.length() == 0) {
                            //jTextArea1.append("Errror en el formato del DNI\n");
                            JOptionPane.showMessageDialog(null, "Error en Formato Archivo");
                            break;
                        }
                        nombres=hoja.getCell(1, fila).getContents();
                        gen=hoja.getCell(2, fila).getContents();
                        //facu=hoja.getCell(3, fila).getContents();
                        tari=hoja.getCell(3, fila).getContents();
                        ///semes=hoja.getCell(5, fila).getContents();
                        
                        int vercantidad=Integer.parseInt(control.DevolverRegistroDto("select count(idadministrativos),idadministrativos from administrativos where dni='"+dni+"';", 1));
                        System.out.println(nombres+"--"+dni+"--"+gen);
                        System.out.println(String.format("insert into administrativos values(null,'%s','%s','%s','sinImagen');",
                                    nombres,dni,gen));
                        if(vercantidad==0){
                            control.Sql=String.format("insert into administrativos values(null,'%s','%s','%s','sinImagen');",
                                    nombres,dni,gen);
                            int idpers=control.executeAndGetId(control.Sql);
                            System.out.println(control.Sql);
                            control.Sql=String.format("insert into consumidores values(null,'%s','%s','0','%s','3');", 
                                    tari,semes,""+idpers) ;
                            control.CrearRegistro(control.Sql );
                        }else{
                            int idpers=Integer.parseInt(control.DevolverRegistroDto("select count(iddocentes),iddocentes from docentes where dni='"+dni+"';", 2));
                            System.out.println("SELECT count(*) from consumidores where idcomensales='"+idpers+"' and idtipocomensal='3' and idsemestre='"+semes+"' and estado='0';");
                            int vercom=Integer.parseInt(control.DevolverRegistroDto("SELECT count(*) from consumidores where idcomensales='"+idpers+"' and idtipocomensal='3' and idsemestre='"+semes+"' and estado='0';", 1));
                            
                            
                            if(vercom==0){
                                control.Sql=String.format("insert into consumidores values(null,'%s','%s','0','%s','3');",tari,semes,""+idpers) ;
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

