import java.util.HashMap;
import java.util.Map;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        // Count total frequency of each number
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Case 1: k == 1
        // Look for the largest number with total frequency of 1
        if (k == 1) {
            int maxVal = -1;
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                if (entry.getValue() == 1) {
                    maxVal = Math.max(maxVal, entry.getKey());
                }
            }
            return maxVal;
        }

        // Case 2: k == n
        // Only one subarray of size k exists, return the maximum element
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }

        // Case 3: 1 < k < n
        // Only the first and last elements can appear in exactly one subarray
        int first = nums[0];
        int last = nums[n - 1];
        int maxVal = -1;

        if (countMap.get(first) == 1) {
            maxVal = Math.max(maxVal, first);
        }
        if (countMap.get(last) == 1) {
            maxVal = Math.max(maxVal, last);
        }

        return maxVal;
    }
}