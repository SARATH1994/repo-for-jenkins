##########
definations

Source server ( This is the server to which you need the connection from client )

client server ( the machine from where you need to connect )

###################################
Log as root in Both the machines 

Create a new User with a passwd like below 

useradd -d /home/user1 -m user1                                   -->User1 Creations in the client machines 

passws user1

useradd -d /home/user2 -m user2                                   -->User2 Creations in  the source  machines 

passws user2 

test the above with by giving 

sudo su - user1/user2

###############################################

Log as root to Source server

edit the /etc/hosts file                                          ------> Adding custom Name to the IP in hosts file 

append the ip and custom name of server to it 

172.45.155.45 webserver

#########################################################

Log as root to Source server

vim or nano /etc/ssh/sshd_config

assign value :  PasswordAuthentication yes

Save the file and then run the following command to reload the SSH config:

sudo service sshd reload

#######################################################################

Log as user2 to source server

mkdir ~/.ssh 
chmod 700 ~/.ssh

##################################################################################

Log as user1 to client server 

ssh-keygen will generate pub,private key 

with or without sudo 

ssh-copy-id -i /home/user1/.ssh/id_rsa.pub user2@webserver

make sure 600 permissions to /home/user1/.ssh/authorized_keys file in source server 

############################################################################################

DEBUG COMMANDS 

sudo service sshd status/restart/reload/start/stop -l

ssh -v user2@webserver will get verbose log 




