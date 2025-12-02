public class Entity {
    private final Object[] values = new Object[128];

    public <T> void set(PropertyKey<T> key, T value){
        values[key.getId()] = value;
    }

    public <T> T getOrDefault(PropertyKey<T> key, T defaultValue){
        Object value = values[key.getId()];
        return value == null ? defaultValue : (T) value;
    }

    public <T> boolean tryGet(PropertyKey<T> key, Holder<T> out) {
        Object value = values[key.getId()];
        if (value != null) {
            out.value = (T) value;
            return true;
        }
        return false;
    }

    public static class Holder<T> {
        public T value;
    }

}
