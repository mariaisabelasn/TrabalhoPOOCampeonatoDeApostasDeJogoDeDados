import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Campeonato implements Serializable {
    private int n = 10, i=0;
    private Jogador[] players; //array polimorfo que recebe instancia do tipo Maquina e Humano
    private int contJogadores;
    private String nome, biotipo, cpf, opcao, opcao1, opcao2, opcao3;
    private double saldo;
 
    // CONSTRUTOR
    public Campeonato() {
        this.n = 10;
        this.players = new Jogador[n]; // vetor dos jogadores do campeonato
        this.contJogadores = 0;

        for (int i = 0; i < players.length; i++) {
            players[i] = null; // Define cada elemento como nulo
        }
    }

    public void incluirjogador() {
        Scanner teclado= new Scanner(System.in);
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
        Scanner teclado= new Scanner(System.in);

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
                if(players[i] instanceof Humano){ // se for humano -- POLIMORFISMO
                    Humano humano = (Humano) players[i];
                    jogo = humano.escolherJogo();
                    humano.iniciarCassino(players[i], jogo, i);
                }
                
                else if(players[i] instanceof Maquina){// se for maquina --POLIMORFISMO
                    Maquina maquina = (Maquina) players[i];
                    jogo = maquina.sorteiaJogo();
                    maquina.iniciarCassino(players[i], jogo, i);
                }
            
            }
        }               
    }
             
    public void mostrarCartela(Jogador player, int i) { // Mostra a Cartela do Jogo General
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
        if(soma>(2*jogoGeneral.getJogadas(12))){ // se dobro do valor da jogada treze for menor que a soma das outras jogadas então ele ganhou o jogo general
            System.out.println("Você ganhou a rodada!");
            player.getJogoDados(player.getJogadasRealizadas()).setArmazenarResultados(i, 1, player.getJogadasRealizadas());//seta com 1 se ganhar
            System.out.printf("Seu saldo era de R$ %.2f\n", player.getSaldo());
            novoSaldo = player.getSaldo() + player.getJogoDados(player.getJogadasRealizadas()).getValorAposta(); //adicionar ao saldo o valor apostado
            player.setSaldo(novoSaldo);
            System.out.printf("Seu saldo atual é de R$ %.2f\n", player.getSaldo());
        }
        else{ // se nao ele perdeu o jogo general
            System.out.println("Você perdeu a rodada!");
            player.getJogoDados(player.getJogadasRealizadas()).setArmazenarResultados(i, -1, player.getJogadasRealizadas());
            System.out.printf("Seu saldo era de R$ %.2f\n", player.getSaldo());
            novoSaldo = player.getSaldo() - player.getJogoDados(player.getJogadasRealizadas()).getValorAposta(); // retirar do saldo o valor apostado
            player.setSaldo(novoSaldo);
            System.out.printf("Seu saldo atual é de R$ %.2f\n", player.getSaldo());
        }
        System.out.print("\n");

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
                System.out.print(jogoGeneral.getSalvarJogadasG(i,j)+"\t\t"); // pega as pontuações jogadas de uma "ficha" dos jogadores que é o jogogeneral
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
        Scanner teclado= new Scanner(System.in);

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
                    if(p!=null && p instanceof Humano){ //POLIMORFISMO
                        System.out.println("-> Nome do jogador: "+ p.getNome() +" "+ "Saldo bancário: R$"+p.getSaldo());
                    }
                }

                break;
            
            case "c":
                for (Jogador p : players) {//printa o saldo de todas as maquinas
                    if(p!=null && p instanceof Maquina){//POLIMORFISMO
                        System.out.println("-> Nome do jogador: "+ p.getNome() +" "+ "Saldo bancário: R$"+p.getSaldo());
                    }
                }
                break;
        
            default:
                System.out.println ("Opcao invalida. Tente novamente");
        }


    }

    public void mostrarExtratos(){ //(valores das jogadas [jogo general], valor apostado, ganho ou perda)
        Scanner teclado= new Scanner(System.in);
        System.out.println("Deseja imprimir extrato de qual jogo?");
        System.out.println("a) Para o Jogo General");
        System.out.println("b) Para o Jogo de Azar");
        System.out.println("c) Para ambos os jogos");
        opcao = teclado.nextLine();
        
        switch (opcao) {
            case "a"://JOGO GENERAL
                System.out.println("Deseja imprimir extrato de qual tipo de jogador?");
                System.out.println("a) Para todos os jogadores");
                System.out.println("b) Para jogadores humanos");
                System.out.println("c) Para jogadores máquinas");
                opcao1 = teclado.nextLine();

                switch (opcao1) {
                    case "a"://imprime o extrato do jogo general para todos os jogadores
                            for(int j=0; j<contJogadores; j++){
                                if(players[j]!=null){
                                    for (int i=0; i<players[j].getJogadasRealizadas(); i++){//mudar para p.getjogadas realizadas
                                        if(players[j].getJogoDados(i) instanceof JogoGeneral){
                                                System.out.println("-> Nome do jogador: "+ players[j].getNome());
                                                System.out.println("Jogo General, "+(i+1)+"º jogo realizado");
                                                System.out.printf("O valor apostado nesse jogo foi: %.2f\n", players[j].getJogoDados(i).getArmazenadorDeApostas(j, i) );
                                                System.out.println("Esse foi o jogo feito: \n");
                                                mostrarExtratoJG(players[j], i);
                                                System.out.println("E o resultado dele foi esse: ");
                                                    if(players[j].getJogoDados(i).getArmazenadorDeResultados(j, i)==1){
                                                        System.out.println("Jogo vencido!!\n");
                                                    }
                                                    else{
                                                        System.out.println("Jogo perdido!\n");
                                                    }
                                            }
                                        }
                                    }
                                }
                           
                        
                        break;
                    case "b"://imprime o extrato do general para todos os jogadores humanos
                            for(int j=0; j<contJogadores; j++){
                                if(players[j]!=null && players[j] instanceof Humano){
                                    for (int i=0; i<players[j].getJogadasRealizadas(); i++){
                                        if(players[j].getJogoDados(i) instanceof JogoGeneral){
                                                    System.out.println("-> Nome do jogador: "+ players[j].getNome());
                                                    System.out.println("Jogo General, "+(i+1)+"º jogo realizado");
                                                    System.out.printf("O valor apostado nesse jogo foi: %.2f%n", players[j].getJogoDados(i).getArmazenadorDeApostas(j, i) );
                                                    System.out.println("Esse foi o jogo feito: \n");
                                                    mostrarExtratoJG(players[j], i);
                                                    System.out.println("E o resultado dele foi esse: ");
                                                        if(players[j].getJogoDados(i).getArmazenadorDeResultados(j, i)==1){
                                                            System.out.println("Jogo vencido!!\n");
                                                        }
                                                        else{
                                                            System.out.println("Jogo perdido!\n");
                                                        }
                                                }
                                            }
                                        }
                                }
                          
                        
                        break;
                    case "c"://imprime o extrato do general para todos os jogadores máquina
                   // for (Jogador p : players) {
                       for (int j=0; j<contJogadores; j++){
                                if(players[j]!=null &&  players[j] instanceof Maquina){//polimorfismo
                                    for(int i=0; i<players[j].getJogadasRealizadas(); i++){
                                        if(players[j].getJogoDados(i) instanceof JogoGeneral){
                                                    System.out.println("-> Nome do jogador: "+ players[j].getNome());
                                                    System.out.println("Jogo General, "+(i+1)+"º jogo realizado");
                                                    System.out.printf("O valor apostado nesse jogo foi: %.2f\n", players[j].getJogoDados(i).getArmazenadorDeApostas(j, i) );
                                                    System.out.println("Esse foi o jogo feito: \n");
                                                    mostrarExtratoJG(players[j], j);
                                                    System.out.println("E o resultado dele foi esse: ");
                                                        if(players[j].getJogoDados(i).getArmazenadorDeResultados(j, i)==1){
                                                            System.out.println("Jogo vencido!!\n");
                                                        }
                                                        else{
                                                            System.out.println("Jogo perdido!\n");
                                                        }
                                                }
                                              
                                        }
                                }
                         }
                        
                        break;
                
                    default:
                    System.out.println("Opção, inválida!");
                        break;
                }

                break;

            case "b"://JOGO AZAR
                System.out.println("Deseja imprimir extrato de qual tipo de jogador?");
                System.out.println("a) Para todos os jogadores");
                System.out.println("b) Para jogadores humanos");
                System.out.println("c) Para jogadores máquinas");
                opcao2 = teclado.nextLine();

                switch (opcao2) {
                    case "a"://imprime o extrato do jogo azar para todos os jogadores
                        for (int j=0; j<contJogadores; j++){
                                if(players[j]!=null){
                                    for(int i=0; i<players[j].getJogadasRealizadas(); i++){
                                        if(players[j].getJogoDados(i) instanceof JogoAzar){
                                                    System.out.println("-> Nome do jogador: "+ players[j].getNome());
                                                    System.out.println("Jogo Azar, "+(i+1)+"º jogo realizado");
                                                    System.out.printf("O valor apostado nesse jogo foi: %.2f \n", players[j].getJogoDados(i).getArmazenadorDeApostas(j, i) );     
                                                    System.out.println("E o resultado dele foi esse: ");
                                                    if(players[j].getJogoDados(i).getArmazenadorDeResultados(j, i)==1){
                                                        System.out.println("Jogo vencido!!\n");
                                                    }
                                                    else{
                                                        System.out.println("Jogo perdido!\n");
                                                    }                                           
                                                }
                                         }
                                    }
                               }
                        
                        break;
                    case "b"://imprime o extrato do jogo azar para todos os jogadores humanos
                       for (int j=0; j<contJogadores; j++){
                                if(players[j]!=null && players[j] instanceof Humano){//polimorfismo
                                    for(int i=0; i<players[j].getJogadasRealizadas(); i++){
                                        if(players[j].getJogoDados(i) instanceof JogoAzar){
                                                    System.out.println("-> Nome do jogador: "+ players[j].getNome());
                                                    System.out.println("Jogo Azar, "+(i+1)+"º jogo realizado");
                                                    System.out.printf("O valor apostado nesse jogo foi: %.2f \n", players[j].getJogoDados(i).getArmazenadorDeApostas(j, i) ); 
                                                    System.out.println("E o resultado dele foi esse: ");
                                                    if(players[j].getJogoDados(i).getArmazenadorDeResultados(j, i)==1){
                                                        System.out.println("Jogo vencido!!\n");
                                                    }
                                                    else{
                                                        System.out.println("Jogo perdido!\n");
                                                    }                                               
                                                }
                                     }
                                }
                         }                        
                        break;
                    case "c"://imprime o extrato do jogo azar para todos os jogadores máquina
                       for (int j=0; j<contJogadores; j++){
                                if(players[j]!=null && players[j] instanceof Maquina){//polimorfismo/
                                    for(int i=0; i<players[j].getJogadasRealizadas(); i++){
                                        if(players[j].getJogoDados(i) instanceof JogoAzar){
                                                    System.out.println("-> Nome do jogador: "+ players[j].getNome());
                                                    System.out.println("Jogo Azar, "+(i+1)+"º jogo realizado");
                                                    System.out.printf("O valor apostado nesse jogo foi: %.2f%n", players[j].getJogoDados(i).getArmazenadorDeApostas(j, i) );
                                                    System.out.println("E o resultado dele foi esse: ");
                                                        if(players[j].getJogoDados(i).getArmazenadorDeResultados(j, i)==1){
                                                            System.out.println("Jogo vencido!!\n");
                                                        }
                                                        else{
                                                            System.out.println("Jogo perdido!\n");
                                                        }                                            
                                            }
                                        }
                                    }
                                }
                        break;
                
                    default:
                    System.out.println("Opção inválida");
                        break;
            }
            break;
            
            case "c":
                System.out.println("Deseja imprimir extrato de qual tipo de jogador?");
                System.out.println("a) Para todos os jogadores");
                System.out.println("b) Para jogadores humanos");
                System.out.println("c) Para jogadores máquinas");
                opcao3 = teclado.nextLine();

                switch (opcao3) {
                    case "a"://imprime o extrato dos dois jogos  para todos os jogadores
                        for (int j=0; j<contJogadores; j++){
                            System.out.println("-> Nome do jogador: "+ players[j].getNome());
                            for(int i=0; i<players[j].getJogadasRealizadas(); i++){
                                if(players[j].getJogoDados(i) instanceof JogoGeneral){//polimorfismo
                                                System.out.println("Jogo General, "+(i+1)+"º jogo realizado");
                                                System.out.printf("O valor apostado nesse jogo foi: %.2f%n", players[j].getJogoDados(i).getArmazenadorDeApostas(j, i) );
                                                System.out.println("Esse foi o jogo feito: \n");
                                                mostrarExtratoJG(players[j], j);
                                                System.out.println("E o resultado dele foi esse: ");
                                                if(players[j].getJogoDados(i).getArmazenadorDeResultados(j, i)==1){
                                                    System.out.println("Jogo vencido!!");
                                                }
                                                else{
                                                    System.out.println("Jogo perdido!");
                                                }
                                            }
                                            if(players[j].getJogoDados(i) instanceof JogoAzar){//polimorfismo
                                                System.out.println("\nJogo Azar, "+(i+1)+"º jogo realizado");
                                                System.out.printf("O valor apostado nesse jogo foi: %.2f%n", players[j].getJogoDados(i).getArmazenadorDeApostas(j, i) );
                                                System.out.println("E o resultado dele foi esse: ");
                                                if(players[j].getJogoDados(i).getArmazenadorDeResultados(j, i)==1){
                                                    System.out.println("Jogo vencido!!\n");
                                                }
                                                else{
                                                    System.out.println("Jogo perdido!\n");
                                                }
                                                                                                
                                            }
                                            j++;
                                        }
                                  }         
                        break;
                    case "b"://imprime o extrato dos dois jogos para todos os jogadores humanos
                        for (int j=0; j<contJogadores; j++){
                                if(players[j]!=null && players[j] instanceof Humano){//polimorfismo
                                    for(int i=0; i<players[j].getJogadasRealizadas(); i++){
                                        System.out.println("-> Nome do jogador: "+ players[j].getNome());
                                        if(players[j].getJogoDados(i) instanceof JogoGeneral){//polimorfismo
                                            System.out.println("Jogo General, "+(i+1)+"º jogo realizado");
                                            System.out.printf("O valor apostado nesse jogo foi: %.2f%n", players[j].getJogoDados(i).getArmazenadorDeApostas(j, i) );
                                            System.out.println("Esse foi o jogo feito: \n");
                                            mostrarExtratoJG(players[j], j);
                                            System.out.println("E o resultado dele foi esse: ");
                                            if(players[j].getJogoDados(i).getArmazenadorDeResultados(j, i)==1){
                                                System.out.println("Jogo vencido!!\n");
                                            }
                                            else{
                                                System.out.println("Jogo perdido!\n");
                                            }
                                            
                                        }
                                        if(players[j].getJogoDados(i) instanceof JogoAzar){//polimorfismo
                                            System.out.println("Jogo Azar, "+(i+1)+"º jogo realizado");
                                            System.out.printf("O valor apostado nesse jogo foi: %.2f%n", players[j].getJogoDados(i).getArmazenadorDeApostas(j, i) );
                                            System.out.println("E o resultado dele foi esse: ");
                                            if(players[j].getJogoDados(i).getArmazenadorDeResultados(j, i)==1){
                                                System.out.println("Jogo vencido!!\n");
                                            }
                                            else{
                                                System.out.println("Jogo perdido!\n");
                                            }                                               
                                        }
                                         }
                                    }
                                }
                        
                        break;
                    case "c"://imprime o extrato do dos dois jogos para todos os jogadores máquina
                        for (int j=0; j<contJogadores; j++){
                            if(players[j]!=null && players[j] instanceof Maquina){//polimorfismo
                                for(int i=0; i<players[j].getJogadasRealizadas(); i++){
                                        System.out.println("-> Nome do jogador: "+ players[j].getNome());
                                                if(players[j].getJogoDados(i) instanceof JogoGeneral){//polimorfismo
                                                    System.out.println("Jogo General, "+(i+1)+"º jogo realizado");
                                                    System.out.printf("O valor apostado nesse jogo foi: %.2f%n", players[j].getJogoDados(i).getArmazenadorDeApostas(j, i) );
                                                    System.out.println("Esse foi o jogo feito: \n");
                                                    mostrarExtratoJG(players[j], j);
                                                    System.out.println("E o resultado dele foi esse: ");
                                                        if(players[j].getJogoDados(i).getArmazenadorDeResultados(j, i)==1){
                                                            System.out.println("Jogo vencido!!\n");
                                                        }
                                                        else{
                                                            System.out.println("Jogo perdido!\n");
                                                        }
                                                
                                                }
                                                if(players[j].getJogoDados(i) instanceof JogoAzar){
                                                    System.out.println("Jogo Azar, "+(i+1)+"º jogo realizado");
                                                    System.out.printf("O valor apostado nesse jogo foi: %.2f%n", players[j].getJogoDados(i).getArmazenadorDeApostas(j, i) );
                                                    System.out.println("E o resultado dele foi esse: ");
                                                        if(players[j].getJogoDados(i).getArmazenadorDeResultados(j, i)==1){
                                                            System.out.println("Jogo vencido!!\n");
                                                        }
                                                        else{
                                                            System.out.println("Jogo perdido!\n");
                                                        }                                              
                                                }
                                    }
                                }
                        }
                     break;

                    default:
                    System.out.println("Opção inválida!");
                        break;
                }
                break;
        
            default:
                System.out.println ("Opcao invalida. Tente novamente");
        }

    }

    public void mostrarEstatistica(){
        Scanner teclado= new Scanner(System.in);
        System.out.println("------- Estatísticas -------");
        System.out.println("Deseja imprimir as estatísticas para qual das opções abaixo?");
        System.out.println("a) Por tipo de Jogador[humano ou máquina]");
        System.out.println("b) Por tipo de jogo[general ou de azar] escolhido por um jogador [específico]");
        System.out.println("c) Total por jogos[general e azar]");
        System.out.println("d) Total do campeonato");
        opcao = teclado.nextLine();
        
        switch (opcao) {
            case "a":
                System.out.println("Humano (h) ou máquina (m)? ");
                opcao1=teclado.nextLine();
                int[] soma=new int[6];

                switch (opcao1) {
                    case "h":
                        for (Jogador p : players) {//printa a estatistica de todos os jogadores humano
                            if(p!=null && p instanceof Humano){//POLIMORFISMO
                                for (int i=0; i<p.getJogadasRealizadas(); i++){
                                    JogoDados jogoDados = p.getJogoDados(i);
                                    for(int j=0;j<6;j++){
                                        soma[j] += jogoDados.getCont(j);
                                    }
                                }
                                System.out.println("\n->"+p.getNome()+":");
                                for(int j=0;j<6;j++){
                                    System.out.printf("Face "+(j+1)+": %d\n",soma[j]);
                                }
                                for(int k=0;k<6;k++){ //zera o vetor soma
                                    soma[k] = 0;
                                }
                            }
                            
                        }
                        
                        break;

                    case "m":
                        for (Jogador p : players) {//printa  a estatistica de todos os jogadores maquina
                            if(p!=null && p instanceof Maquina){//POLIMORFISMO
                                for (int i=0; i<p.getJogadasRealizadas(); i++){
                                    JogoDados jogoDados = p.getJogoDados(i);
                                    for(int j=0;j<6;j++){
                                        soma[j] += jogoDados.getCont(j);
                                    }
                                }
                                System.out.println("\n->"+p.getNome()+":");
                                for(int j=0;j<6;j++){
                                    System.out.printf("Face "+(j+1)+": %d\n",soma[j]);
                                }
                                for(int k=0;k<6;k++){//zera o vetor soma
                                    soma[k] = 0;
                                }
                            }
                            
                        }
                        
                    break;
                
                    default:
                        System.out.println("Opção inválida! Tente outra!");
                        break;
                }

                break;
            case "b":
                System.out.println("Jogo General (g) ou Jogo Azar (a)? ");
                opcao1=teclado.nextLine();
                soma = new int[6];
                

                switch (opcao1) {
                    case "g":
                        for (Jogador p : players) {//printa a estatistica de todos os jogos general
                            if(p!=null){
                                for (int i=0; i<p.getJogadasRealizadas(); i++){
                                    JogoDados jogoDados = p.getJogoDados(i);
                                    if(jogoDados!=null && jogoDados instanceof JogoGeneral){//POLIMORFISMO
                                        for(int j=0;j<6;j++){
                                            soma[j] += jogoDados.getCont(j);
                                        }
                                        System.out.printf("\n->"+jogoDados.getNomeJogo()+" %d:\n",i+1);
                                    }
                                    
                                }
                                for(int j=0;j<6;j++){
                                    System.out.printf("Face "+(j+1)+": %d\n",soma[j]);
                                }
                                for(int k=0;k<6;k++){//zera o vetor soma
                                    soma[k] = 0;
                                }
                            }
                            
                        }
                        
                        break;

                    case "a":
                        for (Jogador p : players) {//printa  a estatistica de todos os jogos azar
                            if(p!=null){
                                for (int i=0; i<p.getJogadasRealizadas(); i++){
                                    JogoDados jogoDados = p.getJogoDados(i);
                                    if(jogoDados!=null && jogoDados instanceof JogoAzar){//POLIMORFISMO
                                        for(int j=0;j<6;j++){
                                            soma[j] += jogoDados.getCont(j);
                                        }
                                        System.out.printf("\n->"+jogoDados.getNomeJogo()+" %d:\n",i+1);
                                    }
                                    
                                }
                                for(int j=0;j<6;j++){
                                    System.out.printf("Face "+(j+1)+": %d\n",soma[j]);
                                }
                                for(int k=0;k<6;k++){//zera o vetor soma
                                    soma[k] = 0;
                                }
                            }
                            
                        }
                        
                    break;
            
                default:
                    System.out.println("Opção inválida! Tente outra!");
                    break;
                }

                break;

            case "c":
                int[] somaJA = new int[6];
                int[] somaJG = new int[6];
                for (Jogador p : players) {//printa a estatistica de todos os jogos general
                    if(p!=null){
                        for (int i=0; i<p.getJogadasRealizadas(); i++){
                            JogoDados jogoDados = p.getJogoDados(i);
                            if(jogoDados!=null && jogoDados instanceof JogoGeneral){//POLIMORFISMO
                                for(int j=0;j<6;j++){
                                    somaJG[j] += jogoDados.getCont(j);
                                }
                            }
                            else if(jogoDados!=null && jogoDados instanceof JogoAzar){//POLIMORFISMO
                                for(int j=0;j<6;j++){
                                    somaJA[j] += jogoDados.getCont(j);
                                }
                            }
                            
                        }
                        
                    }
                    
                }
                System.out.printf("\n->Jogo General:\n");
                for(int j=0;j<6;j++){
                    System.out.printf("Face "+(j+1)+": %d\n",somaJG[j]);
                }
                System.out.printf("\n->Jogo Azar:\n");
                for(int j=0;j<6;j++){
                    System.out.printf("Face "+(j+1)+": %d\n",somaJA[j]);
                }
                for(int k=0;k<6;k++){//zera o vetor soma
                    somaJA[k] = 0;
                    somaJG[k] = 0;
                }

                break;
                case "d":
                    soma = new int[6];
                    for (Jogador p : players) {//printa a estatistica de todos os jogos general
                        if(p!=null){
                            for (int i=0; i<p.getJogadasRealizadas(); i++){
                                JogoDados jogoDados = p.getJogoDados(i);
                                for(int j=0;j<6;j++){
                                    soma[j] += jogoDados.getCont(j);
                                }
                            }
                            
                        }
                        
                    }

                    System.out.println("Total do Campeonato:");
                    for(int j=0;j<6;j++){
                        System.out.printf("Face "+(j+1)+": %d\n",soma[j]);
                    }
                    for(int k=0;k<6;k++){//zera o vetor soma
                        soma[k] = 0;
                    }
                    break;
                
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