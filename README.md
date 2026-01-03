## Deploy Spring Boot application and MySQL in different pod as deployment in Kubernetes and connect spring boot app with MySQL.

## Start Minikube cluster
```
minikube start
```

### check minikube status whether cluster is running fine or not
```
minikube status
```

### Connect Minikube to Internal Docker Deamon

```
minikube docker-env
& minikube -p minikube docker-env --shell powershell | Invoke-Expression 
```

Note: check all minikube's docker image by using commaing "docker images"

-------------------------------------------------------------------

## Run Deployment Obj, Service Obj and PVC Storage via db-deployment.yaml file
Note : go to the path where the deployment file is located (inorder to run the db-deployment.yaml file)

### (FOR MYSQL) run db-deployment.yaml file which contains PVC VOLUME, DEPLOYMENT OBJECT AND SERVICE OBJECT
-- here we defined MySQL image version which will be automatically pulled from Docker Hub via yaml file
-- also deployment object, service object and pvc storage volume is created in a single file for MYSQL
```
kubectl apply -f .\db-deployment.yaml
```

### check whether pvc, deployement and service objects are created or not
```
kubectl get pvc
kubectl get deployments 
kubectl get pods 
kubectl get service 

```

### get detail or logs about any specific pod
```
kubectl logs your pod name
```
Note : if every object mentioned above is running, it means our yaml file is successfully configured

## login to MySQL as bash inside poweshell
```
 kubectl exec -it your_pod_name -- bash

```
```
mysql -h mysql -u root -p

Now enter your MySQL password
```

OR
-- you can use one liner command to connect to MySQL CLI
```
 kubectl exec -it mysql-pod-name -- mysql -u root -p

-- give your MySQL password and enter
-- now you are under MySQL cli
```
-- Now we are under our MySQL container in bash mode




=======================================================================

## Creating and Deploying Spring Boot application to Kubernetes using deployment.yaml files
1. made some configuration changes in application.yaml files which will fetch properties from app-deployment.yaml file
2. Build the latest jar file so that we will create docker image of latest jar by running
``` 
mvn install 
	or 
mvn clean install
```
3. Build docker image by going to the path where Dockerfile is located
```
 docker build -t your_image_name:tag_name .
```
4. check whether docker image is available by typing ``` docker images```
5. Create deployment and service object by running app-deployment.yaml file
```
kubectl apply -f .\app-deployment.yaml
```
-- you will see a prompt mentioning deployment.apps/.. and service/... created
6. Check whether deployment object and service objects are created or not
```
kubectl get deployments
kubectl get service
```
-- you will be able to see MySQL as well as Spring boot deployment and service objects are created

7. Check how many replicas/instances of pods are running for spring boot deployment object
```
kubectl get pods
```
-- you will be able to see exact replicas mentioned in app-deployment.yaml file running as different pod in deployment object

8. Check whether all your individual pod are running fine or not
``` kubectl logs pod_name

		example kubectl logs springboot-crud-deployment-5bd7b748-mx6ld 
```

++++++++++++++++++++++++++++++++++++++++++++++
Reference:

Youtube : https://www.youtube.com/watch?v=pIPji3_rYPY&list=PLVz2XdJiJQxybsyOxK7WFtteH42ayn5i9&index=7

GitHub : https://github.com/Java-Techie-jt/springboot-crud-k8s
