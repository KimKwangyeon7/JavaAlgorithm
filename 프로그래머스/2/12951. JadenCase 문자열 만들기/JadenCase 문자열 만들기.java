class Solution {
    public String solution(String s) {
        int m = s.length();
        
        String ans = "";
        
        if (!Character.isDigit(s.charAt(0))){
            ans += Character.toUpperCase(s.charAt(0));
        }
        else{
            ans += s.charAt(0);
        }
        for(int i = 1; i < m; i++){
            if (s.charAt(i-1) == ' ' && s.charAt(i) != ' '){ // 첫 문자일 때
                if (!Character.isDigit(s.charAt(i))){        
                    ans += Character.toUpperCase(s.charAt(i));  
                }
                else{
                    ans += s.charAt(i);
                }
            }
            else {
                if (!Character.isDigit(s.charAt(i))){
                    ans += Character.toLowerCase(s.charAt(i)); 
                }
                else{
                    ans += s.charAt(i);
                }
            }
        }
        return ans;
    }
}