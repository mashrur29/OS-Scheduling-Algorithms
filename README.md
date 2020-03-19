# OS-Scheduling-Algorithms

Determine the number of page faults, turnaround time, average waiting time, performance gain compared to FCFS for the following CPU schedule algorithms
  • FCFS
  • SJF (shortest job first)
  • Round-Robin Scheduling
and performance gain such as for SJF is defined as:
PFgain = ((FCFSturn_around_time - SJFturn_around_time)/SJFturn_around_time) × 100

For each of the processes, the number of frames allocated is one-third of the number of the page of the process. For example, OS allocates three frames for a process with nine pages. Use ceiling function to calculate the number of frames for a process. In the long-term scheduling, the OS uses SSTF which takes about 50ns to serve a request for a block of data or information. For simplicity, the request for each process for the page fault is served sequentially from the waiting queue. The block size is 512 bytes which are the same as the sector size and page size. Assume that the computer has sufficient memory to serve all the listed process and context switching takes 0ns. OS takes 20ns to move 1024 bytes from the kernel space to the user space and update page table. Further, the page table contains valid and invalid bits for the pages. The OS uses the LRU scheme for page replacement, and block a process (in the waiting queue) until it (kernel) serves page fault. Also, assume that the memory address is 32-bits (4GB) where the last 9-bit (512 bytes) is used for internal frame address (or displacement), and rest 23-bit is used for frame number.

# Important Note: For each of the memory references, a block of instructions are executed starting from this reference address and it takes roughly 30ns.

# Input

In the first line, the input contains number of process, time quanta (ns) and next several lines where
each of the lines contains process id, number of page,arrival time (ns), and a series of memory references
which are as following 1,11,12,134,345,450,920,5000,4123,2345,20,569,18,74,3004,3234, and so on. The
real test data may contains more than 500 processes.

# Output
The first line contains scheduling algorithm (such as FCFS), the average waiting time (two digits decimal),
average turn around time (two digits decimal), the total number of page faults (Integer), number of page
faults (Integer) for each process, and performance gain (two digits decimal) for each of the scheduling
algorithm compared to FCFS. Consider time unit as ns.

# Sample Input
5, 10 <br/> 
1, 5, 50, 1, 34, 56, 123, 657, 234, 1033, 23, 590, 32, 1620, 2013, 35, 134, 29, 360, 790 <br/>
2, 9, 0, 13, 314, 516, 3123, 657, 2234, 1033, 3323, 590, 302, 1620, 2013, 135, 2134, 3329, 4300, 790, 3224 <br/>
12, 10, 100, 13, 314, 519, 3223, 557, 1234, 1033, 2323, 1590, 3302, 2420, 2114, 135, 2334, 4329, 4200, 1790, 32

# Sample Output
FCFS 23.50, 134.45, 35, 7, 13, 15, 0.00 <br/> 
SJF 18.50, 100.34, 30, 8, 12, 10, 33.99 <br/> 
RRS 20.00, 120.00, 32, 7, 13, 12, 12.04
**Alert: Space between the numbers.
Note: the sample input and output may not represent the correct calculation.
