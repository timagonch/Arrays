import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for the Project1 class.
 * Ensures that all methods behave as expeced
 * under normal and edge coditions.
 * 
 * @author  Tim Goncharov
 * @version Jan 07, 2025
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

    /** Test for constructor with invalid capacities */
    //@Test
    //public void testConstructorInvalidCapacity() {
     //   Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
       //     new Project1(0);
      //  });
     //   assertEquals("Capacity must be greater than zero.", exception1.getMessage());

       // Exception exception2 = assertThrows(NegativeArraySizeException.class, () -> {
          //  new Project1(-5);
      //  });
       // assertEquals("Capacity must be greater than zero.", exception2.getMessage());
    //}

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
     * Testing adding values to the internal array.
     */
    @Test
    public void testAddValue()
    {
       // 1. set up initial conditions
       // 2. call the method
       // 3. check expected results
        runner.addValue(42);
        assertEquals(42, runner.getValue(0));
        assertEquals(1, runner.size());
    }

    /** 
     * Test for addValue() when the array is full.
     */
    @Test
    public void testAddValueWhenFull() {
        for (int i = 0; i < runner.getCapacity(); i++) {
            runner.addValue(i);
        }
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            runner.addValue(100);
        });
        assertEquals("Array is full", exception.getMessage());
        
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
    public void testSetValue()
    {
       // 1. set up initial conditions
       // 2. call the method
       // 3. check expected results
        runner.addValue(3);
        assertEquals(3, runner.setValue(0, 10));
        assertEquals(10, runner.getValue(0));
    }

    /** Test for setValue() with an invalid index */
    @Test
    public void testSetValueOutOfBounds() {
        // Case 1: Attempting to set a value when the array is empty
        Exception exception1 = assertThrows(IndexOutOfBoundsException.class, () -> 
        {
            runner.setValue(0, 10); // No elements exist, should throw an exception
        });
        assertEquals("Index 0 is out of bounds. Valid range: 0 to -1", exception1.getMessage());

        // Add some values to the array
        runner.addValue(5);
        runner.addValue(10);

        // Case 2: Setting a value at an out-of-bounds index (greater than size - 1)
        Exception exception2 = assertThrows(IndexOutOfBoundsException.class, () -> 
        {
            runner.setValue(5, 20); // Index 5 is out of bounds (only 2 elements exist)
        });
    
        // Case 3: Setting a value at a negative index
        Exception exception3 = assertThrows(IndexOutOfBoundsException.class, () -> 
        {
            runner.setValue(-1, 15);
        });
    }

    /**
     * Testing the removal of values from internal array.
     */
    @Test
    public void testRemoveValueAt()
    {
       // 1. set up initial conditions
       // 2. call the method
       // 3. check expected results
        runner.addValue(10);
        runner.addValue(20);
        runner.addValue(30);
        runner.removeValueAt(1);
        assertEquals(30, runner.getValue(1));
        assertEquals(2, runner.size());
    }

    /** Test for removeValueAt() with an invalid index */
    @Test
    public void testRemoveValueAtInvalid() 
    {
        Exception exception1 = assertThrows(IndexOutOfBoundsException.class, () -> {
            runner.removeValueAt(0);
        });

        runner.addValue(10);
        runner.addValue(20);

        Exception exception2 = assertThrows(IndexOutOfBoundsException.class, () -> {
            runner.removeValueAt(5);
        });

        Exception exception3 = assertThrows(IndexOutOfBoundsException.class, () -> {
            runner.removeValueAt(-1);
        });
    }

    /** Test for getMinimum() */
    @Test
    public void testGetMinimum() {
        assertEquals(Integer.MIN_VALUE, runner.getMinimum());
        runner.addValue(5);
        runner.addValue(2);
        runner.addValue(8);
        assertEquals(2, runner.getMinimum());
    }

    /** Test for getMaximum() */
    @Test
    public void testGetMaximum() {
        assertEquals(Integer.MAX_VALUE, runner.getMaximum());
        runner.addValue(5);
        runner.addValue(2);
        runner.addValue(8);
        assertEquals(8, runner.getMaximum());
    }

    /** Test for getRange() */
    @Test
    public void testGetRange() {
        runner.addValue(10);
        runner.addValue(3);
        runner.addValue(15);
        assertEquals(12, runner.getRange());
    }

    /** Test for getRange() with empty array */
    @Test
    public void testGetRangeEmpty() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            runner.getRange();
        });
        assertEquals("Cannot compute range of an empty data structure.", exception.getMessage());
    }

    /** Test for getAverage() */
    @Test
    public void testGetAverage() {
        runner.addValue(5);
        runner.addValue(10);
        runner.addValue(15);
        assertEquals(10.0, runner.getAverage(), 0.001);
    }

    /** Test for getAverage() with empty array */
    @Test
    public void testGetAverageEmpty() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            runner.getAverage();
        });
        assertEquals("Cannot calculate average of an empty array.", exception.getMessage());
    }

    /** Test for addRandom() */
    @Test
    public void testAddRandom() {
        runner.addRandom(5);
        assertEquals(5, runner.size());
    }

    /** Test for addRandom() exceeding capacity */
    @Test
    public void testAddRandomExceedingCapacity() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            runner.addRandom(11);
        });
        // Ensure that despite the exception, the array still has 10 elements.
        assertTrue(runner.isFull());
    }

    /** Test for hasDuplicates() */
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
