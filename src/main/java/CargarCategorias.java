import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CargarCategorias implements TipoDato{
	public void cargarSegunTipo(File archivo, ObjectMapper mapper) throws JsonParseException, JsonMappingException, IOException {
		List<Categoria> categorias = mapper.readValue(archivo, new TypeReference<List<Categoria>>(){});
		RepoCategorias.getInstance().agregarCategorias(categorias);
	}
}