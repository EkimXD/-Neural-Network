/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesneuronales;

import interfaz.NewJFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author USRBET
 */
public class RedesNeuronales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic 
        //JOptionPane.showMessageDialog(null, "Please wait", "Wait", JOptionPane.INFORMATION_MESSAGE);
        //new RedNeuronal().train("-L 0.75 -M 0.75 -N 500 -V 0 -S 0 -E 20 -H a", "E:\\Documentos\\5to semestre\\IntArtificial\\Prog\\RedesNeuronales\\nurseryTrain.arff");
        new RedNeuronal().test("E:\\Documentos\\5to semestre\\IntArtificial\\Prog\\RedesNeuronales\\nursery.arff");
        new interfaz.NewJFrame();
    }
    
}
