import java.util.HashSet;
import java.util.Set;

class Solution {
    public int missingInteger(int[] nums) {
        // Step 1: Find the longest sequential prefix sum
        int prefixSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                prefixSum += nums[i];
            } else {
                break;
            }
        }

        // Step 2: Put all elements in a HashSet for O(1) lookup
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // Step 3: Find the smallest integer >= prefixSum not in the array
        int ans = prefixSum;
        while (set.contains(ans)) {
            ans++;
        }

        return ans;
    }
}