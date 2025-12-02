import builder.simpleBuilder.StadiumBuilder;
import director.Director;
import model.Stadium;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BuilderTests {
    private Director director = new Director();

    @Test
    public void testSimpleBuilder(){
        StadiumBuilder builder = new StadiumBuilder();
        director.constructStadiumByBudget(builder, "TEST_NAME", "TEST_CITY", "TEST_OWNER", 20000);
        Stadium stadium1 = builder.getResult();
        Assertions.assertEquals("TEST_NAME", "TEST_NAME");
        Assertions.assertEquals("TEST_CITY", "TEST_CITY");
        Assertions.assertEquals("TEST_OWNER", "TEST_OWNER");
    }

    @Test
    public void testSimpleBuilderThrowsException() {
        StadiumBuilder builder = new StadiumBuilder();
        director.constructStadiumByBudget(builder, "T", "T", "T", 20000);
        Assertions.assertThrows(IllegalStateException.class,
                () -> builder.getResult());
    }

}
