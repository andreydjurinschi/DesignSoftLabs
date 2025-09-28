package softLab.pipline;

import java.util.ArrayList;
import java.util.List;

public final class Pipeline implements IPipelineStep {
    List<IPipelineStep> steps = new ArrayList<>();

    public void addStep(IPipelineStep step){
        steps.add(step);
    }

    @Override
    public Object Execute(Object context) {
        for(var s : steps){
            s.Execute(context);
        }
        return "HI";
    }
}
