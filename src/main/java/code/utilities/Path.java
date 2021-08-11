package code.utilities;

public class Path {
	int x;
	int y;
	
	int pathLength;
	
	Path path;
	
	public Path(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Path(int x, int y, int pathLength) {
		this.x = x;
		this.y = y;
		this.pathLength = pathLength;
	}
	
	public int getLength() {
		if(path != null && path.pathLength != 0) {
			return path.pathLength;
		}else {
			return pathLength;
		}
	}
	
	public void setPath(Path p) {
		if(p.path != null) {
			path = p.path;
		}else {
			path = p;
		}
	}
}
