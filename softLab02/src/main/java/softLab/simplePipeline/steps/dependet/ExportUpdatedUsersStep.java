package softLab.simplePipeline.steps.dependet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import softLab.entities.User;
import softLab.simplePipeline.context.Context;
import softLab.simplePipeline.IPipelineStep;
import softLab.utils.JsonWrite;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * -----------------------------------------
 * IMPORTANT! before calling this step
 * you need to call {@link GenerateUsernameStep}
 * -----------------------------------------
 * step writes new user data in associated file
 */

public class ExportUpdatedUsersStep implements IPipelineStep {
    private final String pathToFile;

    public ExportUpdatedUsersStep(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public void execute(Context context) {
        List<User> userList = context.getAllUsers();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        File file = new File(pathToFile);
        try {
            JsonWrite.updateUsers(file.getAbsolutePath(), userList);
        } catch (IOException e) {
            System.out.println("Failed to update users list at" + file.getName());
            e.printStackTrace();
        }

    }
}
