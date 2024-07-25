import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        int start = 540;
        List<Integer> mins = new ArrayList<>();
        for (int i = 0; i < timetable.length; i++){
            int hour = Integer.parseInt(timetable[i].substring(0,2));
            int min = Integer.parseInt(timetable[i].substring(3));
            mins.add(hour*60 + min);
            //System.out.println(mins[i]);
        }
        Collections.sort(mins);
        
        for (int i = 1; i <= n; i++){
            int arrive = start + (i-1)*t;
            if (i == n){
               if (mins.size() < m){
                   int tmp = arrive;
                   String hour = "" + (tmp / 60);
                   String minute = "" + (tmp % 60);
                   if ((tmp / 60) < 10){
                       hour = "0" + hour;
                   }
                   if ((tmp % 60) < 10){
                       minute = "0" + minute;
                   }
                   
                   return hour + ":" + minute;
               } else {
                    int k = 0;
                    while (k < m-1){
                        int tmp = mins.get(0);
                        if (tmp <= arrive){
                            mins.remove(0);
                            k++;
                        } else {
                            break;
                        }
                    }
                   if (mins.get(0) > arrive){
                       int tmp = arrive;
                       String hour = "" + (tmp / 60);
                       String minute = "" + (tmp % 60);
                       if ((tmp / 60) < 10){
                           hour = "0" + hour;
                       }
                       if ((tmp % 60) < 10){
                           minute = "0" + minute;
                       }

                       return hour + ":" + minute;
                   } else {
                       int tmp = mins.get(0)-1;
                       String hour = "" + (tmp / 60);
                       String minute = "" + (tmp % 60);
                       if ((tmp / 60) < 10){
                           hour = "0" + hour;
                       }
                       if ((tmp % 60) < 10){
                           minute = "0" + minute;
                       }

                       return hour + ":" + minute; 
                   }
               }
    
            } else {
                int k = 0;
                while (k < m && !mins.isEmpty()){
                    int tmp = mins.get(0);
                    if (tmp <= arrive){
                        mins.remove(0);
                        k++;
                    } else {
                        break;
                    }
                }
            }
        }
        return answer;
    }
}