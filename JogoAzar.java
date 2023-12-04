import java.util.Scanner;
// Nessa classe usamos herança e herança multipla

public class JogoAzar extends JogoDados{
    private Dado[] dados;

    public JogoAzar(double valorAposta){
        super(2, "Jogo Azar", valorAposta); //HERANÇA
        
        dados = new Dado[2];


        // Inicialize cada elemento do array dados
        for (int i = 0; i < 2; i++) {
            this.dados[i] = new Dado(); // Ou use o construtor apropriado de Dado, se existir.
        }
    }

    public void executarRegrasJogo(Jogador player, int j){ // metodo para executar as regras e jogar o jogo azar
        Scanner teclado= new Scanner(System.in);
        int soma=0;
        double novoSaldo = 0;

        double valorAposta;
        do{
            // Verifica se o valor da aposta é maior que o saldo do jogador
            if(getValorAposta()>player.getSaldo()){
                System.out.println("Saldo insuficiente! Aposte outro valor");
                valorAposta = teclado.nextDouble();
                player.getJogoDados(player.getJogadasRealizadas()).setValorAposta(valorAposta); 
            }
            
        }while(getValorAposta()>player.getSaldo());
        System.out.println("Saldo "+player.getSaldo());
        System.out.println("Valor aposta "+ getValorAposta());

        // Rola os dados e calcula a soma das faces
        player.getJogoDados(player.getJogadasRealizadas()).rolarDados(2);

        System.out.println("1º Lançamento");

        soma = dados[0].getSideUp()+dados[1].getSideUp();
        System.out.printf(toString()+" = %d\n",soma); //imprimir as faces que sairam


        if (soma == 7 || soma == 11) { // Jogador ganha
            System.out.println("Jogador ganhou! [encerra jogada]");
            player.getJogoDados(player.getJogadasRealizadas()).setArmazenarResultados(j, 1, player.getJogadasRealizadas());//seta com 1 quando player ganha o jogo
            System.out.println("...");
            System.out.printf("Seu saldo era de R$ %.2f\n", player.getSaldo());
            novoSaldo = player.getSaldo() + getValorAposta(); // adicionar ao saldo o valor apostado
            player.setSaldo(novoSaldo); //define o novo saldo ao jogador
            System.out.printf("Seu saldo atual é de R$ %.2f\n", player.getSaldo());
            System.out.print("\n");
        } else if (soma == 2 || soma == 3 || soma == 12) {// Jogador perde
            System.out.println("Jogador perdeu! [encerra jogada]");
            player.getJogoDados(player.getJogadasRealizadas()).setArmazenarResultados(j, -1, player.getJogadasRealizadas());//seta com -1 
            System.out.printf("Seu saldo era de R$ %.2f\n", player.getSaldo());
            novoSaldo = player.getSaldo() - getValorAposta();// retirar do saldo o valor apostado
            player.setSaldo(novoSaldo);//define o novo saldo ao jogador
            System.out.printf("Seu saldo atual é de R$ %.2f\n", player.getSaldo());
            System.out.print("\n");
        } else { // se ele nao ganhou nem perdeu no primeiro lançamento ele tem que buscar um numero
            System.out.printf("Número a ser buscado: %d\n", soma);

            int novaSoma=0;
            int i = 2;
            do {
                // Rolagem dos dados para encontrar o número a ser buscado
                player.getJogoDados(player.getJogadasRealizadas()).rolarDados(2);

                novaSoma = dados[0].getSideUp()+dados[1].getSideUp();

                System.out.printf("%dº Lançamento\n",i);
                System.out.printf(toString()+" = %d\n",novaSoma);  //imprimir as faces que sairam

                if (novaSoma == soma) { // Jogador ganha se o numero retirado na primeira rodada for igual ao novo
                    System.out.println("Jogador ganhou! [encerra jogada]");
                    player.getJogoDados(player.getJogadasRealizadas()).setArmazenarResultados(j, 1, player.getJogadasRealizadas());//seta com 1 se ganha
                    System.out.println("...");
                    System.out.printf("Seu saldo era de R$ %.2f\n", player.getSaldo());
                    novoSaldo = player.getSaldo() + getValorAposta();// adicionar ao saldo o valor apostado
                    player.setSaldo(novoSaldo);//define o novo saldo ao jogador
                    System.out.printf("Seu saldo atual é de R$ %.2f\n", player.getSaldo());
                    System.out.print("\n");
                    break;
                } else if (novaSoma == 2 || novaSoma == 3 || novaSoma == 12) { // Jogador perde
                    System.out.println("Jogador perdeu! [encerra jogada]");
                    player.getJogoDados(player.getJogadasRealizadas()).setArmazenarResultados(j, -1, player.getJogadasRealizadas());//seta com -1 se perde
                    System.out.printf("Seu saldo era de R$ %.2f\n", player.getSaldo());
                    novoSaldo = player.getSaldo() - getValorAposta();// retirar do saldo o valor apostado
                    player.setSaldo(novoSaldo);//define o novo saldo ao jogador
                    System.out.printf("Seu saldo atual é de R$ %.2f\n", player.getSaldo());
                    System.out.print("\n");
                    break;
                }
                i++;
            } while (novaSoma != soma || (novaSoma == 2 || novaSoma == 3 || novaSoma == 12));
        }
    }


    //SOBRESCRITA DA HERANÇA
    @Override
    public void rolarDados(int nDados) { // resultados dos 2 dados
        for (int i = 0; i < nDados; i++) {
            if (this.dados[i] == null) {
                this.dados[i] = new Dado(); // ou use o construtor apropriado de Dado, se existir
            }
            this.dados[i].roll(numFaces);
        }
        int[] qtd = somarFacesSorteadas(dados); 
        setCont(qtd);
    }

    public String toString() {    //printar as faces roladas
        return dados[0].getSideUp() + " + " + dados[1].getSideUp();
    }
    
    
}
