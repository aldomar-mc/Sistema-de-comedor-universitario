package Frames;

import Clases.*;import java.awt.Rectangle;import java.io.BufferedReader;
import java.io.File;import java.io.FileReader;import java.io.FileWriter;
import java.io.InputStreamReader;import java.io.PrintWriter;import java.util.Calendar;
import java.util.GregorianCalendar;import javax.imageio.stream.FileImageOutputStream;
import javax.swing.JFileChooser;import javax.swing.JOptionPane;
import javax.swing.SwingWorker;import java.*;
public class BackupDialog extends javax.swing.JDialog {
 private static final long serialVersionUID = 7516566787521098458L;
 private JFileChooser chooser; private String location;
    //ConexionBD bD=new ConexionBD();
public static ConexionBD1 bD = ConexionBD1.getInstance();
    
    public BackupDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal); initComponents();bD.Conectar();
        chooser = new JFileChooser();chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        txtProyectName.setText(getFileName()); txtProyectLocation.setText("" + chooser.getCurrentDirectory());
        barProceso.setVisible(false);   areaError.setVisible(false);
        jScrollPane1.setVisible(false);txtProceso.setText("");
        this.pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        txtProyectName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtProyectLocation = new javax.swing.JTextField();
        txtProyectFolder = new javax.swing.JTextField();
        btnAcept = new javax.swing.JButton();
        btnBrowse = new javax.swing.JButton();
        txtIncorrects = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaError = new javax.swing.JTextArea();
        barProceso = new javax.swing.JProgressBar();
        txtProceso = new javax.swing.JLabel();
        btnCancel1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Proyecto");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtProyectName.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtProyectNameCaretUpdate(evt);
            }
        });
        getContentPane().add(txtProyectName, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 11, 392, -1));

        jLabel1.setText("Nombre del Archivo");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 17, -1, -1));

        jLabel4.setText("Ubicación del Archivo");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, -1, -1));

        jLabel5.setText("Carpeta del Archivo");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 71, -1, -1));

        txtProyectLocation.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtProyectLocationCaretUpdate(evt);
            }
        });
        getContentPane().add(txtProyectLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 42, 392, -1));
        getContentPane().add(txtProyectFolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 68, 435, -1));

        btnAcept.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAcept.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Accept.png"))); // NOI18N
        btnAcept.setText("Aceptar");
        btnAcept.setEnabled(false);
        btnAcept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptActionPerformed(evt);
            }
        });
        getContentPane().add(btnAcept, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 254, 150, 35));

        btnBrowse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fileopen.png"))); // NOI18N
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });
        getContentPane().add(btnBrowse, new org.netbeans.lib.awtextra.AbsoluteConstraints(531, 37, 36, -1));

        txtIncorrects.setBackground(new java.awt.Color(51, 0, 255));
        txtIncorrects.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIncorrects.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(txtIncorrects, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 295, 554, 15));

        areaError.setColumns(20);
        areaError.setRows(5);
        jScrollPane1.setViewportView(areaError);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 119, 554, 129));
        getContentPane().add(barProceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 99, 269, -1));

        txtProceso.setText("jLabel2");
        getContentPane().add(txtProceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 99, 267, -1));

        btnCancel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnCancel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancel.png"))); // NOI18N
        btnCancel1.setText("Cancelar");
        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 254, 150, 35));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        int showSaveDialog = chooser.showSaveDialog(this);
        if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
            location = chooser.getSelectedFile().toString();
            txtProyectLocation.setText(location);
            
        }
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void txtProyectNameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtProyectNameCaretUpdate
        txtProyectFolder.setText(location + "\\" + txtProyectName.getText());
        checkTexts();
    }//GEN-LAST:event_txtProyectNameCaretUpdate

    private void txtProyectLocationCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtProyectLocationCaretUpdate
        location = txtProyectLocation.getText();
        txtProyectFolder.setText(location + "\\" + txtProyectName.getText());
        checkTexts();
    }//GEN-LAST:event_txtProyectLocationCaretUpdate

    private void btnAceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptActionPerformed
       this.pack();
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                btnAcept.setEnabled(false);
                crearBackup();
                return true;
            }

            @Override
            protected void done() {
                super.done();
                btnAcept.setEnabled(true);
            }
        };
        worker.execute();
      //   new Backup().CrearBackup("", "", "","", "",txtProyectFolder.getText().trim());

    }//GEN-LAST:event_btnAceptActionPerformed

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
      dispose();
    }//GEN-LAST:event_btnCancel1ActionPerformed

