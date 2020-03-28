package Clases;

/**
 * *
 * @author Miguel Silva Zapata
 */
import com.toedter.calendar.JDateChooser;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.view.JasperViewer;

public class Controlador {

    /**
     * ************************ATRIBUTOS****************************
     */
//    public static ConexionBD Base = new ConexionBD();
    //public static ConexionBD1 Base = new ConexionBD1();
    public static ConexionBD1 Base = ConexionBD1.getInstance();
    //public ConexAlternativa Base1=new ConexAlternativa();
    public Visualizar Vsz = new Visualizar();
    public String Sql = "";
    public int fila;
    public boolean bandera = false;
    public boolean InventarioInicial = false;
    static public String Artificio = "";
    static public String sede;
    public IMPRIMIR impresor = new IMPRIMIR();
    public Numero_a_Letra numlt = new Numero_a_Letra();
    //public Codificador Codigo = new Codificador();
    public String Data[] = new String[10];
    public int Veces = 0;
    public JFileChooser explorador = new JFileChooser();
    private int BUFFER = 10485760;
    private StringBuffer temp = null;
    private FileWriter fichero = null;
    private PrintWriter pw = null;
    private final DecimalFormat decimalFormat = new DecimalFormat("###,##0.00", DecimalFormatSymbols.getInstance(new Locale("en", "US")));
    public static String ICON_PATH = "/Imagenes/icon.png";

    public String QuitarCaracter(String Cadena) {
        String res = "";
        boolean b = false;
        Cadena = Cadena.replace("'", ";");
        return Cadena;
    }

