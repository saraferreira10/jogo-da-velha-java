package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private String jogadorAtual = "X";
    private GridLayout grid = new GridLayout(3, 3);
    private JButton[][] botoes = new JButton[3][3];


    public Main() {
        setTitle("Jogo da Velha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Aplicação do layout
        setLayout(grid);

        // Adição de botões à grade
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int coluna = i;
                final int linha = j;

                botoes[i][j] = new JButton();

                botoes[i][j].setFont(new Font("Arial", Font.BOLD, 20));

                botoes[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (botoes[coluna][linha].getText().isEmpty()) {
                            botoes[coluna][linha].setText(jogadorAtual);
                            if (!verificarVitoria(jogadorAtual.charAt(0))) {
                                if (!verificarEmpate()) {
                                    mudarJogador();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Deu velha!");
                                    resetarJogo();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Jogador " + jogadorAtual + " ganhou!");
                                resetarJogo();
                            }
                        }
                    }
                });

                add(botoes[coluna][linha]);
            }

        }

        setVisible(true);
    }

    public void mudarJogador() {
        jogadorAtual = jogadorAtual.equals("X") ? "O" : "X";
    }

    private boolean verificarVitoria(char jogador) {
        for (int i = 0; i < 3; i++) {
            if (botoes[i][0].getText().equals(Character.toString(jogador)) &&
                    botoes[i][1].getText().equals(Character.toString(jogador)) &&
                    botoes[i][2].getText().equals(Character.toString(jogador))) {
                return true;
            }
            if (botoes[0][i].getText().equals(Character.toString(jogador)) &&
                    botoes[1][i].getText().equals(Character.toString(jogador)) &&
                    botoes[2][i].getText().equals(Character.toString(jogador))) {
                return true;
            }
        }
        if (botoes[0][0].getText().equals(Character.toString(jogador)) &&
                botoes[1][1].getText().equals(Character.toString(jogador)) &&
                botoes[2][2].getText().equals(Character.toString(jogador))) {
            return true;
        }
        if (botoes[0][2].getText().equals(Character.toString(jogador)) &&
                botoes[1][1].getText().equals(Character.toString(jogador)) &&
                botoes[2][0].getText().equals(Character.toString(jogador))) {
            return true;
        }
        return false;
    }

    private boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (botoes[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void resetarJogo() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText("");
            }
        }
    }

    public static void main(String[] args) {
        //TelaInicial  telaInicial = new TelaInicial();
        Main main = new Main();
    }

}