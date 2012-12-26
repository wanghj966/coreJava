package stream.ObjectStream;

/**
 * Created with IntelliJ IDEA.
 * User: wanghj
 * Date: 12-12-26
 * Time: 上午9:26
 * To change this template use File | Settings | File Templates.
 */
public class SerialCloneTest {
    public static void main(String[] args){
        Employee harry=new Employee("Harry Hacker",35000,1989,10,1);
        Employee harry2=(Employee)harry.clone();
        harry.raiseSalary(10);
        System.out.println(harry);
        System.out.println(harry2);
    }

}
