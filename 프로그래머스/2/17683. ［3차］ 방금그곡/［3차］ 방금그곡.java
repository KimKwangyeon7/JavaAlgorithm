class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int t = Integer.MIN_VALUE;
        for (String music: musicinfos){
            String[] info = music.split(",");
            int time = checkTime(info[0], info[1]);
            String chngStr = change(info[3]);
            String newStr = makeNew(change(m).length(), time, chngStr);
            System.out.println(newStr);
            if (check(change(m), newStr)){
                if (time > t){
                    answer = info[2];
                    t = time;
                }
            }
        }
        return answer;
    }
    private static int checkTime(String start, String end){
        String[] s = start.split(":");
        String[] e = end.split(":");
        
        int smin = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
        int emin = Integer.parseInt(e[0]) * 60 + Integer.parseInt(e[1]);
        
        return emin-smin;
    }
    private static String makeNew(int melody, int time, String str){
        String ans = "";
        int v = time / str.length();
        int cnt = 0;
        while (cnt < v){
            ans += str;
            cnt++;
        }

        int r = time % str.length();
        if (r != 0){
            ans += str.substring(0, r);
        }
        
        int a = str.length() / melody;
        if (a > 0){
            if (ans.length() >= str.length()*2){
                return str+str;
            } else {
                return ans;
            }
        } else {
            int l = str.length();
            cnt = 2;
            while (true){
                if (l * cnt >= melody){
                    if (l*cnt*2 < ans.length()){
                        return ans.substring(0, l*cnt*2);
                    } else {
                        return ans;
                    }
                }
                cnt++;
            }
        }
    }
    private static boolean check(String m, String newStr){
        if (m.length() > newStr.length()){
            return false;
        }
        if (m.length() == newStr.length()){
            for (int i = 0; i < newStr.length(); i++){
                if (newStr.charAt(i) != m.charAt(i)){
                    return false;
                }
            }
            return true;
        }
        int val = newStr.length() / m.length();
        for (int i = 0; i < newStr.length(); i++){
            if (newStr.charAt(i) == m.charAt(0)){
                int flag = 0;
                for (int j = 1; j < m.length(); j++){
                    if (i+j == newStr.length()){
                        flag = 1;
                        break;
                    }
                    if (newStr.charAt(i+j) != m.charAt(j)){
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0){
                    return true;
                }
                
            }
        }
        return false;
    }
    private static String change(String str){
        String ans = "";
        for (int i = 0; i < str.length(); i++){
            if (!Character.isAlphabetic(str.charAt(i))){
                ans = ans.substring(0, ans.length()-1);
                if (str.charAt(i-1) == 'A'){
                    ans += 'a';
                } else if (str.charAt(i-1) == 'B'){
                    ans += 'b';
                } else if (str.charAt(i-1) == 'C'){
                    ans += 'c';
                } else if (str.charAt(i-1) == 'D'){
                    ans += 'd';
                } else if (str.charAt(i-1) == 'E'){
                    ans += 'e';
                } else {
                    ans += 'f';
                }
            } else {
                ans += str.charAt(i);
            }
        }
        return ans;
    }
}