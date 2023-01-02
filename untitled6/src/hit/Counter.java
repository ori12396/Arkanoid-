//207632795
package hit;

/**
 * @author ori zohar
 * Vounter class .
 */
public class Counter {
    private int counter;

    /**
     * this is the constructor.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * this method add a current number to the counter.
     *
     * @param number current number to add.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * this method decrease a current number to the counter.
     *
     * @param number current number to decrease.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * assessors method.
     *
     * @return the counter.
     */
    public int getValue() {
        return this.counter;
    }
}
