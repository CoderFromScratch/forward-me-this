package models;

import com.fasterxml.jackson.databind.JsonNode;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PendingEmail extends Model {

    @Id
    public long idPendingEmail;

    public JsonNode requestJson;

    @ManyToOne
    public EmailAddress emailAddress;

}
