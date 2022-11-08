package listeners;

/**
 * @author Itay Sova
 * This interface represents the hit notifier.
 */
public interface HitNotifier {
    /**
     * This method adds a new given hit listener to a list in the hit notifier.
     * @param hl the new hit listner to add.
     */
    void addHitListener(HitListener hl);
    // Remove hl from the list of listeners to hit events.
    /**
     * This method removes a given hit listener from a list in the hit notifier.
     * @param hl the hit listner to remove.
     */
    void removeHitListener(HitListener hl);
}