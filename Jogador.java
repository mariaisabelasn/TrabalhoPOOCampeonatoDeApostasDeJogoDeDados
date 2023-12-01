import java.io.Serializable;
import java.util.Scanner;
public abstract class Jogador implements Serializable{
    private String nome = new String();
    private String tipoJogador = new String();
    // JogoGeneral jogoGeneral;
    // JogoAzar jogoAzar;
    private double saldo;
    private JogoDados[] jogoDados;
    private int jogosRealizados= 0;
    private double valorAposta;
    private int i=0;
    private Dado dados;
    private transient Scanner teclado;
    private int[] qtdFaces;

   

    // Construtor
    public Jogador(String nome, String tipoJogador, double saldo) {
        this.nome = nome;
        this.tipoJogador = tipoJogador;
        if(jogosRealizados==0){
            this.saldo = 100;//saldo de todo jogador inicia com 100, maquina ou humano
        }
        else{
            this.saldo=saldo;
        }
        jogoDados =new JogoDados[10];//dez jogos dados
        teclado =new Scanner(System.in);
        qtdFaces=new int[6];
    }

    public String getNome() {// Função para pegar o nome dos respectivos jogadores.
        return this.nome;
    }

    public String getTipoJogador() {// Função para pegar o tipo dos respectivos jogadores.
        return this.tipoJogador;
    }

    public double getSaldo(){//função para pegar o saldo do jogador
        return this.saldo;
    }

    public void setSaldo(double valorAtualizado){//função para setar saldo
        this.saldo=valorAtualizado; //atualiza o valor ganho ou perdido do saldo

    }

    public int getJogoGeneral(int i, Jogador player) { // Função para pegar as jogadas da "ficha" dos respectivos jogadores.
        JogoGeneral jogoGeneral = (JogoGeneral) player.getJogoDados(player.getJogadasRealizadas());
        return  jogoGeneral.getJogadas(i);
    }

    public abstract void iniciarCassino(Jogador player, int jogo, int i);


    // public JogoGeneral getJogoG() { //para acessar por outra classe o jogo de cada jogador
    //     return this.jogoGeneral;
    // }
    
    // public JogoAzar getJogoA(){//para acessar de outra classe o jogo azar de cada jogador
    //     return this.jogoAzar;
    // }
    public void setJogoDados(JogoDados jogoDados, int i ){//seta jogo general colocando ele no array de jogoDados
        // this.jogoGeneral=jogoGeneral;
        this.jogosRealizados=i;
        this.jogoDados[jogosRealizados]=jogoDados;
        //jogosRealizados++;
    }
    public JogoDados getJogoDados (int i){
        return this.jogoDados[i];
        //return this.jogoDados[i].toString();
    }

    public int getJogadasRealizadas(){
        return jogosRealizados;
    }

    public void setJogadasRealizadas(){
         this.jogosRealizados++;
    }

    // public void setJogoAzar(JogoAzar jogoAzar){//seta jogo azar colocando ele no array de jogoDados
    //     this.jogoAzar=jogoAzar;
    //     this.jogoDado=jogoAzar;
    //     i++;
    // }


    public void dell(){ //deleta os dados do jogador
        this.nome=null;
        this.tipoJogador=null;
        // this.jogoGeneral=null;
        // this.jogoAzar=null;
        this.saldo=0;
        this.jogoDados=null;
    }

    public void mostrarJogadasExecutadas() {//mostra jogadas executadas do jogo general
        // Jogadas já feitas
        if(getJogoDados(getJogadasRealizadas()) instanceof JogoGeneral){
            JogoGeneral jogoGeneral = (JogoGeneral) getJogoDados(getJogadasRealizadas());
            for (int i = 0 ; i < 13 ; i++) { 
                if(jogoGeneral.getJogadas(i) !=-1) {
                    System.out.printf("%d\t", jogoGeneral.getJogadas(i)); 
                } 
                else if(jogoGeneral.getJogadas(i) ==-1) {
                    System.out.print("-\t");
                }
            }
            System.out.println("");
    }
    }

    public void iniciarJogoGeneral(Jogador player){//vai ter que passar um valor i pro jogaor
        double valorAposta=0;
        // int i=0;
        JogoGeneral jogoGeneral = (JogoGeneral) player.getJogoDados(player.getJogadasRealizadas());
        do{
            if(player.getJogoDados(player.getJogadasRealizadas()).getValorAposta()>player.getSaldo()){
                System.out.println("Saldo insuficiente! Aposte outro valor");
                valorAposta = teclado.nextDouble();
                player.getJogoDados(player.getJogadasRealizadas()).setValorAposta(valorAposta); 
            }
        }while(player.getJogoDados(player.getJogadasRealizadas()).getValorAposta()>player.getSaldo());

       // for(i = 0; i < contJogadores; i++) { // para iniciar ou resetar as jogadas e poder começar o campeonato novamente jogo general
            for(int j = 0; j < 13; j++){// para iniciar ou resetar as jogadas e poder começar o campeonato novamente jogo general
                jogoGeneral.setJogadas(j, -1);
            }
        //}
         
        for (int j = 0; j < 13; j++) {
           // for (i = 0; i < contJogadores; i++) {//NÃO DEVE EXISTIR PQ É UM UNICO JOGADOR POR VEZ
                System.out.println(">>Rolando dados para " + player.getNome());
                System.out.print("Valores obtidos: ");// imprime sem pular a linha pros dados ficarem do lado
                player.getJogoDados(player.getJogadasRealizadas()).rolarDados(5);
                qtdFaces = player.getJogoDados(player.getJogadasRealizadas()).somarFacesSorteadas(player.getJogoDados(player.getJogadasRealizadas()).getdados());
                //player.getJogoDados(player.getJogadasRealizadas()).
                jogoGeneral.mostrarDados();//tava grudado na linha de cima
                int opcao = 0;

                if(player instanceof Humano){ //faz a parada só jogar se o player for humano
                    Humano humano = (Humano) player;
                    humano.escolherJogada();
                }
                else if(player instanceof Maquina){ ///faz a parada só jogar se o player for maquina
                    Maquina maquina = (Maquina) player;
                    maquina.aplicarEstrategia();
                }

                int soma=0;
                for(int k=0;k<13;k++){
                    soma += player.getJogoGeneral(k, player);
                }
                double novoSaldo;
                if(soma>(2*jogoGeneral.getJogadas(13))){
                    System.out.println("Você ganhou a rodada!");
                    System.out.printf("Seu saldo era de R$ %.2f\n", player.getSaldo());
                    novoSaldo = player.getSaldo() + player.getJogoDados(player.getJogadasRealizadas()).getValorAposta();
                    player.setSaldo(novoSaldo);
                    System.out.printf("Seu saldo atual é de R$ %.2f\n", player.getSaldo());
                }
                else{
                    System.out.println("Você perdeu a rodada!");
                    System.out.printf("Seu saldo era de R$ %.2f\n", player.getSaldo());
                    novoSaldo = player.getSaldo() - player.getJogoDados(player.getJogadasRealizadas()).getValorAposta();
                    player.setSaldo(novoSaldo);
                    System.out.printf("Seu saldo atual é de R$ %.2f\n", player.getSaldo());
                }

            //}
        }


     }


    //fazer uma função para armazenar dez jogos(azar ou general)

    //função pra controlar a conta bancaria (começa sempre em 100)(saldo que vai decrementando e aumentando o valor da conta) 
            //ter saldo suficiente e n ultrapassar dez apostas

}