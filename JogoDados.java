import java.util.Random;

public abstract class JogoDados implements Estatistica{
    private int nDados;
    private String nomeJogo;
    private float saldo;
    private Dado[] dados;

    public JogoDados(int nDados, String nomeJogo, float saldo, Dado[] dados){
        this.nDados = nDados;
        this.nomeJogo = nomeJogo;
        this.saldo = saldo;
        this.dados = dados;
    }

    public void roll(){ //faz a rolagem do dado
        Random x = new Random();
        this.sideUp = x.nextInt(6) + 1;
    }
    
    @Override
    public int[] somarFacesSorteadas(Dado[] dados) {
        int i = 0;
        int[] cont;
        do {
            cont[] += dados[i].getSideUp();
            i++;
        } while (i != 2);
        return cont;//retorna a pontuação 
    }
}
