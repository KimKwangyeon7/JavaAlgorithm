class Solution {
    public long solution(int w, int h) {
        long answer = 1;
        int x = find(w, h);
        int width = w / x;
        int height = h / x;
        
        answer = ((long)w * h) - (width+height-1)*x;
        return answer;
    }
    private static int find(int w, int h) {
		int x = Math.min(w, h);
		while (x > 0) {
			if ((w % x == 0) && (h % x == 0)) {
				return x;
			}
			x--;
		}
    	return -1;
    }
}