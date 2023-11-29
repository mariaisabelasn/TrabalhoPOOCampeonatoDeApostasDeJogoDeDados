import java.io.Serializable;
public abstract class Jogador implements Serializable{
    String nome = new String();
    String tipoJogador = new String();
    JogoGeneral jogoGeneral;
    double saldo;
    double saldo;
    int jogosRealizados= 0;
    double valorAposta;
   

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

    public void setSaldo(double valorAposta){//função para setar saldo
        this.valorAposta=valorAposta;

    }

    public int getJogoGeneral(int i) { // Função para pegar as jogadas da "ficha" dos respectivos jogadores.
        return this.jogoGeneral.getJogadas(i);
    }

    public JogoGeneral getJogo() { //para acessar por outra classe o jogo de cada jogador
        return this.jogoGeneral;
    }

    public void dell(){ //deleta os dados do jogador
        this.nome=null;
        this.tipoJogador=null;
        this.jogoGeneral=null;
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