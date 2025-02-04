import java.util.Random;
import itsc2214.*;
/**
 * Project1 class.
 * Creates an array of integers and provides methods to compute the minimum, 
 * maximum, range, and average of the elements.
 * 
 * @author  Tim Goncharov
 * @version Fab 04, 2025
 */
public class Project1 implements ArrayInt {

    private int[] mylist;
    private int size;
    private int capacity;

    /**
     * Constructs a new Project1 object with a specified capacity.
    * Initializes an internal array to store elements.
    *
    * @param capacity the maximum number of elements the data structure can hold.
    */
    
    public Project1(int capacity)
    {
        // TODO instantiate an array of capacity
        mylist = new int[capacity];
       // if (capacity <= 0) {
           // throw new IllegalArgumentException("Capacity must be greater than zero.");
       // }
        this.capacity = capacity;
        this.size = 0;         // initialize inner array to length of zero.
    }

    /**
    * Retrieves the minimum value in the array.
    * @return the smallest value in the array.
    *         Returns Integer.MIN_VALUE if the array is empty.
    */
    public int getMinimum()
    {   
        if (size == 0){
            return Integer.MIN_VALUE;
        } else {
            int min = mylist[0];
            for (int i = 1; i < size; i++){
                if (mylist[i] < min){
                    min = mylist[i];
                }
            }
            return min;
        }
    }

    /**
    * Retrieves the maximum value in the array.
    * @return the largest value in the array.
    *         Returns Integer.MAX_VALUE if the array is empty.
    */
    public int getMaximum()
    {
        if (size == 0){
            return Integer.MAX_VALUE;
        } else {
            int max = mylist[0];
            for (int i = 1; i < size; i++){
                if (mylist[i] > max){
                    max = mylist[i];
                }
            }
            return max;
        }
    }

    /**
    * Computes the range of values in the array.
    * @return the difference between the maximum and minimum values.
    * @throws IllegalStateException if the array is empty.
    */
    public int getRange() {
        if (size == 0) {
            throw new IllegalStateException("Cannot compute range of an empty data structure.");
        }
        return getMaximum() - getMinimum();
    }

    /**
    * Calculates the average of the elements in the inner array.
    * @return the average of the elements as a double.
    * @throws IllegalStateException if the data structure is empty.
    */
    public double getAverage()
    {
        if (isEmpty()){
            throw new IllegalStateException("Cannot calculate average of an empty array.");
        }
        int total = 0;
        for (int i = 0; i < size; i++){
            total += mylist[i];
        }
        return (double) total / size;
    }

    // TODO define the methods defined in ArrayInt
    // https://webpages.charlotte.edu/mperez19/itsc2214/javadoc/itsc2214/ArrayInt.html
    
    /**
    * Returns the length the array.
    * @return the size of the array.
    */
    public int getCapacity(){
        return capacity;
    }

    /**
    * Returns the current number of elements in the array.
    * @return the size of the array.
    */
    public int size(){
        return size;
    }

    /**
    * Checks if the array is empty.
    * @return true if the size is 0, false otherwise.
    */
    public boolean isEmpty(){
        return size() == 0;
    }

    /**
    * Checks if the data structure has reached its maximum capacity.
    * @return true if the current size equals the maximum capacity, false otherwise.
    */
    public boolean isFull(){
        return size() == getCapacity();
    }
    
    /**
    * Adds a new number to the array.
    * @param value the integer value to be added.
    * @throws IllegalStateException if the data structure is full and cannot accept new values.
     */
    public void addValue(int value) throws IllegalStateException{
        if (isFull())
        {
            throw new IllegalStateException("Array is full");
        }
        mylist[size] = value;
        size++;
    }

    /**
    * Retrieves the number at the specified index in the array.
    * @param index the position of the value to be retrieved.
    * @return the integer value at the specified index.
    * @throws IndexOutOfBoundsException if the index is less than 0 or
    *         greater than or equal to the current size.
    */
    public int getValue(int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index >= size) 
        {
            throw new IndexOutOfBoundsException();
        }
        return mylist[index];
    }

    /**
    * Sets the value at the specified index in the array.
    * This method does not grow the array; it only replaces an existing value.
    * 
    * @param index the position of the value to be updated.
    * @param value the new integer value to set at the specified index.
    * @return the old value that was replaced.
    * @throws IndexOutOfBoundsException if the index is less than 0 or greater 
    *         than or equal to the current size.
    */
    public int setValue(int index, int value) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                "Index " + index + " is out of bounds." + 
                " Valid range: 0 to " + (size - 1)
            );
        }
    
        int oldValue = mylist[index]; // Store the old value before replacing
        mylist[index] = value; // Replace with the new value
        return oldValue; // Return the old value
    }

    /**
    * Adds up to `n` random values at the end of the array.
    * If there is not enough space, it adds as many as possible before throwing an exception.
    * 
    * @param n the number of random values to add.
    * @throws IllegalStateException if the array becomes full while adding values.
    */
    public void addRandom(int n) throws IllegalStateException {
        Random rand = new Random(); // Create Random instance
        int added = 0; // Counter for how many values are successfully added

        while (added < n && size < capacity) { // Keep adding until full or `n` is reached
            mylist[size++] = rand.nextInt(100); // Adds a random number (0 to 99)
            added++; // Track how many values were successfully added
        }

        // If not all `n` values were added, throw an exception
        if (added < n) {
            throw new IllegalStateException("Array is full after adding " + added + 
            " values. Could not add all " + n + ".");
        }
    }

    /**
    * Removes the value at the specified index by shifting all 
    * subsequent elements down by one position.
    * 
    * @param index the position of the value to be removed.
    * @throws IndexOutOfBoundsException if the index is less than 0 
    * or greater than or equal to the current size.
    */
    public void removeValueAt(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + 
            " is out of bounds. Valid range: 0 to " + (size - 1));
        }

        // Shift all elements to the left, overwriting the element at `index`
        for (int i = index; i < size - 1; i++) {
            mylist[i] = mylist[i + 1];
        }

        size--; // Reduce size after shifting elements
    }

    /**
    * Checks if the array contains any duplicate values.
    * 
    * @return true if a duplicate is found, false otherwise.
    */
    public boolean hasDuplicates() {
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (mylist[i] == mylist[j]) {
                    return true; // Duplicate found
                }
            }
        }
        return false; // No duplicates found
    }
    

}
