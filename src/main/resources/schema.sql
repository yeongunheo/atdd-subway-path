CREATE TABLE IF NOT EXISTS STATION
(
    id
    BIGINT
    AUTO_INCREMENT
    NOT
    NULL,
    name
    VARCHAR
(
    255
) NOT NULL UNIQUE,
    PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS LINE
(
    id
    BIGINT
    AUTO_INCREMENT
    NOT
    NULL,
    name
    VARCHAR
(
    255
) NOT NULL UNIQUE,
    color VARCHAR
(
    20
) NOT NULL,
    PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS SECTION
(
    id
    BIGINT
    AUTO_INCREMENT
    NOT
    NULL,
    line_id
    BIGINT
    NOT
    NULL,
    up_station_id
    BIGINT
    NOT
    NULL,
    down_station_id
    BIGINT
    NOT
    NULL,
    distance
    int
    NOT
    NULL,
    PRIMARY
    KEY
(
    id
),
    FOREIGN KEY
(
    line_id
) REFERENCES LINE
(
    id
) ON DELETE CASCADE,
    FOREIGN KEY
(
    up_station_id
) REFERENCES STATION
(
    id
)
  ON DELETE CASCADE,
    FOREIGN KEY
(
    down_station_id
) REFERENCES STATION
(
    id
)
  ON DELETE CASCADE
    );
