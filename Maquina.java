import java.util.Random;
import java.util.Scanner;

public class Maquina extends Jogador implements JogarComoMaquina {
    // private Jogador players = new Jogador();
    // private JogoDados jDados = new JogoDados();
    private int[] vet;
    private Random random;
    private Scanner teclado;
    private Campeonato campeonato;
    private Dado[] dados;

    public Maquina(String nome, String tipoJogador, float saldo){
        super(nome, tipoJogador, saldo);
        vet=new int[13];
        random =new Random();
        this.teclado=new Scanner(System.in);
        this.campeonato=new Campeonato();
        dados =new Dado[5];
    }

    @Override
    public void iniciarCassino(Jogador player, int jogo, int i) {
        if(jogo==1){
            double valorAposta=0;
            do{
                if (player instanceof Humano){
                    System.out.println("Qual o valor que deseja apostar? ");//pede o valor da aposta
                    valorAposta = teclado.nextDouble();

                    if(valorAposta==0){
                    System.out.println("Aposte algum valor!");
                }
                }
                else if(player instanceof Maquina){
                    Maquina maquina=(Maquina) player;
                    valorAposta= maquina.quantoApostar();//para ver quanto a maquina aposta
                    System.out.println("Valor apostado pela máquina: R$"+valorAposta);//mostra o valor que a máquina apostou
                }
                
            }while(valorAposta==0);
            
            // jogoGeneral = new JogoGeneral(valorAposta);
            JogoDados jg =new JogoGeneral(valorAposta, dados);//outro indice
            player.setJogoDados(jg, player.getJogadasRealizadas());

            //JogoGeneral jogoGeneral = (JogoGeneral) players[i].getJogoDados(players[i].getJogadasRealizadas()); //conversão pra jogo general

            if (player instanceof Humano){
                //    humano=(Humano) players[i];
                //    humano.setJogoGeneral(jogoGeneral);
                player.iniciarJogoGeneral(i, player);
                //players[i].getJogoDados(jogoGeneral).
                // iniciarJogoGeneral(i);
                campeonato.mostrarCartela();
                i++; //passa pro outra casa do vetor
            }
            
            else if(player instanceof Maquina){
                // maquina=(Maquina) players[i];
                // maquina.setJogoGeneral(jogoGeneral);
               // jogoGeneral.iniciarJogoGeneral(i);
               player.iniciarJogoGeneral(i, player);
               campeonato.mostrarCartela();
                i++;
            }
            // players[i].jogoGeneral.iniciarJogoGeneral();
            // mostrarCartela();

        }
        else if(jogo==2){
            double valorAposta=0;
            do{
                if (player instanceof Humano){
                    System.out.println("Qual o valor que deseja apostar? ");//pede o valor da aposta
                    valorAposta = teclado.nextDouble();

                    if(valorAposta==0){
                    System.out.println("Aposte algum valor!");
                }
                }
                else if(player instanceof Maquina){
                    Maquina maquina=(Maquina) player;
                    valorAposta= maquina.quantoApostar();//para ver quanto a maquina aposta
                    System.out.println("Valor apostado pela máquina: R$"+valorAposta);//mostra o valor que a máquina apostou
                }
            }while(valorAposta==0);
            
            JogoDados ja =new JogoAzar(valorAposta);//outro indice

            player.setJogoDados(ja, player.getJogadasRealizadas());
            
            JogoAzar jogoAzar =(JogoAzar) player.getJogoDados(player.getJogadasRealizadas()); //converte jogo dados em jogo azar

            if (player instanceof Humano){
                //    humano=(Humano) players[i];
                //    humano.setJogoAzar(jogoAzar);
            jogoAzar.executarRegrasJogo(i);
                i++; //passa pro outra casa do vetor
            }
            
            else if(player instanceof Maquina){
                // maquina.setJogoAzar(jogoAzar);
                // maquina=(Maquina) players[i];
            jogoAzar.executarRegrasJogo(i);
                i++;
            }
        }
        // players[i].jogoAzar.executarRegrasJogo();
            // i++; //passa pro outra casa do vetor
        
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
        //   super.maquinasRealizadas++;//atualiza a guantidade de jogos feitos
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
