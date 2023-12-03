import java.util.Random;
import java.util.Scanner;

public class Maquina extends Jogador implements JogarComoMaquina {
    private int[] vet;
    private Random random;
    private transient Scanner teclado;
    private Campeonato campeonato;

    public Maquina(String nome, String tipoJogador, double saldo){
        super(nome, tipoJogador, saldo);
        vet=new int[13];
        random =new Random();
        this.teclado=new Scanner(System.in);
        this.campeonato=new Campeonato();
    }

    @Override
    public void iniciarCassino(Jogador player, int jogo, int i) {
        if(jogo==1){
            double valorAposta=0;
            do{
                if (player instanceof Humano){
                    System.out.println("Qual o valor que deseja apostar? ");//pede o valor da aposta
                    valorAposta = teclado.nextDouble();
                   // player.getJogoDados(player.getJogadasRealizadas()).setArmazenarAposta(i, valorAposta, player.getJogadasRealizadas());
                    if(valorAposta==0){
                    System.out.println("Aposte algum valor!");
                }
                }
                else if(player instanceof Maquina){
                    Maquina maquina=(Maquina) player;
                    valorAposta= maquina.quantoApostar();//para ver quanto a maquina aposta
                    // player.getJogoDados(player.getJogadasRealizadas()).setArmazenarAposta(i, valorAposta, player.getJogadasRealizadas());
                    System.out.println("Valor apostado pela máquina: R$"+valorAposta);//mostra o valor que a máquina apostou
                }
                
            }while(valorAposta==0);
            
            JogoDados jg =new JogoGeneral(valorAposta);//outro indice
           // player.getJogoDados(player.getJogadasRealizadas()).setArmazenarAposta(i,valorAposta,player.getJogadasRealizadas());//manda pro array de aposta o valor da aposta do jogo
            player.setJogoDados(jg, player.getJogadasRealizadas());

            if (player instanceof Humano){
                player.iniciarJogoGeneral(player);
                JogoGeneral jogoGeneral=(JogoGeneral) player.getJogoDados(player.getJogadasRealizadas());
                player.getJogoDados(player.getJogadasRealizadas()).setArmazenarAposta(i,valorAposta,player.getJogadasRealizadas());//manda pro array de aposta o valor da aposta do jogo
                jogoGeneral.setSalvarJogadasG(super.getJogadasRealizadas());//vai passar pro salvador o array de todas as jogadas do jogo general
                campeonato.mostrarCartela(player);
                //i++; //passa pro outra casa do vetor
                player.setJogadasRealizadas();
            }
            
            else if(player instanceof Maquina){
                player.iniciarJogoGeneral(player);
                JogoGeneral jogoGeneral=(JogoGeneral) player.getJogoDados(player.getJogadasRealizadas());
                player.getJogoDados(player.getJogadasRealizadas()).setArmazenarAposta(i,valorAposta,player.getJogadasRealizadas());//manda pro array de aposta o valor da aposta do jogo
                jogoGeneral.setSalvarJogadasG(super.getJogadasRealizadas());//vai passar pro salvador o array de todas as jogadas do jogo general 
                campeonato.mostrarCartela(player);
               // i++;
                player.setJogadasRealizadas();
            }

        }

        else if(jogo==2){
            double valorAposta=0;
            do{
                if (player instanceof Humano){
                    System.out.println("Qual o valor que deseja apostar? ");//pede o valor da aposta
                    valorAposta = teclado.nextDouble();
                    // player.getJogoDados(player.getJogadasRealizadas()).setArmazenarAposta(i, valorAposta, player.getJogadasRealizadas());
                    if(valorAposta==0){
                    System.out.println("Aposte algum valor!");
                }
                }
                else if(player instanceof Maquina){
                    Maquina maquina=(Maquina) player;
                    valorAposta= maquina.quantoApostar();//para ver quanto a maquina aposta
                    //player.getJogoDados(player.getJogadasRealizadas()).setArmazenarAposta(i, valorAposta, player.getJogadasRealizadas());//manda pro array de aposta o valor da aposta do jogo
                    System.out.println("Valor apostado pela máquina: R$"+valorAposta);//mostra o valor que a máquina apostou
                }
            }while(valorAposta==0);
            
            JogoDados ja =new JogoAzar(valorAposta);//outro indice
            //player.getJogoDados(player.getJogadasRealizadas()).setArmazenarAposta(i,valorAposta,player.getJogadasRealizadas());//manda pro array de aposta o valor da aposta do jogo
            player.setJogoDados(ja, player.getJogadasRealizadas());
            
            JogoAzar jogoAzar =(JogoAzar) player.getJogoDados(player.getJogadasRealizadas()); //converte jogo dados em jogo azar

            if (player instanceof Humano){
                jogoAzar.executarRegrasJogo(player);
                player.getJogoDados(player.getJogadasRealizadas()).setArmazenarAposta(i,valorAposta,player.getJogadasRealizadas());//manda pro array de aposta o valor da aposta do jogo
                //i++; //passa pro outra casa do vetor
                player.setJogadasRealizadas();
            }
            
            else if(player instanceof Maquina){
                jogoAzar.executarRegrasJogo(player);
                player.getJogoDados(player.getJogadasRealizadas()).setArmazenarAposta(i,valorAposta,player.getJogadasRealizadas());//manda pro array de aposta o valor da aposta do jogo
                //i++;
                player.setJogadasRealizadas();
            }
        }
        
    }
        
    

