import java.util.Random;

public class CoinFlip {
    public boolean toCenter(){
        Random random = new Random();
        double randValue = random.nextDouble();
        if(randValue>=0.5) return true;
        else return false;
    }

    public String toCenterS1(){
        Random random = new Random();
        double randValue = random.nextDouble();
        if(randValue<=0.33){
            return "К";
        }else if(randValue>0.33 && randValue<=0.66){
            return "От";
        }else{
            return "Остаться";
        }
    }
}
