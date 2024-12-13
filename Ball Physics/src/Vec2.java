
public class Vec2 {
	public double x;
	public double y;
	
	public Vec2() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vec2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vec2(Vec2 v){
		this.x = v.x;
		this.y = v.y;
	}
	
	public Vec2 add(Vec2 v) {
		return new Vec2(this.x + v.x, this.y + v.y);
	}
	
	public Vec2 sub(Vec2 v) {
		return new Vec2(this.x - v.x, this.y - v.y);
	}
	
	public Vec2 mult(Vec2 v) {
		return new Vec2(this.x * v.x, this.y * v.y);
	}
	
	public Vec2 mult(double n) {
		return mult(new Vec2(n,n));
	}
	
	public Vec2 div(Vec2 v) {
		return new Vec2(this.x / v.x, this.y / v.y);
	}
	
	public Vec2 div(double n) {
		return div(new Vec2(n,n));
	}
}