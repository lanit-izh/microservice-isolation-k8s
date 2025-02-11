# Проект реализующий тестирования сервиса с заглушкой в minikube

### Цель проекта 

Научится с использованием minikube изменять конфигурации  приложений, для быстрого развертывания сервисов-заглушек и использования их в целях тестирования. 

 [Ссылка на видео инструкцию](https://drive.google.com/file/d/135BE8iyojzOyV_XiyuSP_FbuTDNCx0Jb/view?usp=sharing) 

### Описание проекта 

Проект включает в себя 3 приложение и конфигурационные файлы для запуска в minikube 
 * "Main Application" - главное приложение, которое мы собираемся тестировать.
 * "Internal Application" - внутреннее приложение, с которым напрямую взаимодействует "Main Application" . 
 * "Simulator Application " - приложение заглушка, которое будет подменять сервис "Internal Application".
 
  

  ![Alt text](src/services_schema.png?raw=true "")



### Запуск проекта

  Предусловия: На запускаемой машине, установлен minikube, docker 
   
  1. Скачать проект  и перейти в каталог проекта
    

    git clone https://gitlab.com/bsk88/minikube_simulate_service.git 

    cd minikube_simulate_service


  2. Запустить скрипт, который запустит миникуб и развернет в нем приложения
     
     
    bash run.sh


  3. После успешного выполнения проверить, что открывается swagger "Main Application" по адресу 
   
       
       
    http://{host}:30002/swagger-ui.html#

       
   где  host - ip адрес машины, на которой развернут minikube.
   
   4. При совершении запросов через swagger ui, можно увидеть что при совершении запроса на получения имя вызываемого сервиса в ответ приходит - 'вызван метод сервиса 'Production service' .
     При запросе, на получение пользователя приходит один и тот же пользователь с данными 
     
     
     {
       "id": 1,
       "user_name": "Иван Иванович",
       "organization_id": 2
     }


    
   5. Что бы переключить работу "Main Application" с внутреннего  продуктового приложения на заглушку (симулятор) необходимо в терминале выполнить команду 

          
         
    kubectl set env deployment/main-app SERVICE_URL=http://simulator
          
    
   6. После этого, совершении запросов через swagger ui, при вызове  запроса на получения имя вызываемого сервиса в ответ приходит - 'Вызван метод сервиса 'Simulator Service' 
   При запросе, на получение пользователя приходят рандомные данные с пользователями отличающимися от  продуктового сервиса.
   
   7. Для завершения работы minikube необходимо выполнить команду 
   
       
    minikube delete 
       
   8. Затем удалить папку с проектом.
   
    cd ..
    rm -rf minikube_simulate_service                
          
    

    
