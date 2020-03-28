package Clases;

import java.net.URL;

import javax.swing.ImageIcon;

public class Tools {
    
    public Tools() {
        
    }
    
    public static ImageIcon cargarIcono( String pPath , int pAncho, int pAlto ) {
    
        try {
            
            URL iconoURL = Tools.class.getResource(pPath); 
            
            ImageIcon iconoOriginal = new ImageIcon( iconoURL );
            
            ImageIcon iconoEscala = new ImageIcon(iconoOriginal.getImage().getScaledInstance(pAncho, pAlto, java.awt.Image.SCALE_DEFAULT));
            
            return iconoEscala;
            
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        
        return null;
        
    }
    
    
}