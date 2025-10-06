package softLab.genericPipeline.steps.dependet;

import softLab.entities.User;
import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;
import softLab.visitor.IVisitor;

import java.util.List;

public class UsersWithoutUsernameStep implements IPipelineStep<User, User> {

    private StringBuilder builder = new StringBuilder();

    @Override
    public Context<User> Execute(Context<User> context) {
        List<User> allUsers = context.getData();
        List<User> withoutUsernames = allUsers.stream().filter(u-> u.getUsername() == null).toList();
        if(withoutUsernames.isEmpty()){
            builder.append("All users have usernames");
        }else{
            builder.append("Users without usernames: ");
            for(var user : withoutUsernames){
                builder.append("user: " + user.getName() + " " + user.getSurname() + "\n");
            }
        }
        return new Context<>(withoutUsernames);
    }

    public String accept(IVisitor visitor){
        return visitor.visitUsersWithoutUsernamesStep(this, builder);
    }
}
