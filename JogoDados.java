public abstract class JogoDados implements Estatistica{
    int nDados;
    String nomeJogo;
    float saldo;
    Dado[] dados;
    int[] vstatic = new int[6];

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
