```
docker run --name isbasi-postgres -d   -e POSTGRES_PASSWORD=pgpassword  -p 5432:5432 postgres
docker run -p 5050:80 -e 'PGADMIN_DEFAULT_EMAIL=pgadmin4@pgadmin.org' -e 'PGADMIN_DEFAULT_PASSWORD=admin' -d --name pgadmin4 dpage/pgadmin4
docker run -d -p 27017:27017 --name isbasi-email-mongo mongo:latest
docker run -d --hostname my-rabbit --name myrabbit -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=123456 -p 15672:15672 -p 5672:5672 rabbitmq:3-management
docker logs isbasi-postgres
# postgres veritabanın çalıştığı IP. pgadmin'de yeni Server eklemek için.
docker container inspect isbasi-postgres | grep '"IPAddress"' -m 1 | cut -d : -f 2 | sed 's/"//g' | sed 's/,//g'
localhost:15672 #rabbitmq admin interface
localhost:5050 #pgadmin interface
docker exec -it test-isbasi-email-mongo bash

```