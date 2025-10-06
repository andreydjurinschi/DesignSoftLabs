package softLab.genericPipeline;

import softLab.genericPipeline.context.Context;
import softLab.genericPipeline.steps.dependet.PrintNewUsernamesStep;
import softLab.genericPipeline.steps.dependet.UsersWithoutUsernameStep;
import softLab.genericPipeline.steps.independet.InitDataStep;
import softLab.genericPipeline.steps.oneEntitySteps.AddUserToFileStep;
import softLab.genericPipeline.steps.oneEntitySteps.CheckIfUsernameIsNotNull;
import softLab.genericPipeline.steps.oneEntitySteps.GenerateUsernameStep;
import softLab.genericPipeline.steps.oneEntitySteps.NormalizeNameSurnameStep;
import softLab.visitor.Visitor;

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
            if(intermediateData.isDone()){
                System.out.println("Context stopped at " + step.getClass());
                break;
            }
            intermediateData = ((IPipelineStep<Object, Object>) step).Execute((Context<Object>) intermediateData);
        }
        return (Context<T>) intermediateData;
    }
    public String printStepsLog(Visitor visitor, Context<?> context){
        StringBuilder builder = new StringBuilder();
        for(IPipelineStep<?, ?> step : steps){
            if(context.isDone()){
                builder.append("Visitor skipped for step: ").append(step.getClass().getSimpleName()).append("\n");
                break;
            }
            builder.append(stepDescription(step, visitor));
        }
        return builder.toString();
    }

    private String stepDescription(IPipelineStep<?, ?> step, Visitor visitor){
        /* step s for one entity */
        if(step instanceof NormalizeNameSurnameStep normalizeStep){
            return normalizeStep.accept(visitor);
        } else if(step instanceof CheckIfUsernameIsNotNull checkStep){
            return checkStep.accept(visitor);
        } else if(step instanceof GenerateUsernameStep genStep){
            return genStep.accept(visitor);
        } else if(step instanceof AddUserToFileStep addStep){
            return addStep.accept(visitor);
        /* step s for list of entities */
        } else if(step instanceof InitDataStep dataSTep){
            return dataSTep.accept(visitor);
        } else if(step instanceof UsersWithoutUsernameStep usersSTep){
            return usersSTep.accept(visitor);
        } else if(step instanceof softLab.genericPipeline.steps.dependet.GenerateUsernameStep generateStep){
            return generateStep.accept(visitor);
        } else if(step instanceof PrintNewUsernamesStep printStep){
            return printStep.accept(visitor);
        }
        return "";
    }
}
