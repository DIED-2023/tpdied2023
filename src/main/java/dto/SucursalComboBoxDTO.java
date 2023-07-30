package dto;

public class SucursalComboBoxDTO {
	private Integer id;
	private String nombre;

	public SucursalComboBoxDTO(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}
}