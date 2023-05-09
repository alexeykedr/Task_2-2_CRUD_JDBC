package model;

import java.util.Objects;

public class Skill {
    private Long id;
    private String name;
    private Developer developer;

    public Skill(Long id, String name, Developer developer) {
        this.id = id;
        this.name = name;
        this.developer = developer;
    }



    public Skill(Long id, String name) {
        this.id = id;
        this.name = name;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skill skill)) return false;
        return Objects.equals(getId(), skill.getId()) && Objects.equals(getName(), skill.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public boolean isNew() {
        return getId() == null;
    }

    public Developer getDeveloper() {
        return developer;
    }
}
