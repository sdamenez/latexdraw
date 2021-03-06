package net.sf.latexdraw.gui.hand;

import net.sf.latexdraw.gui.CompositeGUIVoidCommand;
import net.sf.latexdraw.gui.ShapePropInjector;
import net.sf.latexdraw.gui.TestTextStyleGUI;
import net.sf.latexdraw.instruments.Hand;
import net.sf.latexdraw.instruments.MetaShapeCustomiser;
import net.sf.latexdraw.instruments.Pencil;
import net.sf.latexdraw.instruments.ShapeTextCustomiser;
import net.sf.latexdraw.instruments.TextSetter;
import net.sf.latexdraw.models.interfaces.shape.IText;
import net.sf.latexdraw.models.interfaces.shape.TextPosition;
import net.sf.latexdraw.util.Injector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class TestHandTextStyle extends TestTextStyleGUI {

	@Override
	protected Injector createInjector() {
		return new ShapePropInjector() {
			@Override
			protected void configure() throws IllegalAccessException, InstantiationException {
				super.configure();
				pencil = mock(Pencil.class);
				bindAsEagerSingleton(ShapeTextCustomiser.class);
				bindAsEagerSingleton(Hand.class);
				bindToInstance(MetaShapeCustomiser.class, mock(MetaShapeCustomiser.class));
				bindToInstance(TextSetter.class, mock(TextSetter.class));
				bindToInstance(Pencil.class, pencil);
			}
		};
	}

	@Test
	public void testControllerNotActivatedWhenSelectionEmpty() {
		new CompositeGUIVoidCommand(activateHand, updateIns, checkInsDeactivated).execute();
	}

	@Test
	public void testControllerActivatedWhenSelectionGrid() {
		new CompositeGUIVoidCommand(selectionAddText, activateHand, updateIns).execute();
		assertTrue(ins.isActivated());
		assertTrue(titledPane.isVisible());
	}

	@Test
	public void testControllerDeactivatedWhenSelectionNotGrid() {
		new CompositeGUIVoidCommand(selectionAddRec, activateHand, updateIns).execute();
		assertFalse(ins.isActivated());
		assertFalse(titledPane.isVisible());
	}

	@Test
	public void testControllerDeactivatedWhenSelectionEmpty() {
		new CompositeGUIVoidCommand(activateHand, updateIns).execute();
		assertFalse(ins.isActivated());
		assertFalse(titledPane.isVisible());
	}

	@Test
	public void testSelectBLPencil() {
		new CompositeGUIVoidCommand(activateHand, selectionAddText, selectionAddGrid, selectionAddText, updateIns).execute();
		clickOnblButton.execute();
		assertEquals(TextPosition.BOT_LEFT, ((IText)drawing.getSelection().getShapeAt(0)).getTextPosition());
		assertEquals(TextPosition.BOT_LEFT, ((IText)drawing.getSelection().getShapeAt(2)).getTextPosition());
		assertTrue(blButton.isSelected());
	}

	@Test
	public void testSelectBRPencil() {
		new CompositeGUIVoidCommand(activateHand, selectionAddText, selectionAddGrid, selectionAddText, updateIns).execute();
		clickOnbrButton.execute();
		assertEquals(TextPosition.BOT_RIGHT, ((IText)drawing.getSelection().getShapeAt(0)).getTextPosition());
		assertEquals(TextPosition.BOT_RIGHT, ((IText)drawing.getSelection().getShapeAt(2)).getTextPosition());
		assertTrue(brButton.isSelected());
	}

	@Test
	public void testSelectBPencil() {
		new CompositeGUIVoidCommand(activateHand, selectionAddText, selectionAddGrid, selectionAddText, updateIns).execute();
		clickOnbButton.execute();
		assertEquals(TextPosition.BOT, ((IText)drawing.getSelection().getShapeAt(0)).getTextPosition());
		assertEquals(TextPosition.BOT, ((IText)drawing.getSelection().getShapeAt(2)).getTextPosition());
		assertTrue(bButton.isSelected());
	}

	@Test
	public void testSelectTPencil() {
		new CompositeGUIVoidCommand(activateHand, selectionAddText, selectionAddGrid, selectionAddText, updateIns).execute();
		clickOntButton.execute();
		assertEquals(TextPosition.TOP, ((IText)drawing.getSelection().getShapeAt(0)).getTextPosition());
		assertEquals(TextPosition.TOP, ((IText)drawing.getSelection().getShapeAt(2)).getTextPosition());
		assertTrue(tButton.isSelected());
	}

	@Test
	public void testSelectTLPencil() {
		new CompositeGUIVoidCommand(activateHand, selectionAddText, selectionAddGrid, selectionAddText, updateIns).execute();
		clickOntlButton.execute();
		assertEquals(TextPosition.TOP_LEFT, ((IText)drawing.getSelection().getShapeAt(0)).getTextPosition());
		assertEquals(TextPosition.TOP_LEFT, ((IText)drawing.getSelection().getShapeAt(2)).getTextPosition());
		assertTrue(tlButton.isSelected());
	}

	@Test
	public void testSelectTRPencil() {
		new CompositeGUIVoidCommand(activateHand, selectionAddText, selectionAddGrid, selectionAddText, updateIns).execute();
		clickOntrButton.execute();
		assertEquals(TextPosition.TOP_RIGHT, ((IText)drawing.getSelection().getShapeAt(0)).getTextPosition());
		assertEquals(TextPosition.TOP_RIGHT, ((IText)drawing.getSelection().getShapeAt(2)).getTextPosition());
		assertTrue(trButton.isSelected());
	}

	@Test
	public void testSelectRPencil() {
		new CompositeGUIVoidCommand(activateHand, selectionAddText, selectionAddGrid, selectionAddText, updateIns).execute();
		clickOnrButton.execute();
		assertEquals(TextPosition.RIGHT, ((IText)drawing.getSelection().getShapeAt(0)).getTextPosition());
		assertEquals(TextPosition.RIGHT, ((IText)drawing.getSelection().getShapeAt(2)).getTextPosition());
		assertTrue(rButton.isSelected());
	}

	@Test
	public void testSelectLPencil() {
		new CompositeGUIVoidCommand(activateHand, selectionAddText, selectionAddGrid, selectionAddText, updateIns).execute();
		clickOnlButton.execute();
		assertEquals(TextPosition.LEFT, ((IText)drawing.getSelection().getShapeAt(0)).getTextPosition());
		assertEquals(TextPosition.LEFT, ((IText)drawing.getSelection().getShapeAt(2)).getTextPosition());
		assertTrue(lButton.isSelected());
	}

	@Test
	public void testSelectCentrePencil() {
		new CompositeGUIVoidCommand(activateHand, selectionAddText, selectionAddGrid, selectionAddText, updateIns).execute();
		clickOncentreButton.execute();
		assertEquals(TextPosition.CENTER, ((IText)drawing.getSelection().getShapeAt(0)).getTextPosition());
		assertEquals(TextPosition.CENTER, ((IText)drawing.getSelection().getShapeAt(2)).getTextPosition());
		assertTrue(centreButton.isSelected());
	}
}
