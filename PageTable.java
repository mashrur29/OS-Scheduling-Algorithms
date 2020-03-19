/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author mashrur
 * 
 * Name: Mashrur Rashik
 * Roll: 29
 * 
 */
public class PageTable {

    int tableSize;
    int[] pageTable;
    int pageFaults;
    boolean[] isFault;
    boolean is;
    Queue<MutablePair> memoryReference = new LinkedList<>();
    Queue<Integer> memoryReferences = new LinkedList<>();
    
    public PageTable(int tableSize) {
        this.tableSize = tableSize;
        pageTable = new int[tableSize];
        for (int i = 0; i < tableSize; i++) {
            pageTable[i] = -1;
        }
        isFault = new boolean[tableSize];
        pageFaults = 0;
        is = false;
    }

    public int findPage(int pageNumber) {
        for (int i = 0; i < tableSize; i++) {
            if (pageTable[i] == pageNumber) {
                return i;
            }
        }
        return -1;
    }

    public int isEmpty() {
        for (int i = 0; i < tableSize; i++) {
            if (pageTable[i] == -1) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean isFault(int pageNumber) {
        if(findPage(pageNumber) == -1) return true;
        else return false;
    }
   
    public void insert(int pageNumber) {
        int exists = findPage(pageNumber);
        if (exists == -1) {
            pageFaults++;
            is = true;
        }
        else is = false;

        if (exists != -1) {
            for (int i = exists; i > 0; i--) {
                pageTable[i] = pageTable[i - 1];
            }
            pageTable[0] = pageNumber;
        } else {
            int emptySpace = isEmpty();
            if (emptySpace == -1) {
                emptySpace = tableSize - 1;
            }
            for (int i = emptySpace; i > 0; i--) {
                pageTable[i] = pageTable[i - 1];
            }
            pageTable[0] = pageNumber;
        }

    }

    public int getPageFaults() {
        return pageFaults;
    }

}
