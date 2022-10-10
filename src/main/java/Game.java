import java.util.ArrayList;
import java.util.List;

public class Game {
    CoinFlip coinFlip = new CoinFlip();
    private int steps;
    private int games;
    private double stepCounter = 0;
    public List<Boolean> booleans = List.of(true, true,true,true,true,
            false, false, false, false, false, false,
            true,true,
            false,false,false,false,
            true,true,true,true,
            false,
            true,
            false,false);
    public Game(int steps, int games){
        this.games = games;
        this.steps = steps;
    }
    Circle circle = Circle.S0;
    public boolean gameModeration(){
        for(int i = 0; i<steps; i++){
            switch (circle){
                case S0:
                    circle = Circle.S1;
                    break;

                case S1:
                    String choice = coinFlip.toCenterS1();
                    switch (choice){
                        case "От":
                            if(booleans.get(i) == true) circle = Circle.S2;
                            break;
                        case "К":
                            if(booleans.get(i) == true) circle = Circle.S0;
                            else circle = Circle.S2;
                            break;
                        case "Остаться":
                            if(booleans.get(i) == false) circle = Circle.S1;
                            break;
                    }
                    break;
                case S2:
                    boolean toCenterS2 = coinFlip.toCenter();
                    if(toCenterS2){
                        if(booleans.get(i)) circle = Circle.S1;
                        else circle  = Circle.S3;
                    }else{
                        if(booleans.get(i)) circle = Circle.S3;
                        else circle  = Circle.S1;
                    }
                    break;
                case S3:
                    boolean toCenterS3 = coinFlip.toCenter();
                    if(toCenterS3){
                        if(booleans.get(i)) circle = Circle.S2;
                        else circle  = Circle.S4;
                    }else{
                        if(booleans.get(i)) circle = Circle.S4;
                        else circle  = Circle.S2;
                    }
                    break;
                case S4:
                    boolean toCenterS4 = coinFlip.toCenter();
                    if(toCenterS4){
                        if(booleans.get(i)) circle = Circle.S3;
                        else { stepCounter = ++i; return true;}
                    }else{
                        if(booleans.get(i)){ stepCounter = ++i; return true;}
                        else circle  = Circle.S3;
                    }
                    break;

            }
        }
        stepCounter = steps;
        return false;
    }
    public void results(){
        List<Boolean> games_results = new ArrayList<Boolean>();
        List<Double> games_steps = new ArrayList<Double>();
        double d = 0;
        for(int i = 0; i<games;i++){
            games_results.add(this.gameModeration());
            games_steps.add(stepCounter);
            stepCounter = 0;
        }
        for (Double dd:games_steps) {
            d+=dd;
        }
        System.out.println("Среднее кол-во шагов - "+d/games);
        int robot_wins = 0;
        for (Boolean bool:games_results) {
            if(bool) robot_wins++;
        }
        int other_wins = games-robot_wins;
        System.out.println("Алгоритм выиграл " + robot_wins + " раз.");
        System.out.println("Ведущий(случайный выбор) выиграл " + other_wins + " раз.");

    }



}
enum Circle{
    S0,
    S1,
    S2,
    S3,
    S4,
    S5


}
