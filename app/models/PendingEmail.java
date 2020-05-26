package models;

import com.fasterxml.jackson.databind.JsonNode;
import io.ebean.Model;
import models.json.EmailJson;
import play.libs.Json;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PendingEmail extends Model {

    @Id
    public long idPendingEmail;

    @Column(nullable = false)
    public JsonNode requestJson;

    @ManyToOne
    public EmailAddress emailAddress;

    public static PendingEmail createNewPendingEmail(EmailAddress emailAddress, EmailJson emailJson) {
        PendingEmail pendingEmail = new PendingEmail();
        pendingEmail.emailAddress = emailAddress;
        pendingEmail.requestJson = Json.toJson(emailJson);
        pendingEmail.save();
        return pendingEmail;
    }

    public EmailJson getEmailJson() {
        return Json.fromJson(
                requestJson,
                EmailJson.class
        );
    }
}
