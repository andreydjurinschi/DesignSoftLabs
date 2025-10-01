package softLab.genericPipeline;

import softLab.entities.User;
import softLab.genericPipeline.context.Context;
import softLab.genericPipeline.steps.dependet.PrintNewUsernamesStep;
import softLab.genericPipeline.steps.dependet.UsersWithoutUsernameStep;
import softLab.genericPipeline.steps.independet.oneEntitySteps.AddUserToFileStep;
import softLab.genericPipeline.steps.independet.oneEntitySteps.CheckIfUsernameIsNotNull;
import softLab.genericPipeline.steps.independet.oneEntitySteps.GenerateUsernameStep;
import softLab.genericPipeline.steps.independet.oneEntitySteps.NormalizeNameSurnameStep;
import softLab.genericPipeline.testSteps.InitDataStep;

import java.util.Collections;

public class PipelineFactory {

    public static void GenerateEmailsPipeline(){
        Context<Void> context = new Context<>(Collections.emptyList());
        Pipeline pipeline = new Pipeline();
        pipeline.addStep(new InitDataStep());
        pipeline.addStep(new UsersWithoutUsernameStep());
        pipeline.addStep(new GenerateUsernameStep());
        pipeline.addStep(new PrintNewUsernamesStep());
        pipeline.executeSteps(context);
    }
    public static void CreateNewUsersPipeline(User user){
        Context<User> context = new Context<>(user);
        Pipeline pipeline = new Pipeline();
        pipeline.addStep(new NormalizeNameSurnameStep());
        pipeline.addStep(new CheckIfUsernameIsNotNull());
        pipeline.addStep(new GenerateUsernameStep());
        pipeline.addStep(new AddUserToFileStep("softlab02/src/main/java/softLab/simplePipeline/simplePipelineData/updated_users.json"));
        pipeline.executeSteps(context);
    }
}
