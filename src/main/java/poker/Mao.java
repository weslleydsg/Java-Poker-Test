package poker;

import java.util.Collections;
import java.util.List;

/**
 * 
 */
public class Mao {
	private List<Carta> cartas;

	public Mao(List<Carta> cartas) {
		this.setCartas(cartas);
	}

	public RankingMao ranking() {
		Collections.sort(getCartas());
		if (isRoyalFlush()) {
			return RankingMao.ROYAL_STRAIGHT_FLUSH;
		}
		if (isStraightFlush()) {
			return RankingMao.STRAIGHT_FLUSH;
		}
		if (isQuadra()) {
			return RankingMao.QUADRA;
		}
		if (isFullHouse()) {
			return RankingMao.FULL_HOUSE;
		}
		if (isFlush()) {
			return RankingMao.FLUSH;
		}
		if (isSequencia()) {
			return RankingMao.SEQUENCIA;
		}
		if (isTrinca()) {
			return RankingMao.TRINCA;
		}
		if (isDoisPares()) {
			return RankingMao.DOIS_PARES;
		}
		if (isUmPar()) {
			return RankingMao.UM_PAR;
		}
		return RankingMao.NADA;
	}

	private boolean isRoyalFlush() {
		if (!isStraightFlush()) {
			return false;
		}
		return cartas.get(0).getValor() == 1;
	}

	private boolean isStraightFlush() {
		return isFlush() && isSequencia();
	}

	private boolean isQuadra() {
		Carta carta1 = buscaSeguraCarta(0);
		Carta carta4 = buscaSeguraCarta(3);
		if (carta4 != null && carta1.getValor() == carta4.getValor()) {
			return true;
		}
		Carta carta2 = buscaSeguraCarta(1);
		Carta carta5 = buscaSeguraCarta(4);
		if (carta5 != null && carta2.getValor() == carta5.getValor()) {
			return true;
		}
		return false;
	}

	private boolean isFullHouse() {
		return isTrinca() && isUmPar();
	}

	private boolean isFlush() {
		final Naipe naipe = cartas.get(0).getNaipe();
		for (final Carta carta : cartas) {
			if (carta.getNaipe() != naipe) {
				return false;
			}
		}
		return true;
	}

	private boolean isSequencia() {
		for (int i = 1; i < cartas.size(); i++) {
			Integer valorCartaAtual = cartas.get(i).getValor();
			Integer valorCartaAnterior = cartas.get(i - 1).getValor();
			if (!(valorCartaAnterior == 1 && valorCartaAtual == 10)
					&& valorCartaAtual != valorCartaAnterior + 1) {
				return false;
			}
		}
		return true;
	}

	private boolean isTrinca() {
		int count = 0;
		for (int i = 0; i < cartas.size() - 2; i++) {
			if (cartas.get(i).getValor() == cartas.get(i + 1).getValor()
					&& cartas.get(i).getValor() == cartas.get(i + 2).getValor()) {
				count++;
			}
		}
		return count == 1;
	}

	private boolean isDoisPares() {
		return contadorPares() == 2;
	}

	private boolean isUmPar() {
		return contadorPares() == 1;
	}

	private Integer contadorPares() {
		int contador = 0;
		for (int index = 0; index < cartas.size() - 1; index++) {
			Integer valorCartaAtual = cartas.get(index).getValor();
			Integer valorCartaProxima = cartas.get(index + 1).getValor();
			if (valorCartaAtual != valorCartaProxima) {
				continue;
			}
			Carta cartaAuxiliarAnterior = buscaSeguraCarta(index - 1);
			if (cartaAuxiliarAnterior == null || valorCartaAtual != cartaAuxiliarAnterior.getValor()) {
				Carta cartaAuxiliarProxima = buscaSeguraCarta(index + 2);
				if (cartaAuxiliarProxima == null || valorCartaAtual != cartaAuxiliarProxima.getValor()) {
					contador++;
				}
			}
		}
		return contador;
	}

	private Carta buscaSeguraCarta(Integer index) {
		if (index < 0 || index >= cartas.size()) {
			return null;
		}
		return cartas.get(index);
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}
}
