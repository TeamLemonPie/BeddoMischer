package de.lemonpie.beddomischer.validator;

import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import de.lemonpie.beddomischer.model.card.Card;
import de.lemonpie.beddomischer.model.card.CardSymbol;
import de.lemonpie.beddomischer.model.card.CardValue;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(BeddoMischerTestRunner.class)
public class CardValidatorTest
{

	@Test
	public void validateDifferentCardShouldPass()
	{
		Assert.assertTrue(CardValidator.getInstance().validateCard(new Card(CardSymbol.CROSS, CardValue.TWO)));
		Assert.assertTrue(CardValidator.getInstance().validateCard(new Card(CardSymbol.CROSS, CardValue.THREE)));
	}

	@Test
	public void validateEqualCardsShouldFail()
	{
		Assert.assertTrue(CardValidator.getInstance().validateCard(new Card(CardSymbol.CROSS, CardValue.TWO)));
		Assert.assertFalse(CardValidator.getInstance().validateCard(new Card(CardSymbol.CROSS, CardValue.TWO)));
	}

	@Test
	public void validateEqualCardsAfterClearShouldPass()
	{
		Assert.assertTrue(CardValidator.getInstance().validateCard(new Card(CardSymbol.CROSS, CardValue.TWO)));
		CardValidator.getInstance().clear(new Card(CardSymbol.CROSS, CardValue.TWO));
		Assert.assertTrue(CardValidator.getInstance().validateCard(new Card(CardSymbol.CROSS, CardValue.TWO)));
	}
}
