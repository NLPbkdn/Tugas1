/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spamdetector;

import java.util.ArrayList;

/**
 *
 * @author ivanandrianto
 */
public class FeatureExtraction {
    
    private ArrayList<String> trainingData;
    
    public FeatureExtraction(ArrayList<String> trainingData) {
        
    }
    
    public ArrayList<String> execute () {
        // Split words
        ArrayList<String[]> splittedTrainingData = new ArrayList<String[]>();
        for (int i = 0; i < trainingData.size(); i++) {
            splittedTrainingData.add(splitWords(trainingData.get(i)));
        }

        // Normalize
        for (int i = 0; i < splittedTrainingData.size(); i++) {
            String[] splittedWords = splittedTrainingData.get(i);
            for (int j = 0; j < splittedWords.length; j++) {
                splittedWords[j] = normalize(splittedWords[j]);
            }
        }
        return trainingData;
    }
    
    private String[] splitWords(String str) {
        return str.split("[ ,.]+");
    }
    
    private String normalize(String word) {
        String[] stopCharacters = {".", ","};
        for (int i = 0; i < stopCharacters.length; i++) {
            word.replace(stopCharacters[i], "");
        }
        return word;
    }

}
