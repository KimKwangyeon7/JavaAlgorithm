import java.util.HashSet;
class Solution {
    static int min;
    public int solution(String s) {
        //int answer = 0;
        int len = s.length();
        
        min = len;
        if (len <= 2){
            return len;
        } else if (len == 3){
            if (s.charAt(0) == s.charAt(1) && s.charAt(1) == s.charAt(2)){
                return 2;
            } else {
                return 3;
            }
        } else {
            for (int i = 1; i <= len/2; i++){
                min = Math.min(min, zip(i, s));
            }
        }
        
        return min;
    }
    static int zip(int size, String s){
        HashSet<String> set = new HashSet<>();
        int cnt = 0;
        int idx = 0;
        int len = s.length();
        int flag = 1;
        while (true){
            if (min <= cnt){
                break;
            }
            if (idx+size <= len){
                String tmp = s.substring(idx, idx+size);
                if (set.contains(tmp)){
                    flag++;
                } else {
                	cnt += check(flag);
                    flag = 1;
                    set = new HashSet<>();
                    set.add(tmp);
                    cnt += size;      
                }
            } else {
                cnt += len - idx;
                break;
            }
            idx = idx + size;
        }

        return cnt + check(flag);
    }
    
    static int check(int flag) {
    	int cnt = 0;
    	if (flag == 1){
            flag = 1;
        }
        else if (flag < 10){
            cnt++;
        } else if (flag < 100){
            cnt += 2;
        } else if (flag < 1000) {
            cnt += 3;
        } else {
            cnt += 4;
        }
    	return cnt;
    }
}