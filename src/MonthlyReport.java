import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {

  private final String FILE_NAME = "m.20210";
  private final String EXPANSION = ".csv";
  private final int NUMBER_MONTHLY_REPORTS = 3;
  private int allOperationsCount = 0;
  private boolean monthlyReportsRead = false;
  final FileReader fileReader = new FileReader();
  private final HashMap<Integer, ArrayList<Transaction>> transactions = new HashMap<>();

  public void readMonthlyReports() {

    allOperationsCount = 0;
    transactions.clear();

    for (int i = 1; i <= NUMBER_MONTHLY_REPORTS; i++) {
      ArrayList<String> contentMonth = fileReader.readFileContents(
        FILE_NAME + i + EXPANSION
      );
      for (int j = 1; j < contentMonth.size(); j++) {
        String line = contentMonth.get(j);
        String[] fields = line.split(",");
        String name = fields[0];
        boolean expense = Boolean.parseBoolean(fields[1]);
        int quantity = Integer.parseInt(fields[2]);
        int sumOfOne = Integer.parseInt(fields[3]);
        Transaction transaction = new Transaction(
          name,
          expense,
          quantity,
          sumOfOne
        );

        allOperationsCount += 1;
        
        if (transactions.containsKey(i)) {
          transactions.get(i).add(transaction);
        } else {
          ArrayList<Transaction> transactionList = new ArrayList<>();
          transactionList.add(transaction);
          transactions.put(i, transactionList);
        }
        monthlyReportsRead = true;
      }
      System.out.println("Из отчета номер " + i + " данные успешно считаны.");
    }
  }

  public void getMaximumExpenses(
    int monthNumber,
    ArrayList<Transaction> transactionList
  ) {
    String productName = "";
    int price = 0;
    for (Transaction transaction : transactionList) {
      int sum = transaction.quantity * transaction.sumOfOne;
      if (transaction.expense && price < sum) {
        price = sum;
        productName = transaction.name;
      }
    }
    System.out.println(
      "За " +
      monthNumber +
      " месяц " +
      "наибольшая трата пришлась на " +
      productName +
      " и составила " +
      price + "."
    );
  }

  public void getMaximumProfits(
    int monthNumber,
    ArrayList<Transaction> transactionList
  ) {
    String productName = "";
    int price = 0;
    for (Transaction transaction : transactionList) {
      int sum = transaction.quantity * transaction.sumOfOne;
      if (!transaction.expense && price < sum) {
        price = sum;
        productName = transaction.name;
      }
    }
    System.out.println(
      "За " +
      monthNumber +
      " месяц " +
      "самым прибыльным товаром является " +
      productName +
      " на сумму " +
      price + "."
    );
  }
  public int getAllProfits(
          ArrayList<Transaction> transactionList
  ) {

    int profit = 0;
    for (Transaction transaction : transactionList) {
      if (!transaction.expense ) {
        profit += transaction.quantity * transaction.sumOfOne;
      }
    }
    return profit;
  }

  public int getAllExpenses(
          ArrayList<Transaction> transactionList
  ) {

    int expenses = 0;
    for (Transaction transaction : transactionList) {
      if (transaction.expense ) {
        expenses += transaction.quantity * transaction.sumOfOne;
      }
    }
    return expenses;
  }


  public HashMap<Integer, ArrayList<Transaction>> getTransactions() {
    return transactions;
  }

  public boolean isMonthlyReportsRead() {
    return monthlyReportsRead;
  }

  public int allOperationsCount() {
    return allOperationsCount;
  }
}
