package controllers;

import com.google.inject.Inject;
import models.EmailAddress;
import models.PendingEmail;
import models.json.EmailJson;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.MailerService;

public class ForwardPostController extends Controller {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private MailerService mailerService;

    @Inject
    public ForwardPostController(MailerService mailerService) {
        this.mailerService = mailerService;
    }

    public Result processEmailRequest(String emailAddress, Http.Request request) {
        logger.debug("Processing Email Request : {} " , emailAddress);
        if (!EmailValidator.getInstance().isValid(emailAddress)) {
            logger.warn("Invalid Email Request : {} " , emailAddress);
            return badRequest();
        }

        if ( EmailAddress.existsByEmailAddress(emailAddress) ) {
            logger.debug("Email exists on DB : {} " , emailAddress);
            if ( EmailAddress.isConfirmedByEmailAddress(emailAddress) ) {
                logger.debug("Email is confirmed : {} " , emailAddress);
                // Send Email
                mailerService.sendEmail(
                        emailAddress,
                        "TITLE PLACER",
                        "BODY PLACER"
                );
                // TODO Show Success Page
            } else {
                logger.debug("Email NOT confirmed : {} " , emailAddress);
                // Store Email
                PendingEmail.createNewPendingEmail(
                        EmailAddress.findByEmailAddress(emailAddress),
                        new EmailJson(
                                request.queryString(),
                                request.body()
                        )
                );
                // TODO Show Success Page
            }
        } else {
            logger.debug("Email NOT on DB : {} " , emailAddress);
            EmailAddress emailAddressEntity = EmailAddress.createNewEmailAddress(emailAddress);
            // Store Email
            PendingEmail.createNewPendingEmail(
                    emailAddressEntity,
                    new EmailJson(
                            request.queryString(),
                            request.body()
                    )
            );
            // TODO Send Confirmation Email
        }

        return ok();
    }

}
