package model;

import java.util.Collections;
import java.util.List;

public class Developer implements Skill, Specialty {
    int id;
    String firstName, lastName;

    List<Skill> skills;
    //it's for getting List<Skill> from DB in ResultSet CRUDUtils
    List<String> skillsToString;

    Specialty specialty;
    // it's for getting Specialty from DB in ResultSet CRUDUtils
    String specialtyToString;


    public Developer(int id, String firstName, String lastName, List<String> skillsToString, String specialtyToString) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skillsToString = Collections.singletonList(skills.toString());
        this.specialtyToString = specialty.toString();

    }
}
