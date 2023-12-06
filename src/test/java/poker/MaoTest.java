package poker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MaoTest {
	public class SetupRakingParams {
		private int[] valores;
		private Naipe[] naipes = new Naipe[0];

		public SetupRakingParams(int[] valores) {
			this.valores = valores;
		}

		public int[] getValores() {
			return valores;
		}

		public Naipe[] getNaipes() {
			return naipes;
		}

		public void setNaipes(Naipe[] naipes) {
			this.naipes = naipes;
		}
	}

	private RankingMao setupRaking(SetupRakingParams params) {
		List<Carta> cartas = new ArrayList<>();
		int[] valores = params.valores;
		Naipe[] naipes = params.naipes;

		for (int i = 0; i < valores.length; i++) {
			CartaBuilder cartaBuilder = new CartaBuilder().withValor(valores[i]);
			if (naipes.length != 0) {
				Naipe naipe = naipes.length == 1 ? naipes[0] : naipes[i];
				cartaBuilder.withNaipe(naipe);
			}

			cartas.add(cartaBuilder.build());
		}

		Mao mao = new Mao(cartas);
		RankingMao ranking = mao.ranking();

		return ranking;
	}

	@Test
	public void testRankingRoyalFlush() {
		int[] valores = { 10, 11, 12, 1, 13 };
		RankingMao ranking = setupRaking(new SetupRakingParams(valores));

		assertEquals(RankingMao.ROYAL_STRAIGHT_FLUSH, ranking);
	}

	@Test
	public void testRankingStraightFlush() {
		int[] valores = { 2, 3, 4, 5, 6 };
		RankingMao ranking = setupRaking(new SetupRakingParams(valores));

		assertEquals(RankingMao.STRAIGHT_FLUSH, ranking);
	}

	@Test
	public void testRankingQuadraComeco() {
		int[] valores = { 2, 2, 2, 6, 2 };
		Naipe[] naipes = { Naipe.COPAS, Naipe.OUROS, Naipe.ESPADAS, Naipe.COPAS,
				Naipe.PAUS };
		SetupRakingParams params = new SetupRakingParams(valores);
		params.setNaipes(naipes);
		RankingMao ranking = setupRaking(params);

		assertEquals(RankingMao.QUADRA, ranking);
	}

	@Test
	public void testRankingQuadraFinal() {
		int[] valores = { 2, 6, 6, 6, 6 };
		Naipe[] naipes = { Naipe.ESPADAS, Naipe.COPAS, Naipe.OUROS, Naipe.PAUS,
				Naipe.COPAS };
		SetupRakingParams params = new SetupRakingParams(valores);
		params.setNaipes(naipes);
		RankingMao ranking = setupRaking(params);

		assertEquals(RankingMao.QUADRA, ranking);
	}

	@Test
	public void testRankingFullHouse() {
		int[] valores = { 2, 2, 2, 7, 7 };
		Naipe[] naipes = { Naipe.COPAS, Naipe.OUROS, Naipe.ESPADAS, Naipe.COPAS,
				Naipe.OUROS };
		SetupRakingParams params = new SetupRakingParams(valores);
		params.setNaipes(naipes);
		RankingMao ranking = setupRaking(params);

		assertEquals(RankingMao.FULL_HOUSE, ranking);
	}

	@Test
	public void testRankingFlush() {
		int[] valores = { 2, 4, 6, 8, 10 };
		Naipe[] naipes = { Naipe.COPAS };
		SetupRakingParams params = new SetupRakingParams(valores);
		params.setNaipes(naipes);
		RankingMao ranking = setupRaking(params);

		assertEquals(RankingMao.FLUSH, ranking);
	}

	@Test
	public void testRankingSequencia() {
		int[] valores = { 2, 3, 4, 5, 6 };
		Naipe[] naipes = { Naipe.COPAS, Naipe.OUROS, Naipe.ESPADAS, Naipe.COPAS,
				Naipe.PAUS };
		SetupRakingParams params = new SetupRakingParams(valores);
		params.setNaipes(naipes);
		RankingMao ranking = setupRaking(params);

		assertEquals(RankingMao.SEQUENCIA, ranking);
	}

	@Test
	public void testRankingSequenciaRoyal() {
		int[] valores = { 10, 12, 11, 1, 13 };
		Naipe[] naipes = { Naipe.ESPADAS, Naipe.COPAS, Naipe.ESPADAS, Naipe.ESPADAS,
				Naipe.ESPADAS };
		SetupRakingParams params = new SetupRakingParams(valores);
		params.setNaipes(naipes);
		RankingMao ranking = setupRaking(params);

		assertEquals(RankingMao.SEQUENCIA, ranking);
	}

	@Test
	public void testRankingTrinca() {
		int[] valores = { 2, 7, 4, 7, 7 };
		Naipe[] naipes = { Naipe.COPAS, Naipe.OUROS, Naipe.ESPADAS, Naipe.COPAS,
				Naipe.PAUS };
		SetupRakingParams params = new SetupRakingParams(valores);
		params.setNaipes(naipes);
		RankingMao ranking = setupRaking(params);

		assertEquals(RankingMao.TRINCA, ranking);
	}

	@Test
	public void testRankingDoisPares() {
		int[] valores = { 2, 7, 2, 10, 7 };
		Naipe[] naipes = { Naipe.COPAS, Naipe.OUROS, Naipe.ESPADAS, Naipe.PAUS,
				Naipe.COPAS };
		SetupRakingParams params = new SetupRakingParams(valores);
		params.setNaipes(naipes);
		RankingMao ranking = setupRaking(params);

		assertEquals(RankingMao.DOIS_PARES, ranking);
	}

	@Test
	public void testRankingUmPar() {
		int[] valores = { 2, 7, 10, 1, 10 };
		Naipe[] naipes = { Naipe.COPAS, Naipe.OUROS, Naipe.COPAS, Naipe.ESPADAS,
				Naipe.PAUS };
		SetupRakingParams params = new SetupRakingParams(valores);
		params.setNaipes(naipes);
		RankingMao ranking = setupRaking(params);

		assertEquals(RankingMao.UM_PAR, ranking);
	}

	@Test
	public void testRankingNada() {
		int[] valores = { 2, 4, 7, 9, 10 };
		Naipe[] naipes = { Naipe.COPAS, Naipe.ESPADAS, Naipe.OUROS, Naipe.COPAS,
				Naipe.PAUS };
		SetupRakingParams params = new SetupRakingParams(valores);
		params.setNaipes(naipes);
		RankingMao ranking = setupRaking(params);

		assertEquals(RankingMao.NADA, ranking);
	}

	@Test
	public void testRankingNada3Cartas() {
		int[] valores = { 2, 10, 7 };
		Naipe[] naipes = { Naipe.ESPADAS, Naipe.ESPADAS, Naipe.PAUS };
		SetupRakingParams params = new SetupRakingParams(valores);
		params.setNaipes(naipes);
		RankingMao ranking = setupRaking(params);

		assertEquals(RankingMao.NADA, ranking);
	}
}
