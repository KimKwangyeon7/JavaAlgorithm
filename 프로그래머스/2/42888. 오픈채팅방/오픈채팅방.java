import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        //String[] answer = {};
        StringBuilder sb = new StringBuilder();
        HashMap<String, String> map = new HashMap<>();
        for (String r: record){
            String[] tmp = r.split(" ");
            if (tmp[0].charAt(0) == 'E'){
                map.put(tmp[1], tmp[2]);
            } else if (tmp[0].charAt(0) == 'C'){
                map.put(tmp[1], tmp[2]);
            }
        }
        
        for (String r: record){
            String[] tmp = r.split(" ");
            if (r.charAt(0) == 'E'){
                sb.append(map.get(tmp[1])).append("님이 들어왔습니다.-");
            } else if (r.charAt(0) == 'L'){
                sb.append(map.get(tmp[1])).append("님이 나갔습니다.-");
            }
        }
        String[] answer = sb.toString().split("-");
        return answer;
    }
}