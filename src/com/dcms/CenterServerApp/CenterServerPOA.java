package com.dcms.CenterServerApp;


/**
* CenterServerApp/CenterServerPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CenterServer.idl
* Friday, June 15, 2018 at 9:31:03 PM Eastern Daylight Time
*/

public abstract class CenterServerPOA extends org.omg.PortableServer.Servant
 implements CenterServerOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("createTRecord", new java.lang.Integer (0));
    _methods.put ("createSRecord", new java.lang.Integer (1));
    _methods.put ("editRecord", new java.lang.Integer (2));
    _methods.put ("getRecordCounts", new java.lang.Integer (3));
    _methods.put ("displayRecord", new java.lang.Integer (4));
    _methods.put ("transferRecord", new java.lang.Integer (5));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // CenterServerApp/CenterServer/createTRecord
       {
         String firstName = in.read_string ();
         String lastName = in.read_string ();
         String address = in.read_string ();
         String phone = in.read_string ();
         String specialization = in.read_string ();
         String location = in.read_string ();
         String managerId = in.read_string ();
         String $result = null;
         $result = this.createTRecord (firstName, lastName, address, phone, specialization, location, managerId);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // CenterServerApp/CenterServer/createSRecord
       {
         String firstName = in.read_string ();
         String lastName = in.read_string ();
         String coursesRegistered = in.read_string ();
         String status = in.read_string ();
         String statusDate = in.read_string ();
         String managerId = in.read_string ();
         String $result = null;
         $result = this.createSRecord (firstName, lastName, coursesRegistered, status, statusDate, managerId);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 2:  // CenterServerApp/CenterServer/editRecord
       {
         String recordID = in.read_string ();
         String fieldName = in.read_string ();
         String newValue = in.read_string ();
         String managerId = in.read_string ();
         String $result = null;
         $result = this.editRecord (recordID, fieldName, newValue, managerId);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 3:  // CenterServerApp/CenterServer/getRecordCounts
       {
         String managerId = in.read_string ();
         String $result = null;
         $result = this.getRecordCounts (managerId);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 4:  // CenterServerApp/CenterServer/displayRecord
       {
         String recordId = in.read_string ();
         String managerId = in.read_string ();
         String $result = null;
         $result = this.displayRecord (recordId, managerId);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 5:  // CenterServerApp/CenterServer/transferRecord
       {
         String managerID = in.read_string ();
         String recordID = in.read_string ();
         String remoteCenterServerName = in.read_string ();
         String $result = null;
         $result = this.transferRecord (managerID, recordID, remoteCenterServerName);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:CenterServerApp/CenterServer:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public CenterServer _this() 
  {
    return CenterServerHelper.narrow(
    super._this_object());
  }

  public CenterServer _this(org.omg.CORBA.ORB orb) 
  {
    return CenterServerHelper.narrow(
    super._this_object(orb));
  }


} // class CenterServerPOA
