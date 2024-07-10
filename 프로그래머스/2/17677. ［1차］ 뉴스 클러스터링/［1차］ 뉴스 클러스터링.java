import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < str1.length()-1; i++){
            String tmp = "" + str1.charAt(i) + str1.charAt(i+1);
            if (!Character.isAlphabetic(tmp.charAt(0)) || !Character.isAlphabetic(tmp.charAt(1))){
                continue;
            } else {
                tmp = tmp.toUpperCase();
                //System.out.println(tmp);
                list1.add(tmp);
            }
        }
        
        int gyo = 0;
        int hap = list1.size();
        
        for (int i = 0; i < str2.length()-1; i++){
            String tmp = "" + str2.charAt(i) + str2.charAt(i+1);
            //System.out.println(tmp);
            if (!Character.isAlphabetic(tmp.charAt(0)) || !Character.isAlphabetic(tmp.charAt(1))){
                continue;
            } else {
                tmp = tmp.toUpperCase();
                //System.out.println(tmp);
                hap++;
                if (list1.contains(tmp)){
                    //System.out.println("존재!");
                    hap--;
                    gyo++;
                    list1.remove(tmp);
                }
            }     
        }
        if (hap == 0){
            return 65536;
        } else {
            return (int)((double)gyo/hap*65536);
        }
    }
}