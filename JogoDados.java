import java.io.Serializable;
public abstract class JogoDados implements Estatistica, Serializable{
    private int nDados;
    private double valorAposta;
    private String nomeJogo;
    private Dado[] dados;
    private int[] cont;// Inicializa o array cont
    private int[] jogadas; //verificar

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
    public void setCont( int[] qtdFaces){
        this.cont[0] = qtdFaces[0];
        this.cont[1] = qtdFaces[1];
        this.cont[2] = qtdFaces[2];
        this.cont[3] = qtdFaces[3];
        this.cont[4] = qtdFaces[4];
        this.cont[5] = qtdFaces[5];
    }

    public String somaEstatistica(){
        int soma=0;
        for(int i=0;i<6;i++){
            soma += getCont(i);
        }
        return "Face 1: "+getCont(0)+" Face 2: "+getCont(1)+" Face 3: "+getCont(2)+" Face 4: "+getCont(3)+" Face 5:"+getCont(4)+" Face 6: "+getCont(5)+" Total: "+soma;
    }

    public Dado[] getdados (){
        return this.dados;
        
    }

    // public String toString() {
    //     return "Face 1: "+dados[0]+" Face 2:"+dados[1]+" Face 3: "+dados[2]+" Face 4: "+dados[3]+" Face 5:"+dados[4]+" Face 6: "+dados[5];
    // }

    public abstract void rolarDados(int nDados);
    
    
    @Override
    public int[] somarFacesSorteadas(Dado[] dados) { // método para verificar quantas vezes a face do dado caiu
        int i = 0;
    
        while (i < dados.length) {
            if(dados[i].getSideUp()==1){ //Face 1
                cont[0]++;
            }
            else if(dados[i].getSideUp()==2){//Face 2
                cont[1]++;
            }
            else if(dados[i].getSideUp()==3){//Face 3
                cont[2]++;
            }
            else if(dados[i].getSideUp()==4){//Face 4
                cont[3]++;
            }
            else if(dados[i].getSideUp()==5){//Face 5
                cont[4]++;
            }
            else if(dados[i].getSideUp()==6){//Face 6
                cont[5]++;
            }

            i++;
        }
    
        return cont; // Retorna o vetor com a quantidade de vezes que cada face apareceu
    }

}
