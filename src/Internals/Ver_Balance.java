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

public class Ver_Balance extends javax.swing.JInternalFrame {

    /*************************Atributos**********************/ 
    Mimodelo modelo=new Mimodelo();
    Controlador control=new Controlador();
    String idLinProd="",versentencia="";
    int cantidadTotal=0;
    InfoGeneral info= new InfoGeneral();
    IMPRIMIR imp= new IMPRIMIR();
    /*************************Atributos**********************/ 
    
    /*************************Métodos************************/     
   
    public void MostrarLineaproducto(){
     BuscarLineaproducto();    
    }
    public void BuscarLineaproducto(){
        
        if(cbTipoComensal.getSelectedItem().toString().equals("TODOS")){
            versentencia="  ";
        }else{
            versentencia=" and descritipcom='"+cbTipoComensal.getSelectedItem().toString()+"' ";
        }
        //sacar el total de consumidores normales
        control.Sql="SELECT count(*) FROM crono_comensale where fechaconsumo='"+control.Formato_Amd(jDateChooser1.getDate())+"' and estadocrono<>2 and almu=0 "+versentencia;
        System.out.println(control.Sql);
        int vercrono=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
        //sacar el todoal de consumidores  con almuerzo
        control.Sql="SELECT count(*) FROM crono_comensale where fechaconsumo='"+control.Formato_Amd(jDateChooser1.getDate())+"' and estadocrono<>2 "+versentencia;
        System.out.println(control.Sql);
        int vercronoAlmuerzo=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
        
        
        control.Sql="SELECT count(*) FROM vat_asistencia where fecha= '"+control.Formato_Amd(jDateChooser1.getDate())+"' "+" and descripturno='Desayuno' "+ versentencia;
        System.out.println(control.Sql);
        int cantDesayuno=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
        control.Sql="SELECT count(*) FROM vat_asistencia where fecha= '"+control.Formato_Amd(jDateChooser1.getDate())+"' "+" and descripturno='Almuerzo' "+ versentencia;
        System.out.println(control.Sql);
        int cantAlmuerzo=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
        control.Sql="SELECT count(*) FROM vat_asistencia where fecha= '"+control.Formato_Amd(jDateChooser1.getDate())+"' "+" and descripturno='Cena' "+ versentencia;
        System.out.println(control.Sql);
        int canCenat=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
        if(vercrono==0 ){
            lbTotalAlmuerzo.setText(""+vercronoAlmuerzo);
             lbTotalDesayunos.setText(""+vercrono);
             lbTotalcena.setText(""+vercrono);
             
            lbDesayuno.setText(""+cantDesayuno);
            lbAlumuerzo.setText(""+cantAlmuerzo);
            lbCena.setText(""+canCenat);
            
            lbFaltaAlmuerzo.setText(""+(vercronoAlmuerzo-cantAlmuerzo));
            lbFaltaCena.setText(""+(vercrono-canCenat));
            lbFaltaDesayuno.setText(""+(vercrono-cantDesayuno));
            
            lbPorceDesayuno.setText("0 %");
            lbPorceAlmuerzo.setText("0 %");
            lbPorceCena.setText("0 %");      
            cantidadTotal=0;
        }else{
            cantidadTotal=vercrono;
            
             lbTotalAlmuerzo.setText(""+vercronoAlmuerzo);
             lbTotalDesayunos.setText(""+vercrono);
             lbTotalcena.setText(""+vercrono);
            
             lbDesayuno.setText(""+cantDesayuno);
            lbAlumuerzo.setText(""+cantAlmuerzo);
            lbCena.setText(""+canCenat);
            
            lbFaltaAlmuerzo.setText(""+(vercronoAlmuerzo-cantAlmuerzo));
            lbFaltaCena.setText(""+(vercrono-canCenat));
            lbFaltaDesayuno.setText(""+(vercrono-cantDesayuno));
            
            
            control.Sql="delete from estadistica";
            control.EliminarRegistro(control.Sql);
            
            lbPorceDesayuno.setText(""+((cantDesayuno*100/vercrono))+" %");
            lbPorceAlmuerzo.setText(""+((cantAlmuerzo*100/vercronoAlmuerzo))+" %");
            lbPorceCena.setText(""+((canCenat*100/vercrono))+" %");
            
            ////insert para el reporte
            control.Sql="insert into estadistica values(1,'Asistencia','Desayuno','"+cantDesayuno+"','"+vercrono+"');";
            control.CrearRegistro(control.Sql);
            control.Sql="insert into estadistica values(2,'InAsistencia','Desayuno','"+(vercrono-cantDesayuno)+"','"+vercrono+"');";
            control.CrearRegistro(control.Sql);
            control.Sql="insert into estadistica values(3,'Asistencia','Almuerzo','"+cantAlmuerzo+"','"+vercronoAlmuerzo+"');";
            control.CrearRegistro(control.Sql);
            control.Sql="insert into estadistica values(4,'InAsistencia','Almuerzo','"+(vercronoAlmuerzo-cantAlmuerzo)+"','"+vercronoAlmuerzo+"');";
            control.CrearRegistro(control.Sql);
            control.Sql="insert into estadistica values(5,'Asistencia','Cena','"+canCenat+"','"+vercrono+"');";
            control.CrearRegistro(control.Sql);
            control.Sql="insert into estadistica values(6,'InAsistencia','Cena','"+(vercrono-canCenat)+"','"+vercrono+"');";
            control.CrearRegistro(control.Sql);
        }

    }

