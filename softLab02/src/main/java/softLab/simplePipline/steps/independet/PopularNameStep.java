package softLab.simplePipline.steps.independet;

import softLab.simplePipline.context.Context;
import softLab.simplePipline.IPipelineStep;

import java.util.HashMap;
import java.util.Map;

/**
 * ------------------------------------------------
 * Independent step
 * ------------------------------------------------
 * Step that shows the popular name
 */

public class PopularNameStep implements IPipelineStep {
    @Override
    public void execute(Context context) {
        Map<String, Integer> map = new HashMap<>();
        for (var u : context.getAllUsers()) {
            map.merge(u.getName(), 1, Integer::sum);
        }
        String popularName = null;
        int max = 0;

        for (var entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                popularName = entry.getKey();
            }
        }
        System.out.println("Popular name: " + popularName + " (" + max + " users with that name)");
    }
}
