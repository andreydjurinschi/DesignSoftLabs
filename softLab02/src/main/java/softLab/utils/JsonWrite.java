package softLab.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    public static void addUser(String fileName, User user) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            File file = new File(fileName);
            ArrayNode arrayNode;

            if (file.exists()) {
                arrayNode = (ArrayNode) mapper.readTree(file);
            } else {
                arrayNode = mapper.createArrayNode();
            }

            ObjectNode userNode = mapper.createObjectNode();
            userNode.put("NEW_USER", "NEW_USER");
            userNode.put("name", user.getName());
            userNode.put("surname", user.getSurname());
            userNode.put("dateOfBirth", user.getDateOfBirth().toString());
            userNode.put("username", user.getUsername());

            arrayNode.add(userNode);

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, arrayNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
