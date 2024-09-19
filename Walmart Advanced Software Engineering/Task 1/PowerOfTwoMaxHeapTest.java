import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

public class PowerOfTwoMaxHeapTest {

    private PowerOfTwoMaxHeap heap;

    @BeforeEach
    public void setUp() {
        // Initialize the heap with an exponent of 2 (branching factor = 4)
        heap = new PowerOfTwoMaxHeap(2);
    }

    @Test
    public void testInsertSingleElement() {
        heap.insert(10);
        assertFalse(heap.isEmpty(), "Heap should not be empty after insertion.");
        assertEquals(10, heap.peekMax(), "Peek max should return the inserted element.");
        assertEquals(1, heap.size(), "Heap size should be 1 after one insertion.");
    }

    @Test
    public void testInsertMultipleElements() {
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.insert(30);
        heap.insert(15);

        assertEquals(5, heap.size(), "Heap size should be 5 after five insertions.");
        assertEquals(30, heap.peekMax(), "Peek max should return the largest element.");
    }

    @Test
    public void testPopMax() {
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.insert(30);
        heap.insert(15);

        int max = heap.popMax();
        assertEquals(30, max, "Pop max should return the largest element.");
        assertEquals(4, heap.size(), "Heap size should decrease by one after popMax.");
        assertEquals(20, heap.peekMax(), "Peek max should return the next largest element.");
    }

    @Test
    public void testPopMaxUntilEmpty() {
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);

