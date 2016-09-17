/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Element;

import java.util.ArrayList;
import IndonesianNLP.*;

/**
 *
 * @author Hp
 */
public class Element {
    private String label;
    private ArrayList<String> words;
    private ArrayList<Integer> value;
    
    public Element(){
        label= "";
        words = new ArrayList<String>();
    }
    
    public Element(String _words,String _label){
        label = _label;
        IndonesianSentenceTokenizer tokenizer = new IndonesianSentenceTokenizer();
        words = tokenizer.tokenizeSentence(_words);
        value = new ArrayList<Integer>();
    }
    
    public ArrayList<String> getWords(){
        return words;
    }
    
    public String getLabel(){
        return label;
    }
    
    public ArrayList<Integer> getValue(){
        return value;
    }
    
    public void setWords(ArrayList<String> _words){
        words = _words;
    }
    
    public void setLabel(String _label){
        label = _label;
    }
    
    public void setvalue(ArrayList<Integer> _value){
        value = _value;
    }
}
