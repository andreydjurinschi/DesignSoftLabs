package softLab.simplePipline.steps;

import softLab.simplePipline.Context;
import softLab.simplePipline.IPipelineStep;

/**
 * Independent step that
 * shows the user count
 */
public class UsersCountStep implements IPipelineStep {

    @Override
    public void execute(Context context) {
        int userCount = context.getAllUsers().size();
        System.out.println("Users count: " + userCount);
    }
}
