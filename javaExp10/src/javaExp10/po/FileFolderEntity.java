package javaExp10.po;

public class FileFolderEntity {
	private String size;
	private String createDate;
	private String modifiedDate;
	private String name;
	private int type;		//type = 1表示文件 type = 2表示文件夹
	
	public FileFolderEntity(String size, String createDate, String modifiedDate, String name,
			int type) {
		super();
		this.size = size;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.name = name;
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
