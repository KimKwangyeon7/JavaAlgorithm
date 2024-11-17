import java.util.*;
class Solution {
    public int[] solution(String msg) {
        List<Integer> list = new ArrayList<>();
        int cnt = 27;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; i++){
            int c = 'A' + i;
            //System.out.println((char)c);
            map.put("" + (char)c, i+1);
        }
        int len = msg.length();
        for (int i = 0; i < len; i++){
            //String str = "" + msg.charAt(i);
            StringBuilder sb = new StringBuilder();
            sb.append(msg.charAt(i));
            //int tmp = map.get(str);
            int tmp = map.get(sb.toString());
            if (i == len-1){
                list.add(tmp);
                break;
            }
            for (int j = i+1; j < len; j++){
                sb.append(msg.charAt(j));
                if (map.containsKey(sb.toString())){
                    if (j == len-1){
                        list.add(map.get(sb.toString()));
                        i = len;
                        break;
                    }
                    tmp = map.get(sb.toString());
                    continue;
                }
                if (j == len-1){
                    list.add(tmp);
                    list.add(map.get("" + msg.charAt(j)));
                    i = len;
                    break;
                }
                map.put(sb.toString(), cnt++);
                list.add(tmp);
                i = j-1;
                break;
            }
            if (i == len){
                break;
            }
        }
        int[] answer = new int[list.size()];
        int idx = 0;
        for (int a: list){
            answer[idx++] = a;
        }
        return answer;
    }
}