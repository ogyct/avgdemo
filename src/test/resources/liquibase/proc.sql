DO
$do$
    BEGIN
        FOR i IN 1..10000
            LOOP
                INSERT INTO post
                    (version, title, body)
                values (0, concat('title', i), concat('body', i));
            END LOOP;
    END;
$do$;
GO


