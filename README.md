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

https://jenkins.io/doc/book/pipeline/jenkinsfile/#_footnotedef_2
https://jenkins.io/doc/book/glossary/#glossary

Agent
An agent is typically a machine, or container, which connects to a Jenkins master and executes tasks when directed by the master.

Label
User-defined text for grouping Agents, typically by similar functionality or capability. For example linux for Linux-based agents or docker for Docker-capable agents.
