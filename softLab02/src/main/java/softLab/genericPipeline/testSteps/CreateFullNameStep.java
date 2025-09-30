package softLab.genericPipeline.testSteps;

import softLab.entities.User;
import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;

import java.util.List;
import java.util.stream.Collectors;

public class CreateFullNameStep implements IPipelineStep<User, String>{

    @Override
    public Context<String> Execute(Context<User> context) {
        List<String> fullNames = context.getData()
                .stream()
                .map(u -> u.getName() + " " + u.getSurname())
                .collect(Collectors.toList());
        return new Context<>(fullNames);
    }
}
