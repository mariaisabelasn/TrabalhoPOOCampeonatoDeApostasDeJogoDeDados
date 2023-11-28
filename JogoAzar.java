import java.util.Scanner;

public class JogoAzar extends JogoDados{
    private Dado[] dados;
    private Scanner teclado;

    public JogoAzar(float valorAposta){
        super(2, "Jogo Azar", valorAposta);
        
        teclado = new Scanner(System.in);// scanf do java
        dados = new Dado[2];

        // Inicialize cada elemento do array dados
        for (int i = 0; i < 2; i++) {
            this.dados[i] = new Dado(); // Ou use o construtor apropriado de Dado, se existir
        }
    }

    public boolean executarRegrasJogo(){
        int i;
        int soma=0;

        rolarDados(2);
        int cont[] = somarFacesSorteadas(dados);

        System.out.println("1º Lançamento");

        soma = dados[0].getSideUp()+dados[1].getSideUp();
        System.out.printf(toString()+" = %d%n",soma); 


        if (soma == 7 || soma == 11) {
            System.out.println("Jogador ganhou! [encerra jogada]");
            System.out.println("...");
            return true;
        } else if (soma == 2 || soma == 3 || soma == 12) {
            System.out.println("Jogador perdeu! [encerra jogada]");
            return false;
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
                    return true;
                } else if (novaSoma == 2 || novaSoma == 3 || novaSoma == 12) {
                    System.out.println("Jogador perdeu! [encerra jogada]");
                    return false;
                }
                i++;
            } while (novaSoma != soma || (novaSoma == 2 || novaSoma == 3 || novaSoma == 12));
        }
        return false;
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
        JogoAzar jogoAzar = new JogoAzar( 100);
        

        jogoAzar.executarRegrasJogo();
    }
    
    
}
