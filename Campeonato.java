import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Campeonato implements Serializable {
    private int n = 10, i=0;
    private Jogador[] players; 
    private int contJogadores;
    private transient Scanner teclado;
    private String nome, biotipo, cpf, opcao, opcao1, opcao2, opcao3;
    private double saldo;
    private int vet[];//VER PRA QUE SERVIA NO CÓDIGO ANTIGO
    private JogoDados[] jogoDados; //array polimorfo que recebe instancia do tipo JogoAzar e JogoGeneral

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
                    System.out.println("Insira seu cpf:");
                    cpf=teclado.nextLine();
                }
    
            }while(!biotipo.equals("H") && !biotipo.equals("h") && !biotipo.equals("M") && !biotipo.equals("m"));//tratamento de dados pra caso o biotipo for diferente de humano ou máquina

            if(biotipo.equals("H") || biotipo.equals("h")){
                players[contJogadores] = new Humano(nome, biotipo, cpf, saldo);
            }
            else{
                players[contJogadores] = new Maquina(nome, biotipo, saldo);
            }
            contJogadores++;

        } else {
            System.out.println("Não podem ser incluídos novos jogadores(as)");
        }

    } 

    public void removerJogador() {
        System.out.println("Jogadores:");
        if(contJogadores==0){
            System.out.println("Não há jogadores para excluir");
            
        }
        else{
            for (int i = 0; i <contJogadores; i++) { // printar o nome dos jogadores
                
                System.out.println((i+1)+" - "+ players[i].getNome());
            }
            System.out.println("Digite o nome do jogador:");
            nome = teclado.nextLine();
            int i=0;
            int j;
            boolean verifica=false;
            do{
                if (nome.equals(players[i].getNome())) {
                    players[i].dell();
                    for (j = i; j < (contJogadores)-1; j++) {
                        if(j+1!=n){//se não for o final, pra não puxar lixo
                            players[j] = players[j+1];// vai "puxando" os que vem depois pro lugar do excluindo e reordenando
                        }
                    }
                    players[contJogadores-1]=null;//zera o ultimo indice
                    contJogadores--;// diminui a quantidade total de jogadores para que, se o usuario quiser, possa adicionar outro
                    verifica=true;//verifica que teve um jogador
                    break;
                }
                i++;
            }while(i<contJogadores);
            
            
            if (verifica==false){//caso não haja o jogador
                    System.out.println("Jogador(a) inexistente");
            }
            else{
                System.out.println("Jogador(a) excluido com sucesso");
            }
        }
    }

    public void iniciarCampeonato() {//inicia ou reinicia um campeonato

        for (i=0; i<contJogadores; i++){//percorre o vetor de players pra todos jogarem uma vez antes de voltar ao menu
            int jogo=0;
            
            if(players[i]!=null){
                if(players[i] instanceof Humano){
                    Humano humano = (Humano) players[i];
                    jogo = humano.escolherJogo();
                    humano.iniciarCassino(players[i], jogo, i);
                }
                
                else if(players[i] instanceof Maquina){
                    Maquina maquina = (Maquina) players[i];
                    jogo = maquina.sorteiaJogo();
                    maquina.iniciarCassino(players[i], jogo, i);
                }
            
            }
        }               
    }

    
                
    public void mostrarCartela(Jogador player) { // Mostra a Cartela do Jogo General
        System.out.println("-- Cartela de Resultados --");
        System.out.print("Jogada\t");

        System.out.print(player.getNome()+"("+player.getTipoJogador()+")\t\t");
        System.out.print("\n"); //pula linha quando os nomes terminam

        String[] type={"1", "2", "3", "4", "5", "6", "7(T)", "8(Q)", "9(F)", "10(S+)", "11(S-)", "12(G)", "13(X)"};//string com os "nomes" das jogadas
        
        for(int j=0; j<13;j++){
            System.out.print(type[j]+"\t");//imprime os nomes das jogadas
            System.out.print(player.getJogoGeneral(j, player)+"\t\t"); // pega as pontuações jogadas de uma "ficha" dos jogadores que é o jogogeneral
            System.out.print("\n");
        }
        if(contJogadores<=5){//para imprimir linha fofa 
            System.out.println("-------------------<3-------------------<3-------------------<3-------------------");
        }
        else if(contJogadores>5){
            System.out.println("-------------------<3-------------------<3-------------------<3-------------------<3------------------<3-------------------<3------------------");
        }
        System.out.print("Total\t");

        System.out.print(somaJogadas(player)+"\t\t");
        System.out.print("\n");

        int soma=0;
        for(int k=0;k<13;k++){
            soma += player.getJogoGeneral(k, player);
        }
        JogoGeneral jogoGeneral = (JogoGeneral)player.getJogoDados(player.getJogadasRealizadas());

        double novoSaldo;
        if(soma>(2*jogoGeneral.getJogadas(12))){
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
        System.out.print("\n");
        System.out.println(player.getJogoDados(player.getJogadasRealizadas()).somaEstatistica());
    }

    public void mostrarExtratoJG(Jogador player, int i) { // Mostra a Cartela dos extartos do jogo general
        System.out.println("-- Cartela de Resultados --");
        System.out.print("Jogada\t");

        System.out.print(player.getNome()+"("+player.getTipoJogador()+")\t\t");
        System.out.print("\n"); //pula linha quando os nomes terminam

        String[] type={"1", "2", "3", "4", "5", "6", "7(T)", "8(Q)", "9(F)", "10(S+)", "11(S-)", "12(G)", "13(X)"};//string com os "nomes" das jogadas
        
        //for(int i=0; i<contJogadores; i++){
            JogoGeneral jogoGeneral = (JogoGeneral) player.getJogoDados(i);
            int soma=0;
            for(int j=0; j<13;j++){
                System.out.print(type[j]+"\t");//imprime os nomes das jogadas
                System.out.print(jogoGeneral.getSalvarJogadasG(i, j)+"\t\t"); // pega as pontuações jogadas de uma "ficha" dos jogadores que é o jogogeneral
                System.out.print("\n");
            }
            soma=somaJogadasDoExtrato(i, jogoGeneral);
            // }
        if(contJogadores<=5){//para imprimir linha fofa 
            System.out.println("-------------------<3-------------------<3-------------------<3-------------------");
        }
        else if(contJogadores>5){
            System.out.println("-------------------<3-------------------<3-------------------<3-------------------<3------------------<3-------------------<3------------------");
        }
        System.out.print("Total\t");

        System.out.print(soma+"\t\t");
        System.out.print("\n");
    }

    public void mostrarSaldo(){
        System.out.println("Deseja imprimir saldo para quem?");
        System.out.println("a) Para todos os Jogadores");
        System.out.println("b) Apenas para os jogadores humanos");
        System.out.println("c) Apenas para os jogadores máquinas");
        opcao = teclado.nextLine();
        
        switch (opcao) {
            case "a":
                for (Jogador p : players) {//printa o saldo de todos os jogadores
                    if(p!=null){
                        System.out.println("-> Nome do jogador: "+ p.getNome() +" "+ "Saldo bancário: R$"+p.getSaldo());
                    }
                }
                
                break;

            case "b":
                for (Jogador p : players) {//printa o saldo de todos os humanos
                    if(p!=null && p instanceof Humano){
                        System.out.println("-> Nome do jogador: "+ p.getNome() +" "+ "Saldo bancário: R$"+p.getSaldo());
                    }
                }

                break;
            
            case "c":
                for (Jogador p : players) {//printa o saldo de todas as maquinas
                    if(p!=null && p instanceof Maquina){
                        System.out.println("-> Nome do jogador: "+ p.getNome() +" "+ "Saldo bancário: R$"+p.getSaldo());
                    }
                }
                break;
        
            default:
                System.out.println ("Opcao invalida. Tente novamente");
        }


    }

    public void mostrarExtratos(){ //(valores das jogadas [jogo general], valor apostado, ganho ou perda)
        System.out.println("Deseja imprimir extrato de qual jogo?");
        System.out.println("a) Para o Jogo General");
        System.out.println("b) Para o Jogo de Azar");
        System.out.println("c) Para ambos os jogos");
        opcao = teclado.nextLine();
        
        switch (opcao) {
            case "a":
                System.out.println("Deseja imprimir extrato de qual tipo de jogador?");
                System.out.println("a) Para todos os jogadores");
                System.out.println("b) Para jogadores humanos");
                System.out.println("c) Para jogadores máquinas");
                opcao1 = teclado.nextLine();

                switch (opcao1) {
                    case "a"://imprime o extrato do jogo general para todos os jogadores
                        
                        break;
                    case "b"://imprime o extrato do general para todos os jogadores humanos
                        for (Jogador p : players) {
                                if(p!=null && p instanceof Humano){
                                    System.out.println("-> Nome do jogador: "+ p.getNome());
                                    for (int i=0; i<contJogadores; i++){
                                        if(p.getJogoDados(i) instanceof JogoGeneral){
                                            System.out.println("Jogo General, "+(i+1)+"º jogo realizado");
                                            System.out.println("Esse foi o jogo feito: \n");
                                            mostrarExtratoJG(p, i);
                                            
                                        }
                                    }
                                }
                            }

                        
                        break;
                    case "c"://imprime o extrato do general para todos os jogadores máquina
                    for (Jogador p : players) {
                                if(p!=null && p instanceof Maquina){
                                    System.out.println("-> Nome do jogador: "+ p.getNome());
                                    //for (int i=0; i<contJogadores; i++){
                                    for(int j=0; j<10; j++){
                                            if(p.getJogoDados(j) instanceof JogoGeneral){
                                                System.out.println("Jogo General, "+(j+1)+"º jogo realizado");
                                                System.out.println("Esse foi o jogo feito: \n");
                                                mostrarExtratoJG(p, j);
                                                
                                            }
                                        }
                                  //  }
                                }
                            }
                        
                        break;
                
                
                    default:
                        break;
                }


                // for (Jogador p : players) {//imprime o extrato do jogo general
                //     if(p!=null){
                //         System.out.println("-> Nome do jogador: "+ p.getNome() +" "+ "Saldo bancário: R$"+p.getSaldo());
                //     }
                // }
                break;

            case "b":
                System.out.println("Deseja imprimir extrato de qual tipo de jogador?");
                System.out.println("a) Para todos os jogadores");
                System.out.println("b) Para jogadores humanos");
                System.out.println("c) Para jogadores máquinas");
                opcao1 = teclado.nextLine();

                switch (opcao1) {
                    case "a"://imprime o extrato do jogo azar para todos os jogadores
                        
                        break;
                    case "b"://imprime o extrato do jogo azar para todos os jogadores humanos
                        
                        break;
                    case "c"://imprime o extrato do jogo azar para todos os jogadores máquina
                        for (Jogador p : players) {
                            if(p!=null && p instanceof Maquina){
                                System.out.println("-> Nome do jogador: "+ p.getNome());
                                for (int i=0; i<10; i++){
                                    if(p.getJogoDados(i) instanceof JogoAzar){
                                        System.out.println("Jogo Azar, "+i+"º jogo realizado");
                                        System.out.println("Valor apostado:");//vaomos precisar de um array de apostas feitas em cada jogada
                                        System.out.println("Valor Ganho/Perdido: "); //vamo precisar de um array que grave um se na posição se o jogador ganhar o jogo e -1 se perder
                                    }
                                }
                            }
                        }
                        
                        break;
                
                
                    default:
                        break;
                }

                for (Jogador p : players) {//printa o saldo de todos os humanos
                    if(p!=null && p instanceof Humano){
                        System.out.println("-> Nome do jogador: "+ p.getNome() +" "+ "Saldo bancário: R$"+p.getSaldo());
                    }
                }
                break;
            
            case "c":
                System.out.println("Deseja imprimir extrato de qual tipo de jogador?");
                System.out.println("a) Para todos os jogadores");
                System.out.println("b) Para jogadores humanos");
                System.out.println("c) Para jogadores máquinas");
                opcao1 = teclado.nextLine();

                switch (opcao1) {
                    case "a"://imprime o extrato dos dois jogos  para todos os jogadores
                        
                        break;
                    case "b"://imprime o extrato dos dois jogos para todos os jogadores humanos
                        
                        break;
                    case "c"://imprime o extrato do dos dois jogos para todos os jogadores máquina
                        
                        break;
                
                
                    default:
                        break;
                }
                // for (Jogador p : players) {//printa o saldo de todas as maquinas
                //     if(p!=null && p instanceof Maquina){
                //         System.out.println("-> Nome do jogador: "+ p.getNome() +" "+ "Saldo bancário: R$"+p.getSaldo());
                //     }
                // }
                break;
        
            default:
                System.out.println ("Opcao invalida. Tente novamente");
        }


    }


    public void mostrarEstatistica(){
        System.out.println("------- Estatísticas -------");
        System.out.println("Deseja imprimir as estatísticas para qual das opções abaixo?");
        System.out.println("a) Por tipo de Jogador[humano ou máquina]");
        System.out.println("b) Por tipo de jogo[general ou de azar] escolhido por um jogador [específico]");
        System.out.println("c) Total por jogos[general e azar]");
        System.out.println("d) Total do campeonato");
        opcao = teclado.nextLine();
        
        switch (opcao) {
            case "a":
                do{
                    System.out.println("Humano (h) ou máquina (m)? ");
                    opcao=teclado.nextLine();

                    if(opcao.equals("h")){
                        System.out.println();

                    }
                    else if(opcao.equals("m")){
                        //chamar fçao da estatisticas de maquina
                    }
                }while(!opcao.equals("h")|| !opcao.equals("m"));

            break;
            case "b":
                do{
                    System.out.println("Jogo Azar (a) ou Jogo General (g)? ");
                    opcao=teclado.nextLine();

                    if(opcao.equals("a")){
                        for (JogoDados j : jogoDados) {//printa o saldo de todas as maquinas
                            if(j!=null && j instanceof JogoAzar){
                                System.out.println("Jogo Azar: "+j.somaEstatistica());
                            }
                         }
                        
                    }
                    // else if(opcao.equals("g")){
                    //     System.out.printf("Jogo General: %d%n",jogoDados.somaEstatistica());
                    // }
                }while(!opcao.equals("a")|| !opcao.equals("g"));

                break;
            // case "c":
                
            //     System.out.printf("Jogo Azar: %d%n",jogoDados.somaEstatistica());
            //     System.out.printf("Jogo General: %d%n",jogoDados.somaEstatistica());
            //     break;
            // case "d":
            //     System.out.printf("Total do Campeonato: %d%n",jogoDados.somaEstatistica()+jogoDados.somaEstatistica());
            //     break;
        
            default:
                System.out.println ("Opcao invalida. Tente novamente");
        }
    }

    public int somaJogadas(Jogador player){
            int soma=0;
            for(int j=0; j<13; j++){//pra percorrer todos os jogos de cada jogador
                soma+=player.getJogoGeneral(j, player);

            }  
            return soma; 
    }

    public int somaJogadasDoExtrato(int i, JogoGeneral jogoGeneral){
            int soma=0;
            for(int j=0; j<13; j++){//pra percorrer todos os jogos de cada jogador
                soma+=jogoGeneral.getSalvarJogadasG(i, j);

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
                    System.out.println("Nome do jogador(a) "+i+ ": "+p.getNome().toString());
                    System.out.println("Tipo do jogador(a) "+i+": "+p.getTipoJogador().toString());
                    i++;
                }
            }
            contJogadores = i-1;
        } catch (Exception ex) {
            System.err.println("erro: " + ex.toString());
        }

    }

}