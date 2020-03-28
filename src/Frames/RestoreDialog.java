package Frames;

import Clases.ConexionBD;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author 
 */
public class RestoreDialog extends javax.swing.JDialog {
    
    private static final long serialVersionUID = 7516566787521098458L;
    private JFileChooser chooser;
    private String location;

    /**
     * Creates new form NewProyectDialog
     */
    public RestoreDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        //txtProyectLocation.setText("" + chooser.getCurrentDirectory());
        barProceso.setVisible(false);
        areaError.setVisible(false);
        jScrollPane1.setVisible(false);
        txtProceso.setText("");
        this.pack();
        
        
    }
/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * reg
    enerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel4 = new javax.swing.JLabel();
        txtProyectLocation = new javax.swing.JTextField();
        btnAcept = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnBrowse = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaError = new javax.swing.JTextArea();
        barProceso = new javax.swing.JProgressBar();
        txtProceso = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Proyecto");

        jLabel4.setText("Ubicación del Archivo");

        txtProyectLocation.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtProyectLocationCaretUpdate(evt);
            }
        });

        btnAcept.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAcept.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Accept.png"))); // NOI18N
        btnAcept.setText("Aceptar");
        btnAcept.setEnabled(false);
        btnAcept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancel.png"))); // NOI18N
        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnBrowse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/folder.png"))); // NOI18N
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        areaError.setColumns(20);
        areaError.setRows(5);
        jScrollPane1.setViewportView(areaError);

        txtProceso.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtProyectLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnAcept)
                                .addGap(101, 101, 101)
                                .addComponent(btnCancel)
                                .addGap(164, 164, 164))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(barProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtProyectLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barProceso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtProceso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAcept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        int showSaveDialog = chooser.showOpenDialog(this);
        if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
            location = chooser.getSelectedFile().toString();
            txtProyectLocation.setText(location);
        }
    }//GEN-LAST:event_btnBrowseActionPerformed
    
    private void txtProyectLocationCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtProyectLocationCaretUpdate
        location = txtProyectLocation.getText();
        checkTexts();
    }//GEN-LAST:event_txtProyectLocationCaretUpdate
    
    private void btnAceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptActionPerformed
        this.pack();
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                btnAcept.setEnabled(false);
                restaurarBackup();
                return true;
            }
            
            @Override
            protected void done() {
                super.done();
                btnAcept.setEnabled(true);
            }
        };
        worker.execute();
        
        
    }//GEN-LAST:event_btnAceptActionPerformed
    
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

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
            java.util.logging.Logger.getLogger(RestoreDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RestoreDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RestoreDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RestoreDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RestoreDialog dialog = new RestoreDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txtProceso;
    private javax.swing.JTextField txtProyectLocation;
    // End of variables declaration//GEN-END:variables

    private void checkTexts() {
        if (txtProyectLocation.getText().trim().length() == 0) {
            btnAcept.setEnabled(false);
        } else {
            btnAcept.setEnabled(true);
        }
    }
    
    public void restaurarBackup() {
        txtProceso.setText("Por favor Espere Mientras se Restaura");
        barProceso.setVisible(true);
        barProceso.setIndeterminate(true);
        pack();
        boolean error = false;
        String errores = "";
        
        File archivo = new File(txtProyectLocation.getText());
        if (archivo.exists()) {
            try {
                String pros = " mysql --user=root --database="+ ConexionBD.db+" --password=" + ConexionBD.contraseña
                        + " -e \"source " + archivo.getAbsolutePath() + "\"";
                System.out.println("voy a ejecutar");
                
                Process proceso = Runtime.getRuntime().exec(pros);
                InputStream in = proceso.getInputStream();
                BufferedReader bError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                 System.out.println("me quedé");
                String lineError = "";
                int contador=0;
                while ((lineError = bError.readLine()) != null) {
                    errores += lineError;
                    contador++;
                    System.out.println(errores);
                }
                
                if (errores.equals("")) {
                    System.out.println("sin error");
                    barProceso.setVisible(false);
                    txtProceso.setText("");
                    JOptionPane.showMessageDialog(null, "La restauración de la copia de seguridad\n" + archivo.getAbsolutePath()
                            + "\nse realizó con éxito", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                    
                } else {
                    error = true;
                    System.out.println("con error");
                    String aux = errores;
                    errores = "Ocurrio errores al Procesar la petición\n" + aux;
                    
                }
                bError.close();
                in.close();
            } catch (Exception e) {
                System.out.println(e);
                
                errores += e.getMessage() + "\n\n";
                Throwable t = e;
                StackTraceElement[] s = t.getStackTrace();
                for (StackTraceElement el : s) {
                    errores += "Error:   " + el.toString() + "\n";
                }
                error = true;
                errores += "\n\n";
                System.out.println(errores);
                
            }
            
            if (error) {
                areaError.append(errores);
            }
        } else {
            JOptionPane.showMessageDialog(this, "El archivo " + archivo.toString() + "\n" + "no existe");
        }
    }
}
