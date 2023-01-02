//207632795
package hit;

/**
 * @author ori
 * hitnotifier interface.
 */
public interface HitNotifier {
    /**
     * this method add a listener.
     * @param hl listener to add
     */
    void addHitListener(HitListener hl);

    /**
     * this method remove a listener from the list .
     * @param hl item to remove.
     */
    void removeHitListener(HitListener hl);
}
