package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.*;

/*
 */
public class HomeController extends Controller {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /*
     *
     */
    public Result index() {
        logger.debug("Index called");
        return ok(views.html.index.render());
    }

}
