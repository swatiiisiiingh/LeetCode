class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // last[j] stores the largest index in word1 that can form 
        // an exact match for word2[j...m-1]
        int[] last = new int[m];
        int idx = n - 1;

        for (int j = m - 1; j >= 0; j--) {
            while (idx >= 0 && word1.charAt(idx) != word2.charAt(j)) {
                idx--;
            }
            last[j] = idx;
            idx--; // Move past the matched character for the next iteration
        }

        int[] result = new int[m];
        boolean usedMismatch = false;
        int j = 0;

        for (int i = 0; i < n && j < m; i++) {
            boolean isMatch = word1.charAt(i) == word2.charAt(j);
            boolean canSuffixMatch = (j == m - 1) || (last[j + 1] > i);

            if (isMatch) {
                if (!usedMismatch || canSuffixMatch) {
                    result[j++] = i;
                }
            } else {
                if (!usedMismatch && canSuffixMatch) {
                    usedMismatch = true;
                    result[j++] = i;
                }
            }
        }

        // Return result if a valid sequence of length m was found
        return j == m ? result : new int[0];
    }
}