import java.util.Scanner;
public class JogoAzar extends JogoDados{
    private Dado[] dados;
    private Scanner teclado;
    private Jogador[] players;

    public JogoAzar(double valorAposta){
        super(2, "Jogo Azar", valorAposta);
        
        teclado = new Scanner(System.in);// scanf do java
        dados = new Dado[2];


        // Inicialize cada elemento do array dados
        for (int i = 0; i < 2; i++) {
            this.dados[i] = new Dado(); // Ou use o construtor apropriado de Dado, se existir
        }
    }

    public boolean executarRegrasJogo(int i){
        // int i=0;
        int soma=0;
        double novoSaldo = 0;

        double valorAposta=0;
        do{
            if(getValorAposta()>players[i].getSaldo()){
                System.out.println("Saldo insuficiente! Aposte outro valor");
                valorAposta = teclado.nextFloat();
                players[i].setSaldo(valorAposta);
            }
            i++;
        }while(getValorAposta()>players[i].getSaldo());

        rolarDados(2);
        int qtdFaces[] = somarFacesSorteadas(dados);

        System.out.println("1º Lançamento");

        soma = dados[0].getSideUp()+dados[1].getSideUp();
        System.out.printf(toString()+" = %d%n",soma); 


        if (soma == 7 || soma == 11) {
            System.out.println("Jogador ganhou! [encerra jogada]");
            System.out.println("...");
            System.out.printf("Seu saldo era de R$ %.2d%n", players[i].getSaldo());
            novoSaldo = valorAposta*2;
            players[i].setSaldo(novoSaldo);
            System.out.printf("Seu saldo atual é de R$ %.2d%n", players[i].getSaldo());
            return true;
        } else if (soma == 2 || soma == 3 || soma == 12) {
            System.out.println("Jogador perdeu! [encerra jogada]");
            System.out.printf("Seu saldo era de R$ %.2d%n", players[i].getSaldo());
            novoSaldo = players[i].getSaldo() - valorAposta;
            players[i].setSaldo(novoSaldo);
            System.out.printf("Seu saldo atual é de R$ %.2d%n", players[i].getSaldo());
            return false;
        } else {
            System.out.printf("Número a ser buscado: %d%n", soma);

            int novaSoma=0;
            i = 2;
            do {
                rolarDados(2);
                qtdFaces = somarFacesSorteadas(dados);

                novaSoma = dados[0].getSideUp()+dados[1].getSideUp();

                System.out.printf("%dº Lançamento%n",i);
                System.out.printf(toString()+" = %d%n",novaSoma); 

                if (novaSoma == soma) {
                    System.out.println("Jogador ganhou! [encerra jogada]");
                    System.out.println("...");
                    System.out.printf("Seu saldo era de R$ %.2d%n", players[i].getSaldo());
                    novoSaldo = valorAposta*2;
                    players[i].setSaldo(novoSaldo);
                    System.out.printf("Seu saldo atual é de R$ %.2d%n", players[i].getSaldo());
                    return true;
                } else if (novaSoma == 2 || novaSoma == 3 || novaSoma == 12) {
                    System.out.println("Jogador perdeu! [encerra jogada]");
                    System.out.printf("Seu saldo era de R$ %.2d%n", players[i].getSaldo());
                    novoSaldo = players[i].getSaldo() - valorAposta;
                    players[i].setSaldo(novoSaldo);
                    System.out.printf("Seu saldo atual é de R$ %.2d%n", players[i].getSaldo());
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
    
    
}
