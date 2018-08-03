package de.lemonpie.beddomischer.validator;

import de.lemonpie.beddomischer.BeddoMischerTestRunner;
import de.lemonpie.beddomischer.model.card.Card;
import de.lemonpie.beddomischer.model.card.CardSymbol;
import de.lemonpie.beddomischer.model.card.CardValue;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(BeddoMischerTestRunner.class)
public class CardValidatorTest
{

	@Test
	public void validateDifferentCardShouldPass()
	{
		assertThat(CardValidator.getInstance().validateCard(new Card(CardSymbol.CROSS, CardValue.TWO))).isTrue();
		assertThat(CardValidator.getInstance().validateCard(new Card(CardSymbol.CROSS, CardValue.THREE))).isTrue();
	}

	@Test
	public void validateEqualCardsShouldFail()
	{
		assertThat(CardValidator.getInstance().validateCard(new Card(CardSymbol.CROSS, CardValue.TWO))).isTrue();
		assertThat(CardValidator.getInstance().validateCard(new Card(CardSymbol.CROSS, CardValue.TWO))).isFalse();
	}

	@Test
	public void validateEqualCardsAfterClearShouldPass()
	{
		assertThat(CardValidator.getInstance().validateCard(new Card(CardSymbol.CROSS, CardValue.TWO))).isTrue();
		CardValidator.getInstance().clear(new Card(CardSymbol.CROSS, CardValue.TWO));
		assertThat(CardValidator.getInstance().validateCard(new Card(CardSymbol.CROSS, CardValue.TWO))).isTrue();
	}
}
