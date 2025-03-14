import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(String word, String[] pages) {
        int N = pages.length;
        word = word.toLowerCase(); // 대소문자 무시
        
        Map<String, Integer> pageIndex = new HashMap<>(); // URL -> index 매핑
        Map<Integer, Double> basicScore = new HashMap<>(); // index -> 기본 점수
        Map<String, List<String>> linkMap = new HashMap<>(); // URL -> 외부 링크 리스트
        Map<String, Double> linkScore = new HashMap<>(); // URL -> 링크 점수
        
        // 정규 표현식 패턴 설정
        Pattern metaPattern = Pattern.compile("<meta property=\"og:url\" content=\"(https://[^\"]+)\"");
        Pattern linkPattern = Pattern.compile("<a href=\"(https://[^\"]+)\"");
        Pattern wordPattern = Pattern.compile("[a-zA-Z]+"); // 단어 단위 추출
        
        for (int i = 0; i < N; i++) {
            String html = pages[i].toLowerCase(); // HTML을 소문자로 변환
            Matcher metaMatcher = metaPattern.matcher(html);
            Matcher linkMatcher = linkPattern.matcher(html);
            
            // 1. URL 추출
            String url = "";
            if (metaMatcher.find()) {
                url = metaMatcher.group(1);
                pageIndex.put(url, i);
            }
            
            // 2. 본문 텍스트 추출 (태그 제외)
            String body = html.split("<body>")[1].split("</body>")[0];
            Matcher wordMatcher = wordPattern.matcher(body);
            
            int count = 0;
            while (wordMatcher.find()) {
                if (wordMatcher.group().equals(word)) {
                    count++;
                }
            }
            basicScore.put(i, (double) count);
            
            // 3. 외부 링크 추출
            List<String> links = new ArrayList<>();
            while (linkMatcher.find()) {
                links.add(linkMatcher.group(1));
            }
            linkMap.put(url, links);
        }
        
        // 4. 링크 점수 계산
        for (String url : linkMap.keySet()) {
            int idx = pageIndex.get(url);
            double score = basicScore.get(idx);
            List<String> links = linkMap.get(url);
            int linkCount = links.size();
            
            if (linkCount > 0) {
                double distributedScore = score / linkCount;
                for (String linkedUrl : links) {
                    linkScore.put(linkedUrl, linkScore.getOrDefault(linkedUrl, 0.0) + distributedScore);
                }
            }
        }
        
        // 5. 최종 매칭 점수 계산 및 최댓값 찾기
        double maxScore = -1;
        int answer = 0;
        
        for (String url : pageIndex.keySet()) {
            int idx = pageIndex.get(url);
            double totalScore = basicScore.get(idx) + linkScore.getOrDefault(url, 0.0);
            
            if (totalScore > maxScore) {
                maxScore = totalScore;
                answer = idx;
            } else if (totalScore == maxScore && idx < answer) {
                answer = idx;
            }
        }
        
        return answer;
    }
}
