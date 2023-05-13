import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {

  private final String FILE_NAME = "y.2021.csv";
  final FileReader fileReader = new FileReader();
  private boolean yearlyReportsRead = false;
  private final HashMap<Integer, ArrayList<MonthTotalPerYear>> monthsTotalPerYear = new HashMap<>();

  void readYearlyReport() {
    monthsTotalPerYear.clear();
    ArrayList<String> contentYear = fileReader.readFileContents(FILE_NAME);
    for (int i = 1; i < contentYear.size(); i++) {
      String line = contentYear.get(i);
      String[] fields = line.split(",");

      int month = Integer.parseInt(fields[0]);
      int amount = Integer.parseInt(fields[1]);
      boolean expense = Boolean.parseBoolean(fields[2]);

      MonthTotalPerYear monthTotalPerYear = new MonthTotalPerYear(
        month,
        amount,
        expense
      );
      if (monthsTotalPerYear.containsKey(month)) {
        monthsTotalPerYear.get(month).add(monthTotalPerYear);
      } else {
        ArrayList<MonthTotalPerYear> monthsTotalPerYearList = new ArrayList<>();
        monthsTotalPerYearList.add(monthTotalPerYear);
        monthsTotalPerYear.put(month, monthsTotalPerYearList);
      }
      yearlyReportsRead = true;
    }
    System.out.println("Из годового отчета данные успешно считаны.");
  }

  public HashMap<Integer, ArrayList<MonthTotalPerYear>> getMonthTotalPerYear() {
    return monthsTotalPerYear;
  }

  public void profitForEachMonth(
    int montNumber,
    ArrayList<MonthTotalPerYear> monthTotalPerYearList
  ) {
    int expense = 0;
    int profit = 0;
    for (MonthTotalPerYear monthTotalPerYear : monthTotalPerYearList) {
      if (monthTotalPerYear.expense) {
        expense = monthTotalPerYear.amount;
      } else {
        profit = monthTotalPerYear.amount;
      }
    }

    int difference = profit - expense;
    System.out.println(
      String.format(
        "За %d месяц прибыль составила %s%d",
        montNumber,
        difference < 0 ? "-" : "",
        Math.abs(difference)
      )
    );
  }

  public int expensesPerYear() {
    int expense = 0;
    for (int i = 1; i <= monthsTotalPerYear.size(); i++) {
      ArrayList<MonthTotalPerYear> monthTotalPerYearList = monthsTotalPerYear.get(
        i
      );

      for (MonthTotalPerYear monthTotalPerYear : monthTotalPerYearList) {
        if (monthTotalPerYear.expense) {
          expense += monthTotalPerYear.amount;
        }
      }
    }
    return expense;
  }

  public int profitsPerYear() {
    int profit = 0;
    for (int i = 1; i <= monthsTotalPerYear.size(); i++) {
      ArrayList<MonthTotalPerYear> monthTotalPerYearList = monthsTotalPerYear.get(
        i
      );
      for (MonthTotalPerYear monthTotalPerYear : monthTotalPerYearList) {
        if (!monthTotalPerYear.expense) {
          profit += monthTotalPerYear.amount;
        }
      }
    }
    return profit;
  }

  public boolean isYearlyReportsRead() {
    return yearlyReportsRead;
  }
}
