package tim.prune.function.srtm;

import tim.prune.data.Coordinate;
import tim.prune.data.DataPoint;

/**
 * Class to represent a single tile of Srtm data, from a single hgt.zip file
 */
public class SrtmTile
{
	/** Latitude in degrees north/south */
	private int _latitude = 0;
	/** Longitude in degrees east/west */
	private int _longitude = 0;

	/**
	 * Constructor working out the tile for a single point
	 * @param inPoint data point
	 */
	public SrtmTile(DataPoint inPoint)
	{
		Coordinate latitude = inPoint.getLatitude();
		_latitude = (int) Math.floor(latitude.getDouble());
		Coordinate longitude = inPoint.getLongitude();
		_longitude = (int) Math.floor(longitude.getDouble());
	}

	/**
	 * Constructor working out the tile for a single point
	 * @param inLatitude latitude in degrees
	 * @param inLongitude longitude in degrees
	 */
	public SrtmTile(int inLatitude, int inLongitude)
	{
		_latitude = inLatitude;
		_longitude = inLongitude;
	}

	@Override
	public int hashCode()
	{
		return _latitude * 1000 + _longitude;
	}

	/**
	 * Check for equality
	 * @param inOther other tile object
	 * @return true if both represent same tile
	 */
	@Override
	public boolean equals(Object inOther)
	{
		if (inOther == null || inOther.getClass() != getClass()) {
			return false;
		}
		SrtmTile otherTile = (SrtmTile) inOther;
		return (_latitude == otherTile._latitude) && (_longitude == otherTile._longitude);
	}

	/** @return latitude as int */
	public int getLatitude() {
		return _latitude;
	}

	/** @return longitude as int */
	public int getLongitude() {
		return _longitude;
	}

	/**
	 * @return filename of tile
	 */
	public String getTileName()
	{
		return (_latitude >= 0?"N":"S")
			+ (Math.abs(_latitude) < 10?"0":"")
			+ Math.abs(_latitude)
			+ (_longitude >= 0?"E":"W")
			+ (Math.abs(_longitude) < 100?"0":"")
			+ (Math.abs(_longitude) < 10?"0":"")
			+ Math.abs(_longitude)
			+ ".hgt.zip";
	}
}
