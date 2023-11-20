public class Maquina extends Jogador implements JogarComoMaquina {
    private Jogador players = new Jogador();
    private JogoDados jDados = new JogoDados();

    public Maquina(String nome, String tipoJogador){
        super(nome, tipoJogador);
    }

    @Override
    public int aplicarEstrategia() {
        if (players[i].getTipoJogador().equals("M")|| players[i].getTipoJogador().equals("m") && jDados.nomeJogo){   // Se for do tipo máquina irá escolher a melhor jogada
            opcao=0;
            int melhorPontuacao = 0;
            int melhorJogada=0;
                while(opcao<13){//basicamente vai ver para aquela rodada qual vai ser a jogada com maior pontuação
                if(this.players[i].getJogo().getJogadas(opcao)==-1){//se já não for ocupada a jogada
                    int pontuacao = this.players[i].getJogo().pontuarJogada(opcao+1);
                    
                    if(pontuacao>=melhorPontuacao){//serve p achar a melhor jogada mas vai acabar preenchendo todas as outras do vetor jogadas tbm
                        melhorPontuacao=pontuacao;
                        melhorJogada=opcao;
                        vet[melhorJogada] = 1;//se a jogada já tiver sido usada anteriormente é marcada como 1;
                    }
                    
                }
               
                opcao++;
            }
            
            for (int k=0; k<13; k++){
                if(this.players[i].getJogo().getJogadas(k)!= melhorPontuacao && vet[k]!=1){
                    this.players[i].getJogo().setJogadas(k, -1); //resolve o problema de preenchimento de outras jogadas
                }
            }  
            this.players[i].getJogo().setJogadas(melhorJogada, melhorPontuacao);//pontua para a máquina             
            System.out.println("1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)");
            players[i].mostrarJogadasExecutadas();
            System.out.println("Jogada que a maquina escolheu: "+ (melhorJogada+1));//retorna a jogada feita pela maquina melhorjogada(posição do vet)+1(pra ficar o "nome" da jogada certinho)
        }
        return 0;
    }
}
