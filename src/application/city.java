package application;

public class city {
	private String name;
	double x;
	double y;
	double lat;
	double lon;

	public city(String name, double mercatorX, double mercatorY, double lat, double lon) {
		super();
		this.name = name;
		this.x = mercatorX;
		this.y = mercatorY;
		this.lat = lat;
		this.lon = lon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getX() {
		return x;
	}

	public void setX(double d) {
		this.x = d;
	}

	public double getY() {
		return y;
	}

	public void setY(double d) {
		this.y = d;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	@Override
	public String toString() {
		return "city{" + "name='" + name + '\'' + ", x=" + x + ", y=" + y + '}';
	}

	public double getMercatorX() {
		return (lon - Graph.org_xMin) / (Graph.org_xMax - Graph.org_xMin) * Graph.yourWidth;
	}

	public double getMercatorY() {
		return (lat - Graph.org_yMin) / (Graph.org_yMax - Graph.org_yMin) * Graph.yourHeight;
	}
}
