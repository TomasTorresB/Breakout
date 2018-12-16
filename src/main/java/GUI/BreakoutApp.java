package GUI;

import GUI.Control.*;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.gameplay.GameState;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import controller.Game;
import facade.HomeworkTwoFacade;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import logic.brick.*;
import logic.level.Level;

import java.util.*;

import static GUI.BreakoutGameFactory.*;

public class BreakoutApp extends GameApplication implements Observer {
    private HomeworkTwoFacade hw2 = new HomeworkTwoFacade();
    private int cantBricks = 5;
    private int circlePos = 640;

    private PlayerControl getPlayerControl() {
        return getGameWorld().getSingleton(BreakoutGameType.PLAYER).get().getComponent(PlayerControl.class);
    }

    private BallControl getBallControl() {
        return getGameWorld().getSingleton(BreakoutGameType.BALL).get().getComponent(BallControl.class);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(800);
        gameSettings.setHeight(600);
        gameSettings.setTitle("Breakout App");
        gameSettings.setVersion("0.1");
    }

    @Override
    protected void initGame() {
        hw2.addAppObserver(this);
        Entity player = newPlayer(250, 525);
        Entity bg = newBackground();
        Entity bg2 = newBackground2(600,0);
        Entity ball = newBall(295,505);
        Entity walls = newWalls();
        getGameWorld().addEntities(player,bg,bg2,ball,walls);
    }

    private void DisplayCurrentLevel() {
        List<Brick> BRICKS = hw2.getBricks();
        if (hw2.hasCurrentLevel()) {
            Brick golden = new GoldenBrick();
            BRICKS.add(golden);
        }
        Collections.shuffle(BRICKS);
        int x = 0;
        int y = 50;
        int cont = 0;
        int lar = 0;
        int anc = 0;
        for (Brick b : BRICKS) {
            if (b.isGlassBrick()) {
                Entity brick = newGlassBrick(x + 60 * lar, y + 31 * anc, 60, 30);
                brick.getComponent(GlassBrickControl.class).setBrick((GlassBrick) b);
                getGameWorld().addEntities(brick);
                cont++;
                lar = cont % 10;
                anc = cont / 10;
            }
            else if (b.isWoodenBrick()) {
                Entity brick = newWoodenBrick(x + 60 * lar, y + 31 * anc, 60, 30);
                brick.getComponent(WoodenBrickControl.class).setBrick((WoodenBrick) b);
                getGameWorld().addEntities(brick);
                cont++;
                lar = cont % 10;
                anc = cont / 10;
            }
            else if (b.isMetalBrick()) {
                Entity brick = newMetalBrick(x + 60 * lar, y + 31 * anc, 60, 30);
                brick.getComponent(MetalBrickControl.class).setBrick((MetalBrick) b);
                getGameWorld().addEntities(brick);
                cont++;
                lar = cont % 10;
                anc = cont / 10;
            }
            else {
                Entity brick = newGoldenBrick(x + 60 * lar, y + 31 * anc, 60, 30);
                getGameWorld().addEntities(brick);
                cont++;
                lar = cont % 10;
                anc = cont / 10;
            }
        }
    }

    private static int currentTimeMillis() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

