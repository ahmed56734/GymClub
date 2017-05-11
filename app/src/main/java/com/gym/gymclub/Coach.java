package com.gym.gymclub;

/**
 * Created by ahmed on 5/8/17.
 */

public class Coach extends Person {

    private String salary;

    public Coach(int id, String name, String telephone, String email, String birthDate, String gender, String picturePath, String salary) {
        super(id, name, telephone, email, birthDate, gender, picturePath);
        this.salary = salary;
    }

    public String getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getSalary();
    }
}
