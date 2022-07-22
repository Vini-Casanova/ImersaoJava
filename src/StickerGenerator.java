import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {

    public void criar(InputStream inputStream, String nomeArquivo ) throws Exception {

        //leitura da imagem 
        //InputStream inputStream = new FileInputStream(new File("entrada/imagemMaior.jpg"));
        //InputStream inputStream = new URL().openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // criar nova imagem em memoria com transparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para a nova imagem (em mem)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar fonte 
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);
        
        
        // escrever uma frase na nova imagem
        graphics.drawString("TOPZADA", largura/3, novaAltura - 100);

        //escrever a nova imagem em um arquivo novo
        ImageIO.write(novaImagem, "png", new File("saida/"+nomeArquivo));


    }
    
}
