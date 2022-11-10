public class Account {
private String firstName;
private String lastName;
private int bornDate;
private String emailAddress;
private String passportNumber;
private String passWord;

public Account(String firstName, String lastName, int bornDate, String emailAddress, String passportNumber,
        String passWord) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.bornDate = bornDate;
    this.emailAddress = emailAddress;
    this.passportNumber = passportNumber;
    this.passWord = passWord;
}

public String getFirstName() {
    return firstName;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}

public String getLastName() {
    return lastName;
}

public void setLastName(String lastName) {
    this.lastName = lastName;
}

public int getBornDate() {
    return bornDate;
}

public void setBornDate(int bornDate) {
    this.bornDate = bornDate;
}

public String getEmailAddress() {
    return emailAddress;
}

public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
}

public String getPassportNumber() {
    return passportNumber;
}

public void setPassportNumber(String passportNumber) {
    this.passportNumber = passportNumber;
}

public String getPassWord() {
    return passWord;
}

public void setPassWord(String passWord) {
    this.passWord = passWord;
}

}
