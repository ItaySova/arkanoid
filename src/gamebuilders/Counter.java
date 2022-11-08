package gamebuilders;

/**
 * @author Itay Sova
 */
public class Counter {
    private int counter;
    /**
     * A constructor which creates a counter from an initial value.
     * @param counter the given initial value.
     */
    public Counter(int counter)  {
        this.counter = counter;
    }
    /**
     * Adding a given number to the counter.
     * @param number the number to add to the counter.
     */
    public void increase(int number) {
        counter = counter + number;
    }
    /**
     * Subtracting a given number from the counter.
     * @param number the number to substract from the counter.
     */
    public void decrease(int number) {
        counter = counter - number;
    }

    /**
     * An accessor method which returns the integer value of the counter.
     * @return the current value of the counter.
     */
    public int getValue() {
        return counter;
    }
}