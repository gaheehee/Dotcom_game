package extdotcomgame;

import dotcomobserver.DamageRate;
import dotcomobserver.SurvivingCells;

import java.util.*;

public class DotComBust {

    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;
    int hitNum = 0;

    /*
     * DotCom Object 생성 후 보드 위에 배치하는 메소드
     */
    public void setUpGame(){

        ShortDotCom one = new ShortDotCom();
        LongDotCom two = new LongDotCom();
        HeavyDotCom three = new HeavyDotCom();

        one.attach(new SurvivingCells(one)); //SurvivingCells 만들고 그것이 관찰할 오브젝트 넣어줌
        two.attach(new SurvivingCells(two));
        three.attach(new SurvivingCells(three));

        one.attach(new DamageRate(one, one.size()));
        two.attach(new DamageRate(two,two.size()));
        three.attach(new DamageRate(three, three.size()));

        // setName이 에러가 나지 않는 이유: ShortDotCom이 DotCom에 상속되어있어서
        one.setName("Pets.com");
        two.setName("eToys.com");
        three.setName("Go2.com");

        // dotComsList에는 DotCom타입의 array만 넣을 수 있음
        // add가 실행이 되는 이유: ShortDotCom이 DotCom에 상속되어있어서
        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);

        ArrayList<String> newLocation;
        newLocation = helper.placeDotCom(one.size());
        one.setLocationCells(newLocation);
        newLocation = helper.placeDotCom(two.size());
        two.setLocationCells(newLocation);
        newLocation = helper.placeDotCom(three.size());
        three.setLocationCells(newLocation);
    }


    /*
     * 전체 게임 실행 메소드
     */
    public void startPlaying() {

        // isEmpty가 아니면 while loop 돔
        while (!dotComsList.isEmpty()) {
            String userGuess = helper.getUserInputRandom("Enter a guess");    // userInput을 받음
            this.checkUserGuess(userGuess);    // userguess를 가지고 맞았는지 틀렸는지 체크
        }
        this.finishGame();
    }


    /*
     * User가 입력한 String을 받아서 User가 맞추었는지 체크하는 메소드
     */
    private void checkUserGuess(String userGuess) {

        numOfGuesses++;
        String result ="";

        for (int x = 0; x < dotComsList.size(); x++) {
            result = dotComsList.get(x).checkYourSelf(userGuess);

            if (result.equals("hit")) {
                result += " " + dotComsList.get(x).getName();
                break;

            } else if (result.equals("kill")) {
                result += " " + dotComsList.get(x).getName();
                dotComsList.remove(x);
                break;
            }
        }

        if (!result.equals("miss"))
            System.out.println(result);
    }


    /*
     *  게임이 끝났음을 알리는 메세지를 출력하는 메소드
     */
    private void finishGame() {

        System.out.println("All Dot Coms are dead!");

        if (numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses + " guesses.");

        } else {
            System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
        }
    }
}