package com.gym.gymclub;

/**
 * Created by ahmed on 5/8/17.
 */

public class Player extends Person{
    private String height;

    public Player(int id, String name, String telephone, String email, String birthDate, String gender, String picturePath, String height) {
        super(id, name, telephone, email, birthDate, gender, picturePath);
        this.height = height;
    }

    public String getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getHeight();
    }
}
