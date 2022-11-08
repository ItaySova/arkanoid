package gamebuilders.screens;
/**
 * @author Itay Sova
 * @param <T> the type.
 */
public interface Task<T> {
    /**
     * This method runs the task.
     * @return null.
     */
    T run();
}