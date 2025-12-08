/**
 * The type Property key.
 *
 * @param <T> the type parameter
 */
public class PropertyKey <T>{
    private final int id;

    /**
     * Instantiates a new Property key.
     *
     * @param id the id
     */
    public PropertyKey(int id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

}
