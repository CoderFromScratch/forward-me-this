package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class EmailAddress extends Model {

    @Id
    public long idEmailAddress;

    public String emailAddress;

    public boolean emailConfirmed;

    @OneToMany(mappedBy = "emailAddress")
    public List<PendingEmail> pendingEmailList;

}
