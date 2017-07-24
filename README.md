Distributed Key Value Store(Java 8)

Summary:

This is a simple key value distributed store with a primary metadata server and few data store servers which are configured
KVDistributedStore:-

The metadata server and data store server run standalone over the network and they communicate with each other through sockets.
RunBook:-

The metadata is configured in the DataStoreConfig.xml in “src/main/resources” in the project
To start the 1 metadata server and 4 data store server run the program which will start all the 5 servers as thread.
com.satisdrms.kvds.examples.StartServer.java


KVDistributedStoreAPI:- Implemented using JERSEY 

The rest API makes calls to the request handler which takes care of get and post requests.
The requesthandles gets the list of nodes for the given key by calculating a simple hash based on the number of the nodes alive and returns two nodes for distribution.
The KV pair is sent to a data store server and data store server communicates with other data store server identified for storing this KV pair and send the data to those servers.

RunBook:-

The project is implemented as maven project and libraries it needs will be downloaded from the maven repository. The libraries it needs are as below
•	jersey-server
•	jersey-core
•	servlet-api
•	asm
•	jersey-bundle
•	KVDistributedStoreThe request handler is coded in the KVDistributed store and that needs to imported to the api for the api get and post to work
•	jackson-mapper-asl
To start the api services :- place the below config file in the below location for the api services to know the running servers.
 
Location :- C:\rest

Sample curl call commands are:- I ran the commands in windows
curl -H "Accept: application/json" http://localhost:8081/api/rest/get/test
curl -H "Content-type: application/json" -X POST "http://localhost:8080/api/rest/set/test" -d {"""value""":"""dasautos1"""}1
curl -H "Content-type: application/json" -X POST "http://localhost:8080/api/rest/set/test" -d {\"value\":\"dasautos12\"}

KVDSTest:-

The project is implemented as maven project and libraries it needs will be downloaded from the maven repository.
The unit test cases cover the get and post response and request.
The unit test cases are implemented in rest assured.

RunBook:-
The libraries need to run are 
•	Junit
•	jackson-databind
•	rest-assured
•	json
•	json-path

To execute test cases after starting the servers and apis run the below program.
com.satisdrms.kvds.test.CallTestCases.java


