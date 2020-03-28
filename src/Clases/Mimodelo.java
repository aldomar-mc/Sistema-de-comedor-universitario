/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

/**
 *
 * @author Miguel
 */
import javax.swing.*;
import javax.swing.table.*;
public class Mimodelo extends DefaultTableModel{
 public boolean isCellEditable (int row, int column)
   {   if (column == 30)
          return true;
       return false;
   }
}
