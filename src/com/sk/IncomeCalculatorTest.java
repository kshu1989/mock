package com.sk;

import org.easymock.EasyMock;
import junit.framework.TestCase;

public class IncomeCalculatorTest extends TestCase {

	public void testCalc1() {
		IncomeCalculator calc = new IncomeCalculator();
		ICalcMethod calcMethod = EasyMock.createMock(ICalcMethod.class);
		EasyMock.expect(calcMethod.calc(Position.BOSS)).andReturn(70000.0)
				.times(2);
		EasyMock.expect(calcMethod.calc(Position.PROGRAMMER))
				.andReturn(50000.0);
		// Setup is finished need to activate the mock
		EasyMock.replay(calcMethod);

		calc.setCalcMethod(calcMethod);
		try {
			calc.calc();
			fail("Exception did not occur");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		calc.setPosition(Position.BOSS);
		assertEquals(70000.0, calc.calc(), 0);
		assertEquals(70000.0, calc.calc(), 0);
		calc.setPosition(Position.PROGRAMMER);
		assertEquals(50000.0, calc.calc(), 0);
		calc.setPosition(Position.SURFER);
		EasyMock.verify(calcMethod);
	}

	public void testCalc2() {
		IncomeCalculator calc = new IncomeCalculator();

		ICalcMethod calcMethod = EasyMock.createMock(ICalcMethod.class);
		EasyMock.expect(calcMethod.calc(EasyMock.anyObject(Position.class)))
				.andReturn(70000.0).times(1,3);

		EasyMock.replay(calcMethod);

		calc.setCalcMethod(calcMethod);
		calc.setPosition(Position.PROGRAMMER);

		assertEquals(70000.0, calc.calc(), 0);

//		System.out.println(calcMethod.calc(Position.SURFER));
		
		EasyMock.verify(calcMethod);
	}

	public void testNoCalc() {
		IncomeCalculator calc = new IncomeCalculator();
		ICalcMethod calcMethod = EasyMock.createMock(ICalcMethod.class);
		calc.setPosition(Position.SURFER);
		calc.calc();
	}

	public void testNoPosition() {
		IncomeCalculator calc = new IncomeCalculator();
		ICalcMethod calcMethod = EasyMock.createMock(ICalcMethod.class);
		EasyMock.expect(calcMethod.calc(Position.BOSS)).andReturn(70000.0);
		EasyMock.replay(calcMethod);
		calc.setCalcMethod(calcMethod);
		calc.calc();
	}

	public void testCalc3() {
		IncomeCalculator calc = new IncomeCalculator();
		ICalcMethod calcMethod = EasyMock.createMock(ICalcMethod.class);
		// Setting up the expected value of the method call calc
		EasyMock.expect(calcMethod.calc(Position.SURFER))
				.andThrow(new RuntimeException("Don't know this guy")).times(1);

		// Setup is finished need to activate the mock
		EasyMock.replay(calcMethod);
		calc.setPosition(Position.SURFER);
		calc.setCalcMethod(calcMethod);
		calc.calc();
	}
}
