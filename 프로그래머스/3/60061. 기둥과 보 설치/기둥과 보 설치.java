import java.util.*;

class Solution {
    private boolean[][] pillars;
    private boolean[][] covers;
    
    public int[][] solution(int n, int[][] build_frame) {
        pillars = new boolean[n+3][n+3];
        covers = new boolean[n+3][n+3];
        for(int[] commands : build_frame){
            int x = commands[0]+1;
            int y = commands[1]+1;
            int type = commands[2];
            boolean isDelete = (commands[3]==0);
            if(isDelete){
                if(type==0){
                    pillars[x][y] = false;    
                }else{
                    covers[x][y] = false;
                }
                
                if(!canDestroy(x, y, n)){
                    if(type==0){
                        pillars[x][y] = true;
                    }else{
                        covers[x][y] = true;
                    }
                }
            }else{
                if(type==0 && checkPillar(x,y)){
                    pillars[x][y] = true;    
                }
                if(type==1 && checkCover(x,y)){
                    covers[x][y] = true;    
                } 
            }
        }

        ArrayList<int[]> results = new ArrayList<>();
        for(int i=1; i<=n+1; i++){
            for(int j=1; j<=n+1; j++){
                if(pillars[i][j]) results.add(new int[]{i-1, j-1, 0});
                if(covers[i][j]) results.add(new int[]{i-1, j-1, 1});
            }
        }
        int[][] answer = results.toArray(new int[0][0]);
        return answer;
    }
    
    private boolean checkPillar(int x, int y){
        return y==1 || pillars[x][y-1] || covers[x][y] || covers[x-1][y];
    }
    
    private boolean checkCover(int x, int y){
        return pillars[x][y-1] || pillars[x+1][y-1] || (covers[x+1][y] && covers[x-1][y]);
    }
    
    private boolean canDestroy(int x, int y, int n){
        for(int i=1; i<=n+1; i++){
            for(int j=1; j<=n+1; j++){
                if(pillars[i][j] && !checkPillar(i,j)) return false;
                if(covers[i][j] && !checkCover(i,j)) return false;
            }
        }
        return true;
    }
}
