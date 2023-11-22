public class Humano extends Jogador implements JogarComoHumano{
    private String cpf;
    private String agencia;
    private String conta;
    private int numeroBanco;

    public Humano(String nome, String tipoJogador, String cpf, String agencia, String conta,  int numeroBanco){
        super(nome, tipoJogador);
        this.cpf = cpf;
        this.agencia = agencia;
        this.conta = conta;
        this.numeroBanco = numeroBanco;
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
