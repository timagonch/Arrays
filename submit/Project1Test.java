import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for the Project1 class.
 * Ensures that all methods behave as expeced
 * under normal and edge coditions.
 * 
 * @author  Tim Goncharov
 * @version Fab 04, 2025
 */
public class Project1Test {

    private Project1 runner;

    /**
     * setup() method, runs before each of your test methods.
     * Use this method to recreate the objects needed for
     * testing your class.
     */
    @Before
    public void setup() {
        runner = new Project1(10);
    }

    // Put your test methods here
    // type "/junitTest" to get a skelleton of the code

    /**
     * Test to get the total capacity of the array.
     */
    @Test
    public void testGetCapacity()
    {
       // 1. set up initial conditions
       // 2. call the method
       // 3. check expected results
        assertEquals(10,runner.getCapacity());
    }

    /**
     * Testing internal size of the array.
     */
    @Test
    public void testSize()
    {
       // 1. set up initial conditions
       // 2. call the method
       // 3. check expected results
        assertEquals(0, runner.size());
        runner.addValue(5);
        assertEquals(1, runner.size());
    }

    /**
     * Testing to see if internal array is empty.
     */
    @Test
    public void testIsEmpty()
    {
       // 1. set up initial conditions
       // 2. call the method
       // 3. check expected results
        assertTrue(runner.isEmpty());
        runner.addValue(5);
        assertFalse(runner.isEmpty());
    }

    /**
     * Testing to check if internal array is full.
     */
    @Test
    public void testIsFull()
    {
       // 1. set up initial conditions
       // 2. call the method
       // 3. check expected results
        assertFalse(runner.isFull());
        for (int i = 0; i < 10; i++) {
            runner.addValue(i);
        }
        assertTrue(runner.isFull());
    }

    /**
    * Testing that addValue() throws an exception when adding beyond capacity.
    */
    @Test
    public void testAddValueWhenFull() {
        // Fill the array to full capacity
        for (int i = 0; i < runner.getCapacity(); i++) {
            runner.addValue(i);
        }

        // Attempt to add one more value beyond capacity
        try {
            runner.addValue(100);
            fail("Expected IllegalStateException was not thrown");
        } catch (IllegalStateException e) {
            assertEquals("Array is full", e.getMessage()); // Verify correct error message
        }
    }

    /**
    * Testing normal behavior of addValue().
    */
    @Test
    public void testAddValue() {
        try {
            runner.addValue(42);
            assertEquals(42, runner.getValue(0));
            assertEquals(1, runner.size());

            runner.addValue(11);
            assertEquals(11, runner.getValue(1));
            assertEquals(2, runner.size());
        } catch (Exception e) {
            fail("Exception was thrown unexpectedly: " + e.getMessage());
        }
    }

