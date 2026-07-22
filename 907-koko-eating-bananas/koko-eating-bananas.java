class Solution{
    private int findMax(int[] v){
        int maxi = Integer.MIN_VALUE;
        for (int i=0; i<v.length; i++){
            maxi=Math.max(maxi, v[i]);
        }
        return maxi;
    }
    private long calculateTotalHours(int[] v, int hourly){
        long totalH=0;
        for (int i=0; i<v.length; i++){
            totalH += (v[i] + hourly-1)/hourly;
        }
        return totalH;
    }
    public int minEatingSpeed(int[] piles, int h){
        int low=1;
        int high = findMax(piles);
        while (low<=high){
            int mid=low+(high-low)/2;
            long totalH=calculateTotalHours(piles,mid);

            if(totalH <= h){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return low;
    }
}