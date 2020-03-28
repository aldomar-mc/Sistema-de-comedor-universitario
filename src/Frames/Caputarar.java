package Frames;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import Clases.*;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.logging.Level;

public class Caputarar extends javax.swing.JFrame {

    ConexionBD1 cn = new ConexionBD1();
    private DPFPCapture lector = DPFPGlobal.getCaptureFactory().createCapture();
    private DPFPEnrollment reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    private DPFPVerification verificador = DPFPGlobal.getVerificationFactory().createVerification();
    private DPFPTemplate template;
    public static String TEMPLATE_PROPERTY = "Template";
    Controlador control = new Controlador();

    protected void iniciar() {
        lector.addDataListener(new DPFPDataAdapter() {
            @Override
            public void dataAcquired(final DPFPDataEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        EnviarTexto("La Huella Digital ha sido Capturada!!");
                        ProcesarCaptura(e.getSample());
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            }
        });

        lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        EnviarTexto("El Lector de Huella esta Activado o conectado");
                    }
                });
            }

            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        EnviarTexto("El Lector de Huella esta Desactivado o no Conectado");
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            }
        });

        lector.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        EnviarTexto("El dedo ha sido colocado sobre la huella digital");
                    }
                });
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        EnviarTexto("El dedo ha sido quitado de la huella digital");
                    }
                });
            }
        });

        lector.addErrorListener(new DPFPErrorAdapter() {
            public void errorReader(final DPFPErrorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        EnviarTexto("Errot: " + e.getError());
                    }
                });
            }
        });
    }
    public DPFPFeatureSet featuresinscripcion;
    public DPFPFeatureSet featureverificacion;

    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose) {
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
    }

    public Image CrearImagenHuella(DPFPSample sample) {
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }

    public void DibujarHuella(Image image) {
        lblImagenHuella.setIcon(new ImageIcon(image.getScaledInstance(lblImagenHuella.getWidth(),
                lblImagenHuella.getHeight(), Image.SCALE_DEFAULT)));
        repaint();
    }

    public void EstadoHuella() {
        EnviarTexto("Muestra de huella necesaria para Guardar Template " + reclutador.getFeaturesNeeded());
        reclutador.getFeaturesNeeded();
    }

    public void EnviarTexto(String string) {
        jTextArea1.append(string + "\n");
    }

    public void start() {
        try {
            lector.startCapture();
        } catch (Exception e) {
        }
        
        EnviarTexto("Utilizando el Lector de Huella Digital");
    }

    public void stop() {
        lector.stopCapture();
        EnviarTexto("no se esta utilizando el lector");
    }

    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
        this.template = template;
        firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }

    public void ProcesarCaptura(DPFPSample sample) {
        featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
        featureverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
        if (featuresinscripcion != null) {
            try {
                System.out.println("Las Caracteristicas de la huella ha sido creadas");
                reclutador.addFeatures(featuresinscripcion);

                Image image = CrearImagenHuella(sample);
                DibujarHuella(image);

                btnVerificar.setEnabled(true);
                btnIdentificar.setEnabled(true);

            } catch (DPFPImageQualityException e) {
                System.err.println("Error: " + e.getMessage());
            } finally {
                EstadoHuella();

                switch (reclutador.getTemplateStatus()) {
                    case TEMPLATE_STATUS_READY:
                        stop();
                        setTemplate(reclutador.getTemplate());
                        EnviarTexto("la Plantilla de la huella digital ha sido creada y puede Verificarla");
                        btnIdentificar.setEnabled(false);
                        btnVerificar.setEnabled(false);
                        btGuardar.setEnabled(true);
                        btGuardar.grabFocus();
                        break;
                    case TEMPLATE_STATUS_FAILED:
                        reclutador.clear();
                        stop();
                        EstadoHuella();
                        setTemplate(null);
                        JOptionPane.showMessageDialog(Caputarar.this, "La plantila de la huella no pueod ser creada");
                        start();
                        break;
                }
            }
        }
    }

    public void guardarHuella() throws SQLException {
        ByteArrayInputStream datoshuella = new ByteArrayInputStream(template.serialize());
        Integer tamañoHuella = template.serialize().length;
        int idtipocom=Integer.parseInt(control.DevolverRegistroDto("select idtipocomensal from tipocomensal where descritipcom='"+jComboBox1.getSelectedItem().toString()+"';", 1));
        String nombre = JOptionPane.showInputDialog("Ingrese un N° de Dni: ");
        if(idtipocom==1){
            control.Sql = "SELECT count(*) FROM alumnos where dni='" + nombre + "';";            
            int verpersonal = Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
            if (verpersonal == 0) {
                JOptionPane.showMessageDialog(null, "No Existe ningun Alumno con el N° de DNI " + nombre);
                return;
            }
        }
        if(idtipocom==2){
            control.Sql = "SELECT count(*) FROM docentes where dni='" + nombre + "';";            
            int verpersonal = Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
            if (verpersonal == 0) {
                JOptionPane.showMessageDialog(null, "No Existe ningun Docente con el N° de DNI " + nombre);
                return;
            }
        }
        if(idtipocom==3){
            control.Sql = "SELECT count(*) FROM administrativos where dni='" + nombre + "';";            
            int verpersonal = Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
            if (verpersonal == 0) {
                JOptionPane.showMessageDialog(null, "No Existe ningun Personal Administrativo con el N° de DNI " + nombre);
                return;
            }
        }
        if(idtipocom==4){
            control.Sql = "SELECT count(*) FROM regcasosespeciales where dni='" + nombre + "';";            
            int verpersonal = Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
            if (verpersonal == 0) {
                JOptionPane.showMessageDialog(null, "No Existe ningun Personal Administrativo con el N° de DNI " + nombre);
                return;
            }
        }
        
        int idper=0;
        if(idtipocom==1){
            control.Sql = "SELECT idconsumidores FROM consumidores c,alumnos a where c.idconsumidores=a.idalumnos and a.dni='" + nombre + "';";
            idper = Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));            
        }
        if(idtipocom==2){
            control.Sql = "SELECT idconsumidores FROM consumidores c,docentes a where c.idconsumidores=a.idalumnos and a.dni='" + nombre + "' ;";
            idper = Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));            
        }
        if(idtipocom==3){
            control.Sql = "SELECT idconsumidores FROM consumidores c,administrativos a where c.idconsumidores=a.idalumnos and a.dni='" + nombre + "' ;";
            idper = Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));            
        }
        if(idtipocom==4){
            control.Sql = "SELECT idconsumidores FROM consumidores c,regcasosespeciales a where c.idconsumidores=a.idregcasosespeciales and a.dni='" + nombre + "' ;";
            idper = Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));            
        }
          
        try {
            Connection c = cn.conec;
            PreparedStatement guardarStmt = c.prepareStatement("Insert into somhue(ID, huenombre, huehuella)values(?,?,?);");
            guardarStmt.setInt(1, idper);
            guardarStmt.setString(2, nombre);
            guardarStmt.setBinaryStream(3, datoshuella, tamañoHuella);
            guardarStmt.execute();
            guardarStmt.close();
            JOptionPane.showMessageDialog(null, "la huella se guardo correctamente");
            btGuardar.setEnabled(false);
            btnVerificar.grabFocus();

        } catch (SQLException e) {
            System.err.println("Error al guardar la huella");
        }
    }

    public void verficvarHuella(String nom) {
        try {
            Connection c = cn.conec;
            PreparedStatement verificarStmt = c.prepareStatement("select huehuella from somehue where huenombre=?");
            verificarStmt.setString(1, nom);
            ResultSet rs = verificarStmt.executeQuery();
            if (rs.next()) {
                byte templateBuffer[] = rs.getBytes("huehuella");
                DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
                setTemplate(referenceTemplate);
                DPFPVerificationResult result = verificador.verify(featureverificacion, getTemplate());
                if (result.isVerified()) {
                    JOptionPane.showMessageDialog(null, "Las huellas captura con la de " + nom, " Verifica de huella", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "no corresponde con la de " + nom, " Verifica de huella", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No existe un registro de huellas para " + nom, " Verifica de huella", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            System.err.println("Error al verrificar los datos de la huella");
        }
    }

    public void identificarHuella() throws IOException {
        try {
            Connection c = cn.conec;
            PreparedStatement identificarStmt = c.prepareStatement("select huenombre, huehuella from somhue");
            ResultSet rs = identificarStmt.executeQuery();
            while (rs.next()) {
                byte templateBuffer[] = rs.getBytes("huehuella");
                String nombre = rs.getString("huenombre");
                DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
                setTemplate(referenceTemplate);
                DPFPVerificationResult result = verificador.verify(featureverificacion, getTemplate());
                int idtipocom=Integer.parseInt(control.DevolverRegistroDto("select idtipocomensal from tipocomensal where descritipcom='"+jComboBox1.getSelectedItem().toString()+"';", 1));
                if (result.isVerified()) {
                    if(idtipocom==1){
                        control.Sql = "SELECT if(idconsumidores=null,'',a.nombres) FROM consumidores c,alumnos a where c.idcomensales=a.idalumnos and c.idtipocomensal=1 and a.dni='" + nombre + "';";
                        String nom = control.DevolverRegistroDto(control.Sql, 1);
                        JOptionPane.showMessageDialog(null, "la Huella captura es de " + nom, "Verificador de Huella", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if(idtipocom==2){
                        control.Sql = "SELECT if(idconsumidores=null,'No Existe',a.nombres) FROM consumidores c,docentes a where c.idcomensales=a.iddocentes and c.idtipocomensal=2 and a.dni='" + nombre + "';";
                        String nom = control.DevolverRegistroDto(control.Sql, 1);
                        JOptionPane.showMessageDialog(null, "la Huella captura es de " + nom, "Verificador de Huella", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if(idtipocom==3){
                        control.Sql = "SELECT if(idconsumidores=null,'No Existe',a.nombres) FROM consumidores c,administrativos a where c.idcomensales=a.idadministrativos and c.idtipocomensal=3 and a.dni='" + nombre + "';";
                        String nom = control.DevolverRegistroDto(control.Sql, 1);
                        JOptionPane.showMessageDialog(null, "la Huella captura es de " + nom, "Verificador de Huella", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if(idtipocom==4){
                        control.Sql = "SELECT if(idconsumidores=null,'No Existe',a.nombres) FROM consumidores c,regcasosespeciales a where c.idcomensales=a.idregcasosespeciales and c.idtipocomensal=4 and a.dni='" + nombre + "';";
                        String nom = control.DevolverRegistroDto(control.Sql, 1);
                        JOptionPane.showMessageDialog(null, "la Huella captura es de " + nom, "Verificador de Huella", JOptionPane.INFORMATION_MESSAGE);
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "no existe ningun regitro que coincida con la huella", "Verificacion de Huella", JOptionPane.ERROR_MESSAGE);
            setTemplate(null);
//           btGuardar.setEnabled(true);

        } catch (SQLException e) {
            System.err.println("Error al identificar huella dactialar " + e.getMessage());
        }
    }

    public Caputarar() {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null,"imposisble modificar el tema visualizar el tema","",JOptionPane.ERROR_MESSAGE);
//        }
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Vender.png")).getImage());
 //       this.setFrameIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Vender.png")));
        this.setTitle("Grabar Huellas Digitales");
        control.LlenarCombo(jComboBox1,"select * from tipocomensal",2);
        jComboBox1.setSelectedIndex(0);
        btnVerificar.setVisible(false);
        setLocationRelativeTo(null);
        start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PnCaptura = new javax.swing.JPanel();
        lblImagenHuella = new javax.swing.JLabel();
        PnAciones = new javax.swing.JPanel();
        btnVerificar = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        btnIdentificar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        PnCaptura.setBackground(new java.awt.Color(255, 255, 255));
        PnCaptura.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Huella Capturada", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lblImagenHuella.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PnCapturaLayout = new javax.swing.GroupLayout(PnCaptura);
        PnCaptura.setLayout(PnCapturaLayout);
        PnCapturaLayout.setHorizontalGroup(
            PnCapturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagenHuella, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PnCapturaLayout.setVerticalGroup(
            PnCapturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagenHuella, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        PnAciones.setBackground(new java.awt.Color(255, 255, 255));
        PnAciones.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Capturar Huella", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N

        btnVerificar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnVerificar.setForeground(new java.awt.Color(0, 51, 102));
        btnVerificar.setMnemonic('V');
        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        btGuardar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btGuardar.setForeground(new java.awt.Color(0, 51, 102));
        btGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/registrar.png"))); // NOI18N
        btGuardar.setMnemonic('G');
        btGuardar.setText("Guardar");
        btGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarActionPerformed(evt);
            }
        });

        btnIdentificar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnIdentificar.setForeground(new java.awt.Color(0, 51, 102));
        btnIdentificar.setMnemonic('I');
        btnIdentificar.setText("Indentificar");
        btnIdentificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIdentificarActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(0, 51, 102));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir.png"))); // NOI18N
        btnSalir.setMnemonic('S');
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Tipo Conmensal");

        javax.swing.GroupLayout PnAcionesLayout = new javax.swing.GroupLayout(PnAciones);
        PnAciones.setLayout(PnAcionesLayout);
        PnAcionesLayout.setHorizontalGroup(
            PnAcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnAcionesLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(PnAcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PnAcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnIdentificar, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                        .addComponent(btnVerificar, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(PnAcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addGap(57, 57, 57))
        );
        PnAcionesLayout.setVerticalGroup(
            PnAcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnAcionesLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(PnAcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnAcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnIdentificar, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnAcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVerificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PnAciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(PnCaptura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(PnCaptura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(PnAciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        stop();
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed

        String nombre = JOptionPane.showInputDialog("Dni a Verificar: ");
        verficvarHuella(nombre);
        reclutador.clear();

    }//GEN-LAST:event_btnVerificarActionPerformed

    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
        try {
            
            guardarHuella();
            reclutador.clear();
            lblImagenHuella.setIcon(null);
            start();

        } catch (SQLException ex) {
            Logger.getLogger(Caputarar.class.getName()).log(Level.SEVERE, null, ex);
//            System.err.println(ex.getMessage());

        }
    }//GEN-LAST:event_btGuardarActionPerformed

    private void btnIdentificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIdentificarActionPerformed

        try {
            identificarHuella();
            reclutador.clear();
            lblImagenHuella.setIcon(null);

        } catch (IOException e) {
            System.err.println(e.getMessage());
            Logger.getLogger(Caputarar.class.getName()).log(Level.SEVERE, null, e);
        }

    }//GEN-LAST:event_btnIdentificarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        stop();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        iniciar();
        start();
        EstadoHuella();
        btGuardar.setEnabled(false);
        btnIdentificar.setEnabled(false);
        btnVerificar.setEnabled(false);
        btnSalir.grabFocus();

    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(Caputarar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caputarar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caputarar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caputarar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caputarar().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnAciones;
    private javax.swing.JPanel PnCaptura;
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btnIdentificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblImagenHuella;
    // End of variables declaration//GEN-END:variables
    
}
