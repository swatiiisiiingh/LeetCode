class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + nums[i];

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int required = target + prefix[i];
            int bound = Arrays.binarySearch(prefix, required);

            if (bound < 0)
                bound = -bound - 1;

            if (bound <= n)
                ans = Math.min(ans, bound - i);
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}