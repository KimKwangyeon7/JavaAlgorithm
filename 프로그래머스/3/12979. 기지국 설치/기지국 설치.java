class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int s = 1;
        int cnt = 0;
        int e = 0;
        for(int i = 0; i < stations.length; i++) {
            if(s < stations[i] - w){
                e = stations[i] - w - 1;
                cnt = (e - s + 1) / (2 * w + 1);
                if((e - s + 1) % (2 * w + 1) > 0){ 
                    cnt++;
                }
                answer += cnt;
            }
            s = stations[i] + w + 1;
        }
        if(stations[stations.length - 1] + w < n){
            s = stations[stations.length - 1] + w + 1;
            e = n;
            cnt = (e - s + 1) / (2 * w + 1);
            if((e - s + 1) % (2 * w + 1) > 0){ 
                cnt++;
            }
            answer += cnt;
        }
        return answer;
    }
}