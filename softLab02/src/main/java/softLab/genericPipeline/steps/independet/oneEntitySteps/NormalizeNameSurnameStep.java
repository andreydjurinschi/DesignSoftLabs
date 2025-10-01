package softLab.genericPipeline.steps.independet.oneEntitySteps;

import softLab.entities.User;
import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;

public class NormalizeNameSurnameStep implements IPipelineStep<User, User> {
    @Override
    public Context<User> Execute(Context<User> context) {
        User user = context.getUser();
        String name = firstCharToUpperCase(user.getName());
        String surname = firstCharToUpperCase(user.getSurname());
        user.setName(name);
        user.setSurname(surname);
        return context.returnNew(user);
    }

    private String firstCharToUpperCase(String str){
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}
