if(jogo==1){
                    double valorAposta=0;
                    do{
                        if (players[i] instanceof Humano){
                            System.out.println("Qual o valor que deseja apostar? ");//pede o valor da aposta
                            valorAposta = teclado.nextDouble();

                            if(valorAposta==0){
                            System.out.println("Aposte algum valor!");
                        }
                        }
                        else if(players[i] instanceof Maquina){
                            maquina=(Maquina) players[i];
                            valorAposta= maquina.quantoApostar();//para ver quanto a maquina aposta
                            System.out.println("Valor apostado pela máquina: R$"+valorAposta);//mostra o valor que a máquina apostou
                        }
                        
                    }while(valorAposta==0);
                    
                    // jogoGeneral = new JogoGeneral(valorAposta);
                    JogoDados jg =new JogoGeneral(valorAposta);//outro indice
                    players[i].setJogoDados(jg, players[i].getJogadasRealizadas());

                    //JogoGeneral jogoGeneral = (JogoGeneral) players[i].getJogoDados(players[i].getJogadasRealizadas()); //conversão pra jogo general

                    if (players[i] instanceof Humano){
                        //    humano=(Humano) players[i];
                        //    humano.setJogoGeneral(jogoGeneral);
                        players[i].iniciarJogoGeneral(i, players[i]);
                        //players[i].getJogoDados(jogoGeneral).
                        // iniciarJogoGeneral(i);
                        mostrarCartela();
                        i++; //passa pro outra casa do vetor
                    }
                    
                    else if(players[i] instanceof Maquina){
                        // maquina=(Maquina) players[i];
                        // maquina.setJogoGeneral(jogoGeneral);
                       // jogoGeneral.iniciarJogoGeneral(i);
                       players[i].iniciarJogoGeneral(i, players[i]);
                        mostrarCartela();
                        i++;
                    }
                    // players[i].jogoGeneral.iniciarJogoGeneral();
                    // mostrarCartela();

                }
                else if(jogo==2){
                    double valorAposta=0;
                    do{
                        if (players[i] instanceof Humano){
                            System.out.println("Qual o valor que deseja apostar? ");//pede o valor da aposta
                            valorAposta = teclado.nextDouble();

                            if(valorAposta==0){
                            System.out.println("Aposte algum valor!");
                        }
                        }
                        else if(players[i] instanceof Maquina){
                            maquina=(Maquina) players[i];
                            valorAposta= maquina.quantoApostar();//para ver quanto a maquina aposta
                            System.out.println("Valor apostado pela máquina: R$"+valorAposta);//mostra o valor que a máquina apostou
                        }
                    }while(valorAposta==0);
                    
                    JogoDados ja =new JogoAzar(valorAposta);//outro indice
                    players[i].setJogoDados(ja, players[i].getJogadasRealizadas());
                    
                    JogoAzar jogoAzar =(JogoAzar) players[i].getJogoDados(players[i].getJogadasRealizadas()); //converte jogo dados em jogo azar

                    if (players[i] instanceof Humano){
                        //    humano=(Humano) players[i];
                        //    humano.setJogoAzar(jogoAzar);
                    jogoAzar.executarRegrasJogo(i);
                        i++; //passa pro outra casa do vetor
                    }
                    
                    else if(players[i] instanceof Maquina){
                        // maquina.setJogoAzar(jogoAzar);
                        // maquina=(Maquina) players[i];
                    jogoAzar.executarRegrasJogo(i);
                        i++;
                    }
                
                // players[i].jogoAzar.executarRegrasJogo();
                    // i++; //passa pro outra casa do vetor
                }
            }