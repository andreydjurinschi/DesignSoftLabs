import org.junit.jupiter.api.Test;
import softLab.entities.User;
import softLab.genericPipeline.Pipeline;
import softLab.genericPipeline.context.Context;
import softLab.genericPipeline.steps.oneEntitySteps.CheckIfUsernameIsNotNull;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class PipelineTest {

    @Test
    public void testUsersWithoutUsernamesStep(){

        User user = new User();
        user.setName("test");
        user.setSurname("test");
        Context<User> context = new Context<>(user);
        user.setDateOfBirth(LocalDate.of(2024, 12, 12));

        Pipeline pipeline = new Pipeline();

        pipeline.addStep(new CheckIfUsernameIsNotNull());
        pipeline.executeSteps(context);

        assertFalse(context.isValid());

    }
}
