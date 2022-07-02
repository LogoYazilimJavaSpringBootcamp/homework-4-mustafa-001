```
docker run --name isbasi-postgres -d   -e POSTGRES_PASSWORD=pgpassword  -p 5432:5432 postgres
docker run -p 5050:80 -e 'PGADMIN_DEFAULT_EMAIL=pgadmin4@pgadmin.org' -e 'PGADMIN_DEFAULT_PASSWORD=admin' -d --name pgadmin4 dpage/pgadmin4
docker logs isbasi-postgres
docker container inspect isbasi-postgres | grep '"IPAddress"' -m 1 | cut -d : -f 2 | sed 's/"//g' | sed 's/,//g'

```