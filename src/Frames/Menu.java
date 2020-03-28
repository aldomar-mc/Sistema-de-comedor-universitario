/**
 * ** @author Miguel Silva Zapata
 */
package Frames;

import Clases.Controlador;
import Clases.InfoGeneral;
import Internals.CambiarClave;
import Internals.Casos_Especiales;
import Internals.CrearPresmiso;
import Internals.Facultad;
import Internals.LosClientes;
import Internals.Permisos;
import Internals.Registro_Administrativos;
import Internals.Registro_Alumnos;
import Internals.Registro_Asistencia_Manual;
import Internals.Registro_Casos_Especiales;
import Internals.Registro_Docentes;
import Internals.Registro_Pago;
import Internals.Semestre_Academico;
import Internals.Tarifas_Consumo;
import Internals.TipoComensal;
import Internals.TipoComprobante;
import Internals.Turnos_Consumo;
import Internals.Usuarios;
import Internals.Ver_Asistencia;
import Internals.Ver_Balance;
import Internals.Ver_Comensales_Hoy;
import Internals.Ver_Comensales_Inscritos;
import Internals.Ver_Cronograma_Comensal;
import Internals.Ver_Estadisticas_De_Comensales;
import Internals.Ver_Licencias_Fechas;
import Internals.Ver_InAsistencias;
import Internals.Ver_Pagos_Realizados;
import Internals.Ver_Total_Pagos;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class Menu extends javax.swing.JFrame {

    /**
     * ****************Los Atributos***************
     */
    private Rectangle R = null;
    Controlador control = new Controlador();
    InfoGeneral info = new InfoGeneral();
    private static Image imagen;
    String us = "";

    /**
     * ****************Los Atributos***************
     */
    /**
     * ****************Los metodos***************
     */
    private void setPosition(JInternalFrame internalFrame) {
        internalFrame.setLocation((R.width - internalFrame.getWidth()) / 2, 10);
    }

    public void setInternalFrame(JInternalFrame internalFrame) {
        jDesktopPane1.add(internalFrame, JLayeredPane.DEFAULT_LAYER);
        setPosition(internalFrame);
        internalFrame.setVisible(true);
    }

    public void CargarFormulario(int dto) {
        boolean b = false;
        JInternalFrame frame = null;
        switch (dto) {
            case 1: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Usuarios) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Usuarios objPrestarProductos = new Usuarios();
                    setInternalFrame(objPrestarProductos);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 2: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof CambiarClave) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    CambiarClave objPrestarProductos = new CambiarClave();
                    setInternalFrame(objPrestarProductos);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 3: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof CrearPresmiso) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    CrearPresmiso objPrestarProductos = new CrearPresmiso();
                    setInternalFrame(objPrestarProductos);
                }else{
                    frame.toFront();
                }
                break;

            }

            case 4: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Permisos) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Permisos objPrestarProductos = new Permisos();
                    setInternalFrame(objPrestarProductos);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 5: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof LosClientes) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    LosClientes clien = new LosClientes();
                    setInternalFrame(clien);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 6: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Facultad) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Facultad facu = new Facultad();
                    setInternalFrame(facu);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 7: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof TipoComensal) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    TipoComensal comen = new TipoComensal();
                    setInternalFrame(comen);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 8: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof TipoComprobante) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    TipoComprobante comp = new TipoComprobante();
                    setInternalFrame(comp);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 9: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Semestre_Academico) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Semestre_Academico sem = new Semestre_Academico();
                    setInternalFrame(sem);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 10: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Tarifas_Consumo) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Tarifas_Consumo tar = new Tarifas_Consumo();
                    setInternalFrame(tar);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 11: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Registro_Alumnos) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Registro_Alumnos regcom = new Registro_Alumnos();
                    setInternalFrame(regcom);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 12: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Registro_Docentes) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Registro_Docentes regdoc = new Registro_Docentes();
                    setInternalFrame(regdoc);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 13: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Registro_Administrativos) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Registro_Administrativos regadm = new Registro_Administrativos();
                    setInternalFrame(regadm);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 14: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Registro_Pago) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Registro_Pago regpag = new Registro_Pago();
                    setInternalFrame(regpag);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 15: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Ver_Pagos_Realizados) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Ver_Pagos_Realizados verpag = new Ver_Pagos_Realizados();
                    setInternalFrame(verpag);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 16: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Registro_Asistencia_Manual) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Registro_Asistencia_Manual regmanulasi = new Registro_Asistencia_Manual();
                    setInternalFrame(regmanulasi);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 17: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Turnos_Consumo) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Turnos_Consumo turn = new Turnos_Consumo();
                    setInternalFrame(turn);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 18: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Ver_Comensales_Hoy) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Ver_Comensales_Hoy verhoy = new Ver_Comensales_Hoy();
                    setInternalFrame(verhoy);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 19: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Ver_Total_Pagos) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Ver_Total_Pagos verpg = new Ver_Total_Pagos();
                    setInternalFrame(verpg);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 20: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Ver_Asistencia) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Ver_Asistencia verasis = new Ver_Asistencia();
                    setInternalFrame(verasis);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 21: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Ver_Balance) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Ver_Balance verbalance = new Ver_Balance();
                    setInternalFrame(verbalance);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 22: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Ver_Comensales_Inscritos) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Ver_Comensales_Inscritos verInscritos = new Ver_Comensales_Inscritos();
                    setInternalFrame(verInscritos);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 23: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Ver_Licencias_Fechas) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Ver_Licencias_Fechas verInAsis = new Ver_Licencias_Fechas();
                    setInternalFrame(verInAsis);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 24: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Ver_InAsistencias) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Ver_InAsistencias verLicicencia = new Ver_InAsistencias();
                    setInternalFrame(verLicicencia);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 25: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Ver_Estadisticas_De_Comensales) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Ver_Estadisticas_De_Comensales verLicicencia = new Ver_Estadisticas_De_Comensales();
                    setInternalFrame(verLicicencia);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 26: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Ver_Cronograma_Comensal) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Ver_Cronograma_Comensal vercronog = new Ver_Cronograma_Comensal();
                    setInternalFrame(vercronog);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 27: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Casos_Especiales) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Casos_Especiales vercasos = new Casos_Especiales();
                    setInternalFrame(vercasos);
                }else{
                    frame.toFront();
                }
                break;
            }
            case 28: {
                for (int i = 0; i < jDesktopPane1.getAllFrames().length; i++) {
                    if (jDesktopPane1.getAllFrames()[i] instanceof Registro_Casos_Especiales) {
                        b = true;
                        frame = jDesktopPane1.getAllFrames()[i];
                        break;
                    }
                }
                if (!b) {
                    Registro_Casos_Especiales veregcasos = new Registro_Casos_Especiales();
                    setInternalFrame(veregcasos);
                }else{
                    frame.toFront();
                }
                break;
            }
        }
    }

    public void ReiniciarElSistema() {
        this.dispose();
        new Acceso().setVisible(true);
    }

    public Menu() {
        Iniciar();
//     jDesktopPane1.setBackground(new Color(51,153,255));//(0,102,153)
        jDesktopPane1.setBackground(new Color(195, 221, 240));
    }

    public void Iniciar() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Vender.png")).getImage());
            imagen = new ImageIcon(getClass().getResource("/Imagenes/logo.png")).getImage();
        Actulizar_FechasAntgeriores();
        capturarUsuario();
        jMenu2.setVisible(false);
        jMenu4.setVisible(false);
        
        jMenuItem57.setVisible(false); 
        jMenuItem53.setVisible(false);
    }

    public void capturarUsuario() {
        String path = "/Imagenes/emotion_happy.png";
        URL url = this.getClass().getResource(path);
        ImageIcon icon = new ImageIcon(url);
        control.Sql = "SELECT nomusr FROM usuario where nomusr='" + info.getUsuario() + "';";
        String usaior = control.DevolverRegistroDto(control.Sql, 1);
        control.Sql = "select idusuario from usuario where nomusr='" + info.getUsuario() + "'";
         us = control.DevolverRegistroDto(control.Sql, 1);
        setTitle("Sistema Control de Comensales Ver 1.0             "
                + "                                           " + "Usuario: " + usaior + " ");
    }

     
    public boolean verificarpermiso(String permiso) {
        boolean a = false;
        control.Sql = "SELECT idpermisos FROM permisos where Descripcion='" + permiso + "';";
        String codper = control.DevolverRegistroDto(control.Sql, 1);
        System.out.println(control.Sql);
        control.Sql = "select count(*) from permisosusuario where idusuario='" + us + "' and idpermisos='" + codper + "';";
        System.out.println(control.Sql);
        String ver = control.DevolverRegistroDto(control.Sql, 1);
        if (ver.compareTo("1") == 0) {
            a = true;
        }
        return a;
    }

    /**
     * ****************Los metodos***************
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d=(Graphics2D)g;
                g2d.setColor(new Color(0,50,255));
                //g2d.fillRect(0, 0, getWidth(), getHeight());
                int altura = (int)imagen.getHeight(this)/2;
                int anchura = (int)(imagen.getWidth(this)/2);
                //Dibujamos la imagen con las dimensiones apropiadas en el escritorio usando el metodo drawImage
                g.drawImage(imagen, (int)getWidth()/2 - anchura, (int)getHeight()/2 - altura, this);

                g2d.setFont(new Font("Lucida Calligraphy",Font.BOLD,30));
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                // g2d.drawString("J&M System Soft", this.getWidth()-350, this.getHeight()-50);
                g2d.setFont(new Font("Palatino Linotype",Font.ITALIC,20));
                g2d.setColor(Color.red);
                g2d.drawString("Desarrollando tecnología", this.getWidth()-265, this.getHeight()-25);
                String s= "J&M System Soft";
                int w=300;
                int h=20;
                FontRenderContext frc = g2d.getFontRenderContext();

                Font f = new Font("Lucida Calligraphy", Font.BOLD, 30);
                Font f1 = new Font("Lucida Calligraphy", Font.BOLD, 30);
                AttributedString as = new AttributedString(s);
                as.addAttribute(TextAttribute.FONT, f, 0, 10);
                as.addAttribute(TextAttribute.FONT, f1, 10, s.length());
                AttributedCharacterIterator aci = as.getIterator();
                TextLayout tl = new TextLayout(aci, frc);
                float sw = (float) tl.getBounds().getWidth();
                float sh = (float) tl.getBounds().getHeight();
                Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(this.getWidth()-350, this.getHeight()-50));
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                g2d.setColor(Color.WHITE);
                g2d.setStroke(new BasicStroke(2f));
                g2d.draw(sha);
                g2d.setColor(Color.blue);
                g2d.fill(sha);
            }};    ;
            jMenuBar1 = new javax.swing.JMenuBar();
            jMenu1 = new javax.swing.JMenu();
            jSeparator1 = new javax.swing.JPopupMenu.Separator();
            jMenu6 = new javax.swing.JMenu();
            jMenuItem23 = new javax.swing.JMenuItem();
            jMenuItem24 = new javax.swing.JMenuItem();
            jSeparator3 = new javax.swing.JPopupMenu.Separator();
            jMenu7 = new javax.swing.JMenu();
            jMenuItem25 = new javax.swing.JMenuItem();
            jMenuItem26 = new javax.swing.JMenuItem();
            jMenu8 = new javax.swing.JMenu();
            jMenuItem37 = new javax.swing.JMenuItem();
            jMenuItem38 = new javax.swing.JMenuItem();
            jSeparator2 = new javax.swing.JPopupMenu.Separator();
            jMenuItem58 = new javax.swing.JMenuItem();
            jMenuItem22 = new javax.swing.JMenuItem();
            jMenu2 = new javax.swing.JMenu();
            jSeparator5 = new javax.swing.JPopupMenu.Separator();
            jSeparator4 = new javax.swing.JPopupMenu.Separator();
            jMenuItem49 = new javax.swing.JMenuItem();
            jSeparator6 = new javax.swing.JPopupMenu.Separator();
            jMenuItem19 = new javax.swing.JMenuItem();
            jSeparator7 = new javax.swing.JPopupMenu.Separator();
            jMenuItem52 = new javax.swing.JMenuItem();
            jMenuItem27 = new javax.swing.JMenuItem();
            jMenuItem46 = new javax.swing.JMenuItem();
            jSeparator8 = new javax.swing.JPopupMenu.Separator();
            jMenuItem50 = new javax.swing.JMenuItem();
            jMenuItem51 = new javax.swing.JMenuItem();
            jSeparator9 = new javax.swing.JPopupMenu.Separator();
            jMenu3 = new javax.swing.JMenu();
            jMenuItem6 = new javax.swing.JMenuItem();
            jMenuItem7 = new javax.swing.JMenuItem();
            jMenuItem8 = new javax.swing.JMenuItem();
            jMenuItem48 = new javax.swing.JMenuItem();
            jMenuItem11 = new javax.swing.JMenuItem();
            jMenuItem12 = new javax.swing.JMenuItem();
            jMenuItem13 = new javax.swing.JMenuItem();
            jMenuItem14 = new javax.swing.JMenuItem();
            jMenuItem44 = new javax.swing.JMenuItem();
            jMenu10 = new javax.swing.JMenu();
            jMenuItem1 = new javax.swing.JMenuItem();
            jMenuItem3 = new javax.swing.JMenuItem();
            jMenuItem30 = new javax.swing.JMenuItem();
            jMenuItem33 = new javax.swing.JMenuItem();
            jMenuItem47 = new javax.swing.JMenuItem();
            jMenuItem2 = new javax.swing.JMenuItem();
            jMenuItem10 = new javax.swing.JMenuItem();
            jMenu4 = new javax.swing.JMenu();
            jMenuItem5 = new javax.swing.JMenuItem();
            jMenuItem31 = new javax.swing.JMenuItem();
            jMenuItem36 = new javax.swing.JMenuItem();
            jMenuItem9 = new javax.swing.JMenuItem();
            jMenuItem60 = new javax.swing.JMenuItem();
            jMenuItem61 = new javax.swing.JMenuItem();
            jMenuItem34 = new javax.swing.JMenuItem();
            jMenuItem18 = new javax.swing.JMenuItem();
            jMenuItem39 = new javax.swing.JMenuItem();
            jMenuItem20 = new javax.swing.JMenuItem();
            jMenuItem41 = new javax.swing.JMenuItem();
            jMenuItem55 = new javax.swing.JMenuItem();
            jMenu9 = new javax.swing.JMenu();
            jMenuItem35 = new javax.swing.JMenuItem();
            jMenuItem21 = new javax.swing.JMenuItem();
            jMenuItem32 = new javax.swing.JMenuItem();
            jMenuItem15 = new javax.swing.JMenuItem();
            jMenuItem42 = new javax.swing.JMenuItem();
            jMenuItem43 = new javax.swing.JMenuItem();
            jMenuItem45 = new javax.swing.JMenuItem();
            jMenuItem40 = new javax.swing.JMenuItem();
            jMenuItem56 = new javax.swing.JMenuItem();
            jMenuItem57 = new javax.swing.JMenuItem();
            jMenuItem53 = new javax.swing.JMenuItem();
            jMenu5 = new javax.swing.JMenu();
            jMenuItem16 = new javax.swing.JMenuItem();
            jMenuItem17 = new javax.swing.JMenuItem();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setBackground(new java.awt.Color(204, 255, 102));
            addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowActivated(java.awt.event.WindowEvent evt) {
                    formWindowActivated(evt);
                }
                public void windowOpened(java.awt.event.WindowEvent evt) {
                    formWindowOpened(evt);
                }
            });
            addComponentListener(new java.awt.event.ComponentAdapter() {
                public void componentShown(java.awt.event.ComponentEvent evt) {
                    formComponentShown(evt);
                }
            });

            jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));
            jDesktopPane1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jDesktopPane1MouseClicked(evt);
                }
            });
            jDesktopPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
                public void componentShown(java.awt.event.ComponentEvent evt) {
                    jDesktopPane1ComponentShown(evt);
                }
            });
            getContentPane().add(jDesktopPane1, java.awt.BorderLayout.CENTER);

            jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

            jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Key.png"))); // NOI18N
            jMenu1.setMnemonic('c');
            jMenu1.setText("Configuracion y Seguridad del Sistema");
            jMenu1.add(jSeparator1);

            jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/User.png"))); // NOI18N
            jMenu6.setText("Usuarios");

            jMenuItem23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_add.png"))); // NOI18N
            jMenuItem23.setText("Crear y actualizar usuarios");
            jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem23ActionPerformed(evt);
                }
            });
            jMenu6.add(jMenuItem23);

            jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/password_protect_directories.png"))); // NOI18N
            jMenuItem24.setText("Cambiar Password");
            jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem24ActionPerformed(evt);
                }
            });
            jMenu6.add(jMenuItem24);

            jMenu1.add(jMenu6);
            jMenu1.add(jSeparator3);

            jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Database.png"))); // NOI18N
            jMenu7.setText("Bases de datos");

            jMenuItem25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/backups.png"))); // NOI18N
            jMenuItem25.setText("Backup de la Base de datos");
            jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem25ActionPerformed(evt);
                }
            });
            jMenu7.add(jMenuItem25);

            jMenuItem26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/download_database.png"))); // NOI18N
            jMenuItem26.setText("Restaurar la Base de datos");
            jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem26ActionPerformed(evt);
                }
            });
            jMenu7.add(jMenuItem26);

            jMenu1.add(jMenu7);

            jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/toolbar.png"))); // NOI18N
            jMenu8.setText("Permisos");

            jMenuItem37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/toolbar_add.png"))); // NOI18N
            jMenuItem37.setText("Agregar Permisos");
            jMenuItem37.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem37ActionPerformed(evt);
                }
            });
            jMenu8.add(jMenuItem37);

            jMenuItem38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/toolbar_go.png"))); // NOI18N
            jMenuItem38.setText("Asignar Permisos");
            jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem38ActionPerformed(evt);
                }
            });
            jMenu8.add(jMenuItem38);

            jMenu1.add(jMenu8);
            jMenu1.add(jSeparator2);

            jMenuItem58.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
            jMenuItem58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Refresh-icon.png"))); // NOI18N
            jMenuItem58.setText("Reiniciar el Sistema");
            jMenuItem58.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem58ActionPerformed(evt);
                }
            });
            jMenu1.add(jMenuItem58);

            jMenuItem22.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir.png"))); // NOI18N
            jMenuItem22.setText("Salir del Sistema");
            jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem22ActionPerformed(evt);
                }
            });
            jMenu1.add(jMenuItem22);

            jMenuBar1.add(jMenu1);

            jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ventasps.png"))); // NOI18N
            jMenu2.setMnemonic('v');
            jMenu2.setText("Ventas");
            jMenu2.add(jSeparator5);
            jMenu2.add(jSeparator4);

            jMenuItem49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/document_editing.png"))); // NOI18N
            jMenuItem49.setText("Ver Comprobantes y Servicio");
            jMenuItem49.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem49ActionPerformed(evt);
                }
            });
            jMenu2.add(jMenuItem49);
            jMenu2.add(jSeparator6);

            jMenuItem19.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/font_red_delete.png"))); // NOI18N
            jMenuItem19.setText("Anular comprobante de venta");
            jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem19ActionPerformed(evt);
                }
            });
            jMenu2.add(jMenuItem19);
            jMenu2.add(jSeparator7);

            jMenuItem52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/page_white_edit.png"))); // NOI18N
            jMenuItem52.setText("Facturar Productos Ensamblados");
            jMenuItem52.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem52ActionPerformed(evt);
                }
            });
            jMenu2.add(jMenuItem52);

            jMenuItem27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/coins_add.png"))); // NOI18N
            jMenuItem27.setText("Registrar pagos por deudas");
            jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem27ActionPerformed(evt);
                }
            });
            jMenu2.add(jMenuItem27);

            jMenuItem46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/generate_ssl_certificate.png"))); // NOI18N
            jMenuItem46.setText("Ver Garantia");
            jMenuItem46.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem46ActionPerformed(evt);
                }
            });
            jMenu2.add(jMenuItem46);
            jMenu2.add(jSeparator8);

            jMenuItem50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/open_folder.png"))); // NOI18N
            jMenuItem50.setText("Particion de Producto");
            jMenuItem50.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem50ActionPerformed(evt);
                }
            });
            jMenu2.add(jMenuItem50);

            jMenuItem51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/application_view_gallery.png"))); // NOI18N
            jMenuItem51.setText("Productos para Ensamblaje");
            jMenuItem51.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem51ActionPerformed(evt);
                }
            });
            jMenu2.add(jMenuItem51);
            jMenu2.add(jSeparator9);

            jMenuBar1.add(jMenu2);

            jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/vendermenu.png"))); // NOI18N
            jMenu3.setMnemonic('o');
            jMenu3.setText("Registros");

            jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/premium_support.png"))); // NOI18N
            jMenuItem6.setText("Alumnos");
            jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem6ActionPerformed(evt);
                }
            });
            jMenu3.add(jMenuItem6);

            jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bank.png"))); // NOI18N
            jMenuItem7.setText("Docentes");
            jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem7ActionPerformed(evt);
                }
            });
            jMenu3.add(jMenuItem7);

            jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/client_account_template.png"))); // NOI18N
            jMenuItem8.setText("Administrativos");
            jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem8ActionPerformed(evt);
                }
            });
            jMenu3.add(jMenuItem8);

            jMenuItem48.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/toolbox.png"))); // NOI18N
            jMenuItem48.setText("Registro de Casos Especiales");
            jMenuItem48.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem48ActionPerformed(evt);
                }
            });
            jMenu3.add(jMenuItem48);

            jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/coins_delete.png"))); // NOI18N
            jMenuItem11.setText("Graba Huella Digital");
            jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem11ActionPerformed(evt);
                }
            });
            jMenu3.add(jMenuItem11);

            jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/parentesco.png"))); // NOI18N
            jMenuItem12.setText("Registrar Pagos");
            jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem12ActionPerformed(evt);
                }
            });
            jMenu3.add(jMenuItem12);

            jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/coins_in_hand.png"))); // NOI18N
            jMenuItem13.setText("Ver Pagos Realizados");
            jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem13ActionPerformed(evt);
                }
            });
            jMenu3.add(jMenuItem13);

            jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/money_add.png"))); // NOI18N
            jMenuItem14.setText("Registro de Asistencia");
            jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem14ActionPerformed(evt);
                }
            });
            jMenu3.add(jMenuItem14);

            jMenuItem44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/document_copies.png"))); // NOI18N
            jMenuItem44.setText("Registro Asistencia Manual");
            jMenuItem44.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem44ActionPerformed(evt);
                }
            });
            jMenu3.add(jMenuItem44);

            jMenuBar1.add(jMenu3);

            jMenu10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/anular.png"))); // NOI18N
            jMenu10.setText("Tablas Base");

            jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/category.png"))); // NOI18N
            jMenuItem1.setText("Tipo Comensal");
            jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem1ActionPerformed(evt);
                }
            });
            jMenu10.add(jMenuItem1);

            jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/odata.png"))); // NOI18N
            jMenuItem3.setText("Tipo Comprobante");
            jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem3ActionPerformed(evt);
                }
            });
            jMenu10.add(jMenuItem3);

            jMenuItem30.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/images.png"))); // NOI18N
            jMenuItem30.setText("Semestre Academico");
            jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem30ActionPerformed(evt);
                }
            });
            jMenu10.add(jMenuItem30);

            jMenuItem33.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disable_enable_demo_mode.png"))); // NOI18N
            jMenuItem33.setText("Facultad");
            jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem33ActionPerformed(evt);
                }
            });
            jMenu10.add(jMenuItem33);

            jMenuItem47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/coins_in_hand.png"))); // NOI18N
            jMenuItem47.setText("Tarifas");
            jMenuItem47.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem47ActionPerformed(evt);
                }
            });
            jMenu10.add(jMenuItem47);

            jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/list_box.png"))); // NOI18N
            jMenuItem2.setText("Horarios");
            jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem2ActionPerformed(evt);
                }
            });
            jMenu10.add(jMenuItem2);

            jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/document_editing.png"))); // NOI18N
            jMenuItem10.setText("Casos Especiales");
            jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem10ActionPerformed(evt);
                }
            });
            jMenu10.add(jMenuItem10);

            jMenuBar1.add(jMenu10);

            jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Box.png"))); // NOI18N
            jMenu4.setMnemonic('a');
            jMenu4.setText("Almacen");

            jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cart_add.png"))); // NOI18N
            jMenuItem5.setText("Ingreso de productos");
            jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem5ActionPerformed(evt);
                }
            });
            jMenu4.add(jMenuItem5);

            jMenuItem31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/document_editing.png"))); // NOI18N
            jMenuItem31.setText("Productos");
            jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem31ActionPerformed(evt);
                }
            });
            jMenu4.add(jMenuItem31);

            jMenuItem36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/word_count.png"))); // NOI18N
            jMenuItem36.setText("Agregar Especificaciones");
            jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem36ActionPerformed(evt);
                }
            });
            jMenu4.add(jMenuItem36);

            jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/basket_full.png"))); // NOI18N
            jMenuItem9.setText("Stock Productos en Almacen");
            jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem9ActionPerformed(evt);
                }
            });
            jMenu4.add(jMenuItem9);

            jMenuItem60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cart_full.png"))); // NOI18N
            jMenuItem60.setText("Cambio de Sede de Producto");
            jMenuItem60.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem60ActionPerformed(evt);
                }
            });
            jMenu4.add(jMenuItem60);

            jMenuItem61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/note_go.png"))); // NOI18N
            jMenuItem61.setText("Realizar Ventas Anteriores");
            jMenuItem61.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem61ActionPerformed(evt);
                }
            });
            jMenu4.add(jMenuItem61);

            jMenuItem34.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/receipt_share.png"))); // NOI18N
            jMenuItem34.setText("PrestarProductos");
            jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem34ActionPerformed(evt);
                }
            });
            jMenu4.add(jMenuItem34);

            jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/report_go.png"))); // NOI18N
            jMenuItem18.setText("Registar Devolucion");
            jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem18ActionPerformed(evt);
                }
            });
            jMenu4.add(jMenuItem18);

            jMenuItem39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/basket_put.png"))); // NOI18N
            jMenuItem39.setText("Ingresar Productos Prestados");
            jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem39ActionPerformed(evt);
                }
            });
            jMenu4.add(jMenuItem39);

            jMenuItem20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/basket.png"))); // NOI18N
            jMenuItem20.setText("Ingresar Productos sin Documento");
            jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem20ActionPerformed(evt);
                }
            });
            jMenu4.add(jMenuItem20);

            jMenuItem41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/basket_remove.png"))); // NOI18N
            jMenuItem41.setText("Devolver Prestamo");
            jMenuItem41.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem41ActionPerformed(evt);
                }
            });
            jMenu4.add(jMenuItem41);

            jMenuItem55.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/basket_remove.png"))); // NOI18N
            jMenuItem55.setText("Distribuir productos");
            jMenuItem55.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem55ActionPerformed(evt);
                }
            });
            jMenu4.add(jMenuItem55);

            jMenuBar1.add(jMenu4);

            jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Diagram.png"))); // NOI18N
            jMenu9.setMnemonic('r');
            jMenu9.setText("Reportes");

            jMenuItem35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/acroread.png"))); // NOI18N
            jMenuItem35.setText("Ver Inscritos del Semestre");
            jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem35ActionPerformed(evt);
                }
            });
            jMenu9.add(jMenuItem35);

            jMenuItem21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/acroread.png"))); // NOI18N
            jMenuItem21.setText("Ver Comensales");
            jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem21ActionPerformed(evt);
                }
            });
            jMenu9.add(jMenuItem21);

            jMenuItem32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/acroread.png"))); // NOI18N
            jMenuItem32.setText("Ver Pagos");
            jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem32ActionPerformed(evt);
                }
            });
            jMenu9.add(jMenuItem32);

            jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/acroread.png"))); // NOI18N
            jMenuItem15.setText("Ver Asistencias");
            jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem15ActionPerformed(evt);
                }
            });
            jMenu9.add(jMenuItem15);

            jMenuItem42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/acroread.png"))); // NOI18N
            jMenuItem42.setText("Ver Balance General");
            jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem42ActionPerformed(evt);
                }
            });
            jMenu9.add(jMenuItem42);

            jMenuItem43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/acroread.png"))); // NOI18N
            jMenuItem43.setText("Ver InAsistencias");
            jMenuItem43.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem43ActionPerformed(evt);
                }
            });
            jMenu9.add(jMenuItem43);

            jMenuItem45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/acroread.png"))); // NOI18N
            jMenuItem45.setText("Ver Licencias");
            jMenuItem45.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem45ActionPerformed(evt);
                }
            });
            jMenu9.add(jMenuItem45);

            jMenuItem40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/acroread.png"))); // NOI18N
            jMenuItem40.setText("Ver Estadisticas");
            jMenuItem40.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem40ActionPerformed(evt);
                }
            });
            jMenu9.add(jMenuItem40);

            jMenuItem56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/acroread.png"))); // NOI18N
            jMenuItem56.setText("Ver Cronograma");
            jMenuItem56.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem56ActionPerformed(evt);
                }
            });
            jMenu9.add(jMenuItem56);

            jMenuItem57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/acroread.png"))); // NOI18N
            jMenuItem57.setText("InventarioInicial");
            jMenuItem57.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem57ActionPerformed(evt);
                }
            });
            jMenu9.add(jMenuItem57);

            jMenuItem53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/acroread.png"))); // NOI18N
            jMenuItem53.setText("Cotizaciones");
            jMenuItem53.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem53ActionPerformed(evt);
                }
            });
            jMenu9.add(jMenuItem53);

            jMenuBar1.add(jMenu9);

            jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Info.png"))); // NOI18N
            jMenu5.setMnemonic('y');
            jMenu5.setText("Ayuda");

            jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem16.setText("Acerca de... ");
            jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem16ActionPerformed(evt);
                }
            });
            jMenu5.add(jMenuItem16);

            jMenuItem17.setText("Manual de Usuario");
            jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem17ActionPerformed(evt);
                }
            });
            jMenu5.add(jMenuItem17);

            jMenuBar1.add(jMenu5);

            setJMenuBar(jMenuBar1);

            pack();
        }// </editor-fold>//GEN-END:initComponents
private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        if (verificarpermiso(jMenuItem7.getText())) {
            CargarFormulario(12);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
}//GEN-LAST:event_jMenuItem7ActionPerformed
private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        R = jDesktopPane1.getBounds();
}//GEN-LAST:event_formComponentShown
private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if (verificarpermiso(jMenuItem3.getText())) {
            CargarFormulario(8);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

}//GEN-LAST:event_jMenuItem3ActionPerformed
private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (verificarpermiso(jMenuItem1.getText())) {
            CargarFormulario(7);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
}//GEN-LAST:event_jMenuItem1ActionPerformed
private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
//CargarFormulario(5);
        if (verificarpermiso(jMenuItem6.getText())) {
            CargarFormulario(11);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

}//GEN-LAST:event_jMenuItem6ActionPerformed
private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        //CargarFormulario(6);
        if (verificarpermiso(jMenuItem8.getText())) {
            CargarFormulario(13);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

}//GEN-LAST:event_jMenuItem8ActionPerformed
private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (verificarpermiso(jMenuItem2.getText())) {
            CargarFormulario(17);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

}//GEN-LAST:event_jMenuItem2ActionPerformed
private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
// CargarFormulario(8);
        if (verificarpermiso(jMenuItem13.getText())) {
            CargarFormulario(15);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
}//GEN-LAST:event_jMenuItem13ActionPerformed
private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
// CargarFormulario(9);
        if (verificarpermiso(jMenuItem12.getText())) {
            CargarFormulario(14);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
}//GEN-LAST:event_jMenuItem12ActionPerformed
private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        //CargarFormulario(13);
        if (verificarpermiso(jMenuItem14.getText())) {
            Asistencia asis= new Asistencia();
            asis.setLocationRelativeTo(this);
            asis.setVisible(true);
            asis.toFront();
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
}//GEN-LAST:event_jMenuItem14ActionPerformed
private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
// CargarFormulario(14);
        if (verificarpermiso(jMenuItem30.getText())) {
            CargarFormulario(9);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

}//GEN-LAST:event_jMenuItem30ActionPerformed
private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if (verificarpermiso(jMenuItem5.getText())) {
            CargarFormulario(15);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
}//GEN-LAST:event_jMenuItem5ActionPerformed
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        if (verificarpermiso(jMenuItem9.getText())) {
            CargarFormulario(16);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed
    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        if (verificarpermiso(jMenuItem31.getText())) {
            CargarFormulario(18);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem31ActionPerformed
    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        if (verificarpermiso(jMenuItem19.getText())) {
            CargarFormulario(19);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem19ActionPerformed
    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        if (verificarpermiso(jMenuItem33.getText())) {
            CargarFormulario(6);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem33ActionPerformed
    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
        if (verificarpermiso(jMenuItem34.getText())) {
            CargarFormulario(22);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

    }//GEN-LAST:event_jMenuItem34ActionPerformed
    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed

        if (verificarpermiso(jMenuItem36.getText())) {
            CargarFormulario(24);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

    }//GEN-LAST:event_jMenuItem36ActionPerformed
    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        if (verificarpermiso(jMenuItem27.getText())) {
            CargarFormulario(23);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem27ActionPerformed
    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        if (verificarpermiso(jMenuItem11.getText())) {
            Caputarar cap = new Caputarar();
            
            cap.setVisible(true);
            cap.toFront();
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

    }//GEN-LAST:event_jMenuItem11ActionPerformed
    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        if (verificarpermiso(jMenuItem18.getText())) {
            CargarFormulario(31);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

    }//GEN-LAST:event_jMenuItem18ActionPerformed
    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        if (verificarpermiso(jMenuItem21.getText())) {
            CargarFormulario(18);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

    }//GEN-LAST:event_jMenuItem21ActionPerformed
    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
        if (verificarpermiso(jMenuItem32.getText())) {
            CargarFormulario(19);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

    }//GEN-LAST:event_jMenuItem32ActionPerformed
    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
        if (verificarpermiso(jMenuItem35.getText())) {
            CargarFormulario(22);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

    }//GEN-LAST:event_jMenuItem35ActionPerformed
    private void jMenuItem39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
        if (verificarpermiso(jMenuItem39.getText())) {
            CargarFormulario(35);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem39ActionPerformed
    private void jMenuItem40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem40ActionPerformed
        //       CargarFormulario(36);
        if (verificarpermiso(jMenuItem40.getText())) {
            CargarFormulario(25);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

    }//GEN-LAST:event_jMenuItem40ActionPerformed
    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        if (verificarpermiso(jMenuItem20.getText())) {
            CargarFormulario(37);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

    }//GEN-LAST:event_jMenuItem20ActionPerformed
    private void jMenuItem41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem41ActionPerformed
        if (verificarpermiso(jMenuItem41.getText())) {
            CargarFormulario(38);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

    }//GEN-LAST:event_jMenuItem41ActionPerformed
    private void jMenuItem42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem42ActionPerformed
        if (verificarpermiso(jMenuItem42.getText())) {
            CargarFormulario(21);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

    }//GEN-LAST:event_jMenuItem42ActionPerformed
    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        if (verificarpermiso(jMenuItem15.getText())) {
            CargarFormulario(20);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

    }//GEN-LAST:event_jMenuItem15ActionPerformed
    private void jMenuItem43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem43ActionPerformed
        if (verificarpermiso(jMenuItem43.getText())) {
            CargarFormulario(24);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem43ActionPerformed
    private void jMenuItem44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem44ActionPerformed
        if (verificarpermiso(jMenuItem44.getText())) {
            CargarFormulario(16);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem44ActionPerformed
    private void jMenuItem45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem45ActionPerformed
        if (verificarpermiso(jMenuItem45.getText())) {
            CargarFormulario(23);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

    }//GEN-LAST:event_jMenuItem45ActionPerformed
    private void jMenuItem46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem46ActionPerformed
        if (verificarpermiso(jMenuItem46.getText())) {
            CargarFormulario(44);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem46ActionPerformed
    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        CargarFormulario(45);
    }//GEN-LAST:event_jMenuItem16ActionPerformed
    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        if (verificarpermiso(jMenuItem10.getText())) {
            CargarFormulario(27);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed
    private void jMenuItem47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem47ActionPerformed
        if (verificarpermiso(jMenuItem47.getText())) {
            CargarFormulario(10);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem47ActionPerformed

    private void jMenuItem48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem48ActionPerformed
        if (verificarpermiso(jMenuItem48.getText())) {
            CargarFormulario(28);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem48ActionPerformed

    private void jMenuItem49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem49ActionPerformed
        if (verificarpermiso(jMenuItem49.getText())) {
            CargarFormulario(49);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem49ActionPerformed

    private void jMenuItem50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem50ActionPerformed
        if (verificarpermiso(jMenuItem50.getText())) {
            CargarFormulario(50);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem50ActionPerformed

    private void jMenuItem51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem51ActionPerformed
        if (verificarpermiso(jMenuItem51.getText())) {
            CargarFormulario(51);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem51ActionPerformed

    private void jMenuItem52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem52ActionPerformed
        if (verificarpermiso(jMenuItem52.getText())) {
            CargarFormulario(52);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }

    }//GEN-LAST:event_jMenuItem52ActionPerformed

    private void jMenuItem53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem53ActionPerformed

        CargarFormulario(53);
    }//GEN-LAST:event_jMenuItem53ActionPerformed
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated
    private void jDesktopPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDesktopPane1MouseClicked
        if(evt.getClickCount()==2){
            if(InfoGeneral.usuario.equals("admin")){
                if (verificarpermiso("Stock Minimo")) {
                    CargarFormulario(54);
                }
            }
        }
             
    }//GEN-LAST:event_jDesktopPane1MouseClicked
    private void jMenuItem55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem55ActionPerformed
        CargarFormulario(56);
    }//GEN-LAST:event_jMenuItem55ActionPerformed
    private void jMenuItem56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem56ActionPerformed
if (verificarpermiso(jMenuItem56.getText())) {
            CargarFormulario(26);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem56ActionPerformed
    private void jMenuItem57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem57ActionPerformed
        CargarFormulario(58);
    }//GEN-LAST:event_jMenuItem57ActionPerformed
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened
    private void jDesktopPane1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDesktopPane1ComponentShown

    }//GEN-LAST:event_jDesktopPane1ComponentShown

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        
        
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem60ActionPerformed
        if (verificarpermiso(jMenuItem60.getText())) {
            CargarFormulario(59);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
        
    }//GEN-LAST:event_jMenuItem60ActionPerformed

    private void jMenuItem61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem61ActionPerformed
        if (verificarpermiso(jMenuItem61.getText())) {
            CargarFormulario(60);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem61ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Desea Salir del Sistema!!", "", JOptionPane.YES_NO_OPTION) == 0) {
            dispose();
        }
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem58ActionPerformed
        ReiniciarElSistema();
    }//GEN-LAST:event_jMenuItem58ActionPerformed

    private void jMenuItem38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
        if (verificarpermiso(jMenuItem38.getText())) {
            CargarFormulario(4);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem38ActionPerformed

    private void jMenuItem37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem37ActionPerformed
        if (verificarpermiso(jMenuItem37.getText())) {
            CargarFormulario(3);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem37ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
            RestoreDialog restoreDialog = new RestoreDialog(this, true);
            restoreDialog.setLocationRelativeTo(this);
           restoreDialog.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        BackupDialog backupDialog = new BackupDialog(this, true);
        backupDialog.setLocationRelativeTo(this);
        backupDialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        if (verificarpermiso(jMenuItem24.getText())) {
            CargarFormulario(2);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        if (verificarpermiso(jMenuItem23.getText())) {
            CargarFormulario(1);
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Permiso para este Menu!!");
        }
    }//GEN-LAST:event_jMenuItem23ActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                new Menu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem41;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem43;
    private javax.swing.JMenuItem jMenuItem44;
    private javax.swing.JMenuItem jMenuItem45;
    private javax.swing.JMenuItem jMenuItem46;
    private javax.swing.JMenuItem jMenuItem47;
    private javax.swing.JMenuItem jMenuItem48;
    private javax.swing.JMenuItem jMenuItem49;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem50;
    private javax.swing.JMenuItem jMenuItem51;
    private javax.swing.JMenuItem jMenuItem52;
    private javax.swing.JMenuItem jMenuItem53;
    private javax.swing.JMenuItem jMenuItem55;
    private javax.swing.JMenuItem jMenuItem56;
    private javax.swing.JMenuItem jMenuItem57;
    private javax.swing.JMenuItem jMenuItem58;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem60;
    private javax.swing.JMenuItem jMenuItem61;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    // End of variables declaration//GEN-END:variables

    private void Actulizar_FechasAntgeriores() {
        control.Sql="update cronogramaconsumo set estadocrono='1' where estadocrono=0 and fechaconsumo<curdate()";
        control.EditarRegistro(control.Sql);
    }

}
