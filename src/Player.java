import java.util.*;

public class Player {
    private int playerCard;
    private int playerTotal;
    private boolean draw = true;

    private boolean isHaveAce = false;
    private boolean isOnceOver21 = false;

    public int getPlayerTotal() {
        return playerTotal;
    }

    // カードを引く & 現在の合計
    public void drawCard (Card cards) {
        String bust = "";
        String or = "";
        String great = "";
        int anotherTotal = 0;

        playerCard = cards.selectCard();

        System.out.println("【プレイヤー】");
        System.out.println("引いたカード：" + playerCard);

        // カードが10以上の場合
        if(playerCard >= 10) playerCard = 10;
        // カードがAかつ合計が一度21を超えたことがある場合
        else if(playerCard == 1 && isOnceOver21) isHaveAce = false;
        // カードがAかつ合計一度も21を超えていない場合
        else if(playerCard == 1 && !isOnceOver21) isHaveAce = true;

        // Aを1とした場合の合計
        playerTotal += playerCard;

        //現在の合計出力
        System.out.print("現在の合計　：");
        // 21になった場合
        if(playerTotal == 21 ) {
            draw = false;
            great = " !!!";
        }
        // Aがなく、合計が22以上の場合
        else if(!isHaveAce && playerTotal > 21){
            draw = false;
            bust = " (BUST)";
        }
        // Aがある場合
        else if(isHaveAce){
            // 一度21を超えたことがある場合
            if(isOnceOver21){
                // 22以上になったとき
                if(playerTotal > 21){
                    draw = false;
                    bust = " (BUST)";
                }
            }
            // 一度も21を超えていない場合
            else{
                // 21を超えた場合
                if(playerTotal + 10 > 21) isOnceOver21 = true;
                // 21の場合
                else if(playerTotal + 10 == 21){
                    draw = false;
                    playerTotal += 10;
                    great = " !!!";
                }
                // 21未満の場合
                else{
                    or = " or ";
                    anotherTotal = playerTotal + 10;
                }
            }
        }
        if(anotherTotal != 0) System.out.println(playerTotal + or + anotherTotal);
        else System.out.println(playerTotal + bust + great);
        System.out.println();
    }


    public void play (Card cards, int upCard) {
        String str;
        do{
            // ディーラーのアップカードの表示
            System.out.println("ディーラーのアップカード：" + upCard);
            // カードを引くか否かを入力
            System.out.print("カードを引きますか？(y/n)");
            Scanner scan = new Scanner(System.in);
            str = scan.next();
            // 入力に対する処理
            if(str.equals("y")){
                System.out.println("カードを引きます。");
                this.drawCard(cards);
            }else if(str.equals("n")){
                System.out.println("ディーラーと勝負します。");
                draw = false;
            }else{
                System.out.println("正しく入力してください。");
            }
        }while(draw);
    }
}
