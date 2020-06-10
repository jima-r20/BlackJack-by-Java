public class Dealer {
    private int dealerCard;
    private int dealerTotal;

    private boolean isHaveAce = false;
    private boolean isOnceOver21 = false;

    public int getDealerTotal() {
        return dealerTotal;
    }

    public void drawCard (Card cards) {
        String bust = "";
        String or = "";
        String great = "";
        int anotherTotal = 0;

        dealerCard = cards.selectCard();

        System.out.println("【ディーラー】");
        System.out.println("引いたカード：" + dealerCard);

        // カードが10以上の場合
        if(dealerCard >= 10) dealerCard = 10;
            // カードがAかつ合計が一度21を超えたことがある場合
        else if(dealerCard == 1 && isOnceOver21) isHaveAce = false;
            // カードがAかつ合計一度も21を超えていない場合
        else if(dealerCard == 1 && !isOnceOver21) isHaveAce = true;

        // Aを1とした場合の合計
        dealerTotal += dealerCard;

        //現在の合計出力
        System.out.print("現在の合計　：");
        // 21になった場合
        if(dealerTotal == 21 ) great = " !!!";
        // Aがなく、合計が22以上の場合
        else if(!isHaveAce && dealerTotal > 21) bust = " (BUST)";
        // Aがある場合
        else if(isHaveAce){
            // 一度21を超えたことがある場合
            if(isOnceOver21){
                // 22以上になったとき
                if(dealerTotal > 21) bust = " (BUST)";
            }
            // 一度も21を超えていない場合
            else{
                // 21を超えた場合
                if(dealerTotal + 10 > 21) isOnceOver21 = true;
                // 21の場合
                else if(dealerTotal + 10 == 21){
                    dealerTotal += 10;
                    great = " !!!";
                }
                // 21未満の場合
                else {
                    or = " or ";
                    anotherTotal = dealerTotal + 10;
                }
            }
        }
        if(anotherTotal != 0) System.out.println(dealerTotal + or + anotherTotal);
        else System.out.println(dealerTotal + bust + great);
        System.out.println();
    }

    public void play (Card cards) {
        if(dealerTotal >= 17) return;
        while(dealerTotal < 17) {
            this.drawCard(cards);
        }
    }
}
