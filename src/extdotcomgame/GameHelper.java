package extdotcomgame;

import java.util.*;
import java.io.*;

/*
 * game board의 크기를 자유롭게 확장할 수 있도록하기 위한 클래스
 */
public class GameHelper {

    private static final String alphabet = "abcdefg";    // 행 number는 알파벳으로 표현
    private int gridLength = 7;
    private int gridSize = 49;
    private int[] grid = new int[gridSize];
    // 거기엔 이미 다른 DotCom이 들어와있음을 표시
    // Alternatively,
    // private int[][] grid = new int[gridLength][gridLength]
    // 바둑판 모양을 한 줄로 핀 것
    private int comCount = 0;


    /*
     * promp 메세지를 받아서 user에게 promp 메세지를 출력해 준 후, user에게 입력을 받는 메소드
     */
    public String getUserInput(String promp) {

        String inputLine = null;
        System.out.print(promp + " ");    // promp 메세지 출력

        // 에러(예외)가 발생할 수 있는 코드
        try {
            BufferedReader is = new BufferedReader( new InputStreamReader(System.in));
            // BufferedReader 안에 InputStreamReader을 넣어주어 user에게서 입력받음
            inputLine = is.readLine();    // readLine을 통해 한 줄 읽음

            if (inputLine.length() == 0)
                return null;
        }

        // 에러 시 수행
        catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return inputLine;
    }


    private Random generator = new Random(10);

    /*
     * 자동으로 숫자 생성하는 메소드
     * 랜덤으로 좌표를 생성하여 스트링으로 리턴
     */
    public String getUserInputRandom(String promp){

        String inputLine = null;

        // generator를 이용하여 double형의 값을 랜덤으로 생성, gridLength를 곱하여 0-6사이 값 나오게 함
        int row = (int) (generator.nextDouble()*gridLength);    // 행
        int col = (int) (generator.nextDouble()*gridLength);    // 열

        String temp = String.valueOf(alphabet.charAt(row));    //row 인덱스를 가지고 alphabet으로 바꿈
        temp = temp.concat(Integer.toString(col));
        return temp;    // 랜덤으로 생성된 좌표 String을 반환
    }


    /*
     * 몇 칸짜리의 DotCom을 받을 건 지 받아서 실제 cell의 좌표로 반환하는 메소드
     */
    public ArrayList<String> placeDotCom(int comSize) {

        ArrayList<String> alphaCells = new ArrayList<String>();    // alphaCells은 나중에 출력할 때 사용

        // DotCom이 들어갈 cell을 찾는 코드
        int[] coords = new int[comSize];
        // cell의 좌표를 잠시 보관할 int형 array
        int attempts = 0;
        boolean success = false;
        int locaton = 0;

        comCount++;

        // incr은 배가 배치되는 cell의 번호를 얼마씩 증가시킬 건지를 나타냄
        // comCount는 몇 번째 DotComd인지 나타냄
        // (comCount % 2) == 1 이면 gridLength, 아니면 1
        // 1은 가로, gridLength는 세로 => 가로, 세로 번갈아 가면서 배치
        int incr = ((comCount % 2) == 1) ? gridLength : 1;

        while (!success && attempts++ < 200) {

            int location = (int) (Math.random() * gridSize);
            int x = 0;
            success = true;

            while (success && x < comSize) {

                // grid[location] == 0이면 비어있는 cell이므로 배를 넣을 수 있음
                if (grid[location] == 0) {
                    coords[x++] = location;
                    location += incr;

                    // location이 gridSize를 벗어났는지 체크
                    if (location >= gridSize) {
                        success = false;
                    }

                } else {
                    success = false;
                }
            }
        }

        // 찾은 cell의 번호를 가지고 실제 cell의 좌표로 변환
        int x = 0;

        while (x < comSize) {

            grid[coords[x]] = 1;
            int row = (int) (coords[x] / gridLength);
            //coords에 들어있는 cell의 번호를 gridLength로 나누어주면 행의 길이가 됨 => row는 행의 길이
            int column = coords[x] % gridLength;
            String temp = String.valueOf(alphabet.charAt(row));
            // charAt은 String에서 row번 째에 있는 문자 리턴

            alphaCells.add(temp.concat(Integer.toString(column)));
            x++;
        }
        return alphaCells;
    }

}
