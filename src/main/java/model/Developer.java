package model;

import java.util.List;

public class Developer implements Skill, Specialty {
    int id;
    String firstName, lastName;
    List<Skill> skills;
    Specialty specialty;


    public Developer(int id, String firstName, String lastName, List<Skill> skills, Specialty specialty) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
        this.specialty = specialty;
    }
}
