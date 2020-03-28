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
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import Clases.*;
//import static Ventanas.PantallaPrincipal.tpiso;
//import java.util.logging.Level;

public class Asistencia extends javax.swing.JFrame {

    ConexionBD1 cn = new ConexionBD1();
    private DPFPCapture lector = DPFPGlobal.getCaptureFactory().createCapture();
    private DPFPEnrollment reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    private DPFPVerification verificador = DPFPGlobal.getVerificationFactory().createVerification();
    private DPFPTemplate template;
    public static String TEMPLATE_PROPERTY = "Template";
    int dia, mes, año, hora, minutos, segundos;
    String am_pm, nombredia, turno, codigoAsis = "", hor;
    java.util.Calendar calendario;
    Mimodelo modelo = new Mimodelo();
    Controlador control = new Controlador();
    InfoGeneral info = new InfoGeneral();
private String ruta="";
    private void reloj() {
        calendario = new java.util.GregorianCalendar();
        //segundos = calendario.get(Calendar.SECOND); 
        javax.swing.Timer timer = new javax.swing.Timer(1000, new java.awt.event.ActionListener() {
            @ Override
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                java.util.Date actual = new java.util.Date();
                calendario.setTime(actual);
                dia = calendario.get(Calendar.DAY_OF_MONTH);
                mes = (calendario.get(Calendar.MONTH) + 1);
                año = calendario.get(Calendar.YEAR);
                hora = calendario.get(Calendar.HOUR);
                minutos = calendario.get(Calendar.MINUTE);
                segundos = calendario.get(Calendar.SECOND);
                if (calendario.get(Calendar.AM_PM) == 0) {
                    am_pm = "AM";
                } else {
                    am_pm = "PM";
                }
                if (hora == 00) {
                    hora = 12;
                }
                String hour = String.format("%02d:%02d", hora, minutos) + " " + am_pm;
                hor = String.format("%02d:%02d:%02d", hora, minutos, segundos);
                String di = String.format("%02d", dia);
                String an = String.format("%02d", año);
                String fecha = di + " " + mesNombre(mes) + " " + an;
                lbhora.setText("<html><center>" + hour);
                lbFecha.setText("<html><center>" + sde() + ", " + fecha);
            }
        });
        timer.start();
    }

    public String mesNombre(int mess) {
        String mes = "";
        switch (mess) {
            case 1:
                mes = "Enero";
                break;
            case 2:
                mes = "Febrero";
                break;
            case 3:
                mes = "Marzo";
                break;
            case 4:
                mes = "Abril";
                break;
            case 5:
                mes = "Mayo";
                break;
            case 6:
                mes = "Junio";
                break;
            case 7:
                mes = "Julio";
                break;
            case 8:
                mes = "Agosto";
                break;
            case 9:
                mes = "Setiembre";
                break;
            case 10:
                mes = "Octubre";
                break;
            case 11:
                mes = "Noviembre";
                break;
            case 12:
                mes = "Diciembre";
                break;
        }
        return mes;
    }

    public String sde() {
        control.Sql = "SELECT DATE_FORMAT(curdate(),'%W') nom;";
        String nomdia = control.DevolverRegistroDto(control.Sql, 1);
        switch (nomdia) {
            case "Monday": {
                nombredia = "Lunes";
                break;
            }
            case "Tuesday": {
                nombredia = "Martes";
                break;
            }
            case "Wednesday": {
                nombredia = "Miercoles";
                break;
            }
            case "Thursday": {
                nombredia = "Jueves";
                break;
            }
            case "Friday": {
                nombredia = "Viernes";
                break;
            }
            case "Saturday": {
                nombredia = "Sabado";
                break;
            }
            case "Sunday": {
                nombredia = "Domingo";
                break;
            }
        }
        return nombredia;
    }

    protected void iniciar() {
        lector.addDataListener(new DPFPDataAdapter() {
            @Override
            public void dataAcquired(final DPFPDataEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        EnviarTexto("La Huella Digital ha sido Capturada!!");
                        ProcesarCaptura(e.getSample());
                       
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
                        EnviarTexto("El dedo ha sido colocado sobre la huella digital");
                    }
                });
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
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
        EnviarTexto("Muestra de huella necesaria par Guardar Template " + reclutador.getFeaturesNeeded());
        ;
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
                btnIdentificar.grabFocus();

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
                        JOptionPane.showMessageDialog(Asistencia.this, "La plantila de la huella no puede ser creada");
                        start();
                        break;
                }
            }
        }
    }

    public void guardarHuella() throws SQLException {
        ByteArrayInputStream datoshuella = new ByteArrayInputStream(template.serialize());
        Integer tamañoHuella = template.serialize().length;
        String nombre = JOptionPane.showInputDialog("Nombre: ");
        try {
            Connection c = cn.conec;
            PreparedStatement guardarStmt = c.prepareStatement("Insert into somhue(ID, huenombre, huehuella)values(?,?,?);");
            guardarStmt.setInt(1, 1);
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
                if (result.isVerified()) {
                    //JOptionPane.showMessageDialog(null, "la Huella captura es de "+nombre,"Verificador de Huella",JOptionPane.INFORMATION_MESSAGE);
                    info.setDniee(nombre);
                    txDni.setText(nombre);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "no existe ningun regitro que coincida con la huella", "Verificacion de Huella", JOptionPane.ERROR_MESSAGE);
            setTemplate(null);
//           btGuardar.setEnabled(true);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al identificar huella dactialar " + e.getMessage());
            System.err.println("Error al identificar huella dactialar " + e.getMessage());
        }
    }

    public Asistencia() {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null,"imposisble modificar el tema visualizar el tema","",JOptionPane.ERROR_MESSAGE);
