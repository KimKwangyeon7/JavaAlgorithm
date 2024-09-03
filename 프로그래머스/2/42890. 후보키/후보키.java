import java.util.*;
class Solution {
    static List<int[]> cases;
    static int[] list;
    static int answer;
    public int solution(String[][] relation) {
        answer = 0;
        int len = relation[0].length;
        cases = new ArrayList<>();
        for (int i = 0; i < len; i++){
            if (checkOne(relation, i)){
                answer++;
                cases.add(new int[] {i});
            }
        }
        for (int i = 2; i <= len; i++){
            list = new int[i];
            permu(0, 0, i, len, relation);    
        }
        
        // for (int[] tt: cases){
        //     for (int ttt: tt){
        //         System.out.print(ttt + " ");
        //     }
        //     System.out.println();
        // }
        return answer;
    }
    private static boolean checkOne(String[][] relation, int col){
        HashSet<String> set = new HashSet<>();
        int len = relation.length;
        for (int i = 0; i < len; i++){
            if (set.contains(relation[i][col])){
                return false;
            }
            set.add(relation[i][col]);
        }
        
        return true;
    }
    private static void permu(int L, int start, int size, int len, String[][] relation){
        if (L == size){
            // for (int x: list){
            //     System.out.print(x + " ");
            // }
            if (isRight(relation, L)){
                checkMore();
            }
            return;
        }
        for (int i = start; i < len; i++){
            list[L] = i;
            permu(L+1, i+1, size, len, relation);
        }
    }
    private static boolean isRight(String[][] relation, int L){
        List<String[]> set = new ArrayList<>();
        int len = relation.length;
        for (int i = 0; i < len; i++){
            String[] tmp = new String[L];
            int cnt = 0;
            for (int y: list){
                tmp[cnt++] = relation[i][y];
            }
            // for (String aa: tmp){
            //     System.out.print(aa + " ");
            // }
            // System.out.println();
            if (set.contains(tmp)){
                return false;
            }
            for (String[] str: set){
                int sum = 0;
                for (int j = 0; j < str.length; j++){
                    
                    if (!str[j].equals(tmp[j])){
                        break;
                    }
                    sum++;
                }
                if (sum == str.length){
                    return false;
                }
            }

            set.add(tmp);
        }
        // for (String[] str: set){
        //     System.out.println(str);
        // }
        return true;
    }
    private static boolean checkMore(){
        HashSet<Integer> set = new HashSet<>();
        for (int a: list){
            set.add(a);
        }
        List<int[]> cop = new ArrayList<>();
        for (int[] tmp: cases){
            int cnt = 0;
            for (int t: tmp){
                if (set.contains(t)){
                   cnt++; 
                }
            } 
            if (cnt == tmp.length){
                return false;
            }
        }
        int[] copy = list.clone();
        cases.add(copy);
        answer++;
        return true;
        
    }
}