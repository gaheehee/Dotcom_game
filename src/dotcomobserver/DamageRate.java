package dotcomobserver;

import extdotcomgame.DotCom;

import java.util.ArrayList;

public class DamageRate implements Observer {

    private DotCom subject;
    private int fullSize;

    public DamageRate(DotCom s ,int size) {
        subject = s;
        fullSize = size;
    }

    public void update(){
        ArrayList<String> state = subject.getState();
        int i = 0;
        for (String a : state) {
            i++;
        }
        int survive = fullSize-i;

        int rate = (100 * survive / fullSize);

        System.out.println("DamageRate of " + subject.getName() + ": " + rate + "% ");
    }

}
