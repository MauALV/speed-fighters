package engine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import core.SpeedFighters;


public class Game implements Runnable {
		
		// Elementos Ventana
		private JFrame frame;
		private Canvas canvas;
		
		// Propiedades del Juego
		public static int WIDTH, HEIGHT;
		private String title;
		
		SpeedFighters speed_fighters;
		
		// Graficos
		private BufferStrategy bs;
		private Graphics g;
		
		// Control de Ejecucion
		private Thread thread;
		private boolean running = false;
		
		public Game(String title, int width, int height)
		{
			this.title = title;
			Game.WIDTH = width;
			Game.HEIGHT = height;
			createWindow();
		}
		
		public void createWindow() {
			//System.out.println("Creando Ventana");
			Dimension dims = new Dimension(WIDTH, HEIGHT);
			frame = new JFrame();
			frame.setSize(dims);
			frame.setTitle(title);
			
			canvas = new Canvas();
			canvas.setPreferredSize(dims);
			canvas.setMinimumSize(dims);
			canvas.setMaximumSize(dims);
			
			canvas.addKeyListener(Input.get());
			canvas.addMouseMotionListener(Input.get());
			canvas.addMouseListener(Input.get());
			
			frame.add(canvas);
			frame.pack();
			
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.setFocusable(false);
			canvas.requestFocus();
		}
		
		
		public void init()
		{
			//System.out.println("Inicializando Juego");
			speed_fighters = new SpeedFighters(this);
			speed_fighters.init();
		}
		
		public synchronized void start()
		{
			if (running) return;
			// Hace que running pase al estado de true e inicia el thread principal
			running = true;
			thread = new Thread(this);
			thread.start();
		}
		
		// El m�todo stop se encarga de detener al juego
		public synchronized void stop()
		{
			//System.out.println("Exiting...");
			// Si el juego no est� corriendo, no hace nada
			if (!running) return;
			// Hace que running pase al estado de false y termina el thread principal
			running = false;
			try
			{
				thread.join();
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		public void tick()
		{
			speed_fighters.tick();
		}
		
		// El metodo render() se encarga de hacer el renderizado del juego
		public void render()
		{
			
			bs = canvas.getBufferStrategy();
			// Si Canvas no tiene un BufferStrategy
			if (bs == null)
			{
				canvas.createBufferStrategy(3);
				return;
			}
			// Graphics obtiene lo que est� dibujado en el BufferStrategy
			g = bs.getDrawGraphics();
			// Se limpia el Buffer para dibujar en blanco
			g.clearRect(0, 0, WIDTH, HEIGHT);
			
			
			///////////// Renderizacion del Juego///////////////
//			g.setColor(Color.BLUE);
//			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			speed_fighters.render((Graphics2D)g);
			//
			
			// Deja de hacer uso del "pincel" para no consumir los recursos todo el tiempo
			g.dispose();
			// Muestra lo que Graphics dibuj� en los Buffers
			bs.show();
			
		}
		
		
		
		// �ste es el m�todo primordial para el GameLoop
		public void run()
		{
			// Llama a init() para inicializar lo que es requerido en el juego
			init();
			// Se declara un target de actualizaciones por segundo con fps
			// Con ticks se hace tracking de las actualizaciones que se hacen por segundo
			int fps = 120, ticks = 0;
			// timePerTick nos muestra cu�nto tiempo debe pasar entre cada update
			// timePerTick es 1000000000 porque se manejar� el tiempo en nano segundos
			// delta nos ayuda a saber si el tiempo de timePerTick ya ha pasado
			double timePerTick = 1000000000 / fps, delta = 0;
			// now nos da el tiempo actual y lastTime el tiempo anterior
			// timer da seguimiento al tiempo que ha pasado desde el �ltimo ciclo
			long now, lastTime = System.nanoTime(), timer = 0;
			
			// Mientras el juego se encuentre corriendo...
			while (running)
			{
				// Se obtiene el tiempo actual
				now = System.nanoTime();
				// Se le suma a delta para ver si es momento de hacer un update
				delta += (now-lastTime) / timePerTick;
				// Se le suma a timer el tiempo que se lleva
				timer += now - lastTime;
				// lastTime avanza a ser el actual
				lastTime = now;
				
				// Si el delta es mayor o igual a uno
				if (delta >= 1)
				{
					// Se realiza el update y el pintado
					tick();
					render();
					// Se le suma uno a ticks para dar seguimiento a las actualizaciones que
					// se llevan en �ste segundo
					ticks ++;
					// Se reduce delta en uno para que se pueda volver a realizar el update
					delta --;
				}
				
				// �sto significa que si ha pasado ya un segundo
				if (timer >= 1000000000)
				{
					// Imprime el n�mero de actualizaciones que se hicieron en ese segundo
					System.out.println("Ticks and frames: " + ticks);
					// Reinicia los contadores
					ticks = 0;
					timer = 0;
				}
			}
			// Cuando el juego ya no est� corriendo, se detiene
			stop();
		}
			
		public int getWidth() { return WIDTH; }
		public int getHeight() { return HEIGHT; }
	
}
