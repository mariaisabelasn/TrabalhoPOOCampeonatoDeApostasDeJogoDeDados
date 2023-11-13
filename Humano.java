public class Humano implements JogarComoHumano{
    private String cpf;
    private String agencia;
    private String conta;
    private int numeroBanco;

    public Humano(String cpf, String agencia, String conta,  int numeroBanco){
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
