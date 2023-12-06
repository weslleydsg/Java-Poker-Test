package poker;

/**
 *
 * @author Eiji Adachi
 *
 */
public class Carta implements Comparable<Carta> {
	/**
	 * O valor de uma carta é representado internamente como um número inteiro.
	 *
	 * Ás é representado como 1
	 *
	 * Números de 2 a 10 são representados por seus próprios valores.
	 *
	 * Valete é representado como 11
	 *
	 * Dama é representada como 12
	 *
	 * Rei é representado como 13
	 */

	private final Integer valor;

	/**
	 * Podes ser: paus (♣), ouro (♦), copas (♥) or espadas (♠)
	 */
	private final Naipe naipe;

	public Carta(final Integer ranque, final Naipe naipe) {
		this.valor = ranque;
		this.naipe = naipe;
	}

	public int compareTo(final Carta outraCarta) {
		final int comparacao = this.valor.compareTo(outraCarta.getValor());
		if (comparacao == 0) {
			return this.naipe.compareTo(outraCarta.getNaipe());
		} else {
			return comparacao;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Carta outraCarta = (Carta) obj;
		if (valor == null) {
			if (outraCarta.valor != null) {
				return false;
			}
		} else if (!valor.equals(outraCarta.valor)) {
			return false;
		}
		if (naipe != outraCarta.naipe) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		result = prime * result + ((naipe == null) ? 0 : naipe.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Carta [valor=" + valor + ", naipe=" + naipe + "]";
	}

	public Integer getValor() {
		return valor;
	}

	public Naipe getNaipe() {
		return naipe;
	}
}
