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
            System.out.println ("(d) Imprimir saldos");
            System.out.println ("(e) Imprimir extratos dos resultados");
            System.out.println ("(f) Imprimir estatísticas");
            System.out.println ("(g) Gravar os dados do campeonato em arquivo");
            System.out.println ("(h) Ler os dados do campeonato em arquivo");
            System.out.println ("(i) Sair da aplicação");
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
                    campeonato.mostrarSaldo();
                    break;
                case "e":
                    campeonato.mostrarExtratos();
                    break;
                case "f":
                    campeonato.mostrarEstatistica();
                    break;
                case "g":
                    campeonato.gravarEmArquivo();
                    break;
                case "h":
                    campeonato.lerDoArquivo();
                    break;
                case "i":
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

 