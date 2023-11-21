import java.util.*;

class process
{
    int pid,at,bt;
    process(int pid,int at,int bt)
    {
        this.pid=pid;
        this.at=at;
        this.bt=bt;
    }
}

public class FCFS
{
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter no. of Processes : ");
        int n=sc.nextInt();
        int ct[]=new int[n];
        int tat[]=new int[n];
        int wt[]=new int[n];
        int avgtat=0,avgwt=0;
        process[]P=new process[n];
        for (int i = 0; i < n; i++) 
        {   
            System.out.println("Enter Arrival time of process "+(i+1)+": ");
            int at=sc.nextInt();
            System.out.println("Enter Burst time of process "+(i+1)+": ");
            int bt=sc.nextInt();
            int pid=i+1;
            P[i]=new process(pid,at,bt);
        }
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < n-(i+1); j++) 
            {
                if(P[j].at>P[j+1].at)
                {
                    process temp=P[j];
                    P[j]=P[j+1];
                    P[j+1]=temp;
                }
            }
        }

        for (int i = 0; i < n; i++) 
        {
            if(i==0)
            {
                ct[i]=P[i].at+P[i].bt;
            }
            else if(P[i].at>ct[i-1])
            {
                ct[i]=P[i].at+P[i].bt;
            }
            else
            {
                ct[i]=P[i].bt+ct[i-1];
            }

            tat[i]=ct[i]-P[i].at;
            wt[i]=tat[i]-P[i].bt;

            avgtat+=tat[i];
            avgwt+=wt[i];
        }

        System.out.println("Process id    Arrival   Burst   Complete  Turn   Wait");
        for (int i = 0; i < n; i++) 
        {
            System.out.println(P[i].pid+"\t"+P[i].at+"\t"+P[i].bt+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
        }

        System.out.println("\n Average Turn around time : "+avgtat/n);
        System.out.println("\n Average Waiting time : "+avgwt/n);

        sc.close();
    }
}