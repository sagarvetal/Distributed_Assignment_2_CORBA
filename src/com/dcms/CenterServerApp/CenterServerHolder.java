package com.dcms.CenterServerApp;

/**
* CenterServerApp/CenterServerHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CenterServer.idl
* Friday, June 15, 2018 at 9:31:03 PM Eastern Daylight Time
*/

public final class CenterServerHolder implements org.omg.CORBA.portable.Streamable
{
  public CenterServer value = null;

  public CenterServerHolder ()
  {
  }

  public CenterServerHolder (CenterServer initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = CenterServerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    CenterServerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return CenterServerHelper.type ();
  }

}
