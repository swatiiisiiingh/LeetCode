class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean hasNonZero = false;

        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }

        // If total XOR is already non-zero, take the whole array
        if (totalXor != 0) {
            return nums.length;
        }

        // If total XOR is 0, check if we can remove any non-zero element
        return hasNonZero ? nums.length - 1 : 0;
    }
}