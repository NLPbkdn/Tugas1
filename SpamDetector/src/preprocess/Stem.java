/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocess;

/**
 *
 * @author Hp
 */
public class Stem {
    
    public Stem(){
    
    }
    
    public String stemWord(String S){
        String ret = "";
        
        if (findInDictionary(S)){
            return S;
        }else{
            ret = step2(S);
            
        }
        
  
        return ret;
    }
    
    //STEP 1
    private boolean findInDictionary(String S){
        //for the time being, until a dictionary search can be implemented
        return false;
    }
    
    //STEP 2 
    //remove lah, kah, nya, mu dan ku
    private String step2(String S){
        String ret = S;
        if(S.length()> 4){
            if(S.substring(S.length()-3).equals("lah")||S.substring(S.length()-3).equals("kah")){
                ret = S.substring(0,S.length()-3);
            }
            if(S.length()>4){
                if(S.substring(S.length()-3).equals("nya")){
                    ret = S.substring(0,S.length()-3);
                }else if(S.substring(S.length()-2).equals("mu")||S.substring(S.length()-2).equals("ku")){
                    ret = S.substring(0,S.length()-2);
                }   
            }
            return ret;
        }else{
            return S;
        }
    }
    
    //STEP 3
    private String step3(String S){
        String removed = "";
        String ret = "";
        if(S.substring(S.length()-2).equals("an")){
            ret = S.substring(0,S.length()-2);
            removed = "an";
        }else if(S.substring(S.length()-1).equals("i")){
            ret = S.substring(0,S.length()-1);
            removed = "i";
        }
        else{
            return S;
        }
        
        //suffix removed goto step4
        //if step4 failed, remove "k" and re-attempt
        String ret4 = step4(ret);
        
        if(ret==ret4){
            if(removed.equals("an")&&ret.substring(ret.length()-1).equals("k")){
                
                //remove "k" and re-attempt step4
                String ret5 = step4(ret.substring(0,ret4.length()-1));
                
                if(ret4!=ret5){
                    return ret5;
                }
            }
            return ret4+removed;
        }else{
            return ret4;
        }
        
    }
    
    //STEP 4
    //not yet done
    private String step4(String S){
        return S;
    }
    
}
