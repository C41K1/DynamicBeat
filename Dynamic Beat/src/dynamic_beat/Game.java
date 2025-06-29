package dynamic_beat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.*;

public class Game extends Thread {
	//Images
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage(); 																									
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage(); 																									

	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage(); 	
	
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 																									
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 																									
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 																									
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 																									
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 																									
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 																									
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	
	private Image blueFlareImage;
	private Image judgeImage;

	private String titleName;
	private String difficulty;
	private String musicTitle;
	private String comboMeio;
	private String comboRodape;
	private Color comboColor;
	private Timer comboTimer;
	private Music gameMusic;
	
	private int score = 0;
	private int combo = 0;
	
	//To administer/organize the note blocks that are being created
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	//Constructor 
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		this.comboMeio = "";
		this.comboRodape = "";
		this.comboColor = Color.ORANGE;

		gameMusic = new Music(this.musicTitle, false);
		this.comboTimer = new Timer(200, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboColor.equals(Color.YELLOW)) {
					comboColor = Color.RED;
				} else comboColor = Color.YELLOW;
			}
		});
		comboTimer.start();
	}
	
	public void screenDraw(Graphics2D g){
		//noteRouteImage
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		
		//noteRouteLineImage
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);
		
		//Others
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);
	
		//Notes
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (note.getY() > 620) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
				new Music("miss.mp3",false).start();
				combo = 0;
				this.comboMeio = "";
				this.comboRodape = "";
			}
			if (!note.isProceeded()) {
				noteList.remove(i);
				i--;
			}
			else {
				note.screenDraw(g);
			}
		}
		
		//Track Info
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //prevent printString from cracking
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		
		//Difficulty
		g.drawString(difficulty, 1190, 700);;	

		//Key Info
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("S",270,609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K",889,609);
		g.drawString("L", 993, 609);
		
		//Score/Combo
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("Score: "+Integer.toString(score), 400, 702); //565,702
		g.drawString("Combo: "+Integer.toString(combo), 700,702);
		
		//Flare
		g.drawImage(blueFlareImage,320,430,null);
		
		//Judgment
		g.drawImage(judgeImage,460,420,null);

		g.setColor(comboColor);
		g.setFont(new Font("Arial", Font.BOLD, 72));
		g.drawString(comboMeio, 25,300);
		g.setFont(new Font("Arial", Font.BOLD, 42));
		g.drawString(comboRodape, 25,350);

	}
	
	//Events
	//S
	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	//D
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	//F
	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	//Space
	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
		new Music("drumBig1.mp3",false).start();
	}
	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	//J
	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	//K
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	//L
	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	
	@Override
	public void run() {
		dropNotes();
	}
	
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	
	public void dropNotes() {

		Beat[] beats = new Beat[]{};

		if(Objects.equals(difficulty, "Easy")){
			beats = BeatListsEasy.getBeats(titleName, difficulty);
		}
		else if(Objects.equals(difficulty, "Hard")){
			beats = BeatListsHard.getBeats(titleName, difficulty);
		}

		int i = 0;
		gameMusic.start();

		//get the time at which the current music is being played; make a note that is reasonable to the beat of the current music
		//Review! 
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//judge if the player press key on beat
	public void judge(String input) {
		for (int i = 0 ; i < noteList.size(); i++) { //index starts at 0 so it judges the notes that were created first
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}

	public void judgeEvent(String judge) {
		this.comboMeio = "";
		this.comboRodape = "";
		if (!judge.equals("None")) {
			blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
		}
		else {
			new Music("miss.mp3",false).start();
		}
		if (judge.equals("Early")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeEarly.png")).getImage();
			score += 5;
			combo = 0;
			new Music("miss.mp3",false).start();
		}
		else if (judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGood.png")).getImage();
			score += 20;
			combo += 1;
		}
		else if (judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGreat.png")).getImage();
			score += 50;
			combo += 1;
		}
		else if (judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgePerfect.gif")).getImage();
			score += 100;
			combo += 1;
			new Music("ping.mp3",false).start();
		}
		else if (judge.equals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeLate.png")).getImage();
			score += 5;
			combo = 0;
			new Music("miss.mp3",false).start();
		}
		if (combo >= 2){
			this.comboMeio = combo + "x";
			this.comboRodape = "COMBO!";
		}
		else {
			this.comboMeio = "";
			this.comboRodape = "";
		}
	}

}
