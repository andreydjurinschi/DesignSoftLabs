package lab01.softlab.entities;

import jakarta.persistence.*;

/**
 * User domain model
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private int age;

    private float rating;

    private Role role;

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getRating() {
        return rating;
    }
    public Role getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
