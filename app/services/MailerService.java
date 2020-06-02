package services;

import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import javax.inject.Inject;
import java.io.File;
import org.apache.commons.mail.EmailAttachment;

public class MailerService {
    @Inject
    MailerClient mailerClient;

    public void sendEmail(String emailAddress, String emailTitle, String emailBody) {

        Email email = new Email()
                .setSubject(emailTitle)
                .setFrom("Forward Me This <no-reply@forwardmethis.com>")
                .addTo(emailAddress)
                // adds attachment
                // .addAttachment("attachment.pdf", new File("/some/path/attachment.pdf"))
                // adds inline attachment from byte array
                // .addAttachment("data.txt", "data".getBytes(), "text/plain", "Simple data", EmailAttachment.INLINE)
                // adds cid attachment
                // .addAttachment("image.jpg", new File("/some/path/image.jpg"), cid)
                // sends text, HTML or both...
                .setBodyText(emailBody)
                //.setBodyHtml("<html><body><p>An <b>html</b> message with cid <img src=\"cid:" + cid + "\"></p></body></html>")
                ;
        mailerClient.send(email);
    }

}
