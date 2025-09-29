package softLab.simplePipline.steps.independet;

import softLab.simplePipline.Context;
import softLab.simplePipline.IPipelineStep;

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
