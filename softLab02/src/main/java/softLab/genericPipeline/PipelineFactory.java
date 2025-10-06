package softLab.genericPipeline;

import softLab.entities.User;
import softLab.genericPipeline.context.Context;
import softLab.genericPipeline.steps.dependet.PrintNewUsernamesStep;
import softLab.genericPipeline.steps.dependet.UsersWithoutUsernameStep;
import softLab.genericPipeline.steps.independet.InitDataStep;
import softLab.genericPipeline.steps.oneEntitySteps.AddUserToFileStep;
import softLab.genericPipeline.steps.oneEntitySteps.CheckIfUsernameIsNotNull;
import softLab.genericPipeline.steps.oneEntitySteps.GenerateUsernameStep;
import softLab.genericPipeline.steps.oneEntitySteps.NormalizeNameSurnameStep;
import softLab.visitor.Visitor;

import java.util.Collections;

public class PipelineFactory {

    public static void GenerateEmailsPipeline(){
        Visitor visitor = new Visitor();
        Context<Void> context = new Context<>(Collections.emptyList());
        Pipeline pipeline = new Pipeline();
        pipeline.addStep(new InitDataStep());
        pipeline.addStep(new UsersWithoutUsernameStep());
        pipeline.addStep(new softLab.genericPipeline.steps.dependet.GenerateUsernameStep());
        pipeline.addStep(new PrintNewUsernamesStep());
        pipeline.executeSteps(context);
        System.out.println(pipeline.printStepsLog(visitor, context));
    }

    public static void CreateNewUsersPipeline(User user){
        Visitor visitor = new Visitor();
        Context<User> context = new Context<>(user);
        Pipeline pipeline = new Pipeline();
        pipeline.addStep(new NormalizeNameSurnameStep());
        pipeline.addStep(new CheckIfUsernameIsNotNull());
        pipeline.addStep(new GenerateUsernameStep());
        pipeline.addStep(new AddUserToFileStep("softLab02/src/main/java/softLab/pipelineData/updated_users.json"));
        pipeline.executeSteps(context);
        System.out.println(pipeline.printStepsLog(visitor, context));
    }
}
