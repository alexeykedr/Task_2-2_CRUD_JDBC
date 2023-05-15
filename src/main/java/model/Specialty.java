package model;

import java.util.Objects;

public class Specialty {
    private Long id;
    private String name;
    private long skillId;

    public Specialty(Long id, String name) {
        this.id = id;
        this.name = name;
        this.skillId = Long.parseLong(null);
    }

    public Specialty(String name, long skillId) {
        this.id = Long.parseLong(null);
        this.name = name;
        this.skillId = skillId;
    }

    public Specialty(Long id, String name, long skillId) {
        this.id = id;
        this.name = name;
        this.skillId = skillId;
    }

    public Specialty() {
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

    public long getSkillId() {
        return skillId;
    }

    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Specialty specialty)) return false;
        return Objects.equals(getId(), specialty.getId()) && Objects.equals(getName(), specialty.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
