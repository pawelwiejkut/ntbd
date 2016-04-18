package employee;

import javax.jdo.annotations.PersistenceCapable;
import java.util.Date;

/**
 * Created by pawelwiejkut on 16.04.2016.
 */

@PersistenceCapable
public class Applicant extends Person{
    private Date applicationdate;
    private String position;

    public Date getApplicationdate() {
        return applicationdate;
    }

    public void setApplicationdate(Date applicationdate) {
        this.applicationdate = applicationdate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "applicationdate=" + applicationdate +
                ", position='" + position + '\'' +
                '}';
    }
}
