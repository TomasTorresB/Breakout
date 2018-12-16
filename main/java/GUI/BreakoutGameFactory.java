package GUI;

import GUI.Control.*;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.RenderLayer;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public final class BreakoutGameFactory  {

    public static Entity newGlassBrick(double x, double y, double v,double w) {
        PhysicsComponent physics = new PhysicsComponent();
        return Entities.builder()
                .at(x,y)
                .type(BreakoutGameType.GLASSBRICK)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("glass.png", v, w))
                .with(physics, new CollidableComponent(true))
                .with(new GlassBrickControl())
                .build();
    }

    public static Entity newWoodenBrick(double x, double y, double v,double w) {
        PhysicsComponent physics = new PhysicsComponent();
        return Entities.builder()
                .at(x,y)
                .type(BreakoutGameType.WOODENBRICK)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("wood.png", v, w))
                .with(physics, new CollidableComponent(true))
                .with(new WoodenBrickControl())
                .build();
    }

    public static Entity newMetalBrick(double x, double y, double v,double w) {
        PhysicsComponent physics = new PhysicsComponent();
        return Entities.builder()
                .at(x,y)
                .type(BreakoutGameType.METALBRICK)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("metal.png", v, w))
                .with(physics, new CollidableComponent(true))
                .with(new MetalBrickControl())
                .build();
    }

    public static Entity newGoldenBrick(double x, double y, double v,double w) {
        PhysicsComponent physics = new PhysicsComponent();
        return Entities.builder()
                .at(x,y)
                .type(BreakoutGameType.GOLDENBRICK)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("golden.png", v, w))
                .with(physics, new CollidableComponent(true))
                .with(new MetalBrickControl())
                .build();
    }

    public static Entity newPlayer(double x, double y) {
        PhysicsComponent physics = new PhysicsComponent();
        return Entities.builder()
                .at(x, y)
                .type(BreakoutGameType.PLAYER)
                .viewFromNodeWithBBox(new Rectangle(100, 30, Color.BLUE))
                .with(physics, new CollidableComponent(true))
                .with(new PlayerControl())
                .build();
    }

    public static Entity newBackground() {
        return Entities.builder()
                .viewFromNode(new Rectangle(600, 600, Color.BLACK))
                .renderLayer(RenderLayer.BACKGROUND)
                .build();
    }
    public static Entity newBackground2(double x,double y) {
        return Entities.builder()
                .at(x,y)
                .viewFromNode(new Rectangle(200, 600, Color.SILVER))
                .renderLayer(RenderLayer.BACKGROUND)
                .build();
    }

    public static Entity newBall(double x, double y) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(
                new FixtureDef().restitution(1f).density(0.1f).friction(0f));
        physics.setOnPhysicsInitialized(
                () -> physics.setLinearVelocity(0, 0));
        return Entities.builder()
                .at(x, y)
                .type(BreakoutGameType.BALL)
                .viewFromNodeWithBBox(new Circle(10, Color.RED))
                .with(physics, new CollidableComponent(true))
                .with(new BallControl())
                .build();
    }

    public static Entity newWalls() {
        int thickness = 100;
        int w = 800;
        int h = 600;
        Entity walls = Entities.builder().bbox(new HitBox("LEFT", new Point2D(-thickness, 0.0D), BoundingShape.box(thickness, h))).bbox(new HitBox("RIGHT", new Point2D(w-200, 0.0D), BoundingShape.box(thickness, h))).bbox(new HitBox("TOP", new Point2D(0.0D, -thickness), BoundingShape.box(w, thickness))).bbox(new HitBox("BOT", new Point2D(0.0D, h), BoundingShape.box(w, thickness))).with(new PhysicsComponent()).build();
        walls.setType(BreakoutGameType.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }

}
