# TrabalhoPOOCampeonatoDeApostasDeJogoDeDados

Os colaboradores podem clonar o repositório em suas máquinas locais usando o comando git clone e, em seguida, colaborar no código.
Eles podem fazer push das alterações para o repositório remoto usando git push, e todas as alterações serão registradas no controle de versão do GitHub.
Você pode revisar as solicitações de pull (pull requests) feitas pelos colaboradores para mesclar suas alterações no código principal.

# Introdução

Desenvolva um simulador de campeonato de apostas em jogos de dados que permita aos jogadores presentes realizar apostas em dois tipos de jogos de dados, o jogo de azar e o jogo general.

## Regras Gerais:

(1) O campeonato permitirá no máximo dez jogadores (humano/máquina) e cada jogador poderá realizar até dez apostas nos jogos de sua escolha, enquanto houver saldo suficiente para tal.

(2) Para a execução de uma rodada de apostas é necessário ao menos um jogador participante, com saldo disponível (maior que R$ 0,00) para realização de aposta (até no máximo dez)
em jogos de sua escolha (um jogo por aposta).

(3) A cada rodada do campeonato, será perguntado a cada jogador sobre o valor a ser apostado e para qual jogo. Na sequência será realizada a rodada do jogo e a vez será passada ao
próximo jogador.

(4) Para cada jogador serão armazenados os resultados de até dez jogos realizados (do tipo general ou de azar) com seus respectivos valores de aposta.
    – O resultado do jogo de azar compreende se o jogador ganhou ou perdeu determinada jogada, de acordo com as regras do jogo.
    – O resultado do jogo general compreende os valores obtidos para as 13 jogadas. O jogador ganha a aposta se a soma dos valores das jogadas de 1 a 12 for maior que o dobro do valor obtido na jogada 13 (aleatória).

(5) Quando o jogador é inserido no campeonato, o mesmo inicia com o saldo de R$ 100,00. No início de cada rodada, o jogador indicará o quanto deseja apostar. Se o jogador ganhar, este
receberá a mesma quantia que apostou, caso contrário ele perderá a quantia que apostou, por exemplo:
    – O jogador inicialmente tinha R$ 100,00, apostou R$ 10,00 e ganhou. Então o novo saldo do jogador é de R$ 110,00. Em um segundo momento o jogador se sentiu confiante e apostou R$ 50,00 e perdeu, logo seu saldo ficou em R$ 60,00;

(6) Poderão ser executadas n rodadas de apostas desde que sejam cumpridas os condições para cada jogador (saldo suficiente e não ultrapassar mais que dez apostas).

(7) Em cada rodada, para cada jogo escolhido, deverão ser contabilizadas a quantidade que cada face de cada dado já fora sorteada.

(8) A aplicação deverá produzir diferentes relatórios de saldos, extratos de resultado dos jogos e estatística das faces sorteadas, por tipo de jogador, por tipo de jogo, por rodadas e por total de campeonato.


## Regras do Jogo de Azar:

Um jogo de azar faz uso de dois dados e possui a seguinte regra: O jogador lança os dois dados:

(1) Se a soma das faces dos dados for 7 ou 11 o jogador ganha;

(2) Se a soma for 2, 3 ou 12 o jogador perde;

(3) Se a soma obtida no primeiro lançamento de dados não for qualquer um dos valores acima, esta soma será tratada como o valor a ser buscado pelo jogador nos lan¸camentos subsequentes, ou seja, o jogador só irá ganhar se ele conseguir novamente atingir a soma obtida com o primeiro lançamento.

## Regras do Jogo General:

(1) Sendo 13 o número de jogadas possíveis e 13 o número máximo de linhas para cada coluna na cartela de marcaçã, uma rodada consiste de 13 jogadas para cada jogador.

(2) Cada jogador (humano ou máquina), em sua vez, tem apenas uma chance de arremessar os dados.

(3) O resultado obtido ao final do arremesso deve ser classificado, pelo próprio jogador, como uma das seguintes 13 possibilidades:

-Jogada de 1: um certo número de dados (de 0 a 5) marcando o número 1; sendo que a jogada vale mais pontos conforme a quantidade de dados que marcarem o número 1. Por exemplo: 1-1-1-4-5 vale 3 pontos.

-Jogadas de 2, 3, 4, 5 e 6: correspondentes à jogada de 1 para os demais números. Por exemplo: 3-3-4-4-5 vale 6 pontos se for considerada uma jogada de 3; ou 8 pontos se for considerada uma jogada de 4; ou ainda 5 pontos se for uma jogada de 5.

-Trinca (T): três dados marcando o mesmo número. Vale a soma dos 5 dados. Exemplo: 4-4-4-5-6 vale 23 pontos.

-Quadra (Q): quatro dados marcando o mesmo n´umero. Vale a soma dos 5 dados. Exemplo: 1-5-5-5-5 vale 21 pontos.

-Full-hand (F) ou Full-house: uma trinca e um par (exemplo: 2-2-2-6-6). Vale 25 pontos para qualquer combinação.

-Sequência alta (S+): 2-3-4-5-6. Vale 30 pontos.

-Sequência baixa (S-): 1-2-3-4-5. Vale 40 pontos.

-General (G): cinco dados marcando o mesmo n´umero (por exemplo: 4-4-4-4-4). Vale 50 pontos.

-Jogada aleatória (X) : qualquer combinação. Vale a soma dos 5 dados. Por exemplo: 1-4-4-5-6 vale 20 pontos.

(4) O resultado deverá ser mostrado na forma de cartela, na coluna do jogador e na linha correspondente à jogada. Aquela linha (e portanto aquela jogada) não poderá mais ser utilizada pelo jogador na mesma rodada.

(5) Se um determinado resultado não cumprir os requisitos para a jogada escolhida, o jogador zera a respectiva jogada. E ainda, se um determinado resultado não puder ser classificado como nenhuma das jogadas ainda restantes para aquele jogador, ele deverá escolher qual das jogadas restantes será descartada, marcando 0 (zero) para a jogada correspondente.

(6) Ao final de 13 rodadas, com a cartela toda preenchida, somam-se os valores de cada coluna, e o jogador que obtiver mais pontos será considerado o vencedor

