package employee;

import javax.jdo.annotations.PersistenceCapable;
import java.util.Date;

/**
 * Created by pawelwiejkut on 04.04.2016.
 */
@PersistenceCapable
public class Task {
    private String description;
    private Date startDate;
    private Date endDate;
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("description='").append(description).append('\'');
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", project=").append(project);
        sb.append('}');
        return sb.toString();
    }
}
