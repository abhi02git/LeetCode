class Solution {
    public long maxScore(int[] speed, int[] efficiency, int k) {
        //nums1 as speeed
        //nums2 as efficiency

        long res= 0, sum =0;
        int n = speed.length;
        int[][] score = new int[n][2];

        for(int i=0; i< n; i++){
            score[i][0] = efficiency[i];
            score[i][1] = speed[i];
        }

        //Sorting the score in decreasing order according to efficiency
        // 4 2
        // 3 3
        // 2 1
        // 1 3
        Arrays.sort(score, (a,b) -> b[0] - a[0]);

        //min Priority queue for speed in increasing order
        //smallest speed will be eliminated when spq.poll()
        PriorityQueue<Integer> spq = new PriorityQueue<>(k, (a,b) -> (a - b));

        for(int[] curr : score){
            //curr[0] = min efficiency of all since it is in decreasing order
            //curr[1] = speed

            sum = sum + curr[1];
            spq.add(curr[1]);

            if(spq.size() > k) sum = sum - spq.poll();
            if(spq.size() == k) res = Math.max(res, sum*curr[0]);
        }

        return res;

    }
}

// 4 2
// 3 3
// 2 1
// 1 3

// spq: 2,3,3
// sum: 8
// res = 12


/*

 |------------------------------|
 |-------------TLE--------------|
 |------------------------------|

class Solution {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> list1 = new ArrayList<>();
        List<List<Integer>> list2 = new ArrayList<>();

        backtrack(nums1, list1, new ArrayList<>(), 0, k);
        backtrack(nums2, list2, new ArrayList<>(), 0, k);

        long res = 0;

        for(int i= 0; i< list1.size(); i++){
                int sum = 0;
                int min = list2.get(i).get(0);

                for(int num : list1.get(i)){
                    sum += num;
                }

                for(int num : list2.get(i)){
                    if(num <= min) min = num;
                }

            res = Math.max(res, sum*min);
            
        }
        return res;

    }

    public void backtrack(int[] nums, List<List<Integer>> list, List<Integer> templist, int start, int k){
        if(templist.size() == k) list.add(new ArrayList<>(templist));
        for(int i = start; i< nums.length; i++){
            templist.add(nums[i]);
            backtrack(nums, list, templist, i+1,k);
            templist.remove(templist.size() -1);
        }
    }
}

*/
