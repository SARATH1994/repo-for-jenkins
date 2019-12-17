###READ THIS DOC IN EDIT MODE FOR NEAT VIEW ##########

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


Hosts in multiple groups

You can (and probably will) put each host in more than one group. For example a production webserver in a datacenter in Atlanta might be included in groups called [prod] and [atlanta] and [webservers]. You can create groups that track:


What - An application, stack or microservice. (For example, database servers, web servers, etc).
Where - A datacenter or region, to talk to local DNS, storage, etc. (For example, east, west).
When - The development stage, to avoid testing on production resources. (For example, prod, test).

Extending the previous YAML inventory to include what, when, and where would look like:

all:
  hosts:
    mail.example.com:   ##UNGROUPED HOST
  children:
    webservers:
      hosts:
        foo.example.com:        ##What:  servers based on application, wether theese are web (or) db servers 
        bar.example.com:
    dbservers:
      hosts:
        one.example.com:        ##What:  servers based on application, wether theese are web (or) db servers
        two.example.com:
        three.example.com:
    east:
      hosts:
        foo.example.com:
        one.example.com:        ##Where:  servers based on location, where theese are located  
        two.example.com:
    west:
      hosts:
        bar.example.com:        ##Where:  servers based on location, where theese are located
        three.example.com:
    prod:
      hosts:
        foo.example.com:        ##Which/When : servers based on development strategy either dev/test/prod ..etc 
        one.example.com:
        two.example.com:        
    test:                       ##Which/When : servers based on development strategy either dev/test/prod ..etc 
      hosts:
        bar.example.com:
        three.example.com:

You can see that one.example.com exists in the dbservers, east, and prod groups.

You can also use nested groups to simplify prod and test in this inventory, for the same result:

all:
  hosts:
    mail.example.com:
  children:
    webservers:
      hosts:
        www[01:50].example.com:     ##Adding ranges of numeric  hosts
        bar.example.com:
    dbservers:
      hosts:
        db-[a:f].example.com       ##Adding ranges of alphabetic hosts
        two.example.com:
        three.example.com:
    east:
      hosts:
        foo.example.com:
        one.example.com:
        two.example.com:
    west:
      hosts:
        bar.example.com:
        three.example.com:
    prod:
      children:
        east:
    test:
      children:
        west:

As you add more and more managed nodes to your Ansible inventory, however, you will likely want to store variables in separate host and group variable files.

####Assigning a variable to one machine: host variables

atlanta:
  host1:
    http_port: 80
    maxRequestsPerChild: 808
  host2:
    http_port: 303
    maxRequestsPerChild: 909
    
Inventory aliases

...
  hosts:
    jumper:
      ansible_port: 5555
      ansible_host: 192.0.2.50


In the above example, running Ansible against the host alias “jumper” will connect to 192.0.2.50 on port 5555. This only works for hosts with static IPs, or when you are connecting through tunnels.

######Assigning a variable to many machines: group variables

atlanta:
  hosts:
    host1:
    host2:
  vars:
    ntp_server: ntp.atlanta.example.com
    proxy: proxy.atlanta.example.com
    
####    Example     

[childgroup2]
host1
host2
 
[childgroup1]
host2
host3
 
[parent1:children]
childgroup1
childgroup2

above is equivalent to YAML is 

all:
    children:
        parent1:
            children:
                childgroup1:
                    host1:
                    host2:
                childgroup2:
                    host3:
                    host4:
                
A group of a group is called a child group.

The relation is defined through the use of the children key word.                


Child groups have a couple of properties to note:

Any host that is member of a child group is automatically a member of the parent group.
A child group’s variables will have higher precedence (override) a parent group’s variables.
Groups can have multiple parents and children, but not circular relationships.
Hosts can also be in multiple groups, but there will only be one instance of a host, merging the data from the multiple groups.

If Ansible modules are the tools in your workshop, playbooks are your instruction manuals, and your inventory of hosts are your raw material.

While you might run the main /usr/bin/ansible program for ad-hoc tasks, playbooks are more likely to be kept in source control and used to push out your configuration or assure the configurations of your remote systems are in spec.

 At a basic level, a task is nothing more than a call to an ansible module.
 
 #### ####################### pLAY Book 
 
 
 
 
 Each playbook is composed of one or more ‘plays’ in a list.
 
 the goal of a play is to map a group of hosts to some well defined roles, represented by things ansible calls tasks. 
 
 By composing a playbook of multiple ‘plays’, it is possible to orchestrate multi-machine deployments, running certain steps on all machines in the webservers group, then certain steps on the database server group, then more commands back on the webservers group, etc.
 
 The goal of each task is to execute a module, with very specific arguments. Variables can be used in arguments to modules.
 
 Basics
