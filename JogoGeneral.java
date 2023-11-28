import java.io.Serializable;
public class JogoGeneral extends JogoDados implements Serializable{
    private Dado[] dados;
    private int[] jogadas;
    private Jogador[] players;
    private int i, contJogadores;

    public JogoGeneral( float valorAposta) {
        super(5, "Jogo General", valorAposta);

        dados = new Dado[5];
        jogadas = new int[13];

        for (int i = 0; i < 5; i++) {
            this.dados[i] = new Dado(); // cria os 5 dados
        }
        for (int j = 0; j < 13; j++) {
            this.jogadas[j] = -1; // -1 marca que a jogada em questao ainda nao foi realizada.
        }
    }

    public void iniciarJogoGeneral(){
        for(i = 0; i < contJogadores; i++) { // para iniciar ou resetar as jogadas e poder começar o campeonato novamente jogo general
            for(int j = 0; j < 13; j++){
                players[i].getJogo().setJogadas(j, -1);
            }
        }
         
        //fazer um if else aqui pra escolher o jogo
        for (int j = 0; j < 13; j++) {
            for (i = 0; i < contJogadores; i++) {
                System.out.println(">>Rolando dados para " + players[i].getNome());
                System.out.print("Valores obtidos: ");// imprime sem pular a linha pros dados ficarem do lado
                players[i].getJogo().rolarDados();
                players[i].getJogo().mostrarDados();
                int opcao = 0;

                if(players[i] instanceof Humano){
                    Humano humano = (Humano) players[i];
                    humano.escolherJogada();
                }
                else if(players[i] instanceof Maquina){
                    Maquina maquina = (Maquina) players[i];
                    maquina.aplicarEstrategia();
                }

                //PARTE DO JOGADOR hUMANO
                //if (players[i].getTipoJogador().equals("H")|| players[i].getTipoJogador().equals("h")) {
                    
                    
                    // opcao=0;
                    // do {
                    //     System.out.println("Para qual jogada deseja marcar: [1-13] " + players[i].getNome() + "?");
                    //     System.out.println("1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)");
                    //     players[i].mostrarJogadasExecutadas();

                    //     while(opcao<=0|| opcao>13 || players[i].getJogo().getJogadas(opcao-1)!=-1){//caso o usuário tente escolher uma opcao inexistente ou alguma jogada já feita de novo
                    //         opcao = teclado.nextInt();
                    //         teclado.nextLine(); // Limpar o buffer de entrada após a leitura do inteiro
                    //         if(opcao<=0||opcao>13 || players[i].getJogo().getJogadas(opcao-1)!=-1){
                    //             System.out.println("Jogada inválida, escolha outra.");
                    //         }
                    //     }
                    //     if (players[i].getJogo().getJogadas(opcao-1)==-1) { //se a jogada ainda nao tiver sido feita
                    //         this.players[i].getJogo().setJogadas(opcao-1, this.players[i].getJogo().pontuarJogada(opcao));
                    //     } 
                    // } while (players[i].getJogo().getJogadas(opcao-1)==-1);INS


                //}
                else if (players[i].getTipoJogador().equals("M")|| players[i].getTipoJogador().equals("m")){   // Se for do tipo máquina irá escolher a melhor jogada
                    // opcao=0;
                    // int melhorPontuacao = 0;
                    // int melhorJogada=0;
                    //     while(opcao<13){//basicamente vai ver para aquela rodada qual vai ser a jogada com maior pontuação
                    //     if(this.players[i].getJogo().getJogadas(opcao)==-1){//se já não for ocupada a jogada
                    //         int pontuacao = this.players[i].getJogo().pontuarJogada(opcao+1);
                            
                    //         if(pontuacao>=melhorPontuacao){//serve p achar a melhor jogada mas vai acabar preenchendo todas as outras do vetor jogadas tbm
                    //             melhorPontuacao=pontuacao;
                    //             melhorJogada=opcao;
                    //             vet[melhorJogada] = 1;//se a jogada já tiver sido usada anteriormente é marcada como 1;
                    //         }
                            
                    //     }
                       
                    //     opcao++;
                    // }
                    
                    // for (int k=0; k<13; k++){
                    //     if(this.players[i].getJogo().getJogadas(k)!= melhorPontuacao && vet[k]!=1){
                    //         this.players[i].getJogo().setJogadas(k, -1); //resolve o problema de preenchimento de outras jogadas
                    //     }
                    // }  
                    // this.players[i].getJogo().setJogadas(melhorJogada, melhorPontuacao);//pontua para a máquina             
                    // System.out.println("1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)");
                    // players[i].mostrarJogadasExecutadas();
                    // System.out.println("Jogada que a maquina escolheu: "+ (melhorJogada+1));//retorna a jogada feita pela maquina melhorjogada(posição do vet)+1(pra ficar o "nome" da jogada certinho)
                }
            }
        }
    }

    }

    public void rolarDados(int nDados) { // resultados dos 5 dados
        for (int i = 0; i < nDados; i++) {
            this.dados[i].roll(numFaces);
        }
    }

    public int getJogadas(int i){//pega as jogadas já feitas do vetor jogadas
        return this.jogadas[i];
    }

    public void setJogadas(int i, int x) {//vai colocar a pontuação das jogadas
        jogadas[i] = x;
    }
    
    public String toString() { //transforma o array de dados em uma string
        String result = "Valores obtidos: ";
    
        for (int i = 0; i < 5; i++) {
            result += dados[i].toString() ;
            if (i < 5 - 1) {
                result += "-"; // Adiciona um traço entre os elementos, exceto no último
            }
        }
        return result;
    }

    @Override
    public int[] somarFacesSorteadas(Dado[] dados) {
        return super.somarFacesSorteadas(dados);
    }

    public Boolean validarJogada(int njogada) { // vai verificar se a jogada escolhida é valida retorna
        int i = 0;

        if (njogada == 1) {
            if (jogadas[0] != -1) { //verifica se dentro das jogadas feitas essa já n está preenchida
                System.out.println("Jogada já utilizada.");
                return false;
            }
            do {
                if (this.dados[i].getSideUp() == 1) {
                    return true; // certo
                }
                i++;
            } while (i != 5);
        } 
        else if (njogada == 2) {
            if(jogadas[1]!=-1){
                System.out.println("Jogada já utilizada.");
                return false;
              }
            do {
                if (this.dados[i].getSideUp() == 2) {
                    return true; // certo
                }
                i++;
            } while (i != 5);
        } 
        else if (njogada == 3) {
            if(jogadas[2]!=-1){
                System.out.println("Jogada já utilizada.");
                return false;
              }
            do {
                if (this.dados[i].getSideUp() == 3) {
                    return true; // certo
                }
                i++;
            } while (i != 5);
        } 
        else if (njogada == 4) {
            if(jogadas[3]!=-1){
                System.out.println("Jogada já utilizada.");
                return false;
              }
            do {
                if (this.dados[i].getSideUp() == 4) {
                    return true; // certo
                }
                i++;
            } while (i != 5);
        } 
        else if (njogada == 5) {
            if(jogadas[4]!=-1){
                System.out.println("Jogada já utilizada.");
                return false;
              }
            do {
                if (this.dados[i].getSideUp() == 5) {
                    return true; // certo
                }
                i++;
            } while (i != 5);
        } 
        else if (njogada == 6) {
            if(jogadas[5]!=-1){
                System.out.println("Jogada já utilizada.");
                return false;
              }
            do {
                if (this.dados[i].getSideUp() == 6) {
                    return true; // certo
                }
                i++;
            } while (i != 5);
        } 
        else if (njogada == 7) { //trinca
            if(jogadas[6]!=-1){
                System.out.println("Jogada já utilizada.");
                return false;
              }
            if(contador(3)){
                return true;
            }
            else{
                return false;
            }
        }
        else if (njogada == 8) { //quadrupla
            if(jogadas[7]!=-1){
                System.out.println("Jogada já utilizada.");
                return false;
              }
            
            if(contador(4)){
                return true;
            }
            else{
                return false;
            }

        }
        else if(njogada==9){//FULL HOUSE
            if (jogadas[8] != -1) { // Verifica se a jogada já foi usada
                System.out.println("Jogada já utilizada.");
                return false;
            }

            int[] vet={0,0,0,0,0,0};//vetor para representar a quantidade que cada face do dado saiu

            for(i=0; i<5; i++){
                vet[this.dados[i].getSideUp()-1] += 1; //pega o valor da face e coloca ela na "casa" correspondente do vetor vet[0]=face 1
            }

            boolean trinca=false;//identificadores de trinca e dupla
            boolean dupla=false;//pra ser full house tem que ter uma dupla e uma trinca
            for(i=0; i<6; i++){
                if(vet[i]==2){
                    dupla=true;
                }
                if(vet[i]==3){
                    trinca=true;
                }
            }

            if(trinca==true && dupla==true){
                return true;
            }
            else{
                return false; 
            }
        }
        else if(njogada==10){//Sequencia alta
            if(jogadas[9]!=-1){
                System.out.println("Jogada já utilizada.");
                return false;
              }
            int vet[]={2,3,4,5,6};//vetor como a jogada de sequencia alta deve ser
            ordenarDados();//ordenar vetor de dados
            for(i=0; i<5; i++){
                if(this.dados[i].getSideUp()!=vet[i]){
                    return false;
                }
            }
            return true; //se passar pelo for e o primeiro if sem problemas
        }
        else if(njogada==11){//Sequencia Baixa
            if(jogadas[10]!=-1){
                System.out.println("Jogada já utilizada.");
                return false;
              }
            int[] vet={1,2,3,4,5};//vetor como a jogada de sequencia baixa deve ser
            ordenarDados();//ordenar vetor de dados 
            for(i=0; i<5; i++){
                if(this.dados[i].getSideUp()!=vet[i]){
                    return false;
                }
            }
            return true; //se passar pelo for e o primeiro if sem problemas
        }
        else if(njogada==12){//General
            if(jogadas[11]!=-1){
                System.out.println("Jogada já utilizada.");
                return false;
              }
            
            if(contador(5)){
                return true;
            }
            else{
                return false;
            }

        }
        else if(njogada==13){//jogada aleatória
            if(jogadas[11]!=-1){
                System.out.println("Jogada ja usada");
                return false;
            }
            else {
                return true;
            }

        }
        return false;

    }

    public int pontuarJogada(int njogada) {
             
            int i = 0;
            int cont = 0;
            if (njogada == 1 || njogada==2 || njogada==3 || njogada==4 || njogada==5 || njogada==6 ) { //dá pra juntar no mesmo if jogadas 1 2 3 4 5 6 pq todos os resultados delas são as somas
                if(validarJogada(njogada)==false){ //se a jogada escolhida não for valida ela é zerada
                    return 0;
                }
                do {
                    if (dados[i].getSideUp() == njogada) {
                        cont += dados[i].getSideUp();
                    }
                    i++;
                } while (i != 5);
                return cont;//retorna a pontuação 
            } 
            else if (njogada == 7 ) { // trinca 
                if(validarJogada(njogada)==false){ //se a jogada escolhida não for valida ela é zerada
                    return 0;
                }
                do {
                    cont += dados[i].getSideUp();
                    i++;
                } while (i != 5);

                return cont; //retorna a pontuação 
            } 
            else if (njogada == 8) {// quadra
                if(validarJogada(njogada)==false){ //se a jogada escolhida não for valida ela é zerada
                    return 0;
                }
                do {
                    cont += dados[i].getSideUp();
                    i++;
                } while (i != 5);

                return cont;//retorna a pontuação 
            } 
            else if (njogada == 9) {// full hand
                if(validarJogada(njogada)==false){ //se a jogada escolhida não for valida ela é zerada
                    return 0;
                }
                return 25;//retorna a pontuação 
            } 
            else if (njogada == 10) {//sequencia alta
                if(validarJogada(njogada)==false){ //se a jogada escolhida não for valida ela é zerada
                    return 0;
                }
                return 30;//retorna a pontuação 
            } 
            else if (njogada == 11) {//sequencia baixa
                if(validarJogada(njogada)==false){ //se a jogada escolhida não for valida ela é zerada
                    return 0;
                }
                return 40;//retorna a pontuação 
            } 
            else if (njogada == 12) { //general
                if(validarJogada(njogada)==false){ //se a jogada escolhida não for valida ela é zerada
                    return 0;
                }
                return 50; //retorna a pontuação 
            } 
            else if (njogada == 13 ) { // aleatoria
                if(validarJogada(njogada)==false){ //se a jogada escolhida não for valida ela é zerada
                    return 0;
                }
                do {
                    cont += dados[i].getSideUp();
                    i++;
                } while (i != 5);

                return cont; //retorna a pontuação 
            } 
            else{
                System.out.println("Essa jogada não exite");
            }

        return -1; // quer dizer que ta fora do tamanho definido
        
    }

    public Boolean contador (int x){ // recebe a quantidade que deve ser repetido (por ex da quadra x=4)
        boolean resultado = false;
        int posicao, cont=0;
        

        for (int j=0; j<5; j++){
            cont=0;
            posicao=this.dados[j].getSideUp();

                    for (int i=0; i<5; i++){
                        if(posicao==this.dados[i].getSideUp()){
                            cont++;
                        }
                    }   
            if (cont>=x){
                resultado=true;
            }     
        }
        if(resultado==true){
            return true;
        }
        else{
            return false;
        }
    }

    public void ordenarDados() { //função para ordenar os dados para algumas jogadas
        int x;

        for (int i = 0; i < 5 - 1; i++) {
            for (int j = 0; j < 5 - i - 1; j++)
                if (dados[j].getSideUp() > dados[j + 1].getSideUp()) {
                    x = dados[j].getSideUp();
                    dados[j].setSideUp(dados[j + 1].getSideUp());
                    dados[j + 1].setSideUp(x);
                }
        }
    }

    public void mostrarDados (){
        for (int i = 0; i < 5; i++) {
            System.out.print(dados[i].toString()+" ") ;
        }    
        System.out.print("\n");
    }

    public boolean resultadoJogoGeneral(){
        int soma = 0;
        for (int i = 0 ; i < 12 ; i++) { 
			if(this.getJogadas(i) !=-1) {
				soma = this.getJogadas(i); 
			} 
		}
        if(soma>(2*this.getJogadas(13))){
            return true;
        }
        return false;
    }
}
