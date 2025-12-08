import java.util.HashMap;
import java.util.Map;

/**
 * The type Property registry.
 */
public final class PropertyRegistry {
    private static int counter = 0;
    private static Map<String, Integer> keys = new HashMap<>();

    /**
     * Register new property key.
     *
     * @param <T>  the type parameter
     * @param name the name
     * @return the property key
     */
    public static <T>PropertyKey<T> registerNew(String name){
        if(keys.containsKey(name)){
            throw new IllegalArgumentException("key " + name + " already exists");
        }
        keys.put(name, counter);
        return new PropertyKey<>(counter++);
    }

    /**
     * Get key by name integer.
     *
     * @param name the name
     * @return the integer
     */
    public static Integer getKeyByName(String name){
        return keys.get(name);
    }
}
