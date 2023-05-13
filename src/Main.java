import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    MonthlyReport monthlyReport = new MonthlyReport();
    YearlyReport yearlyReport = new YearlyReport();
    ReportEngine reportEngine = new ReportEngine(monthlyReport, yearlyReport);

    while (true) {
      mainMenu();

      String command = scanner.nextLine();
      if (command.equals("1")) {
        System.out.println(1);
        monthlyReport.readMonthlyReports();
      } else if (command.equals("2")) {
        System.out.println(2);
        yearlyReport.readYearlyReport();
      } else if (command.equals("3")) {
        System.out.println(3);
        reportEngine.verification();
      } else if (command.equals("4")) {
        System.out.println(4);
        reportEngine.getMonthStatistic();
      } else if (command.equals("5")) {
        System.out.println(5);
        reportEngine.getYearStatistic();
      } else if (command.equals("Exit")) {
        return;
      } else {
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