//        }
        reloj();
        initComponents();
        this.setTitle("Registro de Asistencia de Comensales");
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Vender.png")).getImage());
////aqui//// this.procedimientosSDK = new Util(this);
////aqui////  procedimientosSDK.inicializarCaptura();
        tpiso.setModel(modelo);//idasistencia, situacion,nom, dni, descturno, hentrada, hsalida,  horain, horafi
        modelo.setColumnIdentifiers(new String[]{"Id", "Tipo Comensal", "Facultad", "Dni/Codigo", "Nombres", "Horario de Registro", "Fecha", "Turno"});
        tpiso.getColumnModel().getColumn(0).setMaxWidth(0);
        tpiso.getColumnModel().getColumn(0).setMinWidth(0);
        tpiso.getColumnModel().getColumn(0).setPreferredWidth(0);
        tpiso.getColumnModel().getColumn(1).setPreferredWidth(50);
        tpiso.getColumnModel().getColumn(2).setPreferredWidth(40);
        tpiso.getColumnModel().getColumn(3).setPreferredWidth(40);
        tpiso.getColumnModel().getColumn(4).setPreferredWidth(120);
        tpiso.getColumnModel().getColumn(5).setPreferredWidth(50);
        tpiso.getColumnModel().getColumn(6).setPreferredWidth(60);
        tpiso.getColumnModel().getColumn(7).setPreferredWidth(50);
        control.forma_table_ver(modelo, tpiso);
        setLocationRelativeTo(null);
        ruta=control.DevolverRegistroDto("select * from directorio;", 2);
        cargar();
        btnIdentificar.grabFocus();
        btGuardar.setVisible(false);
        btnVerificar.setVisible(false);
        start();

    }
