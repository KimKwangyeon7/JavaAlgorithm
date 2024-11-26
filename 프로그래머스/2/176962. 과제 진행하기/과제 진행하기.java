import java.util.*;
class Solution {
    static class Subject implements Comparable<Subject>{
        String name;
        String time;
        int remain;
        
        public Subject(String name, String time, int remain){
            this.name = name;
            this.time = time;
            this.remain = remain;
        }
        
        public int compareTo(Subject o){
            int minA = minute(this.time);
            int minO = minute(o.time);
            return Integer.compare(minA, minO);
        }
    }
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        PriorityQueue<Subject> pq = new PriorityQueue<>();
        for (String[] plan: plans){
            pq.offer(new Subject(plan[0], plan[1], Integer.parseInt(plan[2])));
        }
        Stack<Subject> stack = new Stack<>();
        Subject prev = pq.poll();
        int now = minute(prev.time) + prev.remain;
        int cnt = 0;
        while (true){
            if (pq.isEmpty()){
                answer[cnt++] = prev.name;
                while (!stack.isEmpty()){
                    answer[cnt++] = stack.pop().name;
                }
                break;
            }
            Subject tmp = pq.poll();
            //System.out.println(tmp.name);
            if (minute(tmp.time) < now){
                //System.out.println(prev.remain);
                prev.remain = now-minute(tmp.time);
                //System.out.println(prev.remain);
                stack.push(prev);
            } else if (minute(tmp.time) > now){
                answer[cnt++] = prev.name;
                int cha = minute(tmp.time) - now;
                while (!stack.isEmpty()){
                    Subject top = stack.pop();
                    if (top.remain > cha){
                        top.remain -= cha;
                        stack.push(top);
                        break;
                    } else if (top.remain == cha){
                        answer[cnt++] = top.name;
                        break;
                    } else {
                        answer[cnt++] = top.name;
                        cha -= top.remain;
                    }
                }
            } else {
                answer[cnt++] = prev.name;
            }
            now = minute(tmp.time) + tmp.remain;
            prev = tmp;
        }
        
        return answer;
    }
    static int minute(String time){
        int hour = Integer.parseInt(time.split(":")[0]);
        int min = Integer.parseInt(time.split(":")[1]);
        return hour * 60 + min;
    }
}