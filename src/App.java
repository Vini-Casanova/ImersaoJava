import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        //String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        //ExtratorDeConteudo extrator = new ExtratorConteudoFilmes();

        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        ExtratorDeConteudo extrator = new ExtratorConteudoNasa();


        var http = new ClienteHttp();
        String json = http.buscadados(url);


         //exibir e manipular dados
        List<conteudo> conteudo = extrator.extraiConteudos(json);
        StickerGenerator geradora = new StickerGenerator();
        
        
       
        for (int i = 0; i < 10; i++) {

            conteudo conteudos = conteudo.get(i);

            //Bloco de caso não encontre ou de um erro ele envie a mensagem de erro 
            try{
                InputStream inputStream = new URL(conteudos.getUrlImagem()).openStream();
                System.out.println("Gerando imagem - [" + conteudos.getTitulo() + "]");
                geradora.criar(inputStream, conteudos.getTitulo());
             }catch(java.io.FileNotFoundException err){
                  System.out.println("Imagem não encontrada ou link inválido");
             }
            

            System.out.println(conteudos.getTitulo());
            System.out.println();
            

        } 
        
    }
}
