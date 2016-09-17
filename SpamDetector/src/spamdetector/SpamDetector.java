/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spamdetector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.core.FastVector;
import weka.core.Instances;

import IndonesianNLP.*;
import Element.Element;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import preprocess.*;
import weka.classifiers.trees.SimpleCart;

/**
 *
 * @author Kak Wimus & Ivan & Tio
 */
public class SpamDetector {

    public static BufferedReader readDataFile(String filename) {
        BufferedReader inputReader = null;
        try {
                inputReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
                System.err.println("File not found: " + filename);
        }
        return inputReader;
    }
    
    public static void preProcessing(String filename) throws IOException{
        BufferedReader datafile = readDataFile(filename);
        String line = "";

        line = datafile.readLine();
        String word = line.substring(0,line.lastIndexOf(","));
        IndonesianSentenceTokenizer tokenizer = new IndonesianSentenceTokenizer();
        System.out.println(tokenizer.tokenizeSentence(word));
        
        IndonesianSentenceDetector detector = new IndonesianSentenceDetector();
        System.out.println(detector.splitSentence(word));
    
    
    }
    
    public static void createModel(){
    
    }

    public static Evaluation classify(Classifier model,Instances trainingSet, Instances testingSet) throws Exception {
        Evaluation evaluation = new Evaluation(trainingSet);
        model.buildClassifier(trainingSet);
        evaluation.evaluateModel(model, testingSet);
        return evaluation;
    }
 
    public static double calculateAccuracy(FastVector predictions) {
        double correct = 0;
        for (int i = 0; i < predictions.size(); i++) {
                NominalPrediction np = (NominalPrediction) predictions.elementAt(i);
                if (np.predicted() == np.actual()) {
                        correct++;
                }
        }
        return 100 * correct / predictions.size();
    }

    public static Instances[][] crossValidationSplit(Instances data, int numberOfFolds) {
            Instances[][] split = new Instances[2][numberOfFolds];
            for (int i = 0; i < numberOfFolds; i++) {
                    split[0][i] = data.trainCV(numberOfFolds, i);
                    split[1][i] = data.testCV(numberOfFolds, i);
            }
            return split;
    }
    
    public void ClassifySpam() throws IOException{
        
        BufferedReader datafile = readDataFile("tio.arff");
        Instances data = new Instances(datafile);
        data.setClassIndex(0);
        System.out.println(data.classAttribute().toString());
        // Do 10-split cross validation
        Instances[][] split = crossValidationSplit(data, 10);

        // Separate split into training and testing arrays
        Instances[] trainingSplits = split[0];
        Instances[] testingSplits = split[1];

        // Use a set of classifiers
        Classifier[] models = { 
                        new SimpleCart(), // a decision tree
        };

        // Run for each model
        for (int j = 0; j < models.length; j++) {

                // Collect every group of predictions for current model in a FastVector
                FastVector predictions = new FastVector();

                // For each training-testing split pair, train and test the classifier
                for (int i = 0; i < trainingSplits.length; i++) {
                    try {
                        System.out.println("Building for training Split : " + i);
                        Evaluation validation = classify(models[j], trainingSplits[i], testingSplits[i]);

                        predictions.appendElements(validation.predictions());

                        // Uncomment to see the summary for each training-testing pair.
                        System.out.println(models[j].toString());
                    } catch (Exception ex) {
                        Logger.getLogger(SpamDetector.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                // Calculate overall accuracy of current classifier on all splits
                double accuracy = calculateAccuracy(predictions);

                // Print current classifier's name and accuracy in a complicated,
                // but nice-looking way.
                System.out.println("Accuracy of " + models[j].getClass().getSimpleName() + ": "
                                + String.format("%.2f%%", accuracy)
                                + "\n---------------------------------");
        }
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
            Preprocess P = new Preprocess("datatest.arff");
            P.processing();
            SpamDetector detector = new SpamDetector();
            detector.ClassifySpam();
//            preProcessing("datatest.csv");
            //Attributes A = new Attributes(datatrain);
            //HashMap<String, Integer> attributes = A.createAttributes();
            //A.setValue();
    }
}
