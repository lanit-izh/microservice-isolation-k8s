#!/bin/bash
GR='\033[0;32m'
NC='\033[0m' # No Color

echo "${GR}Run minikube${NC}"
minikube start --driver=virtualbox
echo "${GR}_________________________________________{NC}"

echo "${GR}Build docker images${NC}"
eval $(minikube docker-env)

docker build -t main-app:v1  ./main_application/
docker build -t internal-app:v1 ./internal_application/
docker build -t simulator:v1  ./simulator_application/

echo "${GR}_________________________________________{NC}"
echo "${GR}Apply configMap${NC}"
kubectl apply -f config-map.yaml

echo "${GR}_________________________________________{NC}"
echo "${GR}Run main app in minikube${NC}"
kubectl apply -f main_app_config/main-app-deployment.yaml
kubectl apply -f main_app_config/main-app-service.yaml


echo "${GR}_________________________________________{NC}"
echo "${GR}Run internal app in minikube${NC}"
kubectl apply -f internal_app_config/internal-app-deployment.yaml
kubectl apply -f internal_app_config/internal-app-service.yaml


echo "${GR}_________________________________________{NC}"
echo "${GR}Run simulator app in minikube${NC}"

kubectl apply -f simulator_app_config/simulator-app-deployment.yaml
kubectl apply -f simulator_app_config/simulator-app-service.yaml

echo "${GR}_________________________________________{NC}"
echo "${GR}Open main app port${NC}"
for port in {30000..30100}; do VBoxManage controlvm minikube natpf1 "NodePort$port,tcp,,$port,,$port"; done



