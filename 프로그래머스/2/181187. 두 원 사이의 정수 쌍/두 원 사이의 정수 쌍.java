class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        for (int i = r2-1; i >= 1; i--){
            long high = (long)Math.sqrt(Math.pow(r2, 2) - Math.pow(i, 2));
            if (Math.pow(i, 2) > Math.pow(r2, 2)){
                answer += high;
            } else {
                double tmp = Math.sqrt(Math.pow(r1, 2) - Math.pow(i, 2));
                long low = (long)tmp;
                if (tmp - (double)low == 0){
                    answer += high - low + 1;
                } else {
                    answer += high - low;
                }  
            }    
        }
        answer -= 1;
        answer *= 4;
        answer += (r2-r1+1)*4;
        return answer;
    }
}