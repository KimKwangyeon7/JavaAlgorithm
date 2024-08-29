class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        int cnt = 0;
        for (long num: numbers){
            if (num % 2 == 0){
                answer[cnt] = num+1;
                
            } else {
                long tmp = num;
                String str = toBin(num);
                int idx = 0;
                long plus = 0;
                long minus = 0;
                for (int i = str.length()-1; i >= 0; i--){
                    if (str.charAt(i) == '0'){
                        plus = (long)Math.pow(2, idx);
                        minus = (long)Math.pow(2, idx-1);
                        break;
                    }
                    idx++;
                }
                answer[cnt] =  num + plus - minus;
            }
            cnt++;
        }
        return answer;
    }
    private static String toBin(long x){
        String str = "";
        while (x > 0){
            if (x % 2 == 0){
                str = "0" + str;
                x /= 2;
            } else {
                str = "1" + str;
                x /= 2;
            }
        }
        return "0"+str;
    }
    private static long toDec(String bin){
        long num = 0;
        int cnt = 0;
        for (int i = bin.length()-1; i >= 0; i--){
            if (bin.charAt(i) == '1'){
                num += Math.pow(2, cnt);
            } 
            cnt++;
        }
        return num;
    }
}