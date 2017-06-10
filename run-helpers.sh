docker rm rabbitmq3;
docker run -d --hostname rabbitmq -p 5672:5672 -p 15672:15672 --name rabbitmq3 rabbitmq:3-management;

docker rm eureka;
docker run -d --hostname eureka -p 8761:8761 --name eureka luizkowalski/eureka-server
