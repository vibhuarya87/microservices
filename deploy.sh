set -e

cd account-service
./gradlew bootJar
cd ../stock-service
./gradlew bootJar
cd ../wallet-service
./gradlew bootJar
cd ../authz-service
./gradlew bootJar
cd ../discovery-service
./gradlew bootJar

cd ..
docker-compose build
docker-compose up