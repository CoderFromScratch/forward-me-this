package controllers;

import models.EmailAddress;
import models.PendingEmail;
import models.json.EmailJson;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

public class ForwardPostController extends Controller {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    public Result processEmailRequest(String emailAddress, Http.Request request) {
        logger.debug("Processing Email Request : {} " , emailAddress);
        if (!EmailValidator.getInstance().isValid(emailAddress)) {
            logger.warn("Invalid Email Request : {} " , emailAddress);
            return badRequest();
        }

        if ( EmailAddress.existsByEmailAddress(emailAddress) ) {
            if ( EmailAddress.isConfirmedByEmailAddress(emailAddress) ) {
                // TODO Send Email
                // TODO Show Success Page
            } else {
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
