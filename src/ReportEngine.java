import java.util.ArrayList;
import java.util.HashMap;

public class ReportEngine {

  public MonthlyReport monthlyReport;
  public YearlyReport yearlyReport;

  public ReportEngine(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
    this.monthlyReport = monthlyReport;
    this.yearlyReport = yearlyReport;
  }

  public void getMonthStatistic() {
    if (monthlyReport.isMonthlyReportsRead()) {
      HashMap<Integer, ArrayList<Transaction>> transactions = monthlyReport.getTransactions();
      for (int i = 1; i <= transactions.size(); i++) {
        ArrayList<Transaction> transactionList = transactions.get(i);
        monthlyReport.getMaximumProfits(i, transactionList);
        monthlyReport.getMaximumExpenses(i, transactionList);
      }
    } else {
      System.out.println(
        "Пожалуйста, сначала считайте данные месячных отчетов"
      );
    }
  }

  public void getYearStatistic() {
    if (
      yearlyReport.isYearlyReportsRead() && monthlyReport.isMonthlyReportsRead()
    ) {
      HashMap<Integer, ArrayList<MonthTotalPerYear>> monthTotalPerYear = yearlyReport.getMonthTotalPerYear();
      for (int i = 1; i <= monthTotalPerYear.size(); i++) {
        ArrayList<MonthTotalPerYear> monthTotalPerYearList = monthTotalPerYear.get(
          i
        );
        yearlyReport.profitForEachMonth(i, monthTotalPerYearList);
      }
      System.out.println(
        "Средний доход за все имеющиеся операции в году составил: " +
        String.format(
          "%.3f",
          yearlyReport.profitsPerYear() / monthlyReport.allOperationsCount()
        )
      );
      System.out.println(
        "Средний расход за все имеющиеся операции в году составил: " +
        String.format(
          "%.3f",
          yearlyReport.expensesPerYear() / monthlyReport.allOperationsCount()
        )
      );
    } else {
      System.out.println(
        "Пожалуйста, сначала считайте данные годового и месячных отчетов"
      );
    }
  }

  public void verification() {
    boolean discrepanciesFound = false;
    if (
      monthlyReport.isMonthlyReportsRead() && yearlyReport.isYearlyReportsRead()
    ) {
      HashMap<Integer, ArrayList<Transaction>> transactions = monthlyReport.getTransactions();
      HashMap<Integer, ArrayList<MonthTotalPerYear>> monthTotalPerYear = yearlyReport.getMonthTotalPerYear();

      for (int i = 1; i <= transactions.size(); i++) {
        ArrayList<Transaction> transactionList = transactions.get(i);
        int allProfits = monthlyReport.getAllProfits(transactionList);
        int allExpenses = monthlyReport.getAllExpenses(transactionList);

        for (MonthTotalPerYear totalPerYear : monthTotalPerYear.get(i)) {
          String message = "";
          if (totalPerYear.expense && totalPerYear.amount != allExpenses) {
            message =
              "Сумма расходов в отчета за " +
              i +
              " месяц, не соответсвтует сумме расходов в годовом отчете. Год: " +
              totalPerYear.amount +
              ", месяц: " +
              allExpenses;
            discrepanciesFound = true;
          } else if (
            !totalPerYear.expense && totalPerYear.amount != allProfits
          ) {
            message =
              "Сумма доходов в отчета за " +
              i +
              " месяц, не соответсвтует сумме доходов в годовом отчете. Год: " +
              totalPerYear.amount +
              ", месяц: " +
              allProfits;
            discrepanciesFound = true;
          } else {
            continue;
          }
          System.out.println(message);
        }
      }
      if (!discrepanciesFound) {
        System.out.println("Успех! Расхождений не обнаружено ");
      }
    } else {
      System.out.println(
        "Пожалуйста сначала считайте данные месячных и годового отчетов"
      );
    }
  }
}
