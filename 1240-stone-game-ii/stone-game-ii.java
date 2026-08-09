import java.util.Arrays;

class Solution {
    private int[][] memo;
    private int[] suffixSum;

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        
        // Memoization table: memo[i][M]
        // Max value of M needed is N (since M grows up to N)
        memo = new int[n][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Precompute suffix sums: suffixSum[i] stores sum of piles[i...n-1]
        suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        // Alice starts at index 0 with M = 1
        return helper(0, 1, piles);
    }

    private int helper(int i, int M, int[] piles) {
        int n = piles.length;

        // Base case: If remaining piles can all be taken in one turn
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }

        // Return cached result if already calculated
        if (memo[i][M] != -1) {
            return memo[i][M];
        }

        int maxStones = 0;

        // Try taking X piles where 1 <= X <= 2 * M
        for (int X = 1; X <= 2 * M; X++) {
            // Next player's maximum score from remaining piles
            int opponentScore = helper(i + X, Math.max(M, X), piles);
            
            // Current player's score = total remaining stones - opponent's score
            int currentScore = suffixSum[i] - opponentScore;

            maxStones = Math.max(maxStones, currentScore);
        }

        memo[i][M] = maxStones;
        return maxStones;
    }
}