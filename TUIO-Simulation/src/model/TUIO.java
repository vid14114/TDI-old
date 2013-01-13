package model;

public class TUIO {
	//The id of the TUIO - this value must be unique
	private int id;
	// Position on the x-axis
	private int xPos;
	//Position on the y-axis
	private int yPos;
	//Rotation of the TUIO 0= no rotation 360=0 and 180 is flipped.
	private double rotation;
	public TUIO(int id, int xPos, int yPos, double rotation){
		this.id=id;
		this.xPos=xPos;
		this.yPos=yPos;
		this.rotation=rotation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public double getRotation() {
		return rotation;
	}
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	

}