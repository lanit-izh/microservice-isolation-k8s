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

docker build -t main-app:v1  -f ./main_application/
docker build -t internal-app:v1  -f ./internal_application/
docker build -t simulator:v1  -f ./simulator/

echo "_________________________________________"
echo "Apply configMap"
kubectl apply config-map.yaml

echo "_________________________________________"
echo "Run main app in minikube"
kubectl apply -f main_app/main-app-deployment.yaml
kubectl apply -f main_app/main-app-service.yaml


echo "_________________________________________"
echo "Run internal app in minikube"
kubectl apply -f internal_app/internal-app-deployment.yaml
kubectl apply -f internal_app/internal-app-service.yaml


echo "_________________________________________"
echo "Run simulator app in minikube"

kubectl apply -f simulator_app/simulator-app-deployment.yaml
kubectl apply -f simulator_app/simulator-app-serv

echo "_________________________________________"
echo "Open main app port"
for port in {30000..30100}; do VBoxManage controlvm minikube natpf1 "NodePort$port,tcp,,$port,,$port"; done