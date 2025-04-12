import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    // image attributes
    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    // player attributes
    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;

    //pipe attributes
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;

    // game logic
    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;
    int defeat = 0;

    // score
    int score = 0;
    JLabel scoreLabel;

    // constructor
    public FlappyBird() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        addKeyListener(this);
//        setBackground(Color.blue);

        //load images
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();

        startGame();
    }

    public void startGame() {
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();
        score = 0;
        defeat = 0;

        // label skor
        scoreLabel = new JLabel("Score: 0");
//        scoreLabel.setBounds(10, 10, 200, 30);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel);
        revalidate();
        repaint();

        pipesCooldown = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pipe");
                placePipes();
            }
        });
        pipesCooldown.start();

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);

        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        if(defeat==1){
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over", frameWidth/4+10, frameHeight/2);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Click R to Restart", frameWidth/4+5, frameHeight/2+40);
        }
    }

    public void move() {
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        if(player.getPosY() + player.getHeight()>frameHeight){
            defeat = 1;
            pipesCooldown.stop();
            gameLoop.stop();
        }

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());
            if((player.getPosX() < pipe.getPosX() + pipe.getWidth() && player.getPosX() + player.getWidth() > pipe.getPosX() && player.getPosY() < pipe.getPosY() + pipe.getHeight() && player.getPosY() + player.getHeight() > pipe.getPosY()) || (player.getPosY() + player.getHeight() > frameHeight)) {
                defeat = 1;
                pipesCooldown.stop();
                gameLoop.stop();
            }

            if (!pipe.isPassed() && pipe.getImage() == lowerPipeImage && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                pipe.setPassed(true);
                score++;
                scoreLabel.setText("Score: " + score);
            }
        }
    }

    public void placePipes() {
        int randompipeStartPosY = (int) (pipeStartPosY   - pipeHeight/4 - Math.random() * (pipeHeight/2));
        int openingSpace = frameHeight/4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randompipeStartPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, randompipeStartPosY + pipeHeight + openingSpace, pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    public void restartGame() {
        pipes.clear();
        remove(scoreLabel);
        startGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setVelocityY(-10);
        }
        if (defeat==1 && e.getKeyCode() == KeyEvent.VK_R) {
            restartGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
