import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.*;

public class BlackjackUI {

    JFrame frame;
    
    //panel 
    JPanel homePanel,gamePanel,playerPanel,dealerPanel;
    

    //labels 
    JLabel resultLabel;

    // Button 
    JButton hitBtn, standBtn, doubleBtn, restartBtn, startBtn, exitBtn, homeBtn;

    ArrayList<Integer> player = new ArrayList<>();
    ArrayList<Integer> dealer = new ArrayList<>();
    Random rand =  new Random();

    // Grenerate the cards 
    int getCard(){
        return rand.nextInt(13)+1;

    }

    // display cards 

    ImageIcon getCardImage(int card){
       ImageIcon icon = new ImageIcon("cards/"+ card + ".png");
       Image img = icon.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
       return new ImageIcon(img);
    }
    

    // display full hand 
    
    int getTotal(ArrayList<Integer> hand){
        int total = 0, ace =0;
        for(int c : hand ){
            if (c>10) total +=10;
            else if (c== 1) {
                total +=11;
                ace++;
                
            }else total +=c;
        }
        while (total > 21 && ace > 0) {
            total -= 10;
            ace--;

        }
        return total;
    }
    
    //start gaem 

    void startGame(){
        player.clear();
        dealer.clear();

        player.add(getCard());
        player.add(getCard());
        dealer.add(getCard());
        dealer.add(getCard());

        resultLabel.setText("Game start ");
        enableButtons();
        updateUI();
        
        if(getTotal(player) == 21){
            resultLabel.setText("BlackJack you are win ");
            showFinal();
            disableButtons();
        }
    }

    
    void updateUI(){
        playerPanel.removeAll();
        dealerPanel.removeAll();

        // player cards 
        for(int c : player){
            playerPanel.add(new JLabel(getCardImage(c)));

        }

        //Dealer cards
        dealerPanel.add(new JLabel(getCardImage(dealer.get(0))));
        dealerPanel.add(new JLabel(new ImageIcon("card/back.png")));

        playerPanel.revalidate();
        dealerPanel.revalidate();
    }

    void showGame(){
        frame.setContentPane(gamePanel);
        frame.revalidate();
        startGame();;
    }

    void showFinal(){
        dealerPanel.removeAll();
        for(int c : dealer){
            dealerPanel.add(new JLabel(getCardImage(c)));
        }
        dealerPanel.revalidate();
    }
    void disableButtons() {
        hitBtn.setEnabled(false);
        standBtn.setEnabled(false);
        doubleBtn.setEnabled(false);
    }


    void enableButtons(){
        hitBtn.setEnabled(true);
        standBtn.setEnabled(true);
        doubleBtn.setEnabled(false);
    }



    
/// dealer logic
    void dealerTurn(){
        while (getTotal(dealer) < 17) {
            dealer.add(getCard());
        }

        showFinal();

        int p = getTotal(player);
        int d = getTotal(dealer);

        if(d > 21 || p > d){
            resultLabel.setText("jitgoy "+ " you win ");
        }else if (p == d) {
            resultLabel.setText("draw" + " no one win ");
            
        }else{
            resultLabel.setText("Dealer win ");
        }
        disableButtons();
    }

    //swtich to home 
    void showHome(){
        frame.setContentPane(homePanel);
        frame.revalidate();
        
    }
    public BlackjackUI(){
        frame = new JFrame(" blackjack casion");
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //home panel 

        homePanel = new JPanel(new GridLayout(3,1));

        JLabel title =  new JLabel("welcome to blackjack casion ", SwingConstants.CENTER);
        startBtn =  new JButton("START");
        exitBtn =  new JButton(" EXIT");
        
        homePanel.add(title);
        homePanel.add(startBtn);
        homePanel.add(exitBtn);

        /// game panel 
        /// 
         
        gamePanel =  new JPanel(new GridLayout(5,1));
        
        
        playerPanel = new JPanel();
        dealerPanel =  new JPanel();
        resultLabel =  new JLabel("welcome !", SwingConstants.CENTER);

        hitBtn = new JButton(" HIT ");
        standBtn =  new JButton(" STAND");
        doubleBtn =  new JButton(" DOUBLE ");
        restartBtn = new JButton(" RESTART");
        homeBtn =  new JButton(" HOME");

        JPanel buttonPanel =  new JPanel();
        buttonPanel.add(hitBtn);
        buttonPanel.add(standBtn);
        buttonPanel.add(doubleBtn);


        JPanel buttomPanel =  new JPanel();
        buttomPanel.add(restartBtn);
        buttomPanel.add(homeBtn);


        gamePanel.add(playerPanel);
        gamePanel.add(dealerPanel);
        gamePanel.add(buttonPanel);
        gamePanel.add(resultLabel);
        gamePanel.add(buttomPanel);
       
        //botton action 

        startBtn.addActionListener(e -> showGame());
        exitBtn.addActionListener(e -> System.exit(0));


        hitBtn.addActionListener(e ->{
            player.add(getCard());
            updateUI();
            if(getTotal(player)>21){
                resultLabel.setText("Bust ! you Loss This Round ");
                showFinal();;
                disableButtons();
            }
        });

       startBtn.addActionListener(e -> showGame());
       standBtn.addActionListener(e -> dealerTurn());
       restartBtn.addActionListener(e -> startGame());
       homeBtn.addActionListener(e -> showHome()); 

        //start with home  
        frame.setContentPane(homePanel);
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        new BlackjackUI();
    }

}