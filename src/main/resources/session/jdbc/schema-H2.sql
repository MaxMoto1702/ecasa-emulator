CREATE
;

SELECT S.PRIMARY_ID,
       S.SESSION_ID,
       S.CREATION_TIME,
       S.LAST_ACCESS_TIME,
       S.MAX_INACTIVE_INTERVAL,
       SA.ATTRIBUTE_NAME,
       SA.ATTRIBUTE_BYTES
FROM SPRING_SESSION S
         LEFT OUTER JOIN SPRING_SESSION_ATTRIBUTES SA ON S.PRIMARY_ID = SA.SESSION_PRIMARY_ID
WHERE S.SESSION_ID = ?;