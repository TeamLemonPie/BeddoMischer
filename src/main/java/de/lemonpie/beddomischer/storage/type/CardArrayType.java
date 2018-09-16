package de.lemonpie.beddomischer.storage.type;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.StringType;
import de.lemonpie.beddocommon.model.card.Card;

public class CardArrayType extends StringType
{

	private static final CardArrayType INSTANCE = new CardArrayType();

	private CardArrayType()
	{
		super(SqlType.STRING, new Class<?>[]{CardArrayType.class});
	}

	public static CardArrayType getSingleton()
	{
		return INSTANCE;
	}

	@Override
	public Object javaToSqlArg(FieldType fieldType, Object javaObject)
	{
		Card[] cards = (Card[]) javaObject;
		if(cards != null)
		{
			String[] strings = new String[cards.length];
			for(int i = 0; i < cards.length; i++)
			{
				strings[i] = cards[i].getName();
			}

			return String.join(";", strings);
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
			final String[] data = rawData.split(";");
			final Card[] result = new Card[data.length];
			for(int i = 0; i < data.length; i++)
			{
				result[i] = Card.fromString(data[i]);
			}
			return result;
		}
		else
		{
			return null;
		}
	}
}