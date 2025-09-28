package softLab.pipline.steps;

import softLab.entities.User;
import softLab.pipline.IPipelineStep;
import softLab.pipline.context.Context;

import java.util.ArrayList;
import java.util.List;

public class ExtractNameAndSurname implements IPipelineStep<Context> {

    @Override
    public List<String> Execute(Context context) {
        List<String> toLowerCaseUsers = new ArrayList<>();
        for(var u : context.getUsers()){
            toLowerCaseUsers.add(u.getName().toLowerCase() + "." + u.getSurname().toLowerCase());
        }
        return toLowerCaseUsers;
    }
}
