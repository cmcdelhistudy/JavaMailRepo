package view;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SendMailServlet")
public class SendMailServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String mailId = request.getParameter("mailID");
		String message = request.getParameter("message");

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");

		try {
			Properties prop;
			prop = System.getProperties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.port", "587");
			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.smtp.auth", "true");

			MyAuthenticator ma = new MyAuthenticator("hacked.fused@gmail.com",
					"hackingisnotpersonal");

			Session s = Session.getInstance(prop, ma);

			Message m = new MimeMessage(s);
			m.setRecipient(RecipientType.TO, new InternetAddress(mailId));
			m.setFrom(new InternetAddress("hacked.fused@gmail.com"));
			m.setSubject("CMC  Java Mail Study ");

			Multipart mp = new MimeMultipart();

			BodyPart bp = new MimeBodyPart();
			bp.setText(message);

			MimeBodyPart bpFile = new MimeBodyPart();
			bpFile.attachFile(new File("D:/Study/tintin.png"));

			mp.addBodyPart(bp);
			mp.addBodyPart(bpFile);

			m.setContent(mp);

			Transport.send(m);

			out.println("Mail Successfully Sent ");

		} catch (AddressException ae) {
			out.println("Invalid Address " + ae.getMessage());
		} catch (MessagingException e) {
			out.println("Messaging Exception " + e.getMessage());
		} catch (Exception e) {
			out.println("Exception " + e.getMessage());
		}
		out.println("</body>");
		out.println("</html>");

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
