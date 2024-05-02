package br.com.joao.cm.visao;

import javax.swing.JFrame;

import br.com.joao.cm.modelo.Tabuleiro;

public class TelaPrincipal extends JFrame{

	public TelaPrincipal() {
		Tabuleiro tabuleiro=new Tabuleiro(16,30,50);
 	    setTitle("Campo Minado");
		PainelTabuleiro paineltabuleiro=new PainelTabuleiro(tabuleiro)	;
		add(paineltabuleiro);
		setSize(690,438);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		new TelaPrincipal();
	}
}



