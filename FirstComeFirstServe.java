/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author mashrur
 * 
 * Name: Mashrur Rashik
 * Roll: 29
 * 
 */
public class FirstComeFirstServe {

    FCFSprocess allProcess[];
    int process;
    int totalPageFault;
    double totalWaitingTime;
    double totalTurnAround;
    double avgWaiting;
    double avgTurnAround;
    int pageFault[];
    int currentTime = 0;
    int blockTime = 0;

    public FirstComeFirstServe(FCFSprocess[] allProcess, int process) {
        this.allProcess = allProcess;
        this.process = process;
        for (int i = 0; i < process; i++) {
            allProcess[i].waitingTime = Constants.DiskTime * allProcess[i].numFaults;
            allProcess[i].executionTime = Constants.ExecTime * allProcess[i].memoryRefered;
            allProcess[i].totalTime = allProcess[i].waitingTime + allProcess[i].executionTime;
        }
        totalWaitingTime = 0;
        totalPageFault = 0;
        totalTurnAround = 0;
        pageFault = new int[process];
    }

    public void setTotalWaitingTime(double totalWaitingTime) {
        this.totalWaitingTime += totalWaitingTime;
    }

    public void setTotalTurnAround(double totalTurnAround) {
        this.totalTurnAround += totalTurnAround;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public void incCurrentTime() {
        this.currentTime += Constants.ExecTime;
    }

    public void setBlockTime(int blockTime) {
        this.blockTime = blockTime;
    }

    public void updateCalculation(FCFSprocess temp) {
        setTotalWaitingTime(currentTime - temp.getActualArrival() - (Constants.ExecTime * temp.getMemoryRefered()));
        setTotalTurnAround(currentTime - temp.getActualArrival());
    }

    public void run() {
        Comparator<FCFSprocess> arrivalTimeComparators = new Comparator<FCFSprocess>() {
            @Override
            public int compare(FCFSprocess s1, FCFSprocess s2) {
                return s1.arrivalTime - s2.arrivalTime;
            }
        };

        PriorityQueue<FCFSprocess> pq1 = new PriorityQueue<>(arrivalTimeComparators);
        for (int i = 0; i < process; i++) {
            pq1.add(allProcess[i]);
        }

        setCurrentTime(pq1.peek().arrivalTime);
        setBlockTime(pq1.peek().arrivalTime);

        while (!pq1.isEmpty()) {
            FCFSprocess temp = pq1.remove();
            setCurrentTime(Math.max(currentTime, temp.arrivalTime));
            if (temp.pages.memoryReferences.isEmpty()) {
                continue;
            }

            if (temp.isPageFault()) {
                setBlockTime(Math.max(blockTime, currentTime) + Constants.DiskTime);
                temp.setArrivalTime(blockTime);
                pq1.add(temp);
                temp.faultOccured();
            } else {
                temp.noFault();
                incCurrentTime();

                while (!temp.pages.memoryReferences.isEmpty() && !temp.isPageFault()) {
                    incCurrentTime();
                    temp.noFault();
                }

                if (temp.pages.memoryReferences.isEmpty()) {
                    updateCalculation(temp);
                }

                if (!temp.pages.memoryReferences.isEmpty() && temp.isPageFault()) {
                    setBlockTime(Math.max(blockTime, currentTime) + Constants.DiskTime);
                    temp.setArrivalTime(blockTime);
                    pq1.add(temp);
                    temp.faultOccured();
                }
            }

        }

        avgWaiting = totalWaitingTime / (double) process;
        avgTurnAround = totalTurnAround / (double) process;
        System.out.printf("FCFS %.2f, %.2f, ", avgWaiting, avgTurnAround);
        for (int i = 0; i < process; i++) {
            pageFault[i] = allProcess[i].numFaults;
        }
    }

}
