package poker;

public class CartaBuilder {
  private Integer valor = 1;
  private Naipe naipe = Naipe.ESPADAS;

  public CartaBuilder withValor(Integer valor) {
    this.valor = valor;
    return this;
  }

  public CartaBuilder withNaipe(Naipe naipe) {
    this.naipe = naipe;
    return this;
  }

  public Carta build() {
    return new Carta(valor, naipe);
  }
}
