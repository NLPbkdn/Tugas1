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
import com.opencsv.CSVReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import weka.core.Instances;
import weka.core.converters.TextDirectoryLoader;

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
    
    public void processing() throws IOException{
        BufferedReader datafile = readDataFile();
        String line = "";
        ArrayList<Element> elements = new ArrayList<Element>();
        
        IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
        IndonesianSentenceTokenizer tokenizer = new IndonesianSentenceTokenizer();
        IndonesianStemmer stemmer = new IndonesianStemmer();

        //CSVReader reader = new CSVReader(new FileReader("datatest.arff"));
        //String [] nextLine;
        BufferedReader inputReader = null;
        
 
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("newArff.arff"), "utf-8"))) {
           writer.write("@relation newArff\n" +
                        "\n" +
                        "@attribute Sms string\n" +
                        "@attribute class{'spam', 'NotSpam'}\n" +
                        "\n" +
                        "@data\n");
            inputReader = new BufferedReader(new FileReader("datatest.arff"));
            Instances data = new Instances(inputReader);
            int index = 0;
            while (index < data.numInstances()) {
                String all = data.instance(index).toString();
                String[] label = all.split("\',");
                String words = label[0];
                //System.out.println(words);
                String classSpam = null;
                if (label.length > 1){
                    
                   classSpam  = label[1];
                } else {
                   classSpam = "spam"; 
                }
                //System.out.println(words+ ":" + classSpam);
                //NORMALIZE SENTENCES
                words = formalizer.normalizeSentence(words);
                //DELETE STOP WORDS
                formalizer.initStopword();
                words = formalizer.deleteStopword(words).toLowerCase();
                //STEM SENTENCES
                words = stemmer.stemSentence(words);
                //System.out.println("Tio"+words+ ":" + classSpam);
                //add element to elements
                //elements.add(new Element(words,label));
                index++;
                writer.write("\"" + words +"\"" + ","+ classSpam + "\n");
                
            }
        } catch (FileNotFoundException ex) {
                System.err.println("File not found: datatest.arff");
        }
        IndexTest indexTester = new IndexTest();
        indexTester.IndexARFF("newArff.arff","tio.arff");
        //return elements;
    }
    
}
