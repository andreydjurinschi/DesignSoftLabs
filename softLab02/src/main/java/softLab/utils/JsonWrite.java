package softLab.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import softLab.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonWrite {

    public static void updateUsers(String fileName, List<User> userList) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), userList);
    }

}
