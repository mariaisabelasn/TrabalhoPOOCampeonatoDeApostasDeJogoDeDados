import java.util.Random;

public class Maquina extends Jogador implements JogarComoMaquina {
    // private Jogador players = new Jogador();
    // private JogoDados jDados = new JogoDados();
    private int vet[]=new int[13];
    private Random random =new Random();

    public Maquina(String nome, String tipoJogador, float saldo){
        super(nome, tipoJogador, saldo);
    }

    public int sorteiaJogo() { //como cada jogo é individual e nehum jogador joga com outro é necessário um método pra maquina "entrar" em um jogo
        int opcao = 0;
      if(super.getSaldo() <=0.0){
        System.out.println("Saldo insufíciente, você faliu!");
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
          opcao = random.nextInt(2)+1; //sorteia 1 ou 2 pra máquina jogar

          if(opcao==1){
            System.out.println("Jogo escolhido: General");
            return 1;// inicia general
          }
          else if(opcao==2){
            System.out.println("Jogo escolhido: Azar");
             return 2; //inicia o jogo de azar
          }
          super.jogosRealizados++;//atualiza a guantidade de jogos feitos
      }while((opcao==1||opcao==2)&& super.getSaldo()>0 && super.jogosRealizados<10);
      return 0;
  }

    @Override
    public int aplicarEstrategia() { //aplica a estratégia para o jogo general
        int opcao=0;
            int melhorPontuacao = 0;
            int melhorJogada=0;
                while(opcao<13){//basicamente vai ver para aquela rodada qual vai ser a jogada com maior pontuação
                if(super.getJogoG().getJogadas(opcao)==-1){//se já não for ocupada a jogada
                    int pontuacao = super.getJogoG().pontuarJogada(opcao+1);
                    
                    if(pontuacao>=melhorPontuacao){//serve p achar a melhor jogada mas vai acabar preenchendo todas as outras do vetor jogadas tbm
                        melhorPontuacao=pontuacao;
                        melhorJogada=opcao;
                        vet[melhorJogada] = 1;//se a jogada já tiver sido usada anteriormente é marcada como 1;
                    }
                    
                }
               
                opcao++;
            }
            
            for (int k=0; k<13; k++){
                if(super.getJogoG().getJogadas(k)!= melhorPontuacao && vet[k]!=1){
                    super.getJogoG().setJogadas(k, -1); //resolve o problema de preenchimento de outras jogadas
                }
            }  
            super.getJogoG().setJogadas(melhorJogada, melhorPontuacao);//pontua para a máquina             
            System.out.println("1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)");
            super.mostrarJogadasExecutadas();
            System.out.println("Jogada que a maquina escolheu: "+ (melhorJogada+1));//retorna a jogada feita pela maquina melhorjogada(posição do vet)+1(pra ficar o "nome" da jogada certinho)

        return 0;
    }

    public double quantoApostar(){//define o qunato a máquina vai apostar
        double apostaMaq;
        if(super.getSaldo()>0){//se o saldo for maior q 0 a máquina sempre vai apostar 10% do que ela tem
            apostaMaq=(0.10*super.getSaldo());
            return apostaMaq;
        }
        else{
            return 0;
        }
        
    }
}
