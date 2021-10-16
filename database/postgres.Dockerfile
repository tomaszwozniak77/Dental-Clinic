FROM docker.io/library/postgres:14
ENV POSTGRES_DB ssbd01
ENV POSTGRES_USER ssbd01admin
# TODO move password to env variable/secret
ENV POSTGRES_PASSWORD ssbd01password
COPY postgresql/init.sql /docker-entrypoint-initdb.d/1-init.sql
COPY postgresql/createDB.sql /docker-entrypoint-initdb.d/2-create.sql
COPY postgresql/data.sql /docker-entrypoint-initdb.d/3-data.sql
