import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    FileReader fileReader = new FileReader();
    ArrayList contentYearFile = fileReader.readFileContents("y.2021.csv");
    System.out.println(contentYearFile);

    while (true) {
      mainMenu();

      String command = scanner.nextLine();
      if (command.equals("1")) {
        System.out.println(1);
      } else if (command.equals("2")) {
        System.out.println(2);
      } else if (command.equals("3")) {
        System.out.println(3);
      } else if (command.equals("4")) {
        System.out.println(4);
      } else if (command.equals("5")) {
          System.out.println(5);
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
