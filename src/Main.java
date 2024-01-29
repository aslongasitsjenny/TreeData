
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TreeData data = new TreeData("C:\\Users\\User\\Desktop\\treesPruned\\treesPruned.csv");
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equalsIgnoreCase("quit")) {
            System.out.println("Please enter:");
            System.out.println("1 to print number of trees per borough");
            System.out.println("2 to print most common tree age");
            System.out.println("3 to print least common tree age");
            System.out.println("4 to print average height per tree name");
            System.out.println("5 to enter tree name and print average height");
            System.out.println("quit to quit");

            input = scanner.nextLine();

            switch (input) {
                case "1":
                    data.printTreesPerBorough();
                    break;
                case "2":
                    data.printMostCommonAge();
                    break;
                case "3":
                    data.printLeastCommonAge();
                    break;
                case "4":
                    data.printAverageHeightPerTreeName();
                    break;
                case "5":
                    System.out.println("Enter tree name:");
                    String treeName = scanner.nextLine();
                    data.printAverageHeightForTree(treeName);
                    break;
                case "quit":
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }

        scanner.close();
    }
}