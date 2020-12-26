Add color to console , Ansicolor-plugin: https://blog.mphomphego.co.za/blog/2017/04/13/jenkins-add-color-to-console-output.html

https://stackoverflow.com/questions/4842424/list-of-ansi-color-escape-sequences

https://i.stack.imgur.com/9UVnC.png

ForeGround , BackGround Colors

def Info (message) {
    ansicolor(xtrem) { 
        
   echo """\033[34m
   ------------------------------
   \u2756 INFO: ${message}
   ------------------------------
   """
    
   }
}

What/Who Causes triggered build : def causes = currentBuild.getBuildCauses();  println causes;  returns Json file 


Load From File : allows you to read in Groovy files from disk or from the web

node {
    // Load the file 'externalMethod.groovy' from the current directory, into a variable called "externalMethod".
    def externalMethod = load("externalMethod.groovy")
     // Call the method we defined in externalMethod.
    externalMethod.lookAtThis("Steve")
    }
    
1.	If there were no enough executors you can’t run parallel builds/Concurrent builds 
2.	Running two jobs on same node equals to running a parallel tasks on same node  concurrent builds
3.	We can have a single machine with different node names in Jenkins ui pointing to diffent root dir in remote machine

Nodes = master+agents
Agents = Nodes – master

executor is nothing but 



    
   
