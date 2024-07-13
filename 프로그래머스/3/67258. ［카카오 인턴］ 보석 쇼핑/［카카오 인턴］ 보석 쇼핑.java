import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int ans = Integer.MAX_VALUE;
        HashMap<String, Integer> map = new HashMap<>();
        //List<String> list = new ArrayList<>();
        int start = 0;
        //String firstStr = gems[0];
        //int end = -1;
        HashSet<String> set = new HashSet<>();
        for (String gem: gems){
            set.add(gem);
        }
        int total = set.size();
        if (total == 1){
            return new int[] {1, 1};
        }
        //System.out.println(total);
        //set = new HashSet<>();
        map.put(gems[0], 1);
        for (int i = 1; i < gems.length; i++){
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
            while (true){
                if (map.get(gems[start]) > 1){ 
                    // if (map.get(gems[start]) == 1){
                    //     map.remove(gems[start]);
                    // }
                    map.put(gems[start], map.get(gems[start])- 1);
                    start++;
                } else {
                    break;
                }
            }            
//             if (!list.isEmpty() && list.get(0).equals(gem)){
//                 list.remove(gem);
//                 start++;
//                 list.add(gem);
//                 end++;
                
//                 while (list.size() >= 2){
//                     String a = list.get(0);
//                     String b = list.get(1);
//                     int len = list.size();
//                     if (a.equals(b)){
//                         list.remove(a);
//                         start++;
//                     } else if (a.equals(list.get(len-1))) {
//                         list.remove(a);
//                         start++;
//                     } else {
//                         break;
//                     }
//                 }
//             } else {
//                 list.add(gem);
//                 set.add(gem);
//                 end++;
//             }
            
            if (map.size() == total){
                if (ans > i-start){
                    ans = i - start;
                    answer[0] = start+1;
                    answer[1] = i+1;
                }
            }
        }
          
        return answer;
    }
}