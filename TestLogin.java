package com.salesforce.training.AuthUtils;

import com.salesforce.training.AuthUtils.AuthResultSOAP;
import com.salesforce.training.AuthUtils.AuthSOAP;

/**
 * This is a simple program used to test login using the SOAP API
 * 
 * @author Salesforce University
 *
 */
public class TestLogin {
	//================Code starts here===================
		public static void main(String[] args) {
			
			AuthResultSOAP soapLoginResult = AuthSOAP.doLogin();
			System.out.println("SOAP Result \n" + soapLoginResult.toString());
		}
}
