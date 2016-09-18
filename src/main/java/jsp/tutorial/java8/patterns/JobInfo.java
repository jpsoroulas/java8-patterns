package jsp.tutorial.java8.patterns;

/**
 * Holds the information about the job.
 *
 * @author John Psoroulas
 */
public class JobInfo {

  /**
   * Some job categories for easiest filtering by the departments.
   */
  public enum JobCategory {
    ENGINEERING, SALES, LOGISTICS;
  }

  private JobCategory category;

  private String description;

  public JobInfo(JobCategory category, String description) {
    this.category = category;
    this.description = description;
  }

  public JobCategory getJobCategory() {
    return category;
  }

  public String getDescription() {
    return description;
  }

}
