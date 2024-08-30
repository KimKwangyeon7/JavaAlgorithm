import java.util.*;
class Solution {
    static class Dot implements Comparable<Dot>{
        String head;
        String number;
        String tail;
        int ord;
        
        public Dot(String head, String number, String tail, int ord){
            this.head = head;
            this.number = number;
            this.tail = tail;
            this.ord = ord;
        }
        public int compareTo(Dot o){
            if (compHead(this.head, o.head) == 0){ // 같다면
                int a = Integer.parseInt(this.number);
                int b = Integer.parseInt(o.number);
                if (a == b){ // 같다면
                    return Integer.compare(this.ord, o.ord);
                } 
                return Integer.compare(a, b);
            } else {
                return compHead(this.head, o.head);
            }
        }
    }
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        PriorityQueue<Dot> pq = new PriorityQueue<>();
        for (int i = 0; i < files.length; i++){
            String tmp = files[i];
            String head = "";
            int h = 0;
            String number = "";
            int n = 0;
            String tail = "";
            int t;
            for (int j = 0; j < tmp.length(); j++){
                if (Character.isDigit(tmp.charAt(j))){
                    h = j;
                    break;
                }
            }
            head = tmp.substring(0, h);
            
            for (int k = h; k < tmp.length(); k++){
                if (!Character.isDigit(tmp.charAt(k))){
                    n = k;
                    break;
                }
            }
            if (n == 0){
                number = tmp.substring(h);
            } else {
                number = tmp.substring(h, n);
                tail = tmp.substring(n);
            }
            
            pq.offer(new Dot(head, number, tail, i));
        }
        int cnt = 0;
        while (!pq.isEmpty()){
            Dot x = pq.poll();
            String str = x.head + x.number + x.tail;
            answer[cnt] = str;
            cnt++;
        }
        return answer;
    }
    private static int compHead(String a, String b){
        String aa = a.toUpperCase();
        String bb = b.toUpperCase();
        TreeSet<String> set = new TreeSet<>();
        if (aa.length() == bb.length()){
            int flag = 1;
            for (int i = 0; i < aa.length(); i++){
                if (aa.charAt(i) != bb.charAt(i)){
                    flag = 0;
                    break;
                }
            }
            if (flag == 1){
                return 0;
            }
        }
        set.add(aa);
        set.add(bb);
        if (set.first().equals(aa)){
            return -1;
        } else {
            return 1;
        }
    }
}