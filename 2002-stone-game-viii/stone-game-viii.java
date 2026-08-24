class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] pref = new int[n];
        pref[0] = stones[0];
        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] + stones[i];
        }

        // Base case: at index n - 1, player must take all stones
        int maxDiff = pref[n - 1];

        // Transition backwards down to index 1 (since x >= 2 stones must be picked)
        for (int i = n - 2; i >= 1; i--) {
            maxDiff = Math.max(maxDiff, pref[i] - maxDiff);
        }

        return maxDiff;
    }
}