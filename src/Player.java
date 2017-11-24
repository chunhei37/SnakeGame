import javafx.scene.paint.Color;

public class Player {
	private String name;
	private String icon;
	private int currentScore;
	private boolean bot = false;
	private Color color;
	private int oldScore;
	
	public Player() {
		name = "No Name";
		currentScore = 0;
	}
	public Player(String name) {
		setName(name);
		currentScore = 0;
	}
	
	public Player(String name, boolean isBot) {
		setName(name);
		currentScore = 0;
		setBot(isBot);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public int getCurrentScore() {
		return currentScore;
	}
	
	public void addCurrentScore(int currentScore) {
		this.currentScore += currentScore;
	}
	
	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}
	
	public boolean isBot() {
		return bot;
	}
	
	public void setBot(boolean bot) {
		this.bot = bot;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	public int getOldScore() {
		return oldScore;
	}
	
	public void setOldScore(int oldScore) {
		this.oldScore = oldScore;
	}
	
}

