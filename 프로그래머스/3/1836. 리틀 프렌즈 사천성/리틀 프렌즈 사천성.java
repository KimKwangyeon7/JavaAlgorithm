import java.util.*;

class Solution {
    char[][] board;
    int m, n;
    LinkedList<Character> list = new LinkedList<>();
    HashMap<Character, int[][]> map = new HashMap<>();

    public String solution(int m, int n, String[] input) {
        String answer = "";
        this.m = m;
        this.n = n;
        board = new char[m][n];

        // 보드 초기화 및 타일 위치 기록
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = input[i].charAt(j);
                board[i][j] = c;
                if (c != '.' && c != '*') {
                    if (!map.containsKey(c)) {
                        list.add(c);
                        map.put(c, new int[2][2]);
                        map.get(c)[0][0] = i;
                        map.get(c)[0][1] = j;
                    } else {
                        map.get(c)[1][0] = i;
                        map.get(c)[1][1] = j;
                    }
                }
            }
        }

        Collections.sort(list); // 알파벳 순 정렬

        int idx = 0;
        while (!list.isEmpty()) {
            if (canDelete(list.get(idx))) {
                char removed = list.remove(idx);
                answer += removed;
                deleteChar(removed);
                idx = 0; // 다시 처음부터 검사
            } else {
                idx++;
                if (idx == list.size()) return "IMPOSSIBLE"; // 아무것도 못 지웠으면 종료
            }
        }

        return answer;
    }

    void deleteChar(char ch) {
        board[map.get(ch)[0][0]][map.get(ch)[0][1]] = '.';
        board[map.get(ch)[1][0]][map.get(ch)[1][1]] = '.';
    }

    boolean canDelete(char ch) {
        int r1 = map.get(ch)[0][0];
        int c1 = map.get(ch)[0][1];
        int r2 = map.get(ch)[1][0];
        int c2 = map.get(ch)[1][1];

        // 두 지점 사이 직선 + 한번 꺾는 경로 검사
        if (c1 < c2) {
            if (checkCol(c1, c2, r1, ch) && checkRow(r1, r2, c2, ch)) return true;
            if (checkRow(r1, r2, c1, ch) && checkCol(c1, c2, r2, ch)) return true;
        } else {
            if (checkRow(r1, r2, c2, ch) && checkCol(c2, c1, r1, ch)) return true;
            if (checkCol(c2, c1, r2, ch) && checkRow(r1, r2, c1, ch)) return true;
        }

        return false;
    }

    boolean checkRow(int r1, int r2, int c, char ch) {
        for (int i = Math.min(r1, r2); i <= Math.max(r1, r2); i++) {
            if (board[i][c] != '.' && board[i][c] != ch) return false;
        }
        return true;
    }

    boolean checkCol(int c1, int c2, int r, char ch) {
        for (int i = Math.min(c1, c2); i <= Math.max(c1, c2); i++) {
            if (board[r][i] != '.' && board[r][i] != ch) return false;
        }
        return true;
    }
}
