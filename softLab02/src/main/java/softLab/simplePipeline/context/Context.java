package softLab.simplePipeline.context;

import softLab.entities.User;
import softLab.utils.JsonRead;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Context {

    private final List<User> allUsers;
    private List<User> usersWithoutUsernames = new ArrayList<>();

    public Context() throws IOException {
        this.allUsers = JsonRead.deserialize();
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
