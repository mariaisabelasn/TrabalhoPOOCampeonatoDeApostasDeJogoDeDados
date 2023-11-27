public class JogoAzar extends JogoDados{
    private Dado[] dados = new Dado[2];
    private float valorAposta;


    public JogoAzar(int nDados, String nomeJogo, float saldo, float valorAposta){
        super(nDados, nomeJogo, saldo);
        this.valorAposta = valorAposta;

        // Inicialize cada elemento do array dados
        for (int i = 0; i < 2; i++) {
            this.dados[i] = new Dado(); // Ou use o construtor apropriado de Dado, se existir
        }
    }

    public float getValorAposta(){
        return this.valorAposta;
    }

    public void setValorAposta(float valor){
       this.valorAposta = valor;
    }

    public void executarRegrasJogo(){
        int i;
        int soma=0;

        rolarDados(2);
        int cont[] = somarFacesSorteadas(dados);

        System.out.println("1º Lançamento");

        soma = dados[0].getSideUp()+dados[1].getSideUp();
        System.out.printf(toString()+" = %d%n",soma); 

        int pont = 0;

        if (soma == 7 || soma == 11) {
            System.out.println("Jogador ganhou! [encerra jogada]");
            System.out.println("...");
            pont = 1;
        } else if (soma == 2 || soma == 3 || soma == 12) {
            pont = 0;
            System.out.println("Jogador perdeu! [encerra jogada]");
        } else {
            System.out.printf("Número a ser buscado: %d%n", soma);

            int novaSoma=0;
            i = 2;
            do {
                rolarDados(2);
                cont = somarFacesSorteadas(dados);

                novaSoma = dados[0].getSideUp()+dados[1].getSideUp();

                System.out.printf("%dº Lançamento%n",i);
                System.out.printf(toString()+" = %d%n",novaSoma); 

                if (novaSoma == soma) {
                    System.out.println("Jogador ganhou! [encerra jogada]");
                    System.out.println("...");
                    pont = 1;
                    break;
                } else if (novaSoma == 2 || novaSoma == 3 || novaSoma == 12) {
                    System.out.println("Jogador perdeu! [encerra jogada]");
                    pont = 0;
                    break;
                }
                i++;
            } while (novaSoma != soma || (novaSoma == 2 || novaSoma == 3 || novaSoma == 12));
        }
    }


    public void rolarDados(int nDados) { // resultados dos 2 dados
        for (int i = 0; i < nDados; i++) {
            if (this.dados[i] == null) {
                this.dados[i] = new Dado(); // ou use o construtor apropriado de Dado, se existir
            }
            this.dados[i].roll(numFaces);
        }
    }

    public String toString() {    
        return dados[0].getSideUp() + " + " + dados[1].getSideUp();
    }
    public static void main(String[] args){
        JogoAzar jogoAzar = new JogoAzar( 2, null, 2,2000);
        

        jogoAzar.executarRegrasJogo();;
    }
    
    
}
