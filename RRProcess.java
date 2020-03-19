/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javafx.util.Pair;

/**
 *
 * @author mashrur
 * 
 * Name: Mashrur Rashik
 * Roll: 29
 * 
 */
public class RRProcess {

    int processId, numberPage;
    int arrivalTime;
    int actualArrival;
    int waitingTime;
    int executionTime;
    int memoryRefered;
    int totalTime;
    int tableSize;
    ArrayList<Integer> pageTable = new ArrayList<>();
    int numFaults;
    int sz;
    int remTime;
    static int timeQuanta;
    PageTable pages;

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

    public int getNumFaults() {
        return numFaults;
    }

    public int getSz() {
        return sz;
    }

    public int getRemTime() {
        return remTime;
    }

    public static int getTimeQuanta() {
        return timeQuanta;
    }

    public PageTable getPages() {
        return pages;
    }

    public RRProcess(int processId, int numberPage, int arrivalTime) {
        this.processId = processId;
        this.numberPage = numberPage;
        this.arrivalTime = arrivalTime;
        actualArrival = arrivalTime;
        numFaults = 0;

        double temp = numberPage;
        sz = (int) Math.ceil((temp / 3.0));
        this.tableSize = sz;
        pages = new PageTable(tableSize);
        waitingTime = 0;
        executionTime = 0;
        memoryRefered = 0;
        totalTime = 0;
        remTime = 30;
    }

    public RRProcess() {
        remTime = 30;
    }

    public void referenceMemory(int number) {
        int temp = number / 512;
        pages.memoryReference.add(new MutablePair(temp, 30));
        memoryRefered++;
    }

    public boolean isPageFault() {
        int pageNumber = pages.memoryReference.peek().getKey();
        return pages.isFault(pageNumber);
    }

    public void faultOccured() {
        int pageNumber = pages.memoryReference.peek().getKey();
        pages.insert(pageNumber);
        numFaults++;
    }

    public void noFault() {
        int pageNumber = pages.memoryReference.peek().getKey();
        pages.memoryReference.remove();
        pages.insert(pageNumber);
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

    public void setNumFaults(int numFaults) {
        this.numFaults = numFaults;
    }

    public void setSz(int sz) {
        this.sz = sz;
    }

    public void setRemTime(int remTime) {
        this.remTime = remTime;
    }

    public static void setTimeQuanta(int timeQuanta) {
        RRProcess.timeQuanta = timeQuanta;
    }

    public void setPages(PageTable pages) {
        this.pages = pages;
    }

}
