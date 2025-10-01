package softLab.genericPipeline;

import softLab.genericPipeline.context.Context;
import softLab.genericPipeline.steps.independet.oneEntitySteps.AddUserToFileStep;
import softLab.genericPipeline.steps.independet.oneEntitySteps.CheckIfUsernameIsNotNull;
import softLab.genericPipeline.steps.independet.oneEntitySteps.GenerateUsernameStep;
import softLab.genericPipeline.steps.independet.oneEntitySteps.NormalizeNameSurnameStep;
import softLab.visitor.IVisitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pipeline{
    private List<IPipelineStep<?, ? >> steps = new ArrayList<>();

    public void addStep(IPipelineStep<?, ? > step){
        steps.add(step);
    }

    public <T> Context<T> executeSteps(Context<?> data){
        Context<?> intermediateData = data;
        for(IPipelineStep<?, ? > step : steps){
            intermediateData = ((IPipelineStep<Object, Object>) step).Execute((Context<Object>) intermediateData);
        }
        return (Context<T>) intermediateData;
    }
    public String printStepsLog(IVisitor visitor){
        StringBuilder builder = new StringBuilder();
        for(IPipelineStep<?, ?> step : steps){
            builder.append(stepDescription(step, visitor));
        }
        return builder.toString();
    }

    private String stepDescription(IPipelineStep<?, ?> step, IVisitor visitor){
        if(step instanceof NormalizeNameSurnameStep normalizeStep){
            return normalizeStep.accept(visitor);
        } else if(step instanceof CheckIfUsernameIsNotNull checkStep){
            return checkStep.accept(visitor);
        } else if(step instanceof GenerateUsernameStep genStep){
            return genStep.accept(visitor);
        } else if(step instanceof AddUserToFileStep addStep){
            return addStep.accept(visitor);
        }
        return "";
    }
}
