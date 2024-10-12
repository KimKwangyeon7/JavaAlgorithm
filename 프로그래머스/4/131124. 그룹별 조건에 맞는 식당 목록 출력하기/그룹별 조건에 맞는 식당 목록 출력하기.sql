-- 코드를 입력하세요
# WITH CTE AS (    
#     SELECT R.MEMBER_ID
#     FROM REST_REVIEW R
#     GROUP BY MEMBER_ID
#     HAVING COUNT(*) = 
#         (SELECT MAX(A.CNT)
#          FROM (SELECT COUNT(*) AS CNT, MEMBER_ID
#               FROM REST_REVIEW
#               GROUP BY MEMBER_ID) A)       
# )

SELECT M.MEMBER_NAME, R.REVIEW_TEXT, DATE_FORMAT(R.REVIEW_DATE, "%Y-%m-%d") AS REVIEW_DATE
FROM MEMBER_PROFILE M JOIN REST_REVIEW R ON M.MEMBER_ID = R.MEMBER_ID
WHERE M.MEMBER_ID IN (    
                        SELECT R.MEMBER_ID
                        FROM REST_REVIEW R
                        GROUP BY MEMBER_ID
                        HAVING COUNT(*) = 
                            (SELECT MAX(A.CNT)
                             FROM (SELECT COUNT(*) AS CNT, MEMBER_ID
                                  FROM REST_REVIEW
                                  GROUP BY MEMBER_ID) A)       
                    )
ORDER BY 3, 2;