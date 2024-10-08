WITH CTE AS (
    SELECT * 
    FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
    WHERE CAR_TYPE = '트럭'
)

SELECT H.HISTORY_ID, 
ROUND((C.DAILY_FEE * H.DATE ) / 100 * (100 -
(CASE
    WHEN H.DATE >= 90 THEN (SELECT DISCOUNT_RATE FROM CTE WHERE DURATION_TYPE = '90일 이상')
    WHEN H.DATE >= 30 THEN (SELECT DISCOUNT_RATE FROM CTE WHERE DURATION_TYPE = '30일 이상')
    WHEN H.DATE >= 7  THEN (SELECT DISCOUNT_RATE FROM CTE WHERE DURATION_TYPE = '7일 이상')
    ELSE 0 
END))) FEE 
FROM 
CAR_RENTAL_COMPANY_CAR C JOIN (SELECT *, (DATEDIFF(END_DATE, START_DATE) + 1) DATE
                               FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY) H
ON C.CAR_ID = H.CAR_ID 
WHERE C.CAR_TYPE = '트럭'
ORDER BY FEE DESC, H.HISTORY_ID DESC
