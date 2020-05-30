Need to have 2  repo's 
  
  1.Config-repo : To store all configuration details needed while CI/CD
  2.Pipeline-utilities: To store all shared library files/ Generic groovy files which can use acrros projects
 
 Need to download both repos into Jenkins Workspace with specific branch 
 
 Loading the only needed groovy files into variables which use later on
 
 Checkout Application Source repo which is configured in Config-repo
 
 Building the Source code using build tools such as maven,gradle,nodejs..etc.
 
 Running the UNIT tests
 
 Code analysis using Sonar scanner or with like tools
 
 Build Management : Uploading the arifacts into Artifactory,Nexus,S3 ..etc.
 
 Static security code analysis : Using checkmarx,veracode 
 
 Download Artifact from Artifactory,Nexus 
 
 Deployment into tomcat,Openshift,AWS..etc.
 
 Notification : email sent to developers/devops 
 
 
