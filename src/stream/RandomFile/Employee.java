package stream.RandomFile;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Employee {
    private  String name;
    private  double salary;
    private Date hireDay;
    public static final int NAME_SIZE=40;
    public static final int Record_SIZE=2*NAME_SIZE+8+4+4+4;


    public Employee(){}
    public Employee(String n, double s, int year, int month, int day){
        name=n;
        salary=s;
        GregorianCalendar calendar=new GregorianCalendar(year,month-1,day);
        hireDay=calendar.getTime();
    }

    public Date getHireDay() {
        return hireDay;
    }


    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }
    public void raiseSalary(double byPercent){
        double raise=salary*byPercent/100;
        salary+=raise;
    }
    public String toString(){
         return getClass().getName()+"[name="+name+",salary="+salary+",hireDay="+hireDay+"]";
    }
    public void writeData(DataOutput out) throws IOException {
        DataIO.writeFixedString(name,NAME_SIZE,out);
        out.writeDouble(salary);

        GregorianCalendar calendar=new GregorianCalendar();
        calendar.setTime(hireDay);
        out.writeInt(calendar.get(Calendar.YEAR));
        out.writeInt(calendar.get(Calendar.MONTH)+1);
        out.writeInt(calendar.get(Calendar.DAY_OF_MONTH));
       }
    public void readData(DataInput in)throws IOException {
        name=DataIO.readFixedString(NAME_SIZE,in);
        salary=in.readDouble();
        int y=in.readInt();
        int m=in.readInt();
        int d=in.readInt();
        hireDay=new GregorianCalendar(y,m-1,d).getTime();
    }

}
