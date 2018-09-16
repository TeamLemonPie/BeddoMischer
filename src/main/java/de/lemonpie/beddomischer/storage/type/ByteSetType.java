package de.lemonpie.beddomischer.storage.type;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.StringType;
import de.tobias.logger.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class ByteSetType extends StringType
{

	private static final ByteSetType INSTANCE = new ByteSetType();

	private ByteSetType()
	{
		super(SqlType.BYTE_ARRAY, new Class<?>[]{ByteSetType.class});
	}

	public static ByteSetType getSingleton()
	{
		return INSTANCE;
	}

	@Override
	public Object javaToSqlArg(FieldType fieldType, Object javaObject)
	{
		Set<Byte> byteSet = (Set<Byte>) javaObject;
		if(byteSet != null)
		{
			return Stream.of(byteSet.toArray(new Byte[0]))
					.map(String::valueOf)
					.collect(Collectors.joining(";"));
		}
		else
		{
			return null;
		}
	}

	@Override
	public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos)
	{
		if(sqlArg != null)
		{
			String rawData = (String) sqlArg;
			if(rawData.isEmpty())
			{
				return new HashSet<>();
			}
			String[] rawArray = rawData.split(";");
			final Set<Byte> objects = new HashSet<>();
			for(String element : rawArray)
			{
				try
				{
					objects.add(Byte.valueOf(element));
				}
				catch(NumberFormatException e)
				{
					Logger.debug("Cannot convert {0} from database to byte", element);
				}
			}
			return objects;
		}
		else
		{
			return null;
		}
	}
}