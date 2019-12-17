It is possible to trigger the jenkins jobs based on changes done to particular file 

Change Log : A changelog is a log or record of all notable changes made to a project. The project is often a website or software project, and the changelog usually includes records of changes such as bug fixes, new features, etc.

Change Set : a changeset is a set of changes which should be treated as an indivisible group (i.e. an atomic package); the list of differences between two successive versions in the repository.



Reading VM and Containerization

Computer Hardware includes the physical, tangible parts or components of a computer, such as the cabinet, central processing unit, monitor, keyboard, computer data storage, graphics card, sound card, speakers and motherboard. By contrast, software is instructions that can be stored and run by hardware

The KERNEL is a computer program that is the core of a computer's operating system, with complete control over everything in the system. On most systems, it is one of the first programs loaded on start-up

Containers and virtual machines

A container runs natively on Linux and shares the kernel of the host machine with other containers. It runs a discrete process, taking no more memory than any other executable, making it lightweight.

By contrast, a virtual machine (VM) runs a full-blown “guest” operating system with virtual access to host resources through a hypervisor. In general, VMs provide an environment with more resources than most applications need.

Containers are  OS means Kernel level virualization by using container engine means Docker Engine.

VM's are both Hardware as well as OS level virtuaizaion by using HyperVisor makes the Wholy new compute system with OS.

1.Diff is Guest OS present in VM
2.Container is light weight 
3.Uses only needded memory 
4.C's are portable
5.Flexibility we can make any application into containerazation.

Containers are natively for linux.

Unlike Virtualization, containerization uses the same host os. So the container built on linux can not be run on windows and vice versa.

In windows, you have to take help of virtuallization (using Hyper-v) to have same os as your containers's os and then you should be able to run the same.

Docker for windows is similar app which is built on Hyper-v and helps in running linux docker container on windows. But as far as I know, there is nothing as such which helps run windows containers on linux.




The term 'software' refers to the set of electronic program instructions or data a computer processor reads in order to perform a task or operation. There are two main types of software: systems software and application software.

System software is a type of computer program that is designed to run a computer's hardware and application programs. ... The operating system (OS) is the best-known example of system software. The OS manages all the other programs in a computer.

With the help of Hyper-visor, it will allow us to run differnt OS's with feling like entirely New computers as similar to existing computer by using Host Os or with out host Os

Hyper visor is a software or a program which helps in running differnt machines what we call as a Virtual Machines on host machine 

1.With Host Os 
2.With out Host Os means on a bare metal

It’s what sits between the Hardware and the Virtual machine and is necessary to virtualize the server.

Within each virtual machine runs a unique guest operating system. 

VMs with different operating systems can run on the same physical server—a UNIX VM can sit alongside a Linux VM, and so on. 

Each VM has its own binaries, libraries, and applications that it services, and the VM may be many gigabytes in size.




#############################	Jenkins Info ################################

below links use for jenkins understanding 

https://jenkins.io/doc/book/pipeline/syntax/#agent-parameters
https://jenkins.io/doc/book/pipeline/jenkinsfile/#_footnotedef_2
https://jenkins.io/doc/book/glossary/#glossary

Agent
An agent is typically a machine, or container, which connects to a Jenkins master and executes tasks when directed by the master.

Label
User-defined text for grouping Agents, typically by similar functionality or capability. For example linux for Linux-based agents or docker for Docker-capable agents.

label
Execute the Pipeline, or stage, on an agent available in the Jenkins environment with the provided label. For example: agent { label 'my-defined-label' }

Node
A machine which is part of the Jenkins environment and capable of executing Pipelines or Projects. Both the Master and Agents are considered to be Nodes.

node
agent { node { label 'labelName' } } behaves the same as agent { label 'labelName' }, but node allows for additional options (such as customWorkspace).

The parameters for agent in DSL in jenkins file 

any 
none
node
label
docker
dockerfile
kubernetes

Jenkinsfile (Declarative Pipeline)

pipeline {
    agent none 
    stages {
        stage('Example Build') {
            agent { docker 'maven:3-alpine' } 
            steps {
                echo 'Hello, Maven'
                sh 'mvn --version'
            }
        }
        stage('Example Test') {
            agent { docker 'openjdk:8-jre' } 
            steps {
                echo 'Hello, JDK'
                sh 'java -version'
            }
        }
	
    }
}


The POST section in DSL in jenkins file

Conditions

always
Run the steps in the post section regardless of the completion status of the Pipeline’s or stage’s run.

changed
Only run the steps in post if the current Pipeline’s or stage’s run has a different completion status from its previous run.

fixed
Only run the steps in post if the current Pipeline’s or stage’s run is successful and the previous run failed or was unstable.

regression
Only run the steps in post if the current Pipeline’s or stage’s run’s status is failure, unstable, or aborted and the previous run was successful.

aborted
Only run the steps in post if the current Pipeline’s or stage’s run has an "aborted" status, usually due to the Pipeline being manually aborted. This is typically denoted by gray in the web UI.

failure
Only run the steps in post if the current Pipeline’s or stage’s run has a "failed" status, typically denoted by red in the web UI.

success
Only run the steps in post if the current Pipeline’s or stage’s run has a "success" status, typically denoted by blue or green in the web UI.

unstable
Only run the steps in post if the current Pipeline’s or stage’s run has an "unstable" status, usually caused by test failures, code violations, etc. This is typically denoted by yellow in the web UI.

unsuccessful
Only run the steps in post if the current Pipeline’s or stage’s run has not a "success" status. This is typically denoted in the web UI depending on the status previously mentioned

cleanup
Run the steps in this post condition after every other post condition has been evaluated, regardless of the Pipeline or stage’s status.

Example
Jenkinsfile (Declarative Pipeline)
pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                echo 'Hello World'
            }
        }
    }
    post { 
        always { 
            echo 'I will always say Hello again!'
        }
    }
}

Conventionally, the post section should be placed at the end of the Pipeline.
Post-condition blocks contain steps the same as the steps section.


Stages section : Allowed once in a pipeline 
Stage section : Aloowed inside Satges section 
steps section : Alowed inside Stage section

Jenkinsfile (Declarative Pipeline)
pipeline {
    agent any
    stages { 
        stage('Example') {
            steps {
                echo 'Hello World'
            }
        }
    }
}

The steps section must contain one or more steps

DIRECTIVES : 



The Environment directive specifies a sequence of key-value pairs which will be defined as environment variables for the all steps, or stage-specific steps, depending on where the environment directive is located within the Pipeline.

This directive supports a special helper method credentials() which can be used to access pre-defined Credentials by their identifier in the Jenkins environme

Options for Pipe line specific 

& options for Stage specific 

Parameters

Jenkinsfile (Declarative Pipeline)
pipeline {
    agent any
    parameters {
        string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')

        text(name: 'BIOGRAPHY', defaultValue: '', description: 'Enter some information about the person')

        booleanParam(name: 'TOGGLE', defaultValue: true, description: 'Toggle this value')

        choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')

        password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
    }
    stages {
        stage('Example') {
            steps {
                echo "Hello ${params.PERSON}"

                echo "Biography: ${params.BIOGRAPHY}"

                echo "Toggle: ${params.TOGGLE}"

                echo "Choice: ${params.CHOICE}"

                echo "Password: ${params.PASSWORD}"
            }
        }
    }
}



Triggers
stage
tools
Input
when
Steps 
script : Shared Libraries 


ARTICLE ABT DOCKER UID & GIDS : https://medium.com/@mccode/understanding-how-uid-and-gid-work-in-docker-containers-c37a01d01cf
