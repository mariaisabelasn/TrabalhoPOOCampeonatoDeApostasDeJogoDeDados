import java.util.Random;

public class JogoAzar extends JogoDados{
    private Dado[] dados = new Dado[2];
    private float valorAposta;
    private int[] jogadas = new int[13];


    public JogoAzar(int nDados, String nomeJogo, float saldo, Dado[] dados, float valorAposta){
        super(nDados, nomeJogo, saldo, dados);
        this.valorAposta = valorAposta;
    }

    public float getValorAposta(){
        return this.valorAposta;
    }

    public void setValorAposta(float valor){
       this.valorAposta = valor;
    }

    public void executarRegrasJogo(){
        for(){
            dados[i] = rolarDados(numFaces);
        }
        int dado1 = random.nextInt(6) + 1; // Simula o lançamento do dado 1
        int dado2 = random.nextInt(6) + 1; // Simula o lançamento do dado 2
        int soma = dado1 + dado2;

        System.out.printf("Dado 1: %d, Dado 2: %d%n", dado1, dado2);
        System.out.printf("Soma das faces: %d%n", soma);

        if (soma == 7 || soma == 11) {
            System.out.println("Jogador ganha!");
        } else if (soma == 2 || soma == 3 || soma == 12) {
            System.out.println("Jogador perde!");
        } else {
            System.out.println("Continue jogando para atingir a soma inicial.");

            int novaSoma;
            do {
                dado1 = random.nextInt(6) + 1; // Novo lançamento do dado 1
                dado2 = random.nextInt(6) + 1; // Novo lançamento do dado 2
                novaSoma = dado1 + dado2;

                System.out.printf("Novo Dado 1: %d, Novo Dado 2: %d%n", dado1, dado2);
                System.out.printf("Nova Soma das faces: %d%n", novaSoma);

                if (novaSoma == soma) {
                    System.out.println("Jogador ganha!");
                    break;
                } else if (novaSoma == 7) {
                    System.out.println("Jogador perde!");
                    break;
                }

                System.out.println("Continue jogando para atingir a soma inicial.");

            } while (novaSoma != soma && novaSoma != 7);
        }
    }
    }

    public void rolarDados(int nDados) { // resultados dos 2 dados
        for (int i = 0; i < nDados; i++) {
            this.dados[i].roll();
        }
    }

    public String toString() {
        int soma[] = somarFacesSorteadas(dados);
    
        String result = dados[0].toString() + " + " + dados[1].toString() + "= " + soma[0].toString();
    
        return result;
    }
    
    
}
