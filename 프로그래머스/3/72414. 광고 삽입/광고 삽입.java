
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";    
        int[] time = new int[360001];
        
        //누적합 진행 먼저, 스타팅과 끝나는지점+1에 포인트찍기
        for (String log: logs){
            String[] l = log.split("-");
            int s = toSec(l[0]);
            int e = toSec(l[1]);
          
            time[s]++;
            time[e]--;
        }
        
        int e = toSec(play_time);
        int adv = toSec(adv_time);
        int max_start = e - adv;
        
        //포인트를 기반으로 누적합 진행
        for (int i = 1; i < time.length; i++){
            time[i] += time[i-1];
        }
        
        
        long max = 0;  
        long cur = max;
        int sp = 0;
        
        for (int i = adv; i <= e; i++){
 
           
            cur += time[i] - time[i-adv];
            
            if (cur > max){
                //광고 종료타이밍이 개구간이라 +1해야한다..
                sp = i - adv + 1;
                max = cur;       
            }
            
        }   
        answer=toTime(sp);
      
        return answer;
    }
    
    private static int toSec(String time){
        String[] t = time.split(":");
        // 시분초를 초로 바꿔서 반환
        return Integer.parseInt(t[2]) + Integer.parseInt(t[1])*60 + Integer.parseInt(t[0])*60*60;
    }
    
    private static String toTime(int time){
    	// 시 구하기
        int h = time/3600;
        String str = "" + h;
        if (h < 10) { // 한자리일 경우 앞에 0 붙여주기
        	str = "0" + str;
        }
        // 시간에서 시만큼 빼기       
        time -= h * 3600;
        
        // 분 구하기
        int m = time / 60;
        String str2 = "" + m;       
        if (m < 10) {
        	str2 = "0" + str2;
        }
        time -= m * 60;
        
        // 초 구하기
        String str3 = "" + time;
        if(time<10) {
        	str3 = "0" + str3;
        }
        
        return str + ":" + str2 + ":" + str3;
    }
  
}
