/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import javafx.util.Pair;

/**
 *
 * @author mashrur
 * 
 * Name: Mashrur Rashik
 * Roll: 29
 * 
 */
public class RoundRobin {

    RRProcess allProcess[];
    int process;
    int totalPageFault;
    double totalWaitingTime;
    double totalTurnAround;
    int pageFaults[];
    double avgWaiting;
    double avgTurnAround;
    int pageFault[];
    int currentTime = 0;
    int blockTime = 0;

    public RoundRobin(RRProcess[] allProcess, int process) {
        this.allProcess = allProcess;
        this.process = process;
        for (int i = 0; i < process; i++) {
            allProcess[i].waitingTime = Constants.DiskTime * allProcess[i].numFaults;
            allProcess[i].executionTime = Constants.ExecTime * allProcess[i].memoryRefered;
            allProcess[i].totalTime = allProcess[i].waitingTime + allProcess[i].executionTime;
        }
        pageFaults = new int[process];
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

    public void setBlockTime(int blockTime) {
        this.blockTime = blockTime;
    }

    public void incBlockTime(int time) {
        this.blockTime += time;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public void incCurrentTime(int time) {
        this.currentTime += time;
    }

    public void updateCalculation(RRProcess temp, int currentTime) {
        setTotalWaitingTime(currentTime - temp.actualArrival - (Constants.ExecTime * temp.memoryRefered));
        setTotalTurnAround(currentTime - temp.actualArrival);
    }

    public void updatePageFault() {
        setBlockTime(Math.max(blockTime, currentTime) + Constants.DiskTime);
    }

    public void run() {
        Comparator<RRProcess> arrivalTimeComparators = new Comparator<RRProcess>() {
            @Override
            public int compare(RRProcess s1, RRProcess s2) {
                if (s1.arrivalTime >= s2.arrivalTime) {
                    return 1;
                }
                return s1.arrivalTime - s2.arrivalTime;
            }
        };

        PriorityQueue<RRProcess> pq1 = new PriorityQueue<>(arrivalTimeComparators);
        for (int i = 0; i < process; i++) {
            pq1.add(allProcess[i]);
        }

        setCurrentTime(pq1.peek().arrivalTime);
        setBlockTime(pq1.peek().arrivalTime);

        while (!pq1.isEmpty()) {
            RRProcess temp = pq1.remove();
            setCurrentTime(Math.max(currentTime, temp.arrivalTime));
            if (temp.pages.memoryReference.isEmpty()) {
                continue;
            }

            int currentQuanta = RRProcess.timeQuanta;
            while (currentQuanta > 0) {
                if (temp.isPageFault()) {
                    updatePageFault();
                    temp.setArrivalTime(blockTime);
                    temp.faultOccured();
                    pq1.add(temp);
                    break;
                } else {
                    if (temp.getRemTime() >= currentQuanta) {
                        temp.setRemTime(temp.getRemTime() - currentQuanta);
                        incCurrentTime(currentQuanta);
                        temp.setArrivalTime(currentTime);

                        if (temp.getRemTime() == 0) {
                            temp.noFault();
                            temp.setRemTime(Constants.ExecTime);
                        }

                        if (temp.pages.memoryReference.isEmpty()) {
                            updateCalculation(temp, currentTime);
                            break;
                        }

                        pq1.add(temp);
                        break;
                    } else {
                        temp.noFault();
                        incCurrentTime(temp.getRemTime());
                        currentQuanta -= temp.getRemTime();
                        temp.setRemTime(Constants.ExecTime);
                    }

                    if (temp.pages.memoryReference.isEmpty()) {
                        updateCalculation(temp, currentTime);
                        break;
                    }

                }
            }

        }

        avgWaiting = totalWaitingTime / (double) process;
        avgTurnAround = totalTurnAround / (double) process;
        System.out.printf("RRS %.2f, %.2f, ", avgWaiting, avgTurnAround);
        for (int i = 0; i < process; i++) {
            pageFault[i] = allProcess[i].numFaults;
        }
    }
}
