package extdotcomgame;

import dotcomobserver.Observable;

import java.util.ArrayList;

public class DotCom extends Observable {

    protected ArrayList<String> locationCells;
    protected String name;

    public void setLocationCells(ArrayList<String> loc) {
        if (loc == null) return;
        locationCells = (ArrayList<String>)loc.clone();
    }


    public void setName(String n) {
        name = n;
    }


    public String getName() {
        return name;
    }


    /*
     * user가 입력한 값을 받아서 맞추었는지 체크하는 메소드
     */
    public String checkYourSelf(String userInput) {

        String result = "miss";
        int index = locationCells.indexOf(userInput);    // indexOf: userInput와 같은 문자의 위치 찾기

        if (index >= 0) {
            locationCells.remove(index);

            if (locationCells.isEmpty()) {
                result = "kill";
                System.out.println("You sunk " + name + ".");

            } else {
                result = "hit";
            }
            this.notifyObservers();
        }
        return result;
    }

    public ArrayList<String> getState(){
        return (ArrayList<String>)locationCells.clone();
    }

}
