public class Humano extends Jogador implements JogarComoHumano{
    private String cpf;
    private String agencia;
    private String conta;
    private int numeroBanco;

    public Humano(String nome, String tipoJogador, String cpf){
        super(nome, tipoJogador);
        this.cpf = cpf;
        this.agencia = "2567-8";
        this.conta = "2564987";
        this.numeroBanco = 007;
    }

    @Override
    public int escolherJogada(JogoGeneral Jogo) {
        return 0;
    }

    @Override
    public int escolherJogo() {
        
        return 0;
    }
}
