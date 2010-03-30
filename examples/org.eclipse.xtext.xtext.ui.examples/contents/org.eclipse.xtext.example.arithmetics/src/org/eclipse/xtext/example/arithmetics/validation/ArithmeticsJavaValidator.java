/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.example.arithmetics.validation;

import java.math.BigDecimal;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.example.arithmetics.arithmetics.Evaluation;
import org.eclipse.xtext.example.arithmetics.arithmetics.Expression;
import org.eclipse.xtext.example.arithmetics.arithmetics.FunctionCall;
import org.eclipse.xtext.example.arithmetics.arithmetics.NumberLiteral;
import org.eclipse.xtext.example.arithmetics.arithmetics.Pi;
import org.eclipse.xtext.example.arithmetics.interpreter.Calculator;
import org.eclipse.xtext.validation.Check;
 

/**
 * @author Sven Efftinge - initial contribution and API
 */
public class ArithmeticsJavaValidator extends AbstractArithmeticsJavaValidator {

	public final static String NORMALIZABLE = "normalizable-expression";
	@Check
	public void checkNormalizable(Expression expr) {
		// ignore literals
		if ((expr instanceof NumberLiteral) 
			|| (expr instanceof Pi)
			|| (expr instanceof FunctionCall)
			) 
			return;
		// ignore evaluations
		if (EcoreUtil2.getContainerOfType(expr, Evaluation.class)!=null)
			return;
		
		TreeIterator<EObject> contents = expr.eAllContents();
		while(contents.hasNext()) {
			EObject next = contents.next();
			if (next instanceof FunctionCall) {
				return;
			}
		}
		BigDecimal decimal = new Calculator().evaluate(expr);
		if (decimal.toString().length()<=8) {
			warning("Expression could be normalized to constant '"+decimal+"'", -1,NORMALIZABLE,decimal.toString());
		}
	}

}
