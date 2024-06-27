import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, Integer> ans = new HashMap<>();
        
        int len = records.length;
        for (int i = 0; i < len; i++){
            String carNum = records[i].substring(6, 10);
            if (map.containsKey(carNum)){ // 이미 있으면 출차
                String timeIn = map.get(carNum);
                int time = (Integer.valueOf(records[i].substring(0, 2)) * 60 + Integer.valueOf(records[i].substring(3, 5))) - (Integer.valueOf(timeIn.substring(0, 2)) * 60 + Integer.valueOf(timeIn.substring(3, 5))) ;

                if (ans.containsKey(carNum)){
                    int oldVal = ans.get(carNum);
                    ans.replace(carNum, oldVal + time);
                } else {
                    ans.put(carNum, time);
                }
                map.remove(carNum);
            } else { // 없으면 넣고
                map.put(carNum, records[i].substring(0, 5));
            }
        }
        List<String> keys = new ArrayList<>(map.keySet());
        for (String key: keys){
            String timeIn = map.get(key);
            int time = 1439 - (Integer.valueOf(timeIn.substring(0, 2)) * 60 + Integer.valueOf(timeIn.substring(3, 5)));
 
            if (ans.containsKey(key)){
                int oldVal = ans.get(key);
                ans.replace(key, oldVal + time);
            } else {
                ans.put(key, time);
            }  
        } 
        
        List<String> keySet = new ArrayList<>(ans.keySet());
        Collections.sort(keySet);
        System.out.println(keySet.size());
        int[] answer = new int[keySet.size()];
        for (int i = 0; i < keySet.size(); i++) {
            int timeSum = ans.get(keySet.get(i));
            if (timeSum <= fees[0]){
                answer[i] = fees[1];
            } else {
                int tmp = (timeSum-fees[0]) / fees[2];
                if (((timeSum-fees[0]) % fees[2]) == 0){
                    answer[i] = fees[1] + fees[3]*tmp;
                } else {
                    answer[i] = fees[1] + fees[3]*(tmp+1);
                }
            }
        }
        return answer;
    }
}