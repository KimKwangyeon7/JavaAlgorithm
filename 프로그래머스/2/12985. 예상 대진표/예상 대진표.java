class Solution
{
    static int answer;
    public int solution(int n, int a, int b)
    {
        answer = 0;
        check(n, a, b, 0);
        return answer;
    }
    private static void check(int n, int a, int b, int round){
        if (n == 1){
            return;
        }
        int oppA = (a % 2 == 0) ? a-1: a+1;
        int oppB = (b % 2 == 0) ? b-1: b+1;
        if (oppA == b && oppB == a){
            answer = round+1;
            return;
        }
        
        int nextA = a / 2 + (a % 2);
        int nextB = b / 2 + (b % 2);
        
        int nextN = n / 2;
        check(nextN, nextA, nextB, round+1);
    }
    
}