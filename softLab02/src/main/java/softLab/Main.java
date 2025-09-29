package softLab;

import softLab.simplePipline.Context;
import softLab.simplePipline.Pipeline;
import softLab.simplePipline.steps.CheckUsernameStep;
import softLab.simplePipline.steps.ExportUpdatedUsersStep;
import softLab.simplePipline.steps.GenerateUsernameStep;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Context context = new Context();
        Pipeline pipeline = new Pipeline();
        pipeline.addStep(new CheckUsernameStep());
        pipeline.addStep(new GenerateUsernameStep());
        pipeline.addStep(new ExportUpdatedUsersStep("softLab02/src/main/java/softLab/updated_users.json"));
        pipeline.execute(context);
    }
}