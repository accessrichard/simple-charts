## A simple charts server

By default will spin up an h2 database from resources/db-hs.sql and schema-h2.sql.

This can be accessed from: http://100.115.92.195:8080/h2-console/

Leave username/password blank and url of: jdbc:h2:mem:sampledb
Setting: Generic H2 (Embedded)

#### Setup

See resources/application.yml. Currently is plugged into in memory h2 db. Pount to a database and feed it a query
for a chart to display.

