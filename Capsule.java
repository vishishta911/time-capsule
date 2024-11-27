import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Capsule {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your message: ");
        String msg = sc.nextLine();
        
        System.out.print("Enter the date and time to reveal the message (yyyy-MM-dd HH:mm): ");
        String datentime = sc.nextLine();
        
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date reveal = dateFormat.parse(datentime);
            long currentTime = new Date().getTime();
            long revealTime = reveal.getTime();
            long difference = revealTime - currentTime;
            long days = difference / (1000 * 60 * 60 * 24);
            long hours = (difference / (1000 * 60 * 60)) % 24;
            long minutes = (difference / (1000 * 60)) % 60;

            if (difference > 0) { 
                Timer t = new Timer();
                t.schedule(new TimerTask() {
                    public void run() {
                        System.out.println("Your message: " + msg);
                        t.cancel();
                    }
                }, difference); 
                System.out.println("Your message is scheduled to be revealed on " + reveal);
                System.out.println("________________________________________________");
            } else {
                System.out.println("The specified time is in the past. Please enter a future date.");
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm");
        } finally {
            sc.close();
        }
    }
}
