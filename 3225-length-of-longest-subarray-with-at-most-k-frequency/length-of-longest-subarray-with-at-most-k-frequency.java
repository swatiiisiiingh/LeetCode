import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            int current = nums[right];
            freq.put(current, freq.getOrDefault(current, 0) + 1);

            // Shrink window from the left if frequency exceeds k
            while (freq.get(current) > k) {
                int leftNum = nums[left];
                freq.put(leftNum, freq.get(leftNum) - 1);
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}