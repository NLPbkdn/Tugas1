/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spamdetector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import weka.core.Instances;
import weka.core.converters.TextDirectoryLoader;

/**
 *
 * @author Satria
 */
public class Coba {
    public static void main(String[] args) throws Exception {
    // convert the directory into a dataset
        
        BufferedReader inputReader = null;
        try {
                inputReader = new BufferedReader(new FileReader(args[0]));
                Instances data = new Instances(inputReader);
                System.out.println(data.toString());
        } catch (FileNotFoundException ex) {
                System.err.println("File not found: " + args[0]);
        }
 
    System.out.println("Finished");
  }
}
