import java.util.*;
class Solution {
    static HashMap<String, int[]> cache;
    static HashMap<String, Integer> map;
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        cache = new HashMap<>();
        map = new HashMap<>();
        int cnt = 0;
        int len = cities.length;
        int time = 0;
        if (cacheSize == 0){
            return len*5;
        }
        for (int i = 0; i < len; i++){
            String str = cities[i].toUpperCase();
            map.put(str, map.getOrDefault(str, 0)+1);
            if (cnt < cacheSize){
                if (cache.containsKey(str)){
                    //int[] tmp = cache.get(str);
                    cache.put(str, new int[] {i, map.get(str)});
                    time++;
                } else {
                    cache.put(str, new int[] {i, map.get(str)});
                    cnt++;
                    time += 5;
                }
            } else {
                if (cache.containsKey(str)){
                    //int[] tmp = cache.get(str);
                    cache.put(str, new int[] {i, map.get(str)});
                    time++;
                } else {
                    compareUse();
                    cache.put(str, new int[] {i, map.get(str)});
                    time += 5;
                }
            }
        }
        return time;
    }
    private static void compareUse(){
        int idx = Integer.MAX_VALUE;
        int used = Integer.MAX_VALUE;
        String str = "";
        Set<String> keys = cache.keySet();
        for (String key: keys){
            int[] tmp = cache.get(key);
            if (tmp[0] < idx){
                str = key;
                idx = tmp[0];
            }
        }
        cache.remove(str);
    }
}