        assertEquals(20, heap.popMax());
        assertEquals(10, heap.popMax());
        assertEquals(5, heap.popMax());
        assertTrue(heap.isEmpty(), "Heap should be empty after popping all elements.");
    }

    @Test
    public void testPopMaxFromEmptyHeap() {
        assertThrows(NoSuchElementException.class, () -> {
            heap.popMax();
        }, "popMax should throw NoSuchElementException when heap is empty.");
    }

    @Test
    public void testInsertDuplicateElements() {
        heap.insert(10);
        heap.insert(20);
        heap.insert(20);
        heap.insert(30);
        heap.insert(10);

        assertEquals(30, heap.peekMax(), "Peek max should return the largest element.");
        assertEquals(5, heap.size(), "Heap size should account for duplicate insertions.");
        
        int firstMax = heap.popMax();
        assertEquals(30, firstMax, "First popMax should return 30.");
        
        int secondMax = heap.popMax();
        assertEquals(20, secondMax, "Second popMax should return 20.");
        
        int thirdMax = heap.popMax();
        assertEquals(20, thirdMax, "Third popMax should return the duplicate 20.");
        
        int fourthMax = heap.popMax();
        assertEquals(10, fourthMax, "Fourth popMax should return 10.");
        
        int fifthMax = heap.popMax();
        assertEquals(10, fifthMax, "Fifth popMax should return the duplicate 10.");
        
        assertTrue(heap.isEmpty(), "Heap should be empty after popping all elements.");
    }

    @Test
    public void testPopMaxSingleElement() {
        heap.insert(42);
        assertEquals(42, heap.peekMax(), "Peek max should return the single inserted element.");
        int max = heap.popMax();
        assertEquals(42, max, "Pop max should return the single inserted element.");
        assertTrue(heap.isEmpty(), "Heap should be empty after popping the only element.");
    }

    @Test
    public void testLinearHeapBehavior() {
        PowerOfTwoMaxHeap linearHeap = new PowerOfTwoMaxHeap(0); // 2^0 = 1 child per parent (linear heap)
        
        linearHeap.insert(10);
        linearHeap.insert(20);
        linearHeap.insert(15);
        
        assertEquals(20, linearHeap.peekMax(), "Peek max should return the largest element.");
        
        int max = linearHeap.popMax();
        assertEquals(20, max, "Pop max should return the largest element.");
        assertEquals(2, linearHeap.size(), "Heap size should decrease correctly.");
        
        assertEquals(15, linearHeap.peekMax(), "Peek max should return the next largest element.");
    }

    @Test
    public void testBinaryHeapBehavior() {
        PowerOfTwoMaxHeap binaryHeap = new PowerOfTwoMaxHeap(1); // 2^1 = 2 children per parent (binary heap)
        
        binaryHeap.insert(10);
        binaryHeap.insert(20);
        binaryHeap.insert(15);
        
        assertEquals(20, binaryHeap.peekMax(), "Peek max should return the largest element.");
        
        int max = binaryHeap.popMax();
        assertEquals(20, max, "Pop max should return the largest element.");
        assertEquals(2, binaryHeap.size(), "Heap size should decrease correctly.");
        
        assertEquals(15, binaryHeap.peekMax(), "Peek max should return the next largest element.");
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4})
    public void testVariousBranchingFactors(int exponent) {
        PowerOfTwoMaxHeap testHeap = new PowerOfTwoMaxHeap(exponent);
        int branchingFactor = (int) Math.pow(2, exponent);
        
        // Insert (branchingFactor * 2) elements to ensure multiple levels
        for (int i = 1; i <= branchingFactor * 2; i++) {
            testHeap.insert(i);
        }
        
        assertEquals(branchingFactor * 2, testHeap.size(), "Heap size should match number of insertions.");
        assertEquals(branchingFactor * 2, testHeap.peekMax(), "Peek max should return the largest element.");
        
        // Pop max and verify
        for (int i = branchingFactor * 2; i >= 1; i--) {
            assertEquals(i, testHeap.popMax(), "Pop max should return elements in descending order.");
        }
        
        assertTrue(testHeap.isEmpty(), "Heap should be empty after popping all elements.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    public void testDifferentExponents(int exponent) {
        PowerOfTwoMaxHeap testHeap = new PowerOfTwoMaxHeap(exponent);
        assertEquals(exponent, testHeap.getExponent(), "Exponent should be correctly set.");
        assertEquals((int) Math.pow(2, exponent), testHeap.getBranchingFactor(), "Branching factor should be 2^exponent.");
        
        // Insert and verify behavior
        testHeap.insert(100);
        assertEquals(100, testHeap.peekMax(), "Peek max should return the inserted element.");
    }

    @Test
    public void testGetExponent() {
        assertEquals(2, heap.getExponent(), "getExponent should return the correct exponent value.");
        
        PowerOfTwoMaxHeap binaryHeap = new PowerOfTwoMaxHeap(1);
        assertEquals(1, binaryHeap.getExponent(), "getExponent should return the correct exponent value for binary heap.");
    }

    @Test
    public void testToString() {
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        
        String expected = "PowerOfTwoMaxHeap{heap=[20, 10, 5], exponent=2, branchingFactor=4}";
        assertEquals(expected, heap.toString(), "toString should return the correct string representation of the heap.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void testParameterizedInsertAndPopMax(int exponent) {
        PowerOfTwoMaxHeap testHeap = new PowerOfTwoMaxHeap(exponent);
        int branchingFactor = (int) Math.pow(2, exponent);
        
        // Insert (branchingFactor * 3) elements
        for (int i = 1; i <= branchingFactor * 3; i++) {
            testHeap.insert(i);
        }
        
        assertEquals(branchingFactor * 3, testHeap.size(), "Heap size should match number of insertions.");
        assertEquals(branchingFactor * 3, testHeap.peekMax(), "Peek max should return the largest element.");
        
        // Pop max and verify
        for (int i = branchingFactor * 3; i >= 1; i--) {
            assertEquals(i, testHeap.popMax(), "Pop max should return elements in descending order.");
        }
        
        assertTrue(testHeap.isEmpty(), "Heap should be empty after popping all elements.");
    }

    @Disabled("Performance test - not included in standard unit tests.")
    @Test
    public void testPerformance() {
        PowerOfTwoMaxHeap perfHeap = new PowerOfTwoMaxHeap(3); // 2^3 = 8 children per parent
        int numberOfElements = 100000;

        // Measure insertion time
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfElements; i++) {
            perfHeap.insert(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Insertion of " + numberOfElements + " elements took " + (endTime - startTime) + " ms.");

        // Measure popMax time
        startTime = System.currentTimeMillis();
        while (!perfHeap.isEmpty()) {
            perfHeap.popMax();
        }
        endTime = System.currentTimeMillis();
        System.out.println("PopMax of " + numberOfElements + " elements took " + (endTime - startTime) + " ms.");
    }
}
