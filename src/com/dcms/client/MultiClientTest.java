package com.dcms.client;

import java.util.HashSet;

import com.dcms.util.LocationConstants;
import com.dcms.util.StatusConstants;

public class MultiClientTest implements Runnable {

    private String managerID;
    private String args[];

    public MultiClientTest(final String managerId, final String args[]) {
        this.managerID = managerId;
        this.args = args;
    }

    public static void main(String args[]) throws Exception {
        final HashSet<String> managers = new HashSet<>();
        managers.add("MTL00001");
        managers.add("MTL00002");
        managers.add("MTL00003");
        managers.add("MTL00004");
        managers.add("LVL00001");
        managers.add("LVL00002");
        managers.add("DDO00001");
        managers.add("DDO00002");
        managers.add("MMM00001");

        for (final String managerId : managers) {
            final MultiClientTest clientThread = new MultiClientTest(managerId, args);
            final Thread t = new Thread(clientThread);
            t.start();
            System.out.println("Thread Started");
        }

        Thread.sleep(6000);
        final ClientManager clientManager = new ClientManager(args, "MTL0001");
        System.out.println("******************** END RESULT ********************");
        clientManager.getRecordCounts();
        System.out.println("****************************************************");
    }

    public void run() {
        try {
        	if (this.managerID.startsWith(LocationConstants.MONTREAL) 
					|| this.managerID.startsWith(LocationConstants.LAVAL)
					|| this.managerID.startsWith(LocationConstants.DOLLARD)) {
        		
        		final ClientManager clientManager = new ClientManager(this.args, this.managerID);
        		
        		//Testing student record creation, edition and display functionality with proper data 
        		clientManager.createSRecord("Sagar", "Vetal", "French, English", StatusConstants.ACTIVE, "02-05-2018");
        		clientManager.displayRecord("SR00001");
        		clientManager.editRecord("SR00001", "status", "InActive");
        		clientManager.editRecord("SR00001", "courses registered", "Spanish, french");
        		clientManager.displayRecord("SR00001");
        		
        		//Testing teacher record creation, edition and display functionality with proper data 
        		clientManager.createTRecord("Himanshu", "Kohali", "Montreal", "0123456789", "french", LocationConstants.MONTREAL);
        		clientManager.displayRecord("TR00001");
        		clientManager.editRecord("TR00001", "address", "Alberta");
        		clientManager.editRecord("TR00001", "phone", "0123456789");
        		clientManager.editRecord("TR00001", "location", LocationConstants.LAVAL);
        		clientManager.displayRecord("TR00001");
        		
        		//Testing get record count and transfer record functionality
        		clientManager.getRecordCounts();
        		clientManager.transferRecord("SR00001", LocationConstants.LAVAL);
        		clientManager.getRecordCounts();
        		clientManager.transferRecord("SR00002", LocationConstants.DOLLARD);
        		clientManager.getRecordCounts();
        		
        		//Testing edit record functionality with improper data to test validations
        		clientManager.editRecord("TR00015", "address", "London");
        		clientManager.editRecord("TR00001", "last name", "London");
        		clientManager.editRecord("TR00001", "location", "LON");
        		
        		//Testing get record count and transfer record functionality
        		clientManager.getRecordCounts();
        		clientManager.transferRecord("TR00003", LocationConstants.LAVAL);
        		clientManager.getRecordCounts();
        		clientManager.transferRecord("TR00004", LocationConstants.DOLLARD);
        		clientManager.getRecordCounts();
        	} else {
        		System.out.println("ERROR => Invalid Manager Id");
        	}
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("********************");
        }
    }
}
