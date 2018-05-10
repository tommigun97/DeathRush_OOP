package test;

import org.junit.jupiter.api.Test;

import model.entity.Behavior;
import model.entity.EntitityImpl;
import model.entity.Entity;

public class MiniTest {
    public class A implements Behavior {
        public void a() {
        }

        @Override
        public void setEntity(Entity e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void update() {
            // TODO Auto-generated method stub
        }
    }

    public class B implements Behavior {
        public void b() {
        }

        @Override
        public void setEntity(Entity e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void update() {
            // TODO Auto-generated method stub
        }
    }

    @Test
    void provaGenerici() {
        Entity e = new EntitityImpl.EntitiesBuilder().setBehaviour(new B()).with("maxLife", 10).with("speed", 400)
                .with("alive", true).build();
        Entity a = new EntitityImpl.EntitiesBuilder().setBehaviour(new A()).build();
        ((B) e.getBehaviour().get()).b();
    }

}
