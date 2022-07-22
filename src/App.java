import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Conexão HTTP para a api de top 250 filmes
        //String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        var geradora = new StickerGenerator();
        
        // titulo, poster, rating
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        //exibir dados 
        for (int i = 0; i < 10; i++) {

            Map<String,String> filme = listaDeFilmes.get(i);

            String urlImagem = filme.get("url").replaceAll("(@+)(.*).jpg$", "$1.jpg");

            String titulo = filme.get("title");

            String nomeArquivo = titulo.replace(":", "-") + ".png";

            //Bloco de caso não encontre ou de um erro ele envie a mensagem de erro 
            try{
                InputStream inputStream = new URL(urlImagem).openStream();
                System.out.println("Gerando imagem - [" + titulo + "]");
                geradora.criar(inputStream, nomeArquivo);
             }catch(java.io.FileNotFoundException err){
                  System.out.println("Imagem não encontrada ou link inválido");
             }
            

            System.out.println(titulo);
            System.out.println();
            

        } 
        
    }
}
