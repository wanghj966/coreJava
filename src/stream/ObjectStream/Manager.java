package stream.ObjectStream;

public class Manager extends Employee {
    public Manager(String n, double s, int year, int month, int day) {
        super(n, s, year, month, day);
        secretary=null;
    }

    public Manager() {
    }


    public Employee getSecretary() {
        return secretary;
    }

    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }

    @Override
    public String toString() {
        return super.toString()+"[secretary"+secretary+"]";
    }
    private Employee  secretary;
}
