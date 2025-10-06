/*
package softLab.genericPipeline.testSteps;

import softLab.entities.User;
import softLab.genericPipeline.IPipelineStep;
import softLab.genericPipeline.context.Context;
import softLab.utils.JsonRead;
import softLab.visitor.IVisitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InitDataStep implements IPipelineStep<Void, User> {
    StringBuilder builder = new StringBuilder();

    @Override
    public Context<User> Execute(Context<Void> context) {
        List<User> users = new ArrayList<>();
        try {
            users = JsonRead.deserialize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Context<>(users);
    }



}
*/