    public void forma_table_ver(Mimodelo md, JTable tb){
        FormatoTabla ft=new FormatoTabla(1);
        tb.setDefaultRenderer(Object.class, ft);
        tb.getColumnModel().getColumn(0).setMinWidth(0);
        tb.getColumnModel().getColumn(0).setMaxWidth(0);
        tb.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    public boolean Verificarconsulta(String sql, String usbd, String pbd) {
        boolean bd = false;
        try {
            //Base=new ConexionBD(usbd, pbd);
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(sql);
            if (Base.rt.next()) {
                bd = true;
            }
        } catch (Exception e) {
        }
        return bd;
    }

    public String GeneraNumero(int num) {
        String rs = "";
        if ((num > 0) && (num < 10)) {
            rs = "000" + num;
        }
        if ((num >= 10) && (num < 100)) {
            rs = "00" + num;
        }
        return rs;
    }

    public String VolverFecha(String fce) {
        String f = "";
        f = fce.substring(8, 10) + "/" + fce.substring(5, 7) + "/" + fce.substring(0, 4);
        return f;
    }

    public void PonerFechaenDateChooser(JDateChooser fecha, String fec) {
        ((JTextComponent) fecha.getDateEditor().getUiComponent()).setText(fec);
    }

    public boolean BuscarDatoenJtable(Mimodelo md, String dto, int col) {
        bandera = false;
        fila = 0;
        if (dto.trim().length() == 0) {
        } else {
            if (md.getRowCount() > 0) {
                while (fila < md.getRowCount()) {
                    if (md.getValueAt(fila, col).toString().equalsIgnoreCase(dto)) {
                        bandera = true;
                        fila = md.getRowCount();
                    }
                    fila++;
                }
            }
        }
        return bandera;
    }

    public String ObtenerFechaGarantia(String tp, String vlr) {
        String res = "";
        try {
            Sql = "select DevolverFechadeGarantia('" + tp + "','" + vlr + "')";
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(Sql);
            if (Base.rt.next()) {
                res = Base.rt.getString(1);
            }
        } catch (Exception e) {
        }
        return res;
    }

    public void Longitudtx(JTextField p, KeyEvent e, int lgt) {
        if (p.getText().length() == lgt) {
            e.consume();
        }
    }

    public void VolverDeCboaCbo(JComboBox cbo) {
        if (cbo.getSelectedIndex() == -1) {
            cbo.grabFocus();
        }
    }

    public String PriLtrasMayuscula(String nombre) {
        String mayuscula = nombre.charAt(0) + "";
        mayuscula = mayuscula.toUpperCase();
        nombre = nombre.replaceFirst(nombre.charAt(0) + "", mayuscula);
        for (int k = 1; k < nombre.length(); k++) {
            if (nombre.charAt(k) == ' ') {
                mayuscula = nombre.charAt(k + 1) + "";
                mayuscula = mayuscula.toUpperCase();
                nombre = nombre.replaceFirst(nombre.charAt(k + 1) + "", mayuscula);
                //nombre.replace(nombre.charAt(k+1),mayuscula.charAt(k+1));
            }
        }
        return nombre;
    }

    public void AccesoSistemaus(String usrA, String pwA,
            JFrame vtac, JFrame vtpos, int inten, String usrBD, String pwBD) {
        Sql = "select * from usuario where nomusr='" + usrA + "'"
                + " and psw='" + pwA + "'";
        if (Verificarconsulta(Sql, usrBD, pwBD)) {
            vtac.dispose();
            vtpos.setVisible(true);
        } else {
            Veces++;
            if (Veces == 1) {
                JOptionPane.showMessageDialog(null, "Llevas un intento");
            } else {
                if (Veces == inten) {
                    JOptionPane.showMessageDialog(null, "Cumplistes tus " + inten + " Intentos");
                    vtac.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Llevas " + Veces + " Intentos");
                }
            }
        }
    }

    public void AccesoSistema(String usr, String pw,
            JFrame vtac, JFrame vtpos, int inten, String tipous) {
        Sql = "select * from usuario where nomusr='" + usr + "'"
                + " and psw='" + pw + "' and idtipousuario='" + tipous + "';";
        if (Verificarconsulta(Sql)) {
            vtac.dispose();
            vtpos.setVisible(true);
        } else {
            Veces++;
            if (Veces == 1) {
                JOptionPane.showMessageDialog(null, "Llevas un intento");
            } else {
                if (Veces == inten) {
                    JOptionPane.showMessageDialog(null, "Cumplistes tus " + inten + " Intentos");
                    vtac.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Llevas " + Veces + " Intentos");
                }
            }
        }
    }

    public boolean Malicioso(String dtvld) {
        boolean b = false;
        int p = 0;
        while (p < dtvld.length()) {
            if (dtvld.codePointAt(p) == 39) {
                b = 4 > 2;
                p = dtvld.length();
            }
            p++;
        }
        return b;
    }

    public void LlenarCombo(JComboBox cbo, String Consulta, int pos) {
        cbo.removeAllItems();
        try {
            Base.rt = DevolverRegistro(Consulta);
            while (Base.rt.next()) {
                cbo.addItem(Base.rt.getString(pos));
            }
            cbo.setSelectedIndex(-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void VolverdeTxatx(JTextField tx1) {
        if (tx1.getText().length() == 0) {
            tx1.requestFocus();
        }
    }

    public void EliminarBD(String nmbd) {
        try {
            Sql = "drop database " + nmbd;
            Base.st = Base.conec.createStatement();
            Base.st.executeUpdate(Sql);
        } catch (Exception e) {
        }
    }

    public boolean CrearBackup(String host, String port, String user, String password, String db, String file_backup) {
        boolean ok = false;
        try {
            //sentencia para crear el BackUp
            Process run = Runtime.getRuntime().exec(
                    "C:\\xampp\\mysql\\bin\\mysqldump --host=" + host + " --port=" + port
                    + " --user=" + user + " --password=" + password
                    + " --compact --complete-insert --extended-insert --skip-quote-names"
                    + " --skip-comments --skip-triggers " + db);
            //se guarda en memoria el backup
            InputStream in = run.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            temp = new StringBuffer();
            int count;
            char[] cbuf = new char[BUFFER];
            while ((count = br.read(cbuf, 0, BUFFER)) != -1) {
                temp.append(cbuf, 0, count);
            }
            br.close();
            in.close();
            /* se crea y escribe el archivo SQL */
            fichero = new FileWriter(file_backup);
            pw = new PrintWriter(fichero);
            pw.println(temp.toString());
            ok = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return ok;
    }

    public boolean verifnum(String cd) {
        boolean b = true;
        int p = 0;
        while (p < 2) {
            if ((cd.charAt(p) != '0') || (cd.charAt(p) != '1') || (cd.charAt(p) != '2') || (cd.charAt(p) != '3')
                    || (cd.charAt(p) != '4') || (cd.charAt(p) != '5') || (cd.charAt(p) != '6') || (cd.charAt(p) != '7')
                    || (cd.charAt(p) != '8') || (cd.charAt(p) != '9')) {
                b = false;
                p = 2;
            }
            p++;
        }
        return b;
    }

    public void LLamarPaginaWeb(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
        }
    }

    public String CompletarFecha(String fe) {
        String a = fe.substring(0, 4), d = "", m = "", rts = fe;
        if (fe.length() < 10) {
            if (verifnum(fe.substring(6, 7))) {
                if (!verifnum(fe.substring(6, 7))) {
                    m = "0" + fe.substring(9, 9);
                }
            } else {
                m = "0" + fe.substring(6, 6);
            }
        }
        return rts;
    }

    public boolean Verificandoconsulta(String sql) {
        boolean b = false;
        try {
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(sql);
            if (Base.rt.next()) {
                b = true;
            }
        } catch (Exception e) {
        }
        return b;
    }

    public void VerPrograma(String pgr) throws IOException {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(pgr);
        } catch (IOException ex) {
        }
    }

    public boolean Verificarconsulta(String sql) {
        boolean bd = false;
        try {
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(sql);
            if (Base.rt.next()) {
                bd = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bd;
    }

    public void CrearRegistro(String sq) {
        try {
            Base.st = Base.conec.createStatement();
            Base.st.executeUpdate(sq);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CrearRegistro1(String sq) {
        try {
//       Base1.st=Base1.conec.createStatement();Base1.st.executeUpdate(sq);
        } catch (Exception e) {
        }
    }

    public void CrearRegistroCNProcedureStore(String cdg, String facd) {
        try {
            Base.prest = Base.conec.prepareCall("{call CrearFacultad(?,?)}");
            Base.prest.setString(1, cdg);
            Base.prest.setString(2, facd);
            Base.prest.execute();
        } catch (Exception e) {
        }
    }

    public void EditarRegistro(String sql) {
        try {
            Base.st = Base.conec.createStatement();
            Base.st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void EliminarRegistroDependiante(String sql, String sql1, String msj) {
        try {
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(sql);
            if (Base.rt.next() == false) {
                Base.st = Base.conec.createStatement();
                Base.st.executeUpdate(sql1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String EliminarRegistro(String sql) {
        String cd = "Registro Eliminado";
        try {
            Base.st = Base.conec.createStatement();
            Base.st.executeUpdate(sql);
            Base.rt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cd;
    }

    public String DevolverRegistroDto(String sq, int pos) {
        String rs = "";
        try {
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(sq);
            if (Base.rt.next()) {
                rs = Base.rt.getString(pos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet DevolverRegistro(String sq) {
        try {
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(sq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base.rt;
    }

    public String Encriptar(String cdn1, int cnd) {
        String encr = "";
        int cr;
        cdn1 = Invertir(cdn1);
        for (int j = 0; j < cdn1.length(); j++) {
            cr = cdn1.codePointAt(j);
            if (Par(cdn1.length())) {
                cr = cr + cnd;
            } else {
                cr = cr - cnd;
            }
            encr = encr + (char) cr;
        }
        return encr;
    }

    public void MostrarEnCombo(String vl, JComboBox cbo) {
        int p = 0, ct = 0;
        while (ct < cbo.getItemCount()) {
            if (cbo.getModel().getElementAt(ct).toString().toUpperCase().compareTo(vl.toUpperCase()) == 0) {
                p = ct;
                ct = cbo.getItemCount();
            }
            ct++;
        }
        cbo.setSelectedIndex(p);
    }

    public static String Invertir(String cdn) {
        String rst = "";
        for (int t = cdn.length() - 1; t >= 0; t--) {
            rst = rst + cdn.charAt(t);
        }
        return rst;
    }

    public static boolean Par(int dto) {
        if (dto == 0) {
            return false;
        } else {
            if (dto % 2 == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public String Decencriptar(String cdn1, int cnd) {
        String encr = "";
        int cr;
        cdn1 = Invertir(cdn1);
        for (int j = 0; j < cdn1.length(); j++) {
            cr = cdn1.codePointAt(j);
            if (Par(cdn1.length())) {
                cr = cr - cnd;
            } else {
                cr = cr + cnd;
            }
            encr = encr + (char) cr;
        }
        return encr;
    }

    public void LlenarJTabla(Mimodelo mdl, String sq, int cdt) {
        LimTabla(mdl);
        try {
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(sq);
            while (Base.rt.next()) {
                for (Veces = 1; Veces <= cdt; Veces++) {
                    Data[Veces - 1] = Base.rt.getString(Veces);
                }
                mdl.addRow(Data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LlenarJTablaSE(Mimodelo mdl, String sq, int cdt) {
        LimTabla(mdl);
        try {
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(sq);
            while (Base.rt.next()) {
                for (Veces = 1; Veces <= cdt; Veces++) {
                    Data[Veces - 1] = Base.rt.getString(Veces);
                }
                mdl.addRow(Data);
            }
        } catch (Exception e) {
        }
    }

    public String CoverFecha(String fce) {
        String f = "";
        f = fce.substring(6, 10) + "/" + fce.substring(3, 5) + "/" + fce.substring(0, 2);
        return f;
    }

    public boolean Comprobarpermiso(String usu, String mnu) {
        String tipo = "";
        String mn = "";
        boolean b = false;
        mn = ObtenerDato("menu", "descr", mnu, 1);
        tipo = ObtenerDato("usuario", "nomusr", usu, 2);
        try {
            Sql = "select * from permiso where Menu_codmnu='" + mn
                    + "' and TipoUsuario_codtpusr='" + tipo + "'";
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(Sql);
            if (Base.rt.next()) {
                b = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public boolean Existecam(String cdg, String cmpcod, String tb, String cmp, String vlr) {
        boolean b = false;
        Sql = "select * from " + tb + " where " + cmp + "='" + vlr + "' and " + cmpcod + "<>'" + cdg + "'";
        try {
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(Sql);
            if (Base.rt.next()) {
                b = true;
            }
        } catch (Exception e) {
        }
        return b;
    }

    public boolean Existe(String tb, String cmp, String vlr) {
        boolean b = false;
        Sql = "select * from " + tb + " where " + cmp + "='" + vlr + "'";
        try {
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(Sql);
            if (Base.rt.next()) {
                b = true;
            }
        } catch (Exception e) {
        }
        return b;
    }

    public String Fecha() {
        String fec = "";
        try {
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery("select curdate();");
            if (Base.rt.next()) {
                fec = Base.rt.getString(1);
            }
        } catch (Exception e) {
        }
        return fec;
    }

    public String Hora() {
        String hra = "";
        try {
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery("select curtime()");
            if (Base.rt.next()) {
                hra = Base.rt.getString(1);
            }
        } catch (Exception e) {
        }
        return hra;
    }

    public void LimTabla(Mimodelo mdl) {
        while (mdl.getRowCount() > 0) {
            mdl.removeRow(0);
        }
    }

    public String ObtenerDato(String Tabla, String Cmp, String vl, int ps) {
        String rt = "", Sql = ""; //Base.Conectar();
        Sql = "Select * from " + Tabla + " where " + Cmp + "='" + vl + "'";
        try {
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(Sql);
            if (Base.rt.next()) {
                rt = Base.rt.getString(ps);
            }
        } catch (Exception E) {
        }
        return rt;
    }
//    public String ObtenerDato1(String Tabla, String Cmp, String vl, int ps) {
//        String rt = "", Sql = ""; //Base1.ConectarDinamyc("root","duque", rt, rt);
//        Base1.ConectarDinamyc("root","duque","localhost","mikito");
//        Sql = "Select * from " + Tabla + " where " + Cmp + "='" + vl + "'";
//        try {
//         Base1.st = Base1.conec.createStatement();Base1.rt = Base1.st.executeQuery(Sql);
//         if (Base1.rt.next()) {
//          rt = Base1.rt.getString(ps);
//         }
//        } catch (Exception E) {
//        }
//        return rt;
//    }

    public void SoloLetras(KeyEvent e) {
        if ((e.getKeyChar() >= 48) && (e.getKeyChar() <= 57)) {
            e.consume();
        }
    }

    public void Solonumeros(KeyEvent e) {
        if ( (e.getKeyChar() < 45) || (e.getKeyChar() > 57) || (e.getKeyChar() == 47)) {
            e.consume();
        }
    }

    public void AnularTecl(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_BACK_SPACE) || (e.getKeyChar() == KeyEvent.VK_DELETE)) {
            e.consume();
        }
    }

    public void Restaurar() {
        int valor;
        File carpeta = new File("D:/");
        explorador.setCurrentDirectory(carpeta);
        valor = explorador.showOpenDialog(null);
        if (valor == JFileChooser.APPROVE_OPTION) {
            try {
                String ubicacion = String.valueOf(explorador.getSelectedFile());

                Process proceso = Runtime.getRuntime().exec("cmd /c mysql --user=root --password=silva BDActas < " + ubicacion);

                JOptionPane.showMessageDialog(null, "La Base de Datos ha sido actualizada", "Verificar", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error no se actualizó la Base de Datos por el siguiente motivo: " + e.getMessage(), "Verificar", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "La actualización del Backup ha sido cancelada");
        }
    }

    public void decimal(KeyEvent e, String text) {
        int count = 0;
        if (text.length() == 0 && e.getKeyChar() == '.') {
            e.consume();
        } else {
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '.') {
                    count++;
                    break;
                }
            }
            if (((e.getKeyChar() < 48) || (e.getKeyChar() > 57))
                    && (e.getKeyChar() != 8 && e.getKeyCode() != 37 && e.getKeyCode() != 39
                    && e.getKeyCode() != 127 && e.getKeyChar() != '.')) {
                e.consume();
            } else {
                if ((count > 0 && e.getKeyChar() == '.')) {
                    e.consume();
                }
            }
        }
    }

    public String RecuperaSerie(String tipo) {
        String ser = "";
        int seriean = 0;
        try {
            //Base.Conectar();
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery("select nume from comprobantesvta where tipcompr='" + tipo + "' and esta= 'Activo' order by serie asc limit 1");
            while (Base.rt.next()) {
//               seriean=Base.rt.getInt(1);
                ser = Base.rt.getString(1);

            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return ser;
    }

    public String RecuperaNumeroSerie(String tip) {
        String ser = "";
        int seriean = 0;
        try {
            //Base.Conectar();
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery("select count(*) from comprobantesvta c where tipcompr = '" + tip + "' order by c.serie desc limit 1");
            //SELECT count(*), serie FROM comprobantesvta c where c.tipcompr = 'factura'  order by c.serie desc limit 1;
            while (Base.rt.next()) {
                // seriean=Base.rt.getInt(1);
                seriean = Base.rt.getInt(1);

            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        try {
            //Base.Conectar();
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery("select * from comprobantesvta c where tipcompr = '" + tip + "' order by c.serie desc limit 1");
            //SELECT count(*), serie FROM comprobantesvta c where c.tipcompr = 'factura'  order by c.serie desc limit 1;
            while (Base.rt.next()) {
                // seriean=Base.rt.getInt(1);
                ser = Base.rt.getString(4);

            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        if (seriean == 0) {
            ser = "1";
        } else {
            seriean = Integer.parseInt(ser);
            seriean++;
            ser = "" + seriean;
        }

        return ser;
    }

    public String CrearRegistroDev(String sq) {
        String cad = "";
        try {
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(sq);
            if (Base.rt.next()) {
                cad = Base.rt.getString(1);
            }
        } catch (Exception e) {
        }
        return cad;
    }

    public String EditarRegistroDev(String sql) {
        String cad = "";
        try {
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(sql);
            if (Base.rt.next()) {
                cad = Base.rt.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cad;
    }

    public void MarcarTexto(JTextField tf) {
        tf.setSelectionStart(0);
        tf.setSelectionEnd(tf.getText().length());
        tf.grabFocus();
    }

    public String Formato_Amd(java.util.Date v1) {
        java.util.Date fecha1 = v1;
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String fecha = df.format(fecha1);

        return fecha;
    }

    public void CrearUnaTablaTemporal(String nmtbl, String sqlcrear) {
        Sql = "DROP TABLE IF EXISTS " + nmtbl + ";";
        CrearRegistro(Sql);
        Sql = sqlcrear;
        CrearRegistro(Sql);
    }

    public void Mayusculas(JTextField tx) {
        tx.setText(tx.getText().toUpperCase());
    }

    public void Minusculas(JTextField tx) {
        tx.setText(tx.getText().toLowerCase());
    }

    public void passFocus(KeyEvent evt, JComponent component) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            component.requestFocus();
        }
    }

    public boolean isEnterKey(KeyEvent evt) {
        return evt.getKeyCode() == KeyEvent.VK_ENTER;
    }
    private static final String REPORTS_PATH = System.getProperty("user.dir") + "/src/Reportes/";

    public void showReportDialog(String title, String reportName, Map parameters) {
        try {
       
            ConexionBD1.Conectar();
           Base.Conectar();
            if (parameters == null) {
                parameters = new HashMap();
            }
            parameters.put("SUBREPORT_DIR", REPORTS_PATH);
            parameters.put("codv", System.getProperty("user.dir") + "/src/Reportes/");
            parameters.put(JRParameter.REPORT_LOCALE, new Locale("en", "US"));

            JasperPrint prt = JasperFillManager.fillReport(REPORTS_PATH + reportName + ".jasper", parameters, ConexionBD1.conec);
            int pagesCount = prt.getPages().size();
            if (pagesCount > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.getComponents();
                JsperView.setTitle(title);
                JsperView.setExtendedState(6);

                JsperView.setVisible(true);
                JsperView.toFront();
            } else {
                JOptionPane.showMessageDialog(null, "No hay resultados para mostrar", "Report", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void imprimirComprobanteVenta(String idVenta, String formato, Map map) {
        System.out.println(REPORTS_PATH + formato + ".jasper");
        String impresora = "";
//        String impresora = Configuracion.getInstance().getImpresoraTicket();

        if (impresora == null) {
            impresora = "";
        }
        map.put("SUBREPORT_DIR", REPORTS_PATH);

        Base.Conectar();

        JasperPrint prt;
        boolean showPrintDialog = impresora.length() == 0 || impresora.compareTo("Mostrar Ventana") == 0;
        if (showPrintDialog) {
            try {
                prt = JasperFillManager.fillReport(REPORTS_PATH + formato + ".jasper", map, Base.conec);

                JasperPrintManager.printReport(prt, showPrintDialog);

            } catch (JRException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
            PrintService printService = null;
            if (printServices.length > 0) {
                for (PrintService printService1 : printServices) {
                    if (printService1.getName().compareToIgnoreCase(impresora) == 0) {
                        printService = printService1;
                        break;
                    }
                }

                if (printService != null) {

                    try {
                        prt = JasperFillManager.fillReport(REPORTS_PATH + formato + ".jasper", map, Base.conec);
                        JRPrintServiceExporter jrprintServiceExporter = new JRPrintServiceExporter();
                        jrprintServiceExporter.setParameter(JRExporterParameter.JASPER_PRINT, prt);
                        jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService);
                        jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);

                        jrprintServiceExporter.exportReport();

                    } catch (Exception ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "La impresora predeterminada no está disponible en estos momentos", "Imprimir", JOptionPane.ERROR_MESSAGE);
                        showReportDialog("Comprobante", formato, map);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado la impresora predeterminada", "Imprimir", JOptionPane.ERROR_MESSAGE);
                    showReportDialog("Comprobante", formato, map);
                }
            }
        }
    }

    public String dateFormat(java.util.Date date) {
        java.util.Date fecha1 = date;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = df.format(fecha1);
        return fecha;
    }

    public String decimalFormat(Object object) {
        return this.decimalFormat.format(object);
    }

    public String[] getArrayData(String sql) {
        String[] data = null;
        try {
            Base.Conectar();
            this.Base.st = Base.conec.createStatement();
            this.Base.rt = this.Base.st.executeQuery(sql);
            int numeroColumnas = this.Base.rt.getMetaData().getColumnCount();
            if (this.Base.rt.next()) {
                data = new String[numeroColumnas];
                for (int i = 0; i < numeroColumnas; i++) {
                    data[i] = this.Base.rt.getString(i + 1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public double toDouble(String value, double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public double toDouble(String value) {
        return toDouble(value, 0);
    }

    public void hideTableColumn(JTable table, int... indexs) {
        for (int j : indexs) {

            table.getColumnModel().getColumn(j).setMinWidth(0);
            table.getColumnModel().getColumn(j).setPreferredWidth(0);
            table.getColumnModel().getColumn(j).setWidth(0);
            table.getColumnModel().getColumn(j).setMaxWidth(-1);
        }
    }

    public void setWidthTableColumn(JTable table, int width, int... indexs) {
        for (int j : indexs) {
            table.getColumnModel().getColumn(j).setWidth(width);
            table.getColumnModel().getColumn(j).setPreferredWidth(width);
        }
    }

    public void setMaxWidthColumnTable(JTable table, int width, int... indexs) {
        for (int j : indexs) {
            table.getColumnModel().getColumn(j).setMaxWidth(width);
            table.getColumnModel().getColumn(j).setWidth(width);
            table.getColumnModel().getColumn(j).setPreferredWidth(width);
        }
    }

    public boolean ejecutar(String sql) {
        boolean b = false;
        try {
            Base.st = Base.conec.createStatement();
            Base.st.executeUpdate(sql);
            b = true;
        } catch (Exception e) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        return b;
    }

    public String getValueQuery(String sql) {
        String res = "Ocurrió un error";
        try {
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(sql);

            if (Base.rt.next()) {
                res = Base.rt.getString(1);
            }

        } catch (Exception e) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            res = "Ocurrió un error";
            e.printStackTrace();
        }
        return res;
    }

    public void fillComboBox(String sql, JComboBox comboBox, Map map) {
        fillComboBox(sql, comboBox, map, "");
    }

    public void fillComboBox1(String sql, JComboBox comboBox, Map map, int ps) {
        fillComboBox1(sql, comboBox, map, "", ps);
    }

    public void fillComboBox1(String sql, JComboBox comboBox, Map map, String primerItem, int p) {
        int index = 0;
        try {
            comboBox.removeAllItems();
            map.clear();
            if (primerItem.length() > 0) {
                map.put(index, "null");
                comboBox.addItem(primerItem);
                index++;
            }
            Base.Conectar();
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(sql);
            while (Base.rt.next()) {
                map.put(index, Base.rt.getString(p));
                comboBox.addItem(Base.rt.getString(2));
                index++;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillComboBox(String sql, JComboBox comboBox, Map map, String primerItem) {
        int index = 0;
        try {
            comboBox.removeAllItems();
            map.clear();
            if (primerItem.length() > 0) {
                map.put(index, "null");
                comboBox.addItem(primerItem);
                index++;
            }
            Base.Conectar();
            Base.st = Base.conec.createStatement();
            Base.rt = Base.st.executeQuery(sql);
            while (Base.rt.next()) {
                map.put(index, Base.rt.getString(1));
                comboBox.addItem(Base.rt.getString(2));
                index++;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int executeAndGetId(String sql) {
        int id = -1;

        try {
            Base.st = Base.conec.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            Base.st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            Base.rt = Base.st.getGeneratedKeys();
            if (Base.rt.next()) {
                id = Base.rt.getInt(1);
            }

        } catch (Exception e) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
            id = -1;
            e.printStackTrace();
        }
        return id;
    }

    public static void main(String[] args) {
        char x = '1';
        System.out.println(x == 1);
        System.out.println(Integer.parseInt("" + x) == 1);
    }
     public String diasespañol(String dia){
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
/**
 * ************************IMPLEMENTACION DE METODOS************************
 */
