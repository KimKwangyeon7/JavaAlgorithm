class Solution
{
    public int solution(String s)
    {
        int answer = 0;
        int len = s.length();
        for (int i = len; i >= 2; i--){
            int start = 0;
            int end = start+i-1;
            
            for (int j = end; j < len; j++){
                int x = start;
                int y = j;
                int flag = 0;
                
                while (x <= y){
                    if (s.charAt(x) != s.charAt(y)){
                        flag = 1;
                        break;   
                    }
                    x++;
                    y--;
                }
                
                if (flag == 1){
                    start++;
                    continue;
                }
                return i;
            }
        }
        return 1;
    }
}