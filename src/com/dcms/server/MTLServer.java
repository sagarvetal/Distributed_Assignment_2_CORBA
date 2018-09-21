package com.dcms.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import com.dcms.CenterServerApp.CenterServer;
import com.dcms.CenterServerApp.CenterServerHelper;
import com.dcms.service.ActivityLoggerService;
import com.dcms.util.ActionConstants;
import com.dcms.util.FieldConstants;
import com.dcms.util.FileConstants;
import com.dcms.util.LocationConstants;
import com.dcms.util.MessageTypeConstants;
import com.dcms.util.PortConstants;
import com.dcms.util.UdpServerMessages;

/**
 * MTL Server Class
 * Create server instance for MTL location
 */

public class MTLServer {
   
    public static void main(String[] args) {
    	
        try{
        	final ActivityLoggerService activityLogger = new ActivityLoggerService(FileConstants.SERVER_LOG_FILE_PATH + LocationConstants.MONTREAL + "/" + FileConstants.ACTIVITY_LOG);
            /* Create and Initialize the ORB 
             * Get reference to rootpoa and activate the POAManager
             */
            final ORB orb = ORB.init(args, null);      
            final POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
       
            /* Create servant and register it with the ORB */
            final CenterServerImpl centerServerImpl = new CenterServerImpl(LocationConstants.MONTREAL);
       
            /*Start UDP server for Montreal*/
            new Thread(() -> {
            	startUdpServer(activityLogger, centerServerImpl);
            }).start();

            /* Get object reference from the servant */
            final org.omg.CORBA.Object ref = rootpoa.servant_to_reference(centerServerImpl);
            
            /* Cast the reference to a CORBA reference */
            final CenterServer href = CenterServerHelper.narrow(ref);
       
            /* NameService invokes the transient name service  */
            final org.omg.CORBA.Object objRef =  orb.resolve_initial_references("NameService");
            final NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            /* Bind the Object Reference in Naming */
            final NameComponent path[] = ncRef.to_name(LocationConstants.MONTREAL);
            ncRef.rebind(path, href);
       
            System.out.println("#========= Montreal Server is ready and waiting =========#");
            orb.run();
            
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }
    }
 
    private static void startUdpServer(final ActivityLoggerService activityLogger, final CenterServerImpl server) {
        DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(PortConstants.getUdpPort(server.getServerName()));
			activityLogger.log(MessageTypeConstants.INFO, String.format(UdpServerMessages.UDP_SERVER_STARTED, server.getServerName()));
			while (true) {
				try {
					final byte[] data = new byte[1000];
					final DatagramPacket packet = new DatagramPacket(data, data.length);
					socket.receive(packet);
					
					new Thread(() -> {
						processRequest(activityLogger, server, packet);
		        	}).start();
				} catch (IOException e) {
					activityLogger.log(MessageTypeConstants.ERROR, e.getMessage());
				}
			}
		} catch (SocketException e1) {
			activityLogger.log(MessageTypeConstants.ERROR, e1.getMessage());
		} finally {
            if (socket != null) {
            	socket.close();
            }
        }
    }
    
    private static void processRequest(final ActivityLoggerService activityLogger, final CenterServerImpl server, final DatagramPacket packet) {
    	byte[] response;
        DatagramSocket socket = null;
        final String request = new String(packet.getData()).trim();
        final String[] packetData = request.split(FieldConstants.FIELD_SEPARATOR_ARROW.trim());
        final String sourceServer = packetData[0].trim();
        final String action = packetData[1].trim();
        try {
            socket = new DatagramSocket();
            activityLogger.log(MessageTypeConstants.INFO, String.format(UdpServerMessages.UDP_REQUEST_RECEIVED, action, sourceServer));
            
            if(ActionConstants.GET_COUNT.equalsIgnoreCase(action)) {
            	response = server.getRecordCount().toString().getBytes();
            	socket.send(new DatagramPacket(response, response.length, packet.getAddress(), packet.getPort()));
            } else if(ActionConstants.TRANSFER_RECORD.equalsIgnoreCase(action)) {
            	final String managerId = packetData[2].trim();
            	final String record = packetData[3].trim();
            	response = server.transferRecord(record, managerId).getBytes();
            	socket.send(new DatagramPacket(response, response.length, packet.getAddress(), packet.getPort()));
            }
            
            activityLogger.log(MessageTypeConstants.INFO, String.format(UdpServerMessages.UDP_RESPONSE_SENT, action, sourceServer));
        } catch (IOException e) {
        	activityLogger.log(MessageTypeConstants.ERROR, e.getMessage());
        } finally {
            if (socket != null) {
            	socket.close();
            }
        }
    }
    
}