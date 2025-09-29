package softLab.simplePipline.steps.dependet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import softLab.entities.User;
import softLab.simplePipline.Context;
import softLab.simplePipline.IPipelineStep;
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

        try{
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, userList);
            System.out.println("Updated users exported to " + pathToFile);
        } catch (IOException e) {
            System.out.println("Error while writing updated users to " + pathToFile + "; " + e.getMessage());
        }
    }
}
