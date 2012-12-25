package stream.RandomFile;
import java.io.*;

public class RandomFileTest {
        public static void main(String[] args){
            Employee[] staff=new Employee[3];
            staff[0]=new Employee("Carl Cracker",75000,1987,12,15);
            staff[1]=new  Employee("Harry Hacker",50000,1989,10,3);
            staff[2]=new  Employee("Tony Tester",40000,1990,3,17);

            try{
                DataOutputStream out=new DataOutputStream(new FileOutputStream("employee.dat"));
                for(Employee e:staff)
                e.writeData(out);
                out.close();

                RandomAccessFile in=new RandomAccessFile("employee.dat","r");
                //compute the size;
                int n=(int)(in.length()/Employee.Record_SIZE) ;
                Employee[] newStaff=new Employee[n];
                for(int i=n-1;i>=0;i--){
                    newStaff[i]=new Employee();
                    in.seek(i*Employee.Record_SIZE);
                    newStaff[i].readData(in);
                }
                in.close();
                for(Employee e:newStaff){
                    System.out.println(e);
                }

            }catch (IOException e){
                e.printStackTrace();
            }
        }


}
