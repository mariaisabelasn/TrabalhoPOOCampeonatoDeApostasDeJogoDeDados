import java.io.Serializable;
import java.util.Random;

public class Dado implements Serializable{
    private int sideUp; //parte superior

    //construtor
    public Dado(){
        sideUp = 1;
    }

    public int getSideUp(){//pega a parte virada pra cima
        return this.sideUp;
    }

    public void setSideUp(int x){
        this.sideUp = x;
    }

    public void roll(int numFaces){ //faz a rolagem do dado
        Random x = new Random();
        this.sideUp = x.nextInt(numFaces) + 1;
    }

    public String toString() { //transforma o array de dados em uma string
        return "" + getSideUp();
    }
    
}