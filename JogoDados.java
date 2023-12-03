import java.io.Serializable;
public abstract class JogoDados implements Estatistica, Serializable{
    private int nDados;
    private double valorAposta;
    private String nomeJogo;
    private Dado[] dados;
    private int[] cont;// Inicializa o array cont
    //private int[] jogadas; //verificar

    public JogoDados(int nDados, String nomeJogo, Dado[] dados, double valorAposta){
        this.nDados = nDados;
        this.nomeJogo = nomeJogo;
        this.dados = dados;
        this.valorAposta = valorAposta;
        cont = new int[numFaces];
    }

    // Sobrecarga
    public JogoDados(int nDados, String nomeJogo, double valorAposta) {
        this.nDados = nDados;
        this.nomeJogo = nomeJogo;
        this.dados = new Dado[nDados];
        for (int i = 0; i < nDados; i++) {
            dados[i] = new Dado();
        }
        this.valorAposta = valorAposta;
        cont = new int[numFaces];
    }

    public String getNomeJogo(){
        return this.nomeJogo;
    }

    public void setNomeJogo(String nome){
        this.nomeJogo = nome;
    }

    public double getValorAposta(){
        return this.valorAposta;
    }

    public void setValorAposta(double valorAposta){
        this.valorAposta = valorAposta;
    }

    public int getCont(int i){
        return this.cont[i];
    }

    public int somaEstatistica(){
        int soma=0;
        for(int i=0;i<6;i++){
            soma += getCont(i);
        }
        return soma;
    }

    public Dado[] getdados (){
        return this.dados;
        
    }

    public String toString() {
        return "Nome do jogo: " + nomeJogo + ", Valor da aposta: " + valorAposta + ", Número de dados: " + nDados;
    }

    public abstract void rolarDados(int nDados);

    // public void setSalvarJogadasG(int j, Jogador player){
    //     //array de array pra salvar o array de jogadas do general
    //     //vai ficar na linha 114 Em Humano em baixo do setar no método escolher jogada provelmente em máquina tbm//vai ter que ser antes de recetar
    //     //j++ 
    //     JogoGeneral jogoGeneral= (JogoGeneral)player.getJogoDados(j);
    //     jogoGeneral.jogadasSalvasG[j][]=jogoGeneral.jogadas;
    
    // }
    
    
    @Override
    public int[] somarFacesSorteadas(Dado[] dados) { // método para verificar quantas vezes a face do dado caiu
        int i = 0;
    
        while (i < dados.length) {
            if(dados[i].getSideUp()==1){
                cont[0]++;
            }
            else if(dados[i].getSideUp()==2){
                cont[1]++;
            }
            else if(dados[i].getSideUp()==3){
                cont[2]++;
            }
            else if(dados[i].getSideUp()==4){
                cont[3]++;
            }
            else if(dados[i].getSideUp()==5){
                cont[4]++;
            }
            else if(dados[i].getSideUp()==6){
                cont[5]++;
            }

            i++;
        }
    
        return cont; // Retorna o vetor com a quantidade de vezes que cada face apareceu
    }
}
