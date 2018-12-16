package GUI.Control;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;

public class BallControl extends Component {

    public void release() {
        PhysicsComponent f = entity.getComponent(PhysicsComponent.class);
        f.setLinearVelocity(1 * 10, 8 * 10);
    }

    public void moveLeft() {
        if (entity.getX() <= 45) {
            entity.getComponent(PhysicsComponent.class).reposition(new Point2D(45,505));
        }
        else {
            entity.getComponent(PhysicsComponent.class).reposition(new Point2D(entity.getX() - 1,505));
        }
    }

    public void moveRight() {
        if (entity.getX() >= 545) {
            entity.getComponent(PhysicsComponent.class).reposition(new Point2D(545,505));
        }
        else {
            entity.getComponent(PhysicsComponent.class).reposition(new Point2D(entity.getX() + 1,505));
        }
    }
}
