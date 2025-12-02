package builder.labdaBuilder;

import model.Stadium;

@FunctionalInterface
public interface LambdaBuilder {
    /*default List<Stadium> build(int stadiumCount) {
        return null;
    }*/
    Stadium build();
}