public void cargar() {
    control.Sql="SELECT * FROM vat_asistencia where fecha=curdate() and (nombres like '%"+txbuscar.getText()+"%' or dni like '%"+txbuscar.getText()+"%')";
    control.LlenarJTabla(modelo, control.Sql, 8);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tpiso = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txbuscar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txDni = new javax.swing.JTextField();
        lbTurno = new javax.swing.JLabel();
        panelCurves2 = new org.edisoncor.gui.panel.PanelCurves();
        clockFace1 = new org.edisoncor.gui.varios.ClockFace();
        lbhora = new javax.swing.JLabel();
        lbFecha = new javax.swing.JLabel();
        lbFoto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        PnCaptura.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnCaptura.setForeground(new java.awt.Color(0, 51, 102));

        javax.swing.GroupLayout PnCapturaLayout = new javax.swing.GroupLayout(PnCaptura);
        PnCaptura.setLayout(PnCapturaLayout);
        PnCapturaLayout.setHorizontalGroup(
            PnCapturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnCapturaLayout.createSequentialGroup()
                .addComponent(lblImagenHuella, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PnCapturaLayout.setVerticalGroup(
            PnCapturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagenHuella, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        PnAciones.setForeground(new java.awt.Color(0, 51, 102));

        btnVerificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnVerificar.setForeground(new java.awt.Color(0, 51, 102));
        btnVerificar.setText("Verificar");
        btnVerificar.setPreferredSize(new java.awt.Dimension(100, 30));
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });
        PnAciones.add(btnVerificar);

        btGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btGuardar.setForeground(new java.awt.Color(0, 51, 102));
        btGuardar.setText("Guardar");
        btGuardar.setPreferredSize(new java.awt.Dimension(100, 30));
        btGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarActionPerformed(evt);
            }
        });
        PnAciones.add(btGuardar);

        btnIdentificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnIdentificar.setForeground(new java.awt.Color(0, 51, 102));
        btnIdentificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/registrar.png"))); // NOI18N
        btnIdentificar.setText("Asistencia");
        btnIdentificar.setPreferredSize(new java.awt.Dimension(130, 30));
        btnIdentificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIdentificarActionPerformed(evt);
            }
        });
        PnAciones.add(btnIdentificar);

        btnSalir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(0, 51, 102));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setPreferredSize(new java.awt.Dimension(100, 30));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        PnAciones.add(btnSalir);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        tpiso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tpiso.setForeground(new java.awt.Color(0, 51, 102));
        tpiso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        tpiso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tpisoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tpiso);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Buscar");

        txbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txbuscarKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 102));
        jLabel5.setText("Dni");

        txDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txDniKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txDniKeyTyped(evt);
            }
        });

        lbTurno.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbTurno.setForeground(new java.awt.Color(0, 51, 102));
        lbTurno.setText(" ");

        panelCurves2.add(clockFace1, java.awt.BorderLayout.CENTER);

        lbhora.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        lbhora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbhora.setText(" ");
        lbhora.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbFecha.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        lbFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFecha.setText(" ");
        lbFecha.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbFoto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFoto.setText("Foto");
        lbFoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(PnCaptura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(PnAciones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
                                        .addGap(3, 3, 3))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txDni, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(panelCurves2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbhora, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PnCaptura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PnAciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(txDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbTurno)
                                .addGap(5, 5, 5))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelCurves2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbhora, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed

        stop();
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed

        String nombre = JOptionPane.showInputDialog("Nombre a Verificar: ");
        verficvarHuella(nombre);
        reclutador.clear();
        start();

    }//GEN-LAST:event_btnVerificarActionPerformed

    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
        try {
            guardarHuella();
            reclutador.clear();
            lblImagenHuella.setIcon(null);
            start();

        } catch (SQLException ex) {
            Logger.getLogger(Asistencia.class.getName()).log(Level.SEVERE, null, ex);
//            System.err.println(ex.getMessage());

        }
    }//GEN-LAST:event_btGuardarActionPerformed

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
//        cargar();

    }//GEN-LAST:event_formWindowOpened

    private void tpisoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpisoMouseClicked
        //        if (evt.getClickCount() == 2) {
        //            if (tpiso.getSelectedRowCount() == 1) {
        String mesj = tpiso.getValueAt(tpiso.getSelectedRow(), 1).toString();
        if (mesj.compareTo("Permiso") == 0) {
            control.Sql = "SELECT * FROM salidas where idasistencia='" + tpiso.getValueAt(tpiso.getSelectedRow(), 0).toString() + "' order by idsalidas desc limit 1;";
            String sd = control.DevolverRegistroDto(control.Sql, 5);
            String sd1 = control.DevolverRegistroDto(control.Sql, 6);
            String sd2 = control.DevolverRegistroDto(control.Sql, 2);
            JOptionPane.showMessageDialog(null, "Personal salio de Permiso:\nMotivo: " + sd + "\nResponsable: " + sd1 + "\nHora Salida: " + sd2);
        }
    }//GEN-LAST:event_tpisoMouseClicked

    private void txbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txbuscarKeyReleased
        cargar();
    }//GEN-LAST:event_txbuscarKeyReleased

    

    private void txDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDniKeyPressed
        if (evt.getKeyChar() == 10) {
            if (txDni.getText().length() <15) {
                info.setDniee(txDni.getText());
                AgregarIngreso();
                txDni.grabFocus();
            } else {
                JOptionPane.showMessageDialog(null, "No ha Ingresado el DNI Correctamente!!");
            }
        }
    }//GEN-LAST:event_txDniKeyPressed

    public void AgregarIngreso() {

        verificar();
        //RegistarAsistencia();
    //    cargar();
        ///////    RegistrarIngreso();
        txDni.setText("");
//        dniiii = txdescpiso.getText();

    }

    public void gg() {
        control.Sql = "select hour(curtime());";
        int hr = Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
        if (hr < 12) {
            turno = "Mañana";
            lbTurno.setText("Turno: Mañana");
        } else {
            if (hr < 18) {
                turno = "Tarde";
                lbTurno.setText("Turno: Tarde");
            } else {
                turno = "Noche";
                lbTurno.setText("Turno: Noche");
            }
        }
    }

    public void mensajemn(String msj) {
        JOptionPane.showMessageDialog(null, "<html><font size='6'>" + msj + "</font></html>");
    }
    private void txDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDniKeyTyped
        control.Solonumeros(evt);
    }//GEN-LAST:event_txDniKeyTyped

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        cargar();        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void btnIdentificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIdentificarActionPerformed

        try {
            identificarHuella();
            AgregarIngreso();
            reclutador.clear();
            lblImagenHuella.setIcon(null);

            btGuardar.setEnabled(false);
            btnIdentificar.setEnabled(false);
            btnVerificar.setEnabled(false);
            btnSalir.grabFocus();
            start();

        } catch (IOException e) {
            System.err.println(e.getMessage());
            Logger.getLogger(Asistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnIdentificarActionPerformed

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
            java.util.logging.Logger.getLogger(Asistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Asistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Asistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Asistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Asistencia().setVisible(true);
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
    private org.edisoncor.gui.varios.ClockFace clockFace1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JLabel lbTurno;
    private javax.swing.JLabel lbhora;
    private javax.swing.JLabel lblImagenHuella;
    private org.edisoncor.gui.panel.PanelCurves panelCurves2;
    public static javax.swing.JTable tpiso;
    private javax.swing.JTextField txDni;
    private javax.swing.JTextField txbuscar;
    // End of variables declaration//GEN-END:variables

    private void verificar() {
        lbFoto.setText("");
        String dni=txDni.getText();
        System.out.println(dni);
        if(dni.trim().length()>0){
            control.Sql="SELECT count(*) FROM comensale where dni='"+dni+"';";
            int cntcon=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
            if(cntcon>0){
                
                control.Sql="select count(foto) from alumnos where dni='"+dni+"';";
                int al=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
                if(al==1){
                    control.Sql="select foto from alumnos where dni='"+dni+"';";
                    String foto=control.DevolverRegistroDto(control.Sql, 1);
                    if(foto.trim().length()>0 && foto.equals("sinImagen")==false){
                        //lbFoto.setIcon(new ImageIcon("D:\\fotos\\"+foto)); 
                        //ImageIcon fot = new ImageIcon("D:\\fotos\\"+foto);
                        ImageIcon fot = new ImageIcon(ruta+foto);
                        ImageIcon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), java.awt.Image.SCALE_DEFAULT));
                        lbFoto.setIcon(icono);
                    }else{
                        lbFoto.setIcon(null);
                        lbFoto.setText("FOTO");
                    }
                }
                control.Sql="select count(foto) from docentes where dni='"+dni+"';";
                int doc=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
                if(doc==1){
                    control.Sql="select foto from docentes where dni='"+dni+"';";
                    String foto=control.DevolverRegistroDto(control.Sql, 1);
                    if(foto.trim().length()>0 && foto.equals("sinImagen")==false ){
                        //lbFoto.setIcon(new ImageIcon("D:\\fotos\\"+foto)); 
                        ImageIcon fot = new ImageIcon(ruta+foto);
                        ImageIcon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), java.awt.Image.SCALE_DEFAULT));
                        lbFoto.setIcon(icono);
                    }else{
                        lbFoto.setText("Foto");
                    }
                }
                control.Sql="select count(foto) from administrativos where dni='"+dni+"';";
                int ad=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
                if(ad==1){
                    control.Sql="select foto from administrativos where dni='"+dni+"';";
                    String foto=control.DevolverRegistroDto(control.Sql, 1);
                    if(foto.trim().length()>0 && foto.equals("sinImagen")==false){
                        ImageIcon fot = new ImageIcon(ruta+foto);
                        ImageIcon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), java.awt.Image.SCALE_DEFAULT));
                        lbFoto.setIcon(icono);
                    }else{
                        lbFoto.setIcon(null);
                        lbFoto.setText("FOTO");
                    }
                }
                control.Sql="select count(foto) from regcasosespeciales where dni='"+dni+"';";
                int casos=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
                if(casos==1){
                    control.Sql="select foto from regcasosespeciales where dni='"+dni+"';";
                    String foto=control.DevolverRegistroDto(control.Sql, 1);
                    if(foto.trim().length()>0 && foto.equals("sinImagen")==false){
                        //lbFoto.setIcon(new ImageIcon("D:\\fotos\\"+foto)); 
                        ImageIcon fot = new ImageIcon(ruta+foto);
                        ImageIcon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), java.awt.Image.SCALE_DEFAULT));
                        lbFoto.setIcon(icono);
                    }else{
                        lbFoto.setIcon(null);
                        lbFoto.setText("Foto");
                    }
                }
                
                control.Sql="SELECT count(*) FROM crono_comensale where dni='"+dni+"' and fechaconsumo=curdate()and estadocrono=0;";
                System.out.println(control.Sql);
                int vercrono=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
                if(vercrono==1){
                    control.Sql="SELECT idcronogramaconsumo FROM crono_comensale where dni='"+dni+"' and fechaconsumo=curdate();";
                    String idcrono=control.DevolverRegistroDto(control.Sql, 1);
                    System.out.println(control.Sql);
                    control.Sql="select count(*) from turno where curtime() between horamin and horamax;";
                    int turn=Integer.parseInt(control.DevolverRegistroDto(control.Sql, 1));
                    System.out.println(control.Sql);
                    if(turn==1){
                        control.Sql="select * from turno where curtime() between horamin and horamax;";
                        String idturn=control.DevolverRegistroDto(control.Sql, 1);
                        System.out.println(control.Sql);
                        control.Sql="select count(*) from asistencia where  idcronogramaconsumo='"+idcrono+"' and idturno='"+idturn+"';";
                        int vercua=Integer.parseInt(control.DevolverRegistroDto(control.Sql,1));
                        System.out.println(control.Sql);
                        if(vercua==0){
                            control.Sql=String.format("insert into asistencia values(null,curtime(),'%s','%s',curdate());",idcrono,idturn);
                            control.CrearRegistro(control.Sql);
                            cargar();
                        }else{
                            JOptionPane.showMessageDialog(null, "Usted ya Consumio la Racion de Hoy","System Message",3);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "No esta Aperturado Horario de Consumo de Alimentos","System Message",3);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Usted no tiene Asignado el Menu de Hoy!!","System Message",3);
                }
            }else{
                JOptionPane.showMessageDialog(null, "No Existe Registro con el N° de Dni "+dni);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Ingrese N° de DNI");
        }
    }
}
