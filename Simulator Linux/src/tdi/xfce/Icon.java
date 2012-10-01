package tdi.xfce;

public class Icon {
	
	private String name;
	private int row;
	private int col;
	
	public Icon()
	{
	}
	
	@Override
	public boolean equals(Object obj) {
		Icon i=(Icon)obj;
		if(i.getName().equals(name))
			return true;
		return false;
	}

	public Icon(String name, int row, int col)
	{
		setName(name);
		setRow(row);
		setCol(col);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
}
