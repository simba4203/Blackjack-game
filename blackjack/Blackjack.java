
import java.util.*;

public class Blackjack {
    static Random rand = new Random ();

    public static int getCard()
    {
        int Card = rand.nextInt(13)+ 1;

        if (Card >10) return 10 ;  // J, Q, K = 10
        if (Card == 1) return 11;  // Ace = 11 (simple)
        return Card;

    }
    //calculate the total formula 
    public static int getTotal(ArrayList<Integer>hand){
        int total = 0;
        for (int card : hand) {
            total += card;
        }
        return total;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer>player = new ArrayList<>();
        ArrayList<Integer>Dealer = new ArrayList<>(); 
        

        //Inital cards 
        player.add(getCard());
        player.add(getCard());
        Dealer.add(getCard());
        Dealer.add(getCard());
        
        System.out.println("welcome to blackjack there you  win and loss the dealer are you arae hand ");

        // player ki bari 
        while (true) {
            System.out.println("\nYour card:" + player + "Total:" + getTotal(player));
            System.out.println("Dealer shows: " + Dealer.get(0));
            if 
            (getTotal(player)>21) {
                System.out.println("bhai tu hargya  ");
                return;   
        }
          System.out.println("Hit or Stand (h/s): ");
        char choice = sc.next().charAt(0);

        if (choice == 'h') {
            player.add(getCard());
        }else{
            break;
        }

    }
    //inder dealer ki bari ka code type karna hai 
    while (getTotal(Dealer) < 17) {
        Dealer.add(getCard());

        
    }
    int playerTotal = getTotal(player);
    int Dealertotal = getTotal(Dealer);

    System.out.println("\ndealer card;" + Dealer + "total:" + Dealertotal );
    // result
    if (Dealertotal >21 || playerTotal > Dealertotal){

        System.out.println("jitgoi tu");

    }else if (playerTotal == Dealertotal){
        System.out.println("Its a draw match ");
    } else { System.out.println( "tu har gya bhai" );

    }sc.close();

}

}
