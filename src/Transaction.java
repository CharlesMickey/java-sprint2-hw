public class Transaction {
    String name;
    boolean expense;
    int quantity;
    int sumOfOne;

    public Transaction(String name, boolean expense, int quantity, int sumOfOne) {
        this.name = name;
        this.expense = expense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}
