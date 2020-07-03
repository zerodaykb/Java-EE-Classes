package domain;

public class LoanParameters {

  private int installmentCount;
  private int amount;

  public int getInstallmentCount () {
    return installmentCount;
  }

  public void setInstallmentCount (int installmentCount) {
    this.installmentCount = installmentCount;
  }

  public int getAmount () {
    return amount;
  }

  public void setAmount (int amount) {
    this.amount = amount;
  }
}
