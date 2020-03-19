/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author mashrur
 * 
 * Name: Mashrur Rashik
 * Roll: 29
 * 
 */
public class Process {

    int processId, numberPage;
    int arrivalTime;
    ArrayList<Integer> memoryReference;
    PageTable pages;
    int waitingTime;
    int executionTime;
    int memoryRefered;
    int totalTime;

    public Process(int processId, int numberPage, int arrivalTime) {
        this.processId = processId;
        this.numberPage = numberPage;
        this.arrivalTime = arrivalTime;
        memoryReference = new ArrayList<>();
        double temp = numberPage;
        int sz = (int) Math.ceil((temp / 3.0));
        pages = new PageTable(sz);
        waitingTime = 0;
        executionTime = 0;
        memoryRefered = 0;
        totalTime = 0;
    }

    public Process() {
    }

    public void referenceMemory(int number) {
        memoryReference.add(number);
        int temp = number / 512;
        pages.insert(temp);
        memoryRefered++;
    }

    public int pageFaults() {
        return pages.getPageFaults();
    }

}
