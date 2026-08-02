package arraysandhashing;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupAnagramsTest {

    // Both implementations must agree on every scenario — looping over them
    // here keeps GroupAnagramsHash (letter-count signature) and
    // GroupAnagrams (sorted signature) in sync without duplicating the
    // whole test file per variant.
    private final List<GroupAnagramsChecker> implementations = List.of(new GroupAnagramsHash(), new GroupAnagrams());

    @Test
    void exampleOne() {
        String[] words = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<Set<String>> expected = List.of(
                Set.of("eat", "tea", "ate"),
                Set.of("tan", "nat"),
                Set.of("bat")
        );
        assertGroupsMatch(expected, words);
    }

    @Test
    void emptyInput() {
        assertGroupsMatch(List.of(), new String[]{});
    }

    @Test
    void singleWord() {
        List<Set<String>> expected = List.of(Set.of("abc"));
        assertGroupsMatch(expected, new String[]{"abc"});
    }

    @Test
    void noWordsShareAGroup() {
        // each word has a unique letter signature, so every group is size 1
        List<Set<String>> expected = List.of(Set.of("abc"), Set.of("def"), Set.of("ghi"));
        assertGroupsMatch(expected, new String[]{"abc", "def", "ghi"});
    }

    @Test
    void largerInput() {
        String[] words = {
                "listen", "silent", "enlist",
                "google", "gogole",
                "banana", "abanan", "nanaba",
                "unique"
        };
        List<Set<String>> expected = List.of(
                Set.of("listen", "silent", "enlist"),
                Set.of("google", "gogole"),
                Set.of("banana", "abanan", "nanaba"),
                Set.of("unique")
        );
        assertGroupsMatch(expected, words);
    }

    // Groups and the order of words within a group are unordered in the LeetCode
    // spec, so compare as a set of sets rather than asserting exact list order.
    private void assertGroupsMatch(List<Set<String>> expected, String[] words) {
        Set<Set<String>> expectedSet = new HashSet<>(expected);
        for (GroupAnagramsChecker grouper : implementations) {
            Set<Set<String>> actualSet = grouper.groupAnagrams(words).stream()
                    .map(HashSet::new)
                    .collect(Collectors.toSet());
            assertEquals(expectedSet, actualSet,
                    grouper.getClass().getSimpleName() + " disagreed for input " + Arrays.toString(words));
        }
    }
}