//private static void backup() {
//   try {
//      Process p = Runtime
//            .getRuntime()
//            .exec("C:/Aplicaciones/wamp/bin/mysql/mysql5.1.36/bin/mysqldump -u root -ppassword database");
// 
//      InputStream is = p.getInputStream();
//       FileImageOutputStream fos = new FileOutputStream("backup_pruebas.sql");
//      byte[] buffer = new byte[1000];
// 
//      int leido = is.read(buffer);
//      while (leido > 0) {
//         fos.write(buffer, 0, leido);
//         leido = is.read(buffer);
//      }
// 
//      fos.close();
// 
//   } catch (Exception e) {
//      e.printStackTrace();
//   }
//}
    
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
            java.util.logging.Logger.getLogger(BackupDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BackupDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BackupDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BackupDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                BackupDialog dialog = new BackupDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextArea areaError;
    private javax.swing.JProgressBar barProceso;
    private javax.swing.JButton btnAcept;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnCancel1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txtIncorrects;
    private javax.swing.JLabel txtProceso;
    private javax.swing.JTextField txtProyectFolder;
    private javax.swing.JTextField txtProyectLocation;
    private javax.swing.JTextField txtProyectName;
    // End of variables declaration//GEN-END:variables

    private void checkTexts() {
      if (new File(txtProyectFolder.getText() + (txtProyectName.getText().endsWith(".jcpm") ? "" : ".jcpm")).exists()) {
       txtIncorrects.setText("Ya existe un archivo con ese nombre, especifique otro");
        this.pack(); btnAcept.setEnabled(false); return;
        } 
      else {
        txtIncorrects.setText("");btnAcept.setEnabled(true);
      }
      
      if (txtProyectLocation.getText().trim().length() == 0) {
       btnAcept.setEnabled(false);
       txtIncorrects.setText("Por favor especifique la ubicación del archivo");
       this.pack();return;
      }
      else {
       btnAcept.setEnabled(true);txtIncorrects.setText("");
      }
      if (txtProyectName.getText().trim().length() == 0) {
       btnAcept.setEnabled(false);
       txtIncorrects.setText("Por favor especifique el nombre del proyecto");
       this.pack();return;
      }
      else {
       btnAcept.setEnabled(true);txtIncorrects.setText("");
      }
    }

    private String getFileName() {
     Calendar c = new GregorianCalendar();int x = c.get(Calendar.AM_PM);String mer = "am";
     if (x == 1) {
      mer = "pm";
     }
     return "Backup-" + c.get(Calendar.YEAR) + "" + c.get(Calendar.MONTH) + "" + c.get(Calendar.DAY_OF_MONTH)
     + "" + c.get(Calendar.HOUR) + c.get(Calendar.MINUTE) + c.get(Calendar.SECOND) + ".sql";
    }
    public void crearBackup() {
        boolean error = false;
        String errores = "";
        String cabecera = "-- MySQL Administrator dump 1.4\n"
                + "--\n"
                + "-- ------------------------------------------------------\n"
                + "-- Server version	5.1-community\n\n\n"
                + "create database IF NOT EXISTS "+ConexionBD1.db+";\n"
                + "use "+ConexionBD1.db+";\n\n";

        File archivo = null;int res;
        archivo = new File(txtProyectFolder.getText());
        if (archivo.isFile()) {
          res = JOptionPane.showConfirmDialog(null, "El archivo " + archivo.getName()
          + " ya existe desea sobreescribirlo?", "Confirmacion", JOptionPane.YES_NO_OPTION);
        } 
        else {
         res = 30;
        }
        if ((res == JOptionPane.YES_OPTION || res == 30) && archivo != null) {
         try {
          //JOptionPane.showMessageDialog(null, archivo.getAbsolutePath()+" -- "+ConexionBD.usu+" -- "+ConexionBD.password+" -- "+ConexionBD.db);
          barProceso.setVisible(true);txtProceso.setText("Iniciando Proceso por favor espere");                
//    String[] comando = {"C:\\Archivos de programa\\MySQL\\MySQL Server 5.1\\bin\\mysqldump",
//                    "--user="+ConexionBD.usu,
//                    "--password="+ConexionBD.contraseña,
//                    "--skip-comments",
//                    "--routines",
//                    ""+ConexionBD.db,
//                    "--result-file=" + archivo.getAbsolutePath()};
                String[] comando = {"mysqldump","--user="+ConexionBD1.user,
                    "--password="+ConexionBD1.contraseña,"--skip-comments",
                    "--routines",""+ConexionBD1.db,"--result-file=" + archivo.getAbsolutePath()};
                
      String[] comando1={"mysqldump --host="+ConexionBD1.host," --port=3306"," --user="+ConexionBD1.user,
      " --password="+ConexionBD1.contraseña," --compact --complete-insert --extended-insert --skip-quote-names" ,
        " --skip-comments --skip-triggers "+ConexionBD1.db};
                
       Process proceso = Runtime.getRuntime().exec(comando);
       System.out.println(comando);
       BufferedReader bError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
//                 BufferedReader bError = new BufferedReader(new InputStreamReader(dd.getErrorStream()));
                String lineError = "";
                while ((lineError = bError.readLine()) != null) {
                    errores += lineError;
                }//              
                bError.close();
                //leemos los datos para escribir la cabecera de la copia de seguridad
                //esto para poderlo restaurar con el administrador de Mysql 
                BufferedReader buf = new BufferedReader(new FileReader(archivo));
                String contenido = "";
                String lin = "";
                int count = 0, c = 0;
                barProceso.setString("");
                
                while ((lin = buf.readLine()) != null) {
                    if (lin.trim().length() == 0) {
                        continue;
                    }
                    contenido = contenido + lin + "\n";
                    if (barProceso.getValue() == 100) {
                        barProceso.setValue(0);
                        c = 0;
                    }
                    
                    c++;
                    txtProceso.setText("Procesando línea [" + (count++) + "]");
                    barProceso.setValue(c);
                }
                buf.close();
//                if (archivo.isFile()) {
//                    archivo.delete();
//                }
                FileWriter fichero = new FileWriter(archivo.getAbsolutePath());
                PrintWriter pw = new PrintWriter(fichero);

                pw.println(cabecera + contenido);
                pw.flush();
                pw.close();
                //logger.info(LogCategories.BACKUP,"Creando copia de seguridad de la base de datos manualmente: "+archivo.getName());

                if (errores.equals("")) {
                    barProceso.setValue(barProceso.getMaximum());
                    JOptionPane.showMessageDialog(null, "La copia de seguridad ha sido creado con exito en:\n"
                            + archivo.getAbsolutePath(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    error = true;
                    archivo.delete();
                    String aux = errores;
                    errores = "Ocurrio errores al Procesar la petición\n" + aux;
                }
            } catch (Exception e) {
                // logger.error(LogCategories.UNEXPEXTED_ERROR, e);
                errores += e.getMessage() + "\n\n";
                Throwable t = e;
                StackTraceElement[] s = t.getStackTrace();
                for (StackTraceElement el : s) {
                    errores += "Error:  " + el.toString() + "s\n";
                }
                error = true;
                errores += "\n\n";
            }
        }
        if (error) {
            jScrollPane1.setVisible(true);
            areaError.setVisible(true);
            areaError.append(errores);
            this.pack();
        }
    }
}