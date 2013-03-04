package model;

public class TUIO {

	private int id;
	private int[] pos={0,0};
	private int minimalRotation;
	private int rotationPosition;
	
	public TUIO (String id)
	{
		setId(Integer.parseInt(id));
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int[] getPos() {
		return pos;
	}
	public void setPos(int posX, int posY) {
		pos[0]=posX;
		pos[1]=posY;
	}
	public int getMinimalRotation() {
		return minimalRotation;
	}
	public void setMinimalRotation(int minimalRotation) {
		this.minimalRotation = minimalRotation;
	}
	public int getRotationPosition() {
		return rotationPosition;
	}
	public void setRotationPosition(int rotationPosition) {
		this.rotationPosition = rotationPosition;
	}
}
