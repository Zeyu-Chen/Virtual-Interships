import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PowerOfTwoMaxHeap {
    private ArrayList<Integer> heap;
    private int exponent;
    private int branchingFactor;

    /**
     * Constructor to initialize the heap and branching factor.
     *
     * @param exponent The exponent to determine the number of children per parent (2^exponent).
     * @throws IllegalArgumentException If the exponent is negative.
     */
    public PowerOfTwoMaxHeap(int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Exponent must be non-negative.");
        }
        this.exponent = exponent;
        this.branchingFactor = (int) Math.pow(2, exponent);
        this.heap = new ArrayList<>();
    }

    /**
     * Inserts a new value into the heap and maintains the heap property.
     *
     * @param value The value to be inserted.
     */
    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    /**
     * Removes and returns the maximum value from the heap.
     *
     * @return The maximum value in the heap.
     * @throws NoSuchElementException If the heap is empty.
     */
    public int popMax() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        int max = heap.get(0);
        int lastElement = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            heapifyDown(0);
        }
        return max;
    }

    /**
     * Maintains the heap property by moving the element at the given index up.
     *
     * @param index The index of the element to heapify up.
     */
    private void heapifyUp(int index) {
        int currentIndex = index;
        while (currentIndex > 0) {
            int parentIndex = getParentIndex(currentIndex);
            if (heap.get(currentIndex) > heap.get(parentIndex)) {
                swap(currentIndex, parentIndex);
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    /**
     * Maintains the heap property by moving the element at the given index down.
     *
     * @param index The index of the element to heapify down.
     */
    private void heapifyDown(int index) {
        int currentIndex = index;
        while (true) {
            int largest = currentIndex;
            for (int i = 1; i <= branchingFactor; i++) {
                int childIndex = getChildIndex(currentIndex, i);
                if (childIndex < heap.size() && heap.get(childIndex) > heap.get(largest)) {
                    largest = childIndex;
                }
            }
            if (largest != currentIndex) {
                swap(currentIndex, largest);
                currentIndex = largest;
            } else {
                break;
            }
        }
    }

    /**
     * Calculates the parent index of a given node.
     *
     * @param index The index of the current node.
     * @return The index of the parent node.
     */
    private int getParentIndex(int index) {
        return (index - 1) / branchingFactor;
    }

    /**
     * Calculates the child index based on the parent index and child number.
     *
     * @param parentIndex The index of the parent node.
     * @param childNumber The number of the child (1 to branchingFactor).
     * @return The index of the child node.
     */
    private int getChildIndex(int parentIndex, int childNumber) {
        return branchingFactor * parentIndex + childNumber;
    }

    /**
     * Swaps two elements in the heap.
     *
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Returns the number of elements in the heap.
     *
     * @return The size of the heap.
     */
    public int size() {
        return heap.size();
    }

    /**
     * Checks if the heap is empty.
     *
     * @return True if the heap is empty; otherwise, false.
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Retrieves the maximum element without removing it.
     *
     * @return The maximum element in the heap.
     * @throws NoSuchElementException If the heap is empty.
     */
    public int peekMax() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        return heap.get(0);
    }

    /**
     * Returns the exponent used to determine the branching factor.
     *
     * @return The exponent value.
     */
    public int getExponent() {
        return exponent;
    }

    /**
     * Returns a string representation of the heap, including the exponent.
     *
     * @return The heap as a string.
     */
    @Override
    public String toString() {
        return "PowerOfTwoMaxHeap{" +
                "heap=" + heap +
                ", exponent=" + exponent +
                ", branchingFactor=" + branchingFactor +
                '}';
    }

    /**
     * Main method for demonstrating and testing the heap functionality.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Example: Create a max heap with exponent 2 (each parent has 4 children)
        PowerOfTwoMaxHeap maxHeap = new PowerOfTwoMaxHeap(2);

        // Insert elements into the heap
        maxHeap.insert(10);
        maxHeap.insert(20);
        maxHeap.insert(5);
        maxHeap.insert(30);
        maxHeap.insert(15);

        System.out.println("Heap after insertions: " + maxHeap);

        // Retrieve and remove the maximum element
        System.out.println("Max element: " + maxHeap.popMax());
        System.out.println("Heap after popMax: " + maxHeap);

        // Continue operations
        maxHeap.insert(25);
        System.out.println("Heap after inserting 25: " + maxHeap);
        System.out.println("Max element: " + maxHeap.popMax());
        System.out.println("Final heap: " + maxHeap);
    }
}
