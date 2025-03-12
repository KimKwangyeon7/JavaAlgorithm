class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        int max = Math.max(wallet[0], wallet[1]);
        int min = Math.min(wallet[0], wallet[1]);
        
        if (max >= Math.max(bill[0], bill[1]) && min >= Math.min(bill[0], bill[1])){
            return 0;
        }
        
        while (true){
            if (bill[0] > bill[1]){
                bill[0] /= 2;
            } else {
                bill[1] /= 2;
            }
            
            answer++;
            if (max >= Math.max(bill[0], bill[1]) && min >= Math.min(bill[0], bill[1])){
                break;
            }
        }
        return answer;
    }
}