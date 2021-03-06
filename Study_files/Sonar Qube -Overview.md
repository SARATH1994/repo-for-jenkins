Architecture and Integration

The SonarQube Platform is made of 4 components

1.One SonarQube Server starting 3 main processes:

Web Server for developers, managers to browse quality snapshots and configure the SonarQube instance
Search Server based on Elasticsearch to back searches from the UI
Compute Engine Server in charge of processing code analysis reports and saving them in the SonarQube Database

2.One SonarQube Database to store:

3.the configuration of the SonarQube instance (security, plugins settings, etc.)
the quality snapshots of projects, views, etc.

4.Multiple SonarQube Plugins installed on the server, possibly including language, SCM, integration, authentication, and governance plugins
One or more SonarScanners running on your Build / Continuous Integration Servers to analyze projects

:::::::::Work flow of Sonar Qube :::::::::::

Developers code in their IDEs and use SonarLint to run local analysis.

Developers push their code into their favourite SCM : git, SVN, TFVC, ...

The Continuous Integration Server triggers an automatic build, and the execution of the SonarScanner required to run the SonarQube analysis.

The analysis report is sent to the SonarQube Server for processing.

SonarQube Server processes and stores the analysis report results in the SonarQube Database, and displays the results in the UI.

Developers review, comment, challenge their Issues to manage and reduce their Technical Debt through the SonarQube UI.

Managers receive Reports from the analysis. Ops use APIs to automate configuration and extract data from SonarQube. Ops use JMX to monitor SonarQube Server.

//////////// About Machines and Locations //////////////////////////////

The SonarQube Platform cannot have more than one SonarQube Server and one SonarQube Database.

For optimal performance, each component (server, database, scanners) should be installed on a separate machine, and the server machine should be dedicated.

The SonarQube Server and the SonarQube Database must be located in the same network

SonarScanners don't need to be on the same network as the SonarQube Server.

There is no communication between SonarScanners and the SonarQube Database.


 mvn sonar:sonar   -Dsonar.projectKey=maven-basic   -Dsonar.host.url=http://34.68.60.109:9000   -Dsonar.login=1493628c1ce6e037843606b2aec214c4e10169e8
 curl http://34.68.60.109:9000/api/qualitygates/project_status?projectKey=maven-basic

docker run -d --name sonarqube     -p 9000:9000     -v SonarQ-conf:/opt/sonarqube/conf     -
v SonarQ-data:/opt/sonarqube/data     -v SonarQ-logs:/opt/sonarqube/logs     -v SonarQ-extensions:/opt/sonarqube/extension
s     sonarqube76eb2ee8915b 

All the Sonar API documentation resides at : http://localhost:9000/web_api/
