/**
 * ************************* Acceso.java Created on 28-ene-2014, 14:52:42
 * *******************
 */
package Frames;

import Clases.Controlador;
import Clases.InfoGeneral;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.jvnet.substance.SubstanceLookAndFeel;
import skin.JyMSystemSoftBlueMetalSkin;

public class Acceso extends javax.swing.JFrame {

    Controlador control = new Controlador();
    InfoGeneral info = new InfoGeneral();
    String sede = "";
    private boolean cboTipoUsuarioListo = false;

    public Acceso() {
        initComponents();
        setTitle("BIENVENIDOS");
        //cboSede.setVisible(false);
        //jLabel3.setVisible(false);
        //control.LlenarCombo(cboSede, "Select * from sede", 2);
//        control.LlenarCombo(cboSede, "Select * from sede where nomse<>'Almacen'", 2);
       // cboSede.setSelectedIndex(1);
        control.LlenarCombo(cbTipoUsuario, "SELECT * FROM tipousuario t", 2);
        cboTipoUsuarioListo = true;
        //cbTipoUsuario.setSelectedIndex(0);
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Vender.png")).getImage());
        this.setLocationRelativeTo(null);
        control.bandera = false;
    }

    public void llamarfuncion() {
        control.Sql = "select PrimLetras('" + txtUsuario.getText() + "')";
        JOptionPane.showMessageDialog(rootPane, control.CrearRegistroDev(control.Sql));
    }

