import java.io.Serializable;
import java.util.Scanner;
// Nessa classe usamos polimorfismo
public abstract class Jogador implements Serializable{
    private String nome = new String();
    private String tipoJogador = new String();
    private double saldo;
    private JogoDados[] jogoDados; //Array polimorfo que recebe instancia do tipo JogoAzar e JogoGeneral
    private int jogosRealizados= 0;
    private double valorAposta; 

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

    public void setJogoDados(JogoDados jogoDados, int i ){//seta jogo general colocando ele no array de jogoDados
        this.jogosRealizados=i;
        this.jogoDados[jogosRealizados]=jogoDados;
    }
    public JogoDados getJogoDados (int i){
        return this.jogoDados[i];
    }

    public int getJogadasRealizadas(){//pega o índice da última jogos já realizados
        return jogosRealizados;
    }

    public void setJogadasRealizadas(){ //aumenta o índice de jogos já feitos
         this.jogosRealizados++;
    }

    public void dell(){ //deleta os dados do jogador
        this.nome=null;
        this.tipoJogador=null;
        this.saldo=0;
        this.jogoDados=null;
    }

    public void mostrarJogadasExecutadas() {//mostra jogadas executadas do jogo general
        // Jogadas já feitas
        if(getJogoDados(getJogadasRealizadas()) instanceof JogoGeneral){//POLIMORFISMO
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

    public void iniciarJogoGeneral(Jogador player){
        Scanner teclado= new Scanner(System.in);
        valorAposta=0;
        JogoGeneral jogoGeneral = (JogoGeneral) player.getJogoDados(player.getJogadasRealizadas());
        do{
            if(player.getJogoDados(player.getJogadasRealizadas()).getValorAposta()>player.getSaldo()){
                System.out.println("Saldo insuficiente! Aposte outro valor");
                valorAposta = teclado.nextDouble();
                player.getJogoDados(player.getJogadasRealizadas()).setValorAposta(valorAposta); 
            }
        }while(player.getJogoDados(player.getJogadasRealizadas()).getValorAposta()>player.getSaldo());

            for(int j = 0; j < 13; j++){// para iniciar ou resetar as jogadas e poder começar o campeonato novamente jogo general
                jogoGeneral.setJogadas(j, -1);
            }
         
        for (int j = 0; j < 13; j++) {
                System.out.println(">>Rolando dados para " + player.getNome());
                System.out.print("Valores obtidos: ");// imprime sem pular a linha pros dados ficarem do lado
                player.getJogoDados(player.getJogadasRealizadas()).rolarDados(5);
                jogoGeneral.mostrarDados();//tava grudado na linha de cima
               
                if(player instanceof Humano){ //faz a parada só jogar se o player for humano -- POLIMORFISMO
                    Humano humano = (Humano) player;
                    humano.escolherJogada();
                }
                else if(player instanceof Maquina){ ///faz a parada só jogar se o player for maquina -- POLIMORFISMO
                    Maquina maquina = (Maquina) player;
                    maquina.aplicarEstrategia();
                }

        }
         
     }

}