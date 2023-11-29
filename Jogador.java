import java.io.Serializable;
public abstract class Jogador implements Serializable{
    String nome = new String();
    String tipoJogador = new String();
    JogoGeneral jogoGeneral;
    JogoAzar jogoAzar;
    double saldo;
    JogoDados[] jogoDados;
    int jogosRealizados= 0;
    double valorAposta;
    int i=0;
   

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

    public int getJogoGeneral(int i) { // Função para pegar as jogadas da "ficha" dos respectivos jogadores.
        return this.jogoGeneral.getJogadas(i);
    }

    public JogoGeneral getJogoG() { //para acessar por outra classe o jogo de cada jogador
        return this.jogoGeneral;
    }
    
    public JogoAzar getJogoA(){//para acessar de outra classe o jogo azar de cada jogador
        return this.jogoAzar;
    }
    public void setJogoGeneral(JogoGeneral jogoGeneral){//seta jogo general colocando ele no array de jogoDados
        this.jogoGeneral=jogoGeneral;
        this.jogoDados[i]=jogoGeneral;
        i++;
    }

    public void setJogoAzar(JogoAzar jogoAzar){//seta jogo azar colocando ele no array de jogoDados
        this.jogoAzar=jogoAzar;
        this.jogoDados[i]=jogoAzar;
        i++;
    }


    public void dell(){ //deleta os dados do jogador
        this.nome=null;
        this.tipoJogador=null;
        this.jogoGeneral=null;
        this.jogoAzar=null;
        this.saldo=0;
        this.jogoDados=null;
    }

    public void mostrarJogadasExecutadas() {
        // Jogadas já feitas
		for (int i = 0 ; i < 13 ; i++) { 
			if(this.jogoGeneral.getJogadas(i) !=-1) {
				System.out.printf("%d\t", this.jogoGeneral.getJogadas(i)); 
			} 
            else if(this.jogoGeneral.getJogadas(i) ==-1) {
				System.out.print("-\t");
			}
		}
		System.out.println("");
    }

    //fazer uma função para armazenar dez jogos(azar ou general)

    //função pra controlar a conta bancaria (começa sempre em 100)(saldo que vai decrementando e aumentando o valor da conta) 
            //ter saldo suficiente e n ultrapassar dez apostas

}