#!/bin/bash

echo "Run minikube"
minikube start --driver=virtualbox
echo "_________________________________________"
echo "Download main app"
git clone https://github.com/Bsk88/main_application.git

echo "_________________________________________"
echo "Download internal app"
git clone https://github.com/Bsk88/internal_application.git

echo "_________________________________________"
echo "Download simulator app"
git clone https://github.com/Bsk88/simulator.git

echo "_________________________________________"
echo "Build docker images"
eval $(minikube docker-env)

docker build -t main-app:v1  ./main_application/
docker build -t internal-app:v1 ./internal_application/
docker build -t simulator:v1  ./simulator_application/

echo "_________________________________________"
echo "Apply configMap"
kubectl apply -f config-map.yaml

echo "_________________________________________"
echo "Run main app in minikube"
kubectl apply -f main_app_config/main-app-deployment.yaml
kubectl apply -f main_app_config/main-app-service.yaml


echo "_________________________________________"
echo "Run internal app in minikube"
kubectl apply -f internal_app_config/internal-app-deployment.yaml
kubectl apply -f internal_app_config/internal-app-service.yaml


echo "_________________________________________"
echo "Run simulator app in minikube"

kubectl apply -f simulator_app_config/simulator-app-deployment.yaml
kubectl apply -f simulator_app_config/simulator-app-service.yaml

echo "_________________________________________"
echo "Open main app port"
for port in {30000..30100}; do VBoxManage controlvm minikube natpf1 "NodePort$port,tcp,,$port,,$port"; done



