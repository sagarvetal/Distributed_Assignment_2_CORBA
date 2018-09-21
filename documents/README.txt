%% DCMS (Distributed Class Management System)

Name:         Distributed Class Management System
Authors:      Sagar Vetal(40071979)
	      Zankhanaben Ashish Patel(40067635)
              Himanshu Kohli (40070839)
              Khyatibahen Chaudhary (40071098)
		               
Date:	      June 3, 2018

(1) INTRODUCTION
-----------------
The Distributed Class Management System provides an infrastructure for teachers and student records which are shared across three data centres using CORBA,multithreading and UDP Socket programming.

(2) REQUIRED SYSTEMS
--------------------
-Windows 10 
-Eclipse IDE
-IDL

(3) HOW TO RUN APPLICATION?
-----------------------------
1. Set path in cmd to the src folder.
2. Run command " idlj -fall centerServer.idl"
3. Start ORBD using command "start orbd -ORBInitialPort 1050" (where 1050 is orbd port number)
4. In run configuration Argument part of below server enter "-ORBInitialPort 1050 -ORBInitialHost localhost" for 3 servers
        Execute three Server files are 
	a. MTLServer.java
	b. DDOServer.java
	c. LVLServer.java
5. In run confugration of client do the same things as of server. 
	Client file name : SingleClientTest.java
6. Automated Test cases execute : MultiClientTest.java