    public void Acceder() {
//        if ((cbTipoUsuario.getSelectedItem().toString().equalsIgnoreCase("Administrador"))) {
//            JOptionPane.showMessageDialog(rootPane, "Tienes que seleccionar la sede");
//        } else {
            if (txtPassword.getText().trim().length() > 0 && txtUsuario.getText().trim().length() > 0 && cbTipoUsuario.getSelectedIndex() > -1) {
                String tipous = "";
                info.setUsuario(txtUsuario.getText());
                info.setTipo(cbTipoUsuario.getSelectedItem().toString());
                control.Sql = "select idtipousuario from tipousuario where nomtpus='" + cbTipoUsuario.getSelectedItem().toString() + "';";
                tipous = control.DevolverRegistroDto(control.Sql, 1);
                if (!cbTipoUsuario.getSelectedItem().toString().equalsIgnoreCase("Administrador")) {
//                    control.Sql = "call DevolverSedeUsuario('" + txtUsuario.getText() + "','" + txtPassword.getText()
//                            + "','" + cbTipoUsuario.getSelectedItem().toString() + "');";
//                    Controlador.sede = control.CrearRegistroDev(control.Sql);
                } else {
                   // Controlador.sede = cboSede.getSelectedItem().toString();
                }
//                String idSede = control.DevolverRegistroDto("SELECT `idSede` FROM `sede` WHERE `nomse`='" + cboSede.getSelectedItem() + "';", 1);
//                InfoGeneral.setIdSede(idSede);
//                System.out.println("sede "+Controlador.sede);
                
                control.AccesoSistema(txtUsuario.getText(), txtPassword.getText(), this, new Menu(), 3, tipous);
            } else {
                JOptionPane.showMessageDialog(null, "Datos Incompletos");
            }
       // }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        bAceptar = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        cbTipoUsuario = new javax.swing.JComboBox();
        txtPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 153, 255));
        setUndecorated(true);
        setResizable(false);

        jPanel3.setName("jPanel3"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bAceptar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Accept.png"))); // NOI18N
        bAceptar.setMnemonic('a');
        bAceptar.setText("Aceptar");
        bAceptar.setName("bAceptar"); // NOI18N
        bAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAceptarActionPerformed(evt);
            }
        });
        jPanel1.add(bAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 120, 40));

        bSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir.png"))); // NOI18N
        bSalir.setMnemonic('s');
        bSalir.setText("Salir");
        bSalir.setName("bSalir"); // NOI18N
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });
        jPanel1.add(bSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 130, 40));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel2MouseMoved(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 153));
        jLabel2.setText("Usuario:");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 153));
        jLabel4.setText("Tipo de Usuario:");
        jLabel4.setName("jLabel4"); // NOI18N

        txtUsuario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtUsuario.setName("txtUsuario"); // NOI18N
        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusGained(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });

        cbTipoUsuario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbTipoUsuario.setForeground(new java.awt.Color(0, 51, 102));
        cbTipoUsuario.setName("cbTipoUsuario"); // NOI18N
        cbTipoUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoUsuarioItemStateChanged(evt);
            }
        });
        cbTipoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoUsuarioActionPerformed(evt);
            }
        });
        cbTipoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbTipoUsuarioKeyPressed(evt);
            }
        });

        txtPassword.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtPassword.setName("txtPassword"); // NOI18N
        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPasswordFocusGained(evt);
            }
        });
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPasswordKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 0, 153));
        jLabel5.setText("Password:");
        jLabel5.setName("jLabel5"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTipoUsuario, javax.swing.GroupLayout.Alignment.LEADING, 0, 220, Short.MAX_VALUE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18))
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ACCESO AL SISTEMA ");
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void txtPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyTyped
        if (evt.getKeyChar() == 10) {
            bAceptar.doClick();
        }
    }//GEN-LAST:event_txtPasswordKeyTyped
    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if (evt.getKeyChar() == 10) {
//            if (cboSede.isVisible()) {
//                cboSede.requestFocus();
//            } else {
                if (txtPassword.getText().trim().length() > 0) {
                    bAceptar.doClick();
                }
           // }
        }
    }//GEN-LAST:event_txtPasswordKeyPressed
    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed
    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        if (evt.getKeyChar() == 10 && txtUsuario.getText().trim().length() > 0) {
            txtPassword.grabFocus();
        }
    }//GEN-LAST:event_txtUsuarioKeyPressed
    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_bSalirActionPerformed
    private void bAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAceptarActionPerformed
        Acceder();
    }//GEN-LAST:event_bAceptarActionPerformed
    private void txtUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusGained
        if (cbTipoUsuario.getSelectedIndex() >= 0) {
            MostrarSede();
        }
    }//GEN-LAST:event_txtUsuarioFocusGained
    private void cbTipoUsuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoUsuarioItemStateChanged

    }//GEN-LAST:event_cbTipoUsuarioItemStateChanged
    private void cbTipoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoUsuarioActionPerformed
        if (cboTipoUsuarioListo) {
            if (cbTipoUsuario.getSelectedIndex() != -1) {

                if (!cbTipoUsuario.getSelectedItem().toString().equalsIgnoreCase("Administrador")) {
                    //jLabel3.setVisible(false);
                    //cboSede.setVisible(false);

                } else {

                    //jLabel3.setVisible(true);
                   // cboSede.setVisible(true);

                }

            }

        }
        pack();
    }//GEN-LAST:event_cbTipoUsuarioActionPerformed
    private void txtPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusGained
        control.VolverdeTxatx(txtUsuario);
    }//GEN-LAST:event_txtPasswordFocusGained

    private void jPanel2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseMoved

    private void cbTipoUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbTipoUsuarioKeyPressed
        control.passFocus(evt, txtUsuario);
    }//GEN-LAST:event_cbTipoUsuarioKeyPressed

    public void MostrarSede() {
        if (cbTipoUsuario.getSelectedItem().toString().equalsIgnoreCase("Administrador")) {
//            jLabel3.setVisible(true);
//            cboSede.setVisible(true);
        } else {
//            jLabel3.setVisible(false);
//            cboSede.setVisible(false);
        }
        pack();
    }

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
            java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);
                SubstanceLookAndFeel.setSkin(new JyMSystemSoftBlueMetalSkin());
//                UIManager.put("Panel.background", new Color(255, 255, 255));
                UIManager.put("Button.font", new Font("Arial", Font.BOLD, 12));
                UIManager.put("Menu.font", new Font("Arial", Font.BOLD, 12));
                UIManager.put("MenuItem.font", new Font("Arial", Font.BOLD, 12));
                UIManager.put("Button.Font", new Font("Arial", Font.PLAIN, 12));
                UIManager.put(SubstanceLookAndFeel.BUTTON_NO_MIN_SIZE_PROPERTY, Boolean.TRUE);
                System.setProperty("awt.useSystemAAFontSettings", "lcd");
                new Acceso().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAceptar;
    private javax.swing.JButton bSalir;
    private javax.swing.JComboBox cbTipoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
