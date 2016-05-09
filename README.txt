README:

Manual:
0.Import two project into eclipse, set up Tomcat Environment, and add WebServer project into Tomcat Server.
1.Run Server/src/Driver/ServerDriver.java
	One Server thread starts.
2.Run WebServer on Server.
3.Open URL in explorer: 
localhost:8080/getModels
localhost:8080/getOptions

—————
files included:
1. Eclipse project
- folder: Project1Unit5_Server
		- Driver: ServerDriver.java
		- Client: CarModelOptionsIO.java, DefaultSocketClient.java, SelectCarOption.java
		- Server: AutoServer.java, BuildCarModelOptions.java,SocketClientConstants.java, SocketClientInterface.java
		- Adapter: BuildAuto.java, CreateAuto.java, ProxyAutomobile.java, UpdateAuto.java
		- Exceptions: AutoException.java, Fix1to100.java
		- model: Automotive.java, OptionSet.java
		- scale: EditOptions.java
		- util: FileIO.java, ReadFileUtio.java
		- more test cases: Unit4Test.java
- folder: Project1Unit5_WebServer
		- folder: client
		- Driver: ClientDriver.java
		- Server: AutoServer.java, BuildCarModelOptions.java,SocketClientConstants.java, SocketClientInterface.java
		- Adapter: BuildAuto.java, CreateAuto.java, ProxyAutomobile.java, UpdateAuto.java
		- Exceptions: AutoException.java, Fix1to100.java
		- model: Automotive.java, OptionSet.java
		- scale: EditOptions.java
		- util: FileIO.java, ReadFileUtio.java
		- folder: unit 5
		- ModelServlet.java, OptionServlet.java, PriceServlet.java

2. class diagram
		- Project1Unit5_Server_Class Diagram.jpg
		- Project1Unit5_WebServer_Class Diagram.jpg

3.Output
		- Select Model_output.jpg
		- SelectOption_output.jpg
		- BillInformation_output.jpg


Thank you!
Huijuan Peng