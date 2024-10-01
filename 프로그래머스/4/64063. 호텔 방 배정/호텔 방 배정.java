import java.util.*;
class Solution {
    static HashMap<Long, Long> map;
    public long[] solution(long k, long[] room_number) {
        int len = room_number.length;
        long[] answer = new long[len];
        TreeSet<Long> set = new TreeSet<>();
        
//         for (int i = 0; i < len; i++){
//             if (set.contains(room_number[i])){
//                 Long max = set.last();
//                 // 원하는 방번호가 set에서 가장 큰 경우
//                 if (room_number[i] == max){
//                     set.add(room_number[i]+1);
//                     answer[i] = room_number[i] + 1;
//                     continue;
//                 }
//                 // 원하는 방번호 다음으로 큰 번호
//                 Long next = set.higher(room_number[i]);
//                 if (room_number[i]+1 == next){
//                     while (true){
//                         Long nnext = set.higher(next);
//                         if (next == max || nnext != next+1){
//                             set.add(next+1);
//                             answer[i] = next + 1;
//                             break;
//                         }
//                         next = nnext;
//                     }
//                 } else {
//                     set.add(room_number[i]+1);
//                     answer[i] = room_number[i] + 1;
//                     continue;
//                 }
               
//             } else {
//                 set.add(room_number[i]);
//                 answer[i] = room_number[i];
//             }
        
//        }
        map = new HashMap<>();
        
        for (int i = 0; i < len; i++){
            answer[i] = check(room_number[i]);
        }
        return answer;
    }
    private static long check(long x){
        if (!map.containsKey(x)){
            map.put(x, x+1);
            return x;
        }
        
        long res = check(map.get(x));
        map.put(x, res);
        return res;
    }
}