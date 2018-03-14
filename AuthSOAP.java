package com.salesforce.training.AuthUtils;

//Classes generated with the web services connector tool.
//These could potentially be replaced with the partner version.
//The login methods are the same for both enterprise and partner wsdl.
import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;

//Classes provided by the web services connector tool.
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

/**
 * This class provides methods for logging into and out of the salesforce.com orgs.
 * It is based on the enterprise WSDL. The WSDL is consumed by an external program,
 * the Web Services Connector (wsc) jar, and a file enterprise.jar is produced. Then, both
 * the enterprise.jar and the wsc jar are provided to this program as libraries.
 * 
 * @author Salesforce University
 * 
 */
public class AuthSOAP {
	// ---------Credentials----------
	// Credentials providing access to a specific Salesforce organization.
	/********************************************************
	 * TODO #1:
	 * Right click your project and set your credentials in the Environment Variables under Run As | Run Configurations...
	 * Look for your java file under Java Application
	 * 1. Enter Salesforce login userName
	 * 2. Enter Salesforce login password
	 * 3. Optionally add token if outside of trusted IP range.
	 * 
	 ********************************************************/
	private static final String USERNAME = System.getenv("Username");
	private static final String PASSWORD = System.getenv("Password");
	private static final String TOKEN = System.getenv("Token"); 

	/**
	 * This method attempts to connect to the org through the SOAP API.
	 * @return AuthResult - This object contains the key fields needed to reconnect the org.
	 */
	public static AuthResultSOAP doLogin() {

		AuthResultSOAP authenticationResult = new AuthResultSOAP();  //Final results of login.
		ConnectorConfig config = new ConnectorConfig();      //Object through which a connection is made.
		config.setUsername(USERNAME);
		// append token to password if needed.
		config.setPassword(PASSWORD + TOKEN);

		// config.setTraceMessage(true);
		/* If using a proxy to get through a firewall, uncomment the following 
		 * code and set appropriate values. 
		 */
		// config.setProxy("proxyServer.corp.myCorp.com",8080);
		// config.setNtlmDomain("NtlmDom");
		// config.setProxyUsername("proxyUserName");
		// config.setProxyPassword("***");
		try {
			//Attempt login
			EnterpriseConnection connection = Connector.newConnection(config);

			//Set values from result needed for contacting org after login.
			String baseURI = config.getServiceEndpoint().substring(0,
					config.getServiceEndpoint().indexOf("/", 8));
			authenticationResult.setBaseURI(baseURI);
			authenticationResult.setSessionId(config.getSessionId());
			authenticationResult.setIsSuccessful(true);
			authenticationResult.setConnection(connection);
			
			//Display key values returned after successful login.
			System.out.println("-------SOAP Login Results-----------");
			System.out.println("Auth EndPoint: " + config.getAuthEndpoint());
			System.out.println("Service EndPoint: " + config.getServiceEndpoint());
			System.out.println("Base URI: " + baseURI);
			System.out.println("Username: " + config.getUsername());
			System.out.println("SessionId: " + config.getSessionId());
			System.out.println("Sforce service created.");
			System.out.println("------- End SOAP Login Results--------");

			return authenticationResult;
		} catch (ConnectionException e1) {
			e1.printStackTrace();
			authenticationResult.setIsSuccessful(false);
			return authenticationResult;
		}
	}

}
