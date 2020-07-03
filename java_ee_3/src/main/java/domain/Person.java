package domain;

import java.util.Date;

public class Person {
  private String firstname;
  private String surname;
  private Date dob;
  private String pesel;

  public String getFirstname () {
    return firstname;
  }

  public void setFirstname (String firstname) {
    this.firstname = firstname;
  }

  public String getSurname () {
    return surname;
  }

  public void setSurname (String surname) {
    this.surname = surname;
  }

  public Date getDob () {
    return dob;
  }

  public void setDob (Date dob) {
    this.dob = dob;
  }

  public String getPesel () {
    return pesel;
  }

  public void setPesel (String pesel) {
    this.pesel = pesel;
  }
}
