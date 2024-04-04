package heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedianFinderTest {

    @Test
    public void testFindMedian() {
        // Create a MedianFinder object
        MedianFinder medianFinder = new MedianFinder();

        // Add numbers to the stream
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        medianFinder.addNum(3);

        // Test the findMedian method
        assertEquals(2.0, medianFinder.findMedian());

        // Add more numbers to the stream
        medianFinder.addNum(4);
        medianFinder.addNum(5);

        // Test the findMedian method again
        assertEquals(3.0, medianFinder.findMedian());
    }

    @Test
    public void testFindMedianWithEmptyStream() {
        // Create a MedianFinder object
        MedianFinder medianFinder = new MedianFinder();

        // Test findMedian with an empty stream
        assertEquals(Double.NaN, medianFinder.findMedian());
    }

    @Test
    public void testFindMedianWithSingleElement() {
        // Create a MedianFinder object
        MedianFinder medianFinder = new MedianFinder();

        // Add a single element to the stream
        medianFinder.addNum(5);

        // Test findMedian with a single element
        assertEquals(5.0, medianFinder.findMedian());
    }

    @Test
    public void testFindMedianWithEvenNumberOfElements() {
        // Create a MedianFinder object
        MedianFinder medianFinder = new MedianFinder();

        // Add even number of elements to the stream
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        medianFinder.addNum(3);
        medianFinder.addNum(4);

        // Test findMedian with an even number of elements
        assertEquals(2.5, medianFinder.findMedian());
    }
}
