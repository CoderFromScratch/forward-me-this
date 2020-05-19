package controllers;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Controller;
import play.mvc.Result;

public class ForwardPostController extends Controller {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    public Result processEmailRequest(String emailAddress) {
        logger.debug("Processing Email Request : {} " , emailAddress);
        if (!EmailValidator.getInstance().isValid(emailAddress)) {
            logger.warn("Invalid Email Request : {} " , emailAddress);
            return badRequest();
        }

        return ok();
    }

}
