package view;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {

	PasswordAuthentication pa;

	public MyAuthenticator(String username, String password) {
		pa = new PasswordAuthentication(username, password);
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}

}