    public int sorteiaJogo() { //como cada jogo é individual e nehum jogador joga com outro é necessário um método pra maquina "entrar" em um jogo
        int opcao = 0;
      if(super.getSaldo() <=0.0){
        System.out.println("Saldo insufíciente, você faliu!");
        return 0;
      }
      if(super.getJogadasRealizadas()>=10){
        System.out.println("Você atingiu o limite máximo de jogatina no Cassino M&M, volte amanhã!");
        return 0;
      }
      do{
          System.out.println(super.getNome()+ ", qual jogo deseja jogar?");//DEFINE QUAL JOGO É
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
      }while((opcao==1||opcao==2)&& super.getSaldo()>0 && super.getJogadasRealizadas()<10);
      return 0;
  }

    @Override
    public int aplicarEstrategia() { //aplica a estratégia para o jogo general
        int opcao=0;
            int melhorPontuacao = 0;
            int melhorJogada=0;
           if (getJogoDados(getJogadasRealizadas()) instanceof JogoGeneral){
            JogoGeneral jogoGeneral = (JogoGeneral) getJogoDados(getJogadasRealizadas());
           
                while(opcao<13){//basicamente vai ver para aquela rodada qual vai ser a jogada com maior pontuação
                if(jogoGeneral.getJogadas(opcao)==-1){//se já não for ocupada a jogada
                    int pontuacao = jogoGeneral.pontuarJogada(opcao+1);
                    
                    if(pontuacao>=melhorPontuacao){//serve p achar a melhor jogada mas vai acabar preenchendo todas as outras do vetor jogadas tbm
                        melhorPontuacao=pontuacao;
                        melhorJogada=opcao;
                        vet[melhorJogada] = 1;//se a jogada já tiver sido usada anteriormente é marcada como 1;
                    }
                    
                }
               
                opcao++;
            }
            
            for (int k=0; k<13; k++){
                if(jogoGeneral.getJogadas(k)!= melhorPontuacao && vet[k]!=1){
                    jogoGeneral.setJogadas(k, -1); //resolve o problema de preenchimento de outras jogadas
                }
            }  
            jogoGeneral.setJogadas(melhorJogada, melhorPontuacao);//pontua para a máquina             
            System.out.println("1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)");
            super.mostrarJogadasExecutadas();
            System.out.println("Jogada que a maquina escolheu: "+ (melhorJogada+1));//retorna a jogada feita pela maquina melhorjogada(posição do vet)+1(pra ficar o "nome" da jogada certinho)

        }
        
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
