package extdotcomgame;

import java.util.ArrayList;

public class HeavyDotCom extends DotCom {

    public int size() { return 3;}

    public void setLocationCells(ArrayList<String> locs) {

        if (locs == null) return;

        locationCells = (ArrayList<String>) locs.clone();
        locationCells.addAll(locs);    // addAll: 다른 배열에 있는 내용을 그대로 가져다 현재 배열 리스트 뒤에다 추가
    }

    public ArrayList<String> getState(){
        ArrayList<String> arrList1 = new ArrayList<String>();

        for(int i = 0; i < locationCells.size() ; i++){
            if(!arrList1.contains(locationCells.get(i)))
                arrList1.add(locationCells.get(i));
        }
        return (ArrayList<String>)arrList1.clone();
    }

}
