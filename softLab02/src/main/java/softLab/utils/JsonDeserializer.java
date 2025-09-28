package softLab.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import softLab.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class JsonDeserializer {

    public static List<User> deserialize() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<User> users = mapper.readValue(new File("softLab02/src/main/java/softLab/users.json"),
                new TypeReference<>() {});
        return users;
    }
}
