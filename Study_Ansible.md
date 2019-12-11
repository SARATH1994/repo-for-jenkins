Ansible Concepts 

Control node: Any machine with Ansible installed.

Managed nodes: The network devices (and/or servers) you manage with Ansible. Managed nodes are also sometimes called “hosts”. Ansible is not installed on managed nodes.

Inventory: A list of managed nodes. An inventory file is also sometimes called a “hostfile”.Which resides with a name /etc/ansible/hosts

Modules: The units of code Ansible executes.
Each module has a particular use, from administering users on a specific type of database to managing VLAN interfaces on a specific type of network device.
You can invoke a single module with a task, or invoke several different modules in a playbook

Eg: ansible test-servers -m ping -u ec2-user
    ansible test-servers -m copy -a 'src=/home/knoldus/Personal/blogs/blog3.txt dest=/tmp' -u ec2-user
    ansible test-servers -m yum -a 'name=httpd state=present' -become -u ec2-user
    ansible test-servers -m shell -a 'ls -la' -u ec2-user
    ansible test-servers -m service -a 'name=httpd state=started' -become -u ec2-user
    ansible test-servers -m debug -a 'msg=Hello' -u ec2-user


Tasks
The units of action in Ansible. You can execute a single task once with an ad-hoc command.

Playbooks
Ordered lists of tasks, saved so you can run those tasks in that order repeatedly. Playbooks can include variables as well as tasks. Playbooks are written in YAML and are easy to read, write, share and understand

Eg: 

ansible all -m ping ->> is one task   |----> combinations of tasks involved in a file which is been written in YAML is Play Book
ansible all -m "/usr/bin/echo hello"  | Here ping is module like wise shell, Copy ,Yum ,Service what ever we given with -m is Module name

Action: create a basic inventory

For this basic inventory, edit (or create) /etc/ansible/hosts and add a few remote systems to it. For this example, use either IP addresses or FQDNs:

192.0.2.50
aserver.example.org
bserver.example.org

Connecting to remote nodes

Ansible communicates with remote machines over the SSH protocol. By default, Ansible uses native OpenSSH and connects to remote machines using your current user name, just as SSH does. 

if you give whoami in Control node it displays current user name that means ansible is trying to connect to the hosts which were defined in inventory with current user by default ansible mechannism.Look below for details 

ansible all -m ping -vvvv , -vvvv is to diplay the  log

ssh currentUsername@host , to successed you have to add Control node currentUsername pubkey to manged node CurrentUsername authorozied_keys.

To run with diff user we have to add -u 

ansible all -m ping -u ec2-user, to successed you have to add Control node currentUsername pubkey to manged node ec2-user authorozied_keys.

Copying and executing modules
Once it has connected, Ansible transfers the modules required by your command or playbook to the remote machine(s) for execution

Some modules like yum needs root Priviliges for that u need to give --become  to ad-hoc commnd 

If you need privilege escalation (sudo and similar) to run a command, pass the become flags:

# as bruce
$ ansible all -m ping -u bruce
# as bruce, sudoing to root (sudo is default method)
$ ansible all -m ping -u bruce --become
# as bruce, sudoing to batman
$ ansible all -m ping -u bruce --become --become-user batman


 to successed above you have to add the -u user to sudoers group , refer Imp Sites Ref.md in this repo.
 
 Inventory :
 
 The default location for inventory is a file called /etc/ansible/hosts. You can specify a different inventory file at the command line using the -i <path> option

Inventory basics: formats, hosts, and groups

INI formant 

mail.example.com

[webservers]
foo.example.com
bar.example.com

[dbservers]
one.example.com
two.example.com
three.example.com


YAML format for the same 

all:
  hosts:
    mail.example.com:
  children:
    webservers:
      hosts:
        foo.example.com:
        bar.example.com:
    dbservers:
      hosts:
        one.example.com:
        two.example.com:
        three.example.com:
        
Default groups

There are two default groups: all and ungrouped.

The all group contains every host. The ungrouped group contains all hosts that don’t have another group aside from all.

***Every host will always belong to at least 2 groups (all and ungrouped or all and some other group).

Though all and ungrouped are always present, they can be implicit and not appear in group listings like group_names.       












