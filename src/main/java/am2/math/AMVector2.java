package am2.math;

public class AMVector2{
	public double x;
	public double y;

	public AMVector2(double x, double y){
		this.x = x;
		this.y = y;
	}

	public AMVector2 subtract(AMVector2 vec){
		return new AMVector2(this.x - vec.x, this.y - vec.y);
	}

	public AMVector2 add(AMVector2 vec){
		return new AMVector2(this.x + vec.x, this.y + vec.y);
	}

	public AMVector2 multiply(AMVector2 vec){
		return new AMVector2(this.x * vec.x, this.y * vec.y);
	}

	public AMVector2 divide(AMVector2 vec){
		return new AMVector2(this.x / vec.x, this.y / vec.y);
	}
}
