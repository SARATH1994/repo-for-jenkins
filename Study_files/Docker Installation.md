sudo su - root // Performing AS a root user //

yum install docker // Getting and Installing Docker Arch Package from Amazon Repo//

docker version

----Result-------------------
Cannot connect to the Docker daemon at unix:///var/run/docker.sock. Is the docker dae
mon running?
-----------------------------

service start docker ( if you didn't give this,  docker daemon cannot connect )

docker version ( you will able to see docker client version as well as server version )

------------Result----------Like this 
Client:
 Version:           18.06.1-ce
 API version:       1.38
 Go version:        go1.10.3
 Git commit:        e68fc7a215d7133c34aa18e3b72b4a21fd0c6136
 Built:             Mon Jul  1 18:51:44 2019
 OS/Arch:           linux/amd64
 Experimental:      false

Server:
 Engine:
  Version:          18.06.1-ce
  API version:      1.38 (minimum version 1.12)
  Go version:       go1.10.3
  Git commit:       e68fc7a/18.06.1-ce
  Built:            Mon Jul  1 18:53:20 2019
  OS/Arch:          linux/amd64
  Experimental:     false
 ----------------------------------

systemctl enable docker // To up and run docker server or Docker daemon upon boot //

exit 

now performing as ec2-user in AWS linux machine

if i give docker version commands it is returning 

Docker client version is good

Docker Server version Got permmission denied 

::::: Manage Docker as a non-root user ::::::::::::;

Link : https://docs.docker.com/install/linux/linux-postinstall/

To create the docker group and add your user:

Create the docker group.

$ sudo groupadd docker

Add your user to the docker group.

$ sudo usermod -aG docker ec2-user

logout and login again 


:::::::::::::::Jenkins Installation :::::::::::::::::::::::::;

link :https://jenkins.io/doc/book/installing/#setup-wizard 

docker pull jenkinsci/blueocean

docker run \
  -u root \  ( other wise you can create user jenkins and add it to docker group )
  --rm \ 
  -d \ 
  -p 8080:8080 \ 
  -p 50000:50000 \ 
  -v jenkins-data:/var/jenkins_home \ 
  -v /var/run/docker.sock:/var/run/docker.sock \ 
  jenkinsci/blueocean 

docker volume inspect jenkins-data ( we can see jenkins container data is getting stored in Jenkins-data volume by iisung inspect we can find exact path)

if Container got lost we can run the container with available data like below

docker run -u jenkins --rm -d -p 8080:8080 -p 50000:50000 --mount source=jenkins-data,target=/var/jenkins_home jenkinsci/blueocean

the Above command will do only sure that Persistant data : it wont make any docker service available inside of the running docker container ( jenkins ) 

The below command takes/mount  the existing data from jenkins-data volume and -v flag attach docker serverice file to container. 

Perfect Command : 

docker run -u jenkins -d -p 8080:8080 -p 50000:50000 --mount source=jenkins-data,target=/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock --restart always jenkinsci/blueocean

for debug : docker exec -it jenkins-blueocean bash

chmod 777 /var/run/docker.sock // to allow other containers to use docker host docker.service 



