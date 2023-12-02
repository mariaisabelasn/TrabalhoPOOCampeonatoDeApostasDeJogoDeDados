import java.util.Scanner;
public class Humano extends Jogador implements JogarComoHumano{
    private String cpf;
    private String agencia;
    private String conta;
    private int numeroBanco,i;
    //private Jogador[] players; // referência para os jogadores
    private transient Scanner teclado;
    private Jogador humano;
    private Campeonato campeonato;
    private Dado[] dados;


    public Humano(String nome, String tipoJogador, String cpf,double saldo){
        super(nome, tipoJogador, saldo);
        this.cpf = cpf;
        this.agencia = "2567-8";
        this.conta = "2564987";
        this.numeroBanco = 007; 
        this.teclado=new Scanner(System.in);
        this.campeonato=new Campeonato();
        dados=new Dado[5];
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
        
        // players[i].jogoAzar.executarRegrasJogo();
            // i++; //passa pro outra casa do vetor
        }
    }

    @Override
    public int escolherJogada() { //vai escolher a jogada do jogo general
        int opcao=0;
        if(getJogoDados(getJogadasRealizadas()) instanceof JogoGeneral){
            JogoGeneral jogoGeneral = (JogoGeneral) super.getJogoDados(getJogadasRealizadas()); //tranforma o get jogodados em um jogo general se ele for do tipo jogo general
                do {
                    System.out.println("Para qual jogada deseja marcar: [1-13] " + super.getNome() + "?");
                    System.out.println("1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)");
                    super.mostrarJogadasExecutadas();

                    while(opcao<=0|| opcao>13 || jogoGeneral.getJogadas(opcao-1)!=-1){//caso o usuário tente escolher uma opcao inexistente ou alguma jogada já feita de novo
                        opcao = teclado.nextInt();
                        teclado.nextLine(); // Limpar o buffer de entrada após a leitura do inteiro
                    
                        if(opcao<=0||opcao>13 || jogoGeneral.getJogadas(opcao-1)!=-1){
                            System.out.println("Jogada inválida, escolha outra.");
                        }
                    }
                    
                        if (jogoGeneral.getJogadas(opcao-1)==-1) { //se a jogada ainda nao tiver sido feita
                            jogoGeneral.setJogadas(opcao-1, jogoGeneral.pontuarJogada(opcao));
                        } 
                } while (jogoGeneral.getJogadas(opcao-1)==-1);
        }
      return 0;
    }

    @Override
    public int escolherJogo() {
          int opcao = 0;
          if(super.getSaldo() <=0.0){
            System.out.println("Saldo insufíciente, você faliu!");
            return 0;
          }
          if(getJogadasRealizadas()==10){
            System.out.println("Você atingiu o limite máximo de jogatina no Cassino M&M, volte amanhã!");
            return 0;
          }
        do{
            System.out.println(super.getNome()+ ", qual jogo deseja jogar?");//DEFINE QUAL JOGO É
            System.out.println("1 - Jogo General");
            System.out.println("2 - Jogo Azar");
            opcao = teclado.nextInt();
            

            if(opcao==1){
                return 1;   //inicia general
            }
            else if(opcao==2){
                return 2;//inicia o jogo de azar
            }
            else{
                System.out.println("Opção inválida!");
                opcao = 0;
            }
            // getJogadasRealizadas()++;//atualiza a guantidade de jogos feitos
        }while((opcao==1||opcao==2)&& super.getSaldo()>0 && getJogadasRealizadas()<10);
        return 0;
    }
}
