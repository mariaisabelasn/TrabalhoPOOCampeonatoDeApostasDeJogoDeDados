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
          opcao = random.nextInt(2)+1; //sorteia 1 ou 2 pra máquina jogar

          if(opcao==1){
                  //função que inicia general
          }
          else if(opcao==2){
              //função que inicia o jogo de azar
          }
          else{
              System.out.println("Opção inválida!");
              opcao = 0;
          }
          jogosRealizados++;
      }while((opcao==1||opcao==2)&& this.saldo>0 && this.jogosRealizados<10); //   BOTAR MAIS UM EE PRA QUANDO JÁ TIVER SIDO JOGADO 10 JOGOS
      return 0;
  }

    @Override
    public int aplicarEstrategia() {
        int opcao=0;
            int melhorPontuacao = 0;
            int melhorJogada=0;
                while(opcao<13){//basicamente vai ver para aquela rodada qual vai ser a jogada com maior pontuação
                if(super.getJogo().getJogadas(opcao)==-1){//se já não for ocupada a jogada
                    int pontuacao = super.getJogo().pontuarJogada(opcao+1);
                    
                    if(pontuacao>=melhorPontuacao){//serve p achar a melhor jogada mas vai acabar preenchendo todas as outras do vetor jogadas tbm
                        melhorPontuacao=pontuacao;
                        melhorJogada=opcao;
                        vet[melhorJogada] = 1;//se a jogada já tiver sido usada anteriormente é marcada como 1;
                    }
                    
                }
               
                opcao++;
            }
            
            for (int k=0; k<13; k++){
                if(super.getJogo().getJogadas(k)!= melhorPontuacao && vet[k]!=1){
                    super.getJogo().setJogadas(k, -1); //resolve o problema de preenchimento de outras jogadas
                }
            }  
            super.getJogo().setJogadas(melhorJogada, melhorPontuacao);//pontua para a máquina             
            System.out.println("1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)");
            super.mostrarJogadasExecutadas();
            System.out.println("Jogada que a maquina escolheu: "+ (melhorJogada+1));//retorna a jogada feita pela maquina melhorjogada(posição do vet)+1(pra ficar o "nome" da jogada certinho)

        return 0;
    }

    public double quantoApostar(){
        double apostaMaq;
        if(super.getSaldo()>0){
            apostaMaq=(0.10*super.getSaldo());
            return apostaMaq;
        }
        else
        
    }
}
