class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sumDiff = 0;
        int qDiff = 0;

        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            int sign = (i < n / 2) ? 1 : -1;

            if (c == '?') {
                qDiff += sign; // L_? - R_?
            } else {
                sumDiff += sign * (c - '0'); // L_sum - R_sum
            }
        }

        // If total '?' is odd, Alice always wins
        if ((qDiff & 1) != 0) {
            return true;
        }

        // Alice wins if 2 * (L_sum - R_sum) != 9 * (R_? - L_?)
        // In terms of our variables: 2 * sumDiff != -9 * qDiff
        return 2 * sumDiff != -9 * qDiff;
    }
}