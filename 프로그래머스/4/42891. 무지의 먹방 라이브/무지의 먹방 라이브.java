import java.util.*;
class Solution {
    static class Dot implements Comparable<Dot>{
        int idx;
        int time;
        public Dot(int idx, int time){
            this.idx = idx;
            this.time = time;
        }
        public int compareTo(Dot o){
            if (time == o.time){
                return Integer.compare(idx, o.idx);
            }
            return Integer.compare(time, o.time);
        }
    }
    public int solution(int[] food_times, long k) {
        int answer = 0;
        long len = food_times.length;
        List<Integer> list = new ArrayList<>();
        PriorityQueue<Dot> pq = new PriorityQueue<>();
        long sum = 0;
        for (int i = 0; i < len; i++){
            pq.offer(new Dot(i, food_times[i]));
            sum += food_times[i];
        }
        if (sum <= k){
            return -1;
        }
        long cnt = 0;
        long total = len;
        long prev = 0;
        long r = -1;
        while (true){
            if (cnt + (pq.peek().time - prev) * total > k){
                break;
            }
            Dot tmp = pq.poll();
            if (tmp.time == prev){
                total--;
                continue;
            }
            cnt += (tmp.time-prev) * total;
            prev = tmp.time;
            total--;
            
        }
        
        while (!pq.isEmpty()){
            list.add(pq.poll().idx);
        }
        // Collections.sort(list, new Comparator<Dot>() {
        //     @Override
        //     public int compare(Dot a, Dot b) {
        //         return Integer.compare(a.idx, b.idx);
        //     }
        // });
        Collections.sort(list);
        answer = list.get((int)((k-cnt)%total))+1;

        return answer;  
        
    }
}