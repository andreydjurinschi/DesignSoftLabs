package softLab.simplePipline;

import softLab.simplePipline.steps.independet.AverageAgeStep;
import softLab.simplePipline.steps.independet.CheckUsernameStep;
import softLab.simplePipline.steps.dependet.ExportUpdatedUsersStep;
import softLab.simplePipline.steps.dependet.GenerateUsernameStep;
import softLab.simplePipline.steps.independet.PopularNameStep;
import softLab.simplePipline.steps.independet.UsersCountStep;

/**
 * Factory class with pipeline examples
 */

public class PipelineFactory {

    public static void generateUsernames(Context context){
        Pipeline pipeline = new Pipeline();
        pipeline.addStep(new UsersCountStep());
        pipeline.addStep(new CheckUsernameStep());
        pipeline.addStep(new GenerateUsernameStep());
        pipeline.addStep(new ExportUpdatedUsersStep("softLab02/src/main/java/softLab/updated_users.json"));
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
