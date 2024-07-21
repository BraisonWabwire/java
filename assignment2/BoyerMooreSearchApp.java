import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoyerMooreSearchApp {
    private static final String[] STATES = {
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", 
            "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", 
            "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", 
            "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", 
            "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", 
            "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", 
            "Wisconsin", "Wyoming"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Display the text");
            System.out.println("2. Search");
            System.out.println("3. Exit program");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    displayText();
                    break;
                case 2:
                    System.out.print("Enter the pattern to search: ");
                    String pattern = scanner.nextLine();
                    List<Integer> matches = boyerMooreSearch(STATES, pattern);
                    System.out.println("Pattern found at indices: " + matches);
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static void displayText() {
        System.out.println("Names of 50 states in the United States:");
        for (String state : STATES) {
            System.out.println(state);
        }
    }

    private static List<Integer> boyerMooreSearch(String[] text, String pattern) {
        List<Integer> result = new ArrayList<>();
        int[] badCharTable = buildBadCharTable(pattern);

        for (int i = 0; i < text.length; i++) {
            String str = text[i];
            int m = pattern.length();
            int n = str.length();
            int s = 0;

            while (s <= (n - m)) {
                int j = m - 1;

                while (j >= 0 && pattern.charAt(j) == str.charAt(s + j)) {
                    j--;
                }

                if (j < 0) {
                    result.add(i);
                    s += (s + m < n) ? m - badCharTable[str.charAt(s + m)] : 1;
                } else {
                    s += Math.max(1, j - badCharTable[str.charAt(s + j)]);
                }
            }
        }

        return result;
    }

    private static int[] buildBadCharTable(String pattern) {
        int[] table = new int[256];
        for (int i = 0; i < 256; i++) {
            table[i] = -1;
        }
        for (int i = 0; i < pattern.length(); i++) {
            table[pattern.charAt(i)] = i;
        }
        return table;
    }
}

