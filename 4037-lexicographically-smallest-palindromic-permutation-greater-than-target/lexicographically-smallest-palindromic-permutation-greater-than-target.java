class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Check if a palindromic permutation is possible
        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }

        if (oddCount > 1) {
            return "";
        }

        int m = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        // Case 1: Try exact match of the first half
        int[] tempCount = halfCount.clone();
        boolean canMatchExact = true;
        for (int i = 0; i < m; i++) {
            int charIdx = target.charAt(i) - 'a';
            if (--tempCount[charIdx] < 0) {
                canMatchExact = false;
                break;
            }
        }

        if (canMatchExact) {
            String firstHalf = target.substring(0, m);
            String candidate = buildPalindrome(firstHalf, midChar, n % 2 != 0);
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        // Case 2: Diverge at the largest possible index i in [m - 1, 0]
        for (int i = m - 1; i >= 0; i--) {
            // Count characters needed to form target[0..i-1]
            int[] prefixCount = new int[26];
            boolean validPrefix = true;
            for (int j = 0; j < i; j++) {
                int charIdx = target.charAt(j) - 'a';
                if (++prefixCount[charIdx] > halfCount[charIdx]) {
                    validPrefix = false;
                    break;
                }
            }

            if (!validPrefix) {
                continue;
            }

            // Available characters remaining after prefix
            int[] rem = new int[26];
            for (int j = 0; j < 26; j++) {
                rem[j] = halfCount[j] - prefixCount[j];
            }

            // Find the smallest character strictly greater than target.charAt(i)
            int targetCharIdx = target.charAt(i) - 'a';
            for (int c = targetCharIdx + 1; c < 26; c++) {
                if (rem[c] > 0) {
                    rem[c]--;
                    StringBuilder firstHalf = new StringBuilder();
                    firstHalf.append(target, 0, i);
                    firstHalf.append((char) ('a' + c));

                    // Append remaining characters in ascending order
                    for (int j = 0; j < 26; j++) {
                        while (rem[j] > 0) {
                            firstHalf.append((char) ('a' + j));
                            rem[j]--;
                        }
                    }

                    return buildPalindrome(firstHalf.toString(), midChar, n % 2 != 0);
                }
            }
        }

        return "";
    }

    private String buildPalindrome(String firstHalf, char midChar, boolean isOdd) {
        StringBuilder sb = new StringBuilder(firstHalf);
        if (isOdd) {
            sb.append(midChar);
        }
        for (int i = firstHalf.length() - 1; i >= 0; i--) {
            sb.append(firstHalf.charAt(i));
        }
        return sb.toString();
    }
}