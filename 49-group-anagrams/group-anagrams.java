import java.util.*;
class Solution{
    public List<List<String>> groupAnagrams(String[] strs){
        Map<String,List<String>>mp=new HashMap<>();

        for(String s:strs){
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String temp = new String(chars);

            mp.computeIfAbsent(temp, k-> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(mp.values());
    }
}