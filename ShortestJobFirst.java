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
public class ShortestJobFirst {

    Process allProcess[];
    int process;
    int totalPageFault;
    double avgWaiting;
    double avgTurnAround;
    double totalWaitingTime = 0, totalTurnAround = 0;
    int pageFault[];
    int currentTime = 0;

    public ShortestJobFirst(Process[] allProcess, int process) {
        this.allProcess = allProcess;
        this.process = process;
        for (int i = 0; i < process; i++) {
            allProcess[i].waitingTime = Constants.DiskTime * allProcess[i].pageFaults();
            allProcess[i].executionTime = Constants.ExecTime * allProcess[i].memoryRefered;
            allProcess[i].totalTime = allProcess[i].waitingTime + allProcess[i].executionTime;
        }
        pageFault = new int[process];
        for (int i = 0; i < process; i++) {
            pageFault[i] = allProcess[i].pageFaults();
        }
        totalPageFault = 0;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public void incCurrentTime(int time) {
        this.currentTime += time;
    }

    public void setTotalWaitingTime(double totalWaitingTime) {
        this.totalWaitingTime += totalWaitingTime;
    }

    public void setTotalTurnAround(double totalTurnAround) {
        this.totalTurnAround += totalTurnAround;
    }

    public void updateCalculation(Process currProcess) {
        setTotalWaitingTime(currProcess.waitingTime + (currentTime - currProcess.arrivalTime));
        incCurrentTime(currProcess.totalTime);
        setTotalTurnAround(currentTime - currProcess.arrivalTime);
    }

    public void run() {
        Comparator<Process> arrivalTimeComparators = new Comparator<Process>() {
            @Override
            public int compare(Process s1, Process s2) {
                return s1.totalTime - s2.totalTime;
            }
        };

        Comparator<Process> totalTimeComparators = new Comparator<Process>() {
            @Override
            public int compare(Process s1, Process s2) {
                if (s1.arrivalTime != s2.arrivalTime) {
                    return s1.arrivalTime - s2.arrivalTime;
                } else {
                    return s1.totalTime - s2.totalTime;
                }
            }
        };

        for (int i = 0; i < process; i++) {
            totalPageFault += allProcess[i].pageFaults();
        }

        PriorityQueue<Process> pq1 = new PriorityQueue<>(totalTimeComparators);
        for (int i = 0; i < process; i++) {
            pq1.add(allProcess[i]);
        }

        PriorityQueue<Process> pq2 = new PriorityQueue<>(arrivalTimeComparators);

        int i = 0;
        while (!pq1.isEmpty()) {
            allProcess[i] = pq1.remove();
            i++;
        }

        currentTime = 0;
        totalWaitingTime = 0;
        totalTurnAround = 0;
        Process currProcess;
        setCurrentTime(allProcess[0].arrivalTime);
        i = 0;

        while (i < process) {
            while (i < process && currentTime >= allProcess[i].arrivalTime) {
                pq2.add(allProcess[i]);
                i++;
            }
            currProcess = pq2.remove();
            updateCalculation(currProcess);
            if (i < process && allProcess[i].arrivalTime > currentTime && pq2.isEmpty()) {
                setCurrentTime(allProcess[i].arrivalTime);
            }
        }

        while (!pq2.isEmpty()) {
            currProcess = pq2.remove();
            updateCalculation(currProcess);
        }

        avgWaiting = totalWaitingTime / (double) process;
        avgTurnAround = totalTurnAround / (double) process;
        System.out.printf("SJF %.2f, %.2f, ", avgWaiting, avgTurnAround);
    }

}
