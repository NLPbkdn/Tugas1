/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ivanandrianto
 */
public class Attributes {
    public static ArrayList<String> createAttributes(ArrayList<ArrayList<String>> wordsList) {
        Set<String> list = new HashSet<>();
        for (int i = 0; i < wordsList.size(); i++) {
            ArrayList<String> words = wordsList.get(i);
            for (int j = 0; j < words.size(); j++) {
                list.add(words.get(j));
            }
        }
        ArrayList<String> attributes = new ArrayList<String>();
        attributes.addAll(list);
        System.out.println(attributes);
        return attributes;
    }
}
