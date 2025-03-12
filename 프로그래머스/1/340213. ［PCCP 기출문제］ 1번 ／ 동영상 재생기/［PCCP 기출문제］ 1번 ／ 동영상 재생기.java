import java.util.*;
class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int cur = toSecond(pos);
        int ops = toSecond(op_start);
        int ope = toSecond(op_end);
        int total = toSecond(video_len);
        for (String command: commands){
            if (cur >= ops && cur <= ope){ // 오프닝 구간에 있을 때
                cur = ope;
            }
            if (command.charAt(0) == 'n'){ // next 입력
                cur += 10;
                if (cur > total){
                    cur = total;
                }
            } else if (command.charAt(0) == 'p'){ // prev 입력
                cur -= 10;
                if (cur < 0){
                    cur = 0;
                }
            }
        }
        if (cur >= ops && cur <= ope){ // 오프닝 구간에 있을 때
            cur = ope;
        }
        
        return toTime(cur);
    }
    private int toSecond(String str){
        String[] s = str.split(":");
        return Integer.parseInt(s[0])*60 + Integer.parseInt(s[1]);
    }
    private String toTime(int sec){
        StringBuilder sb = new StringBuilder();
        int h = sec / 60;
        int s = sec % 60;
        
        if (h < 10){
            sb.append("0");
        } 
        sb.append(h).append(":");
        
        if (s < 10){
            sb.append("0");
        }
        sb.append(s);
        
        return sb.toString(); 
    }
}