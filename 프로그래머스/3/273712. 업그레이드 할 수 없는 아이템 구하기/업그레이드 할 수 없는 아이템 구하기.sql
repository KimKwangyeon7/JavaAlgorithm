-- 코드를 작성해주세요

SELECT C.ITEM_ID, C.ITEM_NAME, C.RARITY
FROM ITEM_INFO C
WHERE C.ITEM_ID NOT IN (SELECT PARENT_ITEM_ID FROM ITEM_TREE WHERE PARENT_ITEM_ID IS NOT NULL) 
ORDER BY C.ITEM_ID DESC;