import java.util.HashMap;
import java.util.Map;
class Solution{
    public int lengthOfLongestSubstring(String s){
        Map<Character, Integer> lastSeen = new HashMap<>();
        int left=0;
        int maxLen=0;
        for (int right=0; right<s.length(); right++){
            char currentChar=s.charAt(right);
            if (lastSeen.containsKey(currentChar) && lastSeen.get(currentChar) >= left) {
                left = lastSeen.get(currentChar) + 1;
            }
            lastSeen.put(currentChar,right);
            maxLen=Math.max(maxLen,right-left+1);
        }
        return maxLen;
    }    
}    
    
