//315099184 Oran Shaki
package SpriteAndCollide;

/**
 * a simple class that is used for counting things.
 */
public class Counter {
    private int currentCount = 0;
    /**
     * add number to current count.
     * @param number the given number
     */
    public void increase(int number) {
        currentCount += number;
    }

    /**
     * subtract number from current count.
     * @param number the given number
     */
    public void decrease(int number) {
        currentCount -= number;
    }

    /**
     * @return current count.
     */
    public int getValue() {
        return this.currentCount;
    }

    /**
     * @param num the new number of counter.
     */
    public void setCounter(int num) {
        this.currentCount = num;
    }
}
