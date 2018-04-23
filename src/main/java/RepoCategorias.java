import java.util.ArrayList;
import java.util.List;

public class RepoCategorias {
	private static RepoCategorias repoCategorias;
	private static List<Categoria> categorias = new ArrayList<>();
	
	public static RepoCategorias getInstance(){
		if (repoCategorias == null) {
			repoCategorias = new RepoCategorias();
		}
		return repoCategorias;
	}
	
	public static Categoria obtenerCategoriaSegunConsumo(double consumo) {
		return categorias.stream().filter(categoria -> categoria.meCorrespondeElConsumo(consumo)).findFirst().orElse(null);
	}

	public static void agregarCategoria(Categoria categoria) {
		categorias.add(categoria);		
	}

	public void agregarCategorias(List<Categoria> categorias) {
		this.categorias.addAll(categorias);
	}

	public static void cargarCategorias() {
		CargarDataDesdeJSON cargadorDeDatos = new CargarDataDesdeJSON().getInstance();
		cargadorDeDatos.setTipoDato(new CargarCategorias());
		cargadorDeDatos.cargar("categorias");		
	}

	public List<Categoria> obtenerTodas() {
		return categorias;
	}
}
