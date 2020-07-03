package service;

import domain.LoanApplication;

import java.util.ArrayList;
import java.util.List;

public class LoanService {

  private List<LoanApplication> loans = new ArrayList<>();

  public void add(LoanApplication application) {
    if(loans.contains(application)) {
      return;
    }

    loans.add(application);
  }

  public List<LoanApplication> getAll() {
    return loans;
  }

}
