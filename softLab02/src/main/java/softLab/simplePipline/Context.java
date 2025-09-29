package softLab.simplePipline;

import softLab.entities.User;
import softLab.utils.JsonDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Context {

    private List<User> allUsers;
    private List<User> usersWithoutUsernames = new ArrayList<>();

    public Context() throws IOException {
        this.allUsers = JsonDeserializer.deserialize();
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public List<User> getUsersWithoutUsernames() {
        return usersWithoutUsernames;
    }

    public void setUsersWithoutUsernames(List<User> usersWithoutUsernames) {
        this.usersWithoutUsernames = usersWithoutUsernames;
    }
}
