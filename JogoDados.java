import java.io.Serializable;
public abstract class JogoDados implements Estatistica, Serializable{
    private int nDados;
    private double valorAposta;
    private String nomeJogo;
    private Dado[] dados;
    private int[] cont;// Inicializa o array cont

    public JogoDados(int nDados, String nomeJogo, Dado[] dados, double valorAposta){
        this.nDados = nDados;
        this.nomeJogo = nomeJogo;
        this.dados = dados;
        this.valorAposta = valorAposta;
        cont = new int[numFaces];
        //this.numFaces = numFaces;
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

    // public int getNumFaces(){
    //     return this.numFaces;
    // }
    public abstract int getJogadas(int i);

    public abstract void setJogadas(int i, int x);

    public void rolarDados(int nDados) { // resultados dos dados
        for (int i = 0; i < nDados; i++) {
            this.dados[i].roll(numFaces);
        }
    }
    
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
