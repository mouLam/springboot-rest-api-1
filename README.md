# springboot-rest-api-1

This is a Spring Boot, a REST API built with PostgreSQL and JWT.

- Use Docker Service to use PostgreSQL image
    * commands : 
        * `_docker cp expensestracker_db.sql postgresdb:/_`
        * `docker container exec -it postgresdb bash`
        * `_psql -U postgres --file expensestracker_db.sql_`
    *