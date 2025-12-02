import java.util.HashMap;
import java.util.Map;

public final class PropertyRegistry {
    private static int counter = 0;
    private static Map<String, Integer> keys = new HashMap<>();

    public static <T>PropertyKey<T> registerNew(String name){
        if(keys.containsKey(name)){
            throw new IllegalArgumentException("key " + name + " already exists");
        }
        keys.put(name, counter);
        return new PropertyKey<>(counter++);
    }

    public static Integer getKeyByName(String name){
        return keys.get(name);
    }


}