Hosts and Users

For each play in a playbook, you get to choose which machines in your infrastructure to target and what remote user to complete the steps (called tasks) as.

The hosts line is a list of one or more groups or host patterns, separated by colons, as described in the Patterns: targeting hosts and groups documentation. The remote_user is just the name of the user account:

### Remote User & Hosts 
---
- hosts: webservers
  remote_user: root
  
 Remote users can also be defined per task: 
 
- hosts: webservers
  remote_user: root
  tasks:
    - name: test connection
      ping:
      remote_user: yourname  
      
 ### bECOME uSEAGE 
 
 become_user defines the user which is being used for privilege escalation.

become simply is a flag to either activate or deactivate the same.

Here are three examples which should make it clear:

This task will be executed as root, because root is the default user for privilege escalation:

- do: something
  become: yes

This task will be executed as user someone, because the user is explciitly set:

- do: something
  become: yes
  become_user: someone

This task will not do anything with become_user, because become is not set and defaults to false/no:

- do: something
  become_user: someone
...unless become was set to true on a higher level, e.g. a block, the playbook, group or host-vars etc.

################
 
 
 Each Play book has many plays , each paly has many tasks & each task should have a name, which is included in the output from running the playbook.
 
 Tasks can be declared using module: options
 
 Here is what a basic task looks like. As with most modules, the service module takes key=value arguments:
 
 tasks:
  - name: make s
      state: started
      ure apache is running
    service:
      name: httpd
      state: started

The command and shell modules are the only modules that just take a list of arguments and don’t use the key=value form. 

The command and shell module care about return codes, so if you have a command whose successful exit code is not zero, you may wish to do this:

tasks:
  - name: run this command and ignore the result
    shell: /usr/bin/somecommand || /bin/true
   
Or this:

tasks:
  - name: run this command and ignore the result
    shell: /usr/bin/somecommand
    ignore_errors: True

If the action line is getting too long for comfort you can break it on a space and indent any continuation lines:

tasks:
  - name: Copy ansible inventory file to client
    copy: src=/etc/ansible/hosts dest=/etc/ansible/hosts
            owner=root group=root mode=0644    
            
Variables can be used in action lines. Suppose you defined a variable called vhost in the vars section, you could do this:

tasks:
  - name: create a virtual host file for {{ vhost }}
    template:
      src: somefile.j2
      dest: /etc/httpd/conf.d/{{ vhost }}

Ansible prefers listing modules like this:

template:
    src: templates/foo.j2
    dest: /etc/foo.conf

These ‘notify’ actions are triggered at the end of each block of tasks in a play, and will only be triggered once even if notified by multiple different tasks.

- name: template configuration file
  template:
    src: template.j2
    dest: /etc/foo.conf
  notify:
     - restart memcached
     - restart apache
            
 The things listed in the notify section of a task are called Handlers.
 
 Handlers are lists of tasks, not really any different from regular tasks, that are referenced by a globally unique name, and are notified by notifiers. If nothing notifies a handler, it will not run. Regardless of how many tasks notify a handler, it will run only once,
 
 
 Here’s an example handlers section:

handlers:
    - name: restart memcached
      service:
        name: memcached
        state: restarted
    - name: restart apache
      service:
        name: apache
        state: restarted
 
We sholdnt use like this ! 
handlers:
# this handler name may cause your play to fail!

Instead, place variables in the task parameters of your handler. You can load the values using include_vars like this:

tasks:
  - name: Set host variables based on distribution
    include_vars: "{{ ansible_facts.distribution }}.yml"

handlers:
  - name: restart web service
    service:
      name: "{{ web_service_name | default('httpd') }}"
      state: restarted
As of Ansible 2.2, handlers can also “listen” to generic topics, and tasks can notify those topics as follows:

handlers:
    - name: restart memcached
      service:
        name: memcached
        state: restarted
      listen: "restart web services"
    - name: restart apache
      service:
        name: apache
        state: restarted
      listen: "restart web services"

tasks:
    - name: restart everything
      command: echo "this task will restart the web services"
      notify: "restart web services"
Use unique handler names. If you trigger more than one handler with the same name, the first one(s) get overwritten. Only the last one defined will run.

handlers notified within pre_tasks, tasks, and post_tasks

Linting playbooks
You can use ansible-lint to run a detail check of your playbooks before you execute them.

To see what hosts would be affected by a playbook before you run it, you can do this:

ansible-playbook playbook.yml --list-hosts



- name: restart "{{ web_service_name }}"        
 
 
 
 
