package com.unifap;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Loja {
    private Jogos[] jogos = new Jogos[5];
    private Scanner scan = new Scanner(System.in);
    private int numero;

    public Loja(Cliente c) {
        iniciarJogos();
        iniciarLoja(c);
    }

    private void iniciarLoja(Cliente c) {
        do {
            System.out.println("Olá, bem-vindo à loja BitBuy. O quê deseja fazer?");
            System.out.println(
                            "1. Fazer Login\n" +
                            "2. Sair");

            try {
                numero = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opção Inválida");
                break;
            }


            switch (numero) {
                case 1:
                    loginAprovado();
                    do {
                        System.out.println("O quê deseja fazer?");
                        System.out.println(
                                        "1. Ver lista de jogos\n" +
                                        "2. Checar carteira\n" +
                                        "3. Voltar");

                        try {
                            numero = scan.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Opção Inválida");
                            break;
                        }

                        switch (numero) {
                            case 1:
                                imprimirJogos();

                                System.out.println("O quê deseja fazer?");
                                System.out.println(
                                                "1. Comprar\n" +
                                                "2. Voltar");

                                    try {
                                        numero = scan.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.out.println("Opção Inválida");
                                        break;
                                    }

                                    switch (numero) {
                                        case 1:
                                            comprar(c);
                                            break;
                                        case 2:
                                            break;
                                        default:
                                            System.out.println("VALOR INVÁLIDO");
                                            break;
                                    }

                                break;
                            case 2:
                                checarCarteira(c);
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("VALOR INVÁLIDO");
                                break;
                        }
                        System.out.println();
                    } while (numero != 3);
                    break;
                case 2:
                    System.out.println();
                    System.out.println("VOCÊ SAIU DA LOJA");
                    break;
                default:
                    System.out.println("VALOR INVÁLIDO");
                    System.out.println();
                    break;
            }
        } while (numero != 2);
    }

    private void loginAprovado() {
        System.out.println();
        System.out.println("LOGIN FEITO COM SUCESSO");
        System.out.println();
    }

    private void checarCarteira(Cliente c) {
        System.out.println();
        System.out.println("Saldo na carteira = R$" + String.format("%.2f", c.getSaldo()));
    }

    private void iniciarJogos() {
        jogos[0] = new Jogos("Resident Evil 2", 89.99);
        jogos[1] = new Jogos("Stardew Valley", 24.99);
        jogos[2] = new Jogos("Hollow Knight", 27.99);
        jogos[3] = new Jogos("Cup Head", 36.99);
        jogos[4] = new Jogos("Cities: Skylines", 55.99);
    }

    private void imprimirJogos() {
        System.out.println("______LISTA DE JOGOS______");

        for (int i = 0; i < jogos.length; i++) {
            System.out.println((i+1) + ". " + jogos[i].getNome() + " - R$" + String.format("%.2f", jogos[i].getPreco()));
        }

        System.out.println("__________________________");
        System.out.println();
    }

    private void comprar(Cliente c) {
        System.out.println();
        System.out.println("Digite o número do jogo desejado: ");
        int aux;
        Jogos jogoAtual = null;

        try {
            aux = scan.nextInt();

            for (int i = 0; i < jogos.length; i++) {
                if(aux == i + 1) {
                    jogoAtual = jogos[i];
                }
            }

            assert jogoAtual != null;

            if(jogoAtual.getPreco() != 0) {
                try {
                    c.compra(jogoAtual);

                    System.out.println();
                    System.out.println(jogoAtual.getNome() + " comprado com sucesso!");
                    System.out.println("Saldo restante = R$" + String.format("%.2f", c.getSaldo()));

                    jogoAtual.setNome("Não Disponível");
                    jogoAtual.setPreco(0);
                } catch (IllegalArgumentException e) {
                    System.out.println("SALDO INSUFICIENTE");
                }
            } else {
                System.out.println("JOGO INDISPONÍVEL");
            }
        } catch (NullPointerException e) {
            System.out.println("VALOR INVÁLIDO");
        } catch (InputMismatchException e) {
            System.out.println("NÃO É UM NÚMERO");
        }
    }
}
