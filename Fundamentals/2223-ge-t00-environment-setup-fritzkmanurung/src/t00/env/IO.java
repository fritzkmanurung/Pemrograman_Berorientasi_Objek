package t00.env;

/**
 * @author 12S21014 Fritz Kevin Manurung
 * 
 */
import java.util.Scanner;
public class IO {
    public static void main(String[] _args) {
        Scanner input=new Scanner(System.in);
        String NIM;
        String nama;
        NIM=input.nextLine();
        nama=input.nextLine();

        System.out.println(NIM+"|"+nama+"\n");
    }
}
