package softLab.simplePipeline;

import softLab.simplePipeline.context.Context;
import softLab.simplePipeline.steps.independet.AverageAgeStep;
import softLab.simplePipeline.steps.independet.CheckUsernameStep;
import softLab.simplePipeline.steps.dependet.ExportUpdatedUsersStep;
import softLab.simplePipeline.steps.dependet.GenerateUsernameStep;
import softLab.simplePipeline.steps.independet.PopularNameStep;
import softLab.simplePipeline.steps.independet.UsersCountStep;

/**
 * Factory class with pipeline examples
 */

public class PipelineFactory {

    public static void generateUsernames(Context context){
        Pipeline pipeline = new Pipeline();
        pipeline.addStep(new CheckUsernameStep());
        pipeline.addStep(new GenerateUsernameStep());
        pipeline.addStep(new ExportUpdatedUsersStep("softlab02/src/main/java/softLab/simplePipeline/simplePipelineData/updated_users.json"));
        pipeline.execute(context);
    }

    public static void generateReport(Context context){
        Pipeline pipeline = new Pipeline();
        pipeline.addStep(new UsersCountStep());
        pipeline.addStep(new AverageAgeStep());
        pipeline.addStep(new PopularNameStep());
        pipeline.execute(context);
    }
}
