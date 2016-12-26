import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mandelbrot extends JPanel {
	
    double Cx,Cy;
    double CxMin=-2.5;
    double CxMax=1.5;
    double CyMin=-2.0;
    double CyMax=2.0;
    
    double pixelWidth;
    double pixelHeight;
	private BufferedImage canvas;
	
	public Mandelbrot(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        pixelWidth = (CxMax-CxMin)/width;
        pixelHeight = (CyMax-CyMin)/height;
        drawMandelbrot();
    }
	
	public void drawMandelbrot()
	{
		for(int px = 0; px < canvas.getWidth();px++ )
		{
			for(int py = 0;py < canvas.getHeight();py++)
			{
				double x0 = px*pixelWidth + CxMin;
				double y0 = py*pixelHeight + CyMin;
				double x = 0.0f;
				double y = 0.0f;
				int iteration = 0;
				int maxIterations = 1000;
				while((x * x) + (y *  y) < 4 && iteration < maxIterations)
				{
					double xtemp = (x*x) - (y*y) + x0;
					 y = 2*(x*y) + y0;
					 x = xtemp;
					 iteration++;
				}
				if(iteration == maxIterations)
				{
					canvas.setRGB(px, py, Color.BLACK.getRGB());
				}
				else
				{
					canvas.setRGB(px, py, Color.HSBtoRGB(iteration * 0.36f, 100, 100));
					
				}
			}
		}
	}
    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }
    
	public static void main(String[] args) {
		 int width = 900;
	        int height = 900;
	        JFrame frame = new JFrame("Mandelbrot");

	        Mandelbrot panel = new Mandelbrot(width, height);

	        frame.add(panel);
	        frame.pack();
	        frame.setVisible(true);
	        frame.setResizable(false);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
