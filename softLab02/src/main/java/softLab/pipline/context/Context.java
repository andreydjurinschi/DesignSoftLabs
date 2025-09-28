package softLab.pipline.context;

import softLab.entities.User;
import softLab.utils.JsonDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * context class
 */
public final class Context {

    private final List<User> users;

    public Context() throws IOException {
        try {
            users = JsonDeserializer.deserialize();
        } catch (IOException e) {
            throw new IOException("Problem reading source JSON file", e);
        }
    }

    public List<User> getUsers() {
        return users;
    }

}
