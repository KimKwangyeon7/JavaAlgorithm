import java.util.*;
class Solution {
    public String solution(long n, String[] bans) {
        StringBuilder sb = new StringBuilder();
        Queue<String> qu = new PriorityQueue<>((o1, o2) -> {
            if (o1.length() == o2.length()){
                return o1.compareTo(o2);
            }
            return Integer.compare(o1.length(), o2.length());
        });
        for (String b: bans){
            qu.offer(b);
            //System.out.println(b + " " + check(b,b.length()));
        }
        int cnt = 1;
        long sum = 0;
        while (true){
            sum += Math.pow(26, cnt);
            if (n < sum){
                break;
            }
            cnt++;
        }
        int banCnt = 0;
        while (!qu.isEmpty()){
            String tmp = qu.poll();
            if (tmp.length() < cnt){
                banCnt++;
            } else if (tmp.length() == cnt){
                long res = check(tmp, cnt);
                if (res <= n+banCnt){
                    banCnt++;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        sb.append(make(n+banCnt));
        return sb.toString();
    }
    private static String make(long n){
        StringBuilder sb = new StringBuilder();
        while (n > 0){
            long r = n % 26;
            n = n / 26;
            
            if (r == 0){
                r = 26;
                n -= 1;
            }
            char ch =(char)('a' + (r-1));
            //System.out.println(r + " " + ch);
            sb.append(ch);
        }
        return sb.reverse().toString();
    }
    private static long check(String tmp, int cnt){
        StringBuilder sb = new StringBuilder();
        sb.append(tmp);
        long res = 0L;
        while (cnt > 0){
            char ch = sb.charAt(cnt-1);
            int t = (ch - 'a') + 1;
            res += t * Math.pow(26, sb.length()-cnt);
            cnt--;
        }
        return res;
    }
}