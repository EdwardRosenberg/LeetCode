package heap;

import java.util.PriorityQueue;
import java.util.Queue;

class MedianFinder {

    private final Queue<Integer> smallHeap; //small elements - maxHeap
    private final Queue<Integer> largeHeap; //large elements - minHeap

    public MedianFinder() {
        smallHeap = new PriorityQueue<>((a, b) -> b - a); // Max heap (descending order)
        largeHeap = new PriorityQueue<>((a, b) -> a - b); // Min heap (ascending order)
    }

    public void addNum(int num) {
        smallHeap.add(num);
        balanceHeaps();
    }

    public double findMedian() {
        if (smallHeap.isEmpty() && largeHeap.isEmpty()) {
            // If both heaps are empty, return NaN
            return Double.NaN;
        }

        if (smallHeap.size() == largeHeap.size()) {
            // If the sizes of both heaps are equal, return the average of their top elements
            return (double) (largeHeap.peek() + smallHeap.peek()) / 2;
        } else if (smallHeap.size() > largeHeap.size()) {
            // If smallHeap is larger, return its top element as the median
            return (double) smallHeap.peek();
        } else {
            // If largeHeap is larger, return its top element as the median
            return (double) largeHeap.peek();
        }
    }

    private void balanceHeaps() {

        if (smallHeap.size() - largeHeap.size() > 1 ||
                !largeHeap.isEmpty() && smallHeap.peek() > largeHeap.peek()) {
            // If the size difference between heaps is greater than 1, or if smallHeap's top element
            // is greater than largeHeap's top element, move smallHeap's top element to largeHeap
            largeHeap.add(smallHeap.poll());
        }

        if (largeHeap.size() - smallHeap.size() > 1) {
            // If the size difference between heaps is greater than 1 in favor of largeHeap, move
            // largeHeap's top element to smallHeap
            smallHeap.add(largeHeap.poll());
        }
    }
}
