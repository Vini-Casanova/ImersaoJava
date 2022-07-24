import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoNasa implements ExtratorDeConteudo {

    public List<conteudo> extraiConteudos(String json) {

         // titulo, poster, rating
         var parser = new JsonParser();
         List<Map<String, String>> listaAtributos = parser.parse(json);


        List<conteudo> conteudos = new ArrayList<>();


        // preencher a lista de conteudos
        for (Map<String, String> atributos : listaAtributos) {
            String titulo = atributos.get("title").replace(":", "-") + ".png"; 
            String urlImagem = atributos.get("url");
            var conteudo = new conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
