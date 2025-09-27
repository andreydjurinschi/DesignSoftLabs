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

    public UserFieldMask(){
        Id = false;
        name = false;
        age = false;
        rating = false;
        role = false;
    }

    public boolean isAnySet(){
        boolean[] fields = {Id, name, age, rating, role};
        for(boolean f : fields){
            if(f){
                return true;
            }
        }
        return false;
    }

    public static UserFieldMask setAllBut(UserFieldMask mask, String... requiredFields) {
        UserFieldMask result = new UserFieldMask();
        result.Id = mask.Id;
        result.name = mask.name;
        result.age = mask.age;
        result.rating = mask.rating;
        result.role = mask.role;
        for (String field : requiredFields) {
            switch (field) {
                case "id" -> result.Id = true;
                case "name" -> result.name = true;
                case "age" -> result.age = true;
                case "rating" -> result.rating = true;
                case "role" -> result.role = true;
            }
        }
        return result;
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
