class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] totalCount = new int[26];
        for (int i = 0; i < n; i++) {
            totalCount[s.charAt(i) - 'a']++;
        }

        // Determine how many characters of target can be formed sequentially from left
        int[] prefixCount = totalCount.clone();
        int maxPrefixLen = 0;
        for (int i = 0; i < n; i++) {
            int idx = target.charAt(i) - 'a';
            if (prefixCount[idx] > 0) {
                prefixCount[idx]--;
                maxPrefixLen++;
            } else {
                break;
            }
        }

        // Try divergence index i from right to left
        for (int i = Math.min(maxPrefixLen, n - 1); i >= 0; i--) {
            int[] available = totalCount.clone();
            for (int j = 0; j < i; j++) {
                available[target.charAt(j) - 'a']--;
            }

            int targetChar = target.charAt(i) - 'a';
            int chosenChar = -1;

            // Find the smallest character strictly greater than target[i]
            for (int c = targetChar + 1; c < 26; c++) {
                if (available[c] > 0) {
                    chosenChar = c;
                    break;
                }
            }

            if (chosenChar != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.substring(0, i));
                sb.append((char) ('a' + chosenChar));
                available[chosenChar]--;

                // Append remaining characters in ascending order
                for (int c = 0; c < 26; c++) {
                    while (available[c] > 0) {
                        sb.append((char) ('a' + c));
                        available[c]--;
                    }
                }
                return sb.toString();
            }
        }

        return "";
    }
}