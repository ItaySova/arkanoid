package gamebuilders.screens;
/**
/**
 * @author Itay Sova
 * @param <T> the type.
 */
public class Selection<T> {
    private String key;
    private String message;
    private T returnVal;

    /**
     * constructor.
     *
     * @param key       the key of the selcet
     * @param message   themessege.
     * @param returnVal the return value.
     */
    public Selection(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * @return the key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return themessege.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @return the return value.
     */
    public T getReturnVal() {
        return this.returnVal;
    }

}
