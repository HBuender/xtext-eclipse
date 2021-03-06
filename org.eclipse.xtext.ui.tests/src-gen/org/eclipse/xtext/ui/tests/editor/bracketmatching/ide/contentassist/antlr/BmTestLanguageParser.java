/*
 * generated by Xtext
 */
package org.eclipse.xtext.ui.tests.editor.bracketmatching.ide.contentassist.antlr;

import com.google.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.tests.editor.bracketmatching.ide.contentassist.antlr.internal.InternalBmTestLanguageParser;
import org.eclipse.xtext.ui.tests.editor.bracketmatching.services.BmTestLanguageGrammarAccess;

public class BmTestLanguageParser extends AbstractContentAssistParser {

	@Inject
	private BmTestLanguageGrammarAccess grammarAccess;

	private Map<AbstractElement, String> nameMappings;

	@Override
	protected InternalBmTestLanguageParser createParser() {
		InternalBmTestLanguageParser result = new InternalBmTestLanguageParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}

	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getExpressionAccess().getAlternatives(), "rule__Expression__Alternatives");
					put(grammarAccess.getSExpressionAccess().getAlternatives_1(), "rule__SExpression__Alternatives_1");
					put(grammarAccess.getVALUEAccess().getAlternatives(), "rule__VALUE__Alternatives");
					put(grammarAccess.getExpressionAccess().getGroup_2(), "rule__Expression__Group_2__0");
					put(grammarAccess.getSExpressionAccess().getGroup(), "rule__SExpression__Group__0");
					put(grammarAccess.getSExpressionAccess().getGroup_1_0(), "rule__SExpression__Group_1_0__0");
					put(grammarAccess.getSExpressionAccess().getGroup_1_1(), "rule__SExpression__Group_1_1__0");
					put(grammarAccess.getFileAccess().getExpressionAssignment(), "rule__File__ExpressionAssignment");
					put(grammarAccess.getSExpressionAccess().getElementAssignment_1_0_1(), "rule__SExpression__ElementAssignment_1_0_1");
					put(grammarAccess.getSExpressionAccess().getElementAssignment_1_1_1(), "rule__SExpression__ElementAssignment_1_1_1");
					put(grammarAccess.getAtomAccess().getValueAssignment(), "rule__Atom__ValueAssignment");
				}
			};
		}
		return nameMappings.get(element);
	}

	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			InternalBmTestLanguageParser typedParser = (InternalBmTestLanguageParser) parser;
			typedParser.entryRuleFile();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}

	public BmTestLanguageGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(BmTestLanguageGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
