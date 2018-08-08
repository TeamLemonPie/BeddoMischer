package de.lemonpie.beddomischer.storage;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.StringType;
import de.lemonpie.beddocommon.card.Card;

public class CardType extends StringType
{

	private static final CardType INSTANCE = new CardType();

	private CardType()
	{
		super(SqlType.STRING, new Class<?>[]{CardType.class});
	}

	public static CardType getSingleton()
	{
		return INSTANCE;
	}

	@Override
	public Object javaToSqlArg(FieldType fieldType, Object javaObject)
	{
		Card card = (Card) javaObject;
		return card != null ? card.getName() : null;
	}

	@Override
	public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos)
	{
		return sqlArg != null ? Card.fromString((String) sqlArg) : null;
	}
}