package softLab.simplePipeline.steps.independet;

import softLab.simplePipeline.context.Context;
import softLab.simplePipeline.IPipelineStep;

/**
 * ------------------------------------------------
 * Independent step
 * ------------------------------------------------
 * step that shows user count
 */
public class UsersCountStep implements IPipelineStep {

    @Override
    public void execute(Context context) {
        int userCount = context.getAllUsers().size();
        System.out.println("Users count: " + userCount);
    }
}
