import java.util.Scanner;
public class JogoAzar extends JogoDados{
    private Dado[] dados;
    private transient Scanner teclado;
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

    public boolean executarRegrasJogo( Jogador player){
        int soma=0;
        double novoSaldo = 0;

        double valorAposta;
        do{
            if(getValorAposta()>player.getSaldo()){
                System.out.println("Saldo insuficiente! Aposte outro valor");
                valorAposta = teclado.nextDouble();
                player.getJogoDados(player.getJogadasRealizadas()).setValorAposta(valorAposta); 
            }
            
        }while(getValorAposta()>player.getSaldo());
        System.out.println("Saldo "+player.getSaldo());
        System.out.println("Valor aposta "+ getValorAposta());

        player.getJogoDados(player.getJogadasRealizadas()).rolarDados(2);
        int qtdFaces[] = somarFacesSorteadas(dados);

        System.out.println("1º Lançamento");

        soma = dados[0].getSideUp()+dados[1].getSideUp();
        System.out.printf(toString()+" = %d\n",soma); 


        if (soma == 7 || soma == 11) {
            System.out.println("Jogador ganhou! [encerra jogada]");
            System.out.println("...");
            System.out.printf("Seu saldo era de R$ %.2f\n", player.getSaldo());
            novoSaldo = player.getSaldo() + getValorAposta();
            player.setSaldo(novoSaldo);
            System.out.printf("Seu saldo atual é de R$ %.2f\n", player.getSaldo());
            System.out.print("\n");
            return true;

        } else if (soma == 2 || soma == 3 || soma == 12) {
            System.out.println("Jogador perdeu! [encerra jogada]");
            System.out.printf("Seu saldo era de R$ %.2f\n", player.getSaldo());
            novoSaldo = player.getSaldo() - getValorAposta();
            player.setSaldo(novoSaldo);
            System.out.printf("Seu saldo atual é de R$ %.2f\n", player.getSaldo());
            System.out.print("\n");
            return false;
        } else {
            System.out.printf("Número a ser buscado: %d\n", soma);

            int novaSoma=0;
            int i = 2;
            do {
                player.getJogoDados(player.getJogadasRealizadas()).rolarDados(2);
                qtdFaces = somarFacesSorteadas(dados);

                novaSoma = dados[0].getSideUp()+dados[1].getSideUp();

                System.out.printf("%dº Lançamento\n",i);
                System.out.printf(toString()+" = %d\n",novaSoma); 

                if (novaSoma == soma) {
                    System.out.println("Jogador ganhou! [encerra jogada]");
                    System.out.println("...");
                    System.out.printf("Seu saldo era de R$ %.2f\n", player.getSaldo());
                    novoSaldo = player.getSaldo() + getValorAposta();
                    player.setSaldo(novoSaldo);
                    System.out.printf("Seu saldo atual é de R$ %.2f\n", player.getSaldo());
                    System.out.print("\n");
                    return true;
                } else if (novaSoma == 2 || novaSoma == 3 || novaSoma == 12) {
                    System.out.println("Jogador perdeu! [encerra jogada]");
                    System.out.printf("Seu saldo era de R$ %.2f\n", player.getSaldo());
                    novoSaldo = player.getSaldo() - getValorAposta();
                    player.setSaldo(novoSaldo);
                    System.out.printf("Seu saldo atual é de R$ %.2f\n", player.getSaldo());
                    System.out.print("\n");
                    return false;
                }
                i++;
                    System.out.println("1: "+qtdFaces[0] +" 2: "+qtdFaces[1]+" 3: "+qtdFaces[2] +" 4: "+qtdFaces[3]+" 5: "+qtdFaces[4] +" 6: "+qtdFaces[5]);
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
