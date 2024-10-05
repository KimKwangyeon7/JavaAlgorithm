import java.util.*;

class Solution {
    static Map<String, List<Integer>> map;
    public int[] solution(String[] info, String[] query) {
        int qlen = query.length;
        int ilen = info.length;
        int[] answer = new int[qlen];
        map = new HashMap<>();
        for (int i = 0; i < ilen; i++){
            String[] tmp = info[i].split(" ");
            dfs(0, tmp, "");
        }
        //System.out.println(map.get("----").length);
        for (String key: map.keySet()){
            Collections.sort(map.get(key));
        }
        for (int i = 0; i < qlen; i++){
            String[] tmp = query[i].split(" ");
            String str = tmp[0] + tmp[2] + tmp[4] + tmp[6];
            //System.out.println(tmp[0] + tmp[2] + tmp[4] + tmp[6]);
            if (map.containsKey(str)){
                //System.out.println("존재!" + map.get(str).length);
                List<Integer> nums = map.get(str);
                //Collections.sort(nums);
                answer[i] = binSearch(Integer.parseInt(tmp[7]), nums);
            } else {
                answer[i] = 0;
            }
        }
        return answer;
    }

    private static void dfs(int L, String[] tmp, String str){
        if (L == 4){
            if (map.containsKey(str)){
                List<Integer> a = map.get(str);
                a.add(Integer.parseInt(tmp[4]));
                map.put(str, a);
            } else {
                List<Integer> a = new ArrayList<>();
                a.add(Integer.parseInt(tmp[4]));
                map.put(str, a);
            }
            return;
        }
        dfs(L+1, tmp, str+tmp[L]);
        dfs(L+1, tmp, str+"-");
    }
    private static int binSearch(int target, List<Integer> nums){
        int left = 0;
        int right = nums.size()-1;
        //int mid = 0;
        while (left <= right){
            int mid = (left + right) / 2;
            if (nums.get(mid) < target){
                left = mid + 1;
            } else {
                right = mid-1;
            } 
        }
        return nums.size() - left;
        // if (nums[mid] >= target){
        //     return (nums.length-1) - mid + 1;
        // } else{
        //     return (nums.length-1) - mid;
        // }        
    }
}