//ルール
//  • プレイヤーとディーラーは、カードを1枚ずつ引く。
//  • 追加で何枚引いてもOK。
//  • 2～10はその数字、絵札は10、Aは1または11（どちらでも都合の良い方でOK）と数える。
//  • 引いた札の合計が21に近いほうが勝ち。
//  • ただし、21を超えた瞬間に負け（バースト）。
//  • 引き分けあり。
//  • ディーラーは、17を下回ることはできない。
//  • 掛け金は考慮しない。

import javax.swing.*;
import java.util.*;

public class Blackjack {
    public static void main(String[] args) {
        Card cards = new Card();

        Player player = new Player();
        Dealer dealer = new Dealer();

        // ゲーム準備
        player.drawCard(cards);
        player.drawCard(cards);
        dealer.drawCard(cards);

        int playerResult = player.getPlayerTotal();
        int dealerResult = dealer.getDealerTotal();

        if(playerResult == 21){
            System.out.println("Black Jack!!!");
            System.exit(0);
        }

        // ゲーム開始
        System.out.println("ゲームを開始します。");
        // プレイヤー
        player.play(cards, dealerResult);
        playerResult = player.getPlayerTotal();

        if(playerResult > 21){
            System.out.println("プレイヤーの負けです。");
        }else{
            // ディーラー
            dealer.play(cards);
            dealerResult = dealer.getDealerTotal();

            //ゲーム結果
            System.out.println("----------------------");
            System.out.println("最終結果");
            if( (playerResult > dealerResult) && (playerResult < 22) ){
                System.out.println("プレイヤーの勝利です。");
            }else if( playerResult < 22 && dealerResult > 21 ){
                System.out.println("プレイヤーの勝利です。");
            }else if( (playerResult == dealerResult) && (playerResult < 22 && dealerResult < 22) ){
                System.out.println("引き分けです。");
            }else{
                System.out.println("プレイヤーの負けです。");
            }
        }


        System.out.println("プレイヤー：" + playerResult);
        System.out.println("ディーラー：" + dealerResult);


    }
}
