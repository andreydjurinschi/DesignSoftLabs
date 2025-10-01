package softLab.genericPipeline.context;

import softLab.entities.User;

import java.util.Collections;
import java.util.List;

public final class Context<T> {
    private List<T> data;

    private User user; // for creating stepss

    boolean isValid = true; // for creating steps



    public Context(List<T> data) {
        this.data = (data!=null) ? data : Collections.EMPTY_LIST;
    }

    /**
     * Constructor for user creating steps only
     * {@link User}
     * @param user
     */
    public Context(User user) {
        this.user = user;
    }

    public List<T> getData(){
        return data;
    }

    public int count(){
        return data.size();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Context<T> returnNew(List<T> data){
        return new Context<>(data);
    }

    public Context<User> returnNew(User data){
        return new Context<>(data);
    }

    public boolean isValid() {
        return isValid;
    }
    public void setValid(boolean valid) {
        isValid = valid;
    }
}

