import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
    	int len = enroll.length;
        int[] answer = new int[len];
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < len; i++) {
        	map.put(enroll[i], i);
        }
        int p = seller.length;
        for (int i = 0; i < p; i++) {
        	int sum = amount[i]*100;
        	String sel = seller[i];
        	while (true) {
                if (sum == 0) {
	        		break;
	        	}
        		String ref = referral[map.get(sel)];
	        	if (!ref.equals("-")) { // 추천인이 있으면
	        		answer[map.get(sel)] += sum - sum/10;
	        		sel = ref;
	        	} else { // 추천인이 없으면
	        		answer[map.get(sel)] += sum - sum/10;
	        		break;
	        	}
	        	sum = sum / 10;
        	}
        }
        return answer;
    }
}