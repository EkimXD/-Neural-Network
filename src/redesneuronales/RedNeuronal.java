/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redesneuronales;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.misc.SerializedClassifier;
import weka.core.Attribute;
import weka.core.Debug;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;

/**
 *
 * @author USRBET
 */
public class RedNeuronal {

    private static final String CLASIFICADOR = "ANNModel.train";
    private static final String VERSE = "nameXD";
    Instance instance;

    public void train(String param, String file) {
        try {
            //arff
            FileReader fileReader = new FileReader(file);
            //instancias
            Instances train = new Instances(fileReader);
            train.setClassIndex(train.numAttributes() - 1);
            //crear clasificador
            MultilayerPerceptron mlp = new MultilayerPerceptron();
            mlp.setOptions(Utils.splitOptions(param));
            mlp.buildClassifier(train);
            //save
            Debug.saveToFile(CLASIFICADOR, mlp);
            Evaluation eva=evaluar(train);
            
            System.out.println(eva.toSummaryString("********Entrenamiento********",true)+"\n"+eva.toMatrixString());
            fileReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RedNeuronal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RedNeuronal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RedNeuronal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public String imprimirResultados(Instances instances) {
//        try {
//            SerializedClassifier classifier = new SerializedClassifier();
//            classifier.setModelFile(new File(CLASIFICADOR));
//            //evaluacion
//            //
//            //Classifier mlp = classifier.getCurrentModel();
//            //
//            Evaluation evaluation = new Evaluation(instances);
//            evaluation.evaluateModel(classifier, instances);
//            evaluation.confusionMatrix();
//            return evaluation.toMatrixString();
//            
//
//        } catch (Exception ex) {
//            Logger.getLogger(RedNeuronal.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }
    
    private Evaluation evaluar(Instances instances) {
        try {
            SerializedClassifier classifier = new SerializedClassifier();
            classifier.setModelFile(new File(CLASIFICADOR));
            Evaluation evaluation = new Evaluation(instances);
            evaluation.evaluateModel(classifier, instances);
            
            return evaluation;
            

        } catch (Exception ex) {
            Logger.getLogger(RedNeuronal.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void test(String file) {
        try {
            //arff
            FileReader fileReader = new FileReader(file);
            Instances test = new Instances(fileReader);
            test.setClassIndex(test.numAttributes() - 1);
            Evaluation eva=evaluar(test);
            
            
            System.out.println(eva.toSummaryString("********Prueba********",true)+"\n"+eva.toMatrixString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RedNeuronal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RedNeuronal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RedNeuronal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     public String test(double [] instanceValue ) {
         ArrayList<Attribute> atts = new ArrayList<>();         
         atts.add(new Attribute("parents",new ArrayList<>(Arrays.asList("usual","pretentious","great_pret"))));
         atts.add(new Attribute("has_nurs",new ArrayList<>(Arrays.asList("proper","less_proper","improper","critical","very_crit"))));
         atts.add(new Attribute("form", new ArrayList<>(Arrays.asList("complete","completed","incomplete","foster"))));
         atts.add(new Attribute("children",new ArrayList<>(Arrays.asList("1","2","3","more"))));
         atts.add(new Attribute("housing",new ArrayList<>(Arrays.asList("convenient","less_conv","critical"))));
         atts.add(new Attribute("finance",new ArrayList<>(Arrays.asList("convenient","inconv"))));
         atts.add(new Attribute("social",new ArrayList<>(Arrays.asList("nonprob","slightly_prob","problematic"))));
         atts.add(new Attribute("health",new ArrayList<>(Arrays.asList("recommended","priority","not_recom"))));
         atts.add(new Attribute("class",new ArrayList<>(Arrays.asList("not_recom","recommend","very_recom","priority","spec_prior"))));

         Instances dataRaw = new Instances("TestInstances", atts, 0);
         dataRaw.setClassIndex(dataRaw.numAttributes() - 1);
         dataRaw.add(new DenseInstance(1.0, instanceValue));
         Evaluation eval=evaluar(dataRaw);
         double [][]aux=eval.confusionMatrix();
         String line="";
         for (int i = 0; i < aux.length; i++) {
             for (int j = 0; j < aux[i].length; j++) {
                 if(aux[i][j]==1.0){
                     line=dataRaw.attribute(8).value(j);                     
                 }
                 System.out.print(aux[i][j]+"\t");
             }         
             System.out.println();
         }
         return line;
    }

}
