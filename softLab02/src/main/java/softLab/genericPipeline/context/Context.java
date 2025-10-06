package softLab.genericPipeline.context;

import softLab.entities.User;

import java.util.Collections;
import java.util.List;

public final class Context<T> {
    private List<T> data;

    private User user; // for creating steps

    private boolean isValid = true; // for creating steps

    private boolean isDone = false;

    public Context(List<T> data) {
        this.data = (data!=null) ? data : Collections.EMPTY_LIST;
    }

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

/*    public Context<T> returnNew(List<T> data){
        return new Context<>(data);
    }*/

    public Context<User> returnNew(User data){
        return new Context<>(data);
    }

    public boolean isValid() {
        return isValid;
    }
    public void setValid(boolean valid) {
        isValid = valid;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}

