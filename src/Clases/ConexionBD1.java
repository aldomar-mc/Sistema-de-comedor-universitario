package Clases;

import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.PropertyResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexionBD1 {

    public PreparedStatement prest = null;
    public CallableStatement cllst = null;
    public static Connection conec = null;
    public Connection conec1 = null;
    public Statement st = null, st1 = null;
    public ResultSet rt = null, rt1 = null;
    public static String user, password, host, db, usu, contraseña;
    private static ConexionBD1 instance = null;

    static {
        String properties = "Configurar.properties";
//        String properties = "D:\\Configuracion\\Configurar.properties";
        PropertyResourceBundle file;
        try {
            file = new PropertyResourceBundle(new FileInputStream(properties));
            user = file.getString("user");
            password = file.getString("password");
            host = file.getString("host");
            db = file.getString("database");
////            
////////            user = "root";
////////            password = "123";
////////            host = "localhost";
////////            db = "baseicecomputec4prueba";
////////            
        } catch (Exception e) {
        }
    }

    public ConexionBD1() {
        //     System.out.println("Holaaaa");
    }

    public static ConexionBD1 getInstance() {
        if (instance == null) {
            instance = new ConexionBD1();
            Conectar();
        }
        try {
            if (conec.isClosed()) {
                Conectar();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instance;
    }

    public ConexionBD1(String ubd, String pbd) {
        ConectarDinamyc(ubd, pbd, "localhost", "bdabarrotes");
    }

    public static void Conectar() {
        try {
            String url = "jdbc:mysql://" + host + "/" + db;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conec = DriverManager.getConnection(url, user, password);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al realizar la conexión al servidor de base de datos.\nVerifique los parámetros de conexión.\n\nDetalle de error:\n\n" + e + "", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public void ConectarDinamyc(String usbd, String clvbd, String hs, String bs) {
        try {
            user = usbd;
            password = clvbd;
            host = hs;
            db = bs;
            String url = "jdbc:mysql://" + host + "/" + db;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conec = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//***************FIN METODOS**************
}
