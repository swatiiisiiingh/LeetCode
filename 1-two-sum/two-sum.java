import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Create a HashMap to store number and its index
        Map<Integer, Integer> numMap = new HashMap<>();

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];
            int complement = target - currentNum;

            // Check if the complement exists in the map
            if (numMap.containsKey(complement)) {
                // If found, we have our two numbers. Return their indices.
                return new int[] { numMap.get(complement), i };
            }

            // If complement not found, add the current number and its index to the map
            numMap.put(currentNum, i);
        }

        // This part should theoretically not be reached due to the problem constraints
        // (exactly one solution exists).
        return new int[] {}; 
    }
}