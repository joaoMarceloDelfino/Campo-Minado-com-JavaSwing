package br.com.joao.cm.modelo;

import java.util.ArrayList;
import java.util.List;

 
public class Campo {
	private final int linha;
	private final int coluna;
	private boolean aberto;
	private boolean marcado;
	private boolean minado;
	private List<Campo> vizinhanca = new ArrayList<>();
	private List<CampoObservador>observadores=new ArrayList<>();

	Campo(int linha, int coluna) {

		this.linha = linha;
		this.coluna = coluna;
	}
	public void registrarObservadores(CampoObservador observador) {
		observadores.add(observador);
	}
	public void notificarObservadores(CampoEvento evento) {
		observadores.stream().forEach(o->o.eventoOcorreu(this, evento));
	}

	boolean AdicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaGeral = deltaLinha + deltaColuna;

		if (deltaGeral == 1 && !diagonal) {
			vizinhanca.add(vizinho);
			return true;
		} else if (deltaGeral == 2 && diagonal) {
			vizinhanca.add(vizinho);
			return true;
		}
		return false;
	}

	public void AlternarMarcacao() {
		if (!aberto) {
			marcado = !marcado;
			
			if(marcado) {
				notificarObservadores(CampoEvento.MARCAR);
			}else {
				notificarObservadores(CampoEvento.DESMARCAR);
			}
		}
	}

	public boolean Abrir() {
		if (!aberto && !marcado) {
			aberto = true;
			if (minado) {
				notificarObservadores(CampoEvento.EXPLODIR);
				return true;
 			}
			setAberto(true);
			if (VizinhancaSegura()) {
				vizinhanca.forEach(v -> v.Abrir());

			}
			return true;
		} else {
			return false;
		}

	}

	public boolean VizinhancaSegura() {

		return vizinhanca.stream().noneMatch(v -> v.minado);
	}
	void minar() {
		if(!minado) {
			minado=true;
		}
	}
	public boolean isMarcado() {
		return marcado;
	}
	public boolean isAberto() {
		  return aberto;
	}	
	public int getLinha() {
		return linha;
	}
	public int getColuna() {
		return coluna;
	}
	boolean objetivoAlcancado() {
		boolean desvendado=!minado && aberto;
		boolean protegido= minado && marcado;
		return desvendado || protegido;
	}
	public int minasVizinhanca() {
		return (int) vizinhanca.stream().filter(v->v.minado).count();
 	}
	void reiniciar() {
		aberto=false;
		marcado=false;
		minado=false;
		notificarObservadores(CampoEvento.REINICIAR);

	}
	public boolean isMinado(){
		
		return minado;
	}
	void setAberto(boolean aberto) {
		this.aberto=aberto;
		if(aberto) {
			notificarObservadores(CampoEvento.ABRIR);
		}
	}
  
}
