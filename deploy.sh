set -e

cd user-service
./gradlew bootJar
cd ../stock-service
./gradlew bootJar
cd ../api-gateway
./gradlew bootJar
cd ../discovery-service
./gradlew bootJar

cd ..
docker-compose build
docker-compose up