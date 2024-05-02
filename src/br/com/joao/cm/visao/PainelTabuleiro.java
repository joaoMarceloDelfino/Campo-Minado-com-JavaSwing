package br.com.joao.cm.visao;

import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.joao.cm.modelo.Tabuleiro;

public class PainelTabuleiro extends JPanel {
public PainelTabuleiro(Tabuleiro tabuleiro) {
	setLayout(new GridLayout(tabuleiro.getLinhas(),tabuleiro.getColunas()));
	int total=tabuleiro.getLinhas()*tabuleiro.getColunas();
	 tabuleiro.paraCada(c->add(new BotaoCampo(c)));
 tabuleiro.registrarObservadores(e->{
	SwingUtilities.invokeLater(()->{
		 if(e.isGanhou()) {
				JOptionPane.showMessageDialog(this,"Ganhou"); 
			 }
			 else 	{
					JOptionPane.showMessageDialog(this,"Perdeu"); 

			 }
		 tabuleiro.reiniciar();
	});
	 
 });
 
}
}