class Solution {
    public int[] solution(long[] numbers) {
        int N = numbers.length;
        int[] answer = new int[N];
        
        for (int i = 0; i < N; i++){
            if (numbers[i] == 0){
                answer[i] = 0;
                continue;
            }
            String binNum = toBinary(numbers[i]);
            if (checkComp(binNum)){
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        
        return answer;
    }
    
    private String toBinary(long num){
        String str = "";
        while (num > 1){
            long r = num % 2;
            str += "" + r;
            num = num / 2;
        }
        String ans = "1";
        for (int i = str.length()-1; i >= 0; i--){
            ans += str.charAt(i);
        }
        //System.out.println(ans);
        return ans;
    }
    
    private boolean checkComp(String binNum){
        int len = binNum.length();
        int idx = 1;
        
        while (true){
            int tmp = (int)(Math.pow(2, idx))-1;
            if (len <= tmp){
                int cha = tmp-len;
                while (cha > 0){
                    binNum = "0" + binNum;
                    cha--;
                }
                //System.out.println(binNum);
                
                if (checkTree(binNum)){
                    return true;
                } else {
                    return false;
                }   
            }
            idx++;
        }
        
    }
    private boolean checkTree(String binaryTree) {
        if (binaryTree.length() <= 1) {
            return true;
        }
        
        String leftTree = binaryTree.substring(0, binaryTree.length() / 2);
        String rightTree = binaryTree.substring(binaryTree.length() / 2 + 1);
        
        
        char root = binaryTree.charAt(binaryTree.length() / 2);
        char left = leftTree.charAt(leftTree.length() / 2);
        char right = rightTree.charAt(rightTree.length() / 2);
        
        if (root == '0' && (left == '1' || right == '1') )
            return false;
        else
            return checkTree(leftTree) && checkTree(rightTree);
    }
}