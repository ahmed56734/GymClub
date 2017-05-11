package com.gym.gymclub;

/**
 * Created by ahmed on 5/10/17.
 */

public class Person {
    private int id;
    private String name;
    private String telephone;
    private String email;
    private String birthDate;
    private String gender;
    private String picturePath;
    private String age;

    public Person(int id, String name, String telephone, String email, String birthDate, String gender, String picturePath) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.picturePath = picturePath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        return getId() + " " + getName() + " " + getTelephone() + " " + getEmail() + " " + getBirthDate() + " " + getGender() + " " + getPicturePath();
    }
}