    public Ver_Balance() {
     initComponents();setTitle("Ver Balance General");
     this.setFrameIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Vender.png")));
     
     control.LlenarCombo(cbTipoComensal, "select * from tipocomensal ",2);
     cbTipoComensal.addItem("TODOS");
     cbTipoComensal.setSelectedItem("TODOS");     
     jDateChooser1.setDate(new Date());
     MostrarLineaproducto();
     
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
                    }
                }
            }
        });
       
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbPorcentajeTotal = new javax.swing.JLabel();
        cbTipoComensal = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lbTotalDesayunos = new javax.swing.JLabel();
        lbDesayuno = new javax.swing.JLabel();
        lbPorceDesayuno = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbAlumuerzo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbPorceAlmuerzo = new javax.swing.JLabel();
        lbCena = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbPorceCena = new javax.swing.JLabel();
        lbTotalAlmuerzo = new javax.swing.JLabel();
        lbTotalcena = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbPorcentajeTotal1 = new javax.swing.JLabel();
        lbFaltaDesayuno = new javax.swing.JLabel();
        lbFaltaAlmuerzo = new javax.swing.JLabel();
        lbFaltaCena = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 153, 255));
        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Balance General de Asistencias", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 102))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Total");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 140, 80));

        lbPorcentajeTotal.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        lbPorcentajeTotal.setForeground(new java.awt.Color(0, 51, 153));
        lbPorcentajeTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPorcentajeTotal.setText("%");
        lbPorcentajeTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbPorcentajeTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 100, 160, 80));

        jPanel2.add(cbTipoComensal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 150, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Tipo de Comensal");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));
        jPanel2.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 150, -1));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 153));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Print.png"))); // NOI18N
        jButton1.setText("Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("Fecha");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, 20));

        lbTotalDesayunos.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        lbTotalDesayunos.setForeground(new java.awt.Color(0, 51, 153));
        lbTotalDesayunos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotalDesayunos.setText("48");
        lbTotalDesayunos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbTotalDesayunos, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 140, 80));

        lbDesayuno.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        lbDesayuno.setForeground(new java.awt.Color(0, 51, 153));
        lbDesayuno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDesayuno.setText("48");
        lbDesayuno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbDesayuno, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, 190, 80));

        lbPorceDesayuno.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        lbPorceDesayuno.setForeground(new java.awt.Color(0, 51, 153));
        lbPorceDesayuno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPorceDesayuno.setText("100 %");
        lbPorceDesayuno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbPorceDesayuno, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 180, 160, 80));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Desayuno");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 180, 80));

        lbAlumuerzo.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        lbAlumuerzo.setForeground(new java.awt.Color(0, 51, 153));
        lbAlumuerzo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAlumuerzo.setText("48");
        lbAlumuerzo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbAlumuerzo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 190, 80));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 153));
        jLabel7.setText("Almuerzo");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 180, 80));

        lbPorceAlmuerzo.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        lbPorceAlmuerzo.setForeground(new java.awt.Color(0, 51, 153));
        lbPorceAlmuerzo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPorceAlmuerzo.setText("100 %");
        lbPorceAlmuerzo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbPorceAlmuerzo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, 160, 80));

        lbCena.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        lbCena.setForeground(new java.awt.Color(0, 51, 153));
        lbCena.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCena.setText("48");
        lbCena.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbCena, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, 190, 80));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 153));
        jLabel8.setText("Cena");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 180, 80));

        lbPorceCena.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        lbPorceCena.setForeground(new java.awt.Color(0, 51, 153));
        lbPorceCena.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPorceCena.setText("100 %");
        lbPorceCena.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbPorceCena, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 340, 160, 80));

        lbTotalAlmuerzo.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        lbTotalAlmuerzo.setForeground(new java.awt.Color(0, 51, 153));
        lbTotalAlmuerzo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotalAlmuerzo.setText("48");
        lbTotalAlmuerzo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbTotalAlmuerzo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 140, 80));

        lbTotalcena.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        lbTotalcena.setForeground(new java.awt.Color(0, 51, 153));
        lbTotalcena.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotalcena.setText("48");
        lbTotalcena.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbTotalcena, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 140, 80));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("Asistentes");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 190, 80));

        lbPorcentajeTotal1.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        lbPorcentajeTotal1.setForeground(new java.awt.Color(0, 51, 153));
        lbPorcentajeTotal1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPorcentajeTotal1.setText("Faltantes");
        lbPorcentajeTotal1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbPorcentajeTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 170, 80));

        lbFaltaDesayuno.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        lbFaltaDesayuno.setForeground(new java.awt.Color(0, 51, 153));
        lbFaltaDesayuno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFaltaDesayuno.setText("100 %");
        lbFaltaDesayuno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbFaltaDesayuno, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 180, 170, 80));

        lbFaltaAlmuerzo.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        lbFaltaAlmuerzo.setForeground(new java.awt.Color(0, 51, 153));
        lbFaltaAlmuerzo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFaltaAlmuerzo.setText("100 %");
        lbFaltaAlmuerzo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbFaltaAlmuerzo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 170, 80));

        lbFaltaCena.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        lbFaltaCena.setForeground(new java.awt.Color(0, 51, 153));
        lbFaltaCena.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFaltaCena.setText("100 %");
        lbFaltaCena.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(lbFaltaCena, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 340, 170, 80));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(cantidadTotal>0){
            imp.Impresion2Parametros("cant", ""+cantidadTotal+"","fec",control.Formato_Amd(jDateChooser1.getDate()), "Ver_Graficos_Estadisticos");
        }else{
            JOptionPane.showMessageDialog(null, "No hay registro para Imprimir","System Message",3);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbTipoComensal;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbAlumuerzo;
    private javax.swing.JLabel lbCena;
    private javax.swing.JLabel lbDesayuno;
    private javax.swing.JLabel lbFaltaAlmuerzo;
    private javax.swing.JLabel lbFaltaCena;
    private javax.swing.JLabel lbFaltaDesayuno;
    private javax.swing.JLabel lbPorceAlmuerzo;
    private javax.swing.JLabel lbPorceCena;
    private javax.swing.JLabel lbPorceDesayuno;
    private javax.swing.JLabel lbPorcentajeTotal;
    private javax.swing.JLabel lbPorcentajeTotal1;
    private javax.swing.JLabel lbTotalAlmuerzo;
    private javax.swing.JLabel lbTotalDesayunos;
    private javax.swing.JLabel lbTotalcena;
    // End of variables declaration//GEN-END:variables


}
