import java.util.Scanner;
// Nessa classe usamos polimorfismo, herança e herança multipla
public class Humano extends Jogador implements JogarComoHumano{
    private Campeonato campeonato;

    public Humano(String nome, String tipoJogador,double saldo){
        super(nome, tipoJogador, saldo); //HERANÇA
        this.campeonato=new Campeonato();
    }

    //SOBRESCRITA DA HERANÇA
    @Override
    public void iniciarCassino(Jogador player, int jogo, int i) {
        Scanner teclado= new Scanner(System.in);
        if(jogo==1){
            double valorAposta=0;
            do{
                if (player instanceof Humano){//POLIMORFISMO
                    System.out.println("Qual o valor que deseja apostar? ");//pede o valor da aposta
                    valorAposta = teclado.nextDouble();
                    if(valorAposta==0){
                    System.out.println("Aposte algum valor!");
                }
                }
                else if(player instanceof Maquina){//POLIMORFISMO
                    Maquina maquina=(Maquina) player;
                    valorAposta= maquina.quantoApostar();//para ver quanto a maquina aposta
                    System.out.println("Valor apostado pela máquina: R$"+valorAposta);//mostra o valor que a máquina apostou
                }
                
            }while(valorAposta==0);
            
            JogoDados jg =new JogoGeneral(valorAposta);//outro indice polimorfismo aqui
            player.setJogoDados(jg, player.getJogadasRealizadas());//seta o jogo para o player
            
            if (player instanceof Humano){//POLIMORFISMO
                player.iniciarJogoGeneral(player);
                player.getJogoDados(player.getJogadasRealizadas()).setArmazenarAposta(i,valorAposta,player.getJogadasRealizadas());//manda pro array de aposta o valor da aposta do jogo
                campeonato.mostrarCartela(player, i);
                player.setJogadasRealizadas();
            }
            
            else if(player instanceof Maquina){//POLIMORFISMO
               player.iniciarJogoGeneral(player);
               player.getJogoDados(player.getJogadasRealizadas()).setArmazenarAposta(i,valorAposta,player.getJogadasRealizadas());//manda pro array de aposta o valor da aposta do jogo
               campeonato.mostrarCartela(player, i);
               player.setJogadasRealizadas();
            }

        }
        else if(jogo==2){
            double valorAposta=0;
            do{
                if (player instanceof Humano){//POLIMORFISMO
                    System.out.println("Qual o valor que deseja apostar? ");//pede o valor da aposta
                    valorAposta = teclado.nextDouble(); 
                    if(valorAposta==0){
                    System.out.println("Aposte algum valor!");
                }
                }
                else if(player instanceof Maquina){//POLIMORFISMO
                    Maquina maquina=(Maquina) player;
                    valorAposta= maquina.quantoApostar();//para ver quanto a maquina aposta
                    System.out.println("Valor apostado pela máquina: R$"+valorAposta);//mostra o valor que a máquina apostou
                }
            }while(valorAposta==0);
            
            JogoDados ja =new JogoAzar(valorAposta);//outro indice polimorfismo aqui
            player.setJogoDados(ja, player.getJogadasRealizadas());//seta o jogo para o player
            
            JogoAzar jogoAzar =(JogoAzar) player.getJogoDados(player.getJogadasRealizadas()); //converte jogo dados em jogo azar

            if (player instanceof Humano){//POLIMORFISMO
                jogoAzar.executarRegrasJogo(player, i);//executa o jogo de azar 
                player.getJogoDados(player.getJogadasRealizadas()).setArmazenarAposta(i,valorAposta,player.getJogadasRealizadas());//manda pro array de aposta o valor da aposta do jogo
                player.setJogadasRealizadas();
            }
            
            else if(player instanceof Maquina){//POLIMORFISMO
                jogoAzar.executarRegrasJogo(player, i);//executa o jogo de azar
                player.getJogoDados(player.getJogadasRealizadas()).setArmazenarAposta(i,valorAposta,player.getJogadasRealizadas());//manda pro array de aposta o valor da aposta do jogo
                player.setJogadasRealizadas();
            }
        }
    }

    //SOBRESCRITA DA HERANÇA
    @Override
    public int escolherJogada() { //vai escolher a jogada do jogo general
        Scanner teclado= new Scanner(System.in);
        int opcao=0;
        if(getJogoDados(getJogadasRealizadas()) instanceof JogoGeneral){//POLIMORFISMO
            JogoGeneral jogoGeneral = (JogoGeneral) super.getJogoDados(getJogadasRealizadas()); //tranforma o get jogodados em um jogo general se ele for do tipo jogo general
                do {
                    System.out.println("Para qual jogada deseja marcar: [1-13] " + super.getNome() + "?");
                    System.out.println("1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)");
                    super.mostrarJogadasExecutadas();

                    while (opcao<=0|| opcao>13 || jogoGeneral.getJogadas(opcao-1)!=-1){//caso o usuário tente escolher uma opcao inexistente ou alguma jogada já feita de novo
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
                jogoGeneral.setSalvarJogadasG(super.getJogadasRealizadas());//vai passar pro salvador o array de todas as jogadas do jogo
        }
      return 0;
    }

    // SOBRESCRITA DA HERANÇA
    @Override
    public int escolherJogo() {
        Scanner teclado= new Scanner(System.in);
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
                return 1;//inicia general
            }
            else if(opcao==2){
                return 2;//inicia o jogo de azar
            }
            else{
                System.out.println("Opção inválida!");
                opcao = 0;
            }
        }while((opcao==1||opcao==2)&& super.getSaldo()>0 && getJogadasRealizadas()<10);
        return 0;
    }
}
