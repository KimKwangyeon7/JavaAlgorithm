-- 코드를 작성해주세요
WITH TOTAL_COUNT AS (
    SELECT COUNT(*) AS TOTAL FROM ECOLI_DATA
)

SELECT E1.ID, 
(CASE 
    WHEN (SELECT COUNT(*) FROM ECOLI_DATA E2 WHERE E2.SIZE_OF_COLONY < E1.SIZE_OF_COLONY) < T.TOTAL / 4 THEN 'LOW'
    WHEN (SELECT COUNT(*) FROM ECOLI_DATA E2 WHERE E2.SIZE_OF_COLONY < E1.SIZE_OF_COLONY) < T.TOTAL / 4 * 2 THEN 'MEDIUM'
    WHEN (SELECT COUNT(*) FROM ECOLI_DATA E2 WHERE E2.SIZE_OF_COLONY < E1.SIZE_OF_COLONY) < T.TOTAL / 4 * 3 THEN 'HIGH'
    ELSE 'CRITICAL'
END) AS COLONY_NAME
FROM ECOLI_DATA E1, TOTAL_COUNT T
ORDER BY 1
