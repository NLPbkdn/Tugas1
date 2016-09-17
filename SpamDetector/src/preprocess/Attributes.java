/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocess;

import Element.Element;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ivanandrianto
 */
public class Attributes {
    
    private ArrayList<Element> dataTraining;
    private HashMap<String, Integer> attributes;

    public Attributes(ArrayList<Element> dataTraining) {
        this.dataTraining = dataTraining;
    }

    public HashMap<String, Integer> createAttributes() {
        attributes = new HashMap<String, Integer>();
        System.out.println("DT size: " + dataTraining.size());
        Set<String> list = new HashSet<>();
        for (int i = 0; i < dataTraining.size(); i++) {
            ArrayList<String> words = dataTraining.get(i).getWords();
            for (int j = 0; j < words.size(); j++) {
                attributes.put(words.get(j), 0);
            }
        }
        return attributes;
    }

    public void setValue() {
        for (int i = 0; i < dataTraining.size(); i++) {
            HashMap<String, Integer> hashMapValue = new HashMap<String, Integer>(attributes);
            Element data = dataTraining.get(i);
            ArrayList<String> words = data.getWords();
            for (int j = 0; j < words.size(); j++) {
                String word = words.get(j);
                hashMapValue.put(word, 1);
            }
            ArrayList<Integer> value = getValue(hashMapValue);
            System.out.println(value);
            data.setvalue(value);
        }
    }
    
    private ArrayList<Integer> getValue(HashMap<String, Integer> map) {
        ArrayList<Integer> value = new ArrayList<Integer>();
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            value.add(entry.getValue());
        }
        return value;
    }
}
