/**
 * The type Entity.
 */
public class Entity {
    private final Object[] values = new Object[128];

    /**
     * Set.
     *
     * @param <T>   the type parameter
     * @param key   the key
     * @param value the value
     */
    public <T> void set(PropertyKey<T> key, T value){
        values[key.getId()] = value;
    }

    /**
     * Get or default t.
     *
     * @param <T>          the type parameter
     * @param key          the key
     * @param defaultValue the default value
     * @return the t
     */
    public <T> T getOrDefault(PropertyKey<T> key, T defaultValue){
        Object value = values[key.getId()];
        return value == null ? defaultValue : (T) value;
    }

    /**
     * Try get boolean.
     *
     * @param <T> the type parameter
     * @param key the key
     * @param out the out
     * @return the boolean
     */
    public <T> boolean tryGet(PropertyKey<T> key, Holder<T> out) {
        Object value = values[key.getId()];
        if (value != null) {
            out.value = (T) value;
            return true;
        }
        return false;
    }

    /**
     * The type Holder.
     *
     * @param <T> the type parameter
     */
    public static class Holder<T> {
        /**
         * The Value.
         */
        public T value;
    }

}
