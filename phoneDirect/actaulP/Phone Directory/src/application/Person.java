package application;

public class Person {
    String name;
    long number;
    int age;
    

    public Person(String name, long number) {
        this(name, 0, number);
    }
    
    public Person(String name, int age, long number) {
    	this.name = name;
    	this.age = age;
    	this.number = number;
    }
    
    public void setNumber(long number) {
    	this.number = number;
    }
    
    public void setAge(int age) {
    	this.age = age;
    }

    public String getName() {
        return name;
    }

   public int getAge() {
        return age;
    }

    public long getNumber() {
        return number;
    }
    
    @Override
    public String toString() {
    	return name + " " + age + " " + number;
    }
    
}