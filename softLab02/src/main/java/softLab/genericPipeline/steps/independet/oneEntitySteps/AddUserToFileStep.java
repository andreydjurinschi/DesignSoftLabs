package softLab.genericPipeline.steps.independet.oneEntitySteps;

import softLab.entities.User;
import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;
import softLab.utils.JsonWrite;

public class AddUserToFileStep implements IPipelineStep<User, User> {
    private String fileName;

    public AddUserToFileStep(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Context<User> Execute(Context<User> context) {
        JsonWrite.addUser(fileName, context.getUser());
        System.out.println("Added user to the file: " + fileName);
        return context;
    }
}
