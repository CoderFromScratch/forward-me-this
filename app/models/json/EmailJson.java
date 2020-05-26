package models.json;

import play.mvc.Http;

import java.io.Serializable;
import java.util.Map;

public class EmailJson implements Serializable {

    private Map<String, String[]> parameters;
    private String bodyAsString;

    public EmailJson() {
    }

    public EmailJson(Map<String, String[]> parameters, Http.RequestBody body) {
        this.parameters = parameters;
        this.bodyAsString = body.asText();
    }

    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    public void setBodyAsString(String bodyAsString) {
        this.bodyAsString = bodyAsString;
    }

    public Map<String, String[]> getParameters() {
        return parameters;
    }

    public String getBodyAsString() {
        return bodyAsString;
    }
}
