import java.util.Scanner;

/**
 * Created by d526f on 8/15/2017.
 */
public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Keyword to search for: ");
        String keyword = scanner.nextLine();

        Bidi bidi = new Bidi(keyword);
        bidi.start();
    }
}
