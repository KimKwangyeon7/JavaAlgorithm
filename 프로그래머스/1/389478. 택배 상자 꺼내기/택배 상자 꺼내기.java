class Solution {
    public int solution(int n, int w, int num) {
        int answer = 1;
        int total = n / w + 1;
        int floor = num / w + 1;
        int cha = total - floor;
        
        if (cha == 0){
            return 1;
        }
        while (true){
            if (num % w == 0){
                num += 1;
            } else {
                num += (w-(num%w)) * 2 + 1;
            }
            
            if (num <= n){
                answer++;
            } else {
                break;
            }
        }
        return answer;
    }
}