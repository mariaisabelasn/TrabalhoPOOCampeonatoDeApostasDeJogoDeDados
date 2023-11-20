public class JogoAzar extends JogoDados{
    private Dado[] dados = new Dado[5];
    private float valorAposta;

    public JogoAzar(int nDados, String nomeJogo, float saldo, Dado[] dados, float valorAposta){
        super(nDados, nomeJogo, saldo, dados);
        this.valorAposta = valorAposta;
    }

    public void executarRegrasJogo(){
        
    }

    public void rolarDados() { // resultados dos 2 dados
        for (int i = 0; i < 2; i++) {
            this.dados[i].roll();
        }
    }

    public String toString() { //transforma o array de dados em uma string
        String result = "Valores obtidos: ";
    
        for (int i = 0; i < 5; i++) {
            result += dados[i].toString() ;
            if (i < 5 - 1) {
                result += "-"; // Adiciona um traço entre os elementos, exceto no último
            }
        }
        System.out.println("%d e %d = %d", dados[i].getSideUp);
        if(somarFacesSorteadas(dados)==7||somarFacesSorteadas(dados)==11){
            System.out.println("Jogador ganhou!");
        }
        return result;
    }
}
