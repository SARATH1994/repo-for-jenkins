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

$systemctl enable docker // To up and run docker server or Docker daemon upon boot //

$exit

$sudo useradd jenkins // Add the user, that you want to run with to the docker group.

for debug ,

$id

uid=1001(jenkins) gid=1001(jenkins) groups=1001(jenkins),993(docker)

$cat /etc/passwd

ec2-user:x:1000:1000:EC2 Default User:/home/ec2-user:/bin/bash
jenkins:x:1001:1001::/home/jenkins:/bin/bash

$whoami

jenkins

now performing as jenkins user in  linux machine

if i give docker version commands it is returning 

$docker version or $docker ps 

Docker client version is good

Docker Server/daemon version Got permmission denied 


::::: Access the  Docker as a non-root user ::::::::::::;

Link : https://docs.docker.com/install/linux/linux-postinstall/

To create the docker group and add your user:

Create the docker group.

$ sudo groupadd docker

$ sudo usermod -aG docker jenkins

logout and login again with the above  or restart ,reboot

Now you will be able access the docker daemon with specified user 


When you create a new container it does not get created as your current user, but as root, which the daemon is running under.

$ docker container run --rm \
    -v ${PWD}:/var/www \
    -w /var/www \
    jtreminio/php:7.2 whoami
root


::: So run as your local user, right? ::::

When you run Docker containers you can specify a user ID, plus a group ID. It is easy enough to do:

docker container run --rm \
    -v ${PWD}:/var/www \
    -w /var/www \
    -u $(id -u ${USER}):$(id -g ${USER}) \
    jtreminio/php:7.2 composer require psr/log
This generates the following:

$ls -la

drwx------ 3 jenkins jenkins  139 Feb 11 08:47 .
drwxr-xr-x 4 root    root      37 Feb 11 07:58 ..
-rw------- 1 jenkins jenkins   58 Feb 11 08:08 .bash_history
-rw-r--r-- 1 jenkins jenkins   18 Jul 27  2018 .bash_logout
-rw-r--r-- 1 jenkins jenkins  193 Jul 27  2018 .bash_profile
-rw-r--r-- 1 jenkins jenkins  231 Jul 27  2018 .bashrc
-rw-r--r-- 1 jenkins jenkins   53 Feb 11 08:47 composer.json
-rw-r--r-- 1 jenkins jenkins 2073 Feb 11 08:47 composer.lock
drwxr-xr-x 4 jenkins jenkins   53 Feb 11 08:47 vendor

In my system, my user jenkins has user ID 1000 and group ID 1000, so the new line

-u $(id -u ${USER}):$(id -g ${USER})

gets interpreted as

-u 1001:1001

:::::::::::MUST READ ARTICLE:::::::::::

LINK,  https://jtreminio.com/blog/running-docker-containers-as-current-host-user/#ok-so-what-actually-works


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



