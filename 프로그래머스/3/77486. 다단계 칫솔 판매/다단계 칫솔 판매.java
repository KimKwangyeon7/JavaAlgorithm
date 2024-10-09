import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] ans = new int[enroll.length];
        Stack<String> stack1 = new Stack<>();
        Stack<String> stack2 = new Stack<>();
        Map<String, Integer> e = new HashMap<>();
        Map<String, String>  r = new HashMap<>();
        Map<String, Stack<Integer>> s = new HashMap<>();
        for(int i=0; i<enroll.length; i++)e.put(enroll[i], i);
        for(int i=0; i<referral.length; i++)r.put(enroll[i], referral[i]);
        for(int i=0; i<seller.length; i++){
            s.putIfAbsent(seller[i], new Stack<>());
            s.get(seller[i]).add(amount[i]);
        }

        for(String t : seller) stack1.add(t);

        int a = 0;
        while(!stack1.isEmpty()){
            String t = stack1.pop();
            int amt = s.get(t).pop();
            ans[e.get(t)] += amt * 90;
            int ref = amt * 10;

            stack2.add(r.get(t));
            while(!stack2.isEmpty()){
                String u = stack2.pop();
                if(!e.containsKey(u) || ref == 0) break;

                ans[e.get(u)] += (ref - ref/10);
                ref /= 10;
                stack2.add(r.get(u));
            }
        }

        return ans;
    }
}