    @Override
    protected void initInput() {
        Input input = getInput();
        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                if (getGameState().getInt("GAME OVER") == 0) {
                    getPlayerControl().moveRight();
                    if (getGameState().getInt("Ball state") == 0) {
                        getBallControl().moveRight();
                    }
                }
            }
        }, KeyCode.D);
        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                if (getGameState().getInt("GAME OVER") == 0) {
                    getPlayerControl().moveLeft();
                    if (getGameState().getInt("Ball state") == 0) {
                        getBallControl().moveLeft();
                    }
                }
            }
        }, KeyCode.A);
        input.addAction(new UserAction("Release Ball") {
            @Override
            protected void onAction() {
                if ((getGameState().getInt("Ball state") == 0) && getGameState().getInt("GAME OVER") == 0){
                    getGameState().setValue("Ball state",1);
                    getBallControl().release();
                }
            }
        }, KeyCode.SPACE);
        input.addAction(new UserAction("Add Level") {
            @Override
            protected void onActionBegin() {
                if (getGameState().getInt("GAME OVER") == 0) {
                    Level level = hw2.newLevelWithBricksFull("test", cantBricks, 1, 0, currentTimeMillis());
                    getGameState().setValue("Level name", "Breakout");
                    if (!hw2.hasCurrentLevel()) {
                        hw2.setCurrentLevel(level);
                        DisplayCurrentLevel();
                    } else {
                        getGameState().increment("Number of levels", 1);
                        hw2.addPlayingLevel(level);
                    }
                }
            }
        }, KeyCode.N);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {

        vars.put("Total points",0);
        vars.put("Ball state",0); // 0 inicialmente en reposo, 1 en movimiento
        vars.put("Balls left",hw2.getBallsLeft());
        vars.put("Level name","No level");
        vars.put("Number of levels",0);
        vars.put("GAME OVER",0); // 0 todavia no termina,1 termino
    }

    public void endGame() {
        getGameState().increment("GAME OVER",1);
        getPlayerControl().getEntity().removeFromWorld();
        getGameWorld().getEntitiesByType(BreakoutGameType.GLASSBRICK)
                .forEach(e->e.removeFromWorld());
        getGameWorld().getEntitiesByType(BreakoutGameType.WOODENBRICK)
                .forEach(e->e.removeFromWorld());
        getGameWorld().getEntitiesByType(BreakoutGameType.METALBRICK)
                .forEach(e->e.removeFromWorld());
        getGameWorld().getEntitiesByType(BreakoutGameType.GOLDENBRICK)
                .forEach(e->e.removeFromWorld());

    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0,0);

        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(BreakoutGameType.BALL, BreakoutGameType.WALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity wall,
                                                   HitBox boxBall, HitBox boxWall) {
                        if (boxWall.getName().equals("BOT")) {
                            ball.removeFromWorld();
                            getGameState().setValue("Balls left",hw2.dropBall());
                            circlePos -= 23;
                            getGameWorld().getEntitiesAt(new Point2D(circlePos,200)).forEach(e->e.removeFromWorld());
                            getGameState().setValue("Ball state",0);
                            if (getGameState().getInt("Balls left") > 0) {
                                getPlayerControl().getEntity().removeFromWorld();
                                Entity player2 = newPlayer(250, 525);
                                Entity ball2 = newBall(295, 505);
                                getGameWorld().addEntities(player2, ball2);
                            }
                            else {
                                endGame();
                                Text text7 = getUIFactory().newText("GAME OVER", Color.DARKGOLDENROD, 22);
                                text7.setTranslateX(250);
                                text7.setTranslateY(300);
                                getGameScene().addUINode(text7);
                                getGameState().setValue("Level name","RIP");
                            }
                        }
                    }
                });
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(BreakoutGameType.BALL, BreakoutGameType.GLASSBRICK) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity brick,
                                                   HitBox boxBall, HitBox boxBrick) {
                        brick.getComponent(GlassBrickControl.class).isHit();
                        if (hw2.numberOfBricks() < (double)cantBricks/2.0) {
                            getGameWorld().getEntitiesByType(BreakoutGameType.GOLDENBRICK)
                                    .forEach(e->e.removeFromWorld());
                        }
                    }
                });
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(BreakoutGameType.BALL, BreakoutGameType.WOODENBRICK) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity brick,
                                                   HitBox boxBall, HitBox boxBrick) {
                        brick.getComponent(WoodenBrickControl.class).isHit();
                        if (hw2.numberOfBricks() < (double)cantBricks/2.0) {
                            getGameWorld().getEntitiesByType(BreakoutGameType.GOLDENBRICK)
                                    .forEach(e->e.removeFromWorld());
                        }
                    }
                });
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(BreakoutGameType.BALL, BreakoutGameType.METALBRICK) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity brick,
                                                   HitBox boxBall, HitBox boxBrick) {
                        brick.getComponent(MetalBrickControl.class).isHit();
                        if (hw2.numberOfBricks() < (double)cantBricks/2.0) {
                            getGameWorld().getEntitiesByType(BreakoutGameType.GOLDENBRICK)
                                    .forEach(e->e.removeFromWorld());
                        }
                    }
                });
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(BreakoutGameType.BALL, BreakoutGameType.GOLDENBRICK) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity brick,
                                                   HitBox boxBall, HitBox boxBrick) {
                        if (hw2.hasNextLevel()) {
                            getGameWorld().getEntitiesByType(BreakoutGameType.GLASSBRICK)
                                    .forEach(e -> e.removeFromWorld());
                            getGameWorld().getEntitiesByType(BreakoutGameType.WOODENBRICK)
                                    .forEach(e -> e.removeFromWorld());
                            getGameWorld().getEntitiesByType(BreakoutGameType.METALBRICK)
                                    .forEach(e -> e.removeFromWorld());
                            getGameWorld().getEntitiesByType(BreakoutGameType.GOLDENBRICK)
                                    .forEach(e -> e.removeFromWorld());
                            hw2.goNextLevel();
                            getBallControl().getEntity().removeFromWorld();
                            getPlayerControl().getEntity().removeFromWorld();
                            getGameState().setValue("Ball state", 0);
                            Entity player2 = newPlayer(250, 525);
                            Entity ball2 = newBall(295, 505);
                            getGameWorld().addEntities(player2, ball2);
                            DisplayCurrentLevel();
                        }
                        else {
                            endGame();
                            ball.removeFromWorld();
                            Text text8 = getUIFactory().newText("CONGRATULATIONS", Color.DARKGOLDENROD, 22);
                            text8.setTranslateX(245);
                            text8.setTranslateY(300);
                            getGameScene().addUINode(text8);
                        }
                    }
                });
    }

    @Override
    protected void initUI() {
        Text text = getUIFactory().newText("xdxdxd", Color.DARKGOLDENROD, 35);
        text.setTranslateX(625);
        text.setTranslateY(50);
        text.textProperty().bind(getGameState().stringProperty("Level name"));
        getGameScene().addUINode(text);
        Text text2 = getUIFactory().newText("Total Points:", Color.DARKGOLDENROD, 22);
        text2.setTranslateX(635);
        text2.setTranslateY(100);
        getGameScene().addUINode(text2);
        Text text3 = getUIFactory().newText("", Color.DARKGOLDENROD, 22);
        text3.setTranslateX(670);
        text3.setTranslateY(125);
        text3.textProperty().bind(getGameState().intProperty("Total points").asString());
        getGameScene().addUINode(text3);
        Text text4 = getUIFactory().newText("Balls left:", Color.DARKGOLDENROD, 22);
        text4.setTranslateX(640);
        text4.setTranslateY(175);
        getGameScene().addUINode(text4);
        Text text9 = getUIFactory().newText("Number of levels left:", Color.DARKGOLDENROD, 17);
        text9.setTranslateX(620);
        text9.setTranslateY(245);
        getGameScene().addUINode(text9);
        Text text6 = getUIFactory().newText("", Color.DARKGOLDENROD, 22);
        text6.setTranslateX(685);
        text6.setTranslateY(270);
        text6.textProperty().bind(getGameState().intProperty("Number of levels").asString());
        getGameScene().addUINode(text6);
        for (int i =0;i < hw2.getBallsLeft();i++) {
            Entity e = new Entity();
            Circle lives = new Circle(10, Color.RED);
            e.translate(new Point2D(circlePos,200));
            e.setView(lives);
            getGameWorld().addEntity(e);
            circlePos += 23;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            if (arg.equals("maxLevelScoreUpdate")) {
                getGameWorld().getEntitiesByType(BreakoutGameType.METALBRICK)
                        .forEach(e->e.removeFromWorld());
                DisplayCurrentLevel();
                getGameState().increment("Number of levels",-1);
            } else if (arg.equals("MetalBrickDestroyedUpdate")) {
                Entity e = new Entity();
                Circle lives = new Circle(10, Color.RED);
                e.setView(lives);
                e.translate(new Point2D(circlePos,200));
                getGameWorld().addEntity(e);
                circlePos += 23;
            } else if (arg.equals("Game ended")) {
                endGame();
                getBallControl().getEntity().removeFromWorld();
                Text text8 = getUIFactory().newText("CONGRATULATIONS!", Color.DARKGOLDENROD, 22);
                text8.setTranslateX(245);
                text8.setTranslateY(300);
                getGameScene().addUINode(text8);

            }
        }
        else {
            int res = (int) arg;
            getGameState().increment("Total points",res);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
