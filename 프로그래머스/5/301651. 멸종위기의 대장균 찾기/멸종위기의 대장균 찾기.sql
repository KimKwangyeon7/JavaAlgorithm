WITH RECURSIVE CTE AS (
    SELECT ID, PARENT_ID, 1 AS GENERATION
    FROM ECOLI_DATA 
    WHERE PARENT_ID IS NULL
    UNION ALL
    SELECT A.ID, A.PARENT_ID, B.GENERATION+1
    FROM ECOLI_DATA A JOIN CTE B ON A.PARENT_ID = B.ID 
)
SELECT COUNT(*) AS COUNT, C.GENERATION FROM CTE C
WHERE C.ID NOT IN (SELECT E.PARENT_ID FROM ECOLI_DATA E WHERE E.PARENT_ID IS NOT NULL)
GROUP BY C.GENERATION
ORDER BY C.GENERATION
;