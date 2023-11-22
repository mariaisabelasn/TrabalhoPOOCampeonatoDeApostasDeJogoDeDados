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
    
    // Sobrecarga
    public JogoDados(int nDados, String nomeJogo, float saldo) {
        this.nDados = nDados;
        this.nomeJogo = nomeJogo;
        this.saldo = saldo;
        this.dados = new Dado[nDados];
    }

    public String getNomeJogo(){
        return this.nomeJogo;
    }

    public float getSaldo(){
        return this.saldo;
    }

    public void setNomeJogo(String nome){
        this.nomeJogo = nome;
    }

    public void setSaldo(float saldo){
        this.saldo = saldo;
    }

    public void rolarDados(int nDados) { // resultados dos dados
        for (int i = 0; i < nDados; i++) {
            this.dados[i].roll();
        }
    }
    
    @Override
    public int[] somarFacesSorteadas(Dado[] dados) {
        int i = 0;
        int[] cont = new int[dados.length]; // Inicializa o array cont
        // Usando rolarDados
        rolarDados(dados.length); 
    
        while (i < dados.length) {
            cont[i] += dados[i].getSideUp(); // soma as faces sorteadas
            i++;
        }
    
        return cont; // Retorna a pontuação
    }
}
