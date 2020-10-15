DROP TABLE IF EXISTS label_value_time;


CREATE TABLE label_value_time (id IDENTITY  NOT NULL PRIMARY KEY,
                              category VARCHAR(64) NOT NULL,
                              label   VARCHAR(64) NOT NULL,
                              value   INTEGER NOT NULL,
                              date    TIMESTAMP WITH TIME ZONE);
