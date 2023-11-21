package br.com.api.gerenciamentodepedidos.enums;

public enum TipoPagamento {
	
	BOLETO(1, "Boleto"),
	CARTAO(2, "Cartão");
	
	private int cod;
	private String descricao;
	
	private TipoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getEstadoPagamento() {
		return descricao;
	}
	
	public static TipoPagamento toEnum(Integer cod) {
		
		if(cod == null)
			return null;
		
		for (TipoPagamento x : TipoPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
