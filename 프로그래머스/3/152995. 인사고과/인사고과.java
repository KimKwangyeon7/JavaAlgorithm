import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int n = scores.length;
        int[] wanho = scores[0]; // 완호의 점수 저장

        // 1. 근무 태도 점수(a) 내림차순, 동료 평가 점수(b) 오름차순 정렬
        Arrays.sort(scores, (x, y) -> {
            if (x[0] == y[0]) return Integer.compare(x[1], y[1]); // b 오름차순
            return Integer.compare(y[0], x[0]); // a 내림차순
        });

        // 2. 완호의 인센티브 가능 여부 체크
        int maxB = 0; // 현재까지의 최댓값 (b 점수 기준)
        List<int[]> validScores = new ArrayList<>(); // 인센티브 받을 수 있는 사람들

        for (int[] score : scores) {
            if (score[1] < maxB) { // 이전까지의 b 최댓값보다 작다면 탈락
                if (score[0] == wanho[0] && score[1] == wanho[1]) {
                    return -1; // 완호가 탈락하면 -1 반환
                }
                continue;
            }
            maxB = Math.max(maxB, score[1]);
            validScores.add(score); // 유효한 점수 목록에 추가
        }

        // 3. 인센티브 받을 수 있는 사람들 중에서 등수 계산
        validScores.sort((x, y) -> Integer.compare(y[0] + y[1], x[0] + x[1])); // a + b 내림차순 정렬

        int rank = 1, sameRankCount = 0;
        int prevScore = validScores.get(0)[0] + validScores.get(0)[1];

        for (int i = 0; i < validScores.size(); i++) {
            int sum = validScores.get(i)[0] + validScores.get(i)[1];

            if (sum != prevScore) {
                rank += sameRankCount; // 동점자 수만큼 등수 건너뜀
                sameRankCount = 1;
                prevScore = sum;
            } else {
                sameRankCount++;
            }

            if (validScores.get(i)[0] == wanho[0] && validScores.get(i)[1] == wanho[1]) {
                return rank; // 완호의 등수 반환
            }
        }

        return -1; // 정상적으로 여기까지 올 일은 없음
    }
}
