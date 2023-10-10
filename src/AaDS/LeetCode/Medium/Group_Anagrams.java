package AaDS.LeetCode.Medium;

import java.util.*;

class Group_Anagrams {
    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs){
            char[] word = s.toCharArray();
            Arrays.sort(word);
            String sortedWord = new String(word);

            if(!map.containsKey(sortedWord)){
                map.put(sortedWord, new ArrayList<>());
            }

            map.get(sortedWord).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
