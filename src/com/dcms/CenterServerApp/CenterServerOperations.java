package com.dcms.CenterServerApp;


/**
* CenterServerApp/CenterServerOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CenterServer.idl
* Friday, June 15, 2018 at 9:31:03 PM Eastern Daylight Time
*/

public interface CenterServerOperations 
{
  String createTRecord (String firstName, String lastName, String address, String phone, String specialization, String location, String managerId);
  String createSRecord (String firstName, String lastName, String coursesRegistered, String status, String statusDate, String managerId);
  String editRecord (String recordID, String fieldName, String newValue, String managerId);
  String getRecordCounts (String managerId);
  String displayRecord (String recordId, String managerId);
  String transferRecord (String managerID, String recordID, String remoteCenterServerName);
} // interface CenterServerOperations
