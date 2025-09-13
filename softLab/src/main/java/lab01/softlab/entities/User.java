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

    @Enumerated(EnumType.ORDINAL)
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
}
