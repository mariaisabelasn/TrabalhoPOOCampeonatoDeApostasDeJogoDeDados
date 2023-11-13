public class JogoDados implements Estatistica{
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
    
    @Override
    public int[] somarFacesSorteadas(Dado[] dados) {
        return null;
    }
}
