package arraysandhashing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsAnagramTest {

    IsAnagram2 isAnagram = new IsAnagram2();

    @Test
    void isAnagram() {
        assertTrue(isAnagram.isAnagram("anagram", "nagaram"));
        assertFalse(isAnagram.isAnagram("rat", "car"));
    }
}