/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author mashrur
 * 
 * Name: Mashrur Rashik
 * Roll: 29
 * 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        int process = 0, timeQuanta = 0;
        int ind = 0;
        FileInputStream fstream = new FileInputStream("in.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        Process allProcess[] = new Process[1000];
        FCFSprocess allProcessFCFS[] = new FCFSprocess[1000];
        RRProcess allProcessRR[] = new RRProcess[1000];

        String strLine;

        while ((strLine = br.readLine()) != null) {
            strLine = strLine.replaceAll("\\s+", "");
            String[] stringArray = strLine.split(",");
            if (ind == 0) {
                Integer tmp1 = Integer.parseInt(stringArray[0]);
                process = tmp1.intValue();
                Integer tmp2 = Integer.parseInt(stringArray[1]);
                timeQuanta = tmp2.intValue();
                FCFSprocess.timeQuanta = timeQuanta;
                RRProcess.timeQuanta = timeQuanta;
            } else {
                int processId, numberPage;
                int arrivalTime;
                Integer tmp1 = Integer.parseInt(stringArray[0]);
                processId = tmp1.intValue();
                Integer tmp2 = Integer.parseInt(stringArray[1]);
                numberPage = tmp2.intValue();
                Integer tmp3 = Integer.parseInt(stringArray[2]);
                arrivalTime = tmp3.intValue();

                allProcess[ind - 1] = new Process(processId, numberPage, arrivalTime);
                allProcessFCFS[ind - 1] = new FCFSprocess(processId, numberPage, arrivalTime);
                allProcessRR[ind - 1] = new RRProcess(processId, numberPage, arrivalTime);
                for (int i = 3; i < stringArray.length; i++) {
                    Integer tmp4 = Integer.parseInt(stringArray[i]);
                    allProcess[ind - 1].referenceMemory(tmp4.intValue());
                    allProcessFCFS[ind - 1].referenceMemory(tmp4.intValue());
                    allProcessRR[ind - 1].referenceMemory(tmp4.intValue());
                }
            }
            ind++;
        }

        int totalFaultFCFS = 0, totalFaultSJF = 0, totalFaultRRS = 0;
        double gainFCFS = 0.0, gainSJF = 0.0, gainRRS = 0.0;
        double fsturn = 0, sturn = 0, rrsturn = 0;

        FirstComeFirstServe fs = new FirstComeFirstServe(allProcessFCFS, process);
        fs.run();
        for (int i = 0; i < process; i++) {
            totalFaultFCFS += fs.pageFault[i];
        }
        System.out.print(totalFaultFCFS + ", ");
        for (int i = 0; i < process; i++) {
            System.out.print(fs.pageFault[i] + ", ");
        }
        fsturn = fs.totalTurnAround;
        gainFCFS = ((fsturn - fsturn) / fsturn) * 100.0;
        System.out.printf("%.2f", gainFCFS);
        System.out.println("");
        
        ShortestJobFirst sj = new ShortestJobFirst(allProcess, process);
        sj.run();

        for (int i = 0; i < process; i++) {
            totalFaultSJF += sj.pageFault[i];
        }
        System.out.print(totalFaultSJF + ", ");
        for (int i = 0; i < process; i++) {
            System.out.print(sj.pageFault[i] + ", ");
        }
        sturn = sj.totalTurnAround;
        gainSJF = ((fsturn - sturn) / sturn) * 100.0;
        System.out.printf("%.2f", gainSJF);
        System.out.println("");
        
        RoundRobin rr = new RoundRobin(allProcessRR, process);
        rr.run();
        for (int i = 0; i < process; i++) {
            totalFaultRRS += rr.pageFault[i];
        }
        System.out.print(totalFaultRRS + ", ");
        for (int i = 0; i < process; i++) {
            System.out.print(rr.pageFault[i] + ", ");
        }
        rrsturn = rr.totalTurnAround;
        gainRRS = ((fsturn - rrsturn) / rrsturn) * 100.0;
        
        System.out.printf("%.2f", gainRRS);
        System.out.println("");
        fstream.close();

    }

}
