import java.util.Scanner;
public class UsaCampeonato {
       
    public static void main(String[] args){
        Campeonato campeonato = new Campeonato();
        Scanner teclado = new Scanner (System.in);
        String opcao;
        boolean saida=false;

        do{
            System.out.println ("..:: Menu interativo :..");
            System.out.println ("(a) Para incluir um jogador ");
            System.out.println ("(b) Para excluir um jogador (pelo nome)" );
            System.out.println ("(c) Executar rodada");
            System.out.println ("(d) Mostrar a cartela de resulatdos [da última jogada realizada]");
            System.out.println ("(e) Gravar os dados do campeonato em arquivo");
            System.out.println ("(f) Ler os dados do campeonato em arquivo");
            System.out.println ("(g) Sair da aplicação");
            System.out.println("Entre com uma opção do menu: ");
            opcao = teclado.nextLine( );

            switch(opcao){
                case "a":
                    campeonato.incluirjogador();
                    break;
                case "b":
                    campeonato.removerJogador();
                    break;
                case "c":
                    campeonato.iniciarCampeonato();
                    break;
                case "d":
                    campeonato.mostrarCartela();
                    break;
                case "e":
                    campeonato.gravarEmArquivo();
                    break;
                case "f":
                    campeonato.lerDoArquivo();
                    break;
                case "g":
                    System.out.println ("Saindo");
                    saida=true;
                    break;
                default :
                    System.out.println ("Opcao invalida. Tente novamente");
            }
        }while(saida==false);
        teclado.close();
    }
 }

 