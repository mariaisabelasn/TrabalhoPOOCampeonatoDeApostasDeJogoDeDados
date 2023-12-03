import java.util.Scanner;
public class JogoAzar extends JogoDados{
    private Dado[] dados;
    private transient Scanner teclado;
    //private Jogador[] players; //verificar

    public JogoAzar(double valorAposta){
        super(2, "Jogo Azar", valorAposta);
        
        teclado = new Scanner(System.in);// scanf do java
        dados = new Dado[2];


        // Inicialize cada elemento do array dados
        for (int i = 0; i < 2; i++) {
            this.dados[i] = new Dado(); // Ou use o construtor apropriado de Dado, se existir.
        }
    }

    public void executarRegrasJogo( Jogador player){ // metodo para executar as regras e jogar o jogo azar
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
        int qtdFaces[] = somarFacesSorteadas(dados);

        System.out.println("1º Lançamento");

        soma = dados[0].getSideUp()+dados[1].getSideUp();
        System.out.printf(toString()+" = %d\n",soma); //imprimir as faces que sairam


        if (soma == 7 || soma == 11) { // Jogador ganha
            System.out.println("Jogador ganhou! [encerra jogada]");
            System.out.println("...");
            System.out.printf("Seu saldo era de R$ %.2f\n", player.getSaldo());
            novoSaldo = player.getSaldo() + getValorAposta(); // adicionar ao saldo o valor apostado
            player.setSaldo(novoSaldo); //define o novo saldo ao jogador
            System.out.printf("Seu saldo atual é de R$ %.2f\n", player.getSaldo());
            System.out.print("\n");
        } else if (soma == 2 || soma == 3 || soma == 12) {// Jogador perde
            System.out.println("Jogador perdeu! [encerra jogada]");
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
                qtdFaces = somarFacesSorteadas(dados);

                novaSoma = dados[0].getSideUp()+dados[1].getSideUp();

                System.out.printf("%dº Lançamento\n",i);
                System.out.printf(toString()+" = %d\n",novaSoma);  //imprimir as faces que sairam

                if (novaSoma == soma) { // Jogador ganha se o numero retirado na primeira rodada for igual ao novo
                    System.out.println("Jogador ganhou! [encerra jogada]");
                    System.out.println("...");
                    System.out.printf("Seu saldo era de R$ %.2f\n", player.getSaldo());
                    novoSaldo = player.getSaldo() + getValorAposta();// adicionar ao saldo o valor apostado
                    player.setSaldo(novoSaldo);//define o novo saldo ao jogador
                    System.out.printf("Seu saldo atual é de R$ %.2f\n", player.getSaldo());
                    System.out.print("\n");
                    break;
                } else if (novaSoma == 2 || novaSoma == 3 || novaSoma == 12) { // Jogador perde
                    System.out.println("Jogador perdeu! [encerra jogada]");
                    System.out.printf("Seu saldo era de R$ %.2f\n", player.getSaldo());
                    novoSaldo = player.getSaldo() - getValorAposta();// retirar do saldo o valor apostado
                    player.setSaldo(novoSaldo);//define o novo saldo ao jogador
                    System.out.printf("Seu saldo atual é de R$ %.2f\n", player.getSaldo());
                    System.out.print("\n");
                    break;
                }
                i++;
                System.out.println("1: "+qtdFaces[0] +" 2: "+qtdFaces[1]+" 3: "+qtdFaces[2] +" 4: "+qtdFaces[3]+" 5: "+qtdFaces[4] +" 6: "+qtdFaces[5]);
            } while (novaSoma != soma || (novaSoma == 2 || novaSoma == 3 || novaSoma == 12));
        }
    }



    @Override
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
