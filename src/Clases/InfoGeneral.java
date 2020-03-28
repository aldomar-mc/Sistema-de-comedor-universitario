/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

public class InfoGeneral {

    static public String usuario;
    static public String tipo;
    static public String pass;
    static public int control;
    static public int carac;
    static public int serie;
    static public String Sede;
    static public int compraeditar;
    static public int VerCualEs;
    static public int controlCliente;
    static public String comp;
    static public int gg;
    static public int controlEditarEnsamblaje;
    private static String idSede;
    static public String rucc;
    static public String Dniee;

    public static String getDniee() {
        return Dniee;
    }

    public static void setDniee(String Dniee) {
        InfoGeneral.Dniee = Dniee;
    }

    public static String getDn() {
        return dn;
    }

    public static void setDn(String dn) {
        InfoGeneral.dn = dn;
    }
    static public String dn;

    public static String getSede() {
        return Sede;
    }

    public static void setSede(String Sede) {
        InfoGeneral.Sede = Sede;
    }

    public static String getRucc() {
        return rucc;
    }

    public static void setRucc(String rucc) {
        InfoGeneral.rucc = rucc;
    }
    

    public static int getControlEditarEnsamblaje() {
        return controlEditarEnsamblaje;
    }

    public static void setControlEditarEnsamblaje(int controlEditarEnsamblaje) {
        InfoGeneral.controlEditarEnsamblaje = controlEditarEnsamblaje;
    }

    public static int getSerie() {
        return serie;
    }

    public static int getControlCliente() {
        return controlCliente;
    }

    public static void setControlCliente(int controlCliente) {
        InfoGeneral.controlCliente = controlCliente;
    }

    public static int getGg() {
        return gg;
    }

    public static void setGg(int gg) {
        InfoGeneral.gg = gg;
    }

    public static int getVerCualEs() {
        return VerCualEs;
    }

    public static void setVerCualEs(int VerCualEs) {
        InfoGeneral.VerCualEs = VerCualEs;
    }

    public static int getCompraeditar() {
        return compraeditar;
    }

    public static void setCompraeditar(int compraeditar) {
        InfoGeneral.compraeditar = compraeditar;
    }

    public static String getComp() {
        return comp;
    }

    public static void setComp(String comp) {
        InfoGeneral.comp = comp;
    }

    public static void setSerie(int serie) {
        InfoGeneral.serie = serie;
    }

    public InfoGeneral() {

    }

    public static int getControl() {
        return control;
    }

    public static int getCarac() {
        return carac;
    }

    public static void setCarac(int carac) {
        InfoGeneral.carac = carac;
    }

    public static void setControl(int control) {
        InfoGeneral.control = control;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.usuario = usuario;
    }

    public static String getIdSede() {
        return idSede;
    }

    public static void setIdSede(String idSede) {
        InfoGeneral.idSede = idSede;
    }

}
