public abstract class JogoDados implements Estatistica{
    private int nDados;
    private String nomeJogo;
    private Dado[] dados;

    public JogoDados(int nDados, String nomeJogo, Dado[] dados){
        this.nDados = nDados;
        this.nomeJogo = nomeJogo;
        this.dados = dados;
    }

    // Sobrecarga
    public JogoDados(int nDados, String nomeJogo) {
        this.nDados = nDados;
        this.nomeJogo = nomeJogo;
        this.dados = new Dado[nDados];
    }

    public String getNomeJogo(){
        return this.nomeJogo;
    }

    public void setNomeJogo(String nome){
        this.nomeJogo = nome;
    }

    public void rolarDados() { // resultados dos dados
        for (int i = 0; i < 1; i++) {
            this.dados[i].roll(numFaces);
        }
    }
    
    @Override
    public int[] somarFacesSorteadas(Dado[] dados) { // método para verificar quantas vezes a face do dado caiu
        int i = 0;
        int[] cont = new int[numFaces]; // Inicializa o array cont
    
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
