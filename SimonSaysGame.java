import java.util.*;

public class SimonSaysGame {
    private static final char[] COMMANDS = {'A', 'B', 'C', 'D', 'E', 'F', 'G'}; 
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Integer> leaderboard = new ArrayList<>();
    
    public static void main(String[] args) {
        boolean playAgain;
        do {
            playAgain = playGame();
        } while (playAgain);
        tampilkanLeaderboard();
        System.out.println("Terima kasih telah bermain!");
    }
    
    private static boolean playGame() {
        ArrayList<Character> urutan = new ArrayList<>();
        boolean gameOver = false;
        int level = 1;
        int score = 0;
        
        System.out.println("\n=== SIMON SAYS GAME ===");
        System.out.println("Ingat dan ulangi urutan huruf yang diberikan!\n");
        
        while (!gameOver) {
            urutan.add(COMMANDS[random.nextInt(COMMANDS.length)]);
            System.out.println("DEBUG: huruf yang di ambil " + urutan);
            
            System.out.println("\nLevel " + level);
            tampilkanUrutan(urutan);
            System.out.println("DEBUG: huruf yang tersimpan" + urutan);
            
            System.out.println("Masukkan urutan: ");
            String input = scanner.nextLine().toUpperCase();
            System.out.println("DEBUG: inputan yang diberikan" + input);

            
            if (!cekJawaban(urutan, input)) {
                gameOver = true;
                System.out.println("\nSALAH! Permainan berakhir pada Level " + level);
                break;
            }
            
            System.out.println("Benar! Lanjut ke level berikutnya.");
            score = level * 10;
            System.out.println("DEBUG: score saat ini :" + score);

            level++;
            System.out.println("DEBUG: level selanjutnya :" + level);

        }
        
        leaderboard.add(score);
        System.out.println("DEBUG: leaderboard anda saat ini" + leaderboard);

        System.out.println("Skor akhir Anda: " + score);
        
        System.out.print("Main lagi? (y/n): ");
        return scanner.nextLine().equalsIgnoreCase("y");
    }
    
    private static void tampilkanUrutan(ArrayList<Character> urutan) {
        for (char c : urutan) {
            System.out.print(c + " ");
        }
        System.out.println("\n(Ingat urutan di atas, lalu masukkan)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    private static boolean cekJawaban(ArrayList<Character> urutan, String input) {
        if (input.length() != urutan.size()) {
            System.out.println("DEBUG: inputan anda berlebih");
            return false;
        }
        
        for (int i = 0; i < urutan.size(); i++) {
            if (input.charAt(i) != urutan.get(i)) {
                System.out.println("DEBUG: inputan ada salah");
                System.out.println("DEBUG: inputan anda : " + input);
                System.out.println("DEBUG: urutan seharusnya :" + urutan );
                return false;
            }
        }
        return true;
    }
    
    private static void tampilkanLeaderboard() {
        System.out.println("\n=== LEADERBOARD ===");
        leaderboard.sort(Collections.reverseOrder());
        for (int i = 0; i < Math.min(5, leaderboard.size()); i++) {
            System.out.println((i + 1) + ". Skor: " + leaderboard.get(i));
        }
    }
}
