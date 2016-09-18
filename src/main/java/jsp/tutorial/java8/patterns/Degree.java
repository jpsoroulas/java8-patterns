package jsp.tutorial.java8.patterns;

import org.apache.commons.lang3.Validate;

/**
 * The diploma degree.
 *
 * @author John Psoroulas
 */
public class Degree {

  public enum DegreeField {
    ENGINEERING, CHEMISTRY, PHYSICS, MANAGEMENT
  }

  private DegreeField field;

  private int grade;

  private String holderName;

  private Degree(DegreeBuilder builder) {
    this.holderName = builder.holderName;
    this.field = builder.field;
    this.grade = builder.grade;
  }

  public int getGrade() {
    return grade;
  }

  public DegreeField getField() {
    return field;
  }

  public String getHolderName() {
    return holderName;
  }

  @Override
  public String toString() {
    return "Degree [field=" + field + ", holderName=" + holderName + ", grade=" + grade + "]";
  }

  public static class DegreeBuilder {
    private DegreeField field;

    private int grade;

    private String holderName;

    public DegreeBuilder withField(DegreeField field) {
      this.field = field;
      return this;
    }

    public DegreeBuilder withHolderName(String holderName) {
      this.holderName = holderName;
      return this;
    }

    public DegreeBuilder withGrade(int grade) {
      this.grade = grade;
      return this;
    }

    public Degree build() {
      // TODO some validation about the grades
      Validate.notNull(field, "Undefined degree field!");
      Validate.notEmpty(holderName, "Undefined degree holder's name!");
      return new Degree(this);
    }
  }

}
