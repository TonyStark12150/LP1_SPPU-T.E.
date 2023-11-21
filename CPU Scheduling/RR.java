import java.util.Scanner;

class proc {
    int pid;
    int at;
    int bt;
    int ct;
    int wt;
    int tat;

    proc(int pid, int at, int bt) 
    {
        this.at = at;
        this.pid = pid;
        this.bt = bt;
        this.ct = this.at + this.bt;
        this.tat = this.ct - this.at;
        this.wt = this.tat - this.bt;
    }
}

public class RR {
    void sort(int array[]) {
        int temp = 0;
        for (int index = 0; index < array.length; index++) {
            for (int j = 0; j < array.length - (index + 1); j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no. of processes: ");
        int n = sc.nextInt();
        int avgwt = 0;
        int avgtat = 0;
        proc p[] = new proc[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter arrival time of process " + (i + 1) + ":");
            int at = sc.nextInt();
            System.out.println("Enter burst time of process " + (i + 1) + ":");
            int bt = sc.nextInt();
            int pid = i + 1;
            p[i] = new proc(pid, at, bt);
            avgtat += p[i].tat;
            avgwt += p[i].wt;
        }
        for (int i = 0; i < n; i++) 
        {
            System.out.println(p[i].pid + "\t" + p[i].at + "\t" + p[i].bt + "\t" + p[i].ct + "\t" + p[i].tat + "\t"
                    + p[i].wt);
        }
        System.out.println("avg wt: " + avgwt / n + "\navg tat: " + avgtat / n + "\n");

        sc.close();
    }
}
