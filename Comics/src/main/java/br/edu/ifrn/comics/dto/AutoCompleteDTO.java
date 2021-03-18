package br.edu.ifrn.comics.dto;

/** A classe AutoCompleteDTO providencia métodos necessários para o AutoComplete*/
public class AutoCompleteDTO {
	
	private String label;
	
	private Integer value;
	

	public AutoCompleteDTO(String label, Integer value) {
		super();
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	
	
}
