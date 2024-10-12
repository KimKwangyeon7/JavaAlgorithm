-- 코드를 작성해주세요
WITH PY AS (
    SELECT CODE
    FROM SKILLCODES
    WHERE NAME = 'Python'
),
CC AS (
    SELECT CODE
    FROM SKILLCODES
    WHERE NAME = 'C#'  
)

SELECT D.ID, D.EMAIL, D.FIRST_NAME, D.LAST_NAME
FROM DEVELOPERS D, PY, CC
WHERE (D.SKILL_CODE & PY.CODE) OR (D.SKILL_CODE & CC.CODE)
ORDER BY 1;