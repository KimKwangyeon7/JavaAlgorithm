import java.util.*;
class Solution {
    static class Dot implements Comparable<Dot>{
        int idx;
        int stone;
        int iron;
        int dia;
        
        public Dot(int idx, int stone, int iron, int dia){
            this.idx = idx;
            this.stone = stone;
            this.iron = iron;
            this.dia = dia;
        }
        public int compareTo(Dot o){
            if (this.dia == o.dia){
                if (this.iron == o.iron){
                    return Integer.compare(o.stone, this.stone);
                }
                return Integer.compare(o.iron, this.iron);
            }
            return Integer.compare(o.dia, this.dia);
        }
    }
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        PriorityQueue<Dot> pq = new PriorityQueue<>();
        int len = minerals.length;
        for (int i = 0; i < len; i+=5){
            int stoneCnt = 0;
            int ironCnt = 0;
            int diaCnt = 0;
            for (int j = i; j < i+5 && j < len; j++){
                if (minerals[j].charAt(0) == 'd'){
                    diaCnt++;
                } else if (minerals[j].charAt(0) == 'i'){
                    ironCnt++;
                } else {
                    stoneCnt++;
                }
            }
            Dot tmp = new Dot(i, stoneCnt, ironCnt, diaCnt);
            pq.offer(tmp);
        }
        int size = picks[0] + picks[1] + picks[2];
        while (!pq.isEmpty()){
            Dot tmp = pq.poll();
            if (tmp.idx >= 5*size){
                continue;
            }
            if (picks[0] != 0){
                picks[0]--;
                answer += tmp.dia + tmp.stone + tmp.iron;
            } else if (picks[1] != 0){
                picks[1]--;
                answer += tmp.dia*5 + tmp.stone + tmp.iron;
            } else if (picks[2] != 0){
                picks[2]--;
                answer += tmp.dia*25 + tmp.iron*5 + tmp.stone;
            } else {
                break;
            }
        }
        return answer;
    }
}