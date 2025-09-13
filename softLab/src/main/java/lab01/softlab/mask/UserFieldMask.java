package lab01.softlab.mask;

/**
 * Field mask class
 */
public class UserFieldMask {

    private boolean Id;

    private boolean name;

    private boolean age;

    private boolean rating;

    private boolean role;

    public boolean isId() {
        return Id;
    }

    public void setId(boolean id) {
        Id = id;
    }

    public boolean isName() {
        return name;
    }

    public void setName(boolean name) {
        this.name = name;
    }

    public boolean isAge() {
        return age;
    }

    public void setAge(boolean age) {
        this.age = age;
    }

    public boolean isRating() {
        return rating;
    }

    public void setRating(boolean rating) {
        this.rating = rating;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }
}
