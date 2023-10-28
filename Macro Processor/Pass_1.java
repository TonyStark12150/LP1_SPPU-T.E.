import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;



public class Pass_1
{
    Scanner sc =new Scanner(System.in);
    String MDT[]={};
    String MNT[]={};
    String ALA[]={};
    public static void main(String args[]) throws Exception
    {
        int mntc=1;
        int mdtc=1;
        int ind=1;
        int flag=0;
        String line;
        String MACRO=null;

        File file_in = new File("Input_macro.txt");
        File file_out = new File("Inter.txt");
        BufferedReader read=new BufferedReader(new FileReader(file_in));
        BufferedWriter write_out=new BufferedWriter(new FileWriter(file_out));
        BufferedWriter write_mnt=new BufferedWriter(new FileWriter("mnt.txt"));
        BufferedWriter write_mdt=new BufferedWriter(new FileWriter("mdt.txt"));
        BufferedWriter write_ala=new BufferedWriter(new FileWriter("ala.txt"));
        
        while((line=read.readLine())!=null)
        {
            String parts[]=line.split("\\s+");
            if(parts[0].equalsIgnoreCase("MACRO"))
            {
                flag=1;
                line=read.readLine();
                parts=line.split("\\s+");
                MACRO=parts[0];
                if(parts.length<=1)
                {
                    write_mnt.write(mntc+"\t"+MACRO+"\n");
                    mntc++;
                }
                for (int i = 1; i < parts.length; i++)
                {
                    parts[i]=parts[i].replaceAll("[&,]","");
                    write_ala.write(ind+ "\t" + parts[i] + "\n");
                }
            }
            else if(parts[0].equalsIgnoreCase("MEND"))
            {
                write_mdt.write(mdtc + "\t" + line + "\n");
                flag = 0;
                mdtc++;
            }
            else if(flag==1)
            {
                for (int i = 0; i < parts.length; i++) 
                {
                    if (parts[i].contains("&")) 
                    {
                        parts[i] = parts[i].replaceAll("[&,]", "");
                        write_mdt.write(mdtc + "\t" + parts[i] + "\n");
                    } 
                    else
                    {
                        write_mdt.write(mdtc + "\t" + parts[i] + "\n");
                    }
                    mdtc++;
                }
                write_mdt.write("\n");               
            }
            else 
            {
                write_out.write(line + "\n");
            }
        }
        read.close();
        write_mdt.close();
        write_mnt.close();
        write_ala.close();
        write_out.close();
        System.out.println("PASS 1 Done ...............");
    }
}
