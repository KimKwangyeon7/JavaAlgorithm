import java.util.*;
class Solution {
    static class Dot implements Comparable<Dot>{
        double start;
        double end;
        double gap;
        
        public Dot(double start, double end, double gap){
            this.start = start;
            this.end = end;
            this.gap = gap;
        }
        
        public int compareTo(Dot o){
            return Double.compare(start, o.start);
        }
    }
    public int solution(String[] lines) {
        int answer = 0;
        //double[] starts = new double[lines.length];
        //double[] ends = new double[lines.length];
        if (lines.length == 1){
            return 1;
        }
        PriorityQueue<Dot> pq = new PriorityQueue<>();
        List<Double> starts = new ArrayList<>();
        List<Double> ends = new ArrayList<>();
        //int cnt = 0;
        double max = Double.MIN_VALUE;
        
        for (String line: lines){
            String[] tmp = line.split(" ");
            double e = convert(tmp[1]) + 3.0;
            // //System.out.println(e);
            // max = Math.max(max, e+1);
            double g = Double.parseDouble(tmp[2].substring(0, tmp[2].length()-1));
            // //System.out.println(g);
            double s = Math.round((e - g + 0.001) * 1000) / 1000.0;
            // //System.out.println(s);
            // pq.offer(new Dot(s, e+1, g));
            starts.add(s);
            double y = Math.round((e + 0.999) * 1000) / 1000.0;
            ends.add(y);
        }
        Collections.sort(starts);
        Collections.sort(ends);
        double time = 0;
        int order = 0;
        int cnt = 0;
        for (double s: starts){
            time = s;
            cnt++;
            if (ends.get(order) < time){
                cnt--;
                order++;
            }
            answer = Math.max(answer, cnt);
        }
        
//         double start = pq.peek().start;
//         //System.out.println(start);
//         //System.out.println(max);
//         double r = start - (int)start;
//         int len = (int)max - (int)start + 1;
//         int[] cnt = new int[len];
//         int sidx = 0;
//         int eidx = 0;
//         while (!pq.isEmpty()){
//             Dot tmp = pq.poll();
//             double sr = tmp.start - (int)tmp.start;
//             double er = tmp.end - (int)tmp.end;
//             //System.out.println(tmp.start + " " + tmp.end);
//             if (r <= sr){
//                 sidx = (int)tmp.start - (int)start;
//             } else {
//                 sidx = (int)tmp.start - (int)start - 1;
//             }
            
//             if (r <= er){
//                 eidx = (int)tmp.end - (int)start;
//             } else {
//                 eidx = (int)tmp.end - (int)start - 1;
//             }
            
//             for (int k = sidx; k <= eidx; k++){
//                 cnt[k]++;
//                 answer = Math.max(answer, cnt[k]);
//             }    
//        }
        return answer;
    }
    private static double convert(String str){
        String[] tmp = str.split(":");
        double time = Double.parseDouble(tmp[0]) * 3600 + Double.parseDouble(tmp[1]) * 60 + Double.parseDouble(tmp[2]);
        return time;
    }
}