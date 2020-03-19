/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.util.Pair;

/**
 *
 * @author mashrur
 * 
 * Name: Mashrur Rashik
 * Roll: 29
 * 
 */
public class MutablePair {
    public int value, key;


    public MutablePair(int key, int value) {
        this.value = value;
        this.key = key;
    }

    public Integer getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    
    
}
