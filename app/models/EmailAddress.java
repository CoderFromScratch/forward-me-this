package models;

import io.ebean.Finder;
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

    private static Finder<Long, EmailAddress> finder = new Finder<Long, EmailAddress>(EmailAddress.class);

    public static boolean existsByEmailAddress(String inputEmailAddress) {
        return finder.query()
                .where()
                .eq("emailAddress", inputEmailAddress)
                .exists();
    }

    public static boolean isConfirmedByEmailAddress(String inputEmailAddress) {
        return finder.query()
                .where()
                .eq("emailAddress", inputEmailAddress)
                .eq("emailConfirmed", true)
                .exists();
    }

    public static EmailAddress createNewEmailAddress(String inputEmailAddress) {
        EmailAddress newEmailAddress =  new EmailAddress();
        newEmailAddress.emailAddress = inputEmailAddress;
        newEmailAddress.emailConfirmed = false;
        newEmailAddress.save();
        return newEmailAddress;
    }

    public static EmailAddress findByEmailAddress(String emailAddress) {
        return finder.query().where().eq("emailAddress", emailAddress).findOne();
    }
}
