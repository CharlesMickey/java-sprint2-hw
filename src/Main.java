import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    MonthlyReport monthlyReport = new MonthlyReport();
    YearlyReport yearlyReport = new YearlyReport();
    ReportEngine reportEngine = new ReportEngine(monthlyReport, yearlyReport);

    while (true) {
      mainMenu();

      String command = scanner.nextLine().trim();
      switch (command) {
        case "1":
          monthlyReport.readMonthlyReports();
          break;
        case "2":
          yearlyReport.readYearlyReport();
          break;
        case "3":
          reportEngine.verification();
          break;
        case "4":
          reportEngine.getMonthStatistic();
          break;
        case "5":
          reportEngine.getYearStatistic();
          break;
        case "Exit":
          return;
        default:
          System.out.println("Кажется такой команды нет...;(");
      }
    }
  }

  public static void mainMenu() {
    System.out.println("Введите команду:");
    System.out.println("1: Считать все месячные отчёты");
    System.out.println("2: Считать годовой отчёт");
    System.out.println("3: Сверить отчёты");
    System.out.println("4: Вывести информацию обо всех месячных отчётах ");
    System.out.println("5: Вывести информацию о годовом отчёте");
    System.out.println("Exit: Выход");
  }
}
