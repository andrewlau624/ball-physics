
public class Object {
	public Vec2 position;
	public Vec2 oldPosition;
	public Vec2 acceleration = new Vec2();
	
	public Object(Vec2 v) {
		position = v;
		oldPosition = v;
	}
	
	public void updatePosition(double dt) {
		Vec2 velocity = position.sub(oldPosition);
		oldPosition = position;
		position = position.add(velocity).add(acceleration.mult(dt*dt/2.0));
		acceleration = new Vec2();
	}
	
	public void accelerate(Vec2 acc) {
		acceleration = acceleration.add(acc);
	}
}
