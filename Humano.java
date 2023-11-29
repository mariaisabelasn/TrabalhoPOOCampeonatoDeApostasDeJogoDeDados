import java.util.Scanner;
public class Humano extends Jogador implements JogarComoHumano{
    private String cpf;
    private String agencia;
    private String conta;
    private int numeroBanco;
    private Jogador[] players; // referência para os jogadores
    private Scanner teclado;

    public Humano(String nome, String tipoJogador, String cpf,double saldo){
        super(nome, tipoJogador, saldo);
        this.cpf = cpf;
        this.agencia = "2567-8";
        this.conta = "2564987";
        this.numeroBanco = 007; 
    }

    @Override
    public int escolherJogada() { //vai escolher a jogada do jogo general
        int opcao=0;
             do {
                System.out.println("Para qual jogada deseja marcar: [1-13] " + super.getNome() + "?");
                System.out.println("1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)");
                super.mostrarJogadasExecutadas();

                while(opcao<=0|| opcao>13 || super.getJogo().getJogadas(opcao-1)!=-1){//caso o usuário tente escolher uma opcao inexistente ou alguma jogada já feita de novo
                    opcao = teclado.nextInt();
                    teclado.nextLine(); // Limpar o buffer de entrada após a leitura do inteiro
                   
                    if(opcao<=0||opcao>13 || super.getJogo().getJogadas(opcao-1)!=-1){
                         System.out.println("Jogada inválida, escolha outra.");
                    }
                    }

                     if (super.getJogo().getJogadas(opcao-1)==-1) { //se a jogada ainda nao tiver sido feita
                        super.getJogo().setJogadas(opcao-1, super.getJogo().pontuarJogada(opcao));
                    } 
                } while (super.getJogo().getJogadas(opcao-1)==-1);
      return 0;
    }

    @Override
    public int escolherJogo() {
          int opcao = 0;
          if(super.saldo <=0.0){
            System.out.println("Saldo insufíciente");
            return 0;
          }
          if(super.jogosRealizados>=10){
            System.out.println("Você atingiu o limite máximo de jogatina no Cassino M&M, volte amanhã!");
            return 0;
          }
        do{
            System.out.println("Qual jogo deseja jogar?");//DEFINE QUAL JOGO É
            System.out.println("1 - Jogo General");
            System.out.println("2 - Jogo Azar");
            opcao = teclado.nextInt();
            teclado.nextLine();

            if(opcao==1){
                    //função que inicia general
            }
            else if(opcao==2){
                //função que inicia o jogo de azr
            }
            else{
                System.out.println("Opção inválida!");
                opcao = 0;
            }
        }while(opcao==1||opcao==2);
        return 0;
    }
}
