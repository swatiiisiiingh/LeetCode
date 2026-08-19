import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Map row number to a bitmask of occupied seats (columns 2 to 9)
        Map<Integer, Integer> rowMap = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            
            // Ignore seats 1 and 10 as they do not affect 4-person groups
            if (col >= 2 && col <= 9) {
                // Set the corresponding bit (col - 2 gives index 0 to 7)
                rowMap.put(row, rowMap.getOrDefault(row, 0) | (1 << (col - 2)));
            }
        }
        
        // Bitmasks for the 3 valid group positions relative to cols [2..9]:
        // Left   [2, 3, 4, 5] -> bits 0, 1, 2, 3 -> binary 00001111 (0x0F / 15)
        // Right  [6, 7, 8, 9] -> bits 4, 5, 6, 7 -> binary 11110000 (0xF0 / 240)
        // Middle [4, 5, 6, 7] -> bits 2, 3, 4, 5 -> binary 00111100 (0x3C / 60)
        int leftMask = 0b00001111;
        int rightMask = 0b11110000;
        int middleMask = 0b00111100;
        
        // Any row not in rowMap is completely empty and can host 2 families
        int totalFamilies = (n - rowMap.size()) * 2;
        
        for (int mask : rowMap.values()) {
            boolean canFitLeft = (mask & leftMask) == 0;
            boolean canFitRight = (mask & rightMask) == 0;
            boolean canFitMiddle = (mask & middleMask) == 0;
            
            if (canFitLeft && canFitRight) {
                totalFamilies += 2;
            } else if (canFitLeft || canFitRight || canFitMiddle) {
                totalFamilies += 1;
            }
        }
        
        return totalFamilies;
    }
}