    /**
     * Testing method to retrieve any value given its index.
     */
    @Test
    public void testGetValue()
    {
       // 1. set up initial conditions
       // 2. call the method
       // 3. check expected results
        try {
            runner.getValue(0);
            fail("Error was not thrown when expected.");  
        } catch (Exception e){
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        try {
            runner.getValue(-1);
            fail("Error was not thrown when expected.");  
        } catch (Exception e){
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        try {
            runner.addValue(7);
            assertEquals(7, runner.getValue(0));
        } catch (Exception e) {
            fail("Error was thrown when unexpected");
        }      
    }

    /**
    * Testing method to replace any value with another.
    * Should return old value.
    */
    @Test
    public void testSetValue() {
        try {
            runner.addValue(3);
            assertEquals(3, runner.setValue(0, 10)); // Check if old value is returned
            assertEquals(10, runner.getValue(0)); // Ensure the value is updated
        } catch (Exception e) {
            fail("Exception was thrown unexpectedly: " + e.getMessage());
        }
    }

    /**
    * Testing setValue() when the index is out of bounds.
    */
    @Test
    public void testSetValueOutOfBounds() {
        // Case 1: Setting a value in an empty array
        try {
            runner.setValue(0, 10); // No elements exist, should throw an exception
            fail("Expected IndexOutOfBoundsException was not thrown");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 0 is out of bounds. Valid range: 0 to -1", e.getMessage());
        }

        // Add some values to the array
        runner.addValue(5);
        runner.addValue(10);

        // Case 2: Setting a value at an out-of-bounds index (greater than size - 1)
        try {
            runner.setValue(5, 20); // Index 5 is out of bounds (only 2 elements exist)
            fail("Expected IndexOutOfBoundsException was not thrown");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 5 is out of bounds. Valid range: 0 to 1", e.getMessage());
        }

        // Case 3: Setting a value at a negative index
        try {
            runner.setValue(-1, 15);
            fail("Expected IndexOutOfBoundsException was not thrown");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index -1 is out of bounds. Valid range: 0 to 1", e.getMessage());
        }
    }

        /**
     * Testing the removal of values from the internal array.
     */
    @Test
    public void testRemoveValueAt() {
        // 1. Set up initial conditions
        runner.addValue(10);
        runner.addValue(20);
        runner.addValue(30);

        // 2. Remove the second element (index 1)
        runner.removeValueAt(1);

        // 3. Check expected results
        assertEquals(30, runner.getValue(1)); // 30 should now be at index 1
        assertEquals(2, runner.size()); // Size should decrease
    }

    /**
     * Test for removeValueAt() with an invalid index.
     */
    @Test
    public void testRemoveValueAtInvalid() {
        // Case 1: Removing from an empty array
        try {
            runner.removeValueAt(0);
            fail("Expected IndexOutOfBoundsException was not thrown");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 0 is out of bounds. Valid range: 0 to -1", e.getMessage());
        }

        // Add some values to the array
        runner.addValue(10);
        runner.addValue(20);

        // Case 2: Removing an index greater than size - 1
        try {
            runner.removeValueAt(5); // Only two elements exist (index 0 and 1)
            fail("Expected IndexOutOfBoundsException was not thrown");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 5 is out of bounds. Valid range: 0 to 1", e.getMessage());
        }

        // Case 3: Removing from a negative index
        try {
            runner.removeValueAt(-1);
            fail("Expected IndexOutOfBoundsException was not thrown");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index -1 is out of bounds. Valid range: 0 to 1", e.getMessage());
        }
    }


    /** Test for getMinimum(). */
    @Test
    public void testGetMinimum() {
        assertEquals(Integer.MIN_VALUE, runner.getMinimum());
        runner.addValue(5);
        runner.addValue(2);
        runner.addValue(8);
        assertEquals(2, runner.getMinimum());
    }

    /** Test for getMaximum(). */
    @Test
    public void testGetMaximum() {
        assertEquals(Integer.MAX_VALUE, runner.getMaximum());
        runner.addValue(5);
        runner.addValue(2);
        runner.addValue(8);
        assertEquals(8, runner.getMaximum());
    }

    /** Test for getRange(). */
    @Test
    public void testGetRange() {
        runner.addValue(10);
        runner.addValue(3);
        runner.addValue(15);
        assertEquals(12, runner.getRange());
    }

    /** Test for getRange() with an empty array. 
     */
    @Test
    public void testGetRangeEmpty() {
        // Attempt to compute range on an empty array
        try {
            runner.getRange();
            fail("Expected IllegalStateException was not thrown");
        } catch (IllegalStateException e) {
            assertEquals("Cannot compute range of an empty data structure.", e.getMessage());
        }
    }

    /** Test for getAverage(). */
    @Test
    public void testGetAverage() {
        runner.addValue(5);
        runner.addValue(10);
        runner.addValue(15);
        assertEquals(10.0, runner.getAverage(), 0.001);
    }

    /** Test for getAverage() with an empty array.
     */
    @Test
    public void testGetAverageEmpty() {
        // Attempt to compute average on an empty array
        try {
            runner.getAverage();
            fail("Expected IllegalStateException was not thrown");
        } catch (IllegalStateException e) {
            assertEquals("Cannot calculate average of an empty array.", e.getMessage());
        }
    }

    /** Test for addRandom(). */
    @Test
    public void testAddRandom() {
        runner.addRandom(5);
        assertEquals(5, runner.size());
    }

    /** 
     * Test for addRandom() exceeding capacity.
     */
    @Test
    public void testAddRandomExceedingCapacity() {
        // Attempt to add more values than the array can hold
        try {
            runner.addRandom(11);
            fail("Expected IllegalStateException was not thrown");
        } catch (IllegalStateException e) {
            // Ensure the error message matches expected output
            assertEquals("Array is full after adding 10 values." + 
                " Could not add all 11.", e.getMessage());
        }

        // Ensure that despite the exception, the array is full
        assertTrue(runner.isFull());
    }

    /** Test for hasDuplicates(). */
    @Test
    public void testHasDuplicates() {
        runner.addValue(5);
        runner.addValue(10);
        runner.addValue(5);
        assertTrue(runner.hasDuplicates());
        runner.removeValueAt(2);
        assertFalse(runner.hasDuplicates());
    }

}
