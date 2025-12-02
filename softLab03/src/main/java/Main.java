import builder.simpleBuilder.StadiumBuilder;
import director.Director;
import model.Stadium;

public class Main {
    public static void main(String[] a){
        /**
         * builder + director
         */
        Director director = new Director();
        StadiumBuilder builder = new StadiumBuilder();
        director.constructStadiumByBudget(builder, "C", "C", "V", 50000);
        Stadium stadium = builder.getResult();
        System.out.println(stadium);

        /**
         * static stadium creator
         */
/*        Stadium stadium2 = StadiumBuilder.getResult("Bayern Arena", "Munchen", "Andrei", new Characteristics(
                100.0, 70.0, new double[]{5.0, 5.0}, GrassType.NATURAL, FieldCategory.FIFA
        ), new Capacity(80000));
        System.out.println(stadium2);*/

        /**
         * Lambda builder with same names, city, owner
         */
/*        List<Stadium> stadiumsFowWC = Director.buildAll(2);
        stadiumsFowWC.forEach(st -> {
            System.out.println(st);
        });*/

        /**
         * Fluent builder
         */
/*
        StadiumFluentBuilder stadiumFluentBuilder = new StadiumFluentBuilder();
        director.fluentBuild(stadiumFluentBuilder);
        Stadium stadium3 = stadiumFluentBuilder.build();
        System.out.println(stadium);*/
    }
}
