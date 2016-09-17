/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import Element.Element;
import java.util.ArrayList;
import IndonesianNLP.*;
import java.io.IOException;

/**
 *
 * @author Hp
 */
public class Preprocess {
    private String filename;
    
    public Preprocess(String _filename){
        filename = _filename;
    }
    
    public void setFileName(String _filename){
        filename = _filename;
    }
    
    private BufferedReader readDataFile() {
        BufferedReader inputReader = null;
        try {
                inputReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
                System.err.println("File not found: " + filename);
        }
        return inputReader;
    }
    
    public ArrayList<Element> processing() throws IOException{
        BufferedReader datafile = readDataFile();
        String line = "";
        ArrayList<Element> elements = new ArrayList<Element>();
        
        IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
        IndonesianSentenceTokenizer tokenizer = new IndonesianSentenceTokenizer();
        IndonesianStemmer stemmer = new IndonesianStemmer();
        
        while ((line = datafile.readLine()) != null){
            System.out.println(line);
            String words = line.substring(0,line.lastIndexOf(","));
            String label = line.substring(line.lastIndexOf(",")+1);
            //NORMALIZE SENTENCES
            words = formalizer.normalizeSentence(words);
            //DELETE STOP WORDS
            formalizer.initStopword();
            words = formalizer.deleteStopword(words).toLowerCase();
            //STEM SENTENCES
            words = stemmer.stemSentence(words);
            
            //add element to elements
            elements.add(new Element(words,label));
        }
        
        return elements;
    }
    
}
