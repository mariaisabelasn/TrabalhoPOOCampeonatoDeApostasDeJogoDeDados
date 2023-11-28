import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Campeonato implements Serializable {
    private int n = 10;
    private Jogador[] players; 
    private int contJogadores;
    private Scanner teclado;
    private String nome, biotipo, cpf, opcao;
    private Humano humano;
    private Maquina maquina;
    private float saldo;
    private JogoGeneral jogoGeneral;
    private JogoAzar jogoAzar;
    private String[] jogos;
    private int vet[];


    // CONSTRUTOR
    public Campeonato() {
        n = 10;
        players = new Jogador[n]; // vetor dos jogadores do campeonato
        contJogadores = 0;
        teclado = new Scanner(System.in);// scanf do java
        vet = new int[13];

        for (int i = 0; i < players.length; i++) {
            players[i] = null; // Define cada elemento como nulo
        }
    }

    public void incluirjogador() {
        saldo=0;
        if (contJogadores < n && players[contJogadores] == null) {

            System.out.println("Nome do Jogador(a): ");
            nome = teclado.nextLine();

            do{
                System.out.println("Tipo do Jogador [H-Humano ou M-Máquina]: ");
                biotipo = teclado.nextLine();

                if(biotipo.equals("H") || biotipo.equals("h")){
                    cpf=teclado.nextLine();
                }
    
            }while(!biotipo.equals("H") && !biotipo.equals("h") && !biotipo.equals("M") && !biotipo.equals("m"));//tratamento de dados pra caso o biotipo for diferente de humano ou máquina

            if(biotipo.equals("H") || biotipo.equals("h")){
                players[contJogadores] = new Humano(nome, biotipo, cpf, saldo);
            }
            else{
                players[contJogadores] = new Maquina(nome, biotipo, saldo);
            }
            //players[contJogadores] = jogador;
            contJogadores++;

        } else {
            System.out.println("Não podem ser incluídos novos jogadores(as)");
        }

    } 

    public void removerJogador() {
        System.out.println("Jogadores:");

        for (int i = 0; i <= contJogadores; i++) { // printar o nome dos jogadores
            if(contJogadores==0){
                System.out.println("Não há jogadores para excluir");
                break;
            }
            System.out.println(i+" - "+ players[i].getNome());
        }
        System.out.println("Digite o nome do jogador:");
        nome = teclado.nextLine();
        int i=0;
        int j;
        boolean verifica=false;
        do{
            if (nome.equals(players[i].getNome())) {
                players[i].dell();
                for (j = i; j < (contJogadores); j++) {
                    if(j+1!=n){//se não for o final, pra não puxar lixo
                         players[j] = players[j+1];// vai "puxando" os que vem depois pro lugar do excluindo e reordenando
                    }
                }
                players[j-1]=null;//zera o ultimo indice
                contJogadores--;// diminui a quantidade total de jogadores para que, se o usuario quiser, possa adicionar outro
                verifica=true;//verifica que teve um jogador
                break;
            }
            i++;
        }while(i!=contJogadores);
        
        
        if (verifica==false){//caso não haja o jogador
                System.out.println("Jogador(a) inexistente");
        }
        else{
            System.out.println("Jogador(a) excluido com sucesso");
        }

    }

    public void iniciarCampeonato() {//inicia ou reinicia um campeonato

        //PODE SE UMA FUNÇÃO CHAMNDO A ESCOLHER JOGO DO HUMANO SE FOR HUMANO E DO MAQ SE FOR MAQ
        //JÁ COMEÇAR O SETSALDO COM 100 PILA
        
        int jogo=0,i=0;
        if(players[i] instanceof Humano){
            Humano humano = (Humano) players[i];
            jogo = humano.escolherJogo();
        }
        
        else if(players[i] instanceof Maquina){
            Maquina maquina = (Maquina) players[i];
            jogo = maquina.sorteiaJogo();
        }

        if(jogo==1){
            float valorAposta;
            System.out.println("Qual o valor que deseja apostar? ");//pede o valor da aposta
            valorAposta= teclado.nextFloat();
            
            jogoGeneral = new JogoGeneral(valorAposta);
            jogoGeneral.iniciarJogoGeneral();
            mostrarCartela();

        }
        else if(jogo==2){
            float valorAposta;
            System.out.println("Qual o valor que deseja apostar? ");
            valorAposta= teclado.nextFloat();
            
            jogoAzar = new JogoAzar(valorAposta);
            jogoAzar.executarRegrasJogo();
        }

                    
    }
                
    public void mostrarCartela() {
        System.out.println("-- Cartela de Resultados --");
        System.out.print("Jogada\t");

        for(int i=0; i<contJogadores; i++){
            System.out.print(players[i].getNome()+"("+players[i].getTipoJogador()+")\t\t");
        } // vai imprimir o nome e o tipo de todos os players lado a lado
        System.out.print("\n"); //pula linha quando os nomes terminam

        String[] type={"1", "2", "3", "4", "5", "6", "7(T)", "8(Q)", "9(F)", "10(S+)", "11(S-)", "12(G)", "13(X)"};//string com os "nomes" das jogadas
        
        for(int j=0; j<13;j++){
            System.out.print(type[j]+"\t");//imprime os nomes das jogadas
            
            for(int k=0; k<contJogadores; k++){
                System.out.print(players[k].getJogoGeneral(j)+"\t\t"); // pega as pontuações jogadas de uma "ficha" dos jogadores que é o jogogeneral
            }
            System.out.print("\n");
        }
        if(contJogadores<=5){//para imprimir linha fofa 
            System.out.println("-------------------<3-------------------<3-------------------<3-------------------");
        }
        else if(contJogadores>5){
            System.out.println("-------------------<3-------------------<3-------------------<3-------------------<3------------------<3-------------------<3------------------");
        }
        System.out.print("Total\t");

        for(int k=0; k<contJogadores; k++){
            System.out.print(somaJogadas(k)+"\t\t");
        }
        System.out.print("\n");

    }

    public void mostrarSaldo(){

    }

    public void mostrarExtratos(){

    }

    public void mostrarEstatistica(){
        System.out.println("------- Estatícticas -------");
        System.out.println("Qual tipo de jogador? h - Humano    m - Máquina");
        opcao = teclado.nextLine();
        
        switch (opcao) {
            case "h":
                
                break;
            case "m":

                break;
        
            default:
                System.out.println ("Opcao invalida. Tente novamente");
        }

        System.out.println("Qual tipo de jogo? g - General    a - Azar");
        opcao = teclado.nextLine();
        
        switch (opcao) {
            case "g":
                
                break;
            case "a":

                break;
        
            default:
                System.out.println ("Opcao invalida. Tente novamente");
        }
        jogoAzar.somarFacesSorteadas(null);
    }

    public int somaJogadas(int jogante){
            int soma=0;
            for(int j=0; j<13; j++){//pra percorrer todos os jogos de cada jogador
                soma+=players[jogante].getJogoGeneral(j);
            }  
            return soma; 
    }

    public void gravarEmArquivo() {
        /* Gravar em arquivo */
        System.out.println("");
        File arquivo = new File("Campeonato.dat");
        try {
            FileOutputStream fout = new FileOutputStream(arquivo);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            // gravando o vetor de pessoas no arquivo
            oos.writeObject(players); //gravando o dado dos players
            oos.flush();
            oos.close();
            fout.close();
            System.out.println("Gravado com sucesso!");

        } catch (Exception ex) {
            System.err.println("erro: " + ex.toString());
        }

    }

    public void lerDoArquivo() {
        File arquivo = new File("Campeonato.dat");
        int i=1;

        try {
            FileInputStream fin = new FileInputStream(arquivo);
            ObjectInputStream oin = new ObjectInputStream(fin);

            //Lendo objetos de um arquivo
            players = (Jogador[]) oin.readObject();
            oin.close();
            fin.close();

            for (Jogador p : players) {
                if(p!=null){ 
                    System.out.println("Nome do jogador(a) "+i+ ":"+p.getNome().toString());
                    System.out.println("Tipo do jogador(a) "+i+":"+p.getTipoJogador().toString());
                    i++;
                }
            }
            contJogadores = i-1;
            //mostrarCartela();//mostra a cartela dos jogos 
        } catch (Exception ex) {
            System.err.println("erro: " + ex.toString());
        }

    }

}