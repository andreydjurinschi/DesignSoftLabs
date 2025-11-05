import builder.StadiumBuilder;
import director.Director;
import model.Stadium;

public class Main {
    public static void main(String[] a){
        StadiumBuilder builder = new StadiumBuilder();

        Director director = new Director();

        director.constructStadiumByBudget(builder, "Camp Nou", "Barcelona", "Andrei", 50000);

        Stadium stadium = builder.getResult();

        System.out.println(stadium);
    }
}
