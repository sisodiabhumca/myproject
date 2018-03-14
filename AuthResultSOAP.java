package com.salesforce.training.AuthUtils;

//The Web Services Connector jar provides a connection that the SOAP
//API will use for further requests to the API.
import com.sforce.soap.enterprise.EnterpriseConnection;

/**
 * This class acts as a data structure containing data that programs
 * will need after authorization is complete to continue communicating
 * with the org. It has a data structure to specifically support communication
 * for the SOAP API.
 * 
 * It is used by the AuthSOAP class to store results.
 * 
 * @author Salesforce University
 *
 */
public class AuthResultSOAP {

	//Using SOAP for login, a session id will be returned. 
	//It can be placed in the header of an HTTPRequest object 
	//for further communication.
	private String mySessionId = "";
	
	//Some APIs need a base URI to continue communication
	//with the org. The Streaming API is an example of this.
	private String myBaseURI = "";

	//This member variable can be used to determine if login was successful.
	private Boolean myLoginSuccessful = false;

	//The connection is used by SOAP methods for further communication with API 
	private EnterpriseConnection myConnection = null;

	public EnterpriseConnection getConnection() {
		return myConnection;
	}

	public void setConnection(EnterpriseConnection connection) {
		this.myConnection = connection;
	}

	public String getSessionId() {
		return mySessionId;
	}

	public void setSessionId(String SessionId) {
		mySessionId = SessionId;
	}

	public String getBaseURI() {
		return myBaseURI;
	}

	public void setBaseURI(String baseURI) {
		myBaseURI = baseURI;
	}

	public Boolean getIsSuccessful() {
		return myLoginSuccessful;
	}

	public void setIsSuccessful(Boolean IsSuccessful) {
		myLoginSuccessful = IsSuccessful;
	}

	public String toString() {
		String tempStr = "-------AuthResult-----------\n";

		if (getIsSuccessful()) {
			tempStr += "Login was successful\n"
				+ "baseURI = " + getBaseURI() + "\n" 
				+ "sessionId = " + getSessionId() + "\n";
		} else {
			tempStr =  "Login was not successful\n"; 
		}
		return tempStr + "-----End AuthResult---------\n";       
	}
}
