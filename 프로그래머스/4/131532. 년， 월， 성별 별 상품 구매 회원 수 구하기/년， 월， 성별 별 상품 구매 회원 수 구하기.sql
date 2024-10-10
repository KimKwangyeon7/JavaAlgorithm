-- 코드를 입력하세요
SELECT YEAR(A.SALES_DATE) AS YEAR, MONTH(A.SALES_DATE) AS MONTH, U.GENDER, COUNT(DISTINCT A.USER_ID) AS USERS
FROM ONLINE_SALE A JOIN USER_INFO U ON A.USER_ID = U.USER_ID
WHERE U.GENDER IS NOT NULL
GROUP BY YEAR(A.SALES_DATE), MONTH(A.SALES_DATE), U.GENDER
ORDER BY YEAR(A.SALES_DATE), MONTH(A.SALES_DATE), U.GENDER;
