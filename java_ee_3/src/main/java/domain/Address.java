package domain;

public class Address {

  private String city;
  private String zipCode;
  private String street;
  private String houseNumber;
  private String localNumber;
  private String phoneNumber;

  public String getCity () {
    return city;
  }

  public void setCity (String city) {
    this.city = city;
  }

  public String getZipCode () {
    return zipCode;
  }

  public void setZipCode (String zipCode) {
    this.zipCode = zipCode;
  }

  public String getStreet () {
    return street;
  }

  public void setStreet (String street) {
    this.street = street;
  }

  public String getHouseNumber () {
    return houseNumber;
  }

  public void setHouseNumber (String houseNumber) {
    this.houseNumber = houseNumber;
  }

  public String getLocalNumber () {
    return localNumber;
  }

  public void setLocalNumber (String localNumber) {
    this.localNumber = localNumber;
  }

  public String getPhoneNumber () {
    return phoneNumber;
  }

  public void setPhoneNumber (String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
