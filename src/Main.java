/**
 * Sample main program for Project 1
 */

 public class Main {
    public static void main(String[] args) throws Exception 
    {
        Project1 numCollection = new Project1(100);
        numCollection.addRandom(50);
        numCollection.addValue(10);
        System.out.println("Size = "+numCollection.size());
        System.out.println("Smallest = "+numCollection.getMinimum());
        System.out.println("Largest = "+numCollection.getMaximum());
        System.out.println("Range = "+numCollection.getRange());
        System.out.println("Average = "+numCollection.getAverage());
        System.out.println("Duplicates? = "+numCollection.hasDuplicates());
        System.out.println("Empty? = "+numCollection.isEmpty());
        System.out.println("Full? = "+numCollection.isFull());
    
        int lastIndex = numCollection.getCapacity();
        try {
           numCollection.setValue(lastIndex+1, 25);
        }
        catch (IndexOutOfBoundsException x) {
           System.out.println("Index was out of bounds");
        } 
    }
}