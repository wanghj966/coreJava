package stream.ObjectStream;

import java.io.*;
import java.util.*;
public class ObjectStreamTest {
    public static void main(String[] args){
        Manager carl=new Manager("Carl Cracker",75000,1987,12,15);
        Employee harry=new Employee("Harry Hacker",50000,1989,10,3);
        carl.setSecretary(harry);
        Manager tony=new Manager("Tony Tester",40000,1990,3,17);
        tony.setSecretary(harry);
        Employee[] staff=new Employee[3];
        staff[0]= carl;
        staff[1]=harry;
        staff[2]=tony;
        try{
            ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("employee.dat"));
            out.writeObject(staff);
            out.close();
            //retrieve
            ObjectInputStream in=new ObjectInputStream(new FileInputStream("employee.dat"));
            Employee[] newStaff=(Employee[])in.readObject();
            in.close();

            //raise secretary's salary
            newStaff[1].raiseSalary(10);
            for(Employee e:newStaff){
                System.out.println(e);
            }
        }catch (Exception e){
          e.printStackTrace();
        }
    }
}
