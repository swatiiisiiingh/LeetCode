import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Pair each number with its original index
        int[][] sortedPairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedPairs[i][0] = nums[i];
            sortedPairs[i][1] = i;
        }
        
        // Sort primarily by value
        Arrays.sort(sortedPairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] result = new int[n];
        int i = 0;
        
        while (i < n) {
            int j = i + 1;
            // Find all contiguous elements in sorted order that differ by <= limit
            while (j < n && sortedPairs[j][0] - sortedPairs[j - 1][0] <= limit) {
                j++;
            }
            
            // Extract the original indices for this group and sort them
            List<Integer> indices = new ArrayList<>();
            for (int k = i; k < j; k++) {
                indices.add(sortedPairs[k][1]);
            }
            Collections.sort(indices);
            
            // Place the smallest values into the smallest available original indices
            for (int k = 0; k < indices.size(); k++) {
                result[indices.get(k)] = sortedPairs[i + k][0];
            }
            
            i = j;
        }
        
        return result;
    }
}