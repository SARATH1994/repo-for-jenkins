Add color to console , Ansicolor-plugin: https://blog.mphomphego.co.za/blog/2017/04/13/jenkins-add-color-to-console-output.html

What/Who Causes triggered build : def causes = currentBuild.getBuildCauses();  println causes;  returns Json file 


Load From File : allows you to read in Groovy files from disk or from the web

node {
    // Load the file 'externalMethod.groovy' from the current directory, into a variable called "externalMethod".
    def externalMethod = load("externalMethod.groovy")
     // Call the method we defined in externalMethod.
    externalMethod.lookAtThis("Steve")
    }
    
   