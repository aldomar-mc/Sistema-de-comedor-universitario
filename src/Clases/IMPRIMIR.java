package Clases;

/**
 * ** @author Usuario ********
 */
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.print.DocFlavor;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class IMPRIMIR {

    public String rta1 = "";
    public String rta = "";
    public int ctdpgns = 0;
    InfoGeneral info= new InfoGeneral();
            
//ConexionBD cconn= new ConexionBD();
//Controlador control= new Controlador();
ConexionBD1 cconn= new ConexionBD1();
private static final String REPORTS_PATH = System.getProperty("user.dir") + "/src/Reportes/";
    public void Impresion3Parametros(String para1, String vlrpar1, String para2, String vlrpar2,
            String para3, String vlrpar3, String NRpt) {  
//         public void ImpresionFacturaProductos(String para1, String vlrpar1, String para2, String vlrpar2,
//            String para3, String vlrpar3, String para4, String vlrpar4, String para5, String vlrpar5, String para6,
//            String vlrpar6, String para7, String vlrpar7, String para8, String vlrpar8, String para9, String vlrpar9,
//            String para10, String vlrpar10, String para11, String vlrpar11, String para12, String vlrpar12, String NRpt) {  
        try {
            Map parame = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + NRpt + ".jasper";
            parame.put(para1, vlrpar1);
            parame.put(para2, vlrpar2);
            parame.put(para3, vlrpar3);
            parame.put("SUBREPORT_DIR", REPORTS_PATH);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("lista de Comensales");
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos para mostrar", "Report", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
    public void Impresion2Parametros(String para1, String vlrpar1, String para2, String vlrpar2,String NRpt) {  
//         public void ImpresionFacturaProductos(String para1, String vlrpar1, String para2, String vlrpar2,
//            String para3, String vlrpar3, String para4, String vlrpar4, String para5, String vlrpar5, String para6,
//            String vlrpar6, String para7, String vlrpar7, String para8, String vlrpar8, String para9, String vlrpar9,
//            String para10, String vlrpar10, String para11, String vlrpar11, String para12, String vlrpar12, String NRpt) {  
        try {
            Map parame = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + NRpt + ".jasper";
            parame.put(para1, vlrpar1);
            parame.put(para2, vlrpar2);
            parame.put("SUBREPORT_DIR", REPORTS_PATH);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("lista de Comensales");
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos para mostrar", "Report", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

        public void Impresion4Parametros(String para1, String vlrpar1, String para2, String vlrpar2,
            String para3, String vlrpar3, String para4, String vlrpar4, String NRpt) {
        try {
            Map parame = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + NRpt + ".jasper";
            parame.put(para1, vlrpar1);
            parame.put(para2, vlrpar2);
            parame.put(para3, vlrpar3);
            parame.put(para4, vlrpar4);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Lista de Pagos");
                JsperView.setExtendedState(6);
                
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
        public void Impresion5Parametros(String para1, String vlrpar1, String para2, String vlrpar2,
            String para3, String vlrpar3, String para4, String vlrpar4, String para5, String vlrpar5, String NRpt) {
        try {
            Map parame = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + NRpt + ".jasper";
            parame.put(para1, vlrpar1);
            parame.put(para2, vlrpar2);
            parame.put(para3, vlrpar3);
            parame.put(para4, vlrpar4);
            parame.put(para5, vlrpar5);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Reporte de Facultades");
                JsperView.setExtendedState(6);
                
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
        
    public void ImpresionBoletaProductos(String para1, String vlrpar1, String para2, String vlrpar2,
            String para3, String vlrpar3, String para4, String vlrpar4, String para5, String vlrpar5, String para6,
            String vlrpar6, String para7, String vlrpar7, String NRpt, String para8, String vlrpar8) {
        try {
            Map parame = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + NRpt + ".jasper";
            parame.put(para1, vlrpar1);
            parame.put(para2, vlrpar2);
            parame.put(para3, vlrpar3);
            parame.put(para4, vlrpar4);
            parame.put(para5, vlrpar5);
            parame.put(para6, vlrpar6);
            parame.put(para7, vlrpar7);
            parame.put(para8, vlrpar8);
            System.out.println(para8+" var - val"+vlrpar8);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Reporte de Facultades");
                JsperView.setExtendedState(6);
                
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public void Impresion(String prmcd, String prmnom, String NRpt) {
        try {
            Map parame = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + NRpt + ".jasper";
            parame.put("FAC", prmcd);
            parame.put("NMFAC", prmnom);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Reporte de Facultades");
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
        }
    }

    public void ImpresionDetalleAlojamiento(String pr1, String pr2, String pr3, String pr4, String pr5, String NRpt) {
        try {
            Map parame = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + NRpt + ".jasper";
            parame.put("p_alojamiento", pr1);
            parame.put("p_dias", pr2);
            parame.put("p_precio", pr3);
            parame.put("p_totalalojamiento", pr4);
            parame.put("total_pagar", pr5);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Reporte de Facultades");
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
        }
    }

    public void ImprInveInixSede(String Nomrep, String Titulo, String Sede) {
        try {
            Map parame = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + Nomrep;
            parame.put("Sede", Sede);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle(Titulo);
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public void ImprGeneral(String Nomrep, String Titulo) {
        try {
            Map parame = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + Nomrep;
            parame.put("", "");
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle(Titulo);
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
        }
    }

    public void ImprConFechas(String inicio, String fin, String NRpt, String mnt) {
        try {
            Map parame = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + NRpt;
            parame.put("Desde", inicio);
            parame.put("Hasta", fin);
            parame.put("Monto", mnt);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Reporte Montos de venta por Vendedor");
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
        }
    }

    public void ImprReq(String tpo, String inicio, String fin, String NRpt) {
        try {
            Map parame = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + NRpt;
            parame.put("Tipo", tpo);
            parame.put("Desde", inicio);
            parame.put("Hasta", fin);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Reporte de Requisitorias");
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
        }
    }

    public void ImprReqtotal(String inicio, String fin, String NRpt) {
        try {
            Map parame = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + NRpt;
            parame.put("Desde", inicio);
            parame.put("Hasta", fin);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Reporte Total de Requisitorias");
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
        }
    }

    public void ImprReqxUsuarios(String inicio, String fin, String Usua, String nomUsu, String NRpt) {
        try {
            Map parame = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + NRpt;
            parame.put("Desde", inicio);
            parame.put("Hasta", fin);
            parame.put("Usuario", Usua);
            parame.put("nombre", nomUsu);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Reporte de Requisitorias por Usuarios");
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
        }
    }

    public void ImprRepReqPositivas(String pinicio, String pfinal, String NRpt, String d, String h) {
        try {
            Map parame = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + NRpt;
            parame.put("fdesde", pinicio);
            parame.put("fhasta", pfinal);
            parame.put("des", d);
            parame.put("has", h);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Reporte Total de Requisitorias");
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
        }
    }

    public void Impresiones1(String Titulo, String NRpt, String sed) {
        try {
            Map parame = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + NRpt + ".jasper";
            parame.put("sede", sed);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle(Titulo);
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
        }
    }

    public void Impresiones(String Titulo, String NRpt) {
        try {
            Map parame = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + NRpt + ".jasper";
            parame.put("", "");
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle(Titulo);
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
        }
    }

    public void ImpresionesDistribucion(String Titulo, String NRpt, String par1, String vlrprm1,
            String par2, String vlrprm2) {
        try {
            Map parame = new HashMap();
            rta = System.getProperties().getProperty("user.dir") + "/src/Reportes/" + NRpt + ".jasper";
            parame.put(par1, vlrprm1);
            parame.put(par2, vlrprm2);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle(Titulo);
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
        }
    }

    public void ImprimirusConFechasFactura(String nmbrpte, String codigo, String num, String mes) {
        try {
            Map parame = new HashMap();
            rta = "/src/Reportes/" + nmbrpte;
            rta1 = System.getProperties().getProperty("user.dir") + "\\src\\Reportes\\" + nmbrpte;
            parame.put("codv", codigo);
            parame.put("tot", num);
            parame.put("mesl", mes);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Reporte con Parametros");
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public void ImprimirusConFechasBoleta(String nmbrpte, String codigo, String mes) {
        try {
            Map parame = new HashMap();
            rta = "/src/Reportes/" + nmbrpte;
            rta = System.getProperties().getProperty("user.dir") + "\\src\\Reportes\\" + nmbrpte;
            parame.put("codv", codigo);
            parame.put("mesl", mes);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Reporte con Parametros");
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public void ImprimirusConFechasBofact(String nmbrpte, String codigo, String mes) {
        try {
            Map parame = new HashMap();
            rta = "/src/Reportes/" + nmbrpte;
            rta = System.getProperties().getProperty("user.dir") + "\\src\\Reportes\\" + nmbrpte;
            parame.put("codv", codigo);
            parame.put("tot", mes);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Reporte con Parametros");
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public void ImprimirusConFechas(String nmbrpte, String codigo) {
        try {
            Map parame = new HashMap();
            rta = "/src/Reportes/" + nmbrpte;
            rta = System.getProperties().getProperty("user.dir") + "\\src\\Reportes\\" + nmbrpte;
            parame.put("codv", codigo);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Reporte con Parametros");
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public void ImprimirusConFechasVarias(String nmbrpte, String fechini, String fechFin) {
        try {
            //
            Map parame = new HashMap();
            rta = "/src/Reportes/" + nmbrpte;
            rta1 = System.getProperties().getProperty("user.dir") + "\\src\\Reportes\\" + nmbrpte;
            parame.put("fecIn", fechini);
            parame.put("fecFi", fechFin);
            parame.put("codv", info.getIdSede());
            parame.put("sede", Controlador.sede);
            JasperPrint prt = JasperFillManager.fillReport(rta1, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Reporte con Parametros");
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public void ImprimirusConFechasVariasUsua(String nmbrpte, String fechini, String fechFin, String us) {
        try {
            Map parame = new HashMap();
            rta = "/src/Reportes/" + nmbrpte;
            rta = System.getProperties().getProperty("user.dir") + "\\src\\Reportes\\" + nmbrpte;
            parame.put("fecIn", fechini);
            parame.put("fecFi", fechFin);
            parame.put("usr", us);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Reporte con Parametros");
                JsperView.setExtendedState(6);
                JsperView.show();
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public void ImprimirusConFechasVariasUsuaModo(String nmbrpte, String fechini, String fechFin, String us, String mod) {
        try {
            Map parame = new HashMap();
            rta = "/src/Reportes/" + nmbrpte;
            rta1 = System.getProperties().getProperty("user.dir") + "\\src\\Reportes\\" + nmbrpte;
            parame.put("fecIn", fechini);
            parame.put("fecFi", fechFin);
            parame.put("usr", us);
            parame.put("modo", mod);
            JasperPrint prt = JasperFillManager.fillReport(rta, parame, cconn.conec);
            int n = prt.getPages().size();
            if (n > 0) {
                JasperViewer JsperView = new JasperViewer(prt, false);
                JsperView.setTitle("Reporte con Parametros");
                JsperView.setExtendedState(6);
                JsperView.show();
                
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos");
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }


  public void pdf(String nmbrpte, String fechini, String fechFin){
        JasperReport jasperReport;
        JasperPrint jasperPrint;                
        try
        {
          //se carga el reporte
            
            URL  in=this.getClass().getResource( System.getProperties().getProperty("user.dir") + "\\src\\Reportes\\" + nmbrpte );
       //     URL  in=this.getClass().getResource( "reporte.jasper" );

            Map parame = new HashMap();
            rta = "/src/Reportes/" + nmbrpte;
            rta1 = System.getProperties().getProperty("user.dir") + "\\src\\Reportes\\" + nmbrpte;
            parame.put("fecIn", fechini);
            parame.put("fecFi", fechFin);
            parame.put("codv", info.getIdSede());
         // jasperReport=(JasperReport)JRLoader.loadObject(in);
          //se procesa el archivo jasper
          jasperPrint = JasperFillManager.fillReport(rta1, parame, cconn.conec);
          //se crea el archivo PDF
            JasperExportManager.exportReportToPdfFile( jasperPrint, "D:/misActas/reporte.pdf");
            
        }
        catch (JRException ex)
        {
          System.err.println( "Error iReport: " + ex.getMessage() );
        }
  }


}
