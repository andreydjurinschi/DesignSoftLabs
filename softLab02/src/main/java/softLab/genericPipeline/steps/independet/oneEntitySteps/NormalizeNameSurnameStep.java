package softLab.genericPipeline.steps.independet.oneEntitySteps;

import softLab.entities.User;
import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;
import softLab.visitor.IVisitor;

public class NormalizeNameSurnameStep implements IPipelineStep<User, User> {

    StringBuilder builder = new StringBuilder();

    @Override
    public Context<User> Execute(Context<User> context) {
        User user = context.getUser();
        if(isLowerCased(user.getName())){
            builder.append("Name of ").append(user.getName()).append(" is lowercase");
        } else if (isLowerCased(user.getName())) {
            builder.append("Surname of ").append(user.getSurname()).append(" is lowercase");
        }
        String name = firstCharToUpperCase(user.getName());
        String surname = firstCharToUpperCase(user.getSurname());
        user.setName(name);
        user.setSurname(surname);
        return context.returnNew(user);
    }

    private String firstCharToUpperCase(String str){
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }

    private boolean isLowerCased(String str){
        return Character.isLowerCase(str.charAt(0));
    }

    public String accept(IVisitor visitor){
        return visitor.visitNormalizeNameStep(this);
    }
}
