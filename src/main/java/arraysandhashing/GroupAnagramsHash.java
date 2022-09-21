package arraysandhashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagramsHash {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> response = new ArrayList<>();
        if(strs.length == 0) return response;

        Map<String, List<String>> anagramMap = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            String hashedString = hash(strs[i]);
            anagramMap.computeIfAbsent(hashedString, k -> new ArrayList<>());
            anagramMap.get(hashedString).add(strs[i]);
        }

        response.addAll(anagramMap.values());
        return response;
    }

    private static String hash(String inputString) {
        char[] hash = new char[26];
        for (char c : inputString.toCharArray()) {
            hash[c - 'a']++;
        }
        return new String(hash);
    }
}
