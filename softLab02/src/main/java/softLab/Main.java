package softLab;

import softLab.entities.User;
import softLab.genericPipeline.Pipeline;
import softLab.genericPipeline.PipelineFactory;
import softLab.simplePipeline.context.Context;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {

        /**
         * -----------------------------------------------
         * Generic pipeline that simple generates an email
         * -----------------------------------------------
         */
        PipelineFactory.GenerateEmailsPipeline();

        /**
         * -----------------------------------
         * Generic pipeline, that creates user
         * -----------------------------------
         */
/*      User user = new User();
        user.setName("oleg");
        user.setSurname("ciornei");
        user.setUsername("ciornei");
        user.setDateOfBirth(LocalDate.of(2112, 11, 05));
        PipelineFactory.CreateNewUsersPipeline(user);*/
        /**
         * ---------------
         * SIMPLE PIPELINE
         * ---------------
         */
        /*Context context = new Context();
        PipelineFactory.generateUsernames(context);*/
    }
}