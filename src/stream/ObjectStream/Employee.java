package stream.ObjectStream;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Employee extends SerialClone {
    private  String name;
    private  double salary;
    private Date hireDay;

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

}
