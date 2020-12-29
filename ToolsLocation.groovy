import hudson.tools.*


TaskListener log;
  
for (agent in Jenkins.instance.getNodes()) {
   def computer = agent.toComputer()
   
     println "Currently on computer ${computer.name}:"
     def isOK = (!computer.isOffline() && computer.isAlive() )
     if (isOK) {
 
      
        for (ToolDescriptor<?> desc : ToolInstallation.all()) {
            for (ToolInstallation inst : desc.getInstallations()) {
                println ('\tTool Name: ' + inst.getName());
                println ('\t\tTool Home: ' + inst.translateFor(agent,log));
            }  
 }  
    } else {
        println "  ERROR: can't get PATH from slave: node is offline."
    }
            
    
         
}
