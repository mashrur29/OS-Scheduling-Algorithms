/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
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
public class FCFSprocess {

    int processId, numberPage;
    int arrivalTime;
    int actualArrival;
    int waitingTime;
    int executionTime;
    int memoryRefered;
    int totalTime;
    int tableSize;
    ArrayList<Integer> pageTable = new ArrayList<>();
    int arr[];
    int numFaults;
    int sz;
    static int timeQuanta;
    public static int remTime;
    PageTable pages;

    public FCFSprocess(int processId, int numberPage, int arrivalTime) {
        this.processId = processId;
        this.numberPage = numberPage;
        this.arrivalTime = arrivalTime;
        actualArrival = arrivalTime;
        numFaults = 0;

        double temp = numberPage;
        sz = (int) Math.ceil((temp / 3.0));
        this.tableSize = sz;
        arr = new int[10000001];
        waitingTime = 0;
        pages = new PageTable(tableSize);
        executionTime = 0;
        memoryRefered = 0;
        totalTime = 0;
    }

    public FCFSprocess() {
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setActualArrival(int actualArrival) {
        this.actualArrival = actualArrival;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public void setMemoryRefered(int memoryRefered) {
        this.memoryRefered = memoryRefered;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }

    public void setPageTable(ArrayList<Integer> pageTable) {
        this.pageTable = pageTable;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public void setNumFaults(int numFaults) {
        this.numFaults = numFaults;
    }

    public void setSz(int sz) {
        this.sz = sz;
    }

    public static void setTimeQuanta(int timeQuanta) {
        FCFSprocess.timeQuanta = timeQuanta;
    }

    public static void setRemTime(int remTime) {
        FCFSprocess.remTime = remTime;
    }

    public void setPages(PageTable pages) {
        this.pages = pages;
    }

    public void referenceMemory(int number) {
        int temp = number / 512;
        pages.memoryReferences.add(temp);
        memoryRefered++;
    }

    public boolean isPageFault() {
        int pageNumber = pages.memoryReferences.peek();
        return pages.isFault(pageNumber);
    }

    public void noFault() {
        int pageNumber = pages.memoryReferences.remove();
        pages.insert(pageNumber);
    }

    public void faultOccured() {
        int pageNumber = pages.memoryReferences.peek();
        pages.insert(pageNumber);
        numFaults++;
    }

    public int getProcessId() {
        return processId;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getActualArrival() {
        return actualArrival;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public int getMemoryRefered() {
        return memoryRefered;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public int getTableSize() {
        return tableSize;
    }

    public ArrayList<Integer> getPageTable() {
        return pageTable;
    }

    public int[] getArr() {
        return arr;
    }

    public int getNumFaults() {
        return numFaults;
    }

    public int getSz() {
        return sz;
    }

    public static int getTimeQuanta() {
        return timeQuanta;
    }

    public static int getRemTime() {
        return remTime;
    }

    public PageTable getPages() {
        return pages;
    }

}
