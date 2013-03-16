package model;

/**
 * The information of the location, rotation degreee and such is saved in this class 
 * @author TDI-Team
 *
 */
public class TUIO {

	private String id;
	private int[] pos={0,0};
	private int minimalRotation;
	private int rotationPosition=0;
	
	public TUIO (String id)
	{
		setId(id);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int[] getPos() {
		return pos;
	}
	public void setPos(String posX, String posY) {
		pos[0]=Integer.parseInt(posX);
		pos[1]=Integer.parseInt(posY);
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
	
	public void rotateLeft()
	{
		rotationPosition--;
	}
	
	public void rotateRight()
	{
		rotationPosition++;
	}
}
