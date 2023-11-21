import java.util.Scanner;

class Process {
    int pid;
    int arrivalTime;
    int burstTime;

    public Process(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class SJF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no. of Processes: ");
        int n = sc.nextInt();

        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter Arrival time of process " + (i + 1) + ":");
            int arrivalTime = sc.nextInt();
            System.out.println("Enter Burst time of process " + (i + 1) + ":");
            int burstTime = sc.nextInt();
            processes[i] = new Process(i + 1, arrivalTime, burstTime);
        }

        // Sort processes based on burst time
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (processes[j].burstTime > processes[j + 1].burstTime) {
                    // Swap processes
                    Process temp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = temp;
                }
            }
        }

        int currentTime = 0;
        int completed = 0;

        System.out.println("Processes\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        while (completed < n) {
            for (int i = 0; i < n; i++) {
                if (processes[i].arrivalTime <= currentTime && processes[i].burstTime > 0) {
                    int remainingTime = processes[i].burstTime;
                    currentTime += remainingTime;
                    processes[i].burstTime = 0;
                    completed++;

                    int turnaroundTime = currentTime - processes[i].arrivalTime;
                    int waitingTime = turnaroundTime - remainingTime;

                    System.out.println(processes[i].pid + "\t\t" +
                            processes[i].arrivalTime + "\t\t" +
                            remainingTime + "\t\t" +
                            currentTime + "\t\t" +
                            turnaroundTime + "\t\t" +
                            waitingTime);
                }
            }
        }

        sc.close();
    